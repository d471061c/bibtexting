package com.bibtextingcompany.domain;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;
import com.bibtextingcompany.util.Numeric;

public class Reference implements Serializable {

    public enum ReferenceType {

        ARTICLE, BOOK, INPROCEEDINGS
    }

    private final ReferenceType referenceType;
    private String tag;

    private final int ADDRESS = 0;
    private final int ANNOTE = 1;
    private final int AUTHOR = 2;
    private final int BOOKTITLE = 3;
    private final int CHAPTER = 4;
    private final int CROSSREF = 5;
    private final int EDITION = 6;
    private final int EDITOR = 7;
    private final int HOWPUBLISHED = 8;
    private final int INSTITUTION = 9;
    private final int JOURNAL = 10;
    private final int KEY = 11;
    private final int MONTH = 12;
    private final int NOTE = 13;
    private final int NUMBER = 14;
    private final int ORGANIZATION = 15;
    private final int PAGES = 16;
    private final int PUBLISHER = 17;
    private final int SCHOOL = 18;
    private final int SERIES = 19;
    private final int TITLE = 20;
    private final int TYPE = 21;
    private final int VOLUME = 22;
    private final int YEAR = 23;

    private String address;         //0
    private String annote;          //1
    private String author;          //2
    private String booktitle;       //3
    private String chapter;         //4
    private String crossref;        //5
    private String edition;         //6
    private String editor;          //7
    private String howpublished;    //8
    private String institution;     //9
    private String journal;         //10
    private String key;             //11
    private String month;           //12
    private String note;            //13
    private Integer number;         //14
    private String organization;    //15
    private String pages;           //16
    private String publisher;       //17
    private String school;          //18
    private String series;          //19
    private String title;           //20
    private String type;            //21
    private Integer volume;         //22
    private Integer year;           //23
    public final String[] paramNames = {"address", "annote", "author", "booktitle",
        "chapter", "crossref", "edition", "editor", "howpublished", "institution", "journal",
        "key", "month", "note", "number", "organization", "pages", "publisher", "school", "series", "title",
        "type", "volume", "year"};

    public String getTag() {
        return this.tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getTitle() {
        return this.title;
    }

    public Reference(ReferenceType referenceType) {
        this.referenceType = referenceType;
        this.placeholderize();
    }

    public int[] requiredParameters() {
        int[] required = null;
        switch (this.referenceType) {
            case ARTICLE:
                required = new int[]{AUTHOR, TITLE, JOURNAL, YEAR, VOLUME};
                break;
            case BOOK:
                required = new int[]{AUTHOR, EDITOR, TITLE, PUBLISHER, YEAR};
                break;
            case INPROCEEDINGS:
                required = new int[]{AUTHOR, TITLE, BOOKTITLE, YEAR};
                break;
        }
        return required;
    }

    public int[] optionalParameters() {
        int[] optional = null;
        switch (this.referenceType) {
            case ARTICLE:
                optional = new int[]{NUMBER, PAGES, MONTH, NOTE, KEY};
                break;
            case BOOK:
                optional = new int[]{VOLUME, NUMBER, SERIES, ADDRESS, EDITION, MONTH, NOTE, KEY};
                break;
            case INPROCEEDINGS:
                optional = new int[]{EDITOR, VOLUME, NUMBER, SERIES, PAGES, ADDRESS, MONTH, ORGANIZATION, PUBLISHER, NOTE, KEY};
                break;
        }
        return optional;
    }

    public void setParameters(String[] params) {
        for (int param = 0; param < params.length; param++) {
            switch (param) {
                case 0:
                    this.address = params[param];
                    break;
                case 1:
                    this.annote = params[param];
                    break;
                case 2:
                    this.author = params[param];
                    break;
                case 3:
                    this.booktitle = params[param];
                    break;
                case 4:
                    this.chapter = params[param];
                    break;
                case 5:
                    this.crossref = params[param];
                    break;
                case 6:
                    this.edition = params[param];
                    break;
                case 7:
                    this.editor = params[param];
                    break;
                case 8:
                    this.howpublished = params[param];
                    break;
                case 9:
                    this.institution = params[param];
                    break;
                case 10:
                    this.journal = params[param];
                    break;
                case 11:
                    this.key = params[param];
                    break;
                case 12:
                    this.month = params[param];
                    break;
                case 13:
                    this.note = params[param];
                    break;
                case 14:
                    if (params[param] != null) {
                        if (Numeric.confirmInteger(params[param])) {
                        this.number = Integer.parseInt(params[param]);
                        } else {
                        this.number = 666;    
                        }
                    }
                    break;
                case 15:
                    this.organization = params[param];
                    break;
                case 16:
                    this.pages = params[param];
                    break;
                case 17:
                    this.publisher = params[param];
                    break;
                case 18:
                    this.school = params[param];
                    break;
                case 19:
                    this.series = params[param];
                    break;
                case 20:
                    this.title = params[param];
                    break;
                case 21:
                    this.type = params[param];
                    break;
                case 22:
                    if (params[param] != null) {
                        if (Numeric.confirmInteger(params[param])) {
                        this.volume = Integer.parseInt(params[param]);
                        } else {
                        this.volume = 666;    
                        }
                    }
                    break;
                case 23:
                    if (params[param] != null) {
                        if (Numeric.confirmInteger(params[param])) {
                        this.year = Integer.parseInt(params[param]);
                        } else {
                        this.year = 666;    
                        }
                    }
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * Creates placeholder data set for the reference.
     * This is to prevent any non-functional (crash-prone) references being created.
     */
    public void placeholderize() {
        String[] params = new String[24];
        int[] required;
        int[] optional;
        required = this.requiredParameters();
        optional = this.optionalParameters();
         
        
        for (int i = 0; i<required.length; i++) {
            
                params[required[i]]="Placeholder";
                
            if (required[i]==4 || required[i] == 12 || required[i] == 14 || required[i] == 22 || required[i] == 23) {
                params[required[i]]="10";
            }
            if (required[i]==16) {
                params[required[i]]="15--25";
            }
        }
        
         for (int i = 0; i<optional.length; i++) {
                params[optional[i]]="Placeholder";
                
            if (optional[i]==4 || optional[i] == 12 || optional[i] == 14 || optional[i] == 22 || optional[i] == 23) {
                params[optional[i]]="10";
            }
            if (optional[i]==16) {
                params[optional[i]]="15--25";
            }
        }
        
        
        this.setParameters(params);
        
        this.setTag("placeholder_tag");
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("@").append(this.referenceType.toString().toLowerCase()).append("{").append(tag).append(",\n");
        if (this.address != null) {
            sb.append("    address  = \"").append(this.address).append("\",\n");
        }
        if (this.annote != null) {
            sb.append("    annote  = \"").append(this.annote).append("\",\n");
        }
        if (this.author != null) {
            sb.append("    author  = \"").append(this.author).append("\",\n");
        }
        if (this.booktitle != null) {
            sb.append("    booktitle  = \"").append(this.booktitle).append("\",\n");
        }
        if (this.chapter != null) {
            sb.append("    chapter  = \"").append(this.chapter).append("\",\n");
        }
        if (this.crossref != null) {
            sb.append("    crossref  = \"").append(this.crossref).append("\",\n");
        }
        if (this.edition != null) {
            sb.append("    edition  = \"").append(this.edition).append("\",\n");
        }
        if (this.editor != null) {
            sb.append("    editor  = \"").append(this.editor).append("\",\n");
        }
        if (this.howpublished != null) {
            sb.append("    howpublished  = \"").append(this.howpublished).append("\",\n");
        }
        if (this.institution != null) {
            sb.append("    institution  = \"").append(this.institution).append("\",\n");
        }
        if (this.journal != null) {
            sb.append("    journal  = \"").append(this.journal).append("\",\n");
        }
        if (this.key != null) {
            sb.append("    key  = \"").append(this.key).append("\",\n");
        }
        if (this.month != null) {
            sb.append("    month  = \"").append(this.month).append("\",\n");
        }
        if (this.note != null) {
            sb.append("    note  = \"").append(this.note).append("\",\n");
        }
        if (this.number != null) {
            sb.append("    number  = \"").append(this.number).append("\",\n");
        }
        if (this.pages != null) {
            sb.append("    pages  = \"").append(this.pages).append("\",\n");
        }
        if (this.publisher != null) {
            sb.append("    publisher  = \"").append(this.publisher).append("\",\n");
        }
        if (this.school != null) {
            sb.append("    school  = \"").append(this.school).append("\",\n");
        }
        if (this.series != null) {
            sb.append("    series  = \"").append(this.series).append("\",\n");
        }
        if (this.title != null) {
            StringBuilder append = sb.append("    title  = \"").append(this.title).append("\",\n");
        }
        if (this.type != null) {
            sb.append("    type  = \"").append(this.type).append("\",\n");
        }
        if (this.volume != null) {
            sb.append("    volume  = \"").append(this.volume).append("\",\n");
        }
        if (this.year != null) {
            sb.append("    year  = \"").append(this.year).append("\",\n");
        }
        sb.append("}\n");
        return sb.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Reference other = (Reference) obj;
        if (this.referenceType != other.referenceType) {
            return false;
        }
        if (!Objects.equals(this.address, other.address)) {
            return false;
        }
        if (!Objects.equals(this.annote, other.annote)) {
            return false;
        }
        if (!Objects.equals(this.author, other.author)) {
            return false;
        }
        if (!Objects.equals(this.booktitle, other.booktitle)) {
            return false;
        }
        if (!Objects.equals(this.chapter, other.chapter)) {
            return false;
        }
        if (!Objects.equals(this.crossref, other.crossref)) {
            return false;
        }
        if (!Objects.equals(this.edition, other.edition)) {
            return false;
        }
        if (!Objects.equals(this.editor, other.editor)) {
            return false;
        }
        if (!Objects.equals(this.howpublished, other.howpublished)) {
            return false;
        }
        if (!Objects.equals(this.institution, other.institution)) {
            return false;
        }
        if (!Objects.equals(this.journal, other.journal)) {
            return false;
        }
        if (!Objects.equals(this.key, other.key)) {
            return false;
        }
        if (!Objects.equals(this.month, other.month)) {
            return false;
        }
        if (!Objects.equals(this.note, other.note)) {
            return false;
        }
        if (!Objects.equals(this.number, other.number)) {
            return false;
        }
        if (!Objects.equals(this.organization, other.organization)) {
            return false;
        }
        if (!Objects.equals(this.pages, other.pages)) {
            return false;
        }
        if (!Objects.equals(this.publisher, other.publisher)) {
            return false;
        }
        if (!Objects.equals(this.school, other.school)) {
            return false;
        }
        if (!Objects.equals(this.series, other.series)) {
            return false;
        }
        if (!Objects.equals(this.title, other.title)) {
            return false;
        }
        if (!Objects.equals(this.type, other.type)) {
            return false;
        }
        if (!Objects.equals(this.volume, other.volume)) {
            return false;
        }
        if (!Objects.equals(this.year, other.year)) {
            return false;
        }
        if (!Arrays.deepEquals(this.paramNames, other.paramNames)) {
            return false;
        }
        return true;
    }

    
    
}
