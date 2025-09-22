package com.static_analyzer_spoon;

import com.static_analyzer_spoon.cli_analyse.LauncherCLI;

public class MainCLI {

    private static boolean withGui = false;
    private static boolean withCLI = false;
    private static boolean forMaven = false;
    private static String path = "";

    public static void main(String[] args) {
        System.out.println("Début du programme");
        for (String arg : args) {
            switch (arg) {

                case "--forMaven"://for analyses for maven
                    forMaven = true;
                    break;

                case "--WithGUI"://for analyses with a GUI interface
                    withGui = true;
                    break;
            
                case "--WithCLI"://for analyses with just the interface of the terminal 
                    withCLI = true;
                    break;
                default:
                    if (arg.startsWith("--path=")) {//get the path 
                        path = arg.substring("--path=".length());
                        // Utilisez la variable 'path' comme nécessaire
                        System.out.println("Path fourni : " + path);
                    } else {
                        System.out.println("Option inconnue : " + arg);
                    }
                    break;
            }
        }

        if (withCLI==false && withGui==false) {
            System.out.println("No option provided, defaulting to --WithGUI");
            withGui = true;
        }

        if (withCLI && withGui) {
            throw new IllegalArgumentException("You can't use both --WithGUI and --WithCLI options at the same time.");
        }

        if (withCLI) {
            LauncherCLI launcher = new LauncherCLI(path,forMaven);            
        }
    }
}