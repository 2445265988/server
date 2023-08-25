package com.example.server.controller;

import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@Controller
public class OntologyController {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @ResponseBody
    @RequestMapping(value = "/getOutline", method = RequestMethod.GET)
    List getOutline(){
        String sql = "select * from outline";
        List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql);


        System.out.println(maps.get(0).get("Code"));
//        for(int i=0; i<maps.size(); i++){
//            JSONObject jsonObject = new JSONObject();
//            jsonObject.put("model", "model");
//            jsonObject.put("content", "data");
//
//        }
//        System.out.println(getOntology("地质领域本体", "", "outline"));
        return maps;
    }

    @ResponseBody
    @RequestMapping(value = "/getOntology", method = RequestMethod.GET)
    Map getOntology(){
        System.out.println("now in getOntology");
        Map<String, Object> map = new HashMap<>();
        try{
            File f = new File(this.getClass().getResource("/").getPath());
            String pathname = f.toString() + "\\static\\json\\stree.json";
            String data = getOntology_per_layer("地质领域本体", "", "outline").toString();
            System.out.println(data);

            File file =new File(pathname);

            //if file doesnt exists, then create it
            if(!file.exists()){
                file.createNewFile();
            }

            //true = append file
            FileWriter fileWritter = new FileWriter(file.getName());
            fileWritter.write(data);
            fileWritter.close();

            System.out.println("Done");
            map.put("result", "OK");

        }catch(IOException e){
            map.put("result", "error");
            e.printStackTrace();
        }
//        return getOntology_per_layer("地质领域本体", "", "outline");

        return map;
    }

    JSONObject getOntology_per_layer(String name, String Code, String db){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", name);
        String sql = "";
        if(db == "outline"){
            sql = "select * from outline";
        }else if(db == "category"){
            sql = "SELECT * FROM category WHERE CODE LIKE "+"\""+Code+"_"+"\"";
        }else{
            sql = "SELECT * FROM " + db + " WHERE Code LIKE " + "\""+Code+"__"+"\"";
        }

        List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql);
        if(maps.size() != 0){
            List<JSONObject> children = new ArrayList<JSONObject>();
            for(int i=0; i<maps.size(); i++){
                if(name == "地质领域本体") {
                    children.add(getOntology_per_layer(String.valueOf(maps.get(i).get("Name")), String.valueOf(maps.get(i).get("Code")), "category"));
                }
                else{
                    children.add(getOntology_per_layer(String.valueOf(maps.get(i).get("Name")), String.valueOf(maps.get(i).get("Code")), String.valueOf(maps.get(i).get("Code")).substring(0, 2)));
                }
//                jsonObject.put("value", String.valueOf(maps.get(i).get("Code")));
            }
            jsonObject.put("children", children);
        }

//        System.out.println(jsonObject);
        return jsonObject;

    }

    @ResponseBody
    @RequestMapping(value = "/getCategory", method = RequestMethod.POST)
    List getCategory(HttpServletRequest request){
        String Code = request.getParameter("Code");
        String sql = "SELECT * FROM category WHERE CODE LIKE "+"\""+Code+"%"+"\"";
//        System.out.println(sql);
        List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql);
//        System.out.println(maps);

//        System.out.println("获取具体种类完毕");

        return maps;
    }

    @ResponseBody
    @RequestMapping(value = "/getThird", method = RequestMethod.POST)
    List getThird(HttpServletRequest request){
        String Code = request.getParameter("Code");
        String db = Code.substring(0, 2);
        System.out.println("db:"+ db);
        String sql = "SELECT * FROM " + db + " WHERE Code LIKE " + "\""+Code+"__"+"\"";
        System.out.println("sql:"+ sql);
        List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql);

        return maps;
    }

    @ResponseBody
    @RequestMapping(value = "/getDetail", method = RequestMethod.POST)
    List getDetail(HttpServletRequest request){
        String Code = request.getParameter("Code");
        String db = Code.substring(0, 2);
        System.out.println("db:"+ db);
        String sql = "SELECT * FROM " + db + " WHERE Code LIKE " + "\""+Code+"\"";
        System.out.println("sql:"+ sql);
        List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql);

        return maps;
    }

    @ResponseBody
    @RequestMapping(value = "/getOntologyTree", method = RequestMethod.GET)
    List getOntologyTree(HttpServletRequest request){
//        String catagory = request.getParameter("catagory");
        String catagory = "ontologytreetwo";
        String sql = "SELECT * FROM longstring WHERE catagory = " + "\"" + catagory + "\"";
        List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql);
        System.out.println(maps);

        return maps;
    }
}
