package org.davidespadini.mockextractor;

import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class OneTest {
    private Request req;

    @Test
    public void t1() {
    	DependencyA a = Mockito.mock(DependencyA.class);
    	DependencyB b = new DependencyB();
    	
        new Request(a, b);
    }
}
