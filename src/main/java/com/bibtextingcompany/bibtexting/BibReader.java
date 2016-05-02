package com.bibtextingcompany.bibtexting;

import com.bibtextingcompany.domain.Reference;
import com.bibtextingcompany.util.StringToType;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Reads a properly formatted BibteX file into Bibtexting reference objects.
 * These objects will get "dumbed down", i.e. they will not have proper
 * parameters, only a recreation of the original toString().
 */
public class BibReader {

    private static boolean newRefFound;
    private static boolean refTypeDetected;
    private static boolean refTagDetected;
    private static boolean refTitleDetected;
    private static boolean titleWritten;
    private static StringBuilder sbFullContent;
    private static String tag;
    private static String title;

    /**
     * * Reads a properly formatted BibteX file into Bibtexting reference
     * objects.
     *
     * @param content A string read from a BibteX file.
     * @return List of <Reference>s
     */
    public static List<Reference> readToReference(String content) {
        ArrayList<Reference> newReferences = new ArrayList<Reference>();
        StringBuilder sb = new StringBuilder();
        initReader();
        Reference.ReferenceType refType = null;

        System.out.println(content);
        //    System.out.println(newReferences.size()+" lisätty");
        return contentParser(content, sb, refType, newReferences);
    }

    private static List<Reference> contentParser(String content, StringBuilder sb,
            Reference.ReferenceType refType, ArrayList<Reference> newReferences) {
        for (int i = 0; i < content.length(); i++) {

            if (content.charAt(i) == '@' || i == content.length() - 1) {
                if (sbFullContent.length() > 1) {
                    //    System.out.println("Yritetään kirjoittaa...\n"+sbFullContent.toString());
                    if (!fetchTitle()) return null;

                    newReferences.add(buildDumbReference(refType));
                }
            } else if (!refTypeDetected && content.charAt(i) == '{') {
                refType = fetchRefType(sb);
                sb = new StringBuilder();
            } else if (!refTagDetected && content.charAt(i) != ',') {
                sb.append(content.charAt(i));
            } else if (!refTagDetected) {
                fetchTag(sb);
                sb = new StringBuilder();
            }

            sbFullContent.append(content.charAt(i));
        }
        return newReferences;
    }

    private static void fetchTag(StringBuilder sb) {
        refTagDetected = true;
        tag = sb.toString() + "-dumb";
    }

    private static Reference.ReferenceType fetchRefType(StringBuilder sb) {
        refTypeDetected = true;
        Reference.ReferenceType rT = StringToType.convert(sb.toString());
        return rT;
    }

    private static boolean fetchTitle() {
        int i1 = sbFullContent.indexOf("title");
        int i2 = sbFullContent.indexOf("\"", i1 - 1);
        if (i1 == -1) {
            return false;
        }
        title = sbFullContent.substring(i2 + 1, sbFullContent.indexOf("\",", i2));
        //   System.out.println("titleksi saatiin: "+title);
        return true;
    }

    private static void initReader() {
        refTypeDetected = false;
        refTagDetected = false;
        refTitleDetected = false;
        titleWritten = false;
        sbFullContent = new StringBuilder();
        tag = "";
        title = "";
    }

    private static Reference buildDumbReference(Reference.ReferenceType refType) {
        Reference recreation = new Reference(refType);
        recreation.setDumb(true);
        recreation.setTag(tag);
        recreation.setTitle(title);
        recreation.setFullContent(sbFullContent.toString());
        initReader();
        return recreation;
    }

}
