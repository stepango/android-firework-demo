package app.firework.test;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import javax.microedition.khronos.opengles.GL10;

public class CoordSystem {
    public FloatBuffer mVertexBuffer;
    public IntBuffer mColorBuffer;
    public ByteBuffer mIndexBuffer;

    public CoordSystem() {
        int one = 65535;
        float coord = 1000;
        float vertices[] = {
                coord, -300, 0,
                -coord, -300, 0,
                0, -300, coord,
                0, -300, -coord,
                0, coord - 300, 0,
                0, -300, 0};

        int colors[] = {
                one, one, one, one,
                one, one, one, one,
                one, one, one, one,
                one, one, one, one,
                one, one, one, one,
                one, one, one, one,
        };

        byte indices[] = {0, 5, 5, 1, 1, 5, 5, 2, 2, 5, 5, 3, 3, 5, 5, 4};

        ByteBuffer vbb = ByteBuffer.allocateDirect(vertices.length * 8);
        vbb.order(ByteOrder.nativeOrder());
        mVertexBuffer = vbb.asFloatBuffer();
        mVertexBuffer.put(vertices);
        mVertexBuffer.position(0);

        ByteBuffer cbb = ByteBuffer.allocateDirect(colors.length * 4);
        cbb.order(ByteOrder.nativeOrder());
        mColorBuffer = cbb.asIntBuffer();
        mColorBuffer.put(colors);
        mColorBuffer.position(0);

        mIndexBuffer = ByteBuffer.allocateDirect(indices.length);
        mIndexBuffer.put(indices);
        mIndexBuffer.position(0);
    }

    public void draw(GL10 gl) {
        gl.glFrontFace(GL10.GL_CW);
        gl.glVertexPointer(3, GL10.GL_FLOAT, 0, mVertexBuffer);
        gl.glColorPointer(4, GL10.GL_FIXED, 0, mColorBuffer);
        gl.glDrawElements(GL10.GL_LINE_STRIP, 16, GL10.GL_UNSIGNED_BYTE,
                mIndexBuffer);
    }
}
