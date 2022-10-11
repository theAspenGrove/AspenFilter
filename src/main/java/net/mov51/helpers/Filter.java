package net.mov51.helpers;

import static net.mov51.Main.logger;
import static net.mov51.helpers.fileUtil.loadYaml;

public class Filter {
    public String placeHolder;
    public String secret;
    public String filePath;
    public int lineNumber;
    public boolean filtered;

    public void printOut(){
        logger.debug(this.placeHolder);
        logger.debug(this.secret);
        logger.debug(this.filePath);
        logger.debug(this.lineNumber);
        logger.debug("---");
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

