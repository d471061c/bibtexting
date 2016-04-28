import com.bibtextingcompany.bibtexting.*
import com.bibtextingcompany.domain.Reference

description 'References added by the user are saved in a file'

scenario "New reference is saved in a file", {
    given 'command add selected', {
        refDB = new ReferenceDatabase("DATABASE_TEST")
        io = new StubIO("add", "1", "author", "title123", "journal", "1993", "13", "1", "2-3", "may", "note", "key", "exit")
        ui = new ConsoleUI(io, refDB)
        io = new StubIO("view", "title123", "exit")
        
    }

    when 'it contains proper type of data', {
        ui.run()
        ui = new ConsoleUI(io, refDB)
        ui.run()
    }

    then 'on next startup the reference can be found by view', {
        io.getPrints().shouldHave("title123")
        io.getPrints().shouldNotHave("No references found")
        refDB.clearDatabase()
    }
}