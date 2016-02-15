/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fyp.main;

import android.net.Uri;
import android.os.Environment;
import android.util.Log;
import upload.lib.UploadVideo;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
        int code = urlConnection.getResponseCode();
        try {
            if (code == 404) {
                webpage_output = null;
            } else {
                InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                result = convertInputStreamToString(in);
                webpage_output = result;

            }
        } catch (Exception e) {
            Log.d("InputStream", e.getLocalizedMessage());
        } finally {
            urlConnection.disconnect();
        }

    }

    public void POST(String url) throws IOException {
        HttpURLConnection urlConnection = null;
        try {
            URL url2 = new URL(url);
            urlConnection = (HttpURLConnection) url2.openConnection();
            urlConnection.setRequestMethod("POST");
            urlConnection.setReadTimeout(5000);
            urlConnection.setConnectTimeout(10000);
            urlConnection.setDoOutput(true);
            String data = "id=2";
            OutputStream out = urlConnection.getOutputStream();
            out.write(data.getBytes());
            out.flush();
            out.close();

            int responseCode = urlConnection.getResponseCode();

            if (responseCode == 200) {

                InputStream is = urlConnection.getInputStream();
                String result = convertInputStreamToString(is);
                webpage_output = result;

            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            urlConnection.disconnect();
        }

    }

    public void http_getPost_Entrance(String url, String way) {
        if (way.equals("get")) {
            try {

                this.GET(url);

            } catch (IOException ex) {
                Logger.getLogger(Http_AsyncTask.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (way.equals("post")) {
            try {

                this.POST(url);

            } catch (IOException ex) {
                Logger.getLogger(Http_AsyncTask.class.getName()).log(Level.SEVERE, null, ex);
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

    public void abcd() {
        UploadVideo a = new UploadVideo();

        int reponse = a.upLoad2Server(new File(Environment.getExternalStorageDirectory() + "/DCIM/Camera/a.mp4").toString());
    }

}
