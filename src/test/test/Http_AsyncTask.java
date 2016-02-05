/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test.test;

import android.os.AsyncTask;

/**
 *
 * @author test
 */
class Http_AsyncTask extends AsyncTask<String, Void, String> {

    AsyncTask_Type shape;
    Http_GetPost http_method = new Http_GetPost();

    public Http_AsyncTask(AsyncTask_Type shape) {
        this.shape = shape;
    }

    //AsyncTask Process Function
    @Override
    protected String doInBackground(String... urls) {

        if (shape.getHttp_method().equals("page_get")) {
            http_method.GET(urls[0]);
        }
        if (shape.getHttp_method().equals("page_post")) {
            http_method.POST(urls[0]);
        }

        return null;
    }

    //AsyncTask Finish Function
    @Override
    protected void onPostExecute(String result) {
        shape.doWay(http_method.getWebpage_output());
    }

}
