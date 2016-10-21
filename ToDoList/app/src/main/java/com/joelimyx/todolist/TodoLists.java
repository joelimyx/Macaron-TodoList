package com.joelimyx.todolist;

import java.util.HashMap;
import java.util.LinkedList;

/**
 * Created by Joe on 10/18/16.
 */
public class TodoLists{
    private static TodoLists todoLists = new TodoLists();
    private static LinkedList<String> mNameList;
    private static HashMap<String, LinkedList<DetailItem>> mDetailList;

    public static TodoLists getInstance() {
        return todoLists;
    }

    private TodoLists() {
        mNameList = new LinkedList<>();
        mDetailList = new HashMap<>();
    }
    public void retrieveData(TodoLists data){
        todoLists = data;
    }

    public void createListByName(String name) {
        mNameList.add(name);
        mDetailList.put(name, new LinkedList<DetailItem>());
    }

    public LinkedList<String> getmNameList() {
        return mNameList;
    }

    public LinkedList<DetailItem> getDetailListByName(String name) {
        return mDetailList.get(name);
    }

    public static HashMap<String, LinkedList<DetailItem>> getMap() {
        return mDetailList;
    }

    public LinkedList<DetailItem> removeDetailListByPosition(int pos) {
        LinkedList<DetailItem> temp = mDetailList.remove(mNameList.get(pos));
        mNameList.remove(pos);
        return temp;
    }

    public void restoreList(String name, LinkedList<DetailItem> restore, int pos) {
        mNameList.add(pos, name);
        mDetailList.put(name, restore);
    }
}
