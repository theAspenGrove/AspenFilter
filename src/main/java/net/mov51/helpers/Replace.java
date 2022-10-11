package net.mov51.helpers;

import java.io.IOException;
import java.util.NoSuchElementException;

import static net.mov51.Main.logger;
import static net.mov51.helpers.fileUtil.filters;
import static net.mov51.helpers.fileUtil.WriteLine;

public class Replace {
    public static void censorFiles(){
        for(Filter filter: filters){
            logger.info("Opening file: " + filter.getUUID());
            filter.printOut();
            filter.setFiltered(
                    ReplaceLine(filter, true));
        }
        countFiltered();
    }

    public static void openFiles(){
        for(Filter filter: filters){
            logger.info("Opening file: " + filter.getUUID());
            filter.printOut();
            filter.setFiltered(
                    ReplaceLine(filter, false));
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

    public static boolean ReplaceLine(Filter filter, boolean censor){
        String input = filter.getFileLine();
        String output = "";
        if(censor && input.contains(filter.placeHolder)){
            if(input.contains(filter.placeHolder)) {
                logger.error("File already contains placeholder! " + filter.getUUID());
                return false;
            }
            logger.debug("input: "+ filter.secret);
            output = input.replace(filter.secret,filter.placeHolder);
        } else if ((!censor) && input.contains(filter.secret)) {
            if(input.contains(filter.secret)) {
                logger.error("File already contains secret! " + filter.getUUID());
                return false;
            }
            logger.debug("input: "+ filter.placeHolder);
            output = input.replace(filter.placeHolder,filter.secret);
        }
        try {
            WriteLine(filter.getAdjustedLine(),filter.filePath,output);
            return true;
        } catch (IOException e) {
            logger.error("Could not write to file: " + filter.filePath);
        } catch (NoSuchElementException e){
            logger.debug("Line "+ filter.lineNumber +" not found");
        }
        return false;
    }

}
