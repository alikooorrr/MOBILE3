package ca.ulaval.ima.tp3;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class EssaieAdapteer extends RecyclerView.Adapter<EssaieAdapteer.EssaieAdapteerViewHolder>{

    private Context _context;
    private ArrayList<Essaie> _essaie;

    public EssaieAdapteer(Context _context, ArrayList<Essaie> _essaie) {
        this._context = _context;
        this._essaie = _essaie;
    }



    @NonNull
    @Override
    public EssaieAdapteerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(_context).inflate(R.layout.offreessaie,viewGroup,false);
        return new EssaieAdapteerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EssaieAdapteerViewHolder essaieAdapteerViewHolder, int i) {
        Essaie currentessaie = _essaie.get(i);
        String anneeapi = currentessaie.getImage();
        essaieAdapteerViewHolder.annee.setText(anneeapi);
        String prixapi = currentessaie.getCreated();
        essaieAdapteerViewHolder.prix.setText(prixapi);


    }

    @Override
    public int getItemCount() {
        return _essaie.size();
    }

    public class EssaieAdapteerViewHolder extends RecyclerView.ViewHolder{
        public TextView annee;
        public TextView prix;
        public EssaieAdapteerViewHolder(@NonNull View itemView) {
            super(itemView);
            annee = itemView.findViewById(R.id.anneeoffer);
            prix = itemView.findViewById(R.id.priceoffer);
        }
    }
}
