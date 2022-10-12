package net.mov51.helpers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.NoSuchElementException;
import java.util.stream.Stream;

import static net.mov51.Main.logger;

public class Filter {
    public String placeHolder;
    public String secret;
    public String filePath;
    public int lineNumber;
    public boolean filtered;
    public void printOut(){
        logger.debug("PlaceHolder: " + this.placeHolder);
        logger.debug("Secret: " + this.secret);
        logger.debug("FilePath: " + this.filePath);
        logger.debug("Internal Line Number: " + this.lineNumber);
        logger.debug("Adjusted Line Number: " + this.getAdjustedLine());
        logger.debug("---");
    }

    public String getFileLine(){
        Stream<String> allLines;
        try {
            allLines = Files.lines(Paths.get(this.filePath));
            return allLines.skip(this.getAdjustedLine()).findFirst().get();
        } catch (IOException e) {
            logger.error("Could not open file: " + this.filePath);
            logger.debug(this.getUUID() +" will not be modified!");
            return "";
        } catch (NoSuchElementException e){
            logger.debug("Line "+ this.getAdjustedLine() +" not found");
            logger.debug(this.getUUID() +" will not be modified!");
            return "";
        }
    }

    public void setFiltered(boolean filtered){
        this.filtered = filtered;
    }

    public String getUUID(){
        return this.filePath + ":" + this.lineNumber + " " + this.placeHolder;
    }

    public int getAdjustedLine(){
        return this.lineNumber - 1;
    }



}

