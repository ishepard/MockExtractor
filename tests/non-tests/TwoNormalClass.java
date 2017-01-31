package org.davidespadini.mockextractor;

import org.mockito.Mockito;
import org.junit.Test;

public class TwoNormalClass {
    private Request req;

    @Test
    public void t1() {
        req = Mockito.mock(Request.class);
    }
}
