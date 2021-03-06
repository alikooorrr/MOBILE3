package ca.ulaval.ima.tp3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class offre extends AppCompatActivity {

    private RecyclerView _recyclerView;
    private ArrayList<Essaie> _essaie;
    private RequestQueue _requestQueue;
    private EssaieAdapteer _essaieAdapteer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offre);

        Intent intent = getIntent();

        final String namemodel = intent.getStringExtra("nameModel");
        final String idmodel = intent.getStringExtra("idModel");
        final String idmark = intent.getStringExtra("idMarque");

        Log.d("SALUT",namemodel);
        Log.d("id",idmodel);
        Log.d("idMarque",idmark);


        _recyclerView =(RecyclerView)findViewById(R.id.essaieoffre);
        _recyclerView.setLayoutManager(new LinearLayoutManager(this));
        _recyclerView.setHasFixedSize(true);

        _essaie = new ArrayList<>();
        _essaie.add(new Essaie(0,"ooo","dddd"));
        _requestQueue = Volley.newRequestQueue(this);
        JSONResultLIST(idmodel,idmark);
    }

   public void JSONResultLIST(String model,String mark){
        final String url = "http://159.203.33.206/api/v1/offer/";
        final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("content");
                    Log.d("BB",String.valueOf(jsonArray.length()));
                   for(int i =0;i<jsonArray.length();i++){
                       Log.d("BB",String.valueOf(jsonArray.get(0)));
                        JSONObject content = jsonArray.getJSONObject(i);
                        int yearcontent = content.getInt("year");
                        String createdcontent = content.getString("created");
                        String imagecontent = content.getString("image");
                        _essaie.add(new Essaie(yearcontent,imagecontent,createdcontent));
                    }
                    Log.d("II",String.valueOf(_essaie.get(1)));
                    _essaieAdapteer = new EssaieAdapteer(offre.this,_essaie);
                    _recyclerView.setAdapter(_essaieAdapteer);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();            }
        });
        _requestQueue.add(jsonObjectRequest);
    }

}
