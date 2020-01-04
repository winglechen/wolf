package study.daydayup.wolf.demo.my.starter.json;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import study.daydayup.wolf.framework.rpc.Result;

/**
 * study.daydayup.wolf.demo.my.starter.json
 *
 * @author Wingle
 * @since 2020/1/3 4:14 下午
 **/
public class Fast {
    public static void main(String[] args) throws Exception {
        Result result = Result.fail(200, "xx");

        String jsonResult = JSON.toJSONString(result);
        System.out.println("fast json: " +jsonResult);

        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(System.out, result);

        Result newResult = JSON.parseObject(jsonResult, Result.class);

    }
}
