package net.mov51.helpers;

import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static net.mov51.Main.logger;

public class fileUtil {
    public static List<Filter> filters = new ArrayList<>();
    public static String defaultFilePath = "filters/";

    public static void WriteLine(int line, String filePath, String newLine) throws IOException {
        Path path = Paths.get(filePath);
        List<String> lines = Files.readAllLines(path);
        lines.set(line, newLine);
        Files.write(path, lines);
    }

    public static void loadYaml(String filePath){
        int totalFiles = 0;
        logger.info("Loading filters");
        Yaml yaml = new Yaml(new Constructor(Filter.class));
            File dir = new File(filePath);
            File[] directoryListing = dir.listFiles();
            if (directoryListing != null) {
                for (File child : directoryListing) {
                    try (InputStream in = Files.newInputStream(child.toPath())) {
                        totalFiles++;
                        Filter filter = yaml.loadAs(in, Filter.class);
                        filters.add(filter);
                    }catch (IOException e){
                        logger.error("Error loading filter named: " + child.getName());
                        logger.error("This filter will not be run!");
                        e.printStackTrace();
                    }
                }
            }
        logger.info("Loaded "+ filters.size() + " filters out of " + totalFiles + " files.");
    }
}
