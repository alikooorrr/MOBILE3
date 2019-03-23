package ca.ulaval.ima.tp3;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.Response;

import org.json.JSONObject;

import java.util.ArrayList;


public class ModelAdapter extends RecyclerView.Adapter<ModelAdapter.ModelAdapterViewHolder>{

    private Context context;
    private ArrayList<ContentModel> listContent;
    private ClickToListOffer mListener;

    public interface ClickToListOffer{
        void ItemClickListener(int position);
    }


    public void setOnItemClickListener(ClickToListOffer listener){
        mListener = listener;
    }

    public ModelAdapter(Context context,ArrayList<ContentModel> listContent){
        this.context = context;
        this.listContent = listContent;
    }

    @NonNull
    @Override
    public ModelAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.model,viewGroup,false);
        return new ModelAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ModelAdapterViewHolder modelAdapterViewHolder, int i) {
        ContentModel currentcontent = listContent.get(i);
        String nameapi = currentcontent.getName();
        modelAdapterViewHolder._textview.setText(nameapi);
    }

    @Override
    public int getItemCount() {
        return listContent.size();
    }
    public class ModelAdapterViewHolder extends RecyclerView.ViewHolder{
        public TextView _textview;
        public ModelAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            _textview = itemView.findViewById(R.id.namemodel);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mListener!=null){
                        int item = getAdapterPosition();
                        i = item;
                        mListener.ItemClickListener(item);
                    }
                }
            });
        }
    }


}

