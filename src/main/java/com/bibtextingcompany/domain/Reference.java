package com.bibtextingcompany.domain;

import com.bibtextingcompany.bibtexting.ParameterPolice;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;
import com.bibtextingcompany.util.Numeric;

public class Reference implements Serializable {

    public enum ReferenceType {

        ARTICLE, BOOK, BOOKLET, CONFERENCE, INBOOK, INCOLLECTION, INPROCEEDINGS,
        MANUAL, MASTERSTHESIS, MISC, PHDTHESIS, PROCEEDINGS, TECHREPORT, UNPUBLISHED

    }

    private final ReferenceType referenceType;
    private String tag;

    public final static int ADDRESS = 0;
    public final static int ANNOTE = 1;
    public final static int AUTHOR = 2;
    public final static int BOOKTITLE = 3;
    public final static int CHAPTER = 4;
    public final static int CROSSREF = 5;
    public final static int EDITION = 6;
    public final static int EDITOR = 7;
    public final static int HOWPUBLISHED = 8;
    public final static int INSTITUTION = 9;
    public final static int JOURNAL = 10;
    public final static int KEY = 11;
    public final static int MONTH = 12;
    public final static int NOTE = 13;
    public final static int NUMBER = 14;
    public final static int ORGANIZATION = 15;
    public final static int PAGES = 16;
    public final static int PUBLISHER = 17;
    public final static int SCHOOL = 18;
    public final static int SERIES = 19;
    public final static int TITLE = 20;
    public final static int TYPE = 21;
    public final static int VOLUME = 22;
    public final static int YEAR = 23;

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
        System.out.println(toString());
    }

    public int[][] parameters() {
        int[] required = null;
        int[] optional = null;
        switch (this.referenceType) {
            case ARTICLE:
                required = new int[]{AUTHOR, TITLE, JOURNAL, YEAR, VOLUME};
                optional = new int[]{NUMBER, PAGES, MONTH, NOTE, KEY};
                break;
            case BOOK:
                required = new int[]{AUTHOR, EDITOR, TITLE, PUBLISHER, YEAR};
                optional = new int[]{VOLUME, NUMBER, SERIES, ADDRESS, EDITION, MONTH, NOTE, KEY};
                break;
            case BOOKLET:
                required = new int[]{TITLE};
                optional = new int[]{AUTHOR, HOWPUBLISHED, ADDRESS, MONTH, YEAR};
                break;
            case CONFERENCE:
                required = new int[]{AUTHOR, TITLE, BOOKTITLE, YEAR};
                optional = new int[]{EDITOR, VOLUME, NUMBER, SERIES, PAGES, ADDRESS, MONTH, ORGANIZATION, PUBLISHER, NOTE};
                break;
            case INBOOK:
                required = new int[]{AUTHOR, EDITOR, TITLE, CHAPTER, PAGES};
                optional = new int[]{VOLUME, NUMBER, SERIES, TYPE, ADDRESS, EDITION, MONTH, NOTE};
                break;
            case INCOLLECTION:
                required = new int[]{AUTHOR, TITLE, BOOKTITLE, PUBLISHER, YEAR};
                optional = new int[]{EDITOR, VOLUME, NUMBER, SERIES, TYPE, CHAPTER, PAGES, ADDRESS, EDITION, MONTH, NOTE};
                break;
            case INPROCEEDINGS:
                required = new int[]{AUTHOR, TITLE, BOOKTITLE, YEAR};
                optional = new int[]{EDITOR, VOLUME, NUMBER, SERIES, PAGES, ADDRESS, MONTH, ORGANIZATION, PUBLISHER, NOTE, KEY};
                break;
            case MANUAL:
                required = new int[]{TITLE};
                optional = new int[]{AUTHOR, ORGANIZATION, ADDRESS, EDITION, MONTH, YEAR, NOTE};
                break;
            case MASTERSTHESIS:
                required = new int[]{AUTHOR, TITLE, SCHOOL, YEAR};
                optional = new int[]{TYPE, ADDRESS, MONTH, NOTE};
                break;
            case MISC:
                required = new int[]{};
                optional = new int[]{AUTHOR, TITLE, HOWPUBLISHED, MONTH, YEAR, NOTE};
                break;
            case PHDTHESIS:
                required = new int[]{AUTHOR, TITLE, SCHOOL, YEAR};
                optional = new int[]{TYPE, ADDRESS, MONTH, NOTE};
                break;
            case PROCEEDINGS:
                required = new int[]{TITLE, YEAR};
                optional = new int[]{EDITOR, VOLUME, NUMBER, SERIES, ADDRESS, MONTH, ORGANIZATION, PUBLISHER, NOTE};
                break;
            case TECHREPORT:
                required = new int[]{AUTHOR, TITLE, INSTITUTION, YEAR};
                optional = new int[]{TYPE, NUMBER, ADDRESS, MONTH, NOTE};
                break;
            case UNPUBLISHED:
                required = new int[]{AUTHOR, TITLE, NOTE};
                optional = new int[]{MONTH, YEAR};
                break;
        }
        int[][] parameters = {required, optional};
        return parameters;
    }

    public int[] requiredParameters() {
        return this.parameters()[0];
    }

    public int[] optionalParameters() {
        return this.parameters()[1];
    }

    /**
     * Sets new parameters for a reference. Runs a parsing AND cleaning for the
     * inputs first.
     *
     * @param params new set of Params
     * @return returns params WITH error messages. The error messages can be
     * used to advice the user.
     */
    public String[] setParameters(String[] params) {

        String[] processedParamsWithErrors = ParameterPolice.process(params);
        params = ParameterPolice.clean(processedParamsWithErrors);

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

        return processedParamsWithErrors;
    }

    /**
     * Creates placeholder data set for the reference. This is to prevent any
     * non-functional (crash-prone) references being created.
     */
    public void placeholderize() {
        String[] params = new String[24];
        int[] required;
        int[] optional;
        required = this.requiredParameters();
        optional = this.optionalParameters();

        for (int i = 0; i < required.length; i++) {

            params[required[i]] = "Placeholder";

            if (required[i] == 4 || required[i] == 12 || required[i] == 14 || required[i] == 22 || required[i] == 23) {
                params[required[i]] = "10";
            }
            if (required[i] == 16) {
                params[required[i]] = "15--25";
            }
        }

        for (int i = 0; i < optional.length; i++) {
            params[optional[i]] = "Placeholder";

            if (optional[i] == 4 || optional[i] == 12 || optional[i] == 14 || optional[i] == 22 || optional[i] == 23) {
                params[optional[i]] = "10";
            }
            if (optional[i] == 16) {
                params[optional[i]] = "15--25";
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
