package com.example.workmanagement;

import java.util.ArrayList;

public class TaskManagementException {
    ArrayList<Task> taskList;
    int error;

    TaskManagementException() {
        this.error = -1;
        taskList = new ArrayList<>();
    }

    public void setError(int code) {
        this.error = code;
    }

    public int getError() {
        return this.error;
    }

    public void setList(ArrayList <Task> taskList) {
        this.taskList = taskList;
    }

    public ArrayList<Task> getList() {
        return this.taskList;
    }
}
