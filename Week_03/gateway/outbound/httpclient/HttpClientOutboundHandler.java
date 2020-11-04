package com.example.io.github.kimmking.gateway.outbound.httpclient;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 自己实现的OutboundHandler
 *
 * */
public class HttpClientOutboundHandler {

    private CloseableHttpClient httpClient;
    private String outboundUrl;


    public HttpClientOutboundHandler(){}

    public HttpClientOutboundHandler(String outboundUrl){
        this.outboundUrl = outboundUrl;
        //创建client
        httpClient = HttpClients.createDefault();
    }

    public void handle(final FullHttpRequest fullRequest, final ChannelHandlerContext ctx) throws IOException {
        final String url = this.outboundUrl + fullRequest.uri();
        post(fullRequest, ctx, url);
    }

    public void post(final FullHttpRequest fullRequest, final ChannelHandlerContext ctx, String url) throws IOException {
        System.out.println("url::"+url);
        System.out.println("outboundUrl::"+outboundUrl);
        //2.创建post请求
        HttpPost httpPost = new HttpPost(url);

        //设置长连接
        httpPost.setHeader("Connection", "keep-alive");
        //3.为了安全，防止恶意攻击，在post请求中限制了浏览器才能访问
        httpPost.addHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/68.0.3440.106 Safari/537.36");
        //4.判断状态码
        List<NameValuePair> parameters = new ArrayList<NameValuePair>(0);
        parameters.add(new BasicNameValuePair("scope", "project"));
        parameters.add(new BasicNameValuePair("q", "java"));

        UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(parameters,"UTF-8");

        httpPost.setEntity(formEntity);

        //5.发送请求
        CloseableHttpResponse response = httpClient.execute(httpPost);

        if(response.getStatusLine().getStatusCode()==200){
            HttpEntity entity = response.getEntity();
            String string = EntityUtils.toString(entity, "utf-8");
            System.out.println(string);
        }
        //6.关闭资源
        response.close();
        httpClient.close();
    }


}
