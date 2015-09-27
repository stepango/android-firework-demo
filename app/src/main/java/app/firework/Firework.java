package app.firework;

import android.app.Activity;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.FrameLayout;

import java.io.File;
import java.util.List;
import java.util.Map;

import app.firework.xml.GroupParams;
import app.firework.xml.XMLReader;
import app.firework.xml.XMLWriter;

public class Firework extends Activity implements OnClickListener {
    public static TouchSurfaceView mGLSurfaceView;
    public static File mFolder = new File(Environment.getExternalStorageDirectory().getPath(), "Firework");
    private Button mPauseButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!mFolder.mkdirs()) {
            Log.e("Firework", "Error creating dir");
        }
        setContentView(R.layout.firework);
        FrameLayout fl = (FrameLayout) findViewById(R.id.frame);
        mGLSurfaceView = new TouchSurfaceView(this);
        fl.addView(mGLSurfaceView, new LayoutParams(LayoutParams.MATCH_PARENT,
                LayoutParams.MATCH_PARENT));
        mGLSurfaceView.requestFocus();
        mGLSurfaceView.setFocusableInTouchMode(true);

        mPauseButton = (Button) findViewById(R.id.pause);
        mPauseButton.setOnClickListener(this);

        XmlResourceParser p1 = getResources().getXml(R.xml.salut_with_pol);
        XmlResourceParser p2 = getResources().getXml(R.xml.standart_salut);
        Map<String, List<GroupParams>> mGroupParamsMap;
        mGroupParamsMap = XMLReader.parseXMLResources(new XmlResourceParser[]{
                p1, p2}, new String[]{"salut_with_pol.xml",
                "standart_salut.xml"});
        XMLWriter.writeXML("/sdcard/Firework/", mGroupParamsMap);
        mGLSurfaceView.getSceneRenderer().setmGroupParamsMap(mGroupParamsMap);

    }

    @Override
    protected void onResume() {
        super.onResume();
        mGLSurfaceView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mGLSurfaceView.onPause();
    }

    public void onClick(View v) {
        if (v.equals(mPauseButton)) {
            mGLSurfaceView.getSceneRenderer().setPauseOnOff();
        }
    }

}
