package com.employment.model.student.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.employment.R;
import com.employment.model.student.bean.Recruit;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by roy on 2017/4/9.
 */

public class EmploymentAdapter extends RecyclerView.Adapter<EmploymentAdapter.MyViewHolder> {

    private List<Recruit> recruits;
    private OnclickListener onclickListener;
    private Context mContext;

    public interface OnclickListener {
        void onItemClick(View view, int position);
    }

    public EmploymentAdapter(Context mContext, List<Recruit> recruits) {
        this.recruits = recruits;
        this.mContext = mContext;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_employment_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {

        Recruit recruit = recruits.get(position);
        holder.employmentPosition.setText(recruit.getRjobName()+"");
        holder.employmentCompanyName.setText(recruit.getRjobName() + "");
        holder.employmentAddress.setText(recruit.getCmCompanyByCid().getCaddress() + "");
        holder.employmentSalary.setText(recruit.getRsalary() + "元");
        Glide.with(mContext).load(recruit.getCmCompanyByCid().getCface()).into(holder.companyChrImage);
        holder.companyChrName.setText(recruit.getCmCompanyByCid().getChr());
        holder.employmentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pos = holder.getLayoutPosition();
                if (onclickListener != null)
                    onclickListener.onItemClick(view, pos);
            }
        });
    }

    @Override
    public int getItemCount() {
        return recruits.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.employment_position)
        TextView employmentPosition;
        @BindView(R.id.employment_salary)
        TextView employmentSalary;
        @BindView(R.id.employment_company_name)
        TextView employmentCompanyName;
        @BindView(R.id.employment_address)
        TextView employmentAddress;
        @BindView(R.id.company_chr_image)
        CircleImageView companyChrImage;
        @BindView(R.id.company_chr_name)
        TextView companyChrName;
        @BindView(R.id.employment_layout)
        LinearLayout employmentLayout;

        MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public void setRecruits(List<Recruit> recruits) {
        this.recruits = recruits;
        notifyDataSetChanged();
    }

    public List<Recruit> getRecruits() {
        return recruits;
    }

    public void setOnclickListener(OnclickListener onclickListener) {
        this.onclickListener = onclickListener;
    }
}
