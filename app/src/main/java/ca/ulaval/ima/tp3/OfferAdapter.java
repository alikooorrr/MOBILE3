package ca.ulaval.ima.tp3;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class OfferAdapter extends RecyclerView.Adapter<OfferAdapter.OfferViewHolder> {

    private Context _context;
    private ArrayList<ContentOffer> _contentoffer;

    public OfferAdapter(Context context,ArrayList<ContentOffer> contentOffer){
        _context = context;
        _contentoffer = contentOffer;
    }

    @NonNull
    @Override
    public OfferViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(_context).inflate(R.layout.selectedoffre,viewGroup,false);
        return new OfferViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull OfferViewHolder offerViewHolder, int i) {
        ContentOffer currentItem = _contentoffer.get(i);
        String image = currentItem.getImage();
        int year = currentItem.getYear();
        int price = currentItem.getPrice();
        String brandname = currentItem.getModel().getBrand().getName();

        offerViewHolder.priceoffer.setText(price);
        offerViewHolder.yearoffrer.setText(year);
        offerViewHolder.brandnameoffer.setText(brandname);
        Picasso.with(_context).load(image).fit().centerInside().into(offerViewHolder.imageoffer);
    }

    @Override
    public int getItemCount() {
        return _contentoffer.size();
    }

    //year,price,brandname,image
    public class OfferViewHolder extends RecyclerView.ViewHolder{
        public ImageView imageoffer;
        public TextView priceoffer;
        public TextView yearoffrer;
        public TextView brandnameoffer;

        public OfferViewHolder(@NonNull View itemView) {
            super(itemView);
            imageoffer = itemView.findViewById(R.id.imageoffer);
            priceoffer = itemView.findViewById(R.id.priceoffer);
            yearoffrer = itemView.findViewById(R.id.anneeoffer);
            brandnameoffer = itemView.findViewById(R.id.marquenameoffer);
        }
    }
}
