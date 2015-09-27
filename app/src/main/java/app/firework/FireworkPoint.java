package app.firework;

public class FireworkPoint implements Cloneable {
    private int mColor[];
    private float mCoord[];
    private float mSpeed[];
    private float mNormal[];
    private int mLifeTime;
    private int mExpTime;
    private String mWhatExplose;

    public float[] getCoord() {
        if (mCoord == null) {
            mCoord = new float[]{0, 0, 0};
        }
        return mCoord;
    }

    public void setCoord(float[] mCoord) {
        this.mCoord = mCoord;
    }

    public float[] getSpeed() {
        if (mSpeed == null) {
            mSpeed = new float[]{0, 0, 0};
        }
        return mSpeed;
    }

    public void setSpeed(float[] mSpeed) {
        this.mSpeed = mSpeed;
    }

    public float[] getNormal() {
        if (mNormal == null) {
            mNormal = new float[]{0, 0, 0};
        }
        return mNormal;
    }

    public void setNormal(float[] mNormal) {
        this.mNormal = mNormal;
    }

    public int getLifeTime() {
        return mLifeTime;
    }

    public void setLifeTime(int mLifeTime) {
        this.mLifeTime = mLifeTime;
    }

    public int getExpTime() {
        return mExpTime;
    }

    public void setExpTime(int mExpTime) {
        this.mExpTime = mExpTime;
    }

    public int[] getColor() {
        if (mColor == null) {
            mColor = new int[]{255, 255, 255, 255};
        }
        return mColor;
    }

    public void setColor(int mColor[]) {
        this.mColor = mColor;
    }

    public String getWhatExplose() {
        if (mWhatExplose == null) {
            mWhatExplose = "";
        }
        return mWhatExplose;
    }

    public void setWhatExplose(String mWhatExplose) {
        this.mWhatExplose = mWhatExplose;
    }

}
