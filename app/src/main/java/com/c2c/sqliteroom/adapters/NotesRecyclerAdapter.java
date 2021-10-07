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

public class NotesRecyclerAdapter extends RecyclerView.Adapter<NotesRecyclerAdapter.ViewHolder> {

    private ArrayList<Note> mNotes = new ArrayList<>();
    private OnNoteListener mOnNoteListener;

    public NotesRecyclerAdapter(ArrayList<Note> notes, OnNoteListener onNoteListener) {
        this.mNotes = notes;
        this.mOnNoteListener = onNoteListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_notes_list_item, parent, false);
        return new ViewHolder(view, mOnNoteListener);
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

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView txt_title;
        private TextView txt_timeStamp;

        OnNoteListener onNoteListener;

        public ViewHolder(@NonNull View itemView, OnNoteListener onNoteListener){
            super(itemView);
            txt_title = itemView.findViewById(R.id.note_title);
            txt_timeStamp = itemView.findViewById(R.id.note_timeStamp);

            this.onNoteListener = onNoteListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onNoteListener.onNoteClick(getBindingAdapterPosition());
        }
    }

    public interface OnNoteListener{
        void onNoteClick(int position);
    }
}
