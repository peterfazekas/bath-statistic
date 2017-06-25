package hu.bath.model.factory;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import hu.bath.model.domain.Entity;
import hu.bath.model.domain.Guest;

/**
 * @author Peter_Fazekas
 */
class GuestFactory {

    private final EntityFactory entityFactory;
    private final Map<Integer, List<Entity>> guestMap;

    public GuestFactory() {
        entityFactory = new EntityFactory();
        guestMap = new LinkedHashMap<>();
    }

    public List<Guest> parse(final List<String> lines) {
        createGuestMap(lines);
        return guestMap.entrySet().stream().map(this::createGuest).collect(Collectors.toList());
    }

    private void createGuestMap(final List<String> lines) {
        lines.forEach(this::createGuestMapItem);
    }

    private int getGuestId(final String line) {
        return Integer.parseInt(line.substring(0, 3));
    }

    private void createGuestMapItem(final String line) {
        int key = getGuestId(line);
        Entity entity = entityFactory.createEntry(line);
        List<Entity> guestEntity = guestMap.containsKey(key) ? guestMap.get(key) : new ArrayList<>();
        guestEntity.add(entity);
        guestMap.put(key, guestEntity);
    }

    private Guest createGuest(Map.Entry<Integer, List<Entity>> guestMapItem) {
        return new Guest(guestMapItem.getKey(), guestMapItem.getValue());
    }

}
