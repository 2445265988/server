package com.example.server.service.neo4j;

import com.example.server.entity.neo4j.Thesis;

public interface ThesisService {
    // 添加
    void add(Thesis thesis);
    // 删除
    void delete(Long id);
    // 修改
    void updata(Thesis thesis);
    // 获取全部
    Iterable<Thesis> findAll();
    // 根据学号获取实体
    Thesis findBySno(String sno);
    // 删除
    void deleteAll();
}
