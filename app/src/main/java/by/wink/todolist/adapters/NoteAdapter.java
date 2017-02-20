package by.wink.todolist.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.zip.Inflater;

import by.wink.todolist.R;
import by.wink.todolist.activities.MainActivity;
import by.wink.todolist.models.Note;

/**
 * Created by amine on 20/02/17.
 */

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder> {
    Context context;
    ArrayList<Note> notes = new ArrayList<>();
    private int position;

    @Override
    public NoteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_note, parent, false);
        return new NoteViewHolder(v);
    }

    public NoteAdapter(Context c) {
        context = c;
    }

    public void addNote(Note note) {
        notes.add(0, note);
        notifyItemInserted(0);
    }

    public int getPosition() {
        return position;
    }
    public void setPosition(int position) {
        this.position = position;
    }

    public void updateNote(Note note, int position) {
        notes.set(position,note);
    }

    @Override
    public void onBindViewHolder(NoteViewHolder holder, final int position) {
        Note note = notes.get(position);
        holder.noteTitle.setText(note.getTitle());
        holder.noteBody.setText(note.getBody());
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                setPosition(position);
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public void removeNote(int position) {
        notes.remove(position);

    }


    public Note getNote(int position) {
        return notes.get(position);
    }




    class NoteViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {
        TextView noteTitle, noteBody;

        NoteViewHolder(View itemView) {
            super(itemView);
            noteTitle = (TextView) itemView.findViewById(R.id.note_title);
            noteBody = (TextView) itemView.findViewById(R.id.note_body);
            itemView.setOnCreateContextMenuListener(this);
        }

        @Override
        public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
            MenuInflater inflater = ((MainActivity) context).getMenuInflater();
            inflater.inflate(R.menu.menu_note, contextMenu);
        }


    }
}
