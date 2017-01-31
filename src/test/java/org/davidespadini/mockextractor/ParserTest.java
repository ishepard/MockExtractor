package org.davidespadini.mockextractor;

import java.io.IOException;
import java.util.List;

import org.davidespadini.mockextractor.Parser;
import org.davidespadini.mockextractor.Variable;
import org.junit.Assert;
import org.junit.Test;

public class ParserTest extends BaseTest {
	
	private String[] classpath = new String[]{
		dir() + "/deps/junit-4.12.jar",
		dir() + "/deps/mockito-core-1.10.19.jar"
	};
	private String output = System.getProperty("java.io.tmpdir") + "mockusages.csv";

	@Test
	public void genericType() throws IOException{
		String dir = dir() + "/generics/";
		Parser parser = new Parser(dir, this.output, this.classpath);
		
		List<Variable> vars = parser.parse();

		Assert.assertEquals(2, vars.size());

		Variable v1 = new Variable("org.davidespadini.mockextractor.Request", dir + "FirstTest.java", true);
		Assert.assertTrue(vars.contains(v1));

		Variable v2 = new Variable("org.davidespadini.mockextractor.Message", dir + "FirstTest.java", true);
		Assert.assertTrue(vars.contains(v2));
	}
	
	@Test
	public void simpleTest() throws IOException{
		String dir = dir() + "/simpleTest/";
		Parser parser = new Parser(dir, this.output, this.classpath);
		
		List<Variable> vars = parser.parse();

		Variable v1 = new Variable("org.davidespadini.mockextractor.Request", dir + "SimpleTest.java", true);
		Assert.assertTrue(vars.contains(v1));
		Assert.assertEquals(1, vars.size());
	}

	@Test
	public void ignore_non_tests() throws IOException{
		String dir = dir() + "/non-tests";
		Parser parser = new Parser(dir, this.output, this.classpath);
		
		List<Variable> vars = parser.parse();

		Variable v1 = new Variable("org.davidespadini.mockextractor.Request", dir + "/OneTest.java", true);
		
		Assert.assertEquals(1, vars.size());
		Assert.assertTrue(vars.contains(v1));
	}

	@Test
	public void two_tests() throws IOException{
		String dir = dir() + "/two-tests";
		Parser parser = new Parser(dir, this.output, this.classpath);
		
		List<Variable> vars = parser.parse();

		Variable v1 = new Variable("org.davidespadini.mockextractor.Request", dir() + "/two-tests/OneTest.java", true);
		Variable v2 = new Variable("org.davidespadini.mockextractor.Request", dir() + "/two-tests/TwoTest.java", true);
		
		Assert.assertEquals(2, vars.size());
		Assert.assertTrue(vars.contains(v1));
		Assert.assertTrue(vars.contains(v2));
	}
	
	@Test
	public void three_tests() throws IOException{
		String dir = dir() + "/three-tests";
		Parser parser = new Parser(dir, this.output, this.classpath);
		
		List<Variable> vars = parser.parse();
		
		Variable v1 = new Variable("org.davidespadini.mockextractor.Request", dir() + "/three-tests/OneTest.java", true);		
		Variable v2 = new Variable("org.davidespadini.mockextractor.Request", dir() + "/three-tests/TwoTest.java", true);
		Variable v3 = new Variable("org.davidespadini.mockextractor.Request", dir() + "/three-tests/ThreeTest.java", false);
		
		Assert.assertEquals(3, vars.size());
		Assert.assertTrue(vars.contains(v1));
		Assert.assertTrue(vars.contains(v2));
		Assert.assertTrue(vars.contains(v3));
	}
	
	@Test
	public void mock_and_not_mock() throws IOException{
		String dir = dir() + "/mock-and-not-mock";
		Parser parser = new Parser(dir, this.output, this.classpath);
		
		List<Variable> vars = parser.parse();
		
		Variable v1 = new Variable("org.davidespadini.mockextractor.Request", dir() + "/mock-and-not-mock/MockAndNotMockTest.java", true);
		
		Assert.assertEquals(1, vars.size());
		Assert.assertTrue(vars.contains(v1));
	}

	@Test
	public void static_use_mockito() throws IOException{
		String dir = dir() + "/static-invocation";
		Parser parser = new Parser(dir, this.output, this.classpath);
		
		List<Variable> vars = parser.parse();

		Variable v1 = new Variable("org.davidespadini.mockextractor.Request", dir() + "/static-invocation/OneTest.java", true);
		
		Assert.assertEquals(1, vars.size());
		Assert.assertTrue(vars.contains(v1));
	}
	
	@Test
	public void mock_before_declaration() throws IOException{
		String dir = dir() + "/mock-before-declaration";
		Parser parser = new Parser(dir, this.output, this.classpath);
		
		List<Variable> vars = parser.parse();

		Variable v1 = new Variable("org.davidespadini.mockextractor.Request", dir() + "/mock-before-declaration/mockBeforeDeclarationTest.java", true);
		Variable v2 = new Variable("java.util.List", dir() + "/mock-before-declaration/mockBeforeDeclarationTest.java", false);
		
		Assert.assertEquals(2, vars.size());
		Assert.assertTrue(vars.contains(v1));
		Assert.assertTrue(vars.contains(v2));
	}
	
	@Test
	public void innerClass() throws IOException{
		String dir = dir() + "/inner-class";
		Parser parser = new Parser(dir, this.output, this.classpath);
		
		List<Variable> vars = parser.parse();
		
		Variable v1 = new Variable("org.davidespadini.mockextractor.Request", dir() + "/inner-class/FirstTest.java", true);
		Variable v2 = new Variable("org.davidespadini.mockextractor.List", dir() + "/inner-class/FirstTest.java", false);
		Variable v3 = new Variable("org.davidespadini.mockextractor.Leg", dir() + "/inner-class/FirstTest.java", false);
		Variable v4 = new Variable("org.davidespadini.mockextractor.FirstTest.InnerClass", dir() + "/inner-class/FirstTest.java", false);
		
		Assert.assertEquals(4, vars.size());
		Assert.assertTrue(vars.contains(v1));
		Assert.assertTrue(vars.contains(v2));
		Assert.assertTrue(vars.contains(v3));
		Assert.assertTrue(vars.contains(v4));
	}

	@Test
	public void mockDependencies() throws IOException{
		String dir = dir() + "/mock-dependencies";
		Parser parser = new Parser(dir, this.output, this.classpath);
		
		List<Variable> vars = parser.parse();

		Variable v1 = new Variable("org.davidespadini.mockextractor.Request", dir() + "/mock-dependencies/OneTest.java", false);
		Variable v2 = new Variable("org.davidespadini.mockextractor.DependencyA", dir() + "/mock-dependencies/OneTest.java", true);
		Variable v3 = new Variable("org.davidespadini.mockextractor.DependencyB", dir() + "/mock-dependencies/OneTest.java", false);

		Variable v21 = new Variable("org.davidespadini.mockextractor.Request", dir() + "/mock-dependencies/TwoTest.java", true);
		Variable v22 = new Variable("org.davidespadini.mockextractor.DependencyA", dir() + "/mock-dependencies/TwoTest.java", false);
		Variable v23 = new Variable("org.davidespadini.mockextractor.DependencyB", dir() + "/mock-dependencies/TwoTest.java", false);
		
		Assert.assertEquals(6, vars.size());
		Assert.assertTrue(vars.contains(v1));
		Assert.assertTrue(vars.contains(v2));
		Assert.assertTrue(vars.contains(v3));
		
		Assert.assertTrue(vars.contains(v21));
		Assert.assertTrue(vars.contains(v22));
		Assert.assertTrue(vars.contains(v23));
	}
	
	@Test
	public void inside_type() throws IOException {
		String dir = dir() + "/inside-type";
		Parser parser = new Parser(dir, this.output, this.classpath);
		
		List<Variable> vars = parser.parse();

		Variable v1 = new Variable("org.davidespadini.mockextractor.Request",dir() + "/inside-type/OneTest.java", false);
		Variable v2 = new Variable("org.davidespadini.mockextractor.DependencyA",dir() + "/inside-type/OneTest.java", false);
		Variable v3 = new Variable("org.davidespadini.mockextractor.DependencyB",dir() + "/inside-type/OneTest.java", true);
		
		Assert.assertEquals(3, vars.size());
		Assert.assertTrue(vars.contains(v1));
		Assert.assertTrue(vars.contains(v2));
		Assert.assertTrue(vars.contains(v3));
	}
}
