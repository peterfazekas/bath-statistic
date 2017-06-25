package hu.bath.model.domain;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import hu.bath.model.service.SaunaUsage;

/**
 * @author Peter_Fazekas
 */
public class Guest {

    private final int id;
    private final List<Entity> entities;

    public Guest(final int id, final List<Entity> entities) {
        this.id = id;
        this.entities = entities;
    }

    public int getId() {
        return id;
    }

    public List<Entity> getEntities() {
        return entities;
    }

    public boolean isUsedOnlyOneDepartment() {
        return countUsedDepartmentsExcludingDressingRoom() == 1;
    }

    private long countUsedDepartmentsExcludingDressingRoom() {
        return entities.stream().filter(i -> i.getDepartment().getId() > 0 && i.isExit()).count();
    }

    public int getTime() {
        return entities.get(entities.size() - 1).getTime().toSeconds() - entities.get(0).getTime().toSeconds();
    }

    public boolean isInTimeInterval(final int fromHour, final int toHour) {
        int hour = entities.get(0).getTime().getHour();
        return hour >= fromHour && hour < toHour;
    }

    public String getSaunaEntitiesTime() {
        SaunaUsage saunaUsage = new SaunaUsage(this);
        return saunaUsage.getInfo();
    }

    public Map<Department, Integer> departmentMap() {
        Map<Department, Integer> map = new LinkedHashMap<>();
        Arrays.stream(Department.values()).forEach(i -> map.put(i, hasUsedTheDepartment(i)));
        return map;
    }

    private int hasUsedTheDepartment(final Department department) {
        return entities.stream().filter(i -> i.getDepartment() == department).findFirst().map(i -> 1).orElse(0);
    }
}
