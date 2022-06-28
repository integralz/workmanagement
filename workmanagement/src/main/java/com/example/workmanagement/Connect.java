package com.example.workmanagement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Connect {
    public static String connect(String requestUrl) {
        StringBuffer buffer = new StringBuffer();
        Thread connectionThread = new Thread() {
            @Override
            public void run() {
                try {
                    URL url = new URL(requestUrl);

                    HttpURLConnection myConnection =
                            (HttpURLConnection) url.openConnection();
                    myConnection.connect();
                    if (myConnection.getResponseCode() == 200) {
                        InputStream responseBody = myConnection.getInputStream();

                        BufferedReader reader = new BufferedReader(new InputStreamReader(responseBody));
                        String line;
                        while ((line = reader.readLine()) != null) {
                            buffer.append(line + "\n");
                        }

                    } else {
                        System.err.println("Connect : api connection response is not 200");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    System.err.println("Connect : GET method failed: " + e.getMessage());
                    e.printStackTrace();
                }
            }
        };

        connectionThread.start();

        try {
            connectionThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return String.valueOf(buffer);
    }
}
