package org.davidespadini.mockextractor.visitors;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.davidespadini.mockextractor.utils.ParserUtil;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.IMethodBinding;
import org.eclipse.jdt.core.dom.ITypeBinding;
import org.eclipse.jdt.core.dom.MethodInvocation;
import org.eclipse.jdt.core.dom.TypeLiteral;

public class VariablesVisitor extends ASTVisitor{
	private Set<String> mocked = new HashSet<String>();
	
	public boolean visit(MethodInvocation node) {
		boolean mocked = false;
		
		IMethodBinding bindingMockito = node.resolveMethodBinding();
		if(bindingMockito!=null) {
			mocked = isFromMockito(node, bindingMockito);
		}
		if(!mocked) return super.visit(node);

		if (!(node.arguments().get(0) instanceof TypeLiteral)) return super.visit(node);

		TypeLiteral mi = (TypeLiteral) node.arguments().get(0);
		String binding = getBinding(mi.getType().resolveBinding());
		this.addNewVariable(binding);
		return super.visit(node);
	}

	private boolean isFromMockito(MethodInvocation node, IMethodBinding bindingMockito) {
		String qualifiedName = bindingMockito.getDeclaringClass().getQualifiedName();
		return (qualifiedName.equals("org.mockito.Mockito") || qualifiedName.equals("org.mockito.BDDMockito"))  &&
				node.getName().toString().equals("mock");
	}
	
	@Override
	public boolean visit(FieldDeclaration node) {
        String binding = getBinding(node.getType().resolveBinding());
        boolean mocked = this.isMocked(node);

    	if (mocked && !binding.isEmpty())
    		this.addNewVariable(binding);
		return super.visit(node);
	}
    
    private String getBinding(ITypeBinding binding) {
		if(binding==null) return "";

		return binding.getQualifiedName();
	}
	
	private boolean isMocked(Object o){
    	if (o.getClass().getSimpleName().equals("FieldDeclaration")){
    		return mockedFieldDeclaration((FieldDeclaration) o);
    	} else if (o.getClass().getSimpleName().equals("MethodInvocation")){
    		return mockedVariableDeclarationStatement(o.toString());
    	}
    	return false;
    }
	
	@SuppressWarnings("unchecked")
	private boolean mockedFieldDeclaration(FieldDeclaration fd){
    	List<Object> mod = fd.modifiers();
		for(Object str: mod) {;
		    if (str.toString().equals("@Mock")){
		    	return true;
		    }
		}
		
		return mockedVariableDeclarationStatement(fd.toString());
    }
    
    private boolean mockedVariableDeclarationStatement(String vds){
    	return vds.contains("mock(");
    }
    
    private void addNewVariable(String type){
    	if (toSkip(type)) return;

    	mocked.add(ParserUtil.cleanGenerics(type));
    }
    
	private boolean toSkip(String type){
    	return type == null || type.startsWith("java.lang") || ParserUtil.nameIsPrimitive(type);
    }

	public Set<String> getMocked() {
		return this.mocked;
	}
	
}
