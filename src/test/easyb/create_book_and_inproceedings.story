import com.bibtextingcompany.bibtexting.*
import com.bibtextingcompany.domain.Reference

description 'User can add an book reference to the database'

scenario "New book is saved", {
    given 'command add selected', {
        refDB = new ReferenceDatabase("DATABASE_TEST")
        io = new StubIO("add", "2", "author", "editor", "title", "publisher", "1998", "23", "22", "series", "address", "edition", "month", "note", "key", "exit")
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

scenario "New book", {
    given 'command add selected', {
        refDB = new ReferenceDatabase("DATABASE_TEST")
        io = new StubIO("add", "2", "author", "editor", "title", "publisher", "1998", "", "", "", "", "", "", "", "", "exit")
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

scenario "New book is not saved", {
    given 'command add selected', {
        refDB = new ReferenceDatabase("DATABASE_TEST")
        io = new StubIO("add", "2", "author", "editor", "title", "publisher", "year", "23", "22", "series", "address", "edition", "month", "note", "key", "exit")
        ui = new ConsoleUI(io, refDB)
    }

    when 'it contains invalid data', {
        ui.run()
    }

    then 'the user is informed that the save was not successful', {
        io.getPrints().shouldNotHave("Success: Reference added.")
        io.getPrints().shouldHave("Error: one or more paramters were invalid. Reference was not saved.")
        refDB.clearDatabase()
    }
}

scenario "New book is not saved", {
    given 'command add selected', {
        refDB = new ReferenceDatabase("DATABASE_TEST")
        io = new StubIO("add", "2", "author", "editor", "title", "publisher", "1998", "23", "22", "123", "address", "edition", "month", "note", "key", "exit")
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

scenario "New inproceedings is saved", {
    given 'command add selected', {
        refDB = new ReferenceDatabase("DATABASE_TEST")
        io = new StubIO("add", "7", "author", "title", "booktitle", "1995", "editor", "23", "22", "series", "23-45", "address", "month", "organization", "publisher", "note", "key", "exit")
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

scenario "New inproceedings is saved", {
    given 'command add selected', {
        refDB = new ReferenceDatabase("DATABASE_TEST")
        io = new StubIO("add", "7", "author", "title", "booktitle", "1995", "", "", "", "", "", "", "", "", "", "", "", "exit")
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

scenario "New inproceedings is not saved", {
    given 'command add selected', {
        refDB = new ReferenceDatabase("DATABASE_TEST")
        io = new StubIO("add", "7", "author", "title", "booktitle", "year", "editor", "23", "22", "series", "23-45", "address", "month", "organization", "publisher", "note", "key", "exit")
        ui = new ConsoleUI(io, refDB)
    }

    when 'it contains invalid data', {
        ui.run()
    }

    then 'the user is informed that the save was unsuccessful', {
        io.getPrints().shouldNotHave("Success: Reference added.")
        io.getPrints().shouldHave("Error: one or more paramters were invalid. Reference was not saved.")
        refDB.clearDatabase()
   }
}

scenario "New inproceedings is not saved", {
    given 'command add selected', {
        refDB = new ReferenceDatabase("DATABASE_TEST")
        io = new StubIO("add", "7", "author", "title", "booktitle", "1995", "editor", "23", "22", "series", "pages", "address", "month", "organization", "publisher", "note", "key", "exit")
        ui = new ConsoleUI(io, refDB)
    }

    when 'optional parameters contain invalid data', {
        ui.run()
    }

    then 'the user is informed that the save was unsuccessful', {
        io.getPrints().shouldNotHave("Success: Reference added.")
        io.getPrints().shouldHave("Error: one or more paramters were invalid. Reference was not saved.")
        refDB.clearDatabase()
   }
}