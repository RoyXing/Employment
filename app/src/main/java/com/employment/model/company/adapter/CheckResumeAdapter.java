package com.employment.model.company.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.employment.R;
import com.employment.model.company.bean.Interview;
import com.employment.model.student.bean.StudentInfo;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by roy on 2017/4/22.
 */

public class CheckResumeAdapter extends RecyclerView.Adapter<CheckResumeAdapter.MyViewHolder> {

    private List<Interview> interviews;
    private Context context;
    private OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public CheckResumeAdapter(Context context, List<Interview> interviews) {
        this.context = context;
        this.interviews = interviews;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).
                inflate(R.layout.fragment_check_resume_item, parent, false));
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        StudentInfo studentBySid = interviews.get(position).getCmStudentBySid();
        Glide.with(context).load(studentBySid.getSface()).into(holder.studentImage);
        holder.studentMajor.setText(studentBySid.getDepartment().getDepName());
        holder.studentName.setText(studentBySid.getSname());
        holder.studentPosition.setText(interviews.get(position).getCmRecruitByRid().getRjobName() + "");
        holder.studentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClickListener.onItemClick(view, holder.getLayoutPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return interviews.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.student_image)
        CircleImageView studentImage;
        @BindView(R.id.student_name)
        TextView studentName;
        @BindView(R.id.student_major)
        TextView studentMajor;
        @BindView(R.id.student_layout)
        LinearLayout studentLayout;
        @BindView(R.id.student_position)
        TextView studentPosition;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public List<Interview> getInterviews() {
        return interviews;
    }

    public void setInterviews(List<Interview> interviews) {
        this.interviews = interviews;
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
