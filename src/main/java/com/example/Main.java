package com.example;

import java.util.Timer;
import java.util.TimerTask;
import java.net.HttpURLConnection;
import java.net.URL;

public class Main {
    public static void main(String[] args) {
        String url = System.getenv("TARGET_URL");
        if (url == null || url.isEmpty()) {
            url = "https://example.com"; // غيّرها للموقع اللي بدك ترفريشه
        }
        System.out.println("Auto refreshing: " + url);
        Timer timer = new Timer(true);
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                try {
                    HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
                    conn.setRequestMethod("GET");
                    conn.getResponseCode();
                    conn.disconnect();
                    System.out.println("Refreshed " + url + " at " + new java.util.Date());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, 0, 5 * 60 * 1000); // كل 5 دقائق
        try {
            Thread.sleep(Long.MAX_VALUE);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
