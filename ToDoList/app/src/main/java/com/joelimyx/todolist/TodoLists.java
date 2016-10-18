package com.joelimyx.todolist;

import java.util.LinkedList;

/**
 * Created by Joe on 10/18/16.
 */
public class TodoLists {
    private static TodoLists ourInstance = new TodoLists();
    private static LinkedList<String> mListsName;
    private static LinkedList<DetailList> mDetailList;

    public static TodoLists getInstance() {
        return ourInstance;
    }

    public TodoLists() {
        mListsName = new LinkedList<>();
        mDetailList = new LinkedList<>();
    }

    public static LinkedList<String> getmListsName() {
        return mListsName;
    }

    public static void setmListsName(LinkedList<String> mListsName) {
        TodoLists.mListsName = mListsName;
    }

    public static LinkedList<DetailList> getmDetailList() {
        return mDetailList;
    }

    public static void setmDetailList(LinkedList<DetailList> mDetailList) {
        TodoLists.mDetailList = mDetailList;
    }
}
