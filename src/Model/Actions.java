package Model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Actions {
    private String description;
    private LocalDateTime timeStamp;

    public Actions(String description) {
        this.description = description;
        this.timeStamp = LocalDateTime.now();
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }

    public String toString(){
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return "[" + timeStamp.format(format) +"]" + description;
    }
    
}
