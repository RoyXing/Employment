package com.employment.model.company.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.employment.R;
import com.employment.base.BaseFragment;
import com.employment.model.admin.bean.Department;
import com.employment.model.company.adapter.DepartAdapter;
import com.employment.presenter.CheckPresenter;
import com.employment.presenter.contract.CheckContract;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by roy on 2017/3/28.
 */

public class CheckFragment extends BaseFragment<CheckPresenter> implements CheckContract.View {

    @BindView(R.id.department_recyclerView)
    RecyclerView departmentRecyclerView;
    @BindView(R.id.swipe_departments)
    SwipeRefreshLayout swipeDepartments;
    private DepartAdapter departAdapter;

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_check;
    }

    @Override
    protected void initEventAndData() {
        LinearLayoutManager manager = new LinearLayoutManager(mContext);
        departAdapter = new DepartAdapter(mContext, new ArrayList<Department>());
        departmentRecyclerView.setLayoutManager(manager);
        departmentRecyclerView.setAdapter(departAdapter);

        swipeDepartments.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.getAllDepartment();
            }
        });
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        mPresenter.getAllDepartment();
    }

    @Override
    public void showAllDepartment(List<Department> departments) {
        if (swipeDepartments.isRefreshing())
            swipeDepartments.setRefreshing(false);
        departAdapter.setList(departments);
    }

    @Override
    public void showError(String msg) {
        super.showError(msg);
        if (swipeDepartments.isRefreshing())
            swipeDepartments.setRefreshing(false);
    }
}
