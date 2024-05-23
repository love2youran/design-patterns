package com.youran.designpatterns.scenarios.creational.singleton;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 枚举单例(日志记录器)
 */
public enum Logger {
    INSTANCE;

    private static final String LOG_FILE = "app.log";
    private PrintWriter writer;

    Logger() {
        try {
            FileWriter fw = new FileWriter(LOG_FILE, true);
            writer = new PrintWriter(fw, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void log(String message) {
        writer.println(message);
    }
}