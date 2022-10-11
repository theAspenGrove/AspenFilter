package net.mov51.helpers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.NoSuchElementException;
import java.util.stream.Stream;

import static net.mov51.Main.logger;
import static net.mov51.helpers.fileUtil.filters;
import static net.mov51.helpers.fileUtil.WriteLine;

public class Replace {
    public static void censorFiles(){
        for(Filter filter: filters){
            logger.info("Censoring file: " + filter.getUUID());
            filter.printOut();
            if(ReplaceLine(filter, filter.secret, filter.placeHolder)){
                logger.error("File " + filter.getUUID() + " was not censored!");
            }else{
                logger.info("File " + filter.getUUID() + " was censored!");
                filter.setFiltered(true);
            }
        }
        countFiltered();
    }

    public static void openFiles(){
        for(Filter filter: filters){
            String activeFileID = filter.filePath + ":" + filter.getAdjustedLine();
            logger.debug("Opening file: " + activeFileID);
            filter.printOut();
            if(ReplaceLine(filter, filter.placeHolder, filter.secret)){
                logger.error("File " + filter.getUUID() + " was not opened!");
            }else{
                logger.error("File " + filter.getUUID() + " was opened!");
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
        logger.info("Filtered " + count + " files out of " + filters.size() + " defined filters.");
        if(count == filters.size()){
            logger.info("All files have been filtered");
        }else if(count == 0){
            logger.fatal("No files have been filtered");
            for(Filter filter: filters){
                if(!filter.filtered){
                    logger.error(filter.getUUID());
                }
            }
        } else if (count < filters.size()) {
            logger.warn("Not all files have been filtered!");
            for(Filter filter: filters){
                if(!filter.filtered){
                    logger.warn(filter.getUUID());
                }
            }
        }
    }

    public static boolean ReplaceLine(Filter filter,String target, String replacement){
        try (Stream<String> all_lines = Files.lines(Paths.get(filter.filePath))) {
            String input = all_lines.skip(filter.getAdjustedLine()).findFirst().get();
            logger.debug("input: "+ input);
            String output = input.replace(target,replacement);
            logger.debug("output: "+ output + " *");
            logger.debug("---");
            WriteLine(filter.getAdjustedLine(),filter.filePath,output);
            return input.equals(output);
        }catch (NoSuchElementException e){
            logger.debug("Line "+ filter.lineNumber +" not found");
            logger.debug(filter.getUUID() +" will not be modified!");
            logger.debug("---");
            return true;
        }catch (IOException e){
            logger.debug("Error reading file: " + filter.filePath);
            logger.debug(filter.getUUID() + " will not be modified!");
            logger.debug("---");
            return true;
        }
    }

}
