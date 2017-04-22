package com.employment.model.company.fragment;

import android.support.v7.widget.AppCompatSpinner;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.employment.R;
import com.employment.app.App;
import com.employment.base.BaseFragment;
import com.employment.presenter.PublishPresenter;
import com.employment.presenter.contract.PublishContract;
import com.employment.utils.SystemUtils;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by roy on 2017/3/28.
 */

public class PublishFragment extends BaseFragment<PublishPresenter> implements PublishContract.View {

    @BindView(R.id.recruit_position_name)
    EditText recruitPositionName;
    @BindView(R.id.recruit_position_sex)
    AppCompatSpinner recruitPositionSex;
    @BindView(R.id.recruit_position_number)
    EditText recruitPositionNumber;
    @BindView(R.id.recruit_position_startTime)
    TextView recruitPositionStartTime;
    @BindView(R.id.recruit_position_endTime)
    TextView recruitPositionEndTime;
    @BindView(R.id.recruit_position_type)
    AppCompatSpinner recruitPositionType;
    @BindView(R.id.recruit_position_salary)
    EditText recruitPositionSalary;
    @BindView(R.id.recruit_position_require)
    EditText recruitPositionRequire;
    @BindView(R.id.publish_recruit)
    Button publishRecruit;

    private String sex;
    private String type;

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_company_publish;
    }

    @Override
    protected void initEventAndData() {
        recruitPositionSex.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                sex = getResources().getStringArray(R.array.sex)[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        recruitPositionType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                type = getResources().getStringArray(R.array.type)[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    @Override
    public void setTime(String time, int type) {
        if (type == 0) {
            recruitPositionStartTime.setText(time);
        } else if (type == 1) {
            recruitPositionEndTime.setText(time);
        }
    }

    @Override
    public void publishSuccess() {
        SystemUtils.showToast(mContext,"发布成功！");
    }

    @OnClick({R.id.recruit_position_startTime, R.id.recruit_position_endTime, R.id.publish_recruit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.recruit_position_startTime:
                mPresenter.chooseTime(SystemUtils.string2Date(recruitPositionStartTime.getText().
                        toString()), 0);

                break;
            case R.id.recruit_position_endTime:
                mPresenter.chooseTime(SystemUtils.string2Date(recruitPositionEndTime.getText().
                        toString()), 1);
                break;
            case R.id.publish_recruit:
                HashMap<String, String> map = new HashMap<>();
                map.put("ejob_name", recruitPositionName.getText().toString());
                map.put("rsex", sex);
                map.put("rnum", recruitPositionNumber.getText().toString());
                map.put("rstart", recruitPositionStartTime.getText().toString());
                map.put("rend", recruitPositionEndTime.toString());
                map.put("rtype", type);
                map.put("rsalary", recruitPositionSalary.getText().toString());
                map.put("rsalary", recruitPositionRequire.getText().toString());
                map.put("companyId", App.getAppComponent().realmHelper().getCompanyInfoBean().getCid() + "");
                mPresenter.publishRecruit(map);
                break;
        }
    }
}
