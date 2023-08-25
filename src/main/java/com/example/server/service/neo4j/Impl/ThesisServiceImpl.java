package com.example.server.service.neo4j.Impl;

import com.example.server.dao.neo4j.ThesisDao;
import com.example.server.entity.neo4j.Thesis;
import com.example.server.service.neo4j.ThesisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ThesisServiceImpl implements ThesisService {

    @Autowired
    private ThesisDao thesisDao;
    @Override
    public void add(Thesis thesis) {
        thesisDao.save(thesis);
    }

    @Override
    public void delete(Long id) {
        thesisDao.deleteById(id);
    }

    @Override
    public void updata(Thesis thesis) {
        thesisDao.save(thesis);
    }

    @Override
    public Iterable<Thesis> findAll() {
        return thesisDao.findAll();
    }

    @Override
    public Thesis findBySno(String sno) {

        return thesisDao.findBySno(sno);
    }

    @Override
    public void deleteAll() {
        thesisDao.deleteAll();
    }

}
