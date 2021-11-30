package com.example.passwordmanager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {
    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView username, password, site_url;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            this.username = (TextView) itemView.findViewById(R.id.username);
            this.password = (TextView) itemView.findViewById(R.id.pwd);
            this.site_url = (TextView) itemView.findViewById(R.id.site_url);

        }
    }
    private final LayoutInflater mInflater;
    private List<Data> Datalist;
//    private ClickListener clickListener;

    RecyclerViewAdapter(Context context) {
        mInflater= LayoutInflater.from(context);

    }

    @NonNull
    @Override
    public RecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.MyViewHolder holder, int position) {
        if (Datalist != null) {
            Data data = Datalist.get(position);
            holder.site_url.setText(data.getSite_url());
            holder.username.setText(data.getUsername());
            holder.password.setText(data.getPassword());
        } else {
            holder.site_url.setText("No data");
            holder.username.setText("");
            holder.password.setText("");
        }
    }
    void setDatalist(List<Data> data)
    {
        Datalist=data;
        notifyDataSetChanged();
    }

        @Override
        public int getItemCount () {
            if (Datalist != null)
                return Datalist.size();
            else return 0;
        }
        public Data getPosition(int position){return Datalist.get(position);}

    }