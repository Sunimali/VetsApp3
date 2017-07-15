package com.example.user.vetsapp;

import android.app.Fragment;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.telephony.SmsManager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageButton;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    ImageButton owner;Handler handler = new Handler();
    private GoogleApiClient client;DBOperations dbOperations = new DBOperations(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //ImageButton photo = (ImageButton) findViewById(R.id.imageButton);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
       //

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
       // get();
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.

        android.app.FragmentManager fragmentManager = getFragmentManager();
        int id = item.getItemId();

        if (id == R.id.nav_register) {
            fragmentManager.beginTransaction().replace(R.id.content_frame, new RegisterFragment()).commit();
            // Handle the camera action

            // fragmentManager.beginTransaction().replace(R.id.nav_register,new RegisterFragment()).commit();
        } else if (id == R.id.nav_search) {

            fragmentManager.beginTransaction().replace(R.id.content_frame, new SearchFragament()).commit();onSearchRequested();

        } else if (id == R.id.nav_upcoming_events) {

       fragmentManager.beginTransaction().replace(R.id.content_frame, new UpcomingEventFragments()).commit();

    }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.example.user.vetsapp/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.example.user.vetsapp/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }

    public  void getdelay(){
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY,20);
        calendar.set(Calendar.MINUTE,17);
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                // Toast.makeText(MainActivity.this,"this could be happen!!!!",Toast.LENGTH_LONG).show();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        String date  = new SimpleDateFormat("yyyy/MM/dd").format(new Date());
                        Cursor cursorVac =  dbOperations.sendSMS(date);//cursorVac.
                        Cursor cursorUpcoming = dbOperations.sendSMS(date);
                       ArrayList entityvac = new ArrayList();
                        ArrayList upcoming = new ArrayList();
                        while (cursorVac.moveToNext()){
                            vaccineEntity ve = new vaccineEntity();
                            ve.setDate(cursorVac.getString(2));
                            ve.setVaccine(cursorVac.getString(1));
                            ve.setId(cursorVac.getInt(0));
                            entityvac.add(ve);
                        }
                        while (cursorUpcoming.moveToNext()){
                            vaccineEntity ve = new vaccineEntity();
                            ve.setDate(cursorVac.getString(2));
                            ve.setVaccine(cursorVac.getString(1));
                            ve.setId(cursorVac.getInt(0));
                            upcoming.add(ve);
                        }
                       /* SmsManager smsManager = SmsManager.getDefault();
                        smsManager.sendTextMessage("0719898220", null,"LOKUUUUUUUU...", null, null);*/
                    }
                });
            }
        };
        timer.scheduleAtFixedRate(timerTask,calendar.getTime(),2*60*1000);


    }

}
