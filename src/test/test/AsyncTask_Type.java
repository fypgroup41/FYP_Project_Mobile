/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test.test;

/**
 *
 * @author test
 */
public abstract class AsyncTask_Type {

    private String http_method;

    public String getHttp_method() {
        return http_method;
    }

    public void setHttp_method(String http_method) {
        this.http_method = http_method;
    }

    public AsyncTask_Type(String http_method) {
        this.http_method = http_method;
    }

    public abstract void doWay(String url);

    public abstract void executeAsyncTask();
}
