package com.example.user.vetsapp;

import android.app.ListActivity;
import android.app.SearchManager;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class searchableActivity extends AppCompatActivity {
    String query;
    TextView data;ImageView profile_pic;ImageButton vaccine;Cursor c; int id;String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchable);
        profile_pic = (ImageView)findViewById(R.id.profile_picture);
        getQuery();
        setData();
        ListVaccine();
    }

    public  void getQuery(){
        query = new String();
        Intent search_intent = getIntent();
        if(Intent.ACTION_SEARCH.equals(search_intent.getAction())){
            query = search_intent.getStringExtra(SearchManager.QUERY);

            //Toast.makeText(searchableActivity.this,query,Toast.LENGTH_SHORT).show();

        }

    }
    public void setData(){
        data = (TextView)findViewById(R.id.details);
        DBOperations d = new DBOperations(this);
         c = d.Get(query);
        /*if(c.getCount()==0){
           Toast.makeText(searchableActivity.this,"Nothings Found!!!",Toast.LENGTH_SHORT).show();
            //show error message
        }else{*/
            ArrayList b = new ArrayList();
            while(c.moveToNext()){
                b.add(0,"ID: "+c.getString(0));id = Integer.parseInt(c.getString(0));
                b.add(1,"Name: "+c.getString(1));name = c.getString(1);
                b.add(2,"Age: "+c.getString(2));
                b.add(3,"Breed: "+c.getString(3));
                b.add(4,"Sex: "+c.getString(4));
                b.add(5,"Owner: "+c.getString(5));
                b.add(6,"Address: "+c.getString(6));
                b.add(7,"TP: "+c.getString(7));
                b.add(8,c.getString(8));
            }String da;StringBuffer buffer = new StringBuffer();

            for(int i = 0;i<b.size()-1;i++){
                buffer.append(b.get(i).toString()+"\n");
            }da = buffer.toString();
        profile_pic.setImageDrawable(Drawable.createFromPath(b.get(8).toString()));
            data.setText(da);


           // showMessage("Data",buffer.toString());
       // }
    }
    public  void ListVaccine(){

        //id =  c.getInt(0);//Integer.parseInt(c.getString(0));
        vaccine = (ImageButton)findViewById(R.id.ImageButton_Vaccine);
        vaccine.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(searchableActivity.this,VaccineActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putInt("ID",id);
                        bundle.putString("PET",name);
                        startActivity(intent.putExtras(bundle));
                    }
                }
        );
    }
}
