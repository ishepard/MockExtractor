package org.davidespadini.mockextractor.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.davidespadini.mockextractor.dto.Variable;
import org.davidespadini.mockextractor.visitors.DependenciesVisitor;
import org.davidespadini.mockextractor.visitors.VariablesVisitor;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.FileASTRequestor;

public class Storage extends FileASTRequestor{
	private List<Variable> varsMocked = new ArrayList<Variable>();
	private List<Variable> varsNotMocked = new ArrayList<Variable>();
	
	@Override
    public void acceptAST(String sourceFilePath, CompilationUnit cu) {
		System.out.println("Reading " + sourceFilePath);
		if(!isTest(sourceFilePath)) return;

		DependenciesVisitor depVisitor = new DependenciesVisitor();
		cu.accept(depVisitor);
		Set<String> coupling = depVisitor.getCoupling();
//		System.out.println(coupling);

		VariablesVisitor visitor = new VariablesVisitor();
		cu.accept(visitor);
		Set<String> mocked = visitor.getMocked();
//		System.out.println(mocked);
		
		if (mocked.size() > coupling.size()){
			System.out.println("This should never happen...");
			System.exit(-1);
		}

		for(String clazz : coupling) {
			if(mocked.contains(clazz)) {
				Variable v = new Variable(clazz, sourceFilePath, true);
				this.varsMocked.add(v);
			} else {
				Variable v = new Variable(clazz, sourceFilePath, false);
				this.varsMocked.add(v);
			}
		}

	}
	
	private boolean isTest(String sourceFilePath) {
		return sourceFilePath.toLowerCase().endsWith("test.java") || sourceFilePath.toLowerCase().endsWith("tests.java");
	}

	public List<Variable> getVarsNotMocked(){
		return this.varsNotMocked;
	}

	public List<Variable> getVarsMocked() {
		return varsMocked;
	}
	
}
