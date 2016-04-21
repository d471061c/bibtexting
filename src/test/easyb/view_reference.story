import com.bibtextingcompany.bibtexting.*
import com.bibtextingcompany.domain.Reference

description 'User can search (by title) and view a specific reference in the database'

scenario "user can view a specific reference with a matching title", {
    given 'command view selected', {
        refDB = new ReferenceDatabase("DATABASE_TEST")
        refDB.add(ReferenceCreator.createArticle("John Doe", "The Future of BibteX", "2015", "The Computer Science Journal", "10"))
        refDB.add(ReferenceCreator.createArticle("Jane Doe", "BibteX and You", "2011", "Useless Proceedings in Computer Science", "5"))      
        io = new StubIO("view", "    the future of bibtex   ", "exit")
        ui = new ConsoleUI(io, refDB)
    }

    when 'title to be searched is entered', {
        ui.run()
    }

    then 'a matching reference is returned', {
        io.getPrints().shouldHave("The Future of BibteX")
        io.getPrints().shouldNotHave("BibteX and You")
        refDB.clearDatabase()
    }
}

scenario "user can view multiple references with the same title", {
    given 'command view selected', {
        refDB = new ReferenceDatabase("DATABASE_TEST");
        refDB.add(ReferenceCreator.createArticle("John Doe", "The Future of BibteX", "2015", "The Computer Science Journal", "10"))
        refDB.add(ReferenceCreator.createBook("Jane Doe", "Editor234", "The Future of BibteX", "publisher", "1995")) 
        io = new StubIO("view", "the future of bibtex", "exit")
        ui = new ConsoleUI(io, refDB)
    }

    when 'title to be searched is entered', {
        ui.run()
    }

    then 'the matching references are returned', {
        io.getPrints().shouldHave("John Doe")
        io.getPrints().shouldHave("Jane Doe")
        refDB.clearDatabase()
    }
}

scenario "user can view an reference which has unicode characters", {
    given 'command view selected', {
        refDB = new ReferenceDatabase("DATABASE_TEST");
        refDB.add(ReferenceCreator.createArticle("John Doe", "The Future of BibteX", "2015", "The Computer Science Journal", "10"))
        refDB.add(ReferenceCreator.createInproceedings("Janne Johnson", "Sinä ja BibteX", "booktitle", "1995")) 
        io = new StubIO("view", "   sinä ja bibtex   ", "exit")
        ui = new ConsoleUI(io, refDB)
    }

    when 'title to be searched is entered', {
        ui.run()
    }

    then 'a matching reference is returned', {
        io.getPrints().shouldHave("Sinä ja BibteX")
        io.getPrints().shouldNotHave("The Future of BibteX")
        refDB.clearDatabase()
    }
}

scenario "if no reference is found with a matching title then nothing is returned", {
    given 'command view selected', {
        refDB = new ReferenceDatabase("DATABASE_TEST");
        refDB.add(ReferenceCreator.createBook("John Doe", "editor", "The Future of BibteX", "The Computer Science Publisher", "2010"))
        io = new StubIO("view", "   bibtex and you   ", "exit")
        ui = new ConsoleUI(io, refDB)
    }

    when 'title to be searched is entered', {
        ui.run()
    }

    then 'no references are returned', {
        io.getPrints().shouldHave("No references found with the specified search terms!")
        refDB.clearDatabase()
    }
}