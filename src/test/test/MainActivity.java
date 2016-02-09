package test.test;

import android.app.Activity;
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

public class MainActivity extends Activity {

    private Button btn_send;
    public String webpage_output = null;
    private TextView tvOutput;
    //private FetchPageTask task = null;
    private GridView gv;
    private ArrayAdapter aryAdapter_list;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        setObjectView();
        btn_send.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                AsyncTask_Type login = new S_Login("http://vbacdu.ddns.net:8080/WBS/activity_json?", MainActivity.this, webpage_output, aryAdapter_list, gv, tvOutput, "post");
                login.executeAsyncTask();
            }
        });
    }

    public void setObjectView() {
        btn_send = (Button) findViewById(R.id.btn_send);
        tvOutput = (TextView) findViewById(R.id.tvOutput);
        tvOutput.setMovementMethod(ScrollingMovementMethod.getInstance());
        gv = (GridView) findViewById(R.id.gridViewObj);
        gv.setNumColumns(3);
        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View view, int position, long id) {
                tvOutput.setText(parent.getItemAtPosition(position).toString() + " : " + position);
            }
        });
    }

}
