package com.example.lyricsfinder;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;

public class MainActivity extends AppCompatActivity {

    // add implementation to gradle dependency script
    // Android Volley Overview: https://developer.android.com/training/volley
    // implementation 'com.android.volley:volley:1.1.1'

    //Declarations
    private EditText edtArtistName, edtSongName;
    private Button   btnGetLyrics;
    private TextView    txtLyrics;

    // API: https://api.lyrics.ovh/v1/Rihanna/Diamonds#

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initializations
        edtArtistName = findViewById(R.id.edtArtistName);
        edtSongName = findViewById(R.id.edtSongName);
        btnGetLyrics = findViewById(R.id.btnGetLyrics);
        txtLyrics = findViewById(R.id.btnGetLyrics);


        btnGetLyrics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "https://api.lyrics.ovh/v1/" + edtArtistName.getText().toString() + "/"+ edtSongName.getText().toString();
                url.replace(" ","20%");
                RequestQueue rqtQueue = Volley.newRequestQueue(MainActivity.this);

                JsonObjectRequest jsonObjRqt = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try{
                            //txtLyrics.setText(response.toString());
                            txtLyrics.setText(response.getString("lyrics"));

                        } catch (JSONException e){
                            e.printStackTrace();
                        }


                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
                //Toast.makeText(getApplicationContext(),"This btn is tapped!",Toast.LENGTH_SHORT).show();

                rqtQueue.add(jsonObjRqt);
            }
        });
    }
}