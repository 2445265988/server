package com.example.server.service.Impl;

import com.example.server.dao.TripleDao;
import com.example.server.entity.Triple;
import com.example.server.service.TripleService;
import org.gephi.appearance.api.AppearanceController;
import org.gephi.appearance.api.AppearanceModel;
import org.gephi.appearance.api.Function;
import org.gephi.appearance.plugin.RankingNodeSizeTransformer;
import org.gephi.graph.api.DirectedGraph;
import org.gephi.graph.api.GraphController;
import org.gephi.graph.api.GraphModel;
import org.gephi.graph.api.Node;
import org.gephi.layout.plugin.forceAtlas.ForceAtlasLayout;
import org.gephi.project.api.ProjectController;
import org.gephi.project.api.Workspace;
import org.openide.util.Lookup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service("tripleService")
public class TripleServiceImpl implements TripleService {
    @Autowired
    private TripleDao tripleDao;

    @Override
    public List<Triple> findAll() {
        return tripleDao.findAll();
    }

    private static String randomHexColor() {
        Random random = new Random();
        String red = Integer.toHexString(random.nextInt(256)).toUpperCase();
        String green = Integer.toHexString(random.nextInt(256)).toUpperCase();
        String blue = Integer.toHexString(random.nextInt(256)).toUpperCase();

        red = red.length() == 1 ? "0" + red : red;
        green = green.length() == 1 ? "0" + green : green;
        blue = blue.length() == 1 ? "0" + blue : blue;

        return "#" + red + green + blue;
    }

    @Override
    public Map<String, Object> getDirectedGraph() {
        ProjectController pc = Lookup.getDefault().lookup(ProjectController.class);  //ProjectController是gephi中的class,用来创建一个gephi工程
        pc.newProject();
        Workspace workspace = pc.getCurrentWorkspace(); // 存储数据

        GraphModel graphModel = Lookup.getDefault().lookup(GraphController.class).getGraphModel(); // API的入口点，提供各种方法
        AppearanceController appearanceController = Lookup.getDefault().lookup(AppearanceController.class); //管理和控制渲染效果
        AppearanceModel appearanceModel = appearanceController.getModel(); //访问外观功能的入口点
        graphModel.getNodeTable().addColumn("name", String.class);
        graphModel.getNodeTable().addColumn("degree", Integer.class);
        graphModel.getNodeTable().addColumn("color", String.class);
        graphModel.getEdgeTable().addColumn("name", String.class);

        DirectedGraph directedGraph = graphModel.getDirectedGraph();

        List<Triple> TripleData = tripleDao.findAll();
        Set<String> nodesType = new HashSet<>();
        Set<String> edgesType = new HashSet<>();
        Map<String, Integer> degreeMap = new HashMap<>();

        for (Triple triple : TripleData) {
            String entity_1 = triple.getEntity_1();
            String entity_2 = triple.getEntity_2();

            if (nodesType.contains(entity_1)) {
                int degree = degreeMap.get(entity_1);
                degree++;
                degreeMap.put(entity_1, degree);
            } else {
                nodesType.add(entity_1);
                degreeMap.put(entity_1, 1);
            }

            if (nodesType.contains(entity_2)) {
                int degree = degreeMap.get(entity_2);
                degree++;
                degreeMap.put(entity_2, degree);
            } else {
                nodesType.add(entity_2);
                degreeMap.put(entity_2, 1);
            }

            nodesType.add(triple.getEntity_2());
            edgesType.add(triple.getRelation());
        }

        Map<String, Integer> vertex2num = new HashMap<>();
        int vId = 1;
        for (String NodeName : nodesType) {
            int degree = degreeMap.get(NodeName);

            Node n = graphModel.factory().newNode("" + vId);
            n.setAttribute("name", NodeName);
            n.setAttribute("degree", degree);
            n.setAttribute("color", randomHexColor());

            directedGraph.addNode(n);
            vertex2num.put(NodeName, vId);
            vId++;
        }

        for (Triple triple : TripleData) {
            int startId = vertex2num.get(triple.getEntity_1());
            int endId = vertex2num.get(triple.getEntity_2());
            String relation = triple.getRelation();

            Node startN = directedGraph.getNode("" + startId);
            Node endN = directedGraph.getNode("" + endId);

            org.gephi.graph.api.Edge edge = graphModel.factory().newEdge(startN, endN, 1, true);
            edge.setAttribute("name", relation);

            directedGraph.addEdge(edge);
        }

        Function degreeRanking = appearanceModel.getNodeFunction(directedGraph, graphModel.getNodeTable().getColumn("degree"), RankingNodeSizeTransformer.class);
        RankingNodeSizeTransformer rankingNodeSizeTransformer = (RankingNodeSizeTransformer) degreeRanking.getTransformer();
        rankingNodeSizeTransformer.setMinSize(10);
        rankingNodeSizeTransformer.setMaxSize(200);
        appearanceController.transform(degreeRanking);

        ForceAtlasLayout forceatLayout = new ForceAtlasLayout(null);
        forceatLayout.setGraphModel(graphModel);
        forceatLayout.initAlgo();
        forceatLayout.resetPropertiesValues();

        forceatLayout.setInertia(0.1);
        forceatLayout.setRepulsionStrength(15000.0);
        forceatLayout.setAttractionStrength(2.0);
        forceatLayout.setMaxDisplacement(20.0);
        forceatLayout.setFreezeBalance(true);
        forceatLayout.setFreezeStrength(80.0);
        forceatLayout.setFreezeInertia(0.2);
        forceatLayout.setGravity(35.0);
        forceatLayout.setSpeed(1.0);
        forceatLayout.setAdjustSizes(true);
        forceatLayout.setOutboundAttractionDistribution(false);

        for (int i = 0; i <= 1500 && forceatLayout.canAlgo(); i++) {
            if (i != 0 && i % 10 == 0) {
                System.out.println("已迭代" + i + "次...");
            }
            forceatLayout.goAlgo();
        }
        forceatLayout.endAlgo();

        List<Map<String, Object>> nodes = new ArrayList<>();
        List<Map<String, Object>> links = new ArrayList<>();

        float maxX = Float.MIN_VALUE;
        float minX = Float.MAX_VALUE;
        float maxY = Float.MIN_VALUE;
        float minY = Float.MAX_VALUE;

        for (Node n : directedGraph.getNodes()) {
            Map<String, Object> node = new HashMap<>();

            node.put("id", n.getId().toString());
            node.put("name", n.getAttribute("name"));
            node.put("degree", n.getAttribute("degree"));
            node.put("x", n.x());
            node.put("y", n.y());
            node.put("symbolSize", n.size());

            Map<String, Object> item = new HashMap<>();
            item.put("color", n.getAttribute("color"));
            node.put("itemStyle", item);

            float x = n.x();
            float y = n.y();
            maxX = Math.max(maxX, x);
            minX = Math.min(minX, x);
            maxY = Math.max(maxY, y);
            minY = Math.min(minY, y);

            nodes.add(node);
        }

        for (org.gephi.graph.api.Edge e : directedGraph.getEdges()) {
            Map<String, Object> edge = new HashMap<>();

            edge.put("source", e.getSource().getId().toString());
            edge.put("target", e.getTarget().getId().toString());
            edge.put("uri", e.getAttribute("name"));
            Map<String, Object> item = new HashMap<>();
            item.put("curveness", 0.3);
            item.put("color", e.getSource().getAttribute("color").toString());
            edge.put("lineStyle", item);

            links.add(edge);
        }

        Map<String, Object> result = new HashMap<>();
        result.put("nodes", nodes);
        result.put("links", links);
        result.put("width", maxX - minX);
        result.put("height", maxY - minY);

        return result;
    }


}
