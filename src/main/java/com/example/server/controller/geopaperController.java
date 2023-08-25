package com.example.server.controller;

import com.example.server.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@Controller
public class geopaperController {
    @Autowired
    JdbcTemplate jdbcTemplate;


    @ResponseBody
    @RequestMapping(value = "/paperList", method = RequestMethod.GET)
    public List paperList(@RequestParam(value = "key", required = false) String key) {

        String baseSql = "select * from paper";
        List<Map<String, Object>> maps = new ArrayList<>();
        if (!StringUtil.isEmpty(key)) {
            String searchSql = baseSql + " WHERE name LIKE ?";
            maps = jdbcTemplate.queryForList(searchSql, "%" + key + "%");
        } else {
            maps = jdbcTemplate.queryForList(baseSql);
        }

        System.out.println(maps);
        return maps;
    }

    @ResponseBody
    @RequestMapping(value = "/searchpaperID", method = RequestMethod.POST)
    public List search_paper(HttpServletRequest request) {
        String keyword = request.getParameter("keyword");
        String value = request.getParameter("value");
        String sql = "";
        if ("id".equals(keyword)) {
            sql = "select * from paper where " + keyword + "=" + value;
        } else {
            sql = "select * from paper where " + keyword + "=" + "\"" + value + "\"";
        }

        System.out.println(sql);
        try {
            List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql);

            System.out.println(value);

            return maps;
        } catch (Exception e) {
            System.out.println("没找到相关信息");
            Map<String, Object> map = new HashMap<String, Object>();
            List<Map<String, Object>> maps = new ArrayList<Map<String, Object>>();
            map.put("id", null);
            maps.add(map);
            return maps;
        }
    }


    @ResponseBody
    @RequestMapping(value = "/deletepaper", method = RequestMethod.POST)
    public void delete_paper(HttpServletRequest request) {
        String id = request.getParameter("id");
        String sql = "delete from paper where id=" + id;
        System.out.println(sql);
        jdbcTemplate.update(sql);
    }


    @ResponseBody
    @RequestMapping(value = "/addpaper", method = RequestMethod.POST)
    public void addPaper(HttpServletRequest request) {
        String name = request.getParameter("name");
        String keyword = request.getParameter("keyword");
        String latitude = request.getParameter("latitude");
        String longitude = request.getParameter("longitude");
        String introduction = request.getParameter("introduction");
        String image = request.getParameter("image");
        String place = request.getParameter("place");
        String url = request.getParameter("url");
        String year = request.getParameter("year");

        String sql = "insert into `paper`(`name`, `keyword`, `latitude`, `longitude`, `introduction`, `image`, `place`, `url`, `year`) " +
                "values (" + "\"" + name + "\", " + "\"" + keyword + "\", " + "\"" + latitude + "\", " + "\"" + longitude + "\", " + "\"" + introduction + "\", " + "\"" + image + "\", " + "\"" + place + "\"," + "\"" + url + "\"," + "\"" + year + "\"" + ")";
        System.out.println(sql);
        jdbcTemplate.update(sql);

//        return maps;
    }
}
