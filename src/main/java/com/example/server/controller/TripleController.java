package com.example.server.controller;

import com.example.server.entity.Triple;
import com.example.server.entity.WordCloud;
import com.example.server.service.TripleService;
import org.gephi.graph.api.DirectedGraph;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@Controller
public class TripleController {
    @Autowired
    @Qualifier("redisTemplate")
    private RedisTemplate redisTemplate;

    @Autowired
    private TripleService tripleService;

    @ResponseBody
    @RequestMapping(value = "/getAllTriple", method = RequestMethod.GET)
    public Map<String, Object> getAllTriple(){

        System.out.println("now in getAllTriple");
        List<Map<String,Object>> triples = new ArrayList<>();
//        this.redisTemplate.opsForValue().set("test", "test");
        System.out.println("now in getAllTriple");
        return tripleService.getDirectedGraph();
    }
}
