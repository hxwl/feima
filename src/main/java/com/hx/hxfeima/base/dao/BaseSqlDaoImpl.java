package com.hx.hxfeima.base.dao;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.hibernate.type.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;




@Repository("baseSqlDao")
public class BaseSqlDaoImpl implements BaseSqlDao {

//	@Resource(name="sessionFactory")
//	private SessionFactory sessionFactory;

	/*@Resource
	protected SessionFactory sessionFactory;*/

	@Autowired
	private SessionFactory sessionFactory;

//	@Autowired
//	private Systems systems;
	
	private Session getSession(){
		return sessionFactory.getCurrentSession();
	}
	
	private int page(Integer page, Integer rows){
		if(page==null){
			page = 1;
		}
		return (page - 1) * rows;
	}
	
	private SQLQuery setParams(SQLQuery query, List<Object> params){
		for(int i=0; i<params.size(); i++){
			query.setParameter(String.valueOf(i), params.get(i));
		}
		return query;
	}
	
	private SQLQuery setParams(SQLQuery query, Object[] params){
		for(int i=0; i<params.length; i++){
			query.setParameter(String.valueOf(i), params[i]);
		}
		return query;
	}
	
	private SQLQuery setScalar(SQLQuery query, Map<String,Type> types){
		for(Map.Entry<String,Type> entry : types.entrySet()){
			query.addScalar(entry.getKey(), entry.getValue());
		}
		return query;
	}
	
	private List paging(SQLQuery query, Integer page, Integer rows){
		if(rows==null){
			//rows = systems.getDefaultRows();

			rows = 15;
		}
		return query.setFirstResult(page(page, rows)).setMaxResults(rows).list();
	}
	
	private List paging(Query query, Integer page, Integer rows){
		if(rows==null){
			//rows = systems.getDefaultRows();

			  rows = 15;
		}
		return query.setFirstResult(page(page, rows)).setMaxResults(rows).list();
	}
	
	private Map safedMap(Object obj){
		if(obj!=null){
			return (Map)obj;
		}
		return null;
	}
	
	private Map safedMap(List list){
		if(list!=null){
			return (Map)list.get(0);
		}
		return null;
	}
	
	private <T> T safedT(Class<T> clazz, Object obj){
		if(obj!=null){
			return (T)obj;
		}
		return null;
	}
	
	@Override
	public SQLQuery createSQLQuery(String sql) {
		return this.getSession().createSQLQuery(sql);
	}

	@Override
	public int executeUpdate(String sql) {
		return this.createSQLQuery(sql).executeUpdate();
	}

	@Override
	public int executeUpdate(String sql, Object[] params) {
		SQLQuery query = this.createSQLQuery(sql);
		query = this.setParams(query, params);
		return query.executeUpdate();
	}

	@Override
	public int executeUpdate(String sql, List<Object> params) {
		SQLQuery query = this.createSQLQuery(sql);
		query = this.setParams(query, params);
		return query.executeUpdate();
	}

	@Override
	public int executeUpdate(String sql, Map<String, Object> params) {
		return this.createSQLQuery(sql).setProperties(params).executeUpdate();
	}

	@Override
	public <T> T get(Class<T> entity, String sql) {
		SQLQuery query = createSQLQuery(sql);
		Object obj = query.addEntity(entity).uniqueResult();
		return this.safedT(entity, obj);
	}
	
	@Override
	public <T> T get(Class<T> entity, String sql, Object[] params) {
		SQLQuery query = createSQLQuery(sql);
		query.addEntity(entity);
		for(int i=0; i<params.length; i++){
			query.setParameter(String.valueOf(i), params.length);
		}
		Object obj = query.uniqueResult();
		return this.safedT(entity, obj);
	}

	@Override
	public <T> T get(Class<T> entity, String sql, List<Object> params) {
		SQLQuery query = createSQLQuery(sql);
		query.addEntity(entity);
		for(int i=0; i<params.size(); i++){
			query.setParameter(String.valueOf(i), params.get(i));
		}
		Object obj = query.uniqueResult();
		return this.safedT(entity, obj);
	}

	@Override
	public <T> T get(Class<T> entity, String sql, Map<String, Object> params) {
		Object obj = createSQLQuery(sql).addEntity(entity).setProperties(params).uniqueResult();
		return this.safedT(entity, obj);
	}

	@Override
	public Map get(String sql, Map<String, Type> types) {
		SQLQuery query = createSQLQuery(sql);
		query = setScalar(query, types);
		Object obj = query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).uniqueResult();
		return this.safedMap(obj);
	}

	@Override
	public Map get(String sql, Object[] params, Map<String, Type> types) {
		SQLQuery query = createSQLQuery(sql);
		query = setParams(query, params);
		query = setScalar(query, types);
		Object obj = query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).uniqueResult();
		return this.safedMap(obj);
	}

	@Override
	public Map get(String sql, List<Object> params, Map<String, Type> types) {
		SQLQuery query = createSQLQuery(sql);
		query = setParams(query, params);
		query = setScalar(query, types);
		Object obj = query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).uniqueResult();
		return this.safedMap(obj);
	}

	@Override
	public Map get(String sql, Map<String, Object> params,
			Map<String, Type> types) {
		SQLQuery query = createSQLQuery(sql);
		query = setScalar(query, types);
		Object obj = query.setProperties(params)
				.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).uniqueResult();
		return this.safedMap(obj);
	}

	@Override
	public Map first(String sql, Map<String, Type> types) {
		SQLQuery query = createSQLQuery(sql);
		query = setScalar(query, types);
		List list = paging(query, 1, 1);
		return this.safedMap(list);
	}

	@Override
	public Map first(String sql, Object[] params, Map<String, Type> types) {
		SQLQuery query = createSQLQuery(sql);
		query = setScalar(query, types);
		query = setParams(query, params);
		List list = paging(query, 1, 1);
		return this.safedMap(list);
	}

	@Override
	public Map first(String sql, List<Object> params, Map<String, Type> types) {
		SQLQuery query = createSQLQuery(sql);
		query = setParams(query, params);
		query = setScalar(query, types);
		List list = paging(query, 1, 1);
		return this.safedMap(list);
	}

	@Override
	public Map first(String sql, Map<String, Object> params,
			Map<String, Type> types) {
		SQLQuery query = createSQLQuery(sql);
		query = setScalar(query, types);
		List list = paging(query.setProperties(params), 1, 1);
		return this.safedMap(list);
	}

	@Override
	public List<Map> find(String sql, Map<String, Type> types) {
		SQLQuery sqlQuery = createSQLQuery(sql);
		sqlQuery = setScalar(sqlQuery, types);
		return sqlQuery.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
	}

	@Override
	public List<Map> find(String sql, Object[] params, Map<String, Type> types) {
		SQLQuery sqlQuery = createSQLQuery(sql);
		sqlQuery = setScalar(sqlQuery, types);
		sqlQuery = setParams(sqlQuery, params);
		return sqlQuery.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
	}

	@Override
	public List<Map> find(String sql, List<Object> params,
			Map<String, Type> types) {
		SQLQuery sqlQuery = createSQLQuery(sql);
		sqlQuery = setScalar(sqlQuery, types);
		sqlQuery = setParams(sqlQuery, params);
		return sqlQuery.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
	}

	@Override
	public List<Map> find(String sql, Map<String, Object> params,
			Map<String, Type> types) {
		SQLQuery sqlQuery = createSQLQuery(sql);
		sqlQuery = setScalar(sqlQuery, types);
		return sqlQuery.setProperties(params).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
	}

	@Override
	public List<Map> find(String sql, Map<String, Type> types, Integer page,
			Integer rows) {
		SQLQuery sqlQuery = createSQLQuery(sql);
		sqlQuery = setScalar(sqlQuery, types);
		Query query = sqlQuery.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		return paging(query, page, rows);
	}

	@Override
	public List<Map> find(String sql, Object[] params, Map<String, Type> types,
			Integer page, Integer rows) {
		SQLQuery query = this.createSQLQuery(sql);
		query = this.setScalar(query, types);
		query = this.setParams(query, params);
		Query q = query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		return this.paging(q, page, rows);
	}

	@Override
	public List<Map> find(String sql, List<Object> params,
			Map<String, Type> types, Integer page, Integer rows) {
		SQLQuery query = this.createSQLQuery(sql);
		query = this.setScalar(query, types);
		query = this.setParams(query, params);
		Query q = query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		return this.paging(q, page, rows);
	}

	@Override
	public List<Map> find(String sql, Map<String, Object> params,
			Map<String, Type> types, Integer page, Integer rows) {
		SQLQuery sqlQuery = createSQLQuery(sql);
		sqlQuery = setScalar(sqlQuery, types);
		Query query = sqlQuery.setProperties(params).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		return paging(query, page, rows);
	}

	@Override
	public long count(String sql) {
		SQLQuery sqlQuery = createSQLQuery(sql);
		String rs = sqlQuery.uniqueResult().toString();
		return Long.parseLong(rs);
	}

	@Override
	public long count(String sql, Object[] params) {
		SQLQuery sqlQuery = createSQLQuery(sql);
		sqlQuery = setParams(sqlQuery, params);
		String rs = sqlQuery.uniqueResult().toString();
		return Long.parseLong(rs);
	}

	@Override
	public long count(String sql, List<Object> params) {
		SQLQuery sqlQuery = createSQLQuery(sql);
		sqlQuery = setParams(sqlQuery, params);
		String rs = sqlQuery.uniqueResult().toString();
		return Long.parseLong(rs);
	}

	@Override
	public long count(String sql, Map<String, Object> params) {
		SQLQuery sqlQuery = createSQLQuery(sql);
		String rs = sqlQuery.setProperties(params).uniqueResult().toString();
		return Long.parseLong(rs);
	}

}
