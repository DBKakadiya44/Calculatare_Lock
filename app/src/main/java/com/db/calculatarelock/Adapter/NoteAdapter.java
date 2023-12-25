package com.db.calculatarelock.Adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.db.calculatarelock.R;
import com.db.calculatarelock.nav_Activitys.ContactActivity;
import com.db.calculatarelock.nav_Activitys.NotesActivity;

import java.util.ArrayList;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.ViewHolder> {
    NotesActivity notesActivity;
    ArrayList retrievedList2;
    public NoteAdapter(NotesActivity notesActivity, ArrayList retrievedList2) {
        this.notesActivity=notesActivity;
        this.retrievedList2=retrievedList2;
    }

    @NonNull
    @Override
    public NoteAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(notesActivity).inflate(R.layout.item_notes,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteAdapter.ViewHolder holder, int position) {
        holder.textView.setText(""+retrievedList2.get(position));
        holder.number.setText((position+1)+".");

        holder.menu.setOnClickListener(view -> {
            NotesActivity.removeItemFromArrayList(notesActivity, position);
            notesActivity.startActivity(new Intent(notesActivity, NotesActivity.class));
            notesActivity.finish();
        });
    }

    @Override
    public int getItemCount() {
        return retrievedList2.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView,number;
        ImageView menu;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.itemnote);
            number = itemView.findViewById(R.id.notenumber);
            menu = itemView.findViewById(R.id.notemenu);
        }
    }
}
