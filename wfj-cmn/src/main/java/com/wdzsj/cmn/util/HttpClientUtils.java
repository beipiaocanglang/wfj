package com.wdzsj.cmn.util;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.security.KeyStore;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.NameValuePair;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CookieStore;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.config.SocketConfig;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.ssl.TrustStrategy;
import org.apache.http.util.EntityUtils;

/**
 * Copyright (C) @2016 Webank Group Holding Limited
 */
public class HttpClientUtils {

	public static final String ERROR = "ERROR";
	/**
	 * 创建带证书httpClient链接
	 * @param keyStore
	 * @param keyStorePassword
	 * @param trustStoreFile
	 * @param connMaxTotal
	 * @param connDefaultMaxPerRoute
	 * @param validateInactivityMillSeconds
	 * @param connEvictIdleConnectionsTimeoutMillSeconds
	 * @param proxyHost
	 * @param proxyPort
	 * @param proxyUsername
	 * @param proxyPassword
	 * @return
	 */
    public static CloseableHttpClient createHttpsClientWithCert(KeyStore keyStore, String keyStorePassword, KeyStore trustStoreFile,
                                                               int connMaxTotal,
                                                               int connDefaultMaxPerRoute,
                                                               int validateInactivityMillSeconds,
                                                               int connEvictIdleConnectionsTimeoutMillSeconds,
                                                               String proxyHost,
                                                               int proxyPort,
                                                               String proxyUsername,
                                                               String proxyPassword) {
        SSLContext sslcontext = null;
        try {
            sslcontext = SSLContexts.custom()
                    .loadKeyMaterial(keyStore, keyStorePassword.toCharArray())
                    .loadTrustMaterial(trustStoreFile, (TrustStrategy) new TrustSelfSignedStrategy()).build();
        } catch (Exception e) {
            throw new RuntimeException("key store fail", e);
        }

        // Create all-trusting host name verifier
        HostnameVerifier allHostsValid = new HostnameVerifier() {
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        };

        // Allow TLSv1 protocol only
        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
                sslcontext,
                new String[]{"TLSv1"},
                null,
                SSLConnectionSocketFactory.getDefaultHostnameVerifier());
//                allHostsValid);

        // Create a registry of custom connection socket factories for supported
        // protocol schemes.
        Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
                .register("https", sslsf)
                .register("http", PlainConnectionSocketFactory.INSTANCE)
                .build();


        // Create a connection manager with custom configuration.
        PoolingHttpClientConnectionManager connManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);

        // Create socket configuration
        SocketConfig socketConfig = SocketConfig.custom().setTcpNoDelay(true).build();// 小数据网络包
        // Configure the connection manager to use socket configuration either
        // by default or for a specific host.
        connManager.setDefaultSocketConfig(socketConfig);
        // Validate connections after 1 sec of inactivity
        connManager.setValidateAfterInactivity(validateInactivityMillSeconds);


        // Configure total max or per route limits for persistent connections
        // that can be kept in the pool or leased by the connection manager.
        connManager.setMaxTotal(connMaxTotal);
        connManager.setDefaultMaxPerRoute(connDefaultMaxPerRoute);

        // Use custom cookie store if necessary.
        CookieStore cookieStore = new BasicCookieStore();
        // Use custom credentials provider if necessary.
        //CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        // Create global request configuration
        RequestConfig defaultRequestConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.DEFAULT)
                .setExpectContinueEnabled(true)
                .build();

        HttpHost proxy = null;
        if (StringUtils.isNotEmpty(proxyHost)) {
            proxy = new HttpHost(proxyHost, proxyPort, "http");
        }
        CredentialsProvider credsProvider = new BasicCredentialsProvider();
        if (StringUtils.isNotEmpty(proxyUsername) && StringUtils.isNotEmpty(proxyPassword)) {
            credsProvider.setCredentials(
                    new AuthScope(proxyHost, proxyPort),
                    new UsernamePasswordCredentials(proxyUsername, proxyPassword));
        }

        // Create an HttpClient with the given custom dependencies and
        // configuration.
        CloseableHttpClient httpclient;
        if (proxy == null) {
            httpclient = HttpClients.custom().setConnectionManager(connManager)
                    .setDefaultCookieStore(cookieStore)
                    .setDefaultRequestConfig(defaultRequestConfig).evictExpiredConnections()
                    .evictIdleConnections(connEvictIdleConnectionsTimeoutMillSeconds, TimeUnit.MILLISECONDS)
                    .setSSLSocketFactory(sslsf).build();
        } else {
            httpclient = HttpClients.custom().setConnectionManager(connManager)
                    .setProxy(proxy)
                    .setDefaultCredentialsProvider(credsProvider)
                    .setDefaultCookieStore(cookieStore)
                    .setDefaultRequestConfig(defaultRequestConfig).evictExpiredConnections()
                    .evictIdleConnections(connEvictIdleConnectionsTimeoutMillSeconds, TimeUnit.MILLISECONDS)
                    .setSSLSocketFactory(sslsf).build();
        }
        return httpclient;
    }
    
    
    /**
     * 构建默认httpClient
     * @return
     */
    public static CloseableHttpClient createDefaultHttpClient() {
    	return HttpClients.createDefault();
    }
    
    /**
     * GET请求
     * @param httpClient
     * @param url
     * @param params
     * @return
     */
    public static String doGet(CloseableHttpClient httpClient,String url,Map<String,Object> params) {
    	if(StringUtils.isNotBlank(url) && null != httpClient){
    		CloseableHttpResponse response = null;
    		HttpGet httpGet = null;
    		try {
    			String apiUrl = url;  
        		StringBuffer param = new StringBuffer();  
        		int i = 0;
        		if(null != params && params.size() > 0){
        			for (String key : params.keySet()) {  
        				if (i == 0 && apiUrl.indexOf("?") == -1)  
        					param.append("?");  
        				else{
        					if(i == 0){
        						String paramSub = apiUrl.substring(apiUrl.indexOf("?")+1,apiUrl.length());
        						if(null != paramSub && paramSub.trim().indexOf("=") != -1 && !apiUrl.trim().endsWith("&")){
        							param.append("&");
        						}
        					}else param.append("&");
        				}
        				param.append(key).append("=").append((null == params.get(key))?"":URLEncoder.encode(params.get(key).toString(), "utf-8"));  
        				i++;  
        			}  
        			apiUrl += param; 
        		}
        		httpGet = new HttpGet(apiUrl);
        		//httpClient = createHttpClient();
        		response = httpClient.execute(httpGet);
        		int statusCode = response.getStatusLine().getStatusCode();
                String responseEntity = EntityUtils.toString(response.getEntity(),"UTF-8");
        		System.err.println("doGet("+apiUrl+") status code is:"+statusCode);
        		return responseEntity;
			} catch (Exception e) {
				e.printStackTrace();
				return ERROR;
			} finally {
				try {
					if(null != httpGet) httpGet.abort();
					if(null != response) response.close();
					if(null != httpClient) httpClient.close();
				} catch (Exception e2) {}
			}
    	}
    	return ERROR;
    }
    
    /**
     * POST请求
     * @param httpClient 
     * @param url
     * @param params
     * @return
     */
    public static String doPost(CloseableHttpClient httpClient,String url,Map<String,Object> params) {
    	//CloseableHttpClient httpClient = createHttpClient();
    	if(null != httpClient && StringUtils.isNotBlank(url)){
    		String httpStr = null;  
            HttpPost httpPost = new HttpPost(url);  
            CloseableHttpResponse response = null;  
            try {  
                List<NameValuePair> pairList = null;
                if(null != params && params.size() > 0){
                	pairList = new ArrayList<NameValuePair>(params.size());  
                	for (Map.Entry<String, Object> entry : params.entrySet()) {  
                		NameValuePair pair = new BasicNameValuePair(entry.getKey(),entry.getValue().toString());  
                		pairList.add(pair);
                	}
                }
                httpPost.setEntity(new UrlEncodedFormEntity(pairList, Charset.forName("UTF-8")));
                response = httpClient.execute(httpPost);
                HttpEntity entity = response.getEntity();
                httpStr = EntityUtils.toString(entity, "UTF-8");
            } catch (IOException e) {  
                e.printStackTrace();
                return ERROR;
            } finally {  
                try {  
                	if (response != null) EntityUtils.consume(response.getEntity());  
                    httpClient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return httpStr;
    	}
        return ERROR;
    }
    
    /*public static void main(String[] args) {
    	try {
    		Map<String,Object> param = new LinkedHashMap<String,Object>();
    		param.put("key","你好123");
    		String body = doPost(createDefaultHttpClient(),"http://wfj.com/trnsm/getSecurityVal",param);
    		System.out.println(body);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/
    
    
}
