package net.mov51;

import static net.mov51.helpers.fileUtil.loadYaml;

public class Main {
    public static void main(String[] args){

        if(args.length > 0){
            if(args[0].equals("open")){
                loadYaml();
                System.out.println("Opening files");
                net.mov51.helpers.Replace.openFiles();
            }else if(args[0].equals("censor")){
                loadYaml();
                System.out.println("Censoring files");
                net.mov51.helpers.Replace.censorFiles();
            }else{
                System.out.println("Invalid argument");
            }

        }else{
            System.out.println("No arguments given");
            System.out.println("Please specify 'open' or 'censor'");
            System.out.println("open: uncensor files");
            System.out.println("censor: censor files");
        }
    }

}