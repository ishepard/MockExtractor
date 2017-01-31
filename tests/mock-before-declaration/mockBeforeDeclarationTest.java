package org.davidespadini.mockextractor;

import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import java.util.List;

public class MockAndNotMock {

	public void t2() {
        this.req1 = Mockito.mock(Request.class);
    }

    @Test
    public void t1() {
    	for (int i = 0; i < 100; i ++){
    		req1.add(Mockito.mock(Request.class));
    	}
    }

    private Request req;
    private List<Request> req1;
}
