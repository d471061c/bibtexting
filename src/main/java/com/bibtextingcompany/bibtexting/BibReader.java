package com.bibtextingcompany.bibtexting;

import com.bibtextingcompany.domain.Reference;
import com.bibtextingcompany.util.StringToType;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Reads a properly formatted BibteX file into Bibtexting reference objects.
 * These objects will get "dumbed down", i.e. they will not have proper parameters,
 * only a recreation of the original toString().
 */
public class BibReader {

    /**
     * * Reads a properly formatted BibteX file into Bibtexting reference objects.
     * @param content A string read from a BibteX file.
     * @return  List of <Reference>s
     */
    public static List<Reference> readToReference(String content) {
        if (content.length()<10) {
            return null;
        }
        ArrayList<Reference> newReferences = new ArrayList<Reference>();

        StringBuilder sb = new StringBuilder();
        StringBuilder sbFullContent = new StringBuilder();
        boolean newRefFound = false;
        boolean refTypeDetected = false;
        boolean refTagDetected = false;
        boolean refTitleDetected = false;
        boolean titleWritten = false;
        Reference.ReferenceType refType = null;
        String tag = "";
        String title = "";

        for (int i = 0; i < content.length(); i++) {

            if (content.charAt(i) == '@' || i == content.length() - 1) {
                newRefFound = true;
                if (sbFullContent.length() > 1) {
                //    System.out.println("Yritetään kirjoittaa...\n"+sbFullContent.toString());

                    Scanner scanner = new Scanner(sbFullContent.toString());
                    int i1 = sbFullContent.indexOf("title");
                    int i2 = sbFullContent.indexOf("\"", i1 - 1);
                    title = sbFullContent.substring(i2 + 1, sbFullContent.indexOf("\",", i2));
                 //   System.out.println("titleksi saatiin: "+title);

                    Reference recreation = new Reference(refType);
                    recreation.setDumb(true);
                    recreation.setTag(tag);
                    recreation.setTitle(title);
                    recreation.setFullContent(sbFullContent.toString());
                    newReferences.add(recreation);
                    sbFullContent = new StringBuilder();
                    refTypeDetected = false;
                    refTagDetected = false;
                    refTitleDetected = false;
                    titleWritten = false;
                    title = "";
                    tag = "";
                }
            } else if (newRefFound && !refTypeDetected && content.charAt(i) == '{') {
                refTypeDetected = true;
                refType = StringToType.convert(sb.toString());
                sb = new StringBuilder();
            } else if (!refTagDetected && content.charAt(i) != ',') {
                sb.append(content.charAt(i));
            } else if (!refTagDetected) {
                tag = sb.toString() + "-dumb";
                sb = new StringBuilder();
                refTagDetected = true;
            }

            sbFullContent.append(content.charAt(i));
        }

        //    System.out.println(newReferences.size()+" lisätty");
        return newReferences;
    }
}
