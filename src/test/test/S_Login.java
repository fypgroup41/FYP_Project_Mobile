package test.test;

import android.os.AsyncTask;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author test
 */
public class S_Login extends AsyncTask_Type {

    public MainActivity getActivity() {
        return activity;
    }

    public void setActivity(MainActivity activity) {
        this.activity = activity;
    }

    public String getWebpage_output() {
        return webpage_output;
    }

    public void setWebpage_output(String webpage_output) {
        this.webpage_output = webpage_output;
    }

    public ArrayAdapter getAryAdapter_list() {
        return aryAdapter_list;
    }

    public void setAryAdapter_list(ArrayAdapter aryAdapter_list) {
        this.aryAdapter_list = aryAdapter_list;
    }

    public GridView getGv() {
        return gv;
    }

    public void setGv(GridView gv) {
        this.gv = gv;
    }

    public TextView getTvOutput() {
        return tvOutput;
    }

    public void setTvOutput(TextView tvOutput) {
        this.tvOutput = tvOutput;
    }

    private MainActivity activity;
    private String webpage_output;
    private ArrayAdapter aryAdapter_list;
    private GridView gv;
    private TextView tvOutput;
    private Http_AsyncTask task = null;
    private boolean uiCheck = false;
    private String url;

    public Http_AsyncTask getTask() {
        return task;
    }

    public void setTask(Http_AsyncTask task) {
        this.task = task;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public S_Login(String url, MainActivity activity, String webpage_output, ArrayAdapter aryAdapter_list, GridView gv, TextView tvOutput, String http_method) {
        super(http_method);
        this.url=url;
        this.activity = activity;
        this.webpage_output = webpage_output;
        this.aryAdapter_list = aryAdapter_list;
        this.gv = gv;
        this.tvOutput = tvOutput;
        uiCheck = true;
    }

    public void executeAsyncTask() {
        if (url != null && uiCheck) {
            try {
                if (task == null || task.getStatus().equals(AsyncTask.Status.FINISHED)) {
                    task = new Http_AsyncTask(this);
                    task.execute(url);
                }
            } catch (Exception ex) {
                tvOutput.setText(ex.getMessage());
            }
        }
    }

    public void doWay(String url) {
        JSONArray array;
        List<String> list_output = new ArrayList<String>();
        String output = "";
        try {
            array = new JSONArray(url);
            for (int n = 0; n < array.length(); n++) {
                for (int m = 0; m < 3; m++) {
                    output += " " + array.getJSONArray(n).get(m).toString() + "\n ";
                    list_output.add(array.getJSONArray(n).get(m).toString());
                }
                output += "\n";
            }
            String[] a = new String[list_output.size()];
            int i = 0;
            for (String number : a) {
                a[i] = number;
                i += 1;
            }
            aryAdapter_list = new ArrayAdapter(activity, android.R.layout.simple_list_item_1, list_output);

            gv.setAdapter(aryAdapter_list);
            tvOutput.setText(output);

        } catch (JSONException ex) {
            tvOutput.setText(ex.getMessage());
        }
    }

}
