package com.example.server.dao;

import com.example.server.entity.Triple;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface TripleDao extends JpaRepository<Triple, Integer>, JpaSpecificationExecutor<Triple> {
}
