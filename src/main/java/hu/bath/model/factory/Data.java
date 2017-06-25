package hu.bath.model.factory;

import java.util.List;

import hu.bath.data.read.DataReader;
import hu.bath.data.read.GuestsDataReader;
import hu.bath.model.domain.Guest;

/**
 * @author Peter_Fazekas
 */
public class Data {

    public List<Guest> getData(final String fileName) {
        DataReader file = new GuestsDataReader(fileName);
        GuestFactory data = new GuestFactory();
        return data.parse(file.read());
    }
}
