package org.davidespadini.mockextractor;

import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class FirstTest {
	Request ciao;

	public static class InnerClass {
		public void f() {
			this.legs = 4;
		}

		private List<Leg> legs;
	}

	public void f2(){
		this.ciao = "Ciaoooooo";
		ciao = Mockito.mock(Request.class);
	}

}
