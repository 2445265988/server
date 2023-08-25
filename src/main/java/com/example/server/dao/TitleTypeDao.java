package com.example.server.dao;

import com.example.server.entity.TitleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface TitleTypeDao extends JpaRepository<TitleType,Integer>, JpaSpecificationExecutor<TitleType> {
    // 根据类别返回集合
    List<TitleType> findByType(String type);
}
