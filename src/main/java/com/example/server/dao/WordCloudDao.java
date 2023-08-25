package com.example.server.dao;

import com.example.server.entity.WordCloud;
import org.apache.ibatis.annotations.Select;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface WordCloudDao extends JpaRepository<WordCloud,Integer>, JpaSpecificationExecutor<WordCloud> {

}
