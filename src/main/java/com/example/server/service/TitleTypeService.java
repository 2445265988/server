package com.example.server.service;

import com.example.server.entity.TitleType;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface TitleTypeService {
    // 添加词
    void add(TitleType titleType);
    // 删除词
    void delete(Integer id);
    // 修改词
    void updata(TitleType titleType);
    // 获取全部
    List<TitleType> findAll();
    // 条件分页查询
    Page<TitleType> list(Map<String,Object> searchMap, int page, int pageSize, String order, String prop);
    // 根据名称获取
    List<TitleType> findByType(String title);
}
