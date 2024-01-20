package utils;

import java.io.FileReader;
import java.io.IOException;
import java.nio.CharBuffer;
import java.util.Arrays;

public class CsvReader {
    public static String[] readFromFile(String fileName) throws IOException {
        var cb = CharBuffer.allocate(100);
        var fr = new FileReader(fileName);
        var sb = new StringBuilder();
        while (true) {
            var read = fr.read(cb);
            if (read <= 0) break;
            sb.append(cb.array(), 0, read);
            cb.clear();
        }
        fr.close();
        return Arrays
                .stream(sb.toString().split("\n"))
                .skip(1)
                .toArray(String[]::new);
    }
}
