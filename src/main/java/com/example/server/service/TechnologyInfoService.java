package com.example.server.service;

import com.example.server.entity.TechnologyInfo;
import com.example.server.entity.TypeAndNum;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface TechnologyInfoService {
    // 添加
    void add(TechnologyInfo technologyInfo);
    // 删除
    void delete(Integer id);
    // 修改
    void updata(TechnologyInfo technologyInfo);
    // 获取全部
    List<TechnologyInfo> findAll();
    // 条件分页查询
    Page<TechnologyInfo> list(Map<String,Object> searchMap, int page, int pageSize, String order, String prop);
    // 根据学号返回实体
    TechnologyInfo findBySno(String sno);
    // 根据技术返回实体
    List<TechnologyInfo> findByTechnology(String technonlogy);
    // 获取全部技术类别
    List<String> findAllType();
    // 获取全部学期类别
    List<String> findAllSemester();
    // 根据学号和技术类别返回实体
    TechnologyInfo findBySnoAndAndTechnology(String sno, String technology);
    // 根据学期返回类别数目
    List<TypeAndNum> findTypeAndNumBySemester(String semester);
    // 根据学期和类别返回实体
    List<TechnologyInfo> findByTechnologyAndSemester(String technology, String semester);
}
