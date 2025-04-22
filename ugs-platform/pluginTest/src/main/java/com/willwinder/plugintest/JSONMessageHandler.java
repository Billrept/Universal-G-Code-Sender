package com.willwinder.plugintest;

import org.json.JSONObject;

public class JSONMessageHandler {
    public static int handleMessage(String message) {
        if (message.startsWith("[JSON:") && message.endsWith("]")) {
            String jsonString = message.substring(6, message.length() - 1);
            JSONObject json = new JSONObject(jsonString);
            if (json.has("mode")) {
                String mode = json.getString("mode");
                switch (mode) {
                    case "drawing" :
                        return 0;
                    case "laser" :
                        return 1;
                    case "drill" :
                        return 2;
                }
            }
        }
        return -1;
    }
}
