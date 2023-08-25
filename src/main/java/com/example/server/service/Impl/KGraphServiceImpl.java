package com.example.server.service.Impl;

import com.example.server.dao.NewKGraphDao;
import com.example.server.entity.EchartsEntity;
import com.example.server.entity.EchartsRelationship;
import com.example.server.service.KGraphService;
import com.example.server.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KGraphServiceImpl implements KGraphService {

    @Autowired
    private NewKGraphDao newKGraphDao;

    @Override
    public List<EchartsEntity> findAllEntity(String name) {
        System.out.println("执行findAllEntity！");
        List<EchartsEntity> test;
        if(StringUtil.isEmpty(name)) {
             test = newKGraphDao.findAllEntity();
        }else {
            test = newKGraphDao.findEntityLikeName(name);
        }
        System.out.println(test);
        return test;
    }
    @Override
    public List<EchartsEntity> getNodeByName(String name) {
        System.out.println("getNodeByName！");
        List<EchartsEntity> test = newKGraphDao.getNodeByName(name);
        System.out.println(test);
        return test;
    }

    @Override
    public List<EchartsRelationship> findAllRelationship() {
        System.out.println("执行findAllRelationship！");
        return newKGraphDao.findAllRelationship();
    }
}
