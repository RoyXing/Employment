package com.employment.model.student.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.employment.R;
import com.employment.model.student.bean.Note;

import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by roy on 2017/4/16.
 */

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.MyViewHolder> {

    private Context mContext;
    private List<Note> list;
    private OnClickListener onClickListener;
    private int color[] = {R.color.color_FF00FF, R.color.color_70DB93};

    public interface OnClickListener {
        void onItemClick(View view, int position);
    }

    public NoteAdapter(Context context, List<Note> list) {
        this.mContext = context;
        this.list = list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_note_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        Note note = list.get(position);
        holder.noteTitle.setText(note.getTitle());
        holder.noteContent.setText(note.getContent());
//        holder.linearLayout.setBackgroundColor(color[new Random().nextInt(color.length)]);
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickListener.onItemClick(view, holder.getLayoutPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.note_title)
        TextView noteTitle;
        @BindView(R.id.note_content)
        TextView noteContent;
        @BindView(R.id.note_layout)
        CardView linearLayout;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public List<Note> getList() {
        return list;
    }

    public void setList(List<Note> list) {
        this.list = list;
        notifyDataSetChanged();
    }
}
