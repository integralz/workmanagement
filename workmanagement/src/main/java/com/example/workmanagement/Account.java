package com.example.workmanagement;

import java.util.ArrayList;

public class Account extends TaskManagement {
    String userId;
    ArrayList<Task> taskList = new ArrayList<Task>();

    Account() {
        userId = "";
    }
}
