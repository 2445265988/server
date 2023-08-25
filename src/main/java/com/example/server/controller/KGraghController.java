package com.example.server.controller;

import com.example.server.entity.BaseInfo;
import com.example.server.entity.TechnologyInfo;
import com.example.server.entity.TitleType;
import com.example.server.entity.neo4j.EchartsData;
import com.example.server.entity.neo4j.EchartsLinks;
import com.example.server.entity.neo4j.Thesis;
import com.example.server.entity.neo4j.Topic;
import com.example.server.service.BaseInfoService;
import com.example.server.service.TechnologyInfoService;
import com.example.server.service.TitleTypeService;
import com.example.server.service.neo4j.ThesisService;
import com.example.server.service.neo4j.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@Controller
public class KGraghController {
    @Autowired
    private BaseInfoService baseInfoService;
    @Autowired
    private ThesisService thesisService;
    @Autowired
    private TitleTypeService titleTypeService;
    @Autowired
    private TopicService topicService;
    @Autowired
    private TechnologyInfoService technologyInfoService;

    @ResponseBody
    @RequestMapping(value = "/searchTech", method = RequestMethod.POST)
    public Map searchTech(HttpServletRequest request){
        String tech = request.getParameter("tech");
        return modifyJson(topicService.findByNameLike("*"+tech+"*"));
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.GET,value = "/getAll")
    public Map getAll(){
        return modifyJson(topicService.findAll());
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.GET,value = "/geneAll")
    public Map geneAll(){
        deleteAll();
        for (BaseInfo baseInfo : baseInfoService.findAll()) {
//            System.out.println(baseInfo.getSname());
//            System.out.println(baseInfo.getTitle());
//            j = j + 1;
            thesisService.add(new Thesis(baseInfo.getSno(),baseInfo.getSname(),baseInfo.getTitle()));
        }
//        System.out.println("BaseInfo:"+j);
//        j = 0;
//        for(Thesis t : thesisService.findAll()){
//            j = j + 1;
//        }
//        System.out.println("thesisService:"+j);

//        System.out.println("after for (BaseInfo baseInfo : baseInfoService.findAll())");

        for (TitleType titleType : titleTypeService.findAll()) {
            List<TechnologyInfo> technologyInfoList = technologyInfoService.findByTechnology(titleType.getType());
            Topic topic = new Topic();
            topic.setName(titleType.getType());
            if(technologyInfoList.size()>0){
                List<Thesis> thesesList = new ArrayList<>();
                for (TechnologyInfo technologyInfo : technologyInfoList) {
                    thesesList.add(thesisService.findBySno(technologyInfo.getSno()));
                }
                topic.setTheses(thesesList);
            }
            topicService.add(topic);
        }

//        int i = 0;
//        for(Topic t : topicService.findAll()){
//            i = i + 1;
//        }
//        System.out.println("topicService:"+i);



//        System.out.println("over ......");
//
//        for(Topic topic : topicService.findAll()){
//            System.out.println(topic.getName());
//        }
        return modifyJson(topicService.findAll());
    }

    public void deleteAll(){
        thesisService.deleteAll();
        topicService.deleteAll();
        System.out.println("已有知识图谱数据已清空");
    }

    public Map<String,Object> modifyJson(Iterable<Topic> topics){
        Map<String,Object> map = new HashMap<>();
        List<EchartsData> data = new ArrayList<>();
        List<EchartsLinks> links = new ArrayList<>();
        List<String> thesisTitle = new ArrayList<>();
        // 处理为Echarts需要的格式
        for (Topic topic : topics) {
            Map<String,Object> topicDes = new HashMap<>();
            topicDes.put("title",topic.getName());
            data.add(new EchartsData(topic.getName(),topicDes,40,0));
            List<Thesis> theses = topic.getTheses();
            if(theses!=null){
                for (Thesis  t: theses) {
                    if(!thesisTitle.contains(t.getTitle())){
                        thesisTitle.add(t.getTitle());
//                        System.out.println(t.getTitle());
                        data.add(new EchartsData(t.getTitle(),t,40,1));
                    }
                    links.add(new EchartsLinks(t.getTitle(),topic.getName(),"属于","属于"));
                }
            }
        }
        map.put("data",data);
        map.put("links",links);
//        System.out.println(data.size());
//        System.out.println(links.size());

        return map;
    }
}
