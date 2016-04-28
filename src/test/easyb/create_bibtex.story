import com.bibtextingcompany.bibtexting.*
import com.bibtextingcompany.domain.Reference

description 'User can create a BibTeX file from references saved in the database'

scenario "New BibTeX file is created", {
    given 'command create selected', {
        refDB = new ReferenceDatabase("DATABASE_TEST")
        io = new StubIO("add", "1", "author", "title", "journal", "1993", "13", "1", "2-3", "may", "note", "key", "create", "bibtest", "exit")
        ui = new ConsoleUI(io, refDB)
    }

    when 'a proper file name is given', {
        ui.run()
    }

    then 'the user is informed that the save was successful', {
        io.getPrints().shouldHave("BibTeX file successfully created!")
        io.getPrints().shouldNotHave("Writing to file failed")
        refDB.clearDatabase()
    }
}

scenario "Filename with illegal characters is corrected", {
    given 'command create selected', {
        refDB = new ReferenceDatabase("DATABASE_TEST")
        io = new StubIO("add", "1", "author", "title", "journal", "1993", "13", "1", "2-3", "may", "note", "key", "create", "b<i>b.t*est", "exit")
        ui = new ConsoleUI(io, refDB)
    }

    when 'a inproper file name is given', {
        ui.run()
    }

    then 'the user is informed that the file name was corrected and the save was successful', {
        io.getPrints().shouldHave("Filename corrected from b<i>b.t*est to bibtest") 
        io.getPrints().shouldHave("BibTeX file successfully created!")
        io.getPrints().shouldNotHave("Writing to file failed")
        refDB.clearDatabase()
    }
}

scenario "Filename which is too short is replaced", {
    given 'command create selected', {
        refDB = new ReferenceDatabase("DATABASE_TEST")
        io = new StubIO("add", "1", "author", "title", "journal", "1993", "13", "1", "2-3", "may", "note", "key", "create", "ab", "exit")
        ui = new ConsoleUI(io, refDB)
    }

    when 'a too short of a file name is given', {
        ui.run()
    }

    then 'the user is informed that the file name was corrected and the save was successful', {
        io.getPrints().shouldHave("Filename corrected from ab to filename_too_short") 
        io.getPrints().shouldHave("BibTeX file successfully created!")
        io.getPrints().shouldNotHave("Writing to file failed")
        refDB.clearDatabase()
    }
}