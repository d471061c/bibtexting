
package com.bibtextingcompany.bibtexting;

public class Main {
    
    public static void main(String[] args) {
     
        
        String testi = "Yölevi Äänekoski: Validating Strings in BibteX (1995)";
        System.out.println("Validoidaan string: "+testi);
        System.out.println("Tulos: "+Validator.Validate(testi));
    }
    
}
