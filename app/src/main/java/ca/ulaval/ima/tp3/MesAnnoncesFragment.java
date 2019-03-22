package ca.ulaval.ima.tp3;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MesAnnoncesFragment extends Fragment {

    public MesAnnoncesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View viewOffre = inflater.inflate(R.layout.fragment_mes_annonces, container, false);

        return viewOffre;
    }


}
