package com.example.server.dao;

import com.example.server.entity.BaseInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BaseInfoDao extends JpaRepository<BaseInfo,Integer>, JpaSpecificationExecutor<BaseInfo> {
    // 根据学号获取实体
    BaseInfo findBySno(String sno);

    // 获取全部课题来源
    @Query(value = "SELECT DISTINCT origin from t_base_info", nativeQuery = true)
    List<String> findAllOrigin();

    // 获取全部指导教师
    @Query(value = "SELECT DISTINCT teacher from t_base_info", nativeQuery = true)
    List<String> findAllTeacher();

    // 指导教师比例
    @Query(value = "SELECT teacher,COUNT(*) num FROM t_base_info t GROUP BY t.teacher ORDER BY num DESC", nativeQuery = true)
    List<Object> orderByTeacher();

    // 课题来源比例
    @Query(value = "SELECT origin,COUNT(*) num FROM t_base_info t GROUP BY t.origin ORDER BY num DESC", nativeQuery = true)
    List<Object> orderByOrigin();

    // 课题来源比例
    @Query(value = "SELECT type,COUNT(*) num FROM t_base_info t GROUP BY t.type ORDER BY num DESC", nativeQuery = true)
    List<Object> orderByType();
}
