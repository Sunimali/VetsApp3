package com.example.user.vetsapp;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class VaccineOptions extends AppCompatActivity {
final  int DAILOG_ID = 0;EditText date,name; int year,month,day;Bundle b = new Bundle();RadioGroup st;RadioButton selecteditem;vaccineEntity ent;
    DBOperations dbOperations = new DBOperations(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vaccine_options);
        setEditText();
    }
    public void setEditText(){
       // b = getIntent().getExtras();
        ent = (vaccineEntity) getIntent().getSerializableExtra("ENTITY");
        //ent = (vaccineEntity) b.get("ENTITY");
        date = (EditText)findViewById(R.id.editText4);
        name = (EditText)findViewById(R.id.editText3);
        st = (RadioGroup)findViewById(R.id.Radiogroup);
        String status = ent.getStatus();
        if(status.equals("Pending")){
            st.check(0);
        }else if(status.equals("Done")){
            st.check(1);
        }else {
            st.check(2);
        }
        name.setText(ent.getVaccine());
        date.setText(ent.getDate());
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.vaccine_menu, menu);//Menu Resource, Menu
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item1:
               // Toast.makeText(getApplicationContext(),"delete",Toast.LENGTH_LONG).show();
                final int id = ent.getId();
                makeDaillog("Are you sure you want to delete this??","Delete","Delete vaccine", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dbOperations.deleteRow(id);
                    }
                });


                return true;//delete
            default:
                makeDaillog("Are you sure you want to save this changes??","Save","Save vaccine", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        getEditData();
                        dbOperations.edit(ent.getId(),ent);
                    }
                });

                return super.onOptionsItemSelected(item);//profile
        }
    }
    public void getEditData(){
        ent.setVaccine(name.getText().toString());
        ent.setDate(date.getText().toString());
        int select = st.getCheckedRadioButtonId();
        selecteditem = (RadioButton) findViewById(select);
        ent.setStatus(selecteditem.getText().toString());
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

    public void makeDaillog(String message,String positive,String title,DialogInterface.OnClickListener onClickListener){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //Uncomment the below code to Set the message and title from the strings.xml file
        //builder.setMessage(R.string.dialog_message) .setTitle(R.string.dialog_title);

        //Setting message manually and performing action on button click
        builder.setMessage(message)
                .setCancelable(false)
                .setPositiveButton(positive, onClickListener

                )
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        dialog.cancel();
                    }
                });

        //Creating dialog box
        AlertDialog alert = builder.create();
        //Setting the title manually
        alert.setTitle(title);
        alert.show();
        setContentView(R.layout.activity_main);

    }
}
