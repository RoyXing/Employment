package com.employment.activity;

import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.employment.R;
import com.employment.base.BaseActivity;
import com.employment.base.RxBus;
import com.employment.model.student.event.NoteEvent;
import com.employment.presenter.NoteEditPresenter;
import com.employment.presenter.contract.NoteEditContract;
import com.employment.utils.SystemUtils;

import butterknife.BindView;

/**
 * Created by roy on 2017/4/16.
 */

public class NoteActivity extends BaseActivity<NoteEditPresenter> implements NoteEditContract.View {

    @BindView(R.id.note_edit_title)
    EditText noteEditTitle;
    @BindView(R.id.note_edit_content)
    EditText noteEditContent;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private String noteId;
    private String title;
    private String content;

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_note;
    }

    @Override
    protected void initEventAndData() {
        setToolBar(toolbar, "");
        title = getIntent().getStringExtra("title") + "";
        content = getIntent().getStringExtra("content") + "";
        noteEditTitle.setText(title);
        noteEditContent.setText(content);
        noteId = getIntent().getStringExtra("noteId");
    }

    @Override
    public void addSuccess() {
        RxBus.getInstance().post(new NoteEvent(1));
        finish();
    }

    @Override
    public void deleteSuccess() {
        SystemUtils.showToast(this, "删除成功");
        RxBus.getInstance().post(new NoteEvent(1));
        finish();
    }

    @Override
    public void modifySuccess() {
        SystemUtils.showToast(this, "更新成功");
        RxBus.getInstance().post(new NoteEvent(1));
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.note_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_delete:
                if (!noteId.isEmpty() &&
                        (!(noteEditTitle.getText().toString()).isEmpty() ||
                                !(noteEditContent.getText().toString()).isEmpty()))
                    mPresenter.deleteNote(noteId);
                break;
            case R.id.action_store:
                if (!content.equals(noteEditContent.getText().toString()) || !title.equals(noteEditTitle.getText().toString())) {
                    if (!noteId.isEmpty() &&
                            (!(noteEditTitle.getText().toString()).isEmpty() ||
                                    !(noteEditContent.getText().toString()).isEmpty()))
                        mPresenter.modifyNote(noteId, noteEditTitle.getText().toString(), noteEditContent.getText().toString());
                    else if (!(noteEditTitle.getText().toString()).isEmpty() ||
                            !(noteEditContent.getText().toString()).isEmpty())
                        mPresenter.addNote(noteEditTitle.getText().toString(), noteEditContent.getText().toString());
                    else
                        finish();
                } else
                    finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressedSupport() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
            pop();
        } else {
            finishAfterTransition();
        }
    }
}
