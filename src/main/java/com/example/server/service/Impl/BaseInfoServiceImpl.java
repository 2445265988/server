package com.example.server.service.Impl;

import com.example.server.dao.BaseInfoDao;
import com.example.server.entity.BaseInfo;
import com.example.server.entity.TypeAndNum;
import com.example.server.service.BaseInfoService;
import com.example.server.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service("baseInfoService")
public class BaseInfoServiceImpl implements BaseInfoService {

    @Autowired
    private BaseInfoDao baseInfoDao;



    @Override
    public void add(BaseInfo baseInfo) {
        baseInfoDao.save(baseInfo);
    }

    @Override
    public void delete(Integer id) {
        baseInfoDao.deleteById(id);
    }

    @Override
    public void updata(BaseInfo baseInfo) {
        baseInfoDao.save(baseInfo);
    }

    @Override
    public List<BaseInfo> findAll() {
        return baseInfoDao.findAll();
    }

    @Override
    public Page<BaseInfo> list(Map<String, Object> searchMap, int page, int pageSize, String order, String prop) {
        Pageable pageable;
        if("descending".equals(order)){
            pageable = PageRequest.of(page-1,pageSize, Sort.Direction.DESC,prop);
        } else {
            pageable = PageRequest.of(page-1,pageSize, Sort.Direction.ASC,prop);
        }
        return baseInfoDao.findAll((Specification<BaseInfo>) (root, criteriaQuery, criteriaBuilder) -> {
            Predicate predicate = criteriaBuilder.conjunction();
            // 查询条件
            if(StringUtil.isNotEmpty((String)searchMap.get("title"))){
                predicate.getExpressions().add(criteriaBuilder.like(root.get("title"),"%"+searchMap.get("title")+"%"));
            }
            if(StringUtil.isNotEmpty((String)searchMap.get("sname"))){
                predicate.getExpressions().add(criteriaBuilder.like(root.get("sname"),"%"+searchMap.get("sname")+"%"));
            }
            if(StringUtil.isNotEmpty((String)searchMap.get("origin"))){
                predicate.getExpressions().add(criteriaBuilder.equal(root.get("origin"),searchMap.get("origin")));
            }
            if(StringUtil.isNotEmpty((String)searchMap.get("teacher"))){
                predicate.getExpressions().add(criteriaBuilder.equal(root.get("teacher"),searchMap.get("teacher")));
            }
            return predicate;
        }, pageable);
    }

    @Override
    public BaseInfo findBySno(String sno) {
        return baseInfoDao.findBySno(sno);
    }

    @Override
    public List<String> findAllOrigin() {
        return baseInfoDao.findAllOrigin();
    }

    @Override
    public List<String> findAllTeacher() {
        return baseInfoDao.findAllTeacher();
    }

    @Override
    public List<TypeAndNum> orderByTeacher() {
        List<Object> typeAndNumBySemester = baseInfoDao.orderByTeacher();
        return getTypeAndNums(typeAndNumBySemester);
    }

    @Override
    public List<TypeAndNum> orderByOrigin() {
        List<Object> typeAndNumBySemester = baseInfoDao.orderByOrigin();
        return getTypeAndNums(typeAndNumBySemester);
    }

    @Override
    public List<TypeAndNum> orderByType() {
        List<Object> typeAndNumBySemester = baseInfoDao.orderByType();
        return getTypeAndNums(typeAndNumBySemester);
    }

    private List<TypeAndNum> getTypeAndNums(List<Object> Lists) {
        List<TypeAndNum> typeAndNums = new ArrayList<>();
        for (Object o : Lists) {
            Object[] cells = (Object[]) o;
            typeAndNums.add(new TypeAndNum(cells[0].toString(),Integer.parseInt(cells[1].toString())));
        }
        return typeAndNums;
    }
}
