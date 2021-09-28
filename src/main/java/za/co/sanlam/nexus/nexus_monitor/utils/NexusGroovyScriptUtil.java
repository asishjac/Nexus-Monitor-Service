package za.co.sanlam.nexus.nexus_monitor.utils;

import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

@Component
public class NexusGroovyScriptUtil {

    public String readGroovyScriptFile(File groovyScriptFile){
        String data="";
        StringBuffer sb = new StringBuffer();
        boolean commentLineIndicator=false;
        try {
            Scanner fileReader = new Scanner(groovyScriptFile);
            fileReader.useDelimiter("\\Z");
           // while(fileReader.hasNextLine()){

                //sb.append(fileReader.nextLine().replace('\n',' ').trim());

           // }
            data = fileReader.next();
            fileReader.close();
           // data=sb.toString().trim();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return data;
    }

}
