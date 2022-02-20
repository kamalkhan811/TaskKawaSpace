package com.app.myapplication.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.text.style.UnderlineSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.core.text.HtmlCompat;
import androidx.recyclerview.widget.RecyclerView;


import com.app.myapplication.R;
import com.app.myapplication.model.Location;
import com.app.myapplication.model.Name;
import com.app.myapplication.model.ResultsItem;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class TopCrouselAdapter extends RecyclerView.Adapter<TopCrouselAdapter.MyViewHolder> {
    private ItemListener listener;
    private ArrayList<ResultsItem> mList= new ArrayList<>();
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.top_crousel_item,parent,false);
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
        mList= (ArrayList<ResultsItem>) list;
        notifyDataSetChanged();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvName,tvAddress,tvTime,tvGender;
        ImageView ivCircular;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName=itemView.findViewById(R.id.tv_name);
            tvAddress=itemView.findViewById(R.id.tv_address);
            tvTime=itemView.findViewById(R.id.tv_time);
            tvGender=itemView.findViewById(R.id.tv_gender);
            ivCircular=itemView.findViewById(R.id.iv_pic);
        }

        public void bindItems(ResultsItem data) {
            Name name= data.getName();
            tvName.setText(name.getTitle().trim()+"."+name.getFirst().trim()+" "+name.getLast().trim());
            tvGender.setText(data.getGender().trim());
            Location location=data.getLocation();
           String timeDes= location.getTimezone().getDescription().trim();
           CharSequence finalTimeZone=timeZoneOperation(timeDes);
            CharSequence timeZone=location.getTimezone().getOffset().trim();
            tvTime.setText(TextUtils.concat(timeZone," - ",finalTimeZone));

            String cityState=location.getCity().trim()+", "+location.getState().trim()+", ";
            SpannableString streetNo = spanText(location.getStreet().getNumber()+", ", itemView.getContext(), "#A778FB", "",new StyleSpan(Typeface.BOLD));
            SpannableString country = spanText(location.getCountry().trim()+", ", itemView.getContext(), "#000000", "",new StyleSpan(Typeface.BOLD));
            CharSequence finalAddress=TextUtils.concat(streetNo,location.getStreet().getName().concat(", "),cityState,country,location.getPostcode().trim());
            tvAddress.setText(finalAddress);
            Glide.with(itemView.getContext()).load(data.getPicture().getThumbnail()).into(ivCircular);
        }

        private CharSequence timeZoneOperation(String timeDes) {
            String[] timeDesSplit=timeDes.split(",");
            CharSequence finalTimeZone = "";
            for(int i=0;i<timeDesSplit.length;i++){
                if(i==1){
                    CharSequence timeDesSecond= spanTextWithUnderline(timeDesSplit[1].trim()+", ", itemView.getContext(), "#000000",
                            "",new StyleSpan(Typeface.NORMAL));
                    finalTimeZone=TextUtils.concat(finalTimeZone, timeDesSecond);
                }else if(i==2){
                    SpannableString timeDesThird = spanText(timeDesSplit[2].trim()+", ", itemView.getContext(), "#000000",
                            "",new StyleSpan(Typeface.ITALIC));
                    finalTimeZone = TextUtils.concat(finalTimeZone, timeDesThird);
                }else if(i==0) {
                    finalTimeZone=timeDesSplit[i]+", ";
                }else {
                    finalTimeZone= TextUtils.concat(finalTimeZone, timeDesSplit[i],", ");
                }
            }

            return finalTimeZone.subSequence(0,finalTimeZone.toString().trim().length()-1);
        }

        public SpannableString spanText(String text, Context context, String color, String fontText, StyleSpan style) {
            SpannableString s1 = new SpannableString(text);
            if(!text.isEmpty()) {
                int index = text.indexOf(',');
                if(index==-1){
                    index= text.length();
                }
                s1.setSpan(new ForegroundColorSpan(Color.parseColor(color)), 0, s1.length(), 0);
                s1.setSpan(style, 0, index, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
            }
            return s1;
        }
        public SpannableString spanTextWithUnderline(String text, Context context, String color, String fontText, StyleSpan style) {
            SpannableString s1 = new SpannableString(text);
            if(!text.isEmpty()) {
                int index = text.indexOf(',');
                if(index==-1){
                   index= text.length();
                }
                UnderlineSpan us=new UnderlineSpan();
                TextPaint tp=new TextPaint();
                tp.setColor(Color.parseColor("#000000"));
                us.updateDrawState(tp);
                s1.setSpan(us, 0, index, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
                s1.setSpan(style, 0, index, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
                s1.setSpan(new ForegroundColorSpan(Color.parseColor(color)), 0, s1.length(), 0);
            }
            return s1;
        }
    }

    public void setClickItemListener(ItemListener listener){
        this.listener=listener;
    }
    public interface ItemListener{
        void onClickItem(int position, ResultsItem data);
    }
}
