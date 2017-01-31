package org.davidespadini.mockextractor;

import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class TwoTest {
    private Request req;

    @Test
    public void t1() {
        this.req = Mockito.mock(Request.class);
    }
}
