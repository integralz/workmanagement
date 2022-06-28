package com.example.workmanagement;

import android.telecom.Call;

import java.util.ArrayList;
import java.util.Objects;


public class TaskManagement {
    static Boolean debugMode = false;

    public static void setDebugMode(boolean debug) {
        debugMode = debug;
    }

    public static void initialize(String url) {
        MysqlConnection.initialize(url);
    }

    public static void login(final String userId, final String password, final Callback callback) {

        new Thread(() -> {
            TaskManagementException taskManagementException = new TaskManagementException();

            if (MysqlConnection.checkUser(userId, password)) {
                taskManagementException.setError(1);
            } else {
                taskManagementException.setError(0);
            }

            callback.onCallBack(taskManagementException);
        }).start();
    }

    public static void checkId(final String userId, final Callback callback) {

        new Thread(() -> {
            TaskManagementException taskManagementException = new TaskManagementException();

            if (MysqlConnection.checkId(userId)) {
                taskManagementException.setError(3);
            } else {
                taskManagementException.setError(2);
            }

            callback.onCallBack(taskManagementException);
        }).start();
    }

    public static void makeAccount(final String userId, final String password, Callback callback) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                TaskManagementException taskManagementException = new TaskManagementException();

                if (MysqlConnection.makeAccount(userId, password)) {
                    taskManagementException.setError(4);
                } else {
                    taskManagementException.setError(5);
                }

                callback.onCallBack(taskManagementException);
            }
        }).start();

    }

    public static void listUp(final String userId, final Callback callback) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                TaskManagementException taskManagementException = new TaskManagementException();
                taskManagementException.setList(MysqlConnection.findTask(userId));
                callback.onCallBack(taskManagementException);
            }
        }).start();
    }

    public static void makeTask(final String userId, final String content, final Callback callback) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                TaskManagementException taskManagementException = new TaskManagementException();

                MysqlConnection.makeTask(userId, content);
                callback.onCallBack(taskManagementException);
            }
        }).start();
    }

    public static void deleteTask(final int taskId, final Callback callback) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                TaskManagementException taskManagementException = new TaskManagementException();

                MysqlConnection.deleteTask(taskId);
                callback.onCallBack(taskManagementException);
            }
        }).start();
    }

    public static void searchTask(final String userId, final String word, final Callback callback) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                TaskManagementException taskManagementException = new TaskManagementException();
                taskManagementException.setList(MysqlConnection.findWord(userId, word));
                callback.onCallBack(taskManagementException);
            }
        }).start();
    }

    public static void transferTaskStatus(final int taskId, final Callback callback) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                MysqlConnection.transferTaskStatus(taskId);
            }
        }).start();
    }
}
