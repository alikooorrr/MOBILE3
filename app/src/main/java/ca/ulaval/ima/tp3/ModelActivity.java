package ca.ulaval.ima.tp3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static ca.ulaval.ima.tp3.OffresFragment.EXTRA_NAME;


public class ModelActivity extends AppCompatActivity implements ModelAdapter.ClickToListOffer{

    private RecyclerView _recyclerview;
    private ArrayList<ContentModel> _contentModel;
    private ModelAdapter _modelAdapter;
    private RequestQueue _requestQueue;
    String idMarque="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_model);
        Intent intent = getIntent();


        final String namemarq = intent.getStringExtra(EXTRA_NAME);
        final String idmarq = intent.getStringExtra("id");
        idMarque=idMarque+idmarq;

        _recyclerview = (RecyclerView)findViewById(R.id.recyclerviewmodel);
        _recyclerview.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        _recyclerview.setHasFixedSize(true);
        _contentModel = new ArrayList<>();
        _requestQueue = Volley.newRequestQueue(this);
        JSONResult(namemarq);


    }


    public void JSONResult(final String valeur){
        String url = "http://159.203.33.206/api/v1/model/";
        final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("content");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject content = jsonArray.getJSONObject(i);
                        int iditem = content.getInt("id");
                        JSONObject brand = content.getJSONObject("brand");
                        int idbrand = brand.getInt("id");
                        String namebrand = brand.getString("name");
                        if(namebrand.equals(valeur)){
                            String nameitem = content.getString("name");
                            _contentModel.add(new ContentModel(iditem, nameitem));
                        }
                    }
                    _modelAdapter = new ModelAdapter(ModelActivity.this,_contentModel);
                    _recyclerview.setAdapter(_modelAdapter);
                    _modelAdapter.setOnItemClickListener(ModelActivity.this);
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


    @Override
    public void ItemClickListener(int position) {
        Intent intent = new Intent(this,offre.class);
        ContentModel clickeditem = _contentModel.get(position);
        String p = String.valueOf(position);
        intent.putExtra("nameModel",clickeditem.getName());
        intent.putExtra("idModel",p);
        intent.putExtra("idMarque",idMarque);
        startActivity(intent);
    }


}