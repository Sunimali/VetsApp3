package com.example.user.vetsapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class OwnersActivity extends AppCompatActivity {
    ImageButton register;
    EditText owner,address,tp;DBOperations D;DatabaseHelper h;Bundle b = new Bundle();



    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owners);
        b = getIntent().getExtras();
        // h = new RegisterFragment().setHelper();


        register();

    }
    public void register(){
        h = new DatabaseHelper();
        D = new DBOperations(this);
        register = (ImageButton)findViewById(R.id.imageButton_register);
        owner = (EditText)findViewById(R.id.OwName);
        address = (EditText)findViewById(R.id.OwAddress);
        tp = (EditText)findViewById(R.id.TP);
        register.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                       Toast.makeText(OwnersActivity.this,"name=="+b.getString("NAME")+"path=="+b.getString("PHOTO"),Toast.LENGTH_SHORT).show();
                        //Toast.makeText(OwnersActivity.this,"name=="+h.getName()+"path==",Toast.LENGTH_LONG).show();
                        h.setName(b.getString("NAME"));
                        h.setAge(b.getString("AGE"));
                        h.setSex(b.getString("SEX"));
                        h.setBreed(b.getString("BREED"));
                        h.setPhotoPath(b.getString("PHOTO"));
                        h.setOwner(owner.getText().toString());
                        h.setAddress(address.getText().toString());
                        h.setTP(tp.getText().toString());
                        boolean ok = D.ConnetdatabasetoAdd(h);
                        if(ok){
                           // Toast.makeText(OwnersActivity.this,"name=="+h.getName()+"path=="+h.getPhotoPath(),Toast.LENGTH_LONG).show();
                            Toast.makeText(OwnersActivity.this,"data inserted!!!",Toast.LENGTH_LONG).show();
                        }else{Toast.makeText(OwnersActivity.this,"Error occupied!!!",Toast.LENGTH_LONG).show();}

                    }
                }
        );
    }
}
