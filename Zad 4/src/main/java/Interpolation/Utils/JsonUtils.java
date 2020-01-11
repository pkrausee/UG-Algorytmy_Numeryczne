package Interpolation.Utils;

import Interpolation.Model.Point;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JsonUtils {

    public static List<Point> read(String filename) {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            String content = Files.readString(Paths.get(filename), StandardCharsets.UTF_8);
            return Arrays.asList(objectMapper.readValue(content, Point[].class));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new ArrayList<>();
    }

    public static void save(String filename, String content) {
        try (PrintWriter writer = new PrintWriter(filename, StandardCharsets.UTF_8)) {
            writer.println(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
