package app.firework.updaters;

import app.firework.FireworkPoint;

public class DeleteUpdater implements IUpdater {
    public int update(FireworkPoint point) {
        if (point != null) {
            int time;
            time = point.getLifeTime();
            if (time > 0) {
                time--;
                point.setLifeTime(time);
                return MOVE_POINT;
            } else {
                return DELETE_POINT;
            }
        }
        return DELETE_POINT;
    }
}
