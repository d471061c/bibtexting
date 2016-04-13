package com.bibtextingcompany.bibtexting;

import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/*
 * SOON TO BE REMOVED
 */
public class ConsoleUITest {

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
