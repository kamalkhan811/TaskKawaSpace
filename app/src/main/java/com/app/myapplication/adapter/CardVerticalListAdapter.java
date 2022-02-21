package com.app.myapplication.adapter;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.app.myapplication.R;
import com.app.myapplication.model.Name;
import com.app.myapplication.model.ResultsItem;

import java.util.ArrayList;
import java.util.List;

public class CardVerticalListAdapter extends RecyclerView.Adapter<CardVerticalListAdapter.MyViewHolder> {
    private int selectedIndex=0;
    private ItemListener listener;
    private ArrayList<ResultsItem> mList= new ArrayList<>();
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.bindItems(mList.get(position));
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setListItems(List<ResultsItem> list){
        mList=(ArrayList<ResultsItem>) list;
        notifyDataSetChanged();
    }

    public  void selectItemFromTopCrousel(int position){
        selectedItemHighlited(position);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tvName,tvGenderCountry,tvEmail;
        CardView cvRoot;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName=itemView.findViewById(R.id.tv_name);
            tvEmail=itemView.findViewById(R.id.tv_email);
            tvGenderCountry=itemView.findViewById(R.id.tv_gender_country);
            cvRoot=itemView.findViewById(R.id.cv_root);
            cvRoot.setOnClickListener(this);
        }

        public void bindItems(ResultsItem data) {
          Name name= data.getName();
          tvEmail.setText(data.getEmail());
          tvName.setText(name.getTitle()+"."+name.getFirst()+" "+name.getLast());
          tvGenderCountry.setText(data.getGender()+" . "+data.getNat());
            if(selectedIndex==getAdapterPosition()){
                cvRoot.setCardBackgroundColor(Color.parseColor("#A778FB"));
            }else{
                cvRoot.setCardBackgroundColor(Color.parseColor("#FFFFFF"));
            }
        }

        @Override
        public void onClick(View view) {
            listener.onClickItem(getAdapterPosition(),mList.get(getAdapterPosition()));
              selectedItemHighlited(getAdapterPosition());
        }
    }

    private void selectedItemHighlited(int adapterPosition) {
        int prevIndex=selectedIndex;
        selectedIndex=adapterPosition;
        if(prevIndex!=adapterPosition){
            notifyItemChanged(prevIndex);
            notifyItemChanged(adapterPosition);
        }
    }

    public void setClickItemListener(ItemListener listener){
        this.listener=listener;
    }
    public interface ItemListener{
        void onClickItem(int position, ResultsItem data);
    }

}
