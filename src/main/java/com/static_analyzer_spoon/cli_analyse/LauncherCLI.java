package com.static_analyzer_spoon.cli_analyse;

import spoon.Launcher;
import spoon.reflect.CtModel;

public class LauncherCLI extends AbsractLauncher{

    

    public LauncherCLI (String pathString, boolean forMavenInput) {
        path = pathString;
        forMaven = forMavenInput;
        LauncherCLI.analyseforMaven();
        
        System.out.println("End of the program");  
        
        
    }
}
