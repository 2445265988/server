package com.example.server.service;

import com.example.server.entity.EchartsEntity;
import com.example.server.entity.EchartsRelationship;

import java.util.List;

public interface KGraphService {

    List<EchartsEntity> findAllEntity(String name);

    List<EchartsEntity>getNodeByName(String name);

    List<EchartsRelationship> findAllRelationship();

}
