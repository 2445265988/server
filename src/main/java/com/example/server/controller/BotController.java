package com.example.server.controller;

import com.example.server.entity.EchartsEntity;
import com.example.server.entity.EchartsRelationship;
import com.example.server.entity.WordCloud;
import com.example.server.service.WordCloudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@Controller
public class BotController {


    @ResponseBody
    @RequestMapping(value = "/bot", method = RequestMethod.GET)
    public Map<String, Object> getWordCloud(@RequestParam String spoken) {
        System.out.println("now in BotController");
        Map<String, Object> result = new HashMap<>();
        String botAnswer = "";
        String card = "";
        List<EchartsRelationship> links = new ArrayList<>();
        List<EchartsEntity> nodes = new ArrayList<>();
        String[] administrativeDivisionsOfWuhan = {
                "武汉市",
                "江岸区",
                "江汉区",
                "硚口区",
                "汉阳区",
                "武昌区",
                "青山区",
                "洪山区",
                "东西湖区",
                "汉南区",
                "蔡甸区",
                "江夏区",
                "黄陂区",
                "新洲区"
        };

        if (spoken.contains("武汉市下级行政区划")) {
                botAnswer = "武汉市下级行政区划包括: 江岸区,江汉区,硚口区,汉阳区,武昌区,青山区,洪山区,东西湖区,汉南区,蔡甸区,江夏区,黄陂区,新洲区";
                card = "武汉三镇在历史上形成的时序为江北《汉阳》先于江南《武昌)，而汉口在明代以前，不过是毗连汉阳的一个水曲荒洲，因此，在明代以前，所谓“武汉”，" +
                        "乃是武昌、汉阳两地的合称。元代武昌、汉阳均属湖广行省，鄂(武昌)沙(汉阳》并称，是为双城。1926 年秋，国民革命军攻克武汉。次年初，" +
                        "国民政府将汉口市《辖汉阳县)与武昌合并划为京兆区，作为首都，并建立统一的武汉市政府，此时，武汉才取得了作为政区、市区的称谓。" +
                        "此后，武昌、汉阳、汉口时分时合，直到1949 年解放前夕，汉口作为直辖市，武昌作为省辖市，汉阳作为县而分治。解放后，政务院将汉口、" +
                        "武昌、汉阳《县府所在地及邻近地区)合并为武汉市《原汉阳县治所迁至蔡甸，保留县的建制》，武汉市人民政府设在汉口。至此，武汉三镇合三为一了。";
            for(String name: administrativeDivisionsOfWuhan){
                EchartsEntity echartsEntity = new EchartsEntity(name);
                nodes.add(echartsEntity);
                EchartsRelationship echartsRelationship = new EchartsRelationship();
                echartsRelationship.setName("行政区");
                echartsRelationship.setSource("武汉市");
                echartsRelationship.setTarget(name);
                links.add(echartsRelationship);
            }
        }
        result.put("text", botAnswer);
        result.put("card",card);
        result.put("nodes",nodes);
        result.put("links",links);
        return result;
    }
}
