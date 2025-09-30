package com.static_analyzer_spoon.cli_analyse;

import java.util.Collection;

import com.static_analyzer_spoon.Processor.ProcessorStaticAnalyze;
import com.static_analyzer_spoon.visitor.VisitorClass;

import spoon.Launcher;
import spoon.MavenLauncher;
import spoon.reflect.CtModel;

public abstract class AbsractLauncher {

    
    
    protected static String path;
    protected static boolean forMaven;
    protected static ProcessorStaticAnalyze processorStaticAnalyze = new ProcessorStaticAnalyze();

    public static void analyse()
    {
        Launcher launcher = new Launcher();
        launcher.addInputResource(path);
        launcher.buildModel();
        
        CtModel model = launcher.getModel();
        processorStaticAnalyze.process(model);

        
    }

    public static void analyseforMaven()
    {
        System.out.println("Begin analyze");
        MavenLauncher launcher = new MavenLauncher(path, MavenLauncher.SOURCE_TYPE.APP_SOURCE);
        launcher.buildModel();
        CtModel model = launcher.getModel();
        processorStaticAnalyze.process(model);
        processorStaticAnalyze.show();
        


        
        
    }


    

}
