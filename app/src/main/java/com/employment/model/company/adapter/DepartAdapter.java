package com.employment.model.company.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.employment.R;
import com.employment.model.admin.bean.Department;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by roy on 2017/4/22.
 */

public class DepartAdapter extends RecyclerView.Adapter<DepartAdapter.MyViewHolder> {

    private List<Department> list;

    public DepartAdapter(Context context, List<Department> list) {
        this.list = list;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).
                inflate(R.layout.fragment_check_item, parent, false));
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.departmentDesc.setText(list.get(position).getDescription() + "");
        holder.departmentName.setText(list.get(position).getDepName() + "");
        holder.departmentCount.setText(list.get(position).getStuCount() + "");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.department_name)
        TextView departmentName;
        @BindView(R.id.department_desc)
        TextView departmentDesc;
        @BindView(R.id.department_count)
        TextView departmentCount;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public List<Department> getList() {
        return list;
    }

    public void setList(List<Department> list) {
        this.list = list;
        notifyDataSetChanged();
    }
}
