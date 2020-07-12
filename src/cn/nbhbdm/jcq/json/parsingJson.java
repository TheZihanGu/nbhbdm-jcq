package cn.nbhbdm.jcq.json;

import com.alibaba.fastjson.JSONObject;
/**
 * nbhbdm-jcq - JSON 解析类
 *
 * @author TheZihanGu
 * @date 2020/07/12
 */
public class parsingJson {
    public static void main(String[] args) {
        System.out.println(parsing("哈哈哈"));
    }
    public static String parsing(String keyword){
        String jsonStr = onJson.result(keyword);
        JSONObject outJson = JSONObject.parseObject(jsonStr);
        String sLink = outJson.getString("slink");
        return sLink;
    }
}