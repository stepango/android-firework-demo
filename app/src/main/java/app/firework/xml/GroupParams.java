package app.firework.xml;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import app.firework.updaters.IUpdater;

public class GroupParams {
    private List<PointParams> mPointParams;
    private List<IUpdater> mUpdatersList;
    private float[] mSize;
    private String mName;
    private int mType;
    private int mID;

    public int getID() {
        return mID;
    }

    public void setID(int id) {
        this.mID = id;
    }

    public List<PointParams> getPointParams() {
        if (mPointParams == null) {
            mPointParams = new LinkedList<>();
        }
        return mPointParams;
    }

    public void setPointParams(ArrayList<PointParams> pointParams) {
        this.mPointParams = pointParams;
    }

    public List<IUpdater> getUpdatersList() {
        if (mUpdatersList == null) {
            mUpdatersList = new LinkedList<>();
        }
        return mUpdatersList;
    }

    public void setUpdatersList(List<IUpdater> updatersList) {
        this.mUpdatersList = updatersList;
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

    public int getType() {
        return mType;
    }

    public void setType(int type) {
        this.mType = type;
    }

    public float[] getSize() {
        if (mSize == null) {
            mSize = new float[]{};
        }
        return mSize;
    }

    public void setSize(float[] size) {
        this.mSize = size;
    }
}
