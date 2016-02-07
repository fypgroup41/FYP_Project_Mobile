/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test.test;

import android.util.Log;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.codec.CharEncoding;

/**
 *
 * @author test
 */
public class Http_GetPost {

    public String getWebpage_output() {
        return webpage_output;
    }

    public void setWebpage_output(String webpage_output) {
        this.webpage_output = webpage_output;
    }

    String webpage_output;

    public void GET(String url) throws IOException {

        String result = "";
        URL url2 = new URL(url);
        HttpURLConnection urlConnection = (HttpURLConnection) url2.openConnection();
        try {

            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            result = convertInputStreamToString(in);
            webpage_output = result;

        } catch (Exception e) {
            Log.d("InputStream", e.getLocalizedMessage());
        } finally {
            urlConnection.disconnect();
        }
    }

    public void POST(String url) {
        HttpURLConnection conn = null;
        try {
            URL mURL = new URL(url);
            conn = (HttpURLConnection) mURL.openConnection();
            conn.setRequestMethod("POST");
            conn.setReadTimeout(5000);
            conn.setConnectTimeout(10000);
            conn.setDoOutput(true);

            String data = "id=2";
            OutputStream out = conn.getOutputStream();
            out.write(data.getBytes());
            out.flush();
            out.close();

            int responseCode = conn.getResponseCode();
            if (responseCode == 200) {

                InputStream is = conn.getInputStream();
                String result = convertInputStreamToString(is);
                webpage_output = result;
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                conn.disconnect();// 关闭连接
            }
        }

    }

    //AsyncTask Related Function
    private String convertInputStreamToString(InputStream inputStream)
            throws IOException {
        BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(inputStream));
        String line = "";
        String result = "";
        while ((line = bufferedReader.readLine()) != null) {
            result += line;
        }

        inputStream.close();
        return result;

    }

    public String processData(String data) {
        char[] temp = data.toCharArray();
        data = "";
        for (int i = 0; i < temp.length; i++) {
            if (temp[i] == ' ') {
                data += "%20";
            } else {
                data += temp[i];
            }
        }
        return data;
    }
}
