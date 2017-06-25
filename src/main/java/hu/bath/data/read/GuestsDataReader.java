package hu.bath.data.read;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Peter_Fazekas
 */
public class GuestsDataReader implements DataReader {

    private static final String PATH = "src\\main\\resources\\";

    private final String fileName;

    public GuestsDataReader(final String fileName) {
        this.fileName = PATH + fileName;
    }

    public List<String> read() {
        List<String> lines = null;
        try (BufferedReader read = new BufferedReader(new FileReader(fileName))) {
            lines = read.lines().collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }
}
