package app.firework;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import app.firework.xml.GroupParams;
import app.firework.xml.PointParams;

public class FWGenerator {
    private static final int RAND_CONST = 1;
    private Random mRandom = new Random();

    public float gauss(float mx, float sigma) {
        float a, b, r;
        do {
            a = 2 * (mRandom.nextFloat() / RAND_CONST) - 1;
            b = 2 * (mRandom.nextFloat() / RAND_CONST) - 1;
            r = a * a + b * b;
        } while (!(r < 1));
        float Sq = (float) Math.sqrt(-2 * Math.log(r) / r);
        return (mx + sigma * a * Sq);
    }

    public float normaly(float mx, float sigma) {
        float rf = 2 * mRandom.nextFloat() - 1;
        if (rf < -1) {
            rf = -1;
        }
        if (rf > 1) {
            rf = 1;
        }
        return mx + sigma * rf;
    }

    public List<FWGroup> generate(List<GroupParams> groupParams,
                                  float[] position) {
        List<FWGroup> groups = new LinkedList<FWGroup>();

        FWGroup currentGroup = new FWGroup();
        int countCopyCurrentPointParam;
        Iterator<GroupParams> iterGP = groupParams.iterator();
        while (iterGP.hasNext()) {
            GroupParams gp = iterGP.next();
            currentGroup.setName(gp.getName());

            currentGroup.setType(gp.getType());

            currentGroup.setSize(new float[]{gp.getSize()[0],
                    gp.getSize()[1], gp.getSize()[2]});

            currentGroup.setPosition(position);

            currentGroup.getUpdaters().addAll(gp.getUpdatersList());

            List<PointParams> pointParams = gp.getPointParams();
            Iterator<PointParams> iterPP = pointParams.iterator();
            while (iterPP.hasNext()) {
                PointParams pp = iterPP.next();
                countCopyCurrentPointParam = (int) (gauss(pp.getAmount()[1], pp
                        .getAmount()[0]));
                for (int k = 0; k < countCopyCurrentPointParam; k++) {
                    FireworkPoint currentPoint = new FireworkPoint();

                    currentPoint.setCoord(new float[]{
                            pp.getPosition()[0] + position[0],
                            pp.getPosition()[1] + position[1],
                            pp.getPosition()[2] + position[2]});

                    currentPoint.setNormal(new float[]{pp.getNormal()[0],
                            pp.getNormal()[1], pp.getNormal()[2]});

                    currentPoint
                            .setSpeed(new float[]{
                                    gauss(pp.getMatSpeed()[0], pp
                                            .getDivSpeed()[0]),
                                    gauss(pp.getMatSpeed()[1], pp
                                            .getDivSpeed()[1]),
                                    gauss(pp.getMatSpeed()[2], pp
                                            .getDivSpeed()[2])});
                    currentPoint.setColor(new int[]{
                            (int) (65535 * gauss(pp.getMatColor()[0], pp
                                    .getDivColor()[0])),
                            (int) (65535 * gauss(pp.getMatColor()[1], pp
                                    .getDivColor()[1])),
                            (int) (65535 * gauss(pp.getMatColor()[2], pp
                                    .getDivColor()[2])),
                            (int) (65535 * gauss(pp.getMatColor()[3], pp
                                    .getDivColor()[3]))});

                    currentPoint.setLifeTime((int) (gauss(pp.getTimeLife()[1],
                            pp.getTimeLife()[0])));

                    currentPoint
                            .setExpTime((int) (gauss(pp.getExplosionTime()[1],
                                    pp.getExplosionTime()[0])));

                    currentPoint.setWhatExplose(pp.getWhatExplose());
                    currentGroup.getPoints().add(currentPoint);

                }

            }
            groups.add(currentGroup);
        }
        return groups;

    }
}
