package hu.bath.model.domain;

/**
 * @author Peter_Fazekas
 */
public enum Department {
    DRESSING_ROOM(0, "Öltöző"),
    SWIMMING_POOL(1, "Uszoda"),
    SAUNAS(2, "Szaunák"),
    MEDICINAL_POOLS(3, "Gyógyvizes medencék"),
    BEACH(4, "Strand");

    private final int id;
    private final String description;

    Department(int id, String description) {
        this.id = id;
        this.description = description;
    }

    public static Department fromId(final int id) {
        Department department = null;
        for (Department actualDepartment : Department.values()) {
            if (actualDepartment.getId() == id) {
                department = actualDepartment;
            }
        }
        return department;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }
}
