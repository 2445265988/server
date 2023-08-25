package com.example.server.controller;


import com.huaban.analysis.jieba.JiebaSegmenter;
import org.json.JSONArray;
import net.sf.json.JSONObject;
import org.json.JSONException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@CrossOrigin
@Controller
public class WordReconiton {

    public Socket socket;


    @ResponseBody
    @RequestMapping(value = "/entityExtract", method = RequestMethod.POST)
    public Map entityExtract(HttpServletRequest request) {
        String model = request.getParameter("model");
        String data = request.getParameter("data");

        System.out.println("接收到前端发送中文分词数据...");
        Map<String, String> map = new HashMap<String, String>();
        JiebaSegmenter segmenter = new JiebaSegmenter();
        String result = segmenter.process(data, JiebaSegmenter.SegMode.INDEX).toString();
        System.out.println(result);
        map.put("content", result);


        return map;
    }

    /**
     * 中文分词接口
     *
     * @param data  分词文本
     * @param model 模型
     * @return 结果
     */

    @ResponseBody
    @RequestMapping(value = "/wordreconiton", method = RequestMethod.POST)
    public Map<String, Object> zhongwenfenci(@RequestParam String data, @RequestParam String model) {
        System.out.println("model:" + model);
        System.out.println("data:" + data);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("model", model);
        jsonObject.put("data", data);
        String str = jsonObject.toString();
        Map<String, Object> map = new HashMap<>();

        InputStream is = null;
        OutputStream os = null;
        try {
            socket = new Socket("127.0.0.1", 8080);
            os = socket.getOutputStream();
            PrintStream out = new PrintStream(os, true, "UTF-8");
            out.print(str);
            is = socket.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
            String tmp;
            StringBuilder sb = new StringBuilder();
            while ((tmp = br.readLine()) != null) {
                sb.append(tmp).append('\n');
                break;
            }
            System.out.println(model+"接受到数据: " + sb);
            JSONArray jsonArray = new JSONArray(sb.toString());
            System.out.println("转换数据: " + jsonArray);
            map.put("content", jsonArray.toString());
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
                if (os != null) {
                    os.close();
                }
                if (socket != null) {
                    socket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("远程接口调用结束...");
        }
        return map;
    }
}
