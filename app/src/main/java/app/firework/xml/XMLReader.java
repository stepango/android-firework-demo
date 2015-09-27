package app.firework.xml;

import android.content.res.XmlResourceParser;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xmlpull.v1.XmlPullParserException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import app.firework.updaters.DeleteUpdater;
import app.firework.updaters.ExplosiveUpdater;
import app.firework.updaters.IUpdater;
import app.firework.updaters.PositionUpdater;

public class XMLReader {

    // Read from resource
    public static Map<String, List<GroupParams>> parseXMLResources(
            XmlResourceParser[] resourceParser, String[] filesNames) {
        Map<String, List<GroupParams>> allGroupParamsMap;
        allGroupParamsMap = new HashMap<>();
        for (int i = 0; i < filesNames.length; i++) {
            List<GroupParams> groupParamsList = parseXMLResource(resourceParser[i]);
            String keyString = filesNames[i];
            if (keyString.contains(".xml")) {
                keyString = keyString.substring(0, keyString.length() - 4);
            }
            allGroupParamsMap.put(keyString, groupParamsList);
        }
        return allGroupParamsMap;
    }

    public static List<GroupParams> parseXMLResource(
            XmlResourceParser resourceParser) {
        List<GroupParams> groupParams = null;
        try {
            groupParams = new LinkedList<>();
            parseXMLResourse(resourceParser, groupParams, new GroupParams());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return groupParams;
    }

    private static void parseXMLResourse(XmlResourceParser resource,
                                         List<GroupParams> groupParams, GroupParams currentGroupParams)
            throws IOException {
        int type;
        String tagName;
        IUpdater currentUpdater = null;
        PointParams currentPoint;

        try {
            type = resource.getEventType();
            tagName = resource.getName();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
            return;
        }

        switch (type) {
            case XmlResourceParser.START_DOCUMENT:
                try {
                    resource.next();
                    parseXMLResourse(resource, groupParams, currentGroupParams);
                } catch (XmlPullParserException e) {
                    e.printStackTrace();
                }
                break;
            case XmlResourceParser.END_DOCUMENT:
                break;
            case XmlResourceParser.END_TAG:
                try {
                    resource.next();
                    parseXMLResourse(resource, groupParams, currentGroupParams);
                } catch (XmlPullParserException e) {
                    e.printStackTrace();
                }
                break;
            default:
                if (tagName.equals(Tags.GROUP_PARAMS)) {
                    currentGroupParams = new GroupParams();
                    String name = resource.getAttributeValue(null, Tags.NAME);
                    float sizeX = Float.valueOf(resource.getAttributeValue(null,
                            Tags.SIZE_X));
                    float sizeY = Float.valueOf(resource.getAttributeValue(null,
                            Tags.SIZE_Y));
                    float sizeZ = Float.valueOf(resource.getAttributeValue(null,
                            Tags.SIZE_Z));
                    int groupType = resource.getAttributeIntValue(null, Tags.TYPE,
                            0);

                    currentGroupParams.setName(name);
                    currentGroupParams.setSize(new float[]{sizeX, sizeY, sizeZ});
                    currentGroupParams.setType(groupType);

                    groupParams.add(currentGroupParams);
                }

                if (tagName.equals(Tags.PARAMETERS)
                        && type != XmlResourceParser.END_TAG) {
                    if (resource.getAttributeCount() != 0) {
                        int id = resource.getAttributeIntValue(null, Tags.ID, 0);
                        currentGroupParams.setID(id);
                    }
                }

                if (tagName.equals(Tags.UPDATER)) {
                    String updaterName = resource
                            .getAttributeValue(null, Tags.NAME);
                    if (updaterName != null) {
                        if (updaterName.equals("PositionUpdater")) {
                            currentUpdater = new PositionUpdater();
                        }
                        if (updaterName.equals("DeleteUpdater")) {
                            currentUpdater = new DeleteUpdater();
                        }
                        if (updaterName.equals("ExplosiveUpdater")) {
                            currentUpdater = new ExplosiveUpdater();
                        }
                        currentGroupParams.getUpdatersList().add(currentUpdater);
                    }

                }

                if (tagName.equals(Tags.POINT_PARAMS)) {

                    int id = resource.getAttributeIntValue(null, Tags.ID, -1);
                    float divAmt = Float.valueOf(resource.getAttributeValue(null,
                            Tags.DIV_AMT));
                    float matAmt = Float.valueOf(resource.getAttributeValue(null,
                            Tags.MAT_AMT));
                    float x = Float.valueOf(resource
                            .getAttributeValue(null, Tags.X));
                    float y = Float.valueOf(resource
                            .getAttributeValue(null, Tags.Y));
                    float z = Float.valueOf(resource
                            .getAttributeValue(null, Tags.Z));
                    float normalX = Float.valueOf(resource.getAttributeValue(null,
                            Tags.NORMAL_X));
                    float normalY = Float.valueOf(resource.getAttributeValue(null,
                            Tags.NORMAL_Y));
                    float normalZ = Float.valueOf(resource.getAttributeValue(null,
                            Tags.NORMAL_Z));
                    float divSpeedX = Float.valueOf(resource.getAttributeValue(
                            null, Tags.DIV_SPEED_X));
                    float matSpeedX = Float.valueOf(resource.getAttributeValue(
                            null, Tags.MAT_SPEED_X));
                    float divSpeedY = Float.valueOf(resource.getAttributeValue(
                            null, Tags.DIV_SPEED_Y));
                    float matSpeedY = Float.valueOf(resource.getAttributeValue(
                            null, Tags.MAT_SPEED_Y));
                    float divSpeedZ = Float.valueOf(resource.getAttributeValue(
                            null, Tags.DIV_SPEED_Z));
                    float matSpeedZ = Float.valueOf(resource.getAttributeValue(
                            null, Tags.MAT_SPEED_Z));
                    float divColorR = Float.valueOf(resource.getAttributeValue(
                            null, Tags.DIV_COLOR_R));
                    float matColorR = Float.valueOf(resource.getAttributeValue(
                            null, Tags.MAT_COLOR_R));
                    float divColorG = Float.valueOf(resource.getAttributeValue(
                            null, Tags.DIV_COLOR_G));
                    float matColorG = Float.valueOf(resource.getAttributeValue(
                            null, Tags.MAT_COLOR_G));
                    float divColorB = Float.valueOf(resource.getAttributeValue(
                            null, Tags.DIV_COLOR_B));
                    float matColorB = Float.valueOf(resource.getAttributeValue(
                            null, Tags.MAT_COLOR_B));
                    float divColorA = Float.valueOf(resource.getAttributeValue(
                            null, Tags.DIV_COLOR_A));
                    float matColorA = Float.valueOf(resource.getAttributeValue(
                            null, Tags.MAT_COLOR_A));
                    float divTimeLife = Float.valueOf(resource.getAttributeValue(
                            null, Tags.DIV_TIME_LIFE));
                    float matTimeLife = Float.valueOf(resource.getAttributeValue(
                            null, Tags.MAT_TIME_LIFE));
                    float divExplosionTime = Float.valueOf(resource
                            .getAttributeValue(null, Tags.DIV_EXPLOSION_TIME));
                    float matExplosionTime = Float.valueOf(resource
                            .getAttributeValue(null, Tags.MAT_EXPLOSION_TIME));
                    String whatExplose = resource.getAttributeValue(null,
                            Tags.WHAT_EXPLOSE);

                    currentPoint = new PointParams();
                    currentPoint.setID(id);
                    currentPoint.setAmount(new float[]{divAmt, matAmt});
                    currentPoint.setPosition(new float[]{x, y, z});
                    currentPoint
                            .setNormal(new float[]{normalX, normalY, normalZ});
                    currentPoint.setMatSpeed(new float[]{matSpeedX, matSpeedY,
                            matSpeedZ});
                    currentPoint.setDivSpeed(new float[]{divSpeedX, divSpeedY,
                            divSpeedZ});
                    currentPoint.setMatColor(new float[]{matColorR, matColorG,
                            matColorB, matColorA});
                    currentPoint.setDivColor(new float[]{divColorR, divColorG,
                            divColorB, divColorA});
                    currentPoint.setExplosionTime(new float[]{divExplosionTime,
                            matExplosionTime});
                    currentPoint
                            .setTimeLife(new float[]{divTimeLife, matTimeLife});
                    currentPoint.setWhatExplose(whatExplose);

                    currentGroupParams.getPointParams().add(currentPoint);

                }
                try {
                    resource.next();
                    parseXMLResourse(resource, groupParams, currentGroupParams);
                } catch (XmlPullParserException e) {
                    e.printStackTrace();
                    return;
                }
                break;
        }
    }

    // Read from path
    public static Map<String, List<GroupParams>> parseXMLFiles(String foldePath) {
        String[] XMLFiles = getNamesOfXMLFiles(foldePath);
        Map<String, List<GroupParams>> allGroupParamsMap;
        allGroupParamsMap = new HashMap<>();
        for (String fileName : XMLFiles) {
            List<GroupParams> groupParamsList = parseXMLFile(foldePath + fileName);
            String keyString = fileName;
            if (fileName.contains(".xml")) {
                fileName = fileName.substring(0, fileName.length() - 4);
            }
            allGroupParamsMap.put(keyString, groupParamsList);
        }
        return allGroupParamsMap;
    }

    public static List<GroupParams> parseXMLFile(String filePath) {
        Document doc;
        List<GroupParams> groupParams = null;
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            doc = db.parse(new FileInputStream(filePath));
            groupParams = new LinkedList<>();
            parseDOM(doc, groupParams, new GroupParams());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return groupParams;
    }

    private static void parseDOM(Node node, List<GroupParams> groupParams,
                                 GroupParams currentGroupParams) {
        IUpdater currentUpdater = null;
        PointParams currentPoint;

        if (node == null) {
            return;
        }

        int type = node.getNodeType();
        switch (type) {

            case Node.DOCUMENT_NODE: {
                parseDOM(((Document) node).getDocumentElement(), groupParams,
                        currentGroupParams);
                break;
            }

            case Node.ELEMENT_NODE: {
                NamedNodeMap attrs = node.getAttributes();
                if (node.getNodeName().equals(Tags.GROUP_PARAMS)) {
                    currentGroupParams = new GroupParams();

                    Node nameNode = attrs.getNamedItem(Tags.NAME);
                    Node sizeXNode = attrs.getNamedItem(Tags.SIZE_X);
                    Node sizeYNode = attrs.getNamedItem(Tags.SIZE_Y);
                    Node sizeZNode = attrs.getNamedItem(Tags.SIZE_Z);
                    Node typeNode = attrs.getNamedItem(Tags.TYPE);

                    float sizeX = Float.valueOf(sizeXNode.getNodeValue());
                    float sizeY = Float.valueOf(sizeYNode.getNodeValue());
                    float sizeZ = Float.valueOf(sizeZNode.getNodeValue());
                    int groupType = Integer.valueOf(typeNode.getNodeValue());

                    currentGroupParams.setName(nameNode.getNodeValue());
                    currentGroupParams.setSize(new float[]{sizeX, sizeY, sizeZ});
                    currentGroupParams.setType(groupType);

                    groupParams.add(currentGroupParams);
                }

                if (node.getNodeName().equals(Tags.PARAMETERS)) {
                    Node idNode = attrs.getNamedItem(Tags.ID);
                    int id = Integer.valueOf(idNode.getNodeValue());
                    currentGroupParams.setID(id);
                }

                if (node.getNodeName().equals(Tags.UPDATERS_LIST)) {
                    //TODO
                }

                if (node.getNodeName().equals(Tags.UPDATER)) {
                    Node updaterNode = attrs.getNamedItem(Tags.NAME);
                    String updaterName = updaterNode.getNodeValue();
                    if (updaterName.equals("PositionUpdater")) {
                        currentUpdater = new PositionUpdater();
                    }
                    if (updaterName.equals("DeleteUpdater")) {
                        currentUpdater = new DeleteUpdater();
                    }
                    if (updaterName.equals("ExplosiveUpdater")) {
                        currentUpdater = new ExplosiveUpdater();
                    }
                    currentGroupParams.getUpdatersList().add(currentUpdater);
                }

                if (node.getNodeName().equals(Tags.POINT_PARAMS)) {
                    currentPoint = new PointParams();

                    Node idNode = attrs.getNamedItem(Tags.ID);
                    Node divAmtNode = attrs.getNamedItem(Tags.DIV_AMT);
                    Node matAmtNode = attrs.getNamedItem(Tags.MAT_AMT);
                    Node xNode = attrs.getNamedItem(Tags.X);
                    Node yNode = attrs.getNamedItem(Tags.Y);
                    Node zNode = attrs.getNamedItem(Tags.Z);
                    Node normalXNode = attrs.getNamedItem(Tags.NORMAL_X);
                    Node normalYNode = attrs.getNamedItem(Tags.NORMAL_Y);
                    Node normalZNode = attrs.getNamedItem(Tags.NORMAL_Z);
                    Node divSpeedXNode = attrs.getNamedItem(Tags.DIV_SPEED_X);
                    Node matSpeedXNode = attrs.getNamedItem(Tags.MAT_SPEED_X);
                    Node divSpeedYNode = attrs.getNamedItem(Tags.DIV_SPEED_Y);
                    Node matSpeedYNode = attrs.getNamedItem(Tags.MAT_SPEED_Y);
                    Node divSpeedZNode = attrs.getNamedItem(Tags.DIV_SPEED_Z);
                    Node matSpeedZNode = attrs.getNamedItem(Tags.MAT_SPEED_Z);
                    Node divColorRNode = attrs.getNamedItem(Tags.DIV_COLOR_R);
                    Node matColorRNode = attrs.getNamedItem(Tags.MAT_COLOR_R);
                    Node divColorGNode = attrs.getNamedItem(Tags.DIV_COLOR_G);
                    Node matColorGNode = attrs.getNamedItem(Tags.MAT_COLOR_G);
                    Node divColorBNode = attrs.getNamedItem(Tags.DIV_COLOR_B);
                    Node matColorBNode = attrs.getNamedItem(Tags.MAT_COLOR_B);
                    Node divColorANode = attrs.getNamedItem(Tags.DIV_COLOR_A);
                    Node matColorANode = attrs.getNamedItem(Tags.MAT_COLOR_A);
                    Node divTimeLifeNode = attrs.getNamedItem(Tags.DIV_TIME_LIFE);
                    Node matTimeLifeNode = attrs.getNamedItem(Tags.MAT_TIME_LIFE);
                    Node divExplosionTimeNode = attrs
                            .getNamedItem(Tags.DIV_EXPLOSION_TIME);
                    Node matExplosionTimeNode = attrs
                            .getNamedItem(Tags.MAT_EXPLOSION_TIME);
                    Node whatExploseNode = attrs.getNamedItem(Tags.WHAT_EXPLOSE);

                    int id = Integer.valueOf(idNode.getNodeValue());
                    float divAmt = Float.valueOf(divAmtNode.getNodeValue());
                    float matAmt = Float.valueOf(matAmtNode.getNodeValue());
                    float x = Float.valueOf(xNode.getNodeValue());
                    float y = Float.valueOf(yNode.getNodeValue());
                    float z = Float.valueOf(zNode.getNodeValue());
                    float normalX = Float.valueOf(normalXNode.getNodeValue());
                    float normalY = Float.valueOf(normalYNode.getNodeValue());
                    float normalZ = Float.valueOf(normalZNode.getNodeValue());
                    float divSpeedX = Float.valueOf(divSpeedXNode.getNodeValue());
                    float matSpeedX = Float.valueOf(matSpeedXNode.getNodeValue());
                    float divSpeedY = Float.valueOf(divSpeedYNode.getNodeValue());
                    float matSpeedY = Float.valueOf(matSpeedYNode.getNodeValue());
                    float divSpeedZ = Float.valueOf(divSpeedZNode.getNodeValue());
                    float matSpeedZ = Float.valueOf(matSpeedZNode.getNodeValue());
                    float divColorR = Float.valueOf(divColorRNode.getNodeValue());
                    float matColorR = Float.valueOf(matColorRNode.getNodeValue());
                    float divColorG = Float.valueOf(divColorGNode.getNodeValue());
                    float matColorG = Float.valueOf(matColorGNode.getNodeValue());
                    float divColorB = Float.valueOf(divColorBNode.getNodeValue());
                    float matColorB = Float.valueOf(matColorBNode.getNodeValue());
                    float divColorA = Float.valueOf(divColorANode.getNodeValue());
                    float matColorA = Float.valueOf(matColorANode.getNodeValue());
                    float divTimeLife = Float.valueOf(divTimeLifeNode
                            .getNodeValue());
                    float matTimeLife = Float.valueOf(matTimeLifeNode
                            .getNodeValue());
                    float divExplosionTime = Float.valueOf(divExplosionTimeNode
                            .getNodeValue());
                    float matExplosionTime = Float.valueOf(matExplosionTimeNode
                            .getNodeValue());
                    String whatExplose = "";
                    if (whatExploseNode != null) {
                        whatExplose = whatExploseNode.getNodeValue();
                    }

                    currentPoint.setID(id);
                    currentPoint.setAmount(new float[]{divAmt, matAmt});
                    currentPoint.setPosition(new float[]{x, y, z});
                    currentPoint.setNormal(new float[]{
                            normalX,
                            normalY,
                            normalZ});
                    currentPoint.setMatSpeed(new float[]{
                            matSpeedX,
                            matSpeedY,
                            matSpeedZ});
                    currentPoint.setDivSpeed(new float[]{
                            divSpeedX,
                            divSpeedY,
                            divSpeedZ});
                    currentPoint.setMatColor(new float[]{
                            matColorR,
                            matColorG,
                            matColorB,
                            matColorA});
                    currentPoint.setDivColor(new float[]{
                            divColorR,
                            divColorG,
                            divColorB,
                            divColorA});
                    currentPoint.setExplosionTime(new float[]{
                            divExplosionTime,
                            matExplosionTime});
                    currentPoint.setTimeLife(new float[]{
                            divTimeLife,
                            matTimeLife});
                    currentPoint.setWhatExplose(whatExplose);

                    currentGroupParams.getPointParams().add(currentPoint);
                }

                NodeList children = node.getChildNodes();
                if (children != null) {
                    int len = children.getLength();
                    for (int i = 0; i < len; i++) {
                        parseDOM(children.item(i), groupParams, currentGroupParams);
                    }
                }
                break;
            }

        }

    }

    public static String[] getNamesOfXMLFiles(String folderPath) {
        File folder = new File(folderPath);
        String[] files = folder.list();

        ArrayList<String> XMLFiles = new ArrayList<>();
        for (String fileName : files) {
            String xml = fileName.substring(fileName.length() - 4, fileName
                    .length());
            if (xml.equals(".xml")) {
                XMLFiles.add(fileName);
            }
        }
        String[] XMLStrings = new String[XMLFiles.size()];
        for (int i = 0; i < XMLStrings.length; i++) {
            XMLStrings[i] = XMLFiles.get(i);
        }
        return XMLStrings;
    }

}
