package com.joelimyx.todolist;

/**
 * Created by Joe on 10/18/16.
 */

public class DetailItem {
    private String mTitle, mDetail;
    private boolean isDone;

    public DetailItem(String title, String detail) {
        mTitle = title;
        mDetail = detail;
        isDone = false;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getDetail() {
        return mDetail;
    }

    public void setDetail(String detail) {
        mDetail = detail;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }
}
