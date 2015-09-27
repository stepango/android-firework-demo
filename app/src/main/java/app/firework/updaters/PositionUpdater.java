package app.firework.updaters;

import app.firework.FireworkPoint;

public class PositionUpdater implements IUpdater {

    public int update(FireworkPoint point) {
        if (point != null) {
            float coord[], speed[];
            speed = point.getSpeed();
            coord = point.getCoord();
            for (int i = 0; i < 3; i++) {
                coord[i] += speed[i];
            }
            speed[1] -= 0.1;
            return MOVE_POINT;
        }
        return DELETE_POINT;
    }

}
