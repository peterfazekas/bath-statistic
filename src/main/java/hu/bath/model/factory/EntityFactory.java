package hu.bath.model.factory;

import hu.bath.model.domain.Department;
import hu.bath.model.domain.Entity;
import hu.bath.model.domain.Time;

/**
 * @author Peter_Fazekas
 */
class EntityFactory {

    private static final String SEPARATOR = " ";

    public Entity createEntry(final String line) {
        String[] items = getItems(getEntryData(line));
        Department department = Department.fromId(parseInt(items[0]));
        return new Entity(department, isOut(items), createTime(items));
    }

    private String getEntryData(final String line) {
        return line.substring(4);
    }

    private Time createTime(final String[] items) {
        int hour = parseInt(items[2]);
        int minute = parseInt(items[3]);
        int second = parseInt(items[4]);
        return new Time(hour, minute, second);
    }

    private boolean isOut(final String[] items) {
        return parseInt(items[1]) == 1;
    }

    private String[] getItems(final String line) {
        return line.split(SEPARATOR);
    }

    private int parseInt(final String item) {
        return Integer.parseInt(item);
    }
}
