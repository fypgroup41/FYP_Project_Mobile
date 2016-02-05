package test.test;

import android.app.Activity;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStream;
import java.lang.*;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.http.HttpResponse;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import org.apache.commons.codec.CharEncoding;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends Activity {

    private Button btn_send;
    public String abcd = null;
    private TextView tvOutput;
    FetchPageTask task = null;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        setObjectView();
        btn_send.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {

                try {

                    if (task == null
                            || task.getStatus().equals(AsyncTask.Status.FINISHED)) {
                        task = new FetchPageTask();
                        task.execute("http://maps.googleapis.com/maps/api/geocode/json?address=MongKok&sensor=false");

                    }

                } catch (Exception ex) {
                    tvOutput.setText(ex.getMessage());
                }
            }
        });
    }

    public void setObjectView() {
        btn_send = (Button) findViewById(R.id.btn_send);
        tvOutput = (TextView) findViewById(R.id.tvOutput);
        tvOutput.setMovementMethod(ScrollingMovementMethod.getInstance());
          String[] vData = null;
        // 陣列值
        vData = new String[]{"a", "b", "c", "d", "e", "f", "g", "h", "i"};

        // 陣列接收器
        ArrayAdapter aas = new ArrayAdapter(this, android.R.layout.simple_list_item_1, vData
        );

        // 取得 GridView 物件
        GridView gv = (GridView) findViewById(R.id.gridViewObj);

        // 設定 GridView 的欄位數
        gv.setNumColumns(4);

        // 設定 GridView 的接收器
        gv.setAdapter(aas);

        // 設定 GridView 選擇時的觸發 Trigger
        gv.setOnItemClickListener(new GridView.OnItemClickListener() {
            public void onItemClick(AdapterView arg0, View arg1, int arg2, long arg3) {
                // TODO Auto-generated method stub
                
            }
        });
    }

    public void createDB() {

    }

    private class FetchPageTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... urls) {
            String a = GET(urls[0]);

            return null;
        }

        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(String result) {
            //tvOutput.setText(abcd);
            JSONArray array;
            String output = "";

            try {
                array = new JSONArray(abcd);
                for (int n = 0; n < array.length(); n++) {
                    for (int m = 0; m < array.getJSONArray(n).length(); m++) {
                        output += " " + array.getJSONArray(n).get(m).toString() + "\n ";
                    }
                    output += "\n";
                    output += "\n";
                    output += "\n";
                }
                tvOutput.setText(output);
            } catch (JSONException ex) {
                tvOutput.setText(ex.getMessage());
            }

        }

        private String GET(String url) {
            InputStream inputStream = null;
            String result = "";
            /*     try {

                // create HttpClient
                HttpClient httpclient = new DefaultHttpClient();

                // make GET request to the given URL
                HttpResponse httpResponse = httpclient
                        .execute(new HttpGet(url));

                // receive response as inputStream
                inputStream = httpResponse.getEntity().getContent();

                // convert inputstream to string
                if (inputStream != null) {
                    result = convertInputStreamToString(inputStream);
                } else {
                    result = "Did not work!";
                }

            } catch (Exception e) {
                Log.d("InputStream", e.getLocalizedMessage());
            }*/

            String uriAPI = "http://vbacdu.ddns.net:8080/FYP_Project_Staff/activity_json";

            HttpPost httpRequest = new HttpPost(uriAPI);

            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("id", "1"));
            try {
                //发出HTTP request  
                httpRequest.setEntity(new UrlEncodedFormEntity(params, CharEncoding.UTF_8));
                HttpClient httpclient = new DefaultHttpClient();
                //tvOutput.setText(httpRequest.getURI(). );
                //HttpResponse response = httpclient.execute(httppost);
                //取得HTTP response  
                HttpResponse httpResponse = httpclient.execute(httpRequest);
                abcd = httpResponse.getStatusLine().getStatusCode() + "";
                //若状态码为200 ok  
                if (httpResponse.getStatusLine().getStatusCode() == 200) {
                    //取出回应字串     
                    abcd = EntityUtils.toString(httpResponse.getEntity());

                } else {
                    Log.e("n", "b");
                }
            } catch (ClientProtocolException e) {

                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {

                e.printStackTrace();

            } catch (Exception e) {

            }

            return null;
        }

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

    }

}
