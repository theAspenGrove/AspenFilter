package net.mov51.helpers;

import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class YamlHelpers {

    public static List<Filter> filters = new ArrayList<>();
    public static void loadYaml() throws IOException {
        Yaml yaml = new Yaml(new Constructor(Filter.class));
            File dir = new File("filters/");
            File[] directoryListing = dir.listFiles();
            if (directoryListing != null) {
                for (File child : directoryListing) {
                    try (InputStream in = Files.newInputStream(child.toPath())) {
                        Filter filter = yaml.loadAs(in, Filter.class);
                        filters.add(filter);
                    }
                }
            }
        }
    }
