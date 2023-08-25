package com.example.server.service;

import com.example.server.entity.BaseInfo;
import com.example.server.entity.TypeAndNum;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface BaseInfoService {
    // 添加
    void add(BaseInfo baseInfo);
    // 删除
    void delete(Integer id);
    // 修改
    void updata(BaseInfo baseInfo);
    // 获取全部
    List<BaseInfo> findAll();
    // 条件分页查询
    Page<BaseInfo> list(Map<String,Object> searchMap, int page, int pageSize, String order, String prop);
    // 根据sno获取实体
    BaseInfo findBySno(String sno);
    // 获取全部课题来源
    List<String> findAllOrigin();
    // 获取全部指导教师
    List<String> findAllTeacher();
    // 得到 指导教师的比例
    List<TypeAndNum> orderByTeacher();
    // 得到 课题来源的比例
    List<TypeAndNum> orderByOrigin();
    // 得到 课题类型的比例
    List<TypeAndNum> orderByType();
}
