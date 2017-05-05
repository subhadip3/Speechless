package app.num.http;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.net.wifi.WifiManager;
import android.os.Handler;
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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.StringTokenizer;
import java.util.TimeZone;

import javax.net.ssl.HttpsURLConnection;

public class Student extends Activity {
    private ProgressDialog progress;
    public String sid;
    public String trueSid;
    public String startTime;
    public String keyword;
    public int bid;
    public int count;
    public String keyword1[]=new String[1000];
    public int bid1[]=new int[1000] ;
    public int flag;
    public int n,m,T;
    //LinearLayout ll;

    LinearLayout ll,ll1;
    public void turnOffWifi()
    {
        Log.d("hola","holat");
        WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        //wifiManager.setWifiEnabled(true);
        wifiManager.setWifiEnabled(false);
    }
    public void turnOnWifi()
    {
        Log.d("hola","holat");
        WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        wifiManager.setWifiEnabled(true);
        //wifiManager.setWifiEnabled(false);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student);
        ll= (LinearLayout) findViewById(R.id.ll);
        trueSid = getIntent().getStringExtra("sid");
        startTime=getIntent().getStringExtra("startTime");
        n= Integer.parseInt(getIntent().getStringExtra("n"));
        m=Integer.parseInt(getIntent().getStringExtra("m"));
        T=Integer.parseInt(getIntent().getStringExtra("T"))*60*1000;

        Log.d("trueSid",trueSid);
        Log.d("n",Integer.toString(n));
        Log.d("m",Integer.toString(m));
        Log.d("T",Integer.toString(T));

        //String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());
        /*Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT+5:30"));
        Date currentLocalTime = cal.getTime();
        DateFormat date = new SimpleDateFormat("HH:mm a");
// you can get seconds by adding  "...:ss" to it
        date.setTimeZone(TimeZone.getTimeZone("GMT+5:30"));

        String localTime = date.format(currentLocalTime);
        Log.d("localtime",localTime);*/
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
        int c=0;
        int interval=0;
        Calendar cal1 = Calendar.getInstance();
        int seconds = cal1.get(Calendar.SECOND);
        int minutes = cal1.get(Calendar.MINUTE);
        int hours = cal1.get(Calendar.HOUR_OF_DAY);

        Log.d("cal1", Integer.toString(seconds));
        Log.d("cal1", Integer.toString(minutes));
        Log.d("cal1", Integer.toString(hours));


        Log.d("starttime",startTime);

        int hrs=Integer.parseInt(startTime.split(":")[0]);
        int mins=Integer.parseInt(startTime.split(":")[1]);
        //int secs=Integer.parseInt(startTime.split(":")[2]);

        int studentId=Integer.parseInt(trueSid);
        Log.d("studentId",Integer.toString(studentId));
        /*int n=4;
        int m=2;
        int T=1000*60*6;*/
        int noOfDoc=2;
        int diff=((hrs-hours)*3600+(mins-minutes)*60)*1000;
        int ct=T/noOfDoc -(60*1000*n)/m; ///3min-2min
///////////////////////////////////////////////////////////////////////////////
        //while(c<3)
        //{
        //getstuff
            count=0;

            Handler handler0 = new Handler();
            handler0.postDelayed(new Runnable()
            {
                public void run() {
                    // Actions to do after 10 seconds
                    turnOnWifi();
                }


            }, diff+interval+1000+(studentId%m)*30000);


            Handler handler1 = new Handler();
            handler1.postDelayed(new Runnable() {
                public void run() {
                    // Actions to do after 10 seconds
                    new GetContent().execute();
                    //turnOffWifi();
                }


            }, diff+interval+1000+15000+(studentId%m)*30000);


            Handler handler2 = new Handler();
            handler2.postDelayed(new Runnable() {
            public void run() {
                // Actions to do after 10 seconds
                turnOffWifi();
                //turnOffWifi();
                }


        }, diff+interval+1000+15000+14000+(studentId%m)*30000);

        //voting

            Handler handler3 = new Handler();
            handler3.postDelayed(new Runnable() {
            public void run() {
                turnOnWifi();

            }
            }, diff+interval+(n/m)*30000+(studentId%m)*30000+ct);

         //send
            Handler handler4 = new Handler();
            handler4.postDelayed(new Runnable()
            {
            public void run()
            {
                //turnOnWifi();
                // Actions to do after 10 seconds
                for(int i=0;i<count;i++)
                {
                    Log.d("bid",Integer.toString(bid1[i]));
                    Log.d("keyword",keyword1[i]);

                    bid=bid1[i];

                    keyword=keyword1[i];
                    new SendPostRequest1().execute(trueSid,keyword1[i],Integer.toString(bid1[i]));
                }
                //turnOffWifi();
            }
            }, diff+interval+(n/m)*30000+(studentId%m)*30000+ct+15000);

            Handler handler5 = new Handler();
            handler5.postDelayed(new Runnable() {
            public void run() {

                turnOffWifi();
                count=0;
                //interval=2*(n/m)+ct;

            }
            }, diff+interval+(n/m)*30000+(studentId%m)*30000+ct+15000+15000);


///////////////////////////////////////////////////////////////
        //count=0;

        //Handler handler0 = new Handler();
        handler0.postDelayed(new Runnable()
        {
            public void run() {
                // Actions to do after 10 seconds
                turnOnWifi();
            }


        }, diff+interval+1000+(studentId%m)*30000+(2*(n/m)*30000+ct));


       // Handler handler1 = new Handler();
        handler1.postDelayed(new Runnable() {
            public void run() {
                // Actions to do after 10 seconds
                new GetContent().execute();
                //turnOffWifi();
            }


        }, diff+interval+1000+15000+(studentId%m)*30000+(2*(n/m)*30000+ct));


        //Handler handler2 = new Handler();
        handler2.postDelayed(new Runnable() {
            public void run() {
                // Actions to do after 10 seconds
                turnOffWifi();
                //turnOffWifi();
            }


        }, diff+interval+1000+15000+14000+(studentId%m)*30000+(2*(n/m)*30000+ct));

        //voting

        //Handler handler3 = new Handler();
        handler3.postDelayed(new Runnable() {
            public void run() {
                turnOnWifi();

            }
        }, diff+interval+(n/m)*30000+(studentId%m)*30000+ct+(2*(n/m)*30000+ct));

        //send
        //Handler handler4 = new Handler();
        handler4.postDelayed(new Runnable()
        {
            public void run()
            {
                //turnOnWifi();
                // Actions to do after 10 seconds
                for(int i=0;i<count;i++)
                {
                    Log.d("bid",Integer.toString(bid1[i]));
                    Log.d("keyword",keyword1[i]);

                    bid=bid1[i];

                    keyword=keyword1[i];
                    new SendPostRequest1().execute(trueSid,keyword1[i],Integer.toString(bid1[i]));
                }
                //turnOffWifi();
            }
        }, diff+interval+(n/m)*30000+(studentId%m)*30000+ct+15000+(2*(n/m)*30000+ct));

        //Handler handler5 = new Handler();
        handler5.postDelayed(new Runnable() {
            public void run() {

                turnOffWifi();
                count=0;
                //interval=2*(n/m)+ct;

            }
        }, diff+interval+(n/m)*30000+(studentId%m)*30000+ct+15000+15000+(2*(n/m)*30000+ct));


///////////////////////////////////////////////////////////////

/*
        handler0.postDelayed(new Runnable()
        {
            public void run() {
                // Actions to do after 10 seconds
                turnOnWifi();
            }


        }, ((hrs-hours)*3600+(mins-minutes)*60)*1000+interval+120000+120000+5000);


        //Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                // Actions to do after 10 seconds
                new GetContent().execute();
                turnOffWifi();
            }


        }, ((hrs-hours)*3600+(mins-minutes)*60)*1000+interval+120000+120000+5000+5000);

        //Handler handler1 = new Handler();
        handler1.postDelayed(new Runnable() {
            public void run() {
                turnOnWifi();

            }
        }, ((hrs-hours)*3600+(mins-minutes)*60)*1000+interval+120000+120000+5000+5000+60000);


        //Handler handler2 = new Handler();
        handler2.postDelayed(new Runnable()
        {
            public void run()
            {
                //turnOnWifi();
                // Actions to do after 10 seconds
                for(int i=0;i<count;i++)
                {
                    Log.d("bid",Integer.toString(bid1[i]));
                    Log.d("keyword",keyword1[i]);

                    bid=bid1[i];

                    keyword=keyword1[i];
                    new SendPostRequest1().execute(trueSid,keyword1[i],Integer.toString(bid1[i]));
                }
                //turnOffWifi();
            }
        }, ((hrs-hours)*3600+(mins-minutes)*60)*1000+interval+120000+120000+5000+5000+60000+5000);

        //Handler handler3 = new Handler();
        handler3.postDelayed(new Runnable() {
            public void run() {

                turnOffWifi();
                count=0;
            }
        }, ((hrs-hours)*3600+(mins-minutes)*60)*1000+interval+120000+120000+5000+5000+60000+5000+10000);

*/

        //  c=c+1;
           // interval=interval+120000;//2 min interval;
        //}

    }

    public class GetContent extends AsyncTask<String, Void, String> {

        //public int flag;
        protected void onPreExecute()
        {}

        protected String doInBackground(String... arg0) {

            try{

                URL url = new URL("http://172.24.137.62/get-db.php");



                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(15000 /* milliseconds */);
                conn.setConnectTimeout(15000 /* milliseconds */);
                conn.setRequestMethod("GET");
                conn.setDoInput(true);
                conn.setDoOutput(true);

                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuilder stringBuilder = new StringBuilder();

                String line = null;
                String line1="";
                int cnt=0;
                while ((line = reader.readLine()) != null)
                {
                    Log.d("hello",line);
                    if(cnt==0)
                    {
                        line1=line;
                        cnt=cnt+1;
                    }

                    else
                    line1=line1+'/'+line;
                }
                return line1;

            }
            catch(Exception e){
                return new String("Exception: " + e.getMessage());
            }
        }

        @Override
        protected void onPostExecute(String result)
        {
            Toast.makeText(getApplicationContext(), result,
                    Toast.LENGTH_LONG).show();

            String[] parts = result.split("/");
            generateButtonDynamically(parts);
            for (String part : parts)
            {


                Log.d("hey",part);
            }



        }
    }

    public void generateButtonDynamically(final String[] parts)
    {

        ll1=(LinearLayout)findViewById(R.id.ll1);
        for(int i=0;i<parts.length-1;i=i+1)
        {


            final TextView tv1=new TextView(this);
            //tv.setTextSize(30);
            tv1.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
                    LayoutParams.WRAP_CONTENT));
            //tv.setText(parts[i]);
            ll1.addView(tv1);

            final TextView tv=new TextView(this);
            tv.setTextSize(25);
            tv.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
                    LayoutParams.WRAP_CONTENT));
            tv.setText(parts[i]);
            ll1.addView(tv);

            ll = new LinearLayout(this);
            ll.setOrientation(LinearLayout.HORIZONTAL);

            /*LinearLayout ll5 = new LinearLayout(this);
            ll5.setOrientation(LinearLayout.VERTICAL);
            ll5.setId(i);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            layoutParams.setMargins(25, 20, 25, 10);
            EditText view = new EditText(this);
            view.setText("HHH");
            ll5.addView(view, layoutParams);*/



            flag=0;

            final Button btn1 = new Button(this);
            btn1.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
                    LayoutParams.WRAP_CONTENT));
            //btn.setGravity(Gravity.CENTER_HORIZONTAL);
            btn1.setId(i*3);
            btn1.setText("Got it" );
            ll.addView(btn1);
            btn1.setOnClickListener(new OnClickListener() {
                public void onClick(View v) {

                    int temp=btn1.getId();
                    String kk;
                    int bb;
                    kk=tv.getText().toString();
                    if(btn1.getId()%3==0)
                        bb=3;
                    else if(btn1.getId()%3==1)
                        bb=4;
                    else
                        bb=5;

                    keyword1[count]=kk;
                    bid1[count]=bb;
                    count++;
                    flag=1;
                    //btn1.setBackgroundColor(Color.RED);
                    //ll.setVisibility(View.GONE);
                    //btn3.setVisibility(View.GONE);
                    //new SendPostRequest1().execute();

                    // Perform action on click
                }
            });



            final Button btn2 = new Button(this);
            btn2.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
                    LayoutParams.WRAP_CONTENT));
            //btn.setGravity(Gravity.CENTER_HORIZONTAL);
            btn2.setId(i*3+1);
            btn2.setText("Ummm..." );
            ll.addView(btn2);
            btn2.setOnClickListener(new OnClickListener() {
                public void onClick(View v) {
                    int temp=btn2.getId();
                    int bb;
                    String kk=tv.getText().toString();
                    if(btn2.getId()%3==0)
                        bb=3;
                    else if(btn2.getId()%3==1)
                        bb=4;
                    else
                        bb=5;

                    keyword1[count]=kk;
                    bid1[count]=bb;
                    count++;
                    flag=1;
                    //btn2.setBackgroundColor(Color.RED);
                    //ll.setVisibility(View.INVISIBLE);
                    //new SendPostRequest1().execute();

                    // Perform action on click
                }
            });


            final Button btn3 = new Button(this);
            btn3.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
                    LayoutParams.WRAP_CONTENT));
            //btn.setGravity(Gravity.CENTER_HORIZONTAL);
            btn3.setId(i*3 +2);
            btn3.setText("No Clue" );
            ll.addView(btn3);
            btn3.setOnClickListener(new OnClickListener() {
                public void onClick(View v) {
                    int temp=btn3.getId();
                    //EditText editTextName = (EditText) findViewById(R.id.editTextName);
                    //sid=editTextName.getText().toString();
                    //Log.d("ss3",sid);
                    int bb;
                    String kk=tv.getText().toString();
                    if(btn3.getId()%3==0)
                        bb=3;
                    else if(btn3.getId()%3==1)
                        bb=4;
                    else
                        bb=5;

                    keyword1[count]=kk;
                    bid1[count]=bb;
                    count++;
                    flag=1;
                    //btn3.setBackgroundColor(Color.RED);
                    //ll.setVisibility(View.GONE);
                    //new SendPostRequest1().execute();

                    // Perform action on click
                }
            });


            ll1.addView(ll);

        }
    }


    public class SendPostRequest1 extends AsyncTask<String, Void, String> {

        protected void onPreExecute() {
        }

        protected String doInBackground(String... arg0) {
            //Log.e("params", name);
            //Log.e("params", add);

            try {

                URL url = new URL("http://172.24.137.62/insertSDRating.php"); // here is your URL path

                JSONObject postDataParams1 = new JSONObject();

                Log.d("receivedbid",arg0[1]);
                Log.d("receivedkeyword",arg0[0]);


                postDataParams1.put("sid", arg0[0]);
                postDataParams1.put("keyword", arg0[1]);
                postDataParams1.put("rating", arg0[2]);
                //postDataParams.put("add", add);


                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(15000 /* milliseconds */);
                conn.setConnectTimeout(15000 /* milliseconds */);
                conn.setRequestMethod("POST");
                conn.setDoInput(true);
                conn.setDoOutput(true);
                //Log.e("params", name);
                Log.d("hello","hi");

                OutputStream os = conn.getOutputStream();
                BufferedWriter writer = new BufferedWriter(
                        new OutputStreamWriter(os, "UTF-8"));
                writer.write(getPostDataString(postDataParams1));

                writer.flush();
                writer.close();
                os.close();

                //Log.e("params", name);
                int responseCode=conn.getResponseCode();

                Log.d("hello","hi");
                //int responseCode = conn.getResponseCode();
                Log.d("hello",Integer.toString(responseCode));
                Log.d("hello",Integer.toString(HttpURLConnection.HTTP_OK));
                if (responseCode == HttpsURLConnection.HTTP_OK) {

                    BufferedReader in = new BufferedReader(new
                            InputStreamReader(
                            conn.getInputStream()));

                    StringBuffer sb = new StringBuffer("");
                    String line = "";

                    while ((line = in.readLine()) != null) {

                        sb.append(line);
                        break;
                    }

                    in.close();
                    return sb.toString();

                } else {
                    return new String("false : " + responseCode);
                }
            } catch (Exception e) {
                return new String("Exception: " + e.getMessage());
            }

        }

        @Override
        protected void onPostExecute(String result) {
            Toast.makeText(getApplicationContext(), result,
                    Toast.LENGTH_LONG).show();
        }



    }
    public String getPostDataString(JSONObject params) throws Exception
    {

        StringBuilder result = new StringBuilder();
        boolean first = true;

        Iterator<String> itr = params.keys();

        while(itr.hasNext()){

            String key= itr.next();
            Object value = params.get(key);

            if (first)
                first = false;
            else
                result.append("&");

            result.append(URLEncoder.encode(key, "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(value.toString(), "UTF-8"));

        }
        return result.toString();
    }
}
