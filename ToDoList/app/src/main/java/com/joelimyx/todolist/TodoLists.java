package com.joelimyx.todolist;

import java.util.HashMap;
import java.util.LinkedList;

/**
 * Created by Joe on 10/18/16.
 */
public class TodoLists {
    private static TodoLists todoLists = new TodoLists();
    private static LinkedList<String> mNameList;
    private static HashMap<String, LinkedList<DetailItem>> mDetailList;

    public static TodoLists getInstance() {
        return todoLists;
    }

    private TodoLists(){
        mNameList = new LinkedList<>();
        mDetailList = new HashMap<>();
    }

    public void createListByName(String name){
        mNameList.add(name);
        mDetailList.put(name, new LinkedList<DetailItem>());
    }

    public LinkedList<String> getmNameList() {
        return mNameList;
    }

    public LinkedList<DetailItem> getDetailListByName(String name){
        return mDetailList.get(name);
    }

    public static HashMap<String, LinkedList<DetailItem>> getMap() {
        return mDetailList;
    }
    public void removeDetailListByPosition(int pos){
        mDetailList.remove(mNameList.get(pos));
        mNameList.remove(pos);
    }
}