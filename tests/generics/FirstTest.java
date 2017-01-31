package org.davidespadini.mockextractor;

import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class FirstTest {
	
	@Mock
	private Request<byte[]> reqs;


	@Test
	public void f2(){
		Message<?> expected = Mockito.mock(Message.class);
	}

}
