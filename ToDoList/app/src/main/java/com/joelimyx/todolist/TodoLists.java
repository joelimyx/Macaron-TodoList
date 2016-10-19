package com.joelimyx.todolist;

import java.util.HashMap;
import java.util.LinkedList;

/**
 * Created by Joe on 10/18/16.
 */
public class TodoLists {
    private static TodoLists todoLists = new TodoLists();
    private static LinkedList<String> mNameList;
    private static HashMap<String, LinkedList<DetailList>> mDetialList;

    public static TodoLists getInstance() {
        return todoLists;
    }

    private TodoLists(){
        mNameList = new LinkedList<>();
        mDetialList = new HashMap<>();
    }

    public void createListByName(String name){
        mNameList.add(name);
        mDetialList.put(name, new LinkedList<DetailList>());
    }

    public LinkedList<String> getmNameList() {
        return mNameList;
    }

    public void setmNameList(LinkedList<String> mNameList) {
        TodoLists.mNameList = mNameList;
    }

    public static HashMap<String, LinkedList<DetailList>> getmDetialList() {
        return mDetialList;
    }
}
