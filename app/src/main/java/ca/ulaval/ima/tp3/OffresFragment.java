package ca.ulaval.ima.tp3;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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



public class OffresFragment extends Fragment implements MarqueAdapter.OnItemClickListener {

    public static final String EXTRA_NAME  = "name";

    private RecyclerView _recyclerView;
    private ArrayList<ContentMarque> _contentMarque;
    private MarqueAdapter _marqueAdapter;
    private RequestQueue _requestQueue;


    public OffresFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_offres,container,false);
        _recyclerView = view.findViewById(R.id.idrecyclerview);
        _recyclerView.setHasFixedSize(true);
        _recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        _contentMarque = new ArrayList<>();

        _requestQueue = Volley.newRequestQueue(getContext());

        JSONResult();
        return view;
    }

    public void JSONResult(){
        String url = "http://159.203.33.206/api/v1/brand/";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("content");
                    for(int i =0 ; i<jsonArray.length();i++){
                        JSONObject content = jsonArray.getJSONObject(i);
                        String nameitem = content.getString("name");
                        int iditem = content.getInt("id");
                        _contentMarque.add(new ContentMarque(iditem,nameitem));
                    }
                    _marqueAdapter = new MarqueAdapter(getContext(),_contentMarque);
                    _recyclerView.setAdapter(_marqueAdapter);
                    _marqueAdapter.setOnItemClickListener(OffresFragment.this);
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

        _requestQueue.add(request);

    }

    @Override
    public void onItemClickListener(int position) {
        Intent intent = new Intent(getContext(),ModelActivity.class);
        ContentMarque clickeditem = _contentMarque.get(position);

        intent.putExtra(EXTRA_NAME,clickeditem.getName());
        startActivity(intent);
    }
}
