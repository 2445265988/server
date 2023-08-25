package com.example.server.service.Impl;

import com.example.server.dao.WordCloudDao;
import com.example.server.entity.WordCloud;
import com.example.server.service.WordCloudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("wordCloudService")
public class WordCloudServiceImpl implements WordCloudService {

    @Autowired
    private WordCloudDao wordCloudDao;

    @Override
    public void add(WordCloud wordCloud) {
        wordCloudDao.save(wordCloud);
    }

    @Override
    public List<WordCloud> findAll() {
        return wordCloudDao.findAll();
    }

    @Override
    public void deleteAll() {
        wordCloudDao.deleteAll();
    }
}
