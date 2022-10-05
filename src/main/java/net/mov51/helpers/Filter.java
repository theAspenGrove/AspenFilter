package net.mov51.helpers;

import static net.mov51.helpers.fileUtil.loadYaml;

public class Filter {
    public String placeHolder;
    public String secret;
    public String filePath;
    public int lineNumber;

    public void printOut(){
        System.out.println(this.placeHolder);
        System.out.println(this.secret);
        System.out.println(this.filePath);
        System.out.println(this.lineNumber);
        System.out.println("---");
    }

    public int getAdjustedLine(){
        return this.lineNumber - 1;
    }

    public static void run(String state,String filePath){
        if(state.equals("open")){
            loadYaml(filePath);
            System.out.println("Censoring files");
            net.mov51.helpers.Replace.openFiles();
        }else if(state.equals("censor")){
            loadYaml(filePath);
            System.out.println("Censoring files");
            net.mov51.helpers.Replace.censorFiles();
        }else{
            System.out.println("Invalid argument");
        }
    }



}

