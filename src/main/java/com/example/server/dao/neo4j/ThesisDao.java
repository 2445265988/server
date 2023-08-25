package com.example.server.dao.neo4j;

import com.example.server.entity.neo4j.Thesis;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface ThesisDao extends Neo4jRepository<Thesis,Long> {
    //    List<Thesis> findBySno(String sno);
    Thesis findBySno(String sno);
}
