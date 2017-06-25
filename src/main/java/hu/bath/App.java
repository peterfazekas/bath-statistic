package hu.bath;

import hu.bath.data.log.DataLogger;
import hu.bath.data.log.FileLogger;
import hu.bath.model.factory.Data;
import hu.bath.service.Bath;

/**
 * @author Peter_Fazekas
 */
class App {

    private static final String INPUT = "furdoadat.txt";
    private static final String OUTPUT = "szauna.txt";
    private final Bath bath;
    private final DataLogger log;

    private App() {
        Data data = new Data();
        bath = new Bath(data.getData(INPUT));
        log = new FileLogger(OUTPUT);
    }

    public static void main(String[] args) {
        App app = new App();
        app.print();
    }

    private void print() {
        System.out.println("\n2. feladat:" + bath.getGuestLeftDressingRoomTime());
        System.out.println("\n3. feladat:" + bath.getCountGuestWhoUsedOnlyOneDepartment());
        System.out.println("\n4. feladat:" + bath.getLongestStayTimeGuestData());
        System.out.println("\n5. feladat:" + bath.getGuestNumberByArrivalTime());
        log.printAll(bath.getSaunaEntitiesTime());
        System.out.println("\n7. feladat:" + bath.getDepartmentMap());
    }
}
