package com.example.server.dao.neo4j;

import com.example.server.entity.neo4j.Topic;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface TopicDao extends Neo4jRepository<Topic, Long> {
    Iterable<Topic> findByNameLike(String name);
}
