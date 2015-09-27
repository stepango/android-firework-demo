package app.firework;

public class ExpParams {
    private String mWhatExplose = "";
    private float[] mExplosionCoord = {0f, 0f, 0f};

    public ExpParams(String adress, float[] coord) {
        this.mWhatExplose = adress;
        this.mExplosionCoord = coord;
    }

    public String getmWhatExplose() {
        return mWhatExplose;
    }

    public void setmWhatExplose(String mWhatExplose) {
        this.mWhatExplose = mWhatExplose;
    }

    public float[] getmExplosionCoord() {
        return mExplosionCoord;
    }

    public void setmExplosionCoord(float[] mExplosionCoord) {
        this.mExplosionCoord = mExplosionCoord;
    }
}
