package com.example.server.service.Impl;

import com.example.server.dao.TitleTypeDao;
import com.example.server.entity.TitleType;
import com.example.server.service.TitleTypeService;
import com.example.server.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.util.List;
import java.util.Map;

@Service("titleTypeService")
public class TitleTypeServiceImpl implements TitleTypeService {

    @Autowired
    private TitleTypeDao titleTypeDao;

    @Override
    public void add(TitleType titleType) {
        titleTypeDao.save(titleType);
    }

    @Override
    public void delete(Integer id) {
        titleTypeDao.deleteById(id);
    }

    @Override
    public void updata(TitleType titleType) {
        titleTypeDao.save(titleType);
    }

    @Override
    public List<TitleType> findAll() {
        return titleTypeDao.findAll();
    }

    @Override
    public Page<TitleType> list(Map<String, Object> searchMap, int page, int pageSize, String order, String prop) {
        Pageable pageable;
        if("descending".equals(order)){
            pageable = PageRequest.of(page-1,pageSize, Sort.Direction.DESC,prop);
        } else {
            pageable = PageRequest.of(page-1,pageSize, Sort.Direction.ASC,prop);
        }
        return titleTypeDao.findAll((Specification<TitleType>) (root, criteriaQuery, criteriaBuilder) -> {
            Predicate predicate = criteriaBuilder.conjunction();
            if(StringUtil.isNotEmpty((String)searchMap.get("type"))){
                predicate.getExpressions().add(criteriaBuilder.like(root.get("type"),"%"+searchMap.get("type")+"%"));
            }
            return predicate;
        }, pageable);
    }

    @Override
    public List<TitleType> findByType(String type) {
        return titleTypeDao.findByType(type);
    }
}
