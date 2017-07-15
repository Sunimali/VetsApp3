package com.example.user.vetsapp;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class AddVaccineActivity extends AppCompatActivity {
ImageButton add ;
    EditText name,date;Bundle bundle = new Bundle();
    int year,month,day;static  final int DAILOG_ID = 0;RadioGroup st;RadioButton selecteditem;DBOperations dbOperations = new DBOperations(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_vaccine);
       getDatePicker();
        addNew();

    }
    public void getDatePicker(){
       date = (EditText)findViewById(R.id.editText2);
        date.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        showDialog(DAILOG_ID);
                    }
                }
        );
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        if(id==DAILOG_ID)
        return new DatePickerDialog(this,dplistener,year,month,day);
        return  null;
    }
    private DatePickerDialog.OnDateSetListener dplistener = new DatePickerDialog.OnDateSetListener(){

        @Override
        public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
            year = i;
            month = i1;
            day = i2;
            date.setText(year+"/"+month+"/"+day);
        }
    };
    public void addNew(){
        name = (EditText)findViewById(R.id.editText);
        st  = (RadioGroup)findViewById(R.id.RadioStatutsG) ;


        add = (ImageButton)findViewById(R.id.imageButton2);
        add.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int selected_Id = st.getCheckedRadioButtonId();
                        selecteditem = (RadioButton)findViewById(selected_Id);
                        bundle =  getIntent().getExtras();
                        vaccineEntity v = new vaccineEntity();
                        v.setDate(date.getText().toString());
                        v.setVaccine(name.getText().toString());
                        v.setStatus(selecteditem.getText().toString());
                        v.setId(bundle.getInt("id"));
                        boolean ok =  dbOperations.addVaccine(v);
                        if(ok){

                            Toast.makeText(AddVaccineActivity.this,"data inserted!!!",Toast.LENGTH_LONG).show();
                        }else{Toast.makeText(AddVaccineActivity.this,"Error occupied!!!",Toast.LENGTH_LONG).show();}

                    }
                }
        );
    }
}
