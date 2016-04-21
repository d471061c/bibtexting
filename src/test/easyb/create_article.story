import com.bibtextingcompany.bibtexting.*
import com.bibtextingcompany.domain.Reference

description 'User can add an article reference to the database'

scenario "New Article is saved", {
    given 'command add selected', {
        refDB = new ReferenceDatabase("DATABASE_TEST")
        io = new StubIO("add", "1", "author", "title", "journal", "1993", "13", "1", "2-3", "may", "note", "key", "exit")
        ui = new ConsoleUI(io, refDB)
    }

    when 'it contains proper type of data', {
        ui.run()
    }

    then 'the user is informed that the save was successful', {
        io.getPrints().shouldHave("Success: Reference added.")
        io.getPrints().shouldNotHave("Error: one or more paramters were invalid. Reference was not saved.")
        refDB.clearDatabase()
    }
}

scenario "New Article is saved", {
    given 'command add selected', {
        refDB = new ReferenceDatabase("DATABASE_TEST")
        io = new StubIO("add", "1", "author", "title", "journal", "1993", "13", "", "", "", "", "", "exit")
        ui = new ConsoleUI(io, refDB)
    }

    when 'it contains valid required parameters and no optional parameters', {
        ui.run()
    }

    then 'the user is informed that the save was successful', {
        io.getPrints().shouldHave("Success: Reference added.")
        io.getPrints().shouldNotHave("Error: one or more paramters were invalid. Reference was not saved.")
        refDB.clearDatabase()
    }
}

scenario "New Article is not saved", {
    given 'command add selected', {
        refDB = new ReferenceDatabase("DATABASE_TEST")
        io = new StubIO("add", "1", "author", "title", "journal", "year", "13", "1", "2-3", "may", "note", "key", "exit")
        ui = new ConsoleUI(io, refDB)
    }

    when 'required parameters contain invalid data', {
        ui.run()
    }

    then 'the user is informed that the save was not successful', {
        io.getPrints().shouldNotHave("Success: Reference added.")
        io.getPrints().shouldHave("Error: one or more paramters were invalid. Reference was not saved.")
        refDB.clearDatabase()
    }
}

scenario "New Article is not saved", {
    given 'command add selected', {
        refDB = new ReferenceDatabase("DATABASE_TEST")
        io = new StubIO("add", "1", "author", "title", "journal", "1993", "13", "1", "2-3", "1", "note", "key", "exit")
        ui = new ConsoleUI(io, refDB)
    }

    when 'optional parameters contain invalid data', {
        ui.run()
    }

    then 'the user is informed that the save was not successful', {
        io.getPrints().shouldNotHave("Success: Reference added.")
        io.getPrints().shouldHave("Error: one or more paramters were invalid. Reference was not saved.")
        refDB.clearDatabase()
    }
}