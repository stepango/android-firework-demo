package app.firework;

import android.content.Context;
import android.opengl.GLSurfaceView.Renderer;
import android.opengl.GLU;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import app.firework.test.CoordSystem;
import app.firework.updaters.IUpdater;
import app.firework.xml.GroupParams;
import app.firework.xml.XMLReader;

public class SceneRenderer implements Renderer {

    private final Context mContext;
    private FWGenerator mGenerator;
    private FWBufferOperations mFWBuffer;
    private FWCamera mCamera;
    private float mAngleX = 0f;
    private float mAngleY = 0f;
    private List<FWGroup> mGroups;
    private int mSignal = 0;
    private LinkedList<ExpParams> mExpList;
    private boolean mIsOnPause = false;
    private boolean mTestMode = true;
    private CoordSystem mCoSystem;
    private Map<String, List<GroupParams>> mGroupParamsMap;

    public SceneRenderer(Context context) {
        mContext = context;
        mFWBuffer = new FWBufferOperations();
        mGenerator = new FWGenerator();
        mCamera = new FWCamera();
        mExpList = new LinkedList<>();
        if (mTestMode) {
            mCoSystem = new CoordSystem();
        }
    }

    public boolean getIsOnPause() {
        return mIsOnPause;
    }

    public void setIsOnPause(boolean mIsOnPause) {
        this.mIsOnPause = mIsOnPause;
    }

    public Map<String, List<GroupParams>> getmGroupParamsMap() {
        if (mGroupParamsMap == null) {
            mGroupParamsMap = XMLReader.parseXMLFiles("sdcard/");
        }
        return mGroupParamsMap;
    }

    public void setmGroupParamsMap(
            Map<String, List<GroupParams>> mGroupParamsMap) {
        this.mGroupParamsMap = mGroupParamsMap;
    }

    public void setPauseOnOff() {
        mIsOnPause = !mIsOnPause;
    }

    public synchronized void onDrawFrame(GL10 gl) {
        init(gl);
        if (!mIsOnPause) {
            Update(gl);
        }
        if (mTestMode) {
            mCoSystem.draw(gl);
        }
        drawPoints(gl);
        updateCamera(gl);
    }

    public void onSurfaceChanged(GL10 gl, int width, int height) {
        gl.glViewport(0, 0, width, height);
        gl.glMatrixMode(GL10.GL_PROJECTION);
        gl.glLoadIdentity();
        int scale;
        if (height > width) {
            scale = height / width;
        } else {
            scale = width / height;
        }
        GLU.gluPerspective(gl, 60f, scale, 1f, 3000f);
    }

    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        gl.glDisable(GL10.GL_DITHER);
        gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_FASTEST);
        gl.glClearColor(0, 0, 0, 255);
        gl.glEnable(GL10.GL_CULL_FACE);
        gl.glShadeModel(GL10.GL_SMOOTH);
        gl.glEnable(GL10.GL_DEPTH_TEST);
    }

    private void init(GL10 gl) {
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glEnableClientState(GL10.GL_COLOR_ARRAY);
        gl.glPointSize(2 * mContext.getResources().getDisplayMetrics().density);
        gl.glFrontFace(GL10.GL_CW);
    }

    private void Update(GL10 gl) {
        Iterator<FWGroup> iterGroup = getGroups().iterator();
        while (iterGroup.hasNext()) {
            FWGroup group = iterGroup.next();
            Iterator<IUpdater> iterUpdater;
            iterUpdater = group.getUpdaters().iterator();
            while (iterUpdater.hasNext()) {
                Iterator<FireworkPoint> iterPoint = group.getPoints().iterator();
                IUpdater updater = iterUpdater.next();
                while (iterPoint.hasNext()) {
                    FireworkPoint point = iterPoint.next();
                    mSignal = updater.update(point);
                    switch (mSignal) {
                        case IUpdater.DELETE_POINT:
                            iterPoint.remove();
                            break;
                        case IUpdater.EXPLOSE_POINT:
                            mExpList.add(new ExpParams(point.getWhatExplose(),
                                    point.getCoord()));
                            iterPoint.remove();
                            break;
                        default:
                            break;
                    }
                }
            }
            if (group.getPoints().size() == 0) {
                try {
                    iterGroup.remove();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        Iterator<ExpParams> iterExpParams = mExpList.iterator();
        while (iterExpParams.hasNext()) {
            ExpParams expParams = iterExpParams.next();
            createFireWork(expParams.getmWhatExplose(), expParams
                    .getmExplosionCoord());
            iterExpParams.remove();
        }
    }

    private void drawPoints(GL10 gl) {
        mFWBuffer.groupToBuffer(getGroups());
        if (mFWBuffer.getCoords() != null && mFWBuffer.getColors() != null) {
            gl.glVertexPointer(3, GL10.GL_FLOAT, 0, mFWBuffer.getCoords());
            gl.glColorPointer(4, GL10.GL_FIXED, 0, mFWBuffer.getColors());
            gl.glDrawArrays(GL10.GL_POINTS, 0,
                    mFWBuffer.getCoords().limit() / 3);
        }
    }

    private void updateCamera(GL10 gl) {
        mCamera.MoveToNewPosition(gl, mAngleX, mAngleY);
    }

    public synchronized void createFireWork(String path, float[] coord) {
        List<GroupParams> gp = getmGroupParamsMap().get(path);
        getGroups().addAll(mGenerator.generate(gp, coord));
    }

    public List<FWGroup> getGroups() {
        if (mGroups == null) {
            mGroups = new LinkedList<>();
        }
        return mGroups;
    }

    public void addAngleY(float angleY) {
        this.mAngleY += angleY;
    }

    public void addAngleX(float angleX) {
        this.mAngleX += angleX;
    }
}
