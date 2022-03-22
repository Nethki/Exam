package com.nethki.countries.Adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nethki.countries.Model.Continent;
import com.nethki.countries.R;

import java.util.List;

public class RvMenuAdapter extends RecyclerView.Adapter<RvMenuAdapter.ViewHolder> {

    Context mContext;
    List<Continent>  array_region;
    boolean isActivated = false;
    OnclickButton onclickButton;

    public RvMenuAdapter(Context mContext, List<Continent> array_region,OnclickButton onclickButton) {
        this.mContext = mContext;
        this.array_region = array_region;
        this.onclickButton = onclickButton;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.menu_item,parent,false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.btnContinentName.setText(array_region.get(position).getName());
        holder.btnContinentName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // holder.btnContinentName.setEnabled(false);
                onclickButton.onClickButton(position,array_region.get(position).getName());
            }
        });
            holder.btnContinentName.setEnabled(!array_region.get(position).isActive);

    }

    @Override
    public int getItemCount() {
        return array_region.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        Button btnContinentName;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            btnContinentName = itemView.findViewById(R.id.btn_continent_name);
        }
    }

    public void changStatus(int position){

        reset();
        //change status
        array_region.get(position).setActive(true);
        this.notifyDataSetChanged();


    }
    public void reset(){
        //check if there's active state and switch it
        for(int i = 0 ; i < array_region.size(); i++){
            if(array_region.get(i).isActive){
                array_region.get(i).setActive(false);
            }
        }
    }

    public interface OnclickButton{
        void onClickButton(int position, String name);
    }
}
