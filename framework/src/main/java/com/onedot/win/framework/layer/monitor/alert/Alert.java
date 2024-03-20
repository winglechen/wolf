package com.onedot.win.framework.layer.monitor.alert;

import lombok.extern.slf4j.Slf4j;
import com.onedot.win.common.util.lang.StringUtil;
import com.onedot.win.framework.rpc.http.ContentTypeEnum;
import com.onedot.win.framework.rpc.http.client.HttpClient;

@Slf4j
public class Alert {
    private static final String MESSAGE_BOT_URL = "https://open.feishu.cn/open-apis/bot/v2/hook/083436d2-1a20-467a-b13b-f5cfd7e25218";

    public static void send(String text) {
        String jsonString = StringUtil.join("{\"msg_type\": \"text\", \"content\": {\"text\": \"", text , "\"}}");

        log.info("[Alert] {}", text);
        post(jsonString);
    }

    public static void send(String title, String content) {
        String jsonString = StringUtil.join("{\"msg_type\":\"interactive\",\"card\":{\"elements\":[{\"tag\":\"div\",\"text\":{\"content\":\"", content ,"\",\"tag\":\"lark_md\"}}],\"header\":{\"title\":{\"content\":\"", title, "\",\"tag\":\"plain_text\"},\"template\":\"red\"}}}");

        log.info("[Alert] {}: {}", title, content);
        post(jsonString);
    }

    private static void post(String jsonString) {
        String responseBody = HttpClient.builder()
                .url(MESSAGE_BOT_URL)
                .post(jsonString, ContentTypeEnum.JSON)
                .execute()
                .getBody();

        log.info("[Alert] Response: {}", responseBody);
    }

}

