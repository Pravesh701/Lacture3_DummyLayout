package com.appinventiv.lacture3_dummylayout;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ProgrammingAdapter extends RecyclerView.Adapter<ProgrammingAdapter.ViewHolderProgramming> {

    ArrayList<String> list;
    public ProgrammingAdapter(ArrayList<String> data) {
        list = data;
    }

    @NonNull
    @Override
    public ViewHolderProgramming onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.list_item_name, viewGroup, false);
        return new ViewHolderProgramming(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderProgramming viewHolderProgramming, int i) {
        String name = list.get(i);
        viewHolderProgramming.tv_name.setText(name + "\n");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }



    public static  class ViewHolderProgramming extends RecyclerView.ViewHolder {

        TextView tv_name;

        public ViewHolderProgramming(@NonNull View itemView) {
            super(itemView);
            tv_name = (TextView) itemView.findViewById(R.id.tv_recyclerView);
        }
    }
}
