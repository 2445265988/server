package com.example.server.controller;

import com.example.server.entity.TitleType;
import com.example.server.entity.TypeAndNum;
import com.example.server.service.BaseInfoService;
import com.example.server.service.TechnologyInfoService;
import com.example.server.service.TitleTypeService;
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
public class ChartsController {
    @Autowired
    private TechnologyInfoService technologyInfoService;
    @Autowired
    private TitleTypeService titleTypeService;
    @Autowired
    private BaseInfoService baseInfoService;

    @ResponseBody
    @RequestMapping(value = "getCurveChartData", method = RequestMethod.GET)
    public List getCurveChartData(){
        List<String> product = new ArrayList<>();
        List<String> allSemester = technologyInfoService.findAllSemester();
        product.add("product");
        product.addAll(allSemester);
        List<List<String>> res = new ArrayList<>();
        res.add(product);
        for (TitleType titleType : titleTypeService.findAll()) {
            List<String> type = new ArrayList<>();
            type.add(titleType.getType());
            for (String semester : allSemester) {
                int size = technologyInfoService.findByTechnologyAndSemester(titleType.getType(), semester).size();
                type.add(String.valueOf(size));
            }
            res.add(type);
        }

        return res;
    }

    @ResponseBody
    @RequestMapping(value = "/getBarChartRace", method = RequestMethod.GET)
    public Map getBarChartRace(){
        Map<String,Object> map1 = new HashMap<>();
        List<String> allSemester = technologyInfoService.findAllSemester();
        List<String> years = new ArrayList<>();
        List<List<String>> jdData = new ArrayList<>();
        List<List<Integer>> data = new ArrayList<>();
        for (String semester : allSemester) {
            years.add(semester);
            List<String> jdData_item = new ArrayList<>();
            List<Integer> data_item = new ArrayList<>();
            for (TypeAndNum typeAndNum : technologyInfoService.findTypeAndNumBySemester(semester)) {
                jdData_item.add(typeAndNum.getType());
                data_item.add(typeAndNum.getNum());
            }
            jdData.add(jdData_item);
            data.add(data_item);
        }
        map1.put("years",years);
        map1.put("jdData",jdData);
        map1.put("data",data);

        return map1;
    }

    @ResponseBody
    @RequestMapping(value = "/getAllTypes", method = RequestMethod.GET)
    public List getAllTypes(){
        return titleTypeService.findAll();
    }
}
