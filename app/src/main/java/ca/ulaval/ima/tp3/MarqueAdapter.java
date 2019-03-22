package ca.ulaval.ima.tp3;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class MarqueAdapter extends RecyclerView.Adapter<MarqueAdapter.MarqueAdapterViewHolder>{

    private Context context;
    private ArrayList<ContentMarque> listContent;
    private OnItemClickListener mListener;

    public interface OnItemClickListener{
        void onItemClickListener(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;
    }

    public MarqueAdapter(Context context, ArrayList<ContentMarque> listContent){
        this.context = context;
        this.listContent = listContent;
    }

    @NonNull
    @Override
    public MarqueAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.marques,viewGroup,false);
        return new MarqueAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MarqueAdapterViewHolder marqueAdapterViewHolder, int i) {
        ContentMarque currentcontent = listContent.get(i);
        String nameapi = currentcontent.getName();
        marqueAdapterViewHolder._textview.setText(nameapi);

    }

    @Override
    public int getItemCount() {
        return listContent.size();
    }

    public class MarqueAdapterViewHolder extends RecyclerView.ViewHolder{
         public TextView _textview;
         public MarqueAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            _textview = itemView.findViewById(R.id.namemarque);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mListener!=null){
                        int position = getAdapterPosition();
                        mListener.onItemClickListener(position);
                    }
                }
            });
        }
    }

}
