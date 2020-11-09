package io.jenkins.plugins.notifaction.uitl;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.Objects;

/**
 * Http工具类
 * @author 三月柳絮四月雨
 * @date 2020/11/04
 **/
public class HttpUtil {

    private static final String ONE_SAID_URL = "http://api.guaqb.cn/v1/onesaid/";

    /**
     * 发送企业微信机器人消息
     * @param url webhook
     * @param data 消息
     * @return 结果
     */
    public static String push(String url, String data) {
        HttpPost httpPost = new HttpPost(url);
        StringEntity stringEntity = new StringEntity(data, "UTF-8");
        stringEntity.setContentEncoding("UTF-8");
        httpPost.setEntity(stringEntity);
        return execute(httpPost);
    }

    /**
     * 获取一言
     * @return 一言数据
     */
    public static String getOneSaid() {
        HttpPost httpPost = new HttpPost(ONE_SAID_URL);
        return execute(httpPost);
    }

    /**
     * 请求
     * @param httpPost HttpPost
     * @return String 响应数据
     */
    private static String execute(HttpPost httpPost) {
        HttpResponse response = null;
        try(CloseableHttpClient httpClient = HttpClients.createDefault()) {
            response = httpClient.execute(httpPost);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (Objects.isNull(response)) {
            return null;
        }
        String result = null;
        try {
            result = EntityUtils.toString(response.getEntity());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    private HttpUtil() {}

}
