package app.firework.updaters;

import app.firework.FireworkPoint;

public interface IUpdater {
    int MOVE_POINT = 0;
    int DELETE_POINT = 1;
    int EXPLOSE_POINT = 2;

    int update(FireworkPoint point);
}
