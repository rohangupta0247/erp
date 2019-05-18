package com.saptris.erp;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import com.saptris.erp.annotation.Attribute;
import com.saptris.erp.annotation.Month;
import com.saptris.erp.annotation.NotNull;
import com.saptris.erp.pmm.ReminderScheduler;

public class EntityManager/*<E>*/ {
	//package name
	//private final String packageName="com.saptris.erp.db.";
	
	public static final String separator="><.><";
	public static final String idSeparator="}{.}{";
	
	public static final int ADD_RECORD=1;
	public static final int UPDATE_RECORD=2;
	
	private String packageName;
	private Class<?> entityClass;
	
	public EntityManager(String entityClassName){
		String classString="<empty class string>";
		try {
			packageName= SessionFactoryBuilder.classesMapping.get(entityClassName);
			
			String temp=packageName;
			if(entityClassName.equals("MaintenanceAllUsers"))
				temp= "com.saptris.erp.";
			
			classString= temp+entityClassName;
			
			Class<?> classCheck= Class.forName(classString);
			//Class<?> classCheck= entity.getClass();
			
			if(classCheck.getAnnotation(Entity.class)==null)
				throw new ClassCastException("Not an Entity class");
			else
				entityClass= classCheck;
		}
		catch(Exception e) {
			throw new HibernateException("Some unexpected error occured while creating EntityManager for "+classString, e);
		}
	}
	
	
	public Class<?> getClassType(){
		return entityClass;
	}
	
	//public /*static*/ User getUser(String username) {
	//@SuppressWarnings("unchecked")
	//TODO long id
	public /*static*/ Object/*E*/ getEntity(/*String entityClassName, */int id) {
		Session hbnSession = null;
		try{
			hbnSession = SessionFactoryBuilder.getUserSessionFactory().openSession();
			/*
			String idAttribute="";
			Field attributes[]= entityClass.getDeclaredFields();
			for(Field m: attributes) {
				Id attr= m.getAnnotation(Id.class);
				if(attr!=null) {
					idAttribute= m.getName();
				}
			}

			String jpql = "select e from "+entityClass.getName()+" e where e."+idAttribute+" = "+id;
			//Type safety: Unchecked cast from capture#8-of ? to E
			entity= (E) hbnSession.createQuery(jpql, entityClass).getSingleResult();*/
			Object entity= /*(E) */hbnSession.get(entityClass, id);
			return entity;
		}
		catch(Exception e){
			throw new HibernateException("Some unexpected error occured while getting attributes of "+entityClass.getName(), e);
		}
		finally{
			if(hbnSession!=null)
				hbnSession.close();
		}
	}
	
	/**
	 * 
	 * @param maxResult is size of maximum results to fetch
	 * @param startPosition is starting index of fetching query(index starts at 1)
	 *
	 * @return List<?> of all records
	 */
	//TODO long startPosition
	public /*static*/ String[][] getAllEntityString(/*String entityClassName*/int maxResult, int startPosition) {
		List<?> list= getAllEntity(maxResult, startPosition);
		//Iterator<?> itr= list.iterator();
		int i=0,length= getAttributesName().length;
		String res[][]= new String[maxResult][length];
		for(Object o: list) {
				res[i++]= mapObject(o);
		}
		/*for(String[] ss: res) {
			for(String sss: ss) {
				System.out.print(sss+" ");
			}
			System.out.println();
		}*/
		return res;
	}
	
	/*private */static String[] mapObject(Object object) {
		String str[]= object.toString().split(separator);
		ArrayList<String> list= new ArrayList<>();
		//		Item{ id name Warehouse{ id name Address{ id area state } } cost }
		//		id name }{.}{#Warehouse#id cost
		int i;
		int inside=0;
		for(i=1; i<str.length-1; i++) {
			if(str[i].equals("null"))
				str[i]= "";
			if(inside==0 && !str[i].endsWith("{"))
				list.add(str[i]);
			if(str[i].endsWith("{")) {
				inside++;
				if(inside==1)
					list.add(idSeparator+"#"+str[i].substring(0, str[i].length()-1)+"#"+str[i+1]);
			}
			
			if(inside!=0 && str[i].endsWith("}"))
				inside--;
		}
		return list.toArray(new String[] {});
	}

	/**
	 * 
	 * @param maxResult is size of maximum results to fetch
	 * @param startPosition is starting index of fetching query(index starts at 1)
	 *
	 * @return List<?> of all records
	 */
	//TODO long startPosition
	public /*static*/ List<?> getAllEntity(/*String entityClassName*/int maxResult, int startPosition) {
		Session hbnSession = null;
		try{
			hbnSession = SessionFactoryBuilder.getUserSessionFactory().openSession();
			
			//String jpql = "select e from "+entityClass.getName()+" e";
			//@SuppressWarnings("unchecked")
			//Query<E> query= (Query<E>) hbnSession.createQuery(jpql, entityClass);
			Query<?> query= (Query<?>) hbnSession.createQuery("from "+entityClass.getName());
			//index of parameter starts from 1
			query.setFirstResult(startPosition-1);
			query.setMaxResults(maxResult);
			return query.list();
		}
		catch(Exception e){
			throw new HibernateException("Some unexpected error occured while getting all entities of "+entityClass.getName(), e);
		}
		finally{
			if(hbnSession!=null)
				hbnSession.close();
		}
	}
	public /*static*/ List<?> getAllEntity(/*String entityClassName*/) {
		Session hbnSession = null;
		try{
			if(entityClass==MaintenanceAllUsers.class)
				hbnSession = SessionFactoryBuilder.getDefaultSessionFactory().openSession();
			else
				hbnSession = SessionFactoryBuilder.getUserSessionFactory().openSession();
			
			
			//String jpql = "select e from "+entityClass.getName()+" e";
			//@SuppressWarnings("unchecked")
			//Query<E> query= (Query<E>) hbnSession.createQuery(jpql, entityClass);
			return ((Query<?>) hbnSession.createQuery("from "+entityClass.getName())).list();
		}
		catch(Exception e){
			throw new HibernateException("Some unexpected error occured while getting all entities of "+entityClass.getName(), e);
		}
		finally{
			if(hbnSession!=null)
				hbnSession.close();
		}
	}

	/**
	 * Requires columnName string of form "first_name"
	 * 
	 * @param columnName in which searching is to be done
	 * @param searchItem that is to be searched
	 *
	 * @return String[][]
	 */
	public String[][] search(String columnName, String searchItem /*, int maxResult, int startPosition*/) {
		/*Query<?> query= (Query<?>) hbnSession.createQuery("from "+entityClass.getName());
		//index of parameter starts from 1
		query.setFirstResult(startPosition-1);
		query.setMaxResults(maxResult);
		return query.list();
			List<?> list= getAllEntity(maxResult, startPosition);
			//Iterator<?> itr= list.iterator();
			int i=0,length= getAttributesName().length;
			String res[][]= new String[maxResult][length];
			for(Object o: list) {
					res[i++]= mapObject(o);
			}
			return res;
		 */
		
		if(columnName==null || columnName.equals(""))
			throw new IllegalArgumentException("Empty columnName");
		if(searchItem==null || searchItem.equals(""))
			return new String[0][];
		
		//columnName= toUnderscoreCase(columnName);
		
		/*select * from table where columnName like '%searchItem%'*/
		Session hbnSession = null;
		try{
			hbnSession = SessionFactoryBuilder.getUserSessionFactory().openSession();
			
			/*
			CriteriaBuilder cb= hbnSession.getCriteriaBuilder();
			@SuppressWarnings("unchecked")
			CriteriaQuery<Object> cq = (CriteriaQuery<Object>) cb.createQuery(entityClass);			
			Root<?> root= cq.from(entityClass);
			
			String likeString= searchItem.toLowerCase();
			if(entityClass.getDeclaredField(columnName).getType()==String.class) {
				likeString= "%"+searchItem.toLowerCase()+"%";
			}
			
			cq.select(root).where(cb.like(cb.lower(root.get(columnName)), likeString));
			
			List<?> list= hbnSession.createQuery(cq).list();
			*/
			
			List<?> list= ((Query<?>) hbnSession.createQuery("from "+entityClass.getName()+" e where str(e."+columnName+") like '%"+searchItem+"%'")).list();

			int i=0,length= getAttributesName().length;
			String res[][]= new String[list.size()][length];
			for(Object o: list) {
					res[i++]= mapObject(o);
			}
			
			return res;
		}
		//for searching int with String query
		catch(ClassCastException e){
			return new String[0][];
		}
		catch(Exception e){
			throw new HibernateException("Some unexpected error occured while searching", e);
		}
	    finally{
	    	if(hbnSession!=null)
		    	hbnSession.close();
	    }
	}

	public List<?> getEntityFor(Map<String, Object> attributesMap) {
		Set<String> attributeSet= attributesMap.keySet();
		
		//columnName= toUnderscoreCase(columnName);
		
		/*select * from table where table.columnName=value*/
		/*select * from table where table.columnName.id=id*/
		Session hbnSession = null;
		try{
			hbnSession = SessionFactoryBuilder.getUserSessionFactory().openSession();
			String query= "from "+entityClass.getName()+" e where ";
			int i=0;
			for(String columnName: attributeSet) {
				if(i++!=0)
					query+=" and ";
			//Object idEntity= attributesMap.get(columnName);
			query+="e."+columnName+"= :"+columnName;
			/*Class<?> idEntityClass=idEntity.getClass(); 
			if(idEntityClass.getAnnotation(Entity.class)==null) {
				String idtostring= idEntity.toString();
				//if(idEntityClass!=int.class && idEntityClass!=BigDecimal.class)
					//idtostring= "\""+idtostring+"\"";
				query+="="+":"+columnName;//+idtostring;
			}
			else {
				String idName=null;
				for(Field f:idEntityClass.getDeclaredFields()) {
					if(f.getAnnotation(Id.class)!=null) {
						idName=f.getName();
						break;
					}
				}
				query+="."+idName+"="+":"+columnName;//+idEntityClass.getDeclaredMethod("get"+idName.substring(0, 1).toUpperCase()+idName.substring(1)).invoke(idEntity);
			}*/
			}
			
			Query<?> que= hbnSession.createQuery(query);
			for(String columnName: attributeSet) {
				Object idEntity= attributesMap.get(columnName);
				que.setParameter(columnName, idEntity);
			}
			//System.out.println(query);
			List<?> list= que.list();
			
			i=0;
			int length= getAttributesName().length;
			String res[][]= new String[list.size()][length];
			for(Object o: list) {
				res[i++]= mapObject(o);
			}
			
			return list;
		}/*
		//for searching int with String query
		catch(ClassCastException e){
			return new String[0][];
		}*/
		catch(Exception e){
			throw new HibernateException("Some unexpected error occured while getting entity for id", e);
		}
		finally{
			if(hbnSession!=null)
				hbnSession.close();
		}
	}
	
	/**
	 * Requires columnName string of form "first_name"
	 * 
	 * @param columnName in which searching is to be done
	 * @param idEntity that persistanat state of condtion
	 *
	 * @return Object
	 */
	public List<?> getEntityFor(String columnName, Object idEntity) {
		if(columnName==null || columnName.equals(""))
			throw new IllegalArgumentException("Empty columnName");
		if(idEntity==null)
			return null;;
		
		//columnName= toUnderscoreCase(columnName);
		
		/*select * from table where table.columnName=value*/
		/*select * from table where table.columnName.id=id*/
		Session hbnSession = null;
		try{
			hbnSession = SessionFactoryBuilder.getUserSessionFactory().openSession();
			
			String query= "from "+entityClass.getName()+" e where e."+columnName+"= :"+columnName;
			/*Class<?> idEntityClass=idEntity.getClass(); 
			if(idEntityClass.getAnnotation(Entity.class)==null) {
				query+="="+idEntity;
			}
			else {
				String idName=null;
				for(Field f:idEntityClass.getDeclaredFields()) {
					if(f.getAnnotation(Id.class)!=null) {
						idName=f.getName();
						break;
					}
				}
				query+="."+idName+"="+idEntityClass.getDeclaredMethod("get"+idName.substring(0, 1).toUpperCase()+idName.substring(1)).invoke(idEntity);
			}*/
			//System.out.println(query);
			Query<?> que= hbnSession.createQuery(query);
			que.setParameter(columnName, idEntity);
			List<?> list= que.list();
			
			int i=0,length= getAttributesName().length;
			String res[][]= new String[list.size()][length];
			for(Object o: list) {
				res[i++]= mapObject(o);
			}
			
			return list;
		}/*
		//for searching int with String query
		catch(ClassCastException e){
			return new String[0][];
		}*/
		catch(Exception e){
			throw new HibernateException("Some unexpected error occured while getting entity for id", e);
		}
		finally{
			if(hbnSession!=null)
				hbnSession.close();
		}
	}
	
	public /*static*/ int getCount(/*String entityClassName*/) {
		Session hbnSession = null;
		try{
			hbnSession = SessionFactoryBuilder.getUserSessionFactory().openSession();
			
			String idAttribute="";
			Field attributes[]= entityClass.getDeclaredFields();
			for(Field m: attributes) {
				Id attr= m.getAnnotation(Id.class);
				if(attr!=null) {
					idAttribute= m.getName();
				}
			}

			String jpql = "select count(e."+idAttribute+") from "+entityClass.getName()+" e";
			//TODO (Long)
			return (int) (long) hbnSession.createQuery(jpql).getSingleResult();
		}
		catch(Exception e) {
			throw new HibernateException("Some unexpected error occured while getting count of "+entityClass.getName(), e);
		}
		finally{
			if(hbnSession!=null)
				hbnSession.close();
		}
	}

	/**
	 * Requires the entity class to have id name (if any) end with "_id"
	 * 
	 * @param entity class of entity
	 *
	 * @return String[] of all column names in table
	 */
	public /*static*/ String[] getAttributesName(/*String entityClassName*/) {
		try{
			/*Class<?> entityClass= Class.forName(packageName+entityClassName);

			if(entityClass.getAnnotation(javax.persistence.Entity.class)==null)
				return null;
*/
			TreeMap<Integer, String> map= new TreeMap<Integer,String>();

			Field attributes[]= entityClass.getDeclaredFields();
			for(Field m: attributes) {
				Attribute attr= m.getAnnotation(Attribute.class);
				if(attr!=null) {
					map.put(attr.index(), m.getName());
				}
			}
			return map.values().toArray(new String[0]);
		}
		catch(Exception e){
			throw new HibernateException("Some unexpected error occured while getting attribute names of "+entityClass.getName(), e);
		}
	}
	/*static*//* public String[] getAttributesName(String entityClassName) {
	try{
		Class<?> entityClass= Class.forName(packageName+entityClassName);

		if(entityClass.getAnnotation(javax.persistence.Entity.class)==null)
			return null;

		ArrayList<String> list=new ArrayList<String>();

		Method methods[]= entityClass.getMethods();
		String temp;
		for(Method m: methods) {
			temp= m.getName();
			if(temp.startsWith("get") && !temp.endsWith("_id")) {
				list.add((char)(temp.charAt(3)+32) + temp.substring(4));
			}
		}
		list.remove("class");

		return list.toArray(new String[0]);
	}
	catch(Exception e){
		throw new HibernateException("Some unexpected error occured while getting attribute names of company", e);
	}
}*/

	public /*static*/ String[] getAttributesType(/*String entityClassName*/) {
		try{
			/*Class<?> entityClass= Class.forName(packageName+entityClassName);

			if(entityClass.getAnnotation(Entity.class)==null)
				return null;
*/
			TreeMap<Integer, String> map= new TreeMap<Integer,String>();

			Field attributes[]= entityClass.getDeclaredFields();
			for(Field m: attributes) {
				Attribute attr= m.getAnnotation(Attribute.class);				
				//JoinColumn jc= m.getAnnotation(JoinColumn.class);				
				OneToOne oto= m.getAnnotation(OneToOne.class);				
				OneToMany otm= m.getAnnotation(OneToMany.class);				
				ManyToMany mtm= m.getAnnotation(ManyToMany.class);				
				Month mon= m.getAnnotation(Month.class);				
				if(attr!=null) {
					//XXX input types in this method, save/index.jsp and saveRecord method
					//if(jc!=null) {
					if(m.getType()==User.class)
						map.put(attr.index(), "user");
					else if(oto!=null) {
						map.put(attr.index(), "select");
					}
					//}
					else if(otm!=null || mtm!=null) {
						map.put(attr.index(), "add-more");
					}
					else {
						if(m.getType()==String.class) {
							if(attr.values().length==0)
								map.put(attr.index(), "text");
							else
								map.put(attr.index(), "options");
						}
						else if(m.getType()==int.class)
							map.put(attr.index(), "number");
						else if(m.getType()==BigDecimal.class)
							map.put(attr.index(), "numberAnyStep");
						else if(m.getType()==java.sql.Date.class) {
							if(mon==null)
								map.put(attr.index(), "date");
							else
								map.put(attr.index(), "month");
						}
						else if(m.getType()==java.util.Date.class)
							map.put(attr.index(), "datetime");
					}
				}
			}
			return map.values().toArray(new String[0]);
		}
		catch(Exception e){
			throw new HibernateException("Some unexpected error occured while getting attribute types of "+entityClass.getName(), e);
		}
	}
	
	public Boolean[] getAttributesNullability() {
		try{
			TreeMap<Integer, Boolean> map= new TreeMap<Integer, Boolean>();
			
			Field attributes[]= entityClass.getDeclaredFields();
			for(Field m: attributes) {
				Attribute attr= m.getAnnotation(Attribute.class);				
				NotNull notNull= m.getAnnotation(NotNull.class);				
				if(attr!=null) {
					if(notNull!=null)
						map.put(attr.index(), false);
					else {
						map.put(attr.index(), true);
					}
				}
			}
			return map.values().toArray(new Boolean[0]);
		}
		catch(Exception e){
			throw new HibernateException("Some unexpected error occured while getting attribute nullability of "+entityClass.getName(), e);
		}
	}

	/**
	 * Converts string of form "under_score" to form "under-score"
	 * 
	 * @param string that is to be converted
	 *
	 * @return String
	 */
	public static String toRunningCase(String string) {
		string= string.toLowerCase();
		String temp="";
		for(char c: string.toCharArray()) {
			if(c!='_')
				temp+=c;
			else
				temp+='-';
		}
		return temp;
	}
	
	/**
	 * Converts string of form "under_score" to form "under score"
	 * 
	 * @param string that is to be converted
	 *
	 * @return String
	 */
	public static String toRunningSpaceCase(String string) {
		string= string.toLowerCase();
		String temp="";
		for(char c: string.toCharArray()) {
			if(c=='_') {
				c=' ';
			}
				temp+=c;
		}
		return temp;
	}

	/**
	 * Converts string of form "under_score" to form "Under Score"
	 * 
	 * @param string that is to be converted
	 *
	 * @return String
	 */
	public static String toNamingCase(String string) {
		string= string.toLowerCase();
		String temp="";
		int i=0;
		for(char c: string.toCharArray()) {
			if(i++==0)
				c=(char)(c-32);
			if(c=='_') {
				i=0;
				c=' ';
			}
			temp+=c;
		}
		return temp;
	}
	
	/**
	 * Converts string of form "under_score" to form "UnderScore"
	 * 
	 * @param string that is to be converted
	 *
	 * @return String
	 */
	public static String toCamelCase(String string) {
		string= string.toLowerCase();
		String temp="";
		int i=0;
		for(char c: string.toCharArray()) {
			if(i++==0)
				c=(char)(c-32);
			if(c=='_') {
				i=0;
				//c=' ';
				continue;
			}
			temp+=c;
		}
		return temp;
	}
	
	/**
	 * Converts string of form "under-score" to form "UnderScore"
	 * 
	 * @param string that is to be converted
	 *
	 * @return String
	 */
	public static String toCamelCaseFromRunningCase(String string) {
		string= string.toLowerCase();
		String temp="";
		int i=0;
		for(char c: string.toCharArray()) {
			if(i++==0)
				c=(char)(c-32);
			if(c=='-') {
				i=0;
				//c=' ';
				continue;
			}
			temp+=c;
		}
		return temp;
	}
	
	/**
	 * Converts string of form "UnderScore" to form "Under Score"
	 * 
	 * @param string that is to be converted
	 *
	 * @return String
	 */
	public static String toNamingCaseFromCamelCase(String string) {
		int i;
		char c[]= string.toCharArray();
		String temp=""+c[0];
		for(i=1; i<c.length; i++) {
			if(c[i]>=65 && c[i]<=90)
				temp+=" ";
			temp+=c[i];
		}
		return temp;
	}
	
	/**
	 * Converts string of form "UnderScore" to form "under_score"
	 * 
	 * @param string that is to be converted
	 *
	 * @return String
	 */
	public static String toUnderscoreCaseFromCamelCase(String string) {
		int i;
		char c[]= string.toCharArray();
		String temp=""+ (char)(c[0]+32);
		for(i=1; i<c.length; i++) {
			if(c[i]>=65 && c[i]<=90) {
				temp+="_";
				c[i]= (char)(c[i]+32);
			}
			temp+=c[i];
		}
		return temp;
	}
	
	/**
	 * Converts string of form "Under Score" to form "under_score"
	 * 
	 * @param string that is to be converted
	 *
	 * @return String
	 */
	public static String toUnderscoreCase(String string) {
		string= string.toLowerCase();
		String temp="";
		for(char c: string.toCharArray()) {
			if(c==' ') {
				c='_';
			}
			temp+=c;
		}
		return temp;
	}

	/**
	 * Set request parameters in this same order otherwise wrong data will be stored
	 * 
	 *@param paramMap is map of all attributes name excluding id of entity in running_case and their respective values and value is id in case of attribute being itself an entity
	 *@param status is to tell if ADD or UPDATE
	 *
	 * @return void
	 */
	public /*static*/ void saveRecord(Map<String, String[]> paramMap) throws HibernateException{
		//paramMap is map of all attributes name excluding id of entity in running_case and their respective values
		// and value is id in case of attribute being itself an entity
		Iterator<Entry<String, String[]>> i= paramMap.entrySet().iterator();
		int paramMapSize= paramMap.size();
		//first two parameters are query and status and third can be id
		int extraParams=2;
		int status=0;
		//TODO long
		int id=0;
		//for(int f=0;f<extraParams;f++)
		//skip query param
		i.next();
		//get status param
		String statusString= i.next().getValue()[0];
		if(statusString.equals("add"))
			status= ADD_RECORD;
		else if(statusString.equals("update")) {
			status= UPDATE_RECORD;
			//get id param
			id= Integer.parseInt(i.next().getValue()[0]);
			extraParams=3;
		}
		
		String keys[]= new String[paramMapSize-extraParams];
		String keysForSetter[]= getAttributesName();
		String keysToCamelCase[]= new String[paramMapSize-extraParams];
		String str[]= new String[keys.length];
		String strMultiValue[][] =  new String[keys.length][];
		Object obj[]= new Object[keys.length];
		int p=0;
		for(p=0;p<str.length;p++) {
			Entry<String,String[]> ent= i.next();
			keys[p]= ent.getKey();
			strMultiValue[p]= ent.getValue();
			str[p]= strMultiValue[p][0];
		}
		p=0;
		for(String temp: keys) {
			keysToCamelCase[p++]= toCamelCaseFromRunningCase(temp);
		}
		p=0;
		for(String string: keysForSetter) {
			keysForSetter[p++]= string.substring(0,1).toUpperCase()+string.substring(1);
		}
		
		Class<?> paramArray[]= new Class<?>[str.length]; 
		//Arrays.fill(paramArray, String.class);
		//Arrays.fill(paramArray, Object.class);
		
		String types[]= getAttributesType();
		//XXX input types in this method, save/index.jsp and getAttributesType method
		for(p=0; p<str.length; p++){
			if(types[p].equals("text") || types[p].equals("options")){
				paramArray[p]= String.class;
				obj[p]= str[p];
			}
			else if(types[p].equals("number")){
				paramArray[p]= int.class;
				if(str[p]==null || str[p].equals(""))
					str[p]= "0";
				obj[p]= Integer.parseInt(str[p]);
			} 
			else if(types[p].equals("numberAnyStep")){
				paramArray[p]= BigDecimal.class;
				if(str[p]==null || str[p].equals(""))
					str[p]= "0";
				//obj[p]= Double.parseDouble(str[p]);
				obj[p]= new BigDecimal(str[p]);
			} 
			else if(types[p].equals("date")){
				paramArray[p]= java.sql.Date.class;
				//try {
					// if util.Date then use @Temporal(TemporalType.DATE) in entity classes to store only Date without time
					//SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
					//obj[p]= sdf.parse(str[p]);
				if(str[p]==null || str[p].equals(""))
					obj[p]= null;
				else
					obj[p]= java.sql.Date.valueOf(str[p]);
				/*} catch (ParseException e) {
					throw new HibernateException("Some unexpected error occured while parsing date to save a record: ", e);
				}*/
			} 
			else if(types[p].equals("datetime")){
				paramArray[p]= java.util.Date.class;
				try {
				//html datetime-local input type has format:- 2001-01-01T13:00
				SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
				if(str[p]==null || str[p].equals(""))
					obj[p]= null;
				else
					obj[p]= sdf.parse(str[p]);
				} catch (ParseException e) {
					throw new HibernateException("Some unexpected error occured while parsing date to save a record: ", e);
				}
			} 
			else if(types[p].equals("month")){
				paramArray[p]= java.sql.Date.class;
				//try {
				// if util.Date then use @Temporal(TemporalType.DATE) in entity classes to store only Date without time
				//SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
				//obj[p]= sdf.parse(str[p]);
				if(str[p]==null || str[p].equals(""))
					obj[p]= null;
				else
					obj[p]= java.sql.Date.valueOf(str[p]+"-01");
				/*} catch (ParseException e) {
					throw new HibernateException("Some unexpected error occured while parsing date to save a record: ", e);
				}*/
			} 
			else if(types[p].equals("select")){
				Class<?> entityClassAttr;
				String entityClassNameAttr=keysToCamelCase[p];
				try {
					entityClassAttr= Class.forName(/*packageName*/SessionFactoryBuilder.classesMapping.get(entityClassNameAttr) +entityClassNameAttr);
					if(entityClassAttr.getAnnotation(Entity.class)==null)
						throw new Exception("Not an Entity class");
				}
				catch(Exception e) {
					throw new HibernateException("Some unexpected error occured while getting Class of "+entityClassNameAttr+" for saving", e);
				}
				
				
				paramArray[p]= entityClassAttr;//com.saptris.erp.db.Persistable.class;
				//TODO Long.parseLong
				if(str[p]==null || str[p].equals(""))
					obj[p]=null;
				else
					obj[p]= new EntityManager(entityClassNameAttr).getEntity(Integer.parseInt(str[p]));
			}
			else if(types[p].equals("add-more")){
				Class<?> entityClassAttr;
				String entityClassNameAttr=keysToCamelCase[p];
				try {
					entityClassAttr= Class.forName(/*packageName*/SessionFactoryBuilder.classesMapping.get(entityClassNameAttr) +entityClassNameAttr);
					if(entityClassAttr.getAnnotation(Entity.class)==null)
						throw new Exception("Not an Entity class");
				}
				catch(Exception e) {
					throw new HibernateException("Some unexpected error occured while getting Class of "+entityClassNameAttr+" for saving", e);
				}
				
				
				//paramArray[p]= List.class;//com.saptris.erp.db.Persistable.class;
				paramArray[p]= Set.class;//com.saptris.erp.db.Persistable.class;
				//TODO Long.parseLong
				//parameters have one empty value of hidden field so least size in this case is 1
				//if(str[p]==null || str[p].equals(""))
				if(strMultiValue[p].length==1)
					//obj[p]=new ArrayList<>();
					obj[p]=new HashSet<>();
				else {
					Set<Object> set= new HashSet<>();
					//ArrayList<Object> list= new ArrayList<>();
					for(String temp: strMultiValue[p]) {
						if(temp!=null && !temp.equals(""))
							//list.add(new EntityManager(entityClassNameAttr).getEntity(Integer.parseInt(temp)));
							set.add(new EntityManager(entityClassNameAttr).getEntity(Integer.parseInt(temp)));
					}
					//obj[p]= list;
					obj[p]= set;
				}
			}
			else if(types[p].equals("user")) {
				paramArray[p]= User.class;
				obj[p]= UserManager.getUser();
			}
		}
		
		try{
			//NonNull in lombok cause problem
			//Object res= entityClass.getConstructor(paramArray).newInstance(obj);
			Object res= entityClass.newInstance();
			for(int j=0; j<obj.length;j++) {
				entityClass.getDeclaredMethod("set"+keysForSetter[j], paramArray[j]).invoke(res, obj[j]);
			}
			
			if(status==ADD_RECORD) {
				TransactionalOperation.save(res);
			}
			else if(status==UPDATE_RECORD) {
				String idName=null;
				for(Field f: entityClass.getDeclaredFields()) {
					if(f.getAnnotation(Id.class)!=null)
						idName= f.getName();
				}
				//TODO long
				entityClass.getMethod("set"+idName.substring(0, 1).toUpperCase()+idName.substring(1), int.class).invoke(res, id);
				TransactionalOperation.update(res);
			}
			if(entityClass==MaintenanceAllUsers.class) {
				ReminderScheduler.notifyForNewReminder((MaintenanceAllUsers)res);
			}
		}
		catch(Exception e){
			throw new HibernateException("Some unexpected error occured while saving "+entityClass.getName(), e);
		}
		//addCompany(i.next().getValue()[0], i.next().getValue()[0], i.next().getValue()[0], i.next().getValue()[0], i.next().getValue()[0], i.next().getValue()[0]);
		
		
		/*//print the paramMap
 	java.util.Set<Map.Entry<String, String[]>> set=paramMap.entrySet();
	java.util.Iterator<Map.Entry<String, String[]>> i= set.iterator();
	while(i.hasNext()) {
		Map.Entry<String, String[]> e= i.next();
		System.out.print(e.getKey()+" : {");
		for(String s:e.getValue()) {
			System.out.print(" ["+s+"] ");
		}
		System.out.println("}");
	}*/
	}
	
	/*static*//* public void addCompany(String name, String phone, String email, String gstin, String address, String state) throws HibernateException{
		try{
			TransactionalSave.save(new Company(name, phone, email, gstin, address, state));
		}
		catch(Exception e){
			throw new HibernateException("Some unexpected error occured while adding new company", e);
		}
	}*/
		
	public boolean delete(int primaryKey) {
		Session hbnSession = null;
		try{
			Object entity= getEntity(primaryKey);
			//if(entity instanceof Employee)	((Employee)entity).beforeDelete();
			try{
				Method method= entityClass.getDeclaredMethod("beforeDelete()");
				method.invoke(entity);
			}
			catch(NoSuchMethodException nsme) {}
				
			if(deletable(entity)) {
				return TransactionalOperation.delete(entity);
			}
			else
				return false;
		}
		catch(Exception e){
			throw new HibernateException("Some unexpected error occured while deleting entity of "+entityClass.getName(), e);
		}
		finally{
			if(hbnSession!=null)
				hbnSession.close();
		}
	}
	
	private boolean deletable(Object entity) {
		//Class<?> entityClass= entity.getClass();
		String oneToMany="";
		for(Field f:entityClass.getDeclaredFields()) {
			if(f.getAnnotation(OneToMany.class)!=null) {
				oneToMany= f.getName();
				break;
			}
		}
		if(oneToMany.length()==0)
			return true;
		//List<?> list=null;
		Set<?> set=null;
		try {
			//list= (List<?>) entityClass.getMethod("get"+oneToMany.substring(0,1).toUpperCase()+oneToMany.substring(1)).invoke(entity);
			set= (Set<?>) entityClass.getMethod("get"+oneToMany.substring(0,1).toUpperCase()+oneToMany.substring(1)).invoke(entity);
		} catch (IllegalAccessException | IllegalArgumentException |InvocationTargetException | NoSuchMethodException | SecurityException  e) {
			e.printStackTrace();
			System.out.println("Some unexpected eror occured while getting all values in deletable");
		}
		//if(list==null || list.size()==0)
		if(set==null || set.size()==0)
			return true;
		else return false;
	}
	
	//public String getValue(String primaryKey, String columnName) {return null;}
	//public int setValue(String primaryKey, String columnName, String value) {return -1;}
	//public String[] retrieveColumn(String columnName) {return null;}
	//public static String[] getDbTablesList(){return null;}
	///*private*/ int getRowsCount(){/*select count(*) from table*/return -1;}
}
