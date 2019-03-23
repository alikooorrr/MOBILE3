package ca.ulaval.ima.tp3;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
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
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.google.common.collect.Range;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import static android.app.Activity.RESULT_OK;

public class VendreFragment extends Fragment implements View.OnClickListener {

    ImageView imageView;
    EditText editPicker,price,kilometre,nbrPorte,firstname,lastname,telephone,description;
    //owner = False;
    RadioButton transmission;
    Spinner spinner,spinnermethode;
    String url= "http://159.203.33.206/api/v1/brand/";
    ArrayList<String> modelListSpinner;
    ArrayList<String> marque;
    View viewAnnonces;
    Button soumettre;
    AwesomeValidation awesomeValidation;


    private static int SELECT_IMAGE_INTENT = 1;
    public VendreFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        viewAnnonces = inflater.inflate(R.layout.fragment_vendre, container, false);

        imageView = viewAnnonces.findViewById(R.id.imageView);
        editPicker = viewAnnonces.findViewById(R.id.pickeryear);
        price = viewAnnonces.findViewById(R.id.editprice);
        kilometre = viewAnnonces.findViewById(R.id.editkilometre);
        //A faire
        //owner = viewAnnonces.findViewById(R.id.toggleButton);
        //transmission = viewAnnonces.findViewById(R.id.b)
        spinner = viewAnnonces.findViewById(R.id.marque);
        spinnermethode = viewAnnonces.findViewById(R.id.model);
        nbrPorte = viewAnnonces.findViewById(R.id.editporte);
        firstname = viewAnnonces.findViewById(R.id.editfirstname);
        lastname = viewAnnonces.findViewById(R.id.editlastname);
        telephone = viewAnnonces.findViewById(R.id.editphone);
        description = viewAnnonces.findViewById(R.id.editdescription);
        soumettre = viewAnnonces.findViewById(R.id.valid);

        marque = new ArrayList<>();

        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);

        loadSpinnerData(url);

        imageView = viewAnnonces.findViewById(R.id.imageView);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i,SELECT_IMAGE_INTENT);
            }
        });

        editPicker.setOnClickListener(this);
        soumettre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                soumis(soumettre);
                if(enregistrer()){
                    soumis(soumettre);
                }
            }
        });

        return viewAnnonces;
    }

    public void numerPickerDialog(){
        NumberPicker anneePicker = new NumberPicker(getContext());
        anneePicker.setMinValue(1000);
        anneePicker.setMaxValue(3000);
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

                soumettrebuilder.setView(soumettreview);
                final AlertDialog dialog = soumettrebuilder.create();
                dialog.show();

                btnconfirme.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog();
                        if(!login.getText().toString().isEmpty() && !pwd.getText().toString().isEmpty()){
                            Toast.makeText(getContext(),"Verification login et mpd doivent etre juste",Toast.LENGTH_LONG).show();
                        }{
                            Toast.makeText(getContext(),"Remplisser les champs vides",Toast.LENGTH_LONG).show();

                        }
                    }
                });
            }
        });
    }

    public boolean enregistrer(){

        boolean resultat = false;
        awesomeValidation.addValidation((Activity) getContext(),R.id.editprice,Range.closed(1,900000000),R.string.err_price);
        awesomeValidation.addValidation((Activity) getContext(),R.id.editkilometre,Range.closed(0,200000000),R.string.err_kilometre);
        awesomeValidation.addValidation((Activity) getContext(),R.id.pickeryear,Range.closed(0,3000),R.string.err_year);
        awesomeValidation.addValidation((Activity) getContext(),R.id.firstname,"[a-zA-Z\\s]+",R.string.prenom);
        awesomeValidation.addValidation((Activity) getContext(),R.id.lastname,"[a-zA-Z\\s]+",R.string.nom);
        awesomeValidation.addValidation((Activity) getContext(),R.id.editphone, RegexTemplate.TELEPHONE,R.string.tel);
        awesomeValidation.addValidation((Activity) getContext(),R.id.editporte,Range.closed(2,8),R.string.err_porte);

        if(awesomeValidation.validate()){
            Toast.makeText(getContext(),"",Toast.LENGTH_LONG).show();
            resultat =  true;
        }
        return resultat;
    }

    public void dialog(){
        final ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Connected please...");
        progressDialog.setIndeterminate(true);
        progressDialog.setProgress(0);
        progressDialog.show();

        Runnable progressRunnable = new Runnable() {

            @Override
            public void run() {
                progressDialog.cancel();
            }
        };

        Handler pdCanceller = new Handler();
        pdCanceller.postDelayed(progressRunnable, 3000);

    }

}
