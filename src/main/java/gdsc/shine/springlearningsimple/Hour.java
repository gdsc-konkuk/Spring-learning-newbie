package gdsc.shine.springlearningsimple;

public class Hour {
    private int hour;

    public Hour(int hour) {
        this.hour = hour;
    }

    public String getMessage() {
        if (hour < 12) {
            return "오전";
        }
        return "오후";
    }
}