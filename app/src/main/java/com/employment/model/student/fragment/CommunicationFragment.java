package com.employment.model.student.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.employment.R;
import com.employment.app.App;
import com.employment.app.Constants;
import com.employment.model.student.activity.CommunicationDatialsActivity;
import com.employment.model.student.activity.WriteCommunicationActivity;
import com.employment.model.student.adapter.CommunicationAdapter;
import com.employment.model.student.bean.JsonToBean;
import com.employment.model.student.bean.TopicBean;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;


/**
 * Created by roy on 2016/12/16.
 * 社区
 */

public class CommunicationFragment extends Fragment implements CommunicationAdapter.Refresh {

    private SwipeRefreshLayout refreshLayout;
    private ListView listView;
    private View footview;
    private List<TopicBean> list;
    private CommunicationAdapter adapter;
    private ImageView write;
    int page = 0;
    int positon;//点击的位置
    int how;//那个界面，界面传过来的；
    String urls = "";//多个界面，根据how动态改变url
    String userid = "";//需要传送的userid
    private String status;//加载待审核或审核使用

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_layout_commincation, null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        refreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.refresh);
        listView = (ListView) view.findViewById(R.id.listview_communitcation);
        footview = LayoutInflater.from(getActivity()).inflate(R.layout.footview, null);
        write = (ImageView) view.findViewById(R.id.img_write);
        write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), WriteCommunicationActivity.class);
                getActivity().startActivity(intent);
            }
        });
        getHow();
        listener();
    }

    //根据过来的界面加以处理
    private void getHow() {
        how = Integer.parseInt(getArguments().getString(Constants.COMMUNICATION));
        switch (how) {
            //我的发帖
            case 1:
                urls = Constants.MY_WRITE;
                userid = App.getAppComponent().realmHelper().getStudentInfoBean().getSid() + "";
                break;
            //与我相关
            case 2:
                urls = Constants.MY_COMMINT;
                userid = App.getAppComponent().realmHelper().getStudentInfoBean().getSid() + "";
                break;
            //话题列表
            case 0:
                urls = Constants.TOPIC_LIST;
                userid = "";
                write.setVisibility(View.VISIBLE);
                break;
            case 3:
                urls = Constants.CHECK_PASS;
                userid = App.getAppComponent().realmHelper().getAdminInfoBean().getUid() + "";
                status = "0";
                break;
            case 4:
                urls = Constants.CHECK_PASS;
                userid = App.getAppComponent().realmHelper().getAdminInfoBean().getUid() + "";
                status = "1";
                break;
        }
        list = new ArrayList<>();
        if (how == 0 || how == 1 || how == 2 || how == 4) {
            adapter = new CommunicationAdapter(getContext(), 0, list);
        } else {
            adapter = new CommunicationAdapter(getContext(), 1, list);
        }
        adapter.setRefresh(this);
        listView.setAdapter(adapter);
        listView.addFooterView(footview);
        getList();
    }

    private void listener() {
        if (how == 0 || how == 1 || how == 2) {
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    positon = position;
                    Intent intent = new Intent(getActivity(), CommunicationDatialsActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("bean", list.get(position));
                    intent.putExtra("intent", bundle);
                    startActivityForResult(intent, 1000);
                }
            });
        }
        //刷新
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                page = 0;
                getList();

            }
        });
        //加载更多
        footview.findViewById(R.id.cardview_foot).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getList();
            }
        });
    }

    /**
     * 进行数据下载,用于我的发帖和与我相关,else是帖子列表
     */
    private void getList() {
        refreshLayout.setRefreshing(true);
        if (how == 1 || how == 2) {
            OkHttpUtils
                    .post()
                    .url(urls)
                    .addParams("page", page + "")
                    .addParams("userId", userid)
                    .addParams("status", "1")
                    .build()
                    .execute(new StringCallback() {
                        @Override
                        public void onError(Call call, Exception e, int id) {
                            refreshLayout.setRefreshing(false);
                        }

                        @Override
                        public void onResponse(String response, int id) {
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                if (jsonObject.optInt("code") == 10000) {
                                    List<TopicBean> topicBeanList = JsonToBean.getBeans(jsonObject.opt("response").toString(), TopicBean.class);
                                    listView.removeFooterView(footview);
                                    if (page == 0)
                                        list.clear();
                                    if (topicBeanList.size() >= 10) {
                                        page++;
                                        listView.addFooterView(footview);
                                    } else {
                                        listView.removeFooterView(footview);
                                    }
                                    list.addAll(topicBeanList);
                                    adapter.notifyDataSetChanged();
                                    if (topicBeanList != null && topicBeanList.size() == 0) {
                                        Toast.makeText(getActivity(), "没有帖子", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            refreshLayout.setRefreshing(false);

                        }

                    });
        } else if (how == 0) {
            OkHttpUtils
                    .post()
                    .url(urls)
                    .addParams("page", page + "")
                    .addParams("status", "1")
                    .build()
                    .execute(new StringCallback() {
                        @Override
                        public void onError(Call call, Exception e, int id) {
                            refreshLayout.setRefreshing(false);
                        }

                        @Override
                        public void onResponse(String response, int id) {
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                if (jsonObject.optInt("code") == 10000) {
                                    List<TopicBean> topicBeanList = JsonToBean.getBeans(jsonObject.opt("response").toString(), TopicBean.class);
                                    listView.removeFooterView(footview);
                                    if (page == 0)
                                        list.clear();
                                    if (topicBeanList.size() >= 10) {
                                        page++;
                                        listView.addFooterView(footview);
                                    } else {
                                        listView.removeFooterView(footview);
                                    }
                                    list.addAll(topicBeanList);
                                    adapter.notifyDataSetChanged();
                                    if (topicBeanList != null && topicBeanList.size() == 0) {
                                        Toast.makeText(getActivity(), "没有帖子", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            refreshLayout.setRefreshing(false);

                        }

                    });
        } else if (how == 3 || how == 4) {
            OkHttpUtils
                    .post()
                    .url(urls)
                    .addParams("page", page + "")
                    .addParams("status", status)
                    .build()
                    .execute(new StringCallback() {
                        @Override
                        public void onError(Call call, Exception e, int id) {
                            refreshLayout.setRefreshing(false);
                        }

                        @Override
                        public void onResponse(String response, int id) {
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                if (jsonObject.optInt("code") == 10000) {
                                    List<TopicBean> topicBeanList = JsonToBean.getBeans(jsonObject.opt("response").toString(), TopicBean.class);
                                    listView.removeFooterView(footview);
                                    if (page == 0)
                                        list.clear();
                                    if (topicBeanList.size() >= 10) {
                                        page++;
                                        listView.addFooterView(footview);
                                    } else {
                                        listView.removeFooterView(footview);
                                    }
                                    list.addAll(topicBeanList);
                                    adapter.notifyDataSetChanged();
                                    if (topicBeanList != null && topicBeanList.size() == 0) {
                                        Toast.makeText(getActivity(), "没有帖子", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            refreshLayout.setRefreshing(false);

                        }

                    });
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1000 && resultCode == Activity.RESULT_OK) {
            if (data != null) {
                list.get(positon).setCommentNum(data.getStringExtra("number"));
                adapter.notifyDataSetChanged();
            }
        }

    }


    @Override
    public void refresh() {
        adapter.notifyDataSetChanged();
    }
}
