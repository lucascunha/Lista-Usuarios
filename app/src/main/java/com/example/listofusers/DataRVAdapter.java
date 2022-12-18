package com.example.listofusers;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Adapter;

import com.example.listofusers.model.Data;

import java.util.ArrayList;

public class DataRVAdapter extends RecyclerView.Adapter<DataRVAdapter.DataViewHolder> {

    private ArrayList<Data> listData;
    private OnCardListener mOnCardListener;

    public ArrayList<Data> getListData() {
        return listData;
    }

    public DataRVAdapter(ArrayList<Data> listData, OnCardListener onCardListener) {
        this.listData = listData;
        this.mOnCardListener = onCardListener;
    }

    @NonNull
    @Override
    public DataRVAdapter.DataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.cardview, parent, false);
        return new DataViewHolder(view, mOnCardListener);
    }

    @Override
    public void onBindViewHolder(@NonNull DataRVAdapter.DataViewHolder holder, int position) {
        final Data temp = getListData().get(position);
        holder.cardview_name.setText(temp.getName());
        holder.cardview_age.setText(temp.getAge());
        holder.cardview_address.setText(temp.getAddress());
    }

    @Override
    public int getItemCount() {
        return getListData().size();
    }

    public class DataViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView cardview_name, cardview_age, cardview_address;
        OnCardListener onCardListener;

        public DataViewHolder(@NonNull View itemView, OnCardListener onCardListener) {
            super(itemView);

            cardview_name = itemView.findViewById(R.id.cardview_name);
            cardview_age = itemView.findViewById(R.id.cardview_age);
            cardview_address = itemView.findViewById(R.id.cardview_address);
            this.onCardListener = onCardListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onCardListener.onCardClick(getAdapterPosition());
        }
    }

    public interface OnCardListener {
        void onCardClick(int position);
    }
}
