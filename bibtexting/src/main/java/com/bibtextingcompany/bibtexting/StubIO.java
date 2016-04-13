package com.bibtextingcompany.bibtexting;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class StubIO implements IO {

    private final String[] inputs;
    private int i;
    private final List<String> outputs;

    public StubIO(String... inputs) {
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
    
    
    public List<String> getPrints() {
        return outputs;
    }
}
