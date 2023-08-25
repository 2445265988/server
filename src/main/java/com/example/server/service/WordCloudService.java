package com.example.server.service;

import com.example.server.entity.WordCloud;

import java.util.List;

public interface WordCloudService {
    // 添加
    void add(WordCloud wordCloud);
    // 查询全部
    List<WordCloud> findAll();
    // 删除全部
    void deleteAll();
}