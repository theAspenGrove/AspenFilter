package net.mov51.helpers;

import static net.mov51.helpers.fileUtil.loadYaml;

public class Filter {
    public String placeHolder;
    public String secret;
    public String filePath;
    public int lineNumber;
    public boolean filtered;

    public void printOut(){
        System.out.println(this.placeHolder);
        System.out.println(this.secret);
        System.out.println(this.filePath);
        System.out.println(this.lineNumber);
        System.out.println("---");
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

