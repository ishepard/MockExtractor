package org.davidespadini.mockextractor;

import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class TwoTest {
    private Request req;

    @Test
    public void t1() {
        req = Mockito.mock(Request.class);
    }
}
