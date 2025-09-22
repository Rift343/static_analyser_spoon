package com.static_analyzer_spoon.cli_analyse;

import java.util.Collection;

import com.static_analyzer_spoon.visitor.VisitorClass;

import spoon.Launcher;
import spoon.MavenLauncher;
import spoon.reflect.CtModel;
import spoon.reflect.declaration.CtClass;
import spoon.reflect.declaration.CtType;

public abstract class AbsractLauncher {
    
    protected static String path;
    protected static boolean forMaven;

    public static void analyse()
    {
        Launcher launcher = new Launcher();
        launcher.addInputResource(path);
        launcher.buildModel();
        
        CtModel model = launcher.getModel();

        
    }

    public static void analyseforMaven()
    {
        System.out.println("Begin analyze");
        VisitorClass visitorClass = new VisitorClass();
        MavenLauncher launcher = new MavenLauncher(path, MavenLauncher.SOURCE_TYPE.APP_SOURCE);
        launcher.buildModel();
        CtModel model = launcher.getModel();
        Collection<CtType<?>> allclass = model.getAllTypes();
        for (CtType<?> ctType : allclass) {
            ctType.accept(visitorClass);
        }
        System.out.println(visitorClass.getVisited());
        System.out.println(visitorClass.getCountLigne());
        


        
    }

}
