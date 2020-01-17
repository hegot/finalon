package services;

import database.log.DbLogHandler;
import entities.Log;

public class Logger {
    public static void log(String message) {
        Log log = new Log(message);
        DbLogHandler.addLog(log);
    }
}
