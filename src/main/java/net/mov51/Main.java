package net.mov51;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("Hello world!");
        ReadLine(Integer.parseInt(args[0]),args[1]);
    }
    public static void ReadLine(int line, String path) throws IOException {
        try (Stream<String> all_lines = Files.lines(Paths.get(path))) {
            String input = all_lines.skip(line).findFirst().get();
            System.out.println(input);
            String output = input.replace("test","weakling");
            System.out.println(output);
            WriteLine(line,path,output);

        }catch (NoSuchElementException e){
            System.out.println("Line not found");
        }
    }

    public static void WriteLine(int line, String filePath, String newLine) throws IOException {
        Path path = Paths.get(filePath);
        List<String> lines = Files.readAllLines(path);
        lines.set(line, newLine);
        Files.write(path, lines);
    }
}