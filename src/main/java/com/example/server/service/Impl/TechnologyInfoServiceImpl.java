package com.example.server.service.Impl;

import com.example.server.dao.TechnologyInfoDao;
import com.example.server.entity.TechnologyInfo;
import com.example.server.entity.TypeAndNum;
import com.example.server.service.TechnologyInfoService;
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

@Service("technologyInfoService")
public class TechnologyInfoServiceImpl implements TechnologyInfoService {

    @Autowired
    private TechnologyInfoDao technologyInfoDao;

    @Override
    public void add(TechnologyInfo baseInfo) {
        technologyInfoDao.save(baseInfo);
    }

    @Override
    public void delete(Integer id) {
        technologyInfoDao.deleteById(id);
    }

    @Override
    public void updata(TechnologyInfo baseInfo) {
        technologyInfoDao.save(baseInfo);
    }

    @Override
    public List<TechnologyInfo> findAll() {
        return technologyInfoDao.findAll();
    }

    @Override
    public Page<TechnologyInfo> list(Map<String, Object> searchMap, int page, int pageSize, String order, String prop) {
        Pageable pageable;
        if("descending".equals(order)){
            pageable = PageRequest.of(page-1,pageSize, Sort.Direction.DESC,prop);
        } else {
            pageable = PageRequest.of(page-1,pageSize, Sort.Direction.ASC,prop);
        }
        return technologyInfoDao.findAll((Specification<TechnologyInfo>) (root, criteriaQuery, criteriaBuilder) -> {
            Predicate predicate = criteriaBuilder.conjunction();
            // 查询条件
            if(StringUtil.isNotEmpty((String)searchMap.get("title"))){
                predicate.getExpressions().add(criteriaBuilder.like(root.get("title"),"%"+searchMap.get("title")+"%"));
            }
            if(StringUtil.isNotEmpty((String)searchMap.get("keyWords"))){
                predicate.getExpressions().add(criteriaBuilder.like(root.get("keyWords"),"%"+searchMap.get("keyWords")+"%"));
            }
            if(StringUtil.isNotEmpty((String)searchMap.get("technology"))){
                predicate.getExpressions().add(criteriaBuilder.like(root.get("technology"),"%"+searchMap.get("technology")+"%"));
            }
            if(StringUtil.isNotEmpty((String)searchMap.get("sname"))){
                predicate.getExpressions().add(criteriaBuilder.like(root.get("sname"),"%"+searchMap.get("sname")+"%"));
            }
            if(StringUtil.isNotEmpty((String)searchMap.get("semester"))){
                predicate.getExpressions().add(criteriaBuilder.equal(root.get("semester"),searchMap.get("semester")));
            }
            return predicate;
        }, pageable);
    }

    @Override
    public TechnologyInfo findBySno(String sno) {
        return technologyInfoDao.findBySno(sno);
    }

    @Override
    public List<TechnologyInfo> findByTechnology(String technonlogy) {
        return technologyInfoDao.findByTechnology(technonlogy);
    }

    @Override
    public List<String> findAllType() {
        return technologyInfoDao.findAllType();
    }

    @Override
    public List<String> findAllSemester() {
        return technologyInfoDao.findAllSemester();
    }

    @Override
    public TechnologyInfo findBySnoAndAndTechnology(String sno, String technology) {
        return technologyInfoDao.findBySnoAndTechnology(sno, technology);
    }

    @Override
    public List<TypeAndNum> findTypeAndNumBySemester(String semester) {
        List<Object> typeAndNumBySemester = technologyInfoDao.findTypeAndNumBySemester(semester);
        List<TypeAndNum> typeAndNums = new ArrayList<>();
        for (Object o : typeAndNumBySemester) {
            Object[] cells = (Object[]) o;
            typeAndNums.add(new TypeAndNum(cells[0].toString(),Integer.parseInt(cells[1].toString())));
        }
        return typeAndNums;
    }

    @Override
    public List<TechnologyInfo> findByTechnologyAndSemester(String technology, String semester) {
        return technologyInfoDao.findByTechnologyAndSemester(technology, semester);
    }


}