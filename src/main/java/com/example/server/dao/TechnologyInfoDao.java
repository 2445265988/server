package com.example.server.dao;

import com.example.server.entity.TechnologyInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TechnologyInfoDao extends JpaRepository<TechnologyInfo,Integer>, JpaSpecificationExecutor<TechnologyInfo> {
    // 根据学号返回实体
    TechnologyInfo findBySno(String sno);

    // 根据类别返回实体
    List<TechnologyInfo> findByTechnology(String technonlogy);

    // 获取全部技术类别
    @Query(value = "SELECT DISTINCT type FROM t_title_type", nativeQuery = true)
    List<String> findAllType();

    // 获取全部学期类别
    @Query(value = "SELECT DISTINCT semester FROM t_technology_info ORDER BY semester Asc ", nativeQuery = true)
    List<String> findAllSemester();

    // 根据学号和技术类别返回实体
    TechnologyInfo findBySnoAndTechnology(String sno, String technology);

    // 根据技术类别和学期返回实体
    List<TechnologyInfo> findByTechnologyAndSemester(String technology, String semester);

    // 根据学期返回类别数目
    @Query(value = "SELECT t.technology type, COUNT(*) num FROM t_technology_info t WHERE semester = ?1 GROUP BY t.technology ORDER BY num DESC limit 15", nativeQuery = true)
    List<Object> findTypeAndNumBySemester(String semester);
}
