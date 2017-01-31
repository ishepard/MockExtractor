package org.davidespadini.mockextractor;

public class Request {
	private DependencyA a;
	private DependencyB b;
	
	public Request(DependencyA a, DependencyB b) {
		this.a = a;
		this.b = b;
	}
}
