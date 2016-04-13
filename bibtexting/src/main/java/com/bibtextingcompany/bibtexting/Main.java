
package com.bibtextingcompany.bibtexting;

import com.bibtextingcompany.domain.Article;

public class Main {
    
    public static void main(String[] args) {
        
//        String testi = "Yölevi Äänekoski: Validating Strings in BibteX (1995)";
//        System.out.println("Validoidaan string: "+testi);
//        System.out.println("Tulos: "+Validator.Validate(testi));
    
    //  public Article(String tag, String author, String title, int year, String journal, int volume, int numbers, String pages) {
  
        String tag = "a01";
        String author = "Yölevi Äänekoski";
        String title = "Validating Strings in BibteX";
        String year = "1995";
        String journal = "Useless Proceedings in Computer Science";
        String volume = "3";
        String number = "4";
        String pages = "15 - 25";
        String empty = "";
        
        Article article = new Article();
        
        if (DataValidator.Validate(tag, DataValidator.TEXT)==DataValidator.TEXT) {
            article.setTag(tag);
        } else {
            article.setTag("Unknown");
        }
        
        if (DataValidator.Validate(title, DataValidator.TEXT)==DataValidator.TEXT) {
            article.setTitle(title);
        } else {
            article.setTitle("Unknown Title");
        }
        
        if (DataValidator.Validate(author, DataValidator.TEXT)==DataValidator.TEXT) {
            article.setAuthor(StringValidator.Validate(author));
        } else {
            article.setAuthor("Unknown Author");
        }
        
        if (DataValidator.Validate(journal, DataValidator.TEXT)==DataValidator.TEXT) {
            article.setJournal(journal);
        } else {
            article.setJournal("Unknown Journal");
        }
        
        if (DataValidator.Validate(pages, DataValidator.RANGE_OF_NUMBERS)==DataValidator.RANGE_OF_NUMBERS) {
            article.setPages(pages);
        } else if (DataValidator.Validate(pages, DataValidator.SINGLE_NUMBER)==DataValidator.SINGLE_NUMBER) {
            article.setPages(pages);
        } else {article.setPages("Page Unknown");
        }
        
        if (DataValidator.Validate(year, DataValidator.SINGLE_NUMBER)==DataValidator.SINGLE_NUMBER) {
            article.setYear(Integer.parseInt(year));
        }
        if (DataValidator.Validate(number, DataValidator.SINGLE_NUMBER)==DataValidator.SINGLE_NUMBER) {
            article.setNumber(Integer.parseInt(number));
        }
        if (DataValidator.Validate(volume, DataValidator.SINGLE_NUMBER)==DataValidator.SINGLE_NUMBER) {
            article.setVolume(Integer.parseInt(volume));
        } 
    
//        System.out.println(article);
        
        ReferenceDatabase refDB = new ReferenceDatabase();
        refDB.add(article);
        ConsoleUI ui = new ConsoleUI(new ConsoleIO(), refDB);
        ui.run();
    }
    
}
