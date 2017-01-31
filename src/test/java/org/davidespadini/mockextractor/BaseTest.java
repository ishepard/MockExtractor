package org.davidespadini.mockextractor;

import java.io.File;
import java.io.IOException;

import org.junit.BeforeClass;

public class BaseTest {

	private static String dir;
	
	@BeforeClass
	public static void findDir() {
		try {
			dir = new File(BaseTest.class.getResource("/").getPath() + "../../tests/").getCanonicalPath();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	protected String dir() {
		return dir;
	}
	
}
