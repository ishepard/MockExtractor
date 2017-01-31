package org.davidespadini.mockextractor;

import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class MockAndNotMockTest {
    private Request req;

    @Test
    public void t1() {
        req = Mockito.mock(Request.class);
    }

    @Test
    public void t2() {
    	req = new Request();
    }

    @Test
    public void t3() {
        req = new Request();
    }
}
