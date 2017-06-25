package hu.bath.model.service;

import java.util.List;
import java.util.stream.Collectors;

import hu.bath.model.domain.Department;
import hu.bath.model.domain.Entity;
import hu.bath.model.domain.Guest;
import hu.bath.model.domain.Time;

/**
 * @author Peter_Fazekas
 */
public class SaunaUsage {

    private final int id;
    private final List<Entity> entities;

    public SaunaUsage(final Guest guest) {
        this.id = guest.getId();
        this.entities = guest.getEntities();
    }

    public String getInfo() {
        return getSaunaUsageDuration().toSeconds() > 0 ? String.format("%d %s%n", id, getSaunaUsageDuration()) : "";
    }

    private Time getSaunaUsageDuration() {
        List<Time> saunaEnterEntities = getSaunaEnterTimes();
        List<Time> saunaExitEntities = getSaunaExitTimes();
        Time time = new Time(0);
        for (int index = 0; index < getSaunaEnterTimes().size(); index++) {
            time = Time.add(time, Time.duration(saunaEnterEntities.get(index), saunaExitEntities.get(index)));
        }
        return time;
    }

    private List<Time> getSaunaEnterTimes() {
        return entities.stream().filter(this::isSaunas).filter(Entity::isEnter).map(Entity::getTime).collect(Collectors.toList());
    }

    private List<Time> getSaunaExitTimes() {
        return entities.stream().filter(this::isSaunas).filter(Entity::isExit).map(Entity::getTime).collect(Collectors.toList());
    }

    private boolean isSaunas(final Entity entity) {
        return entity.getDepartment() == Department.SAUNAS;
    }
}
