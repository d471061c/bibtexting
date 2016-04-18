package com.bibtextingcompany.domain;

import java.io.Serializable;
import java.util.Objects;

public class Article implements Serializable {

    private String tag;
    private String author;
    private String title;
    private int year;
    private String journal;
    private int volume;
    private int number;
    private String pages;

    /**
     *
     * @param tag
     * @param author
     * @param title
     * @param year
     * @param journal
     * @param volume
     * @param numbers
     * @param pages
     */
    public Article(String tag, String author, String title, int year, String journal, int volume, int numbers, String pages) {
        this.tag = tag;
        this.author = author;
        this.title = title;
        this.year = year;
        this.journal = journal;
        this.volume = volume;
        this.number = numbers;
        this.pages = pages;
    }

    public Article() {

    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getJournal() {
        return journal;
    }

    public void setJournal(String journal) {
        this.journal = journal;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getPages() {
        return pages;
    }

    public void setPages(String pages) {
        this.pages = pages;
    }

    public void setPages(int start, int end) {
        this.pages = start + "--" + end;
    }

    @Override
    public String toString() {
        return "@article{" + tag + ",\n"
                + "    author  = \"" + author + "\",\n"
                + "    title   = \"" + title + "\",\n"
                + "    year    = \"" + year + "\",\n"
                + "    journal = \"" + journal + "\",\n"
                + "    volume  = \"" + volume + "\",\n"
                + "    number  = \"" + number + "\",\n"
                + "    pages   = \"" + pages + "\"\n"
                + "}\n";
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Article other = (Article) obj;
        if (!Objects.equals(this.tag, other.tag)) {
            return false;
        }
        if (!Objects.equals(this.author, other.author)) {
            return false;
        }
        if (!Objects.equals(this.title, other.title)) {
            return false;
        }
        if (this.year != other.year) {
            return false;
        }
        if (!Objects.equals(this.journal, other.journal)) {
            return false;
        }
        if (this.volume != other.volume) {
            return false;
        }
        if (this.number != other.number) {
            return false;
        }
        if (!Objects.equals(this.pages, other.pages)) {
            return false;
        }
        return true;
    }

}
