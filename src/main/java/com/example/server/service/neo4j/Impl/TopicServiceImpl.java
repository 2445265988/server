package com.example.server.service.neo4j.Impl;

import com.example.server.dao.neo4j.TopicDao;
import com.example.server.entity.neo4j.Topic;
import com.example.server.service.neo4j.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TopicServiceImpl implements TopicService {

    @Autowired
    private TopicDao topicsDao;
    @Override
    public void add(Topic topics) {
        topicsDao.save(topics);
    }

    @Override
    public void delete(Long id) {
        topicsDao.deleteById(id);
    }

    @Override
    public void updata(Topic topics) {
        topicsDao.save(topics);
    }

    @Override
    public Iterable<Topic> findAll() {
//        System.out.println("我在测试是不是从这实例化的");
        return topicsDao.findAll();
    }

    @Override
    public Iterable<Topic> findByNameLike(String name) {
        return topicsDao.findByNameLike(name);
    }
    @Override
    public void deleteAll() {
        topicsDao.deleteAll();
    }

}
