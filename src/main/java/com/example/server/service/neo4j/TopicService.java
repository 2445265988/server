package com.example.server.service.neo4j;

import com.example.server.entity.neo4j.Topic;

public interface TopicService {
    // 添加
    void add(Topic topic);
    // 删除
    void delete(Long id);
    // 修改
    void updata(Topic topic);
    // 获取全部
    Iterable<Topic> findAll();
    // 根据名称模糊查询
    Iterable<Topic> findByNameLike(String name);
    // 删除
    void deleteAll();
}

