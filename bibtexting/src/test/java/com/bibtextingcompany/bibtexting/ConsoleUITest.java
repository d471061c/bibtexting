package com.bibtextingcompany.bibtexting;

import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author Eero
 */
public class ConsoleUITest {

    @Test
    public void testExit() {
        IOStub io = new IOStub("exit");
        new ConsoleUI(io).run();
        assertEquals("Goodbye!\n", io.outputs.get(6));
    }
    
    @Test
    public void testHelp() {
        IOStub io = new IOStub("help", "exit");
        new ConsoleUI(io).run();
        assertEquals("Available commands:\n", io.outputs.get(6));
    }
    
    @Test
    public void testInvalidCommand() {
        IOStub io = new IOStub("asdf", "exit");
        new ConsoleUI(io).run();
        assertEquals("Invalid command; type help for a list of commands\n", io.outputs.get(6));
    }
}

class IOStub implements IO {

    final String[] inputs;
    private int i;
    List<String> outputs;

    public IOStub(String... inputs) {
        this.inputs = inputs;
        outputs = new ArrayList();
    }

    @Override
    public void print(String msg) {
        outputs.add(msg);
    }

    @Override
    public String readLine() {
        return inputs[i++];
    }
}
