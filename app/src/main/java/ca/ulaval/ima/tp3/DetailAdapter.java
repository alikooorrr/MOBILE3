
package ca.ulaval.ima.tp3;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

public class DetailAdapter extends RecyclerView.Adapter<DetailAdapter.DetailViewHolder> {
    @NonNull
    @Override
    public DetailViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull DetailViewHolder detailViewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class DetailViewHolder extends RecyclerView.ViewHolder{

        public DetailViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}

