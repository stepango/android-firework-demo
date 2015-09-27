package app.firework;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.List;

public class FWBufferOperations {

    private IntBuffer mColors = null;
    private FloatBuffer mCoords = null;

    public void groupToBuffer(List<FWGroup> array) {
        int n = 0;
        int m;
        for (FWGroup fwGroup : array) {
            n += fwGroup.getPoints().size();
        }
        m = n * 3;
        n *= 4;
        int[] massColors = new int[n];
        float[] massCoords = new float[m];
        int[] color;
        float[] coord;
        int i = 0;
        int j = 0;
        for (FWGroup currentGroup : array) {
            List<FireworkPoint> points = currentGroup.getPoints();
            for (FireworkPoint currentPoint : points) {
                color = currentPoint.getColor();
                coord = currentPoint.getCoord();
                massColors[i] = color[0];
                i++;
                massColors[i] = color[1];
                i++;
                massColors[i] = color[2];
                i++;
                massColors[i] = color[3];
                i++;
                massCoords[j] = coord[0];
                j++;
                massCoords[j] = coord[1];
                j++;
                massCoords[j] = coord[2];
                j++;

            }
        }
        FloatBuffer floatBuffer = ByteBuffer
                .allocateDirect(massCoords.length * 4)
                .order(ByteOrder.nativeOrder())
                .asFloatBuffer();
        floatBuffer.put(massCoords);
        floatBuffer.position(0);
        mCoords = floatBuffer;

        IntBuffer intBuffer = ByteBuffer
                .allocateDirect(massColors.length * 4)
                .order(ByteOrder.nativeOrder())
                .asIntBuffer();
        intBuffer.put(massColors);
        intBuffer.position(0);
        mColors = intBuffer;

    }

    public IntBuffer getColors() {
        return mColors;
    }

    public FloatBuffer getCoords() {
        return mCoords;
    }

}