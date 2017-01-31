package org.davidespadini.mockextractor;

import jdk.nashorn.internal.ir.RuntimeNode.Request;

import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class OneTest {
    private Request req;

    @Test
    public void t1() {
        req = Mockito.mock(Request.class);
    }
}
