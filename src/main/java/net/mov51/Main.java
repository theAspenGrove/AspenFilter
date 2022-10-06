package net.mov51;

import java.io.File;

import static net.mov51.helpers.Filter.*;
import static net.mov51.helpers.fileUtil.defaultFilePath;
import static net.mov51.helpers.fileUtil.loadYaml;

public class Main {
    public static void main(String[] args){

        if(args.length > 0){
            if(args.length == 1){
                run(args[0],defaultFilePath);
            }else if(args.length == 2){
                run(args[0],args[1]);
            }else{
                System.out.println("Invalid arguments");
            }
        }else{
            System.out.println("No arguments given");
            System.out.println("Please specify 'open' or 'censor'");
            System.out.println("open: uncensor files");
            System.out.println("censor: censor files");
        }
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