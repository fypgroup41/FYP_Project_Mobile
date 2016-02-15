package fyp.main;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebChromeClient;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;
import test.test.R;

public class MainActivity extends Activity {

    private Button btn_send;
    public String webpage_output = null;
    private TextView tvOutput;
    //private FetchPageTask task = null;
    private GridView gv;
    private ArrayAdapter aryAdapter_list;
    MediaController media_Controller;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        setObjectView();
        /*  VideoView videoView = (VideoView) findViewById(R.id.video);
         String src = "https://www.youtube.com/watch?v=HFT7ATLQQx8";
         videoView.setVideoURI(Uri.parse(src));
         media_Controller = new MediaController(this);
         videoView.setMediaController(media_Controller);
         videoView.requestFocus();
         videoView.start();

        
         videoView.requestFocus();
         videoView.start();*/
        /*
         startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.youtube.com/watch?v=cxLG2wtE7TM")));
         */
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
