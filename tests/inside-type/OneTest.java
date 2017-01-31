package org.davidespadini.mockextractor;

import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class OneTest {
    private Request req;

    @Test
    public void t1() {
        req.a = new DependencyA();
        req.b = Mockito.mock(DependencyB.class, new Request());
    }
}
