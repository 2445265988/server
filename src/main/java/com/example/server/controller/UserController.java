package com.example.server.controller;

import com.example.server.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@Controller
public class UserController {
    @Autowired
    JdbcTemplate jdbcTemplate;


    @ResponseBody
    @RequestMapping(value = "/userList", method = RequestMethod.GET)
    public List userList(@RequestParam(value = "key", required = false) String key) {

        String baseSql = "select * from user";
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
    @RequestMapping(value = "/searchID", method = RequestMethod.POST)
    public List search_id(HttpServletRequest request) {
        String keyword = request.getParameter("keyword");
        String value = request.getParameter("value");
        String sql = "";
        if ("id".equals(keyword)) {
            sql = "select * from user where " + keyword + "=" + value;
        } else {
            sql = "select * from user where " + keyword + "=" + "\"" + value + "\"";
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
    @RequestMapping(value = "/deleteUser", method = RequestMethod.POST)
    public void delete_user(HttpServletRequest request) {
        String id = request.getParameter("id");
        String sql = "delete from user where id=" + id;

        System.out.println(sql);

        jdbcTemplate.update(sql);
    }


    @ResponseBody
    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public void addUser(HttpServletRequest request) {
        String name = request.getParameter("name");
        String pwd = request.getParameter("pwd");
        String sex = request.getParameter("sex");
        String birthday = request.getParameter("birthday");
        String address = request.getParameter("address");
        String email = request.getParameter("email");

        String sql = "insert into `user`(`name`, `pwd`, `sex`, `birthday`, `address`, `email`) " +
                "values (" + "\"" + name + "\", " + "\"" + pwd + "\", " + "\"" + sex + "\", " + "\"" + birthday + "\", " + "\"" + address + "\", " + "\"" + email + "\"" + ")";
        System.out.println(sql);
        jdbcTemplate.update(sql);

//        return maps;
    }
}
