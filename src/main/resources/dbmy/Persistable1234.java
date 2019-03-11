package com.saptris.erp.db;

import java.lang.reflect.Field;

import javax.persistence.Entity;

import org.hibernate.HibernateException;

import com.saptris.erp.annotation.Attribute;

public interface Persistable1234 {
	static Persistable1234 get(/*String entityClass, */String toString)  throws HibernateException{
		String toStringSplit[]= toString.split(" ");
		//TODO packageName
		String packageName= "com.saptris.erp.db.";
		String entityClassName= toStringSplit[0];
		String valuesString[]= new String[toStringSplit.length-1];
		int i=0;
		for(i=0;i<valuesString.length;i++)
			valuesString[i]= toStringSplit[i+1];
		Object valueObject[]= new Object[valuesString.length-1];
		Class<?> entityClass;
		try {
			entityClass= Class.forName(packageName+entityClassName);
			if(entityClass.getAnnotation(Entity.class)==null)
				return null;
		}
		catch(Exception e) {
			throw new HibernateException("Some unexpected error occured while getting Class of "+entityClassName, e);
		}
		
		try{
			//TreeMap<Integer, String> map= new TreeMap<Integer,String>();
			Class<?> parameterTypes[]= new Class<?>[valuesString.length-1];
			i=0;
			Field attributes[]= entityClass.getDeclaredFields();
			for(Field m: attributes) {
				/*JoinColumn jc= m.getAnnotation(JoinColumn.class);				
				Attribute attr= m.getAnnotation(Attribute.class);				
				if(attr!=null) {
					if(jc!=null)
						map.put(attr.index(), "select");
					else
						map.put(attr.index(), "text");
				}*/
				Attribute attr= m.getAnnotation(Attribute.class);				
				if(attr!=null) {//all attribute annotated fields filtered
					parameterTypes[i]= m.getType();//stored class type of field
					Class<?> []tempInterface= parameterTypes[i].getInterfaces();//get any interface implemented by type class
					//type class can be a Persistable class / String / int / double
					if(tempInterface.length==1 && tempInterface[0]==Persistable1234.class)//check if only Persistable was implemented
						valueObject[attr.index()]= get(valuesString[i]);//store value by getting its value form recursive call to get()
					else if(parameterTypes[i]==int.class)//type was int
						valueObject[0]= Integer.parseInt(valuesString[0]);
					else if(parameterTypes[i]==double.class)//type was double
						valueObject[0]= Double.parseDouble(valuesString[0]);
					else if(parameterTypes[i]==String.class)//type was String
						valueObject[0]= valuesString[0];
					else//any other case store Object
						valueObject[0]= (Object) valuesString[0];
				}
				i++;
			}
			
			Persistable1234 object= (Persistable1234) entityClass.getConstructor(parameterTypes).newInstance(valueObject);
			String runningSetIdName= "set" + entityClassName + "_id";
			entityClass.getMethod(runningSetIdName, int.class).invoke(object, Integer.parseInt(valuesString[0]));
			return object;
		}
		catch(Exception e){
			throw new HibernateException("Some unexpected error occured while getting object of Persistable class "+entityClass.getName(), e);
		}
	}
}
