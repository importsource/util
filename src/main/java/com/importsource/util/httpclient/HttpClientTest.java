package com.importsource.util.httpclient;

import java.io.IOException;
import java.net.URLEncoder;
 
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
 
public class HttpClientTest {
    public static void main(String[] args) throws Exception{
        String url = "sdfsdfsdfsdfsdf";
        String host = "localhost";
        String param = "startCity="+URLEncoder.encode("杭州", "utf-8")+"&lastCity=sdfsdf&theDate=&userID=";
        HttpClient httpClient = new HttpClient();
        httpClient.getHostConfiguration().setHost(host, 9090, "http");       
         
        HttpMethod method = getMethod(url, param);
        //HttpMethod method = postMethod(url);
         
        httpClient.executeMethod(method);
         
       // String response = method.getResponseBodyAsString();
        String response = new String(method.getResponseBodyAsString().getBytes("ISO-8859-1"));               
        System.out.println(response);
    }
     
    private static HttpMethod getMethod(String url,String param) throws IOException{
        GetMethod get = new GetMethod(url+"?"+param);
        get.releaseConnection();
        return get;
    }
         
    private static HttpMethod postMethod(String url) throws IOException{
        PostMethod post = new PostMethod(url);
        post.setRequestHeader("Content-Type","application/x-www-form-urlencoded;charset=gbk"); 
        NameValuePair[] param = { new NameValuePair("startCity","杭州"),
                new NameValuePair("lastCity","沈阳"),
                new NameValuePair("userID",""),
                new NameValuePair("theDate","") } ;
        post.setRequestBody(param);
        post.releaseConnection();
        return post;
    }
}