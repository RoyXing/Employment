package com.employment.model.student.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import com.employment.R;
import com.employment.app.Constants;
import com.employment.model.student.bean.TopicCommitBean;
import com.employment.utils.TimeUtils;
import com.employment.weight.RoundImageView;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by roy on 2016/12/24.
 */

public class CommunicationCommitAdapter extends ArrayAdapter<TopicCommitBean> {

    private Context context;
    private List<TopicCommitBean> list;

    public CommunicationCommitAdapter(Context context, int resource, List<TopicCommitBean> objects) {
        super(context, resource, objects);
        this.context = context;
        this.list = objects;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        HolderView holderView;
        if (convertView == null) {
            holderView = new HolderView();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_communication_commit, null);
            holderView.headImg = (RoundImageView) convertView.findViewById(R.id.user_img);
            holderView.userName = (TextView) convertView.findViewById(R.id.user_name);
            holderView.content = (TextView) convertView.findViewById(R.id.commit_content);
            holderView.time = (TextView) convertView.findViewById(R.id.commit_time);
            convertView.setTag(holderView);
        } else {
            holderView = (HolderView) convertView.getTag();
        }
        Picasso.with(context).load(Constants.HOST+"/"+list.get(position).getUserIcon()).placeholder(R.drawable.icon).error(R.drawable.icon).into(holderView.headImg);
        holderView.userName.setText(list.get(position).getUserName()+"("+(position+1)+"楼)");
        holderView.time.setText(TimeUtils.longToString(list.get(position).getCreateTime()));
        String parentName=getParentName(list.get(position));
        if (!parentName.equals("")) {
            holderView.content.setText("回复：" + parentName + "" + list.get(position).getContent());
            SpannableStringBuilder builder = new SpannableStringBuilder(holderView.content.getText().toString());
            //ForegroundColorSpan 为文字前景色，BackgroundColorSpan为文字背景色
            ForegroundColorSpan redSpan = new ForegroundColorSpan(Color.RED);
            builder.setSpan(redSpan,3,3+parentName.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            holderView.content.setText(builder);
        } else {
            holderView.content.setText(list.get(position).getContent());
        }
        return convertView;
    }

    class HolderView {
        RoundImageView headImg;
        TextView userName, content, time;

    }

    //寻找对谁的回复
    private String getParentName(TopicCommitBean bean) {
        String parentName = "";
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getCommentId().equals(bean.getParentId())) {
                parentName = list.get(i).getUserName()+"("+(i+1)+"楼)";
                break;
            }
        }
        return parentName;
    }
}
