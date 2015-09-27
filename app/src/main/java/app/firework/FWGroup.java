package app.firework;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import app.firework.updaters.IUpdater;

public class FWGroup {
    private List<FireworkPoint> mPoints;
    private List<IUpdater> mUpdaters;
    private float[] mSize;
    private String mName;
    private int mType;
    private float[] mPosition;

    public float[] getPosition() {
        if (mPosition == null) {
            mPosition = new float[3];
        }
        return mPosition;
    }

    public void setPosition(float[] position) {
        this.mPosition = position;
    }

    public float[] getSize() {
        if (mSize == null) {
            mSize = new float[3];
        }
        return mSize;
    }

    public void setSize(float[] size) {
        this.mSize = size;
    }

    public int getType() {
        return mType;
    }

    public void setType(int type) {
        this.mType = type;
    }

    public String getName() {
        if (mName == null) {
            mName = "";
        }
        return mName;
    }

    public void setName(String name) {
        this.mName = name;
    }

    public List<FireworkPoint> getPoints() {
        if (mPoints == null) {
            mPoints = new LinkedList<FireworkPoint>();
        }
        return mPoints;
    }

    public void setPoints(List<FireworkPoint> mPoints) {
        this.mPoints = mPoints;
    }

    public List<IUpdater> getUpdaters() {
        if (mUpdaters == null) {
            mUpdaters = new ArrayList<IUpdater>(4);
        }
        return mUpdaters;
    }

    public void setUpdaters(List<IUpdater> mUpdaters) {
        this.mUpdaters = mUpdaters;
    }
}
