package ca.ulaval.ima.tp3;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import static android.app.Activity.RESULT_OK;

public class VendreFragment extends Fragment implements View.OnClickListener {

    ImageView imageView;
    Spinner spinner;
    String url= "http://159.203.33.206/api/v1/brand/";
    ArrayList<String> marque;
    View viewAnnonces;
    Spinner spinnermethode;
    EditText editPicker;
    ArrayList<String> modelListSpinner;
    Button soumettre;
    private static int SELECT_IMAGE_INTENT = 1;
    public VendreFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        viewAnnonces = inflater.inflate(R.layout.fragment_vendre, container, false);

        spinner = viewAnnonces.findViewById(R.id.marque);
        soumettre = viewAnnonces.findViewById(R.id.valid);

        spinnermethode = viewAnnonces.findViewById(R.id.model);
        marque = new ArrayList<>();
        loadSpinnerData(url);

        imageView = viewAnnonces.findViewById(R.id.imageView);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i,SELECT_IMAGE_INTENT);
            }
        });
        editPicker = viewAnnonces.findViewById(R.id.pickeryear);
        editPicker.setOnClickListener(this);
        soumettre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                soumis(soumettre);
            }
        });

        return viewAnnonces;
    }

    public void numerPickerDialog(){
        NumberPicker anneePicker = new NumberPicker(getContext());
        anneePicker.setMinValue(2001);
        anneePicker.setMaxValue(2019);
        NumberPicker.OnValueChangeListener newVal = new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                editPicker.setText(""+newVal);
            }
        };
        anneePicker.setOnValueChangedListener(newVal);
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext()).setView(anneePicker);
        builder.setTitle("Year Production");
        builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.show();
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==SELECT_IMAGE_INTENT && resultCode==RESULT_OK){
            Uri selectedImage = data.getData();
            imageView.setImageURI(selectedImage);
        }
    }


    @Override
    public void onClick(View v) {
        numerPickerDialog();
    }

    public void loadSpinnerData(String url){
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("content");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject content = jsonArray.getJSONObject(i);
                        String nameitem = content.getString("name");
                        int iditem = content.getInt("id");
                        marque.add(nameitem);
                    }
                    ArrayAdapter<String> data= new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_dropdown_item, marque);
                    data.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner.setAdapter(data);

                    spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            position +=1;
                            String urlmodel = "http://159.203.33.206/api/v1/brand/"+Integer.toString(position)+"/models/";
                            final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, urlmodel, null, new Response.Listener<JSONObject>() {
                                @Override
                                public void onResponse(JSONObject response) {
                                    try {
                                        JSONArray jsonArray = response.getJSONArray("content");
                                        for (int i = 0; i < jsonArray.length(); i++) {
                                            JSONObject content = jsonArray.getJSONObject(i);
                                            int iditem = content.getInt("id");
                                            String nameModele = content.getString("name");
                                            modelListSpinner.add(nameModele);
                                        }
                                        Log.d("TAILLEAFTER",String.valueOf(modelListSpinner.size()));
                                        for(int i=0;i < modelListSpinner.size();i++){
                                            Log.d("LISTMODELSPINNER",modelListSpinner.get(i));
                                        }
                                        ArrayAdapter<String> dataM= new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_dropdown_item, modelListSpinner);
                                        dataM.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                        spinnermethode.setAdapter(dataM);

                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }, new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    error.printStackTrace();            }
                            });
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });

                 //Display Model

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
        requestQueue.add(stringRequest);

    }

    public void soumis(Button bsoumettre){
        bsoumettre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder soumettrebuilder = new AlertDialog.Builder(getContext());
                View soumettreview = getLayoutInflater().inflate(R.layout.login,null);
                final EditText login = soumettreview.findViewById(R.id.editlogin);
                final EditText pwd = soumettreview.findViewById(R.id.editpassword);
                Button btnconfirme = soumettreview.findViewById(R.id.confirme);

                btnconfirme.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(!login.getText().toString().isEmpty() && !pwd.getText().toString().isEmpty()){
                            Toast.makeText(getContext(),"Verification login et mpd doivent etre juste",Toast.LENGTH_LONG).show();
                        }{
                            Toast.makeText(getContext(),"Remplisser les champs vides",Toast.LENGTH_LONG).show();
                        }
                    }
                });
                soumettrebuilder.setView(soumettreview);
                AlertDialog dialog = soumettrebuilder.create();
                dialog.show();
            }
        });
    }

}
