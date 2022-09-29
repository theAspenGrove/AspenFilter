package net.mov51.helpers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Stream;

import static net.mov51.helpers.YamlHelpers.filters;

public class Replace {
    public static void censorFiles(){
        for(Filter filter: filters){
            System.out.println("Censoring file: " + filter.filePath);
            System.out.println(filter.placeHolder + "*");
            System.out.println(filter.filePath);
            System.out.println(filter.secret);
            System.out.println(filter.lineNumber);
            System.out.println("---");
            if(ReplaceLine(filter.lineNumber, filter.filePath, filter.placeHolder, filter.secret)){
                System.out.println("File " + filter.filePath + " was not censored!");
            }
        }
    }

    public static void openFiles(){
        for(Filter filter: filters){
            System.out.println("Opening file: " + filter.filePath);
            System.out.println(filter.placeHolder);
            System.out.println(filter.filePath);
            System.out.println(filter.secret + "*");
            System.out.println(filter.lineNumber);
            System.out.println("---");
            if(ReplaceLine(filter.lineNumber, filter.filePath, filter.secret, filter.placeHolder)){
                System.out.println("File " + filter.filePath + " was not opened!");
            }
        }
    }


    public static boolean ReplaceLine(int line, String path, String target, String replacement){
        try (Stream<String> all_lines = Files.lines(Paths.get(path))) {
            String input = all_lines.skip(line).findFirst().get();
            System.out.println(input);
            String output = input.replace(replacement,target);
            System.out.println(output + "*");
            System.out.println("---");
            WriteLine(line,path,output);
            return input.equals(output);

        }catch (NoSuchElementException e){
            System.out.println("Line not found");
            System.out.println("This file will not be modified!");
            System.out.println("---");
            return true;
        }catch (IOException e){
            System.out.println("Error reading file: " + path);
            System.out.println("This file will not be modified!");
            System.out.println("---");
            return true;
        }
    }

    public static void WriteLine(int line, String filePath, String newLine) throws IOException {
        Path path = Paths.get(filePath);
        List<String> lines = Files.readAllLines(path);
        lines.set(line, newLine);
        Files.write(path, lines);
    }
}
