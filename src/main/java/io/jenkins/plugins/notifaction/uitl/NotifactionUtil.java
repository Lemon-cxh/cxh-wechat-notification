package io.jenkins.plugins.notifaction.uitl;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.util.Objects;

/**
 * 企业微信群机器人发送消息工具类
 * @author 三月柳絮四月雨
 * @date 2020/11/04
 **/
public class NotifactionUtil {

    private static final CloseableHttpClient HTTP_CLIENT = HttpClients.createDefault();

    public static String push(String url, String data) {
        HttpPost httpPost = new HttpPost(url);
        StringEntity stringEntity = new StringEntity(data, "UTF-8");
        stringEntity.setContentEncoding("UTF-8");
        httpPost.setEntity(stringEntity);
        HttpResponse response = null;
        try {
            response = HTTP_CLIENT.execute(httpPost);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (Objects.isNull(response)) {
            return null;
        }
        return response.getEntity().toString();
    }

    private NotifactionUtil() {}

}
