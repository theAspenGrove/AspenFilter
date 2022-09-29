package net.mov51;

import static net.mov51.helpers.YamlHelpers.loadYaml;

public class Main {
    public static void main(String[] args){
        System.out.println("Hello world!");

        loadYaml();
        if(args.length > 0){
            if(args[0].equals("open")){
                System.out.println("Opening files");
                net.mov51.helpers.Replace.openFiles();
            }else if(args[0].equals("censor")){
                System.out.println("Censoring files");
                net.mov51.helpers.Replace.censorFiles();
            }else{
                System.out.println("Invalid argument");
            }

        }
    }

}