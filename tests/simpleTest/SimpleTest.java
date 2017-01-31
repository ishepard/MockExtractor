package org.davidespadini.mockextractor;

import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class SimpleTest{
    private Request session;
    private Request sessionMocked = mock(Request.class);

    @Mock
    private Request something;

    @Test
    public void initialize() {
        this.session = new Request();
    }
}
