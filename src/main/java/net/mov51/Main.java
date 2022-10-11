package net.mov51;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static net.mov51.helpers.Replace.censorFiles;
import static net.mov51.helpers.Replace.openFiles;
import static net.mov51.helpers.fileUtil.defaultFilePath;
import static net.mov51.helpers.fileUtil.loadYaml;

public class Main {

    public static final Logger logger = LogManager.getRootLogger();
    public static void main(String[] args){
        logger.info("Starting up");

        if(args.length > 0){
            if(args.length == 1){
                run(args[0],defaultFilePath);
            }else if(args.length == 2){
                run(args[0],args[1]);
            }else{
                System.out.println("Invalid arguments");
            }
        }else{
            logger.fatal("No arguments provided");
            logger.error("No arguments given");
            logger.error("Please specify 'open' or 'censor'");
            logger.error("open: un-censor files");
            logger.error("censor: censor files");
        }
    }

    public static void run(String state,String filePath){
        if(state.equals("open")){
            loadYaml(filePath);
            logger.info("Opening files");
            openFiles();
        }else if(state.equals("censor")){
            loadYaml(filePath);
            logger.info("Censoring files");
            censorFiles();
        }else{
            logger.error("Invalid argument");
        }
    }


}