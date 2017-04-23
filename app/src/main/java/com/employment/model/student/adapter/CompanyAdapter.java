package com.employment.model.student.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.employment.R;
import com.employment.model.student.bean.Resume;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by roy on 2017/4/15.
 */

public class CompanyAdapter extends RecyclerView.Adapter<CompanyAdapter.MyViewHolder> {

    private List<Resume> resumes;
    private OnclickListener onclickListener;

    public interface OnclickListener {
        void onItemClick(View view, int position);
    }

    public CompanyAdapter(Context mContext, List<Resume> resumes) {
        this.resumes = resumes;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.fragment_company_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        Resume resume = resumes.get(position);
        holder.companyName.setText(resume.getCmRecruitByRid().getCmCompanyByCid().getCname() + "");
        holder.companyAddress.setText(resume.getCmRecruitByRid().getCmCompanyByCid().getCaddress() + "");
        holder.companyPosition.setText(resume.getCmRecruitByRid().getRjobName() + "");
        if (resume.getIsuccess() == 0) {
            holder.companyResumeStatus.setText("等待审核");
        } else if (resume.getIsuccess() == 1) {
            holder.companyResumeStatus.setText("请准备面试");
        } else if (resume.getIsuccess() == 2) {
            holder.companyResumeStatus.setText("面试通过，已报道");
        } else if (resume.getIsuccess() == 3) {
            holder.companyResumeStatus.setText("面试通过，未报道");
        } else if (resume.getIsuccess() == 4) {
            holder.companyResumeStatus.setText("面试未通过");
        }
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onclickListener.onItemClick(view, holder.getLayoutPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return resumes.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.company_layout)
        LinearLayout linearLayout;
        @BindView(R.id.company_company_name)
        TextView companyName;
        @BindView(R.id.company_position)
        TextView companyPosition;
        @BindView(R.id.company_address)
        TextView companyAddress;
        @BindView(R.id.company_resume_status)
        TextView companyResumeStatus;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public List<Resume> getResumes() {
        return resumes;
    }

    public void setResumes(List<Resume> resumes) {
        this.resumes = resumes;
        notifyDataSetChanged();
    }

    public void setOnclickListener(OnclickListener onclickListener) {
        this.onclickListener = onclickListener;
    }
}
