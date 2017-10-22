package com.developer.sixfingers.jeenchtest;


public class GlobalData {
    private static GlobalData instance;

    public int userId;

    public static synchronized GlobalData getInstance() {
        if (instance == null) {
            instance = new GlobalData();
        }
        return instance;
    }
}