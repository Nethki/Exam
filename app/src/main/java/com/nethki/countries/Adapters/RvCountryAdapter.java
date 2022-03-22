package com.nethki.countries.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.nethki.countries.Model.Country;
import com.nethki.countries.R;

import java.util.List;

public class RvCountryAdapter extends RecyclerView.Adapter<RvCountryAdapter.ViewHolder> {
    Context mContext;
    List<Country> countryList;
    List<Country> origCountryList;
    OnClickButtonCountry onClickButtonCountry;

    public RvCountryAdapter(Context mContext, List<Country> countryList, OnClickButtonCountry onClickButtonCountry) {
        this.mContext = mContext;
        this.countryList = countryList;
        this.origCountryList = countryList;
        this.onClickButtonCountry = onClickButtonCountry;
    }

    public void filterList(List<Country> filterllist) {
        // below line is to add our filtered
        // list in our course array list.
        countryList = filterllist;
        // below line is to notify our adapter
        // as change in recycler view data.
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.country_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if(countryList!=null){
            holder.txtCaa.setText(countryList.get(position).getCca2());
            holder.txtName.setText(countryList.get(position).getName().getCommon());
            Glide.with(mContext)
                    .load(countryList.get(position).getFlags().getPng())
                    .centerCrop()
                    .into(holder.imgFlag);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.e("TEST","Click holder");
                    onClickButtonCountry.onClickCountry(countryList.get(position).getName().getCommon());
                }
            });

//            holder.imgFlag.sets(countryList.get(position).getName().getOfficial());
        }

    }

    @Override
    public int getItemCount() {
        return countryList.size();
    }

    public void updateData(List<Country> countryList) {
        this.countryList = countryList;
        this.notifyDataSetChanged();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtCaa;
        TextView txtName;
        ImageView imgFlag;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtCaa = itemView.findViewById(R.id.cca);
            txtName = itemView.findViewById(R.id.name);
            imgFlag = itemView.findViewById(R.id.img_flag);
        }
    }

    public interface OnClickButtonCountry {
        void onClickCountry(String common);
    }


}
