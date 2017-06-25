package hu.bath.model.domain;

/**
 * @author Peter_Fazekas
 */
public class Time {

    private static final int SEC_IN_HOUR = 3600;
    private static final int SEC_IN_MINUTE = 60;

    private final int hour;
    private final int minute;
    private final int second;

    public Time(int hour, int minute, int second) {
        this.hour = hour;
        this.minute = minute;
        this.second = second;
    }

    public Time(int seconds) {
        hour = seconds / SEC_IN_HOUR;
        minute = (seconds % SEC_IN_HOUR) / SEC_IN_MINUTE;
        second = (seconds % SEC_IN_HOUR) % SEC_IN_MINUTE;
    }

    public static Time duration(final Time t1, final Time t2) {
        return new Time(Math.abs(t1.toSeconds() - t2.toSeconds()));
    }

    public static Time add(final Time t1, final Time t2) {
        return new Time(t1.toSeconds() + t2.toSeconds());
    }

    public int getHour() {
        return hour;
    }

    public int toSeconds() {
        return hour * SEC_IN_HOUR + minute * SEC_IN_MINUTE + second;
    }

    @Override
    public String toString() {
        return String.format("%2d:%02d:%02d", hour, minute, second);
    }
}
