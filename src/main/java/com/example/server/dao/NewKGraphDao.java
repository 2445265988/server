package com.example.server.dao;


import com.example.server.entity.EchartsEntity;
import com.example.server.entity.EchartsRelationship;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

@Mapper
public interface NewKGraphDao{

    // 获取全部实体数据
//    @Query(value = "SELECT * FROM entity", nativeQuery = true)
    @Select("SELECT name FROM entity limit 100")
//    @Select("SELECT name FROM entity")
    List<EchartsEntity> findAllEntity();
    @Select("SELECT name FROM entity WHERE name LIKE CONCAT('%', #{name}, '%')")
    List<EchartsEntity> findEntityLikeName(String name);

    @Select("SELECT target as name FROM relationship WHERE source = #{name}")
    List<EchartsEntity> getNodeByName(String name);
    // 获取全部关系数据
//    @Query(value = "SELECT * FROM relationship",nativeQuery = true)
    @Select("SELECT * FROM relationship")
    List<EchartsRelationship> findAllRelationship();

}
