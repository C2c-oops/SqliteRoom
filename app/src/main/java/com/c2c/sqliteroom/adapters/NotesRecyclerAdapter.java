package com.c2c.sqliteroom.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.c2c.sqliteroom.R;
import com.c2c.sqliteroom.models.Note;

import java.util.ArrayList;

class NotesRecyclerAdapter extends RecyclerView.Adapter<NotesRecyclerAdapter.ViewHolder> {

    private ArrayList<Note> mNotes = new ArrayList<>();

    public NotesRecyclerAdapter(ArrayList<Note> notes) {
        this.mNotes = notes;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_notes_list_item, parent, false);
        return new ViewHolder(view);
    }

    //called for every entry
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txt_title.setText(mNotes.get(position).getTitle());
        holder.txt_timeStamp.setText(mNotes.get(position).getTimeStamp());
    }

    //return size of all data in ArrayList
    @Override
    public int getItemCount() {
        return mNotes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView txt_title;
        private TextView txt_timeStamp;

        public ViewHolder(@NonNull View itemView){
            super(itemView);
            txt_title = itemView.findViewById(R.id.note_title);
            txt_timeStamp = itemView.findViewById(R.id.note_timeStamp);
        }
    }
}
