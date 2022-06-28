package com.example.workmanagement;

import java.sql.SQLException;

public class Timer {
    long startTime;
    long endTime;
    long plusTime;
    int taskId;

    public Timer() {
        startTime = Long.valueOf(0);
        plusTime = Long.valueOf(0);
        taskId = -1;
    }

    public void setTaskId(int newTaskId) {
        this.taskId = newTaskId;
    }

    public int getTaskId() {
        return this.taskId;
    }

    public long getStartTime() {
        return startTime;
    }

    public void start() {
        startTime = System.currentTimeMillis();
    }

    public void end(int taskId) {
        if (startTime != 0) {
            endTime = System.currentTimeMillis();
            plusTime = endTime - startTime;
            startTime = Long.valueOf(0);
            MysqlConnection.addTime(taskId, plusTime);
        }
    }
}
