package com.example.server.controller;

import com.example.server.entity.Triple;
import com.example.server.entity.WordCloud;
import com.example.server.service.TripleService;
import com.example.server.service.WordCloudService;
import org.springframework.beans.factory.annotation.Autowired;
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
public class WordCloudController {
    @Autowired
    private WordCloudService wordCloudService;



    @ResponseBody
    @RequestMapping(value = "/getWordCloud", method = RequestMethod.GET)
    public List getWordCloud(){
        System.out.println("now in WordCloudController");
        List<Map<String,Object>> words = new ArrayList<>();
        for(WordCloud wordCloud : wordCloudService.findAll()){
            Map<String,Object> map = new HashMap<>();
            map.put("name", wordCloud.getName());
            map.put("value",wordCloud.getValue());
            words.add(map);
        }


        return words;
    }
}
