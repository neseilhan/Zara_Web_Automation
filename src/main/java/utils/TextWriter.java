package utils;

import java.io.FileWriter;
import java.io.IOException;

public class TextWriter {

    public static void writeProductInfo(String filePath, String name, String price) {
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write("Product: " + name + "\n");
            writer.write("Price: " + price + "\n");
        } catch (IOException e) {
            throw new RuntimeException("Failed to write product info into file");
        }
    }
}
