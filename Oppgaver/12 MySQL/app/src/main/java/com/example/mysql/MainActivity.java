package com.example.mysql;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.textview);
        getJSON task = new getJSON();
        task.execute(new String[]{"http://student.cs.hioa.no/~s331153/husjsonout.php"});
    }

private class getJSON extends AsyncTask<String, Void,String> {
    JSONObject jsonObject;

    @Override
    protected String doInBackground(String... urls) {
        String retur = "";
        String s = "";
        String output = "";
        for (String url : urls) {
            try {
                URL urlen = new URL(urls[0]);
                HttpURLConnection conn = (HttpURLConnection)
                        urlen.openConnection();
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Accept", "application/json");
                if (conn.getResponseCode() != 200) {
                    throw new RuntimeException("Failed : HTTP error code : + conn.getResponseCode()");
                }
                BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
                System.out.println("Output from Server .... \n");
                while ((s = br.readLine()) != null) {
                    output = output + s;
                }
                conn.disconnect();
                try {
                    JSONArray mat = new JSONArray(output);
                    for (int i = 0; i < mat.length(); i++) {
                        JSONObject jsonobject = mat.getJSONObject(i);
                        String Beskrivelse = jsonobject.getString("Beskrivelse");
                        retur = retur + Beskrivelse + "\n";
                    }
                    return retur;
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                return retur;
            } catch (Exception e) {
                return "Noe gikk feil";
            }
        }
        return retur;
    }

    @Override
    protected void onPostExecute(String ss) {
        textView.setText(ss);
    }
}
}

