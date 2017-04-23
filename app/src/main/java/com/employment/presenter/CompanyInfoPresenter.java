package com.employment.presenter;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.employment.R;
import com.employment.base.RxPresenter;
import com.employment.base.RxUtil;
import com.employment.db.RealmHelper;
import com.employment.http.RetrofitHelper;
import com.employment.http.bean.ResponseBean;
import com.employment.presenter.contract.CompanyInfoContract;
import com.employment.utils.SystemUtils;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import javax.inject.Inject;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

/**
 * Created by roy on 2017/4/9.
 */

public class CompanyInfoPresenter extends RxPresenter<CompanyInfoContract.View> implements CompanyInfoContract.Presenter {

    private RetrofitHelper mRetrofitHelper;
    private RealmHelper realmHelper;
    private DatePickerDialog dialog;
    private PopupWindow popupWindow;
    private Activity mContext;

    @Inject
    public CompanyInfoPresenter(RetrofitHelper mRetrofitHelper, RealmHelper realmHelper, Activity activity) {
        this.mRetrofitHelper = mRetrofitHelper;
        this.realmHelper = realmHelper;
        this.mContext = activity;
    }

    @Override
    public void getCompanyInfo() {
        mView.showContent(realmHelper.getCompanyInfoBean());
    }

    @Override
    public void setTime(long date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(date));
        dialog = new DatePickerDialog(mContext, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                Calendar instance = Calendar.getInstance();
                instance.set(i, i1, i2);
                Date newDate = instance.getTime();
                realmHelper.setCompanyBuildTile(SystemUtils.formatTime(newDate));
                mView.showBuildTime(SystemUtils.formatTime(newDate));
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE));
        dialog.show();
    }

    @Override
    public void modifyInfo(LinearLayout layout, final String info, final String type) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.popup_layout, null);
        final EditText textView = (EditText) view.findViewById(R.id.input_text);
        TextView title = (TextView) view.findViewById(R.id.popup_title);
        textView.setText(info);
        textView.setSelection(info.length());
        final Button cancel = (Button) view.findViewById(R.id.cancel);
        Button ok = (Button) view.findViewById(R.id.ok);
        popupWindow = new PopupWindow(view, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow.dismiss();
            }
        });
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newInfo = textView.getText().toString();
                if (!newInfo.equals(info) && !newInfo.isEmpty()) {
                    realmHelper.setCompanyInfo(newInfo, type);
                    mView.showModifyInfo(newInfo, type);
                    popupWindow.dismiss();
                }
            }
        });
        String titleInfo = "";
        switch (type) {
            case "0":
                titleInfo = "更新地址";
                break;
            case "1":
                titleInfo = "更新电话";
                break;
            case "2":
                titleInfo = "更新邮箱";
                break;
            case "3":
                titleInfo = "更新负责人";
                break;
            case "4":
                textView.setMinHeight(10);
                textView.setMinEms(10);
                textView.setGravity(Gravity.LEFT | Gravity.TOP);
                titleInfo = "更新简介";
                break;
        }
        title.setText(titleInfo);
        popupWindow.setBackgroundDrawable(new ColorDrawable(0X00ffffff));
        backgroundAlpha(0.5f);
        popupWindow.setOutsideTouchable(true);// 非popwindow区域可触摸
        popupWindow.setOnDismissListener(new PopDismissListener());
        popupWindow.setFocusable(true);//可获取焦点
        popupWindow.setTouchable(true);// 设置可触摸
        popupWindow.showAtLocation(layout, Gravity.CENTER, 0, 0);
    }

    @Override
    public void commitCompanyInfo() {
        HashMap<String, String> map = new HashMap<>();
        map.put("companyId", realmHelper.getCompanyInfoBean().getCid() + "");
        map.put("caddress", realmHelper.getCompanyInfoBean().getCaddress());
        map.put("ctime", realmHelper.getCompanyInfoBean().getCtime());
        map.put("cphone", realmHelper.getCompanyInfoBean().getCphone());
        map.put("cemail", realmHelper.getCompanyInfoBean().getCemail());
        map.put("chr", realmHelper.getCompanyInfoBean().getChr());
        map.put("cinfo", realmHelper.getCompanyInfoBean().getCinfo());
        mRetrofitHelper.commitCompanyInfo(map)
                .compose(RxUtil.<ResponseBean>rxSchedulerHelper())
                .subscribe(new Consumer<ResponseBean>() {
                    @Override
                    public void accept(@NonNull ResponseBean responseBean) throws Exception {
                        if (responseBean.getCode() == 200) {
                            Log.e("roy", "更新成功");

                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {

                    }
                });
    }

    /**
     * 设置添加屏幕的背景透明度
     *
     * @param bgAlpha
     */
    public void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = mContext.getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        mContext.getWindow().setAttributes(lp);
    }

    /**
     * 添加新笔记时弹出的popWin关闭的事件，主要是为了将背景透明度改回来
     */
    class PopDismissListener implements PopupWindow.OnDismissListener {

        @Override
        public void onDismiss() {
            backgroundAlpha(1f);
        }
    }

    @Override
    public void detachView() {
        super.detachView();
        if (dialog != null && dialog.isShowing()) {
            dialog.hide();
            dialog = null;
        }
        if (popupWindow != null && popupWindow.isShowing()) {
            popupWindow.dismiss();
            popupWindow = null;
        }
    }
}
