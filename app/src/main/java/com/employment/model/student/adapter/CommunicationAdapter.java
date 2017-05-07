package com.employment.model.student.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.employment.R;
import com.employment.app.Constants;
import com.employment.model.student.bean.JsonToBean;
import com.employment.model.student.bean.TopicBean;
import com.employment.utils.TimeUtils;
import com.employment.weight.RoundImageView;
import com.squareup.picasso.Picasso;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONObject;

import java.util.List;

import okhttp3.Call;

/**
 * Created by roy on 2016/12/21.
 */

public class CommunicationAdapter extends ArrayAdapter<TopicBean> {
    private Context context;
    private List<TopicBean> list;
    private int type;

    public CommunicationAdapter(Context context, int resource, List<TopicBean> objects) {
        super(context, resource, objects);
        this.context = context;
        this.list = objects;
        this.type = resource;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @NonNull
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        CommunicationHolder holder;
        if (convertView == null) {
            holder = new CommunicationHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_communication, null);
            holder.articleCommit = (TextView) convertView.findViewById(R.id.article_commit);
            holder.articleTitle = (TextView) convertView.findViewById(R.id.article_title);
            holder.articleContent = (TextView) convertView.findViewById(R.id.article_content);
            holder.userName = (TextView) convertView.findViewById(R.id.username);
            holder.articleTime = (TextView) convertView.findViewById(R.id.article_time);
            holder.btnOk = (TextView) convertView.findViewById(R.id.pass);
            holder.btnDelete = (TextView) convertView.findViewById(R.id.delete);
            holder.cardView = (CardView) convertView.findViewById(R.id.cardview);
            holder.userImg = (RoundImageView) convertView.findViewById(R.id.userimg);
            holder.rel = (RelativeLayout) convertView.findViewById(R.id.rel_com_number);
            holder.linearLayout = (LinearLayout) convertView.findViewById(R.id.line_admin);
            convertView.setTag(holder);
        } else {
            holder = (CommunicationHolder) convertView.getTag();
        }
        holder.userName.setText(list.get(position).getUserName());
        //holder.articleTitle.setText(list.get(position).getTitle());
        holder.articleTitle.setVisibility(View.GONE);
        holder.articleContent.setText(list.get(position).getContent());
        holder.articleCommit.setText("评论数：" + list.get(position).getCommentNum());
        holder.articleTime.setText(TimeUtils.longToString(list.get(position).getCreateTime()) + "");
        Picasso.with(context).load( list.get(position).getIcon()).placeholder(R.drawable.icon).error(R.drawable.icon).into(holder.userImg);

        holder.btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckCommunication(list.get(position).getId(), position);
                Toast.makeText(context, "正在请求", Toast.LENGTH_SHORT).show();
            }
        });
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delete(list.get(position).getTopicId(), position);
                Toast.makeText(context, "正在请求", Toast.LENGTH_SHORT).show();
            }
        });
        /*控制判定是否显示*/
        if (type == 0) {
            holder.rel.setVisibility(View.VISIBLE);
            holder.linearLayout.setVisibility(View.GONE);
        } else if (type == 1) {
            holder.rel.setVisibility(View.GONE);
            holder.linearLayout.setVisibility(View.VISIBLE);
        }

        return convertView;
    }

    public class CommunicationHolder {
        public RoundImageView userImg;
        public TextView userName, articleTitle, articleContent, articleCommit, articleTime, btnOk, btnDelete;
        public CardView cardView;
        public RelativeLayout rel;
        public LinearLayout linearLayout;
    }

    private void CheckCommunication(String id, final int pio) {
        OkHttpUtils
                .post()
                .url(Constants.PASS)
                .addParams("status", "1")
                .addParams("id", id)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Toast.makeText(context, "服务器错误", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            if (jsonObject.optInt("code") == 10000) {
                                Toast.makeText(context, "审核通过", Toast.LENGTH_SHORT).show();
                                list.remove(pio);
                                refresh.refresh();
                            } else {
                                Toast.makeText(context, "审核未通过", Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }


                    }

                });
    }

    private void delete(String id, final int pio) {
        OkHttpUtils
                .post()
                .url(Constants.DELETE + id)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Toast.makeText(context, "服务器错误", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            if (jsonObject.optInt("code") == 10000) {
                                Toast.makeText(context, "删除成功", Toast.LENGTH_SHORT).show();
                                list.remove(pio);
                                refresh.refresh();
                            } else {
                                Toast.makeText(context, "删除失败", Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }


                    }

                });
    }

    public interface Refresh {
        void refresh();
    }

    private Refresh refresh;

    public void setRefresh(Refresh refresh) {
        this.refresh = refresh;
    }
}
