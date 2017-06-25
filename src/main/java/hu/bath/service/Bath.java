package hu.bath.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import hu.bath.model.domain.Department;
import hu.bath.model.domain.Entity;
import hu.bath.model.domain.Guest;
import hu.bath.model.domain.Time;

/**
 * @author Peter_Fazekas
 */
public class Bath {

    private final List<Guest> guests;

    public Bath(final List<Guest> guests) {
        this.guests = guests;
    }

    /**
     * 2. feladat: Írja a képernyőre, hogy az első és az utolsó vendég mikor lépett ki az öltözőből!
     * @return szöveges válasz
     */
    public String getGuestLeftDressingRoomTime() {
        return getGuestLeftDressingRoomTime("első", 0) + getGuestLeftDressingRoomTime("utolsó", guests.size() - 1);
    }

    private String getGuestLeftDressingRoomTime(final String text, final int index) {
        return String.format("%nAz %s vendég %s-kor lépett ki az öltözőből.", text, getGuestLeftDressingRoomTime(guests.get(index)));
    }

    private Time getGuestLeftDressingRoomTime(final Guest guest) {
        return guest.getEntities().stream().filter(Entity::isOutFromDressingRoom).map(Entity::getTime).findFirst().get();
    }

    /**
     * 3. feladat: Határozza meg és írja ki a képernyőre, hogy hány olyan fürdővendég volt,
     * aki az öltözőn kívül csak egy részlegen járt és azt a részleget csak egyszer használta!
     * @return szöveges válasz
     */
    public String getCountGuestWhoUsedOnlyOneDepartment() {
        return String.format("%nA fürdőben %d vendég járt csak egy részlegen.", countGuestWhoUsedOnlyOneDepartment());
    }

    private long countGuestWhoUsedOnlyOneDepartment() {
        return guests.stream().filter(Guest::isUsedOnlyOneDepartment).count();
    }

    /**
     * 4. feladat: Határozza meg, hogy melyik vendég töltötte a legtöbb időt a fürdőben!
     * A vendég azonosítóját és a fürdőben tartózkodás idejét írja ki a képernyőre!
     * A fürdőben a legtöbb időt töltő vendégek közül elegendő egy vendég adatait megjelenítenie.
     * @return szöveges válasz
     */
    public String getLongestStayTimeGuestData() {
        Guest guest = getLongestStayTimeGuest();
        return String.format(" A legtöbb időt eltöltő vendég:%n%d. vendég %s", guest.getId(), new Time(guest.getTime()));
    }

    private Guest getLongestStayTimeGuest() {
        return guests.stream().filter(i -> i.getTime() == getLongestStayTime()).findFirst().get();
    }

    private int getLongestStayTime() {
        return guests.stream().mapToInt(Guest::getTime).max().getAsInt();
    }

    /**
     * 5. feladat: Készítsen statisztikát, hogy 06:00:00-08:59:59 óra között, 09:00:00-15:59:59 óra között
     * és 16:00:00-19:59:59 óra között hány vendég érkezett a fürdőbe! Az eredményt írja ki a képernyőre!
     * @return szöveges válasz
     */
    public String getGuestNumberByArrivalTime() {
        return getGuestNumberByArrivalTime(6, 9) + getGuestNumberByArrivalTime(9, 16) + getGuestNumberByArrivalTime(16, 20);
    }

    private String getGuestNumberByArrivalTime(final int fromHour, final int toHour) {
        return String.format("%n%d-%d óra között %d vendég", fromHour, toHour, countGuestNumberByArrivalTime(fromHour, toHour));
    }

    private long countGuestNumberByArrivalTime(final int fromHour, final int toHour) {
        return guests.stream().filter(i -> i.isInTimeInterval(fromHour, toHour)).count();
    }

    /**
     * 6. feladat: Készítsen egy listát a szauna részlegen járt vendégekről és az általuk ott töltött időről!
     *  A vendég azonosítóját és a részlegen eltöltött időt a szauna.txt fájlba írja ki!
     *  A fájlban egy sorban a vendég azonosítója és szóközzel elválasztva a részlegen eltöltött
     *  idő szerepeljen óra:perc:másodperc formában! Ügyeljen arra, hogy egy vendég a szauna
     *  részlegben a nap folyamán többször is járhatott!
     * @return
     */
    public List<String> getSaunaEntitiesTime() {
        return guests.stream().map(Guest::getSaunaEntitiesTime).collect(Collectors.toList());
    }

    /**
     * 7. feladat: Készítsen egy listát, amelyben megadja, hogy az egyes részlegeket hányan használták!
     * Az eredményt a minta szerint írja ki a képernyőre! Ha egy vendég egy részlegen többször is járt a nap folyamán, 
     * azt a statisztikában csak egynek számolja!
     * @return
     */
    public String getDepartmentMap() {
        StringBuilder sb = new StringBuilder();
        fillDepartmentMap().entrySet().stream().filter(i -> i.getKey().getId() > 0).map(i -> "\n" + i.getKey().getDescription() + ": " + i.getValue())
                .forEach(sb::append);
        return sb.toString();
    }

    private Map<Department, Integer> fillDepartmentMap() {
        DepartmentMap department = new DepartmentMap();
        Map<Department, Integer> departmentMap = department.createMap();
        for (Guest guest : guests) {
            departmentMap = department.mergeMaps(departmentMap, guest.departmentMap());
        }
        return departmentMap;
    }
}
