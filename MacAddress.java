package com.example.npstudent.myfirstapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;

public class MacAddress extends AppCompatActivity{
    Button btnRead;
    TextView textResult;

    //String[] args = {"/system/bin/cat", "/proc/net/arp"};
    String[] args = {"cat", "/proc/net/arp"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnRead = (Button)findViewById(R.id.readclient);
        textResult = (TextView)findViewById(R.id.result);

        btnRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textResult.setText(toRead());
            }
        });
    }

    private String toRead()
    {
        ProcessBuilder cmd;
        String result="";

        try{
            cmd = new ProcessBuilder(args);

            Process process = cmd.start();
            InputStream in = process.getInputStream();
            byte[] re = new byte[1024];
            while(in.read(re) != -1){
                System.out.println(new String(re));
                result = result + new String(re);
            }
            in.close();
        } catch(IOException ex){
            ex.printStackTrace();
        }
        return result;
    }
}


