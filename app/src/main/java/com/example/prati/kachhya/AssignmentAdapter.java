package com.example.prati.kachhya;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class AssignmentAdapter extends RecyclerView.Adapter<AssignmentAdapter.ViewHolder> {

    RecyclerView recyclerView;
    Context context;
    ArrayList<String>items= new ArrayList<>();
    ArrayList<String>urls= new ArrayList<>();
    public void update(String name, String url){
        items.add(name);
        urls.add(url);
        notifyDataSetChanged(); //refresh automatically
    }
    public AssignmentAdapter(RecyclerView recyclerView,Context context,ArrayList<String> items,ArrayList<String> urls){
        this.recyclerView= recyclerView;
        this.context=context;
        this.items= items;
        this.urls= urls;
    }
    @NonNull
    @Override
    public AssignmentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        //to create view
        View view = LayoutInflater.from(context).inflate(R.layout.assignment_list_items,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AssignmentAdapter.ViewHolder holder, int position) {
        holder.nameofFile.setText(items.get(position));

    }

    @Override
    public int getItemCount() { // Returns no of items
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView nameofFile;

        public ViewHolder(final View itemView) {
            super(itemView);
            nameofFile = itemView.findViewById(R.id.assignmentsubject);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                        int position= recyclerView.getChildAdapterPosition(view);
                    Intent intent= new Intent();
                    intent.setType(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(urls.get(position)));
                    context.startActivity(intent);
                }
            });
        }
    }
}
