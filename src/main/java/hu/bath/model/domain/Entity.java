package hu.bath.model.domain;

/**
 * @author Peter_Fazekas
 */
public class Entity {

    private final Department department;
    private final boolean isOut;
    private final Time time;

    public Entity(final Department department, final boolean isOut, final Time time) {
        this.department = department;
        this.isOut = isOut;
        this.time = time;
    }

    public Department getDepartment() {
        return department;
    }

    public boolean isEnter() {
        return !isOut;
    }

    public boolean isExit() {
        return isOut;
    }

    public Time getTime() {
        return time;
    }

    public boolean isOutFromDressingRoom() {
        return department == Department.DRESSING_ROOM && isOut;
    }
}
