package com.example.noteappproject;

import android.app.Notification;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {

    private List<ListItems> itemsList;

    public ItemAdapter(List<ListItems> itemsList) {
        this.itemsList = itemsList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ListItems item = itemsList.get(position);
        holder.textViewTitle.setText(item.getTitle());
        holder.textViewDescription.setText(item.getDescription());

        holder.checkBoxImportant.setChecked(item.isImportant());
        holder.checkBoxStarted.setChecked(item.isStarted());
        holder.checkBoxFinished.setChecked(item.isFinished());
    }

    @Override
    public int getItemCount() {
        return itemsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView textViewTitle;
        public TextView textViewDescription;
        public CheckBox checkBoxImportant;
        public CheckBox checkBoxStarted;
        public CheckBox checkBoxFinished;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            textViewDescription = itemView.findViewById(R.id.textViewDescription);
            checkBoxImportant = itemView.findViewById(R.id.checkBoxImportant);
            checkBoxStarted = itemView.findViewById(R.id.checkBoxStarted);
            checkBoxFinished = itemView.findViewById(R.id.checkBoxFinished);
        }
    }



}