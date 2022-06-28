package com.example.workmanagement;

public class Task {
    int taskId;
    // 0: 진행중, 1:종료
    int status;
    String userId;
    long startTime;
    long endTime;
    long spendTime;
    String content;
    Timer timer;

    Task() {
        startTime = System.currentTimeMillis();
        endTime = Long.valueOf(-1);
        spendTime = Long.valueOf(0);
        timer = new Timer();
    }

    public int getTaskId() {
        return this.taskId;
    }

    public int getStatus() {
        return this.status;
    }

    public void setStatus() {
        if(this.status == 0) {
            this.status = 1;
        }
        else if(this.status == 1) {
            this.status = 0;
        }
    }

    public Long getStartTime() {
        return this.startTime;
    }

    public Long getEndTime() {
        return this.endTime;
    }

    public Long getSpendTime() {
        return this.spendTime;
    }

    public void plusTime() {
        this.spendTime += 1000;
    }

    public String getContent() {
        return this.content.substring(1, (this.content).length() - 1);
    }

    public Timer getTimer() {
        return timer;
    }

    public void setEndTime(long time) {
          this.endTime = time;
    }

    public void updateTask(int taskId) {
        Task tempTask = MysqlConnection.getTask(taskId);
        this.status = tempTask.status;
        this.endTime = tempTask.endTime;
        this.spendTime = tempTask.spendTime;
    }

    public void updateStatus() {
        if(status == 0) {
            status = 1;
        }
        else {
            status = 0;
        }
    }
}
