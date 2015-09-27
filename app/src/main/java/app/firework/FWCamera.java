package app.firework;

import javax.microedition.khronos.opengles.GL10;

public class FWCamera {
    public void MoveToNewPosition(GL10 gl, float angleX, float angleY) {
        gl.glMatrixMode(GL10.GL_MODELVIEW);
        gl.glLoadIdentity();
        gl.glTranslatef(0, 0, -1000.0f);
        gl.glRotatef(angleY, 1f, 0f, 0f);
        gl.glRotatef(angleX, 0, 1, 0);
    }
}
