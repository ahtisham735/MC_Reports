package com.example.makenotes;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NotesViewHolder> {

    public static class NotesViewHolder extends RecyclerView.ViewHolder{

        LinearLayout container;
        TextView textView;
        NotesViewHolder(View view){
            super(view);
            container=view.findViewById(R.id.note_row);
            textView=view.findViewById((R.id.notes_row_text));
            container.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(v.getContext(),MainActivity2.class);
                    Note obj=(Note)container.getTag();
                    intent.putExtra("content",obj.contents);
                    intent.putExtra("id",obj.id);
                    v.getContext().startActivity(intent);
                }
            });


        }

    }
    public List<Note> noteList=new ArrayList<Note>();
    @NonNull
    @Override
    public NotesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.notes_row,parent,false);
        return new NotesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotesViewHolder holder, int position) {
        Note current=noteList.get(position);
        holder.container.setTag(current);
        holder.textView.setText(current.contents);
    }

    @Override
    public int getItemCount() {
        return noteList.size();
    }
    public void reload(){
        noteList=MainActivity.database.noteDAO().getAllNotes();
        notifyDataSetChanged();
    }



}
