package com.jason.manager.utils;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class HttpUtils {

	private static Logger logger = LoggerFactory.getLogger(HttpUtils.class);
	//用于打印log
	private static AtomicInteger cnt = new AtomicInteger();
	
	public static String sendPost(String url, byte[] body){
		String result = "";
		HttpURLConnection httpURLConnection = null;
		InputStream inputStream = null;
		OutputStream outputStream = null;
		BufferedReader reader = null;
		StringBuffer buffer = new StringBuffer();
		try {
			URL u = new URL(url);
			httpURLConnection = (HttpURLConnection) u.openConnection();
			httpURLConnection.setRequestMethod("POST");
			httpURLConnection.setConnectTimeout(30000);
			httpURLConnection.setReadTimeout(10000);
			httpURLConnection.setDoInput(true);
			httpURLConnection.setDoOutput(true);
			httpURLConnection.setUseCaches(false);
			httpURLConnection.connect();
			outputStream = httpURLConnection.getOutputStream();
			outputStream.write(body);
			outputStream.flush();
			outputStream.close();
			
			inputStream = httpURLConnection.getInputStream();
			reader = new BufferedReader(new InputStreamReader(inputStream,"utf-8"));
			String line = null;
			while ((line = reader.readLine()) != null) {
				buffer.append(line);
			}
			result = buffer.toString();
		} catch (Exception e) {
			return e.toString();
		} finally {
			if(inputStream != null){
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(reader != null){
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(httpURLConnection != null){
				httpURLConnection.disconnect();
			}
		}
		return result;
	}
	
	
	public static String sendPost(String url, Map<String, String> params,int timeOut){
		String result = "";
		HttpURLConnection httpURLConnection = null;
		InputStream inputStream = null;
		OutputStream outputStream = null;
		BufferedReader reader = null;
		StringBuffer buffer = new StringBuffer();
		try {
			URL u = new URL(url);
			httpURLConnection = (HttpURLConnection) u.openConnection();
			httpURLConnection.setRequestMethod("POST");
			httpURLConnection.setConnectTimeout(5000);
			httpURLConnection.setReadTimeout(timeOut);
			httpURLConnection.setDoInput(true);
			httpURLConnection.setDoOutput(true);
			httpURLConnection.setUseCaches(false);
			httpURLConnection.connect();
			
			outputStream = httpURLConnection.getOutputStream();
			
			StringBuffer buffer2 = new StringBuffer();
			Set<String> keys = params.keySet();
			for(String key : keys){
				buffer2.append(key);
				buffer2.append("=");
				String v = params.get(key);
				if(v == null || "".equals(v)){
					buffer2.append(v);
				}else{
					buffer2.append(URLEncoder.encode(params.get(key),"utf-8"));
				}
				buffer2.append("&");
			}
			
			String temp = buffer2.toString();
			String body = temp.substring(0, temp.length() - 1);
			outputStream.write(body.getBytes("utf-8"));
			outputStream.flush();
			outputStream.close();
			inputStream = httpURLConnection.getInputStream();
			reader = new BufferedReader(new InputStreamReader(inputStream, "utf-8"));
			String line = null;
			while ((line = reader.readLine()) != null) {
				buffer.append(line);
			}
			result = buffer.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		} 
		finally {
			if(inputStream != null){
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(reader != null){
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(httpURLConnection != null){
				httpURLConnection.disconnect();
			}
		}
		return result;
	}
	
	
	public static String sendGet(String url,Map<String, String> data){
		CloseableHttpClient httpclient = HttpClients.createDefault();
		String parameters = httpBuildQuery(data);
		long time = System.currentTimeMillis();
		int step = cnt.incrementAndGet();
		System.out.println("["+parameters+"]");
		logger.info(String.format("request%d:%s",step,parameters));
		String realUrl = data.size()>0?url+"?"+parameters:url;
		HttpGet httpget = new HttpGet(realUrl);  
		RequestConfig requestConfig = RequestConfig.custom()  
		        .setConnectTimeout(5000).setConnectionRequestTimeout(1000)  
		        .setSocketTimeout(30000).build(); 
		httpget.setConfig(requestConfig);
		
		String result = "";
		CloseableHttpResponse resp =null;
		try {
			resp = httpclient.execute(httpget);
			logger.info(String.format("time%d:%d",step,(System.currentTimeMillis()-time)));
			 // 获取响应实体    
            HttpEntity entity = resp.getEntity();
            result = EntityUtils.toString(entity);
            logger.info(String.format("reponse%d:%s",step,result));
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(String.format("err%d:%s",step, url),e);
		}finally{
			try {
				if(resp != null) resp.close();
			} catch (Exception e) {
				e.printStackTrace();
				logger.error(String.format("close%d:%s",step, url),e);
			}
		}
         
        return result;
	}
	
	
	/**
	 * 参数编码
	 * @param data
	 * @return 
	 */
	public static String httpBuildQuery(Map<String, String> data) {
		if(data.size() == 0) return "";
		
		String ret = "";
		String k, v;
		Iterator<String> iterator = data.keySet().iterator();
		while (iterator.hasNext()) {
			k = iterator.next();
			v = data.get(k);
			if(v == null) continue;
			try {
				ret += URLEncoder.encode(k, "utf8") + "=" + URLEncoder.encode(v, "utf8");
			} catch (Exception e) {
				logger.error("err:"+k,e);
				continue;
			}
			ret += "&";
		}
		return ret.substring(0, ret.length() - 1);
	}
	
	
	public static String sendPost(String url, Map<String, String> params){
		return sendPost(url, params, 5000);
	}
	
	
}
