package app.firework.updaters;

import app.firework.FireworkPoint;

public class ExplosiveUpdater implements IUpdater {

    public int update(FireworkPoint point) {
        if (point != null) {
            int time;
            time = point.getExpTime();
            if (time > 0) {
                time--;
                point.setExpTime(time);
                return MOVE_POINT;
            } else {
                return EXPLOSE_POINT;
            }
        }
        return EXPLOSE_POINT;
    }
}
