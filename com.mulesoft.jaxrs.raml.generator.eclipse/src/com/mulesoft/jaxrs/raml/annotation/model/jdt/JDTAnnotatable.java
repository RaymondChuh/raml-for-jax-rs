package com.mulesoft.jaxrs.raml.annotation.model.jdt;

import org.eclipse.jdt.core.IAnnotatable;
import org.eclipse.jdt.core.IAnnotation;
import org.eclipse.jdt.core.JavaModelException;

import com.mulesoft.jaxrs.raml.annotation.model.IAnnotationModel;
import com.mulesoft.jaxrs.raml.annotation.model.IBasicModel;

public abstract class JDTAnnotatable implements IBasicModel{

	private static final String VALUE = "value";
	protected org.eclipse.jdt.core.IAnnotatable tm;
	private IAnnotationModel[] mms;


	public JDTAnnotatable(IAnnotatable tm) {
		super();
		this.tm = tm;
	}

	@Override
	public boolean hasAnnotation(String name) {
		IAnnotationModel[] annotations = getAnnotations();
		for (IAnnotationModel m:annotations){
			if (m.getName().equals(name)){
				return true;
			}
		}
		return false;
	}

	@Override
	public String getAnnotationValue(String annotation) {
		IAnnotationModel[] annotations = getAnnotations();
		for (IAnnotationModel m:annotations){
			if (m.getName().equals(annotation)){
				return m.getValue(VALUE);
			}
		}
		return null;
	}
	
	@Override
	public String[] getAnnotationValues(String annotation) {
		IAnnotationModel[] annotations = getAnnotations();
		for (IAnnotationModel m:annotations){
			if (m.getName().equals(annotation)){
				return m.getValues(VALUE);
			}
		}
		return null;
	}
	
	@Override
	public IAnnotationModel[] getAnnotations() {
		try{
		if (mms!=null){
			return mms;
		}
		IAnnotation[] annotations = tm.getAnnotations();
		mms = new IAnnotationModel[annotations.length];
		int a=0;
		for (IAnnotation q:annotations){
			mms[a++]=new JDTAnnotation(q);
		}
		return mms;
		}catch (JavaModelException e) {
			throw new IllegalStateException();
		}
	}
	
}