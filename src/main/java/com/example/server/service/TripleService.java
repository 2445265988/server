package com.example.server.service;

import com.example.server.entity.Triple;
import org.gephi.graph.api.DirectedGraph;

import java.util.List;
import java.util.Map;


public interface TripleService {
    List<Triple> findAll();
    Map<String, Object> getDirectedGraph();
}
