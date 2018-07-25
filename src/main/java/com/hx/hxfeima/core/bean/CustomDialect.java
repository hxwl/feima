package com.hx.hxfeima.core.bean;

import java.sql.Types;
import org.hibernate.dialect.MySQL5Dialect;

public class CustomDialect extends MySQL5Dialect {
	public CustomDialect() {  
        super();  
        registerHibernateType(Types.DECIMAL, "double");  
        registerHibernateType(-1, "string");  
        registerHibernateType(Types.CHAR, "string");  
        registerHibernateType(Types.BIGINT, "int"); 
        
//        registerHibernateType(Types.DECIMAL, Hibernate.BIG_DECIMAL.getName());  
//        registerHibernateType(-1, Hibernate.STRING.getName());    StringType.INSTANCE
//        registerHibernateType(Types.CHAR,Hibernate.STRING.getName());  
//        registerHibernateType(Types.BIGINT,Hibernate.INTEGER.getName()); 
    }  
}
