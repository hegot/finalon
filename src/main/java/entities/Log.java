package entities;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Log {
    private int id;
    private String date;
    private String message;

    public Log(
            int id,
            String date,
            String message
    ) {
        this.id = id;
        this.date = date;
        this.message = message;
    }

    public Log(
            String message
    ) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date dateN = new Date();
        this.date = dateFormat.format(dateN);
        this.message = message;
    }


    public String getDate() {
        return date;
    }

    public String getMessage() {
        return message;
    }
}
