package app.num.http;

/**
 * Created by subhadip on 2/4/17.
 */
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Environment;
import android.util.Base64;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
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
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

import javax.net.ssl.HttpsURLConnection;//package com.example.subhadip.myapplication;


import java.net.HttpURLConnection;
import java.net.URL;
import android.util.Log;
import android.os.AsyncTask;
import java.lang.*;

import java.net.URLEncoder;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;


import org.json.JSONObject;

import java.io.BufferedWriter;
import java.util.Iterator;
import java.io.File;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import static android.R.id.text1;


public class Teacher extends ListActivity {

    private EditText editTextName;
    private EditText editTextAdd;
    public String[] keyword=new String[500];
    public int keyLength;
    public String finalTxt;
    public String path;
    LinearLayout ll;
    LinearLayout ll1;
    //public String add;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.teacher);
        //editTextName = (EditText) findViewById(R.id.editTextName);
        Button b1=(Button) findViewById(R.id.choose);
        ll= (LinearLayout) findViewById(R.id.ll);
        ll1= (LinearLayout) findViewById(R.id.ll1);

        /*StringBuilder text = new StringBuilder();
        try {
            File sdcard = Environment.getExternalStorageDirectory();
            Log.d("hhh",Environment.getExternalStorageDirectory().getAbsolutePath());
            File file = new File(sdcard,"testFile.txt");


            if(file.exists())
            {

                Log.d("hhh", "hhh");
                BufferedReader br = new BufferedReader(new FileReader(file));
                Log.d("hhh", "hhh1");
                String line;
                finalTxt="He";
                //int cnt=0;
                while ((line = br.readLine()) != null)
                {
                    Log.d("hhh",line);

                    text.append(line);
                    text.append('\n');


                }
                br.close();
            }
            else
            {
                Log.d("jj2","jj");
            }

        }catch (IOException e) {
            e.printStackTrace();
        }*/

        /*TextView tv = (TextView)findViewById(R.id.amount);
        tv.setText(text.toString());*/ ////Set the text to text vie
        //editTextAdd = (EditText) findViewById(R.id.editTextAddress);
        //Log.d("hola","holataaa");
        //Log.d("Hi","hello");
        //new SendPostRequest().execute();
        //new SendPostRequest().execute();
        //new SendDisplayRequestNew().execute();


    }
    public void choose(View view)
    {

        path = "/storage/sdcard0/testFolder/";
        if (getIntent().hasExtra("path"))
        {
            path = getIntent().getStringExtra("path");
        }
        setTitle(path);

        // Read all files sorted into the values-array
        List values = new ArrayList();
        File dir = new File(path);
        if (!dir.canRead()) {
            setTitle(getTitle() + " (inaccessible)");
        }
        String[] list = dir.list();
        if (list != null) {
            for (String file : list) {
                if (!file.startsWith(".")) {
                    values.add(file);
                }
            }
        }
        Collections.sort(values);

        // Put the data into the list
        ArrayAdapter adapter = new ArrayAdapter(this,
                android.R.layout.simple_list_item_2, text1, values);
        setListAdapter(adapter);
    }
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        String filename = (String) getListAdapter().getItem(position);
        String fname=filename;
        if (path.endsWith(File.separator)) {
            filename = path + filename;
        } else {
            filename = path + File.separator + filename;
        }

        if (new File(filename).isDirectory()) {
            Intent intent = new Intent(this, Teacher.class);
            intent.putExtra("path", filename);
            startActivity(intent);
        } else {

            /*try {
                String parsedText="";
                PdfReader reader = new PdfReader(Environment.getExternalStorageDirectory()+'/'+filename);
                int n = reader.getNumberOfPages();
                for (int i = 0; i <n ; i++) {
                    parsedText   = parsedText+PdfTextExtractor.getTextFromPage(reader, i+1).trim()+"\n"; //Extracting the content from the different pages
                }
                System.out.println(parsedText);
                reader.close();
            } catch (Exception e) {
                System.out.println(e);
            }*/

            Toast.makeText(this, filename + " is not a directory", Toast.LENGTH_LONG).show();
            Log.d("hhh", "hhh");
            File sdcard = Environment.getExternalStorageDirectory();
            Log.d("hhh",Environment.getExternalStorageDirectory().getAbsolutePath());
            StringBuilder texti = new StringBuilder();
            try {
                Log.d("fname",fname);
                //Log.d("sdcard",sdcard);
                File file = new File(sdcard, fname);
                BufferedReader br = new BufferedReader(new FileReader(file));



                Log.d("hhh", "hhh1");

                String line;
                finalTxt = "He";
                //int cnt=0;

                while ((line = br.readLine()) != null) {
                    Log.d("hhh", line);

                    /*if(cnt==0)
                    {
                        finalTxt=line;
                        cnt=cnt+1;
                    }
                    else*/
                    texti.append(line);
                    texti.append('\n');
                    finalTxt = finalTxt + " " + line;

                }
                br.close();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            //TextView tv = (TextView)findViewById(R.id.amount);
            //tv.setText(texti.toString());
            new SendDisplayRequestNew().execute();

        }
    }
    public void keywordInsert(View view)
    {
        EditText et=(EditText)findViewById(R.id.et);
        String str=et.getText().toString();
        Log.d("string1",str);
        new SendPostRequest().execute(str);
    }
    public void insert(View view) {
       // keyword = editTextName.getText().toString();
        //add = editTextAdd.getText().toString();
        //Log.e("hello", keyword);
        //SendPostRequest spr=new SendPostRequest();
        /*Log.d("keyword[0]",keyword[0]);
        Log.d("keyword[1]",keyword[1]);*/
        new SendDeleteRequest().execute();
        for(int i=0;i<keyLength;i++)
        {

            new SendPostRequest().execute(keyword[i]);
        }
        //insertToDatabase(name, add);
    }

    class SendDeleteRequest extends AsyncTask<String, Void, String> {

        protected void onPreExecute()
        {
        }

        protected String doInBackground(String... arg0)
        {

            try {

                URL url = new URL("http://172.24.137.62/delete-db.php"); // here is your URL path
                //URL url = new URL("https://gateway.watsonplatform.net/natural-language-understanding/api/v1/analyze?version=2017-02-27&text=I%20still%20have%20a%20dream%2C%20a%20dream%20deeply%20rooted%20in%20the%20American%20dream%20%E2%80%93%20one%20day%20this%20nation%20will%20rise%20up%20and%20live%20up%20to%20its%20creed%2C%20%22We%20hold%20these%20truths%20to%20be%20self%20evident%3A%20that%20all%20men%20are%20created%20equal.&features=sentiment,keywords"); // here is your URL path

                JSONObject postDataParams = new JSONObject();
                postDataParams.put("keyword", "xxx");

                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(15000 /* milliseconds */);
                conn.setConnectTimeout(15000 /* milliseconds */);
                conn.setRequestMethod("POST");
                conn.setDoInput(true);
                conn.setDoOutput(true);
                //Log.e("params", name);
                Log.d("hello", "hi");

                OutputStream os = conn.getOutputStream();
                BufferedWriter writer = new BufferedWriter(
                        new OutputStreamWriter(os, "UTF-8"));
                writer.write(getPostDataString(postDataParams));

                writer.flush();
                writer.close();
                os.close();

                //Log.e("params", name);
                int responseCode = conn.getResponseCode();

                Log.d("hello", "hi");
                //int responseCode = conn.getResponseCode();
                Log.d("hello", Integer.toString(responseCode));
                Log.d("hello", Integer.toString(HttpURLConnection.HTTP_OK));
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
            //}
            //return "hhh";

        }


        @Override
        protected void onPostExecute(String result) {
            Toast.makeText(getApplicationContext(), result,
                    Toast.LENGTH_LONG).show();
        }



    }
    class SendPostRequest extends AsyncTask<String, Void, String> {

        protected void onPreExecute()
        {
        }

        protected String doInBackground(String... arg0)
        {
            //Log.e("params", name);
            //Log.e("params", add);
            //Log.d("k0",keyword[0]);
            //Log.d("k1",keyword[1]);
            //Log.d("k2",Integer.toString(keyLength));

           // for (int i = 0; i < 2; i++)
            //{
                try {

                    URL url = new URL("http://172.27.30.193/insert-db.php"); // here is your URL path
                    //URL url = new URL("https://gateway.watsonplatform.net/natural-language-understanding/api/v1/analyze?version=2017-02-27&text=I%20still%20have%20a%20dream%2C%20a%20dream%20deeply%20rooted%20in%20the%20American%20dream%20%E2%80%93%20one%20day%20this%20nation%20will%20rise%20up%20and%20live%20up%20to%20its%20creed%2C%20%22We%20hold%20these%20truths%20to%20be%20self%20evident%3A%20that%20all%20men%20are%20created%20equal.&features=sentiment,keywords"); // here is your URL path

                    JSONObject postDataParams = new JSONObject();
                    postDataParams.put("keyword", arg0[0]);
                    Log.d("soumik1", arg0[0]);
                    //postDataParams.put("add", add);
                    //Log.e("params", name);

                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setReadTimeout(15000 /* milliseconds */);
                    conn.setConnectTimeout(15000 /* milliseconds */);
                    conn.setRequestMethod("POST");
                    conn.setDoInput(true);
                    conn.setDoOutput(true);
                    //Log.e("params", name);
                    Log.d("hello", "hi");

                    OutputStream os = conn.getOutputStream();
                    BufferedWriter writer = new BufferedWriter(
                            new OutputStreamWriter(os, "UTF-8"));
                    writer.write(getPostDataString(postDataParams));

                    writer.flush();
                    writer.close();
                    os.close();

                    //Log.e("params", name);
                    int responseCode = conn.getResponseCode();

                    Log.d("hello", "hi");
                    //int responseCode = conn.getResponseCode();
                    Log.d("hello", Integer.toString(responseCode));
                    Log.d("hello", Integer.toString(HttpURLConnection.HTTP_OK));
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
            //}
            //return "hhh";

        }


        @Override
        protected void onPostExecute(String result) {
            Toast.makeText(getApplicationContext(), result,
                    Toast.LENGTH_LONG).show();
        }



    }
    public String getPostDataString(JSONObject params) throws Exception {

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





    public void display(View view) {
        //keyword = editTextName.getText().toString();
        //add = editTextAdd.getText().toString();
        //Log.e("hello", keyword);
        //SendPostRequest spr=new SendPostRequest();
        Log.d("hi","hello");
        new SendDisplayRequest().execute();
        //insertToDatabase(name, add);
    }


    public class SendDisplayRequest extends AsyncTask<String, Void, String> {

        protected void onPreExecute(){}

        protected String doInBackground(String... arg0) {

            try{

                URL url = new URL("http://172.24.137.62/getfinal-db.php");


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

                    if(cnt==0)
                    {
                        line1=line;
                        cnt=cnt+1;
                    }
                    else
                    {
                        line1=line1+'/'+line;
                    }
                    Log.d("hellomate",line) ;

                }
                return line1;

            }
            catch(Exception e)
            {
                return new String("Exception: " + e.getMessage());
            }
        }

        @Override
        protected void onPostExecute(String result) {
            Toast.makeText(getApplicationContext(), result,
                    Toast.LENGTH_LONG).show();

            String[] parts = result.split("/");
            generateViewDynamically(parts);
            StringBuilder txt1=new StringBuilder();
            for (String part : parts)
            {

                txt1.append(part);
                Log.d("hey",part);
            }

            /*TextView tv1 = (TextView)findViewById(R.id.textViewResult);
            tv1.setText(txt1.toString());*/

        }

    }



    public void generateViewDynamically(final String[] parts)
    {

        for(int i=0;i<parts.length;i=i+1)
        {

            final TextView tv = new TextView(this);
            tv.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));
            //btn.setGravity(Gravity.CENTER_HORIZONTAL);

            tv.setText(parts[i]);
            ll.addView(tv);




        }
    }
    public void generateViewDynamically1(final String[] parts)
    {

        for(int i=0;i<parts.length;i=i+1)
        {

            final TextView tv = new TextView(this);
            tv.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));
            //btn.setGravity(Gravity.CENTER_HORIZONTAL);

            tv.setText(parts[i]);
            ll1.addView(tv);




        }
    }

    public class SendDisplayRequestNew extends AsyncTask<String, Void, String> //extracts keywords and puts them in textview
    {

        protected void onPreExecute(){}

        protected String doInBackground(String... arg0) {
            Log.d("hola","holatii");
            try{
                //Log.d("hiff","there");

                finalTxt = finalTxt.replaceAll(" ", "%20");

                finalTxt="https:"+File.separator+File.separator+"gateway.watsonplatform.net"+File.separator+"natural-language-understanding"+File.separator+"api"+File.separator+"v1"+File.separator+"analyze?version=2017-02-27&text="+finalTxt+"&features=keywords";
                Log.d("finalTxt",finalTxt);
                //URL url = new URL("https://gateway.watsonplatform.net/natural-language-understanding/api/v1/analyze?version=2017-02-27&text=I%20still%20have%20a%20dream%2C%20a%20dream%20deeply%20rooted%20in%20the%20American%20dream%20%E2%80%93%20one%20day%20this%20nation%20will%20rise%20up%20and%20live%20up%20to%20its%20creed%2C%20%22We%20hold%20these%20truths%20to%20be%20self%20evident%3A%20that%20all%20men%20are%20created%20equal.&features=keywords"); // here is your URL path
                URL url=new URL(finalTxt);
                String username="1d9645a2-7b4b-44e0-bf04-9bf5ec32b464";
                String password="pwkYs0fFWduk";
                String userPassword = username + ":" + password;
                //String encoding = new sun.misc.Base64Encoder().encode(userPassword.getBytes());
                String encoding = Base64.encodeToString(userPassword.getBytes(),Base64.DEFAULT);


                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestProperty("Authorization", "Basic " + encoding);
                conn.connect();

                conn.setReadTimeout(15000 /* milliseconds */);
                conn.setConnectTimeout(15000 /* milliseconds */);
                conn.setRequestMethod("GET");

                /*conn.setDoInput(true);
                conn.setDoOutput(true);*/
                Log.d("halt5","there");

                String text="";
                String text1="";

                InputStreamReader in = new InputStreamReader((InputStream) conn.getContent());
                BufferedReader buff = new BufferedReader(in);
                //box.setText("Getting data ...");
                String line;
                do {
                    line = buff.readLine();
                    if(line==null)
                        break;
                    text=text+line + "\n";
                    Log.d("textsss",line);
                    if(line.toLowerCase().contains("text".toLowerCase()))
                    {

                        String[] temp =line.split(":");
                        String tt=temp[1].substring(2,temp[1].indexOf(',')-1);
                        Log.d("string",tt);
                        text1=text1+tt+'/';
                        Log.d("tx1",text1);



                    }


                } while (line != null);
               // box.setText(text.toString());
                Log.d("texthhh","hh");
                Log.d("holaf",text1);
                return text1;

            }
            catch(Exception e)
            {
                return new String("Exception: " + e.getMessage());
            }
        }

        @Override
        protected void onPostExecute(String result) {
            Toast.makeText(getApplicationContext(), result,
                    Toast.LENGTH_LONG).show();

            String[] parts = result.split("/");
            generateViewDynamically1(parts);
            StringBuilder txt=new StringBuilder();
            Log.d("mus","mus");
            int i=0;
            keyLength=0;
            for (String part : parts)
            {
                keyword[i]=part;
                keyLength++;
                txt.append(part);
                txt.append(" ");
                i++;

                Log.d("hey1",part);
            }
            //Log.d("keyword[0]",keyword[0]);
            //TextView tv = (TextView)findViewById(R.id.amount1);
            //tv.setText(txt.toString());

        }
    }



}