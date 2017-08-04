package com.olegsagenadatrytwo.pictureandemi;

import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private static final int IMAGE_REQUEST = 1;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = (ImageView) findViewById(R.id.imgView);
    }

    //the onClick will be used by two buttons
    public void onClick(View view) {
        //switch case is needed to determine which of the buttons was clicked
        switch (view.getId()){
            //case when the Take Picture button clicked
            case R.id.btnTakePicture:
                //create an implicit intent that starts the camera
                Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (camera.resolveActivity(getPackageManager()) != null) {
                    //start the intent with the final int IMAGE_REQUEST passed in for later
                    //use inside the onActivityResult
                    startActivityForResult(camera, IMAGE_REQUEST);
                }
                break;
            //case when the button that takes you to EMI calculator was clicked
            case R.id.btnEMI:
                //create an Intent to go to EMIActivity
                Intent emi = new Intent(this, EMIActivity.class);
                //start the EMIActivity
                startActivity(emi);
                break;
        }
    }

    //onActivityResult will be called when an image was taken. So that means we can retrieve
    //this image and display it in out app
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //if the requestCode is equal to IMAGE_REQUEST that means that this method was called
        //right after the user took the image
        if(requestCode == IMAGE_REQUEST && resultCode == RESULT_OK){
            //retrieve the image
            Bundle bundle = data.getExtras();
            Bitmap bitmap = (Bitmap)bundle.get("data");
            //set our ImageView to the Image that user took
            imageView.setImageBitmap(bitmap);
        }

    }
}
