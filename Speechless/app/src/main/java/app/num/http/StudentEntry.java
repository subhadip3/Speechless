package app.num.http;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.view.View;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.LinearLayout;
//import android.widget.GridLayout.LayoutParams;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.content.Context;

import android.widget.TextView;
import android.widget.Toast;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;

import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.StringTokenizer;

import javax.net.ssl.HttpsURLConnection;

public class StudentEntry extends Activity {
    private ProgressDialog progress;
    public String sid;
    public String startTime,n,m,T;
    public String did;
    public int bid;

    LinearLayout ll;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_entry);
        //ll= (LinearLayout) findViewById(R.id.ll);
        //Log.d("Hi","hello");
        //Toast(sid);


        /*for (int i = 0; i < 10; i++) {
            Button btn = new Button(this);
            btn.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
                    LayoutParams.WRAP_CONTENT));
            //btn.setGravity(Gravity.CENTER_HORIZONTAL);
            btn.setId(i);
            btn.setText("Button " + i);
            btn.setText("Button " + i);



            ll.addView(btn);

        }*/



    }
    public void enter(View view)
    {
        Intent intent = new Intent(this, Student.class);
        EditText et1 = (EditText) findViewById(R.id.editTextName1);//roll no.
        sid=et1.getText().toString();

        EditText et2 = (EditText) findViewById(R.id.editTextName2);//class start
        startTime=et2.getText().toString();

        EditText et3 = (EditText) findViewById(R.id.editTextName3);//number of students
        n=et3.getText().toString();

        EditText et4 = (EditText) findViewById(R.id.editTextName4);//class duration
        m=et4.getText().toString();

        EditText et5 = (EditText) findViewById(R.id.editTextName5);//no. of students per router
        T=et5.getText().toString();

        intent.putExtra("sid", sid);
        intent.putExtra("startTime", startTime);
        intent.putExtra("n", n);
        intent.putExtra("m", m);
        intent.putExtra("T", T);

        startActivity(intent);
    }


}
