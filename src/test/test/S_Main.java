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
public class S_Main extends AsyncTask_Type {

    private MainActivity activity;

    public MainActivity getActivity() {
        return activity;
    }

    public void setActivity(MainActivity activity) {
        this.activity = activity;
    }

    public S_Main(MainActivity activity, String http_method) {
        super(http_method);
        this.activity = activity;
    }

    public void doWay(String url) {
    }

    public void executeAsyncTask() {
    }

    public void showRecord(Integer val) {

    }
}
