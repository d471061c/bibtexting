package com.bibtextingcompany.util;

import com.bibtextingcompany.bibtexting.IO;
import com.bibtextingcompany.domain.Reference;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 *
 */
public class FinePrint {
    
    public static String process(Reference reference, IO io) {
             StringBuilder sb = new StringBuilder();
             String content = reference.toString();
            Scanner scanner = new Scanner(content);
            io.print("______________________________________________\n");
            io.print(reference.getReferenceType().toString().toUpperCase()+" (tag: "+reference.getTag()+")\n");
            io.print("----------------------------------------------\n");
            scanner.nextLine(); // skips the first line containing the reference type (which was just printed out)
            
            while (true) {
                String identifier = scanner.next();
                if (identifier.contentEquals("}")) {
                    break;
                }
                int identifierLength=identifier.length();
                for (int i = identifierLength; i<20; i++) {
                    identifier=identifier+".";
                }
                
                String line = scanner.nextLine();
                
                
                String newLine ="";
                newLine = line.replaceAll(Pattern.quote("\\\"{a}"), "ä");
                                line = newLine;
                newLine = line.replaceAll(Pattern.quote("\\\"{A}"), "Ä");
                                line = newLine;
                newLine = line.replaceAll(Pattern.quote("\\\"{o}"), "ö");
                                line = newLine;
                newLine = line.replaceAll(Pattern.quote("\\\"{O}"), "Ö");
                                line = newLine;
                newLine = line.replaceAll(Pattern.quote("\\aa"), "å");
                                line = newLine;
                newLine = line.replaceAll(Pattern.quote("\\AA"), "Å");
                                line = newLine;
             //   System.out.println("ident: "+identifier+", line: "+line);
                if (line==null) {break;} else {
                    
                    line=line.substring(0, line.length()-2);
                    line=line.replaceFirst("  = \"", "");
                    sb.append(identifier.toUpperCase()+" "+line+"\n");
                    io.print(identifier.toUpperCase()+" "+line+"\n");
                    //sb.append(line);
                    //sb.append(System.getProperty("line.separator"));}
            }
            
        
        //return sb.toString();
    }
            io.print("______________________________________________\n");
            
            return sb.toString();
    }
}
