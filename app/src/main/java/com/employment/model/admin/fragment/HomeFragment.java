package com.employment.model.admin.fragment;

import android.graphics.Color;
import android.support.v7.widget.AppCompatSpinner;
import android.text.SpannableString;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.employment.R;
import com.employment.base.BaseFragment;
import com.employment.model.admin.bean.Department;
import com.employment.model.student.bean.StudentInfo;
import com.employment.model.student.bean.UnEmployment;
import com.employment.presenter.HomePresenter;
import com.employment.presenter.contract.HomeContract;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by roy on 2017/3/28.
 */

public class HomeFragment extends BaseFragment<HomePresenter> implements HomeContract.View, AdapterView.OnItemSelectedListener {

    @BindView(R.id.home_spinner)
    AppCompatSpinner homeSpinner;
    @BindView(R.id.pie_chart)
    PieChart mPieChart;
    private List<String> departmentName;
    private List<Department> departments;
    private List<StudentInfo> infoList;

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_admin_home;
    }

    @Override
    protected void initEventAndData() {
        mPresenter.getAllDepartmentInfo();
        mPresenter.getAllStudentInfo();
        homeSpinner.setOnItemSelectedListener(this);

        mPieChart.setDragDecelerationFrictionCoef(0.95f);
        mPieChart.setDrawHoleEnabled(true);
        mPieChart.setHoleColor(Color.WHITE);
        mPieChart.setTransparentCircleColor(Color.WHITE);
        mPieChart.setTransparentCircleAlpha(110);
        mPieChart.setHoleRadius(58f);
        mPieChart.setTransparentCircleRadius(61f);
        mPieChart.setDrawCenterText(true);
        mPieChart.setRotationAngle(0);
        // 触摸旋转
        mPieChart.setRotationEnabled(true);
        mPieChart.setHighlightPerTapEnabled(true);
    }

    @Override
    public void showAllStudent(List<StudentInfo> infoList) {
        this.infoList = infoList;
        mPresenter.getAllUnEmploymentInfo();
    }

    @Override
    public void showAllDepartmentInfo(List<Department> departments) {
        this.departments = departments;
        departmentName = new ArrayList<>();
        departmentName.add("全部");
        for (Department department : departments) {
            departmentName.add(department.getDepName());
        }
        ArrayAdapter arrayAdapter = new ArrayAdapter(mContext, android.R.layout.simple_expandable_list_item_1, departmentName);
        homeSpinner.setAdapter(arrayAdapter);
    }

    @Override
    public void showUnEmploymentInfo(List<UnEmployment> unEmploymentList) {
        float i = (float) (infoList.size() - unEmploymentList.size()) / (float) infoList.size();
        List<PieEntry> list = new ArrayList<>();
        list.add(new PieEntry(i * 100, "就业 "));
        list.add(new PieEntry(((float) unEmploymentList.size() / (float) infoList.size()) * 100, "未就业 "));
        Description des = new Description();
        des.setText("未就业人数：" + unEmploymentList.size() + "  " + "就业人数：" + (infoList.size() - unEmploymentList.size()));
        setData(list, des, "2017届就业图");
    }

    @Override
    public void showAllUnEmploymentByDepartment(List<UnEmployment> unEmploymentList, String name) {
        float count = 0;
        for (Department department : departments) {
            if (department.getDepName().equals(name)) {
                count = (float) department.getStuCount();
            }
        }
        float i = (count - unEmploymentList.size()) / count;
        List<PieEntry> list = new ArrayList<>();
        list.add(new PieEntry(i * 100, "就业 "));
        list.add(new PieEntry(((float) unEmploymentList.size() / count) * 100, "未就业 "));
        Description des = new Description();
        des.setText("未就业人数：" + unEmploymentList.size() + "  " + "就业人数：" + (int) (count - unEmploymentList.size()));
        setData(list, des, "2017届" + name + "专业就业图");
    }

    private void setData(List<PieEntry> list, Description des, String s) {
        mPieChart.setCenterText(new SpannableString(s));
        PieDataSet dataSet = new PieDataSet(list, "");

        dataSet.setSliceSpace(3f);
        dataSet.setSelectionShift(5f);

        //数据和颜色
        ArrayList<Integer> colors = new ArrayList<>();
        for (int c : ColorTemplate.JOYFUL_COLORS)
            colors.add(c);
        for (int c : ColorTemplate.COLORFUL_COLORS)
            colors.add(c);
        for (int c : ColorTemplate.LIBERTY_COLORS)
            colors.add(c);
        for (int c : ColorTemplate.PASTEL_COLORS)
            colors.add(c);
        colors.add(ColorTemplate.getHoloBlue());
        dataSet.setColors(colors);
        PieData data = new PieData(dataSet);
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(11f);
        data.setValueTextColor(Color.WHITE);
        mPieChart.setData(data);
        mPieChart.highlightValues(null);
        des.setTextSize(Utils.convertDpToPixel(12.0F));
        mPieChart.setDescription(des);

        //刷新
        mPieChart.invalidate();

        mPieChart.animateY(1400, Easing.EasingOption.EaseInOutQuad);

        Legend l = mPieChart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        l.setDrawInside(false);
        l.setXEntrySpace(7f);
        l.setYEntrySpace(0f);
        l.setYOffset(0f);

        // 输入标签样式
        mPieChart.setEntryLabelColor(Color.WHITE);
        mPieChart.setEntryLabelTextSize(12f);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        if (departmentName.get(i).equals("全部")) {
            mPresenter.getAllUnEmploymentInfo();
        } else {
            mPresenter.getUnEmploymentByDepartment(departments.get(i - 1).getDid() + "", departmentName.get(i));
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
    }
}
