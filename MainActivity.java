package com.example.npstudent.myfirstapplication;

import android.Manifest;
import android.app.AlertDialog;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
    private static final int MY_PERMISSIONS_REUESTS_READ_CONTACTS = 10;
    private static final int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 10;
    private Button btn;

        @Override
        public void onCreate(Bundle savedInstanceState){
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            btn = (Button) findViewById(R.id.btn);

            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v){
                    manageBtnClick();
                }
        });
}
        private void manageBtnClick(){
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS)
                    !=PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_CONTACTS },
                        MY_PERMISSIONS_REUESTS_READ_CONTACTS);
            }else{
                readContacts();
            }
        }
        private void readContacts(){
            Toast.makeText(this, "Read Contacts feature call", Toast.LENGTH_LONG).show();
        }
        @Override
        public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                               @NonNull int[] grantResults){
            switch (requestCode){
                case MY_PERMISSIONS_REQUEST_READ_CONTACTS   :
                    if (grantResults.length > 0
                            && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                        readContacts();
                    }else {

                        if(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_CONTACTS)){
                            new AlertDialog.Builder(this).
                                    setTitle("Read Contacts Permission").
                                    setMessage("You need to grant read contacts permission to use read"+
                                    "contacts features. Retry and grant it!").show();

                        } else{
                            new AlertDialog.Builder(this).
                                    setTitle("Read Contacts Permission denied").
                                    setMessage("You denied read contacts permission.So, the feature will be disabled.To enable it, go on settings and" +
                                            "grant read contacts for the application").show();
                          }
                    }
                    break;
            }
        }
}
