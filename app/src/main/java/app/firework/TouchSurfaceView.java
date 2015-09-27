package app.firework;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.view.MotionEvent;

/**
 * Implement a simple rotation control.
 */
public class TouchSurfaceView extends GLSurfaceView {
    public static String mFireworkName = "salut_with_pol";
    private final float TOUCH_SCALE_FACTOR = 180.0f / 320;
    private final float TRACKBALL_SCALE_FACTOR = 36.0f;
    private boolean mTouch = false;
    private SceneRenderer mRenderer;
    private float mPreviousX;
    private float mPreviousY;

    public TouchSurfaceView(Context context) {
        super(context);
        setRenderer(getSceneRenderer());
        setDrawingCacheEnabled(true);

    }

    @Override
    public boolean onTrackballEvent(MotionEvent e) {
        getSceneRenderer().addAngleX(e.getX() * TRACKBALL_SCALE_FACTOR);
        getSceneRenderer().addAngleY(e.getY() * TRACKBALL_SCALE_FACTOR);
        requestRender();
        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        float x = e.getX();
        float y = e.getY();
        switch (e.getAction()) {
            case MotionEvent.ACTION_MOVE:
                float dx = x - mPreviousX;
                float dy = y - mPreviousY;
                getSceneRenderer().addAngleX(dx * TOUCH_SCALE_FACTOR);
                getSceneRenderer().addAngleY(dy * TOUCH_SCALE_FACTOR);
                requestRender();
                mTouch = false;
                break;

            case MotionEvent.ACTION_DOWN:
                mTouch = true;
                break;

            case MotionEvent.ACTION_UP:
                if (mTouch == true) {
                    getSceneRenderer().createFireWork(mFireworkName,
                            new float[]{1, -300, 1});
                }
                break;
        }
        mPreviousX = x;
        mPreviousY = y;
        return true;
    }

    public SceneRenderer getSceneRenderer() {
        if (mRenderer == null) {
            mRenderer = new SceneRenderer(getContext());
        }
        return mRenderer;
    }
}
