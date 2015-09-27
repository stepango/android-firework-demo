package app.firework.xml;

public class PointParams {
    private int mID;
    private float[] mAmount;
    private float[] mPosition;
    private float[] mNormal;
    private float[] mMatSpeed;
    private float[] mDivSpeed;
    private float[] mMatColor;
    private float[] mDivColor;
    private float[] mTimeLife;
    private float[] mExplosionTime;
    private String mWhatExplose;

    public int getID() {
        return mID;
    }

    public void setID(int id) {
        this.mID = id;
    }

    public float[] getAmount() {
        if (mAmount == null) {
            mAmount = new float[]{};
        }
        return mAmount;
    }

    public void setAmount(float[] count) {
        this.mAmount = count;
    }

    public float[] getPosition() {
        if (mPosition == null) {
            mPosition = new float[]{};
        }
        return mPosition;
    }

    public void setPosition(float[] position) {
        this.mPosition = position;
    }

    public float[] getNormal() {
        if (mNormal == null) {
            mNormal = new float[]{};
        }
        return mNormal;
    }

    public void setNormal(float[] normal) {
        this.mNormal = normal;
    }

    public float[] getMatSpeed() {
        if (mMatSpeed == null) {
            mMatSpeed = new float[]{};
        }
        return mMatSpeed;
    }

    public void setMatSpeed(float[] mSpeed) {
        this.mMatSpeed = mSpeed;
    }

    public float[] getDivSpeed() {
        if (mDivSpeed == null) {
            mDivSpeed = new float[]{};
        }
        return mDivSpeed;
    }

    public void setDivSpeed(float[] dSpeed) {
        this.mDivSpeed = dSpeed;
    }

    public float[] getMatColor() {
        if (mMatColor == null) {
            mMatColor = new float[]{};
        }
        return mMatColor;
    }

    public void setMatColor(float[] mColor) {
        this.mMatColor = mColor;
    }

    public float[] getDivColor() {
        if (mDivColor == null) {
            mDivColor = new float[]{};
        }
        return mDivColor;
    }

    public void setDivColor(float[] dColor) {
        this.mDivColor = dColor;
    }

    public float[] getTimeLife() {
        if (mTimeLife == null) {
            mTimeLife = new float[]{};
        }
        return mTimeLife;
    }

    public void setTimeLife(float[] timeLife) {
        this.mTimeLife = timeLife;
    }

    public float[] getExplosionTime() {
        if (mExplosionTime == null) {
            mExplosionTime = new float[]{};
        }
        return mExplosionTime;
    }

    public void setExplosionTime(float[] explosionTime) {
        this.mExplosionTime = explosionTime;
    }

    public String getWhatExplose() {
        if (mWhatExplose == null) {
            mWhatExplose = "";
        }
        return mWhatExplose;
    }

    public void setWhatExplose(String whatExplose) {
        this.mWhatExplose = whatExplose;
    }

}
