package com.krishna.dbconnectionexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    Button btn;
    private static String web_url="http://10.0.2.2/android_php/DatabaseConnection.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn=findViewById(R.id.check);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Checkconnection();
            }
        });

    }
    public void Checkconnection(){

        StringRequest stringRequest= new StringRequest(Request.Method.POST, web_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try
                {
                    JSONObject jsonObject = new JSONObject(response);

                    int sucess=jsonObject.getInt("sucess");
                    Log.d("Success",String.valueOf(sucess));

                    if (sucess==1)
                    {
                        Toast.makeText(MainActivity.this,jsonObject.getString("message"),Toast.LENGTH_LONG).show();
                        Log.d("Message",jsonObject.getString("message"));
                    }
                    else if(sucess ==0)
                    {
                        Toast.makeText(MainActivity.this,jsonObject.getString("message"),Toast.LENGTH_LONG).show();
                    }

                }
                catch (JSONException e)
                {
                    e.printStackTrace();
                    Toast.makeText(MainActivity.this,e.getMessage(),Toast.LENGTH_LONG).show();

                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this,"ERROR Ocurred",Toast.LENGTH_LONG).show();

            }
        });

        RequestQueue requestQueue= Volley.newRequestQueue(MainActivity.this);
        requestQueue.add(stringRequest);
    }
}