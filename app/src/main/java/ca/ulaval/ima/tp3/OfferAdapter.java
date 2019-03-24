package ca.ulaval.ima.tp3;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class OfferAdapter extends RecyclerView.Adapter<OfferAdapter.OfferAdapterViewHolder>{

    private Context _context;
    private ArrayList<ContentOffer> _contentoffer;

    public OfferAdapter(Context _context, ArrayList<ContentOffer> _contentoffer) {
        this._context = _context;
        this._contentoffer = _contentoffer;
    }

    @NonNull
    @Override
    public OfferAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(_context);
        View view = inflater.inflate(R.layout.selectedoffre,null);
        return new OfferAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OfferAdapterViewHolder offerAdapterViewHolder, int i) {
        ContentOffer contentOffer =  _contentoffer.get(i);
        offerAdapterViewHolder.prix.setText(contentOffer.getPrice());
        offerAdapterViewHolder.year.setText(contentOffer.getYear());
        offerAdapterViewHolder.marque.setText(contentOffer.getModel().getBrand().getName());
    }

    @Override
    public int getItemCount() {
        return _contentoffer.size();
    }

    public class OfferAdapterViewHolder extends RecyclerView.ViewHolder{
        //ImageView imageView;
        TextView marque,year,prix;
        public OfferAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            //imageView = itemView.findViewById(R.id.imageoffer);
            marque = itemView.findViewById(R.id.marquenameoffer);
            year = itemView.findViewById(R.id.anneeoffer);
            prix = itemView.findViewById(R.id.priceoffer);

        }
    }
}
