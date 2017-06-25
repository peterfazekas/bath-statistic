package hu.bath.service;

import java.util.LinkedHashMap;
import java.util.Map;

import hu.bath.model.domain.Department;

/**
 * @author Peter_Fazekas
 */
class DepartmentMap {

    public Map<Department, Integer> mergeMaps(final Map<Department, Integer> departmentMap, final Map<Department, Integer> guestDepartmentMap) {
        Map<Department, Integer> mergedDepartmentMap = new LinkedHashMap<>();
        for (Map.Entry<Department, Integer> departmentEntry : departmentMap.entrySet()) {
            int value = guestDepartmentMap.get(departmentEntry.getKey()) + departmentEntry.getValue();
            mergedDepartmentMap.put(departmentEntry.getKey(), value);
        }
        return mergedDepartmentMap;
    }

    public Map<Department, Integer> createMap() {
        Map<Department, Integer> departmentMap = new LinkedHashMap<>();
        for (Department department : Department.values()) {
            departmentMap.put(department, 0);
        }
        return departmentMap;
    }
}
