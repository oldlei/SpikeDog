package com.ceshi.java;

import win.chenglei.MyHttpServer;

import java.util.HashMap;

public class CeShi extends MyHttpServer {
    @Override
    public void doPost(HashMap<String, String> req) {
        System.out.println("doPost");
    }

    @Override
    public void doGet(HashMap<String, String> req) {
        System.out.println("doGet");
    }
}
