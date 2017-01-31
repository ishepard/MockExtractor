package org.davidespadini.mockextractor;

import org.junit.Test;
import static org.mockito.Mockito.*;
import org.mockito.MockitoAnnotations;

public class OneTest {
    private Request req;

    @Test
    public void t1() {
        req = mock(Request.class);
    }
}
