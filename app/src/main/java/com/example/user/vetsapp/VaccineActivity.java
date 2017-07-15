package com.example.user.vetsapp;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class VaccineActivity extends AppCompatActivity {
    ImageButton gotoaddVaccine;Bundle iD = new Bundle();VaccineAdapter vaccineAdapter;ListView vaccines;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vaccine);
        vaccineAdapter = new VaccineAdapter(getApplicationContext(),R.layout.row_layout);
        vaccines = (ListView) findViewById(R.id.listView);
        vaccines.setAdapter(vaccineAdapter);
        iD = getIntent().getExtras();
        populateListView();
        addVaccineAct();
        changesDetails();


    }
    public  void populateListView(){


        ArrayList date = new ArrayList();ArrayList vacName = new ArrayList();ArrayList status = new ArrayList();
        DBOperations db = new DBOperations(this);
       Cursor c =  db.getListvac(iD.getInt("ID"));
        if(c!=null) {
            while (c.moveToNext()) {
                vacName.add(c.getString(1));
                date.add(c.getString(2));
                status.add(c.getString(3));
            }


            String[] vacname = new String[vacName.size()];
            vacname = (String[]) vacName.toArray(vacname);
            String[] datee = new String[date.size()];
            datee = (String[]) date.toArray(datee);
            String[] statuss = new String[status.size()];
            statuss = (String[]) status.toArray(statuss);

            int i = 0;

            for (String Name : vacname) {
                vaccineEntity e = new vaccineEntity(iD.getInt("ID"), datee[i], statuss[i], Name);
                vaccineAdapter.add(e);
                i++;
            }


        }else{
            Toast.makeText(VaccineActivity.this,"No vaccines set for yet!!!!",Toast.LENGTH_LONG).show();
        }
        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.row_layout,my);


    }
    public void addVaccineAct(){
        gotoaddVaccine = (ImageButton)findViewById(R.id.Imagebutton_new);
        gotoaddVaccine.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(VaccineActivity.this,AddVaccineActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putInt("id",iD.getInt("ID"));
                        bundle.putString("NAME",iD.getString("PET"));

                        startActivity(intent.putExtras(bundle));
                    }
                }
        );

    }
    public  void changesDetails(){
        vaccines.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                      vaccineEntity ent = (vaccineEntity) vaccineAdapter.getItem(i);
                       // Bundle bundle = new Bundle();
                       // bundle.putString("ENTITY", String.valueOf(ent));
                        Intent intent = new Intent(VaccineActivity.this,VaccineOptions.class);
                        intent.putExtra("ENTITY", ent);
                        startActivity(intent);

                    }
                }
        );
    }
}
