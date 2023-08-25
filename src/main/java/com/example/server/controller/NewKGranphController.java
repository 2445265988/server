package com.example.server.controller;

import com.example.server.entity.EchartsEntity;
import com.example.server.entity.EchartsRelationship;
import com.example.server.service.KGraphService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
public class NewKGranphController {

    @Autowired
    private KGraphService kGraphService ;

    @GetMapping("/getAllKG")
    public Map<String,Object> getAll(@RequestParam String name){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("data",kGraphService.findAllEntity(name));
        map.put("link",kGraphService.findAllRelationship());
        return map;
    }
    @GetMapping("/getNodeByName")
    public List<EchartsEntity> getNodeByName(@RequestParam String name){
        List<EchartsEntity> map = kGraphService.getNodeByName(name);
        return map;
    }

}