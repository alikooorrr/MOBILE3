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
import com.google.gson.JsonObject;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ListOffreActivity extends AppCompatActivity {

    private RecyclerView _recyclerview;
    private OfferAdapter _offerAdapter;
    private RequestQueue _requestQueue;
    private ArrayList<ContentOffer> _contentOffer;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_offre);


        _recyclerview = (RecyclerView)findViewById(R.id.idlisteoffrerecyclerview);
        _recyclerview.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        _recyclerview.setHasFixedSize(true);
        _contentOffer = new ArrayList<>();


        _requestQueue = Volley.newRequestQueue(this);

    }


    public void JSONResultOffer(){
        //define url
        String url ="";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try{
                    JSONArray jsonArray = response.getJSONArray("content");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject content = jsonArray.getJSONObject(i);
                        int idcontent = content.getInt("id");
                        int yearcontent = content.getInt("year");
                        boolean fromOwnercontent = content.getBoolean("from_owner");
                        int kilometerscontent = content.getInt("kilometers");
                        int pricecontent = content.getInt("price");
                        String createdcontent = content.getString("created");
                        String imagecontent = content.getString("image");
                        JSONObject modelcontent = content.getJSONObject("model");
                        int idmodelcontent = modelcontent.getInt("id");
                        String namemodelcontent = modelcontent.getString("name");
                        JSONObject brandmodelcontent = modelcontent.getJSONObject("brand");
                        String idbrandmodelcontent = brandmodelcontent.getString("name");
                        int namebrandmodelcontent = brandmodelcontent.getInt("id");
                    }
                    /*_offerAdapter = new OfferAdapter(ListOffreActivity.this,);//liste manquante
                    _recyclerview.setAdapter(_offerAdapter);*/

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        _requestQueue.add(jsonObjectRequest);
    }


}
