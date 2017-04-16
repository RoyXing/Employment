package com.employment.model.student.fragment;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.employment.R;
import com.employment.activity.NoteActivity;
import com.employment.base.BaseFragment;
import com.employment.model.student.adapter.NoteAdapter;
import com.employment.model.student.bean.Note;
import com.employment.presenter.NotePresenter;
import com.employment.presenter.contract.NoteContract;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by roy on 2017/3/28.
 */

public class NoteFragment extends BaseFragment<NotePresenter> implements NoteContract.View, NoteAdapter.OnClickListener, View.OnClickListener {

    @BindView(R.id.recyclerView_note)
    RecyclerView recyclerViewNote;
    @BindView(R.id.note_swipe)
    SwipeRefreshLayout noteSwipe;
    @BindView(R.id.note_edit)
    FloatingActionButton floatingActionButton;

    private NoteAdapter adapter;

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_note;
    }

    @Override
    protected void initEventAndData() {
        adapter = new NoteAdapter(mContext, new ArrayList<Note>());
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        layoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        layoutManager.setItemPrefetchEnabled(false);
        recyclerViewNote.setLayoutManager(layoutManager);
        recyclerViewNote.setAdapter(adapter);
        noteSwipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.fetchNoteInfo();
            }
        });
        adapter.setOnClickListener(this);
        recyclerViewNote.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy < 0) {
                    floatingActionButton.setVisibility(View.VISIBLE);
                } else if (dy > 0) {
                    floatingActionButton.setVisibility(View.GONE);
                }
            }
        });
        floatingActionButton.setOnClickListener(this);
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        mPresenter.fetchNoteInfo();
    }

    @Override
    public void onSupportVisible() {
        super.onSupportVisible();
        floatingActionButton.setVisibility(View.VISIBLE);
    }

    @Override
    public void showContent(List<Note> list) {
        if (noteSwipe.isRefreshing()) {
            noteSwipe.setRefreshing(false);
        }
        adapter.setList(list);
    }

    @Override
    public void showError(String msg) {
        if (noteSwipe.isRefreshing()) {
            noteSwipe.setRefreshing(false);
        }
    }

    @Override
    public void onItemClick(View view, int position) {
        Intent intent = new Intent(mContext, NoteActivity.class);
        intent.putExtra("noteId", adapter.getList().get(position).getNid() + "");
        intent.putExtra("title", adapter.getList().get(position).getTitle());
        intent.putExtra("content", adapter.getList().get(position).getContent());
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(mActivity, view, "shareView");
        mContext.startActivity(intent, options.toBundle());
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(mContext, NoteActivity.class);
        intent.putExtra("noteId", "");
        intent.putExtra("title", "");
        intent.putExtra("content", "");
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(mActivity, view, "shareView");
        mContext.startActivity(intent, options.toBundle());
    }
}

