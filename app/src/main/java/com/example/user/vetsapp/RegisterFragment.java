package com.example.user.vetsapp;

import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.content.FileProvider;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class RegisterFragment extends Fragment {
    private static final int CAM_REQUEST = 1;
    View myView;
    ImageButton b,cam;Button path;
    DatabaseHelper help;
    EditText name,age,breed;RadioGroup sex;RadioButton select;
    String nameS,ageS,breedS,sexS,photo_path;String imageFileName;

    @Nullable
    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.registeration_pet,container,false);
        b = (ImageButton)myView.findViewById(R.id.imageButton);
        cam = (ImageButton)myView.findViewById(R.id.Btn_camera);//path= (Button)myView.findViewById(R.id.button);
        getPhoto();//fr();
        fuck();

        return myView;


    }
    public void fuck(){
        name = (EditText)myView.findViewById(R.id.PtName);
        age = (EditText)myView.findViewById(R.id.PtAge);
        breed = (EditText)myView.findViewById(R.id.PtBreed);
        sex = (RadioGroup)myView.findViewById(R.id.radioGroup);

        //final OwnersActivity ownersActivity = new OwnersActivity();
        b.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                       /* help = new DatabaseHelper();
                        help.setName(name.getText().toString());
                        help.setAge(age.getText().toString());
                        help.setBreed(breed.getText().toString());*/
                        int selected_Id = sex.getCheckedRadioButtonId();
                        select = (RadioButton)myView.findViewById(selected_Id);
                        nameS = name.getText().toString();ageS = age.getText().toString();
                        breedS = breed.getText().toString();sexS = select.getText().toString();
                        Bundle localBundle = new Bundle();
                        localBundle.putString("NAME",nameS);
                        localBundle.putString("AGE",ageS);
                        localBundle.putString("BREED", breedS);
                        localBundle.putString("SEX",sexS);
                        localBundle.putString("PHOTO",photo_path);Toast.makeText(RegisterFragment.this.getActivity(),"path=="+photo_path,Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(RegisterFragment.this.getActivity(),OwnersActivity.class);
                       startActivity(intent.putExtras(localBundle));
                    }
                }
        );
    }
    private File getFile(){
        File folder = new File("sdcard/VetsApp");
        if(!folder.exists()){
            folder.mkdir();
        }
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        imageFileName = "JPEG_" + timeStamp + "_"+".jpg";
        File imageFile = new File(folder,imageFileName);
        return imageFile;
    }

    public void getPhoto(){
        cam.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        File file = getFile();
                        camera.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
                        startActivityForResult(camera,CAM_REQUEST);
                    }
                }
        );
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        photo_path =  "sdcard/VetsApp/"+imageFileName;
    }
/*@Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        String path = "sdcard/camApp/cam.jpg";

        //photo.setImageDrawable(Drawable.createFromPath(path));
    }*/



}

