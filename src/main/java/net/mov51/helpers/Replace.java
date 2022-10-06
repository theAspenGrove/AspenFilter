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
            System.out.println("Censoring file: " + filter.getUUID());
            filter.printOut();
            if(ReplaceLine(filter, filter.secret, filter.placeHolder)){
                System.out.println("File " + filter.getUUID() + " was not censored!");
            }else{
                System.out.println("File " + filter.getUUID() + " was censored!");
                filter.setFiltered(true);
            }
        }
        countFiltered();
    }

    public static void openFiles(){
        for(Filter filter: filters){
            String activeFileID = filter.filePath + ":" + filter.getAdjustedLine();
            System.out.println("Opening file: " + activeFileID);
            filter.printOut();
            if(ReplaceLine(filter, filter.placeHolder, filter.secret)){
                System.out.println("File " + filter.getUUID() + " was not opened!");
            }else{
                System.out.println("File " + filter.getUUID() + " was opened!");
                filter.setFiltered(true);
            }
        }
        countFiltered();

    }

    public static void countFiltered(){
        int count = 0;
        for(Filter filter: filters){
            if(filter.filtered){
                count++;
            }
        }
        System.out.println("Filtered " + count + " files out of " + filters.size() + " defined filters.");
        if(count == filters.size()){
            System.out.println("All files have been filtered");
        }else if(count == 0){
            System.out.println("No files have been filtered");
            for(Filter filter: filters){
                if(!filter.filtered){
                    System.out.println(filter.getUUID());
                }
            }
        } else if (count < filters.size()) {
            System.out.println("Not all files have been filtered!");
            for(Filter filter: filters){
                if(!filter.filtered){
                    System.out.println(filter.getUUID());
                }
            }
        }
    }

    public static boolean ReplaceLine(Filter filter,String target, String replacement){
        try (Stream<String> all_lines = Files.lines(Paths.get(filter.filePath))) {
            String input = all_lines.skip(filter.getAdjustedLine()).findFirst().get();
            System.out.println("input: "+ input);
            String output = input.replace(target,replacement);
            System.out.println("output: "+ output + " *");
            System.out.println("---");
            WriteLine(filter.getAdjustedLine(),filter.filePath,output);
            return input.equals(output);
        }catch (NoSuchElementException e){
            System.out.println("Line "+ filter.lineNumber +" not found");
            System.out.println(filter.getUUID() +" will not be modified!");
            System.out.println("---");
            return true;
        }catch (IOException e){
            System.out.println("Error reading file: " + filter.filePath);
            System.out.println(filter.getUUID() + " will not be modified!");
            System.out.println("---");
            return true;
        }
    }

}
