package Model;

public class Actions {
    private String description;
    private long timeStamp;

    public Actions(String description) {
        this.description = description;
        this.timeStamp = System.currentTimeMillis();
    }

    public String getDescription() {
        return description;
    }

    public long getTimeStamp() {
        return timeStamp;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }

    public String toString(){
        return "[" + timeStamp +"]" + description;
    }
    
}
