package com.example.workmanagement;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.util.ArrayList;
import java.util.Objects;

public class MysqlConnection {
    static String url = "http://125.6.40.70:8000/";

    public static void initialize(String mainUrl) {
        url = mainUrl;
    }

    public static boolean checkUser(String userId, String password) {
        String requestUrl = url + "login?id=" + userId + "&password=" + password;
        String answer = Connect.connect(requestUrl);

        if (Objects.equals(answer, "1\n")) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean checkId(String userId) {
        String requestUrl = url + "checkid?id=" + userId;
        String answer = Connect.connect(requestUrl);

        if (Objects.equals(answer, "0\n")) {
            return false;
        } else {
            return true;
        }
    }

    public static boolean makeAccount(String userId, String password) {
        String requestUrl = url + "signup?id=" + userId + "&password=" + password;
        String answer = Connect.connect(requestUrl);

        if (Objects.equals(answer, "0\n")) {
            return false;
        } else {
            return true;
        }

    }

    public static ArrayList<Task> findTask(String userId) {
        ArrayList<Task> tasks = new ArrayList<>();
        String requestUrl = url + "listup?id=" + userId;
        String answer = Connect.connect(requestUrl);

        JsonParser jsonParser = new JsonParser();
        JsonArray json = (JsonArray) jsonParser.parse(answer);

        for (int i = 0; i < json.size(); ++i) {
            JsonObject object = json.get(i).getAsJsonObject();
            Task task = new Task();
            task.taskId = object.get("task_id").getAsInt();
            task.status = object.get("status").getAsInt();
            task.userId = object.get("user_id").toString();
            task.startTime = object.get("start_time").getAsLong();
            task.endTime = object.get("end_time").getAsLong();
            task.spendTime = object.get("spend_time").getAsLong();
            task.content = object.get("content").toString();
            tasks.add(task);
        }
        return tasks;
    }

    public static void makeTask(String userId, String content) {
        String requestUrl = url + "maketask?id=" + userId + "&content=" + content;
        Connect.connect(requestUrl);
    }

    public static void deleteTask(int taskId) {
        String requestUrl = url + "deletetask?taskid=" + taskId;
        Connect.connect(requestUrl);
    }

    public static ArrayList<Task> findWord(String userId, String word) {
        ArrayList<Task> tasks = new ArrayList<>();
        String requestUrl = url + "findword?word=" + word + "&userid=" + userId;
        String answer = Connect.connect(requestUrl);

        JsonParser jsonParser = new JsonParser();
        JsonArray json = (JsonArray) jsonParser.parse(answer);

        for (int i = 0; i < json.size(); ++i) {
            JsonObject object = json.get(i).getAsJsonObject();
            Task task = new Task();
            task.taskId = object.get("task_id").getAsInt();
            task.status = object.get("status").getAsInt();
            task.userId = object.get("user_id").toString();
            task.startTime = object.get("start_time").getAsLong();
            task.endTime = object.get("end_time").getAsLong();
            task.spendTime = object.get("spend_time").getAsLong();
            task.content = object.get("content").toString();
            tasks.add(task);

        }

        return tasks;
    }

    public static Task getTask(int taskId){
        String requestUrl = url + "selecttask?taskid=" + taskId;
        String answer = Connect.connect(requestUrl);

        JsonParser jsonParser = new JsonParser();
        JsonArray json = (JsonArray) jsonParser.parse(answer);

        Task task = new Task();

        for (int i = 0; i < json.size(); ++i) {
            JsonObject object = json.get(i).getAsJsonObject();
            task.taskId = object.get("task_id").getAsInt();
            task.status = object.get("status").getAsInt();
            task.userId = object.get("user_id").toString();
            task.startTime = object.get("start_time").getAsLong();
            task.endTime = object.get("end_time").getAsLong();
            task.spendTime = object.get("spend_time").getAsLong();
            task.content = object.get("content").toString();
        }

        return task;
    }

    public static void addTime(int taskId, Long plusTime) {
        String requestUrl = url + "addtime?taskid=" + taskId + "&plustime=" + plusTime;
        Connect.connect(requestUrl);
    }

    public static void transferTaskStatus(int taskId) {
        String requestUrl = url + "transfer?taskid=" + taskId;
        String answer = Connect.connect(requestUrl);
        if(Objects.equals(answer, "0\n")) {
            String setEndTime = url + "endtime?taskid=" + taskId + "&settime=" + "-1";
            Connect.connect(setEndTime);
        }
        else {
            long endTime = System.currentTimeMillis();
            String setEndTime = url + "endtime?taskid=" + taskId + "&settime=" + endTime;
            Connect.connect(setEndTime);
        }
    }


}

