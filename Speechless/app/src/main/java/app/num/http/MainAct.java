package app.num.http;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
//import android.view.Menu;
//import android.view.MenuItem;
//import android.view.View;package com.example.speechless;


import java.lang.*;

import android.view.View;


import android.view.Menu;
import android.view.MenuItem;
import android.util.Log;

public class MainAct extends Activity
{


    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //View rootView = inflater.inflate(R.layout.fragment_home_, null);
        //Button btn1=(Button) findViewById(R.id.button);
        //Button btn2=(Button) findViewById(R.id.button2);

        Log.d("hola","hola");
        // Initialize the progress bar
    }

    /**
     * Initializes the activity menu
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        //getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

    /**
     * Select an option from the menu
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        /*if (item.getItemId() == R.id.menu_refresh) {
            refreshItemsFromTable();
        }*/

        return true;
    }
    public void moveToTeacher(View view)
    {
        Intent intent = new Intent(this, Teacher.class);
        startActivity(intent);

    }
    public void moveToStudent(View view)
    {
        Intent intent = new Intent(this, StudentEntry.class);
        startActivity(intent);

    }


}