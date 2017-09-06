package com.zhy.java.http;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class ClientFormLogin {
	public static void main(String[] args) throws Exception {
        BasicCookieStore cookieStore = new BasicCookieStore();
        CloseableHttpClient httpclient = HttpClients.custom().setDefaultCookieStore(cookieStore).build();
        try {
            HttpGet httpget = new HttpGet("http://www.variflight.com/");
            CloseableHttpResponse response1 = httpclient.execute(httpget);
            try {
                HttpEntity entity = response1.getEntity();

                System.out.println("Login form get: " + response1.getStatusLine());
                EntityUtils.consume(entity);

                System.out.println("Initial set of cookies:");
                List<Cookie> cookies = cookieStore.getCookies();
                if (cookies.isEmpty()) {
                    System.out.println("None");
                } else {
                    for (int i = 0; i < cookies.size(); i++) {
                        System.out.println("- " + cookies.get(i).toString());
                    }
                }
            } finally {
                response1.close();
            }

           /* HttpUriRequest login = RequestBuilder.post().setUri(new URI("https://someportal/"))
                    .addParameter("IDToken1", "username").addParameter("IDToken2", "password").build();
                    */
            String url ="http://www.variflight.com/flight/detail/productImg&s=bXVrOURLVU5GYnZIZjY0U3QwWUFrZVJ5UmxjPQ==&w=80&h=40&fontSize=20&fontColor=1e90ff&background=ffffff&charw=20?AE71649A58c77=";
            HttpGet get = new HttpGet(url);
            CloseableHttpResponse response2 = httpclient.execute(get);
            InputStream is = null;
            OutputStream os = null;
            HttpEntity entity = null;
            try {
                entity = response2.getEntity();

                System.out.println("Login form get: " + response2.getStatusLine());
               /* is = entity.getContent();
                os = new FileOutputStream("d:/111.png");
                byte[] b = new byte[1024];
                int len;
                while((len = is.read(b)) != -1){
                	System.out.println("len:" + len);
                	os.write(b, 0, len);
                }*/
                String result = EntityUtils.toString(entity, "utf-8");
                System.out.println(result);
            } finally {
            	//os.close();
            	EntityUtils.consume(entity);
                response2.close();
            }
            
        } finally {
            httpclient.close();
        }
        
    }
}
