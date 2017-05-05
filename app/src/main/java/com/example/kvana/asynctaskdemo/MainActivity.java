package com.example.kvana.asynctaskdemo;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    EditText fnumber,snumber;
    Button btn_mul;
    String strUrl = "http://www.telusko.com/addition.htm?t1=3&t2=6";
    String result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fnumber= (EditText) findViewById(R.id.et_fnumber);
        snumber= (EditText) findViewById(R.id.et_snumber);

        btn_mul= (Button) findViewById(R.id.btn_multi);
        btn_mul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i = Integer.parseInt(fnumber.getText().toString());
                int j = Integer.parseInt(snumber.getText().toString());
                int k = i * j;
                strUrl = "http://www.telusko.com/addition.htm?t1="+i+"&t2="+j;
                new MyAsyncTask().execute();
            }
        });

    }

    public class MyAsyncTask extends AsyncTask<String,String,String>
    {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Toast.makeText(MainActivity.this,"Result is :"+result,Toast.LENGTH_LONG).show();
        }

        @Override
        protected String doInBackground(String... params) {
            try {
                URL url = new URL(strUrl);
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("GET");
                con.connect();

                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String value = bufferedReader.readLine();
                System.out.println("Result is: "+value);
                result = value;
            }catch (Exception e){
                System.out.println(e);
            }
            return null;
        }
    }
}
