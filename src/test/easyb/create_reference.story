import com.bibtextingcompany.bibtexting.*
import com.bibtextingcompany.domain.Reference

description 'User can add all remaining types of references to the database'

scenario "New booklet is saved", {
    given 'command add selected', {
        refDB = new ReferenceDatabase("DATABASE_TEST")
        io = new StubIO("add", "3", "title", "", "", "", "", "", "exit")
        ui = new ConsoleUI(io, refDB)
    }

    when 'it contains proper type of data', {
        ui.run()
    }

    then 'the user is informed that the save was successful', {
        io.getPrints().shouldHave("Success: Reference added.")
        io.getPrints().shouldNotHave("Error: one or more parameters were invalid. Reference was not saved.")
        refDB.clearDatabase()
    }
}

scenario "New conference is saved", {
    given 'command add selected', {
        refDB = new ReferenceDatabase("DATABASE_TEST")
        io = new StubIO("add", "4", "author", "title", "booktitle", "1997", "", "", "", "", "", "", "", "", "", "", "exit")
        ui = new ConsoleUI(io, refDB)
    }

    when 'it contains proper type of data', {
        ui.run()
    }

    then 'the user is informed that the save was successful', {
        io.getPrints().shouldHave("Success: Reference added.")
        io.getPrints().shouldNotHave("Error: one or more parameters were invalid. Reference was not saved.")
        refDB.clearDatabase()
    }
}

scenario "New inbook is saved", {
    given 'command add selected', {
        refDB = new ReferenceDatabase("DATABASE_TEST")
        io = new StubIO("add", "5", "author", "editor", "title", "chapter", "22", "", "", "", "", "", "", "", "", "exit")
        ui = new ConsoleUI(io, refDB)
    }

    when 'it contains proper type of data', {
        ui.run()
    }

    then 'the user is informed that the save was successful', {
        io.getPrints().shouldHave("Success: Reference added.")
        io.getPrints().shouldNotHave("Error: one or more parameters were invalid. Reference was not saved.")
        refDB.clearDatabase()
    }
}

scenario "New incollection is saved", {
    given 'command add selected', {
        refDB = new ReferenceDatabase("DATABASE_TEST")
        io = new StubIO("add", "6", "author", "title", "booktitle", "publisher", "1922", "", "", "", "", "", "", "", "", "", "", "", "exit")
        ui = new ConsoleUI(io, refDB)
    }

    when 'it contains proper type of data', {
        ui.run()
    }

    then 'the user is informed that the save was successful', {
        io.getPrints().shouldHave("Success: Reference added.")
        io.getPrints().shouldNotHave("Error: one or more parameters were invalid. Reference was not saved.")
        refDB.clearDatabase()
    }
}

scenario "New manual is saved", {
    given 'command add selected', {
        refDB = new ReferenceDatabase("DATABASE_TEST")
        io = new StubIO("add", "8", "title", "", "", "", "", "", "", "", "exit")
        ui = new ConsoleUI(io, refDB)
    }

    when 'it contains proper type of data', {
        ui.run()
    }

    then 'the user is informed that the save was successful', {
        io.getPrints().shouldHave("Success: Reference added.")
        io.getPrints().shouldNotHave("Error: one or more parameters were invalid. Reference was not saved.")
        refDB.clearDatabase()
    }
}

scenario "New masters thesis is saved", {
    given 'command add selected', {
        refDB = new ReferenceDatabase("DATABASE_TEST")
        io = new StubIO("add", "9", "author", "title", "school", "1993", "", "", "", "", "exit")
        ui = new ConsoleUI(io, refDB)
    }

    when 'it contains proper type of data', {
        ui.run()
    }

    then 'the user is informed that the save was successful', {
        io.getPrints().shouldHave("Success: Reference added.")
        io.getPrints().shouldNotHave("Error: one or more parameters were invalid. Reference was not saved.")
        refDB.clearDatabase()
    }
}

scenario "New misc is saved", {
    given 'command add selected', {
        refDB = new ReferenceDatabase("DATABASE_TEST")
        io = new StubIO("add", "10", "", "", "", "", "", "", "exit")
        ui = new ConsoleUI(io, refDB)
    }

    when 'it contains proper type of data', {
        ui.run()
    }

    then 'the user is informed that the save was successful', {
        io.getPrints().shouldHave("Success: Reference added.")
        io.getPrints().shouldNotHave("Error: one or more parameters were invalid. Reference was not saved.")
        refDB.clearDatabase()
    }
}

scenario "New phd thesis is saved", {
    given 'command add selected', {
        refDB = new ReferenceDatabase("DATABASE_TEST")
        io = new StubIO("add", "11", "author", "title", "school", "2001", "", "", "", "", "exit")
        ui = new ConsoleUI(io, refDB)
    }

    when 'it contains proper type of data', {
        ui.run()
    }

    then 'the user is informed that the save was successful', {
        io.getPrints().shouldHave("Success: Reference added.")
        io.getPrints().shouldNotHave("Error: one or more parameters were invalid. Reference was not saved.")
        refDB.clearDatabase()
    }
}

scenario "New proceedings is saved", {
    given 'command add selected', {
        refDB = new ReferenceDatabase("DATABASE_TEST")
        io = new StubIO("add", "12", "title", "2005", "", "", "", "", "", "", "", "", "", "exit")
        ui = new ConsoleUI(io, refDB)
    }

    when 'it contains proper type of data', {
        ui.run()
    }

    then 'the user is informed that the save was successful', {
        io.getPrints().shouldHave("Success: Reference added.")
        io.getPrints().shouldNotHave("Error: one or more parameters were invalid. Reference was not saved.")
        refDB.clearDatabase()
    }
}

scenario "New tech report is saved", {
    given 'command add selected', {
        refDB = new ReferenceDatabase("DATABASE_TEST")
        io = new StubIO("add", "13", "author", "title", "institution", "2009", "", "", "", "", "", "exit")
        ui = new ConsoleUI(io, refDB)
    }

    when 'it contains proper type of data', {
        ui.run()
    }

    then 'the user is informed that the save was successful', {
        io.getPrints().shouldHave("Success: Reference added.")
        io.getPrints().shouldNotHave("Error: one or more parameters were invalid. Reference was not saved.")
        refDB.clearDatabase()
    }
}

scenario "New unpublished is saved", {
    given 'command add selected', {
        refDB = new ReferenceDatabase("DATABASE_TEST")
        io = new StubIO("add", "14", "author", "title", "note", "", "", "exit")
        ui = new ConsoleUI(io, refDB)
    }

    when 'it contains proper type of data', {
        ui.run()
    }

    then 'the user is informed that the save was successful', {
        io.getPrints().shouldHave("Success: Reference added.")
        io.getPrints().shouldNotHave("Error: one or more parameters were invalid. Reference was not saved.")
        refDB.clearDatabase()
    }
}