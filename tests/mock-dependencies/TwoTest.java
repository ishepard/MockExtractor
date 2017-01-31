package org.davidespadini.mockextractor;

import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class TwoTest {
    private Request req;

    @Mock
    private Request mockedRequest;

    @Test
    public void t1() {
        req = new Request(new DependencyA(), new DependencyB());
    }
}
