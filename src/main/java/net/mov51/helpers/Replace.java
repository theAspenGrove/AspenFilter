package net.mov51.helpers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.NoSuchElementException;
import java.util.stream.Stream;

import static net.mov51.helpers.fileUtil.filters;
import static net.mov51.helpers.fileUtil.WriteLine;

public class Replace {
    public static void censorFiles(){
        for(Filter filter: filters){
            String activeFileID = filter.filePath + ":" + filter.getAdjustedLine();
            System.out.println("Censoring file: " + activeFileID);
            filter.printOut();
            if(ReplaceLine(filter.getAdjustedLine(), filter.filePath, filter.secret, filter.placeHolder,  activeFileID)){
                System.out.println("File " + activeFileID+ " was not censored!");
            }
        }
    }

    public static void openFiles(){
        for(Filter filter: filters){
            String activeFileID = filter.filePath + ":" + filter.getAdjustedLine();
            System.out.println("Opening file: " + activeFileID);
            filter.printOut();
            if(ReplaceLine(filter.getAdjustedLine(), filter.filePath, filter.placeHolder, filter.secret, activeFileID)){
                System.out.println("File " + activeFileID + " was not opened!");
            }
        }
    }

    public static boolean ReplaceLine(int line, String path, String target, String replacement, String activeFileID){
        try (Stream<String> all_lines = Files.lines(Paths.get(path))) {
            String input = all_lines.skip(line).findFirst().get();
            System.out.println("input: "+ input);
            String output = input.replace(target,replacement);
            System.out.println("output: "+ output + " *");
            System.out.println("---");
            WriteLine(line,path,output);
            return input.equals(output);
        }catch (NoSuchElementException e){
            System.out.println("Line "+ line +" not found");
            System.out.println(activeFileID +" will not be modified!");
            System.out.println("---");
            return true;
        }catch (IOException e){
            System.out.println("Error reading file: " + path);
            System.out.println(activeFileID + " will not be modified!");
            System.out.println("---");
            return true;
        }
    }

}
