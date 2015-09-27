package app.firework.xml;

import android.util.Xml;

import org.xmlpull.v1.XmlSerializer;

import java.io.FileOutputStream;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import app.firework.updaters.IUpdater;

public class XMLWriter {

    public static void writeXML(String folderPath,
                                Map<String, List<GroupParams>> groupParams) {
        Set<String> fileNameSet = groupParams.keySet();
        Collection<List<GroupParams>> groupParamsCollection = groupParams.values();
        Iterator<List<GroupParams>> iter = groupParamsCollection.iterator();
        for (String fileName : fileNameSet) {
            String filePath = folderPath + fileName + ".xml";
            List<GroupParams> groupParamsList = iter.next();
            writeXML(filePath, groupParamsList);
        }
    }

    public static void writeXML(String filePath, List<GroupParams> groups) {
        XmlSerializer serializer = Xml.newSerializer();
        try {
            FileOutputStream fos = new FileOutputStream(filePath);
            serializer.setOutput(fos, "UTF-8");
            serializer.startDocument("UTF-8", true);
            serializer.startTag("", Tags.FIREWORKS);
            serializer.startTag("", Tags.GROUPS);
            for (GroupParams groupParams : groups) {
                serializeGroupParams(groupParams, serializer);
            }
            serializer.endTag("", Tags.GROUPS);
            serializer.endTag("", Tags.FIREWORKS);
            serializer.endDocument();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void serializeGroupParams(GroupParams groupParams,
                                             XmlSerializer serializer) {
        String name = groupParams.getName();
        float sizeX = groupParams.getSize()[0];
        float sizeY = groupParams.getSize()[1];
        float sizeZ = groupParams.getSize()[2];
        int type = groupParams.getType();
        try {
            serializer.startTag("", Tags.GROUP_PARAMS);
            serializer.attribute("", Tags.NAME, name);
            serializer.attribute("", Tags.SIZE_X, String.valueOf(sizeX));
            serializer.attribute("", Tags.SIZE_Y, String.valueOf(sizeY));
            serializer.attribute("", Tags.SIZE_Z, String.valueOf(sizeZ));
            serializer.attribute("", Tags.TYPE, String.valueOf(type));
            serializeParameters(groupParams, serializer);
            serializeSetPointParams(groupParams.getPointParams(), serializer);
            serializer.endTag("", Tags.GROUP_PARAMS);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void serializeParameters(GroupParams groupParams,
                                            XmlSerializer serializer) {
        int id = groupParams.getID();
        try {
            serializer.startTag("", Tags.PARAMETERS);
            serializer.attribute("", Tags.ID, String.valueOf(id));
            serializeUpdatersList(groupParams.getUpdatersList(), serializer);
            serializer.endTag("", Tags.PARAMETERS);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static void serializeUpdatersList(
            List<IUpdater> updatersList, XmlSerializer serializer) {
        try {
            serializer.startTag("", Tags.UPDATERS_LIST);
            for (IUpdater updater : updatersList) {
                String name = updater.getClass().getSimpleName();
                serializer.startTag("", Tags.UPDATER);
                serializer.attribute("", Tags.NAME, name);
                serializer.endTag("", Tags.UPDATER);
            }
            serializer.endTag("", Tags.UPDATERS_LIST);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void serializeSetPointParams(
            List<PointParams> setPointParams, XmlSerializer serializer) {
        try {
            serializer.startTag("", Tags.SET_POINT_PARAMS);
            for (PointParams pointParams : setPointParams) {
                serializePointParams(pointParams, serializer);
            }
            serializer.endTag("", Tags.SET_POINT_PARAMS);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void serializePointParams(PointParams pointParams,
                                             XmlSerializer serializer) {
        String divAmt = String.valueOf(pointParams.getAmount()[0]);
        String matAmt = String.valueOf(pointParams.getAmount()[1]);

        String x = String.valueOf(pointParams.getPosition()[0]);
        String y = String.valueOf(pointParams.getPosition()[1]);
        String z = String.valueOf(pointParams.getPosition()[2]);

        String normalX = String.valueOf(pointParams.getNormal()[0]);
        String normalY = String.valueOf(pointParams.getNormal()[1]);
        String normalZ = String.valueOf(pointParams.getNormal()[2]);

        String divSpeedX = String.valueOf(pointParams.getDivSpeed()[0]);
        String divSpeedY = String.valueOf(pointParams.getDivSpeed()[1]);
        String divSpeedZ = String.valueOf(pointParams.getDivSpeed()[2]);

        String matSpeedX = String.valueOf(pointParams.getDivSpeed()[0]);
        String matSpeedY = String.valueOf(pointParams.getDivSpeed()[1]);
        String matSpeedZ = String.valueOf(pointParams.getDivSpeed()[2]);

        String matColorR = String.valueOf(pointParams.getMatColor()[0]);
        String matColorG = String.valueOf(pointParams.getMatColor()[1]);
        String matColorB = String.valueOf(pointParams.getMatColor()[2]);
        String matColorA = String.valueOf(pointParams.getMatColor()[3]);

        String divColorR = String.valueOf(pointParams.getDivColor()[0]);
        String divColorG = String.valueOf(pointParams.getDivColor()[1]);
        String divColorB = String.valueOf(pointParams.getDivColor()[2]);
        String divColorA = String.valueOf(pointParams.getDivColor()[3]);

        String divTimeLife = String.valueOf(pointParams.getTimeLife()[0]);
        String matTimeLife = String.valueOf(pointParams.getTimeLife()[1]);
        String divExplosionTime = String
                .valueOf(pointParams.getExplosionTime()[0]);
        String matExplosionTime = String
                .valueOf(pointParams.getExplosionTime()[1]);

        String whatExplose = pointParams.getWhatExplose();
        try {
            serializer.startTag("", Tags.POINT_PARAMS);

            serializer.attribute("", Tags.DIV_AMT, divAmt);
            serializer.attribute("", Tags.MAT_AMT, matAmt);

            serializer.attribute("", Tags.X, x);
            serializer.attribute("", Tags.Y, y);
            serializer.attribute("", Tags.Z, z);

            serializer.attribute("", Tags.NORMAL_X, normalX);
            serializer.attribute("", Tags.NORMAL_Y, normalY);
            serializer.attribute("", Tags.NORMAL_Z, normalZ);

            serializer.attribute("", Tags.DIV_SPEED_X, divSpeedX);
            serializer.attribute("", Tags.DIV_SPEED_Y, divSpeedY);
            serializer.attribute("", Tags.DIV_SPEED_Z, divSpeedZ);
            serializer.attribute("", Tags.MAT_SPEED_X, matSpeedX);
            serializer.attribute("", Tags.MAT_SPEED_Y, matSpeedY);
            serializer.attribute("", Tags.MAT_SPEED_Z, matSpeedZ);

            serializer.attribute("", Tags.DIV_COLOR_R, divColorR);
            serializer.attribute("", Tags.DIV_COLOR_G, divColorG);
            serializer.attribute("", Tags.DIV_COLOR_B, divColorB);
            serializer.attribute("", Tags.DIV_COLOR_A, divColorA);
            serializer.attribute("", Tags.MAT_COLOR_R, matColorR);
            serializer.attribute("", Tags.MAT_COLOR_G, matColorG);
            serializer.attribute("", Tags.MAT_COLOR_B, matColorB);
            serializer.attribute("", Tags.MAT_COLOR_A, matColorA);

            serializer.attribute("", Tags.DIV_TIME_LIFE, divTimeLife);
            serializer.attribute("", Tags.MAT_TIME_LIFE, matTimeLife);
            serializer.attribute("", Tags.DIV_EXPLOSION_TIME, divExplosionTime);
            serializer.attribute("", Tags.MAT_EXPLOSION_TIME, matExplosionTime);

            serializer.attribute("", Tags.WHAT_EXPLOSE, whatExplose);

            serializer.endTag("", Tags.POINT_PARAMS);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void addGroupParams(String filePath, GroupParams groupParams) {
        List<GroupParams> groups = XMLReader.parseXMLFile(filePath);
        if (groups != null) {
            groups.add(groupParams);
            writeXML(filePath, groups);
        }
    }
}