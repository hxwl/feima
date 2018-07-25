package com.hx.hxfeima.base.dao;

import java.util.List;
import java.util.Map;
import org.hibernate.SQLQuery;
import org.hibernate.type.Type;

public abstract interface BaseSqlDao
{
  public abstract SQLQuery createSQLQuery(String paramString);

  public abstract int executeUpdate(String paramString);

  public abstract int executeUpdate(String paramString, Object[] paramArrayOfObject);

  public abstract int executeUpdate(String paramString, List<Object> paramList);

  public abstract int executeUpdate(String paramString, Map<String, Object> paramMap);

  public abstract <T> T get(Class<T> paramClass, String paramString);

  public abstract <T> T get(Class<T> paramClass, String paramString, Object[] paramArrayOfObject);

  public abstract <T> T get(Class<T> paramClass, String paramString, List<Object> paramList);

  public abstract <T> T get(Class<T> paramClass, String paramString, Map<String, Object> paramMap);

  public abstract Map get(String paramString, Map<String, Type> paramMap);

  public abstract Map get(String paramString, Object[] paramArrayOfObject, Map<String, Type> paramMap);

  public abstract Map get(String paramString, List<Object> paramList, Map<String, Type> paramMap);

  public abstract Map get(String paramString, Map<String, Object> paramMap, Map<String, Type> paramMap1);

  public abstract Map first(String paramString, Map<String, Type> paramMap);

  public abstract Map first(String paramString, Object[] paramArrayOfObject, Map<String, Type> paramMap);

  public abstract Map first(String paramString, List<Object> paramList, Map<String, Type> paramMap);

  public abstract Map first(String paramString, Map<String, Object> paramMap, Map<String, Type> paramMap1);

  public abstract List<Map> find(String paramString, Map<String, Type> paramMap);

  public abstract List<Map> find(String paramString, Object[] paramArrayOfObject, Map<String, Type> paramMap);

  public abstract List<Map> find(String paramString, List<Object> paramList, Map<String, Type> paramMap);

  public abstract List<Map> find(String paramString, Map<String, Object> paramMap, Map<String, Type> paramMap1);

  public abstract List<Map> find(String paramString, Map<String, Type> paramMap, Integer paramInteger1, Integer paramInteger2);

  public abstract List<Map> find(String paramString, Object[] paramArrayOfObject, Map<String, Type> paramMap, Integer paramInteger1, Integer paramInteger2);

  public abstract List<Map> find(String paramString, List<Object> paramList, Map<String, Type> paramMap, Integer paramInteger1, Integer paramInteger2);

  public abstract List<Map> find(String paramString, Map<String, Object> paramMap, Map<String, Type> paramMap1, Integer paramInteger1, Integer paramInteger2);

  public abstract long count(String paramString);

  public abstract long count(String paramString, Object[] paramArrayOfObject);

  public abstract long count(String paramString, List<Object> paramList);

  public abstract long count(String paramString, Map<String, Object> paramMap);
}