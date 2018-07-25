package com.hx.hxfeima.base.dao;

import java.beans.IntrospectionException;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.math.BigInteger;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;

;
import com.hx.hxfeima.core.bean.PageBean;
import com.hx.hxfeima.core.model.Systems;
import com.hx.hxfeima.utils.ExtConstantUtil;
import com.hx.hxfeima.utils.RegUtil;
import com.hx.hxfeima.utils.UtilTool;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.hibernate.procedure.ProcedureCall;
import org.hibernate.procedure.ProcedureOutputs;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.orm.hibernate5.SessionFactoryUtils;
import org.springframework.stereotype.Repository;




@Repository("baseDao")
public class BaseDaoImpl<T> implements BaseDao<T> {

    @Autowired
    private SessionFactory sessionFactory;





    @Autowired
    private Systems systems;
    @Autowired
    private HibernateTemplate hibernateTemplate;

    private int page(Integer page, Integer rows){
        if(page==null){
            page = 1;
        }
        if(rows==null){
            rows = systems.getDefaultRows();
        }
        return (page - 1) * rows;
    }

    private Query createQuery(String hql){
        return this.getSession().createQuery(hql);
    }

    private Query createQueryWithPage(String hql, Integer page, Integer rows){
        if(rows==null){
            rows = systems.getDefaultRows();
        }
        return this.getSession().createQuery(hql)
                .setFirstResult(page(page, rows))
                .setMaxResults(rows);
    }

    public Session getSession() {
        return sessionFactory.getCurrentSession();

    }

    @Override
    public Serializable save(Object o) {
        Session s = this.getSession();
        Serializable ser = s.save(o);
        s.flush();
        return ser;
    }

    @Override
    public void delete(Object o) {
        this.getSession().delete(o);
    }

    @Override
    public int delete(String hql) {
        return createQuery(hql).executeUpdate();
    }

    @Override
    public int delete(String hql, Object[] args) {
        Query query = createQuery(hql);
        for(int i=0; i<args.length; i++){
            query.setParameter(i, args[i]);
        }
        return query.executeUpdate();
    }

    @Override
    public int delete(String hql, List<Object> args) {
        Query query = createQuery(hql);
        for(int i=0; i<args.size(); i++){
            query.setParameter(String.valueOf(i), args.get(i));
        }
        return query.executeUpdate();
    }

    @Override
    public int delete(String hql, Map<String, Object> args) {
        return createQuery(hql).setProperties(args).executeUpdate();
    }

    @Override
    public void update(Object o) {
        this.getSession().update(o);
    }

    @Override
    public int update(String hql) {
        return createQuery(hql).executeUpdate();
    }

    @Override
    public int update(String hql, Object[] args) {
        Query query = createQuery(hql);
        for(int i=0; i<args.length; i++){
            query.setParameter(String.valueOf(i), args[i]);
        }
        return query.executeUpdate();
    }

    @Override
    public int update(String hql, List<Object> args) {
        Query query = createQuery(hql);
        for(int i=0; i<args.size(); i++){
            query.setParameter(String.valueOf(i), args.get(i));
        }
        return query.executeUpdate();
    }

    @Override
    public int update(String hql, Map<String, Object> args) {
        return createQuery(hql).setProperties(args).executeUpdate();
    }

    @Override
    public void saveOrUpdate(Object o) {
        Session s = this.getSession();
        s.saveOrUpdate(o);
        s.flush();
    }

    @Override
    public Object first(String hql) {
        return createQueryWithPage(hql, 1, 1).uniqueResult();
    }

    @Override
    public Object first(String hql, Object[] args) {
        Query query = this.createQueryWithPage(hql, 1, 1);
        for(int i=0; i<args.length; i++){
            query.setParameter(String.valueOf(i), args[i]);
        }
        return query.uniqueResult();
    }

    @Override
    public Object first(String hql, List<Object> args) {
        Query query = this.createQueryWithPage(hql, 1, 1);
        for(int i=0; i<args.size(); i++){
            query.setParameter(String.valueOf(i), args.get(i));
        }
        return query.uniqueResult();
    }

    @Override
    public Object first(String hql, Map<String, Object> args) {
        return this.createQueryWithPage(hql, 1, 1).setProperties(args).uniqueResult();
    }

    @Override
    public <T> T get(Class<T> clazz, Serializable id) {
        return (T)this.getSession().get(clazz, id);
    }

    @Override
    public Object get(String hql, Object[] args) {
        Query query = createQuery(hql);
        for(int i=0; i<args.length; i++){
            query.setParameter(String.valueOf(i), args[i]);
        }
        return query.uniqueResult();
    }

    @Override
    public Object get(String hql, List<Object> args) {
        Query query = createQuery(hql);
        for(int i=0; i<args.size(); i++){
            query.setParameter(String.valueOf(i), args.get(i));
        }
        return query.uniqueResult();
    }

    @Override
    public Object get(String hql, Map<String, Object> args) {
        return createQuery(hql).setProperties(args).uniqueResult();
    }

    @Override
    public List find(String hql) {
        return createQuery(hql).list();
    }

    @Override
    public List find(String hql, Object... args) {
        Query query = createQuery(hql);
        for(int i=0; i<args.length; i++){
            query.setParameter(String.valueOf(i), args[i]);
        }
        return query.list();
    }

    @Override
    public List findTo(String hql, Object... args) {
        Query query = createQuery(hql);
        for(int i=0; i<args.length; i++){
            query.setParameter(i, args[i]);
        }
        return query.list();
    }

    @Override
    public List find(String hql, List<Object> args) {
        Query query = createQuery(hql);
        for(int i=0; i<args.size(); i++){
            query.setParameter(String.valueOf(i), args.get(i));
        }
        return query.list();
    }

    @Override
    public List find(String hql, Map<String, Object> args) {
        return createQuery(hql).setProperties(args).list();
    }

    @Override
    public List find(String hql, Integer page, Integer rows) {
        return createQueryWithPage(hql, page, rows).list();
    }

    @Override
    public List find(String hql, Object[] args, Integer page, Integer rows) {
        Query query = createQueryWithPage(hql, page, rows);
        for(int i=0; i<args.length; i++){
            query.setParameter(String.valueOf(i), args[i]);
        }
        return query.list();
    }

    @Override
    public List find(String hql, List<Object> args, Integer page, Integer rows) {
        Query query = createQueryWithPage(hql, page, rows);
        for(int i=0; i<args.size(); i++){
            query.setParameter(String.valueOf(i), args.get(i));
        }
        return query.list();
    }

    @Override
    public List find(String hql, Map<String, Object> args, Integer page,
                     Integer rows) {
        return createQueryWithPage(hql, page, rows)
                .setProperties(args).list();
    }

    @Override
    public long count(String hql) {
        return (Long)createQuery(hql).uniqueResult();
    }

    @Override
    public long count(String hql, Object[] args) {
        Query query = createQuery(hql);
        for(int i=0; i<args.length; i++){
            query.setParameter(String.valueOf(i), args[i]);
        }
        return (Long)query.uniqueResult();
    }

    @Override
    public long count(String hql, List<Object> args) {
        Query query = createQuery(hql);
        for(int i=0; i<args.size(); i++){
            query.setParameter(String.valueOf(i), args.get(i));
        }
        return (Long)query.uniqueResult();
    }

    @Override
    public long count(String hql, Map<String, Object> args) {
        Query query = createQuery(hql).setProperties(args);
        return (Long)query.uniqueResult();
    }

    @Override
    public List<Object> executeProcedure(String procedureName,
                                         Class[] inputParamClasses,Object[] inputParams,Class[] outParamClasses) {
        ProcedureCall call = this.getSession().createStoredProcedureCall(procedureName);
        //输入参数配置
        for(int i=0;i<inputParamClasses.length;i++){
            call.registerParameter(i,inputParamClasses[i],ParameterMode.IN)
                    .bindValue(inputParams[i]);
        }
        //传出参数配置
        int inputLength = inputParamClasses.length;
        for(int i=inputLength;i<outParamClasses.length+inputLength;i++){
            call.registerParameter(i,outParamClasses[i-inputLength],ParameterMode.OUT);
        }
        //返回结果集
        List<Object> resuList = new ArrayList<Object>();
        ProcedureOutputs output = call.getOutputs();
        for(int i = inputLength;i<inputLength+outParamClasses.length;i++){
            resuList.add(output.getOutputParameterValue(i));
        }
        return resuList;
    }



    /**
     * 通过主键ID,获取对应的对象
     */
    public T getNew(Class clazz,Serializable id)throws RuntimeException {
        return (T)hibernateTemplate.load(clazz, id);
    }

    private Session getHibernateTemplate() {
        return getSession();
    }

    /**
     * 获取所有对应对象
     * @return
     */
    public List<T> getAll(Class clazz)throws RuntimeException {
        return hibernateTemplate.loadAll(clazz);
    }

    /**
     * 保存对象
     * @param o
     */
    public void saveNew(Object o)throws RuntimeException {
//		getHibernateTemplate().saveOrUpdate(o);
        hibernateTemplate.saveOrUpdate(o);
    }

    /**
     * 删除对象
     * @param o
     */
    public void deleteNew(Object o)throws RuntimeException {
        hibernateTemplate.delete(o);
    }

    /**
     * 更新对象
     * @param o
     */
    public void updateNew(Object o)throws RuntimeException {
        hibernateTemplate.update(o);
    }

    /**
     * 通过HQL 查询列表
     * @param hql HQL 语句
     * @return
     * @throws Exception
     */
    public List<T> getListByHql(final String hql,final Map<String,Object> param)throws RuntimeException{

        List<T> result =(List<T>)hibernateTemplate.execute(new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException
            {
                Query query=session.createQuery(hql);
                if(param!=null){
                    query.setProperties(param);
                }
                return query.list();
            }
        });
        return result;
    }

    /**
     * 通过HQL 查询列表
     * @param hql
     * @param param Object...类型
     * @return
     * @throws Exception
     */
    public List<T> getListByHql(final String hql,final Object... param)throws RuntimeException{
        List<T> result =(List<T>)hibernateTemplate.execute(new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException {
                Query query=session.createQuery(hql);
                if(param!=null){
                    for(int i=0;i<param.length;i++){
                        query.setParameter(i, param[i]);
                    }
                }
                return query.list();
            }
        });
        return result;
    }

    /**
     * 通过HQL 查询单个对象
     * @param hql
     * @param param
     * @return
     * @throws Exception
     */
    public T getObjByHql(final String hql, final Object... param)throws RuntimeException{
        T result=(T)hibernateTemplate.execute(new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException {
                Query query=session.createQuery(hql);
                if(param!=null){
                    for(int i=0;i<param.length;i++){
                        query.setParameter(i, param[i]);
                    }
                }
                return query.uniqueResult();
            }
        });
        return result;
    }

    /**
     * 通过HQL 查询单个对象
     * @param hql
     * @param param
     * @return
     * @throws Exception
     */
    public T getObjByHql(final String hql,final Map<String,Object> param) throws RuntimeException{
        T result=(T)hibernateTemplate.execute(new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException {
                Query query=session.createQuery(hql);
                if(param!=null){
                    query.setProperties(param);
                }
                T result=(T)query.uniqueResult();
                return result;
            }
        });
        return result;
    }

    /**
     * 通过SQL 查询 列表类型为Map
     * @param sql SQL语句
     * @param param 查询参数值
     * @return
     * @throws Exception
     */
    public List<Map> getObjListBySql(final String sql,final Object... param) throws RuntimeException{
        List<Map> result=(List<Map>)hibernateTemplate.execute(new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException
            {
                SQLQuery query=(SQLQuery) session.createSQLQuery(sql).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
                if(param!=null){
                    for(int i=0;i<param.length;i++){
                        query.setParameter(i, param[i]);
                    }
                }
                return query.list();
            }
        });
        return result;
    }

    /**
     * 通过
     * @param sql SQL语句
     * @param param 查询参数值
     * @return
     * @throws InvocationTargetException
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws IntrospectionException
     */
    public List<Map> getObjListBySql(final String sql,final Map<String,Object> param)throws RuntimeException{
        List<Map> result=(List<Map>)hibernateTemplate.execute(new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException
            {
                SQLQuery query=(SQLQuery) session.createSQLQuery(sql).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
                if(param!=null){
                    query.setProperties(param);
                }
                return query.list();
            }
        });
        return result;
    }

    /**
     * 通过SQL 查询 列表类型为Map
     * @param sql SQL语句 参数通过拼接闯入(SQL注入攻击不推荐使用)
     * @return
     * @throws Exception
     */
    public List<Map> getObjListBySql(final String sql)throws RuntimeException{
        List<Map> result=(List<Map>)hibernateTemplate.execute(new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException
            {
                SQLQuery query=(SQLQuery) session.createSQLQuery(sql).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
                return query.list();
            }
        });
        return result;
    }

    /**
     * 通过SQL 查询 传入 页数及长度
     * @param sql SQL语句  起始页  页面长度 (SQL注入攻击不推荐使用,查看验证语句方便)
     * @return
     * @throws Exception
     */
    public List<Map> getObjListBySql(String sql, Integer page, Integer rows)throws RuntimeException{
        if(rows==null) rows = systems.getDefaultRows();
        final String querySql = sql + " limit " + page(page, rows) + "," + rows;

        List<Map> result=(List<Map>)hibernateTemplate.execute(new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException
            {
                SQLQuery query=(SQLQuery) session.createSQLQuery(querySql).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
                return query.list();
            }
        });
        return result;
    }

    /**
     * 通过SQL 查询 获得数量
     * @param sql
     * @param param
     * @return
     * @throws Exception
     */
    public Integer getObjCountBySql(final String sql,final Object... param)throws RuntimeException{

        Integer total=(Integer)hibernateTemplate.execute(new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException
            {
                SQLQuery query=session.createSQLQuery(sql);
                if(param!=null){
                    for(int i=0;i<param.length;i++){
                        query.setParameter(i, param[i]);
                    }
                }
                List temp=query.list();
                int tempInt=0;
                if(temp!=null){
                    tempInt=(Integer)temp.get(0);
                }
                return tempInt;
            }
        });
        return total;
    }

    /**
     * 通过SQL 查询 获得数量
     * @param sql
     * @param param
     * @return
     * @throws Exception
     */
    public Integer getObjCountBySql(final String sql,final Map<String,Object> param)throws RuntimeException{

        Integer total=(Integer)hibernateTemplate.execute(new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException
            {
                SQLQuery query=session.createSQLQuery(sql);
                if(param!=null){
                    query.setProperties(param);
                }
                List temp=query.list();
                int tempInt=0;
                if(temp!=null){
                    tempInt=(Integer)temp.get(0);
                }
                return tempInt;
            }
        });
        return total;
    }

    /**
     * 使用原生SQL查询数量
     * @param sql
     * @return
     */
    public Integer getObjCountBySql(String sql)throws RuntimeException{
        final String fsql = sql;
        Integer count = (Integer)this.hibernateTemplate.execute(
                new HibernateCallback() {
                    public Object doInHibernate(Session session)
                            throws HibernateException {
                        SQLQuery query = session.createSQLQuery(fsql);
						/*List temp=(Integer)query.uniqueResult();
						int tempInt=0;
						if(temp!=null){
							tempInt=((BigInteger)temp.get(0)).intValue();
						}*/
                        return ((BigInteger)query.uniqueResult()).intValue();
                    }
                });
        return count;
    }



    /**
     * 执行单条SQL 语句
     * @param sql sql语句
     * @param param 参数列表
     * @return 影响条数
     * @throws Exception
     */
    public Integer executeSql(final String sql,final Object... param)throws RuntimeException{
        Integer flag=(Integer)hibernateTemplate.execute(new HibernateCallback() {
            @Override
            public Integer doInHibernate(Session session) throws HibernateException	{
                SQLQuery query=(SQLQuery) session.createSQLQuery(sql);
                if(param!=null){
                    for(int i=0;i<param.length;i++){
                        query.setParameter(i, param[i]);
                    }
                }
//				session.connection().setAutoCommit(false);
                int flag=query.executeUpdate();
//				session.connection().commit();
                return flag;
            }
        });
        return flag;
    }

    /**
     * 执行单条SQL 语句
     * @param sql sql语句
     * @param param 参数列表
     * @return 影响条数
     * @throws Exception
     */
    public Integer executeSql(final String sql,final Map<String,Object> param)throws RuntimeException{

        Integer flag=(Integer)hibernateTemplate.execute(new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException
            {
                SQLQuery query=(SQLQuery) session.createSQLQuery(sql);
                if(param!=null){
                    query.setProperties(param);
                }
//				session.connection().setAutoCommit(false);
                int flag=query.executeUpdate();
//				session.connection().commit();
                return flag;
            }
        });
        return flag;
    }


    /**
     * 执行单条SQL 语句
     * @param sql SQL语句 参数通过拼接闯入(SQL注入攻击不推荐使用,查看验证语句方便)
     * @return
     * @throws Exception
     */
    public Integer executeSql(final String sql)throws RuntimeException{

        Integer flag=(Integer)hibernateTemplate.execute(new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException
            {
                SQLQuery query=(SQLQuery) session.createSQLQuery(sql);
                //session.connection().setAutoCommit(false);
                int flag=query.executeUpdate();
                //session.connection().commit();
                return flag;
            }
        });
        return flag;
    }


    /**
     * 执行批量SQL 语句操作
     * @param sql SQL语句
     * @param collection List列表 里面以键值对形式保存数据
     * @return 返回影响条数
     * @throws SQLException
     * @throws Exception
     */
    public Integer executeBatchSql(final String sql,final List<Object[]> collection) throws RuntimeException{
        Integer flag=(Integer)hibernateTemplate.execute(new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException
            {
//				Session session=getHibernateTemplate().getSessionFactory().openSession();
                Connection conn=null;
                Integer flag=0;
                try{


                    conn = SessionFactoryUtils.getDataSource(sessionFactory).getConnection(); //session.connection();
                    PreparedStatement state=conn.prepareStatement(sql);
                    //不自动提交
                    conn.setAutoCommit(false);
                    for(int j=0;j<collection.size();j++){
                        Object[] temp=collection.get(j);
                        for(int i=0;i<temp.length;i++){
                            state.setObject(i+1, temp[i]);
                        }
                        state.addBatch();
                        if ((j+1)% 1000 == 0) {
                            int[] result=state.executeBatch();
                            for(int tempI:result){
                                if(tempI==1){
                                    flag++;
                                }
                            }
                        }
                    }
                    int[] result=null;
                    if(collection.size()%1000!=0){
                        result=state.executeBatch();
                    }
                    conn.commit();
                    if(result!=null){
                        for(int temp:result){
                            if(temp==1){
                                flag++;
                            }
                        }
                    }
                }catch(Exception e){
                    e.printStackTrace();
                    //出错回滚数据
                    try {
                        conn.rollback();
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                }finally{
                    session.close();
                }
                return flag;
            }
        });
        return flag;
    }


    /**
     * 通过SQL 查询列表 分页
     * @param sql  Sql 语句
     * @param keyColumnName  表主键
     * @param tableAlias  主表别名
     * @param param  查询参数
     * @param page 分页信息
     * @return Map<String,Object> 其中含有3个关键Key:
     *	ExtConstantUtil.GRID_TOTAL：查询总条数
     *	ExtConstantUtil.GRID_ITEMS：查询记录
     *	ExtConstantUtil.GRID_SUCCESS：查询是否含有记录
     * @throws Exception
     */
    public Map<String,Object> getObjListPageBySql(String sql,String keyColumnName,String tableAlias,Map<String,Object> param,PageBean page)throws RuntimeException{
        return getObjListPageBySql(sql, keyColumnName, tableAlias, false, param, page);
    }

    /**
     * 通过SQL 查询列表 分页
     * @param sql  Sql 语句
     * @param keyColumnName  表主键
     * @param param 查询参数
     * @param page 分页信息
     * @param isAsc 是否升序  默认升序 true:升序 false 降序
     * @return Map<String,Object> 其中含有3个关键Key:
     *	ExtConstantUtil.GRID_TOTAL：查询总条数
     *	ExtConstantUtil.GRID_ITEMS：查询记录
     *	ExtConstantUtil.GRID_SUCCESS：查询是否含有记录
     * @throws Exception
     */
    public Map<String,Object> getObjListPageBySql(String sql,String keyColumnName,String tableAlias,boolean isAsc,Map<String,Object> param,PageBean page)throws RuntimeException{
        Map<String,Object> resultNew=new HashMap<String, Object>();

        if(keyColumnName==null&&"".equals(keyColumnName)){
            throw new RuntimeException("keyColumnName 参数不能为空");
        }
        //分离出第一个from sql.toLowerCase().indexOf("from")
        Integer index= RegUtil.findIndex(sql.toLowerCase(), "from\\s+[0-9a-zA-Z_]+\\s+"+tableAlias.toLowerCase()+"\\s");
        Integer index2=RegUtil.findIndex(sql.toLowerCase(), "order\\sby\\s[0-9a-zA-Z_.,]+\\s[desc|asc]");
        if(index==-1){
            throw new RuntimeException("SQL语句非法,无法分页");
        }
        if(index2<index){
            index2=sql.length();
        }
        StringBuilder from=new StringBuilder(sql.substring(index,index2));
        StringBuilder select=new StringBuilder(sql.substring(0,index));
        String orderBy="";
        if(index2!=sql.length()){
            orderBy=sql.substring(index2);
        }


        StringBuilder count=new StringBuilder("select count("+tableAlias+"."+keyColumnName+") num \n");

        //查询sql
        String tSql="";
        //存放记录
        List result=null;
        //总记录数
        Integer total=null;
        //查询
        tSql= count.toString()+ from.toString();
        if(page.getPage()==1){
            //查询记录数
            result=getObjListBySql(tSql,param);
            if(result==null||result.size()==0||((Map)result.get(0)).get("num").toString().equals("0")){
                page.setKey("total",0);
                resultNew.put(ExtConstantUtil.GRID_SUCCESS, false);
                resultNew.put(ExtConstantUtil.GRID_TOTAL, 0);
                resultNew.put(ExtConstantUtil.GRID_ITEMS, null);
                resultNew.put(ExtConstantUtil.GRID_SUCCESS, true);
                return resultNew;
            }else{
                total = (Integer)((Map)result.get(0)).get("num");
                page.setKey("total",total);
            }
        }else{
            total = (Integer)page.getKey("total");
        }
        //查询数据
        tSql=select.toString()+" "+from.toString()+" "+orderBy+" limit :pageIndex,:pageNum";
        param.put("pageIndex", (page.getPage()-1)*page.getLimit());
        param.put("pageNum", page.getLimit());
        result=getObjListBySql(tSql,param);
        resultNew.put(ExtConstantUtil.GRID_TOTAL, total);
        resultNew.put(ExtConstantUtil.GRID_ITEMS, result);
        resultNew.put(ExtConstantUtil.GRID_SUCCESS, true);
        return resultNew;
    }


    /**
     * 执行无返回的存储过程
     * @param procedureName 存储过程名称
     * @param param 存储过程参数
     * @throws Exception
     */
    public void executeProcedure(final String procedureName,final Object[] param)throws RuntimeException{

        hibernateTemplate.execute(new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException {
                //procedure 调用字符串拼接
                Integer paramLength=(param==null)?0:param.length;
                String procedureSql=builderProcedureSql(procedureName,paramLength);
                Connection conn=null;
                try {
                    //				Session session=getHibernateTemplate().getSessionFactory().openSession();
                    conn = SessionFactoryUtils.getDataSource(sessionFactory).getConnection();
                    //创建存储过程的对象
                    CallableStatement c;
                    c = conn.prepareCall(procedureSql);
                    for(Integer i=0;i<param.length;i++){
                        c.setObject(i+1, param[i]);
                    }
                    //执行存储过程
                    c.execute();
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    throw new RuntimeException("存储过程调用出错");
                } finally{
                    try {
                        conn.close();
                    } catch (SQLException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    session.close();
                }
                return null;
            }
        });



    }

    /**
     * 执行无返回的存储过程 (无参数 param 为null)
     * @param procedureName 存储过程名称
     * @param param 存储过程参数
     * @throws Exception
     * 举例调用:
     * Object[] inParam=new Object[]{"20120908"};
     * userDao.executeProcedure("Test", inParam);
     */
    public void executeProcedure(final String procedureName,final List<Object> param)throws RuntimeException{

        hibernateTemplate.execute(new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException
            {
                //procedure 调用字符串拼接
                Integer paramLength=(param==null)?0:param.size();
                String procedureSql=builderProcedureSql(procedureName,paramLength);
                Connection conn=null;
                try {
//				Session session=getHibernateTemplate().getSessionFactory().openSession();
                    conn = SessionFactoryUtils.getDataSource(sessionFactory).getConnection();
                    //创建存储过程的对象
                    CallableStatement c;
                    c = conn.prepareCall(procedureSql);
                    for(Integer i=0;i<param.size();i++){
//						  c.setObject(i+1, param.get(i));
                        if(param.get(i) instanceof Integer){
                            int value = ((Integer) param.get(i)).intValue();
                            c.setInt(i+1, value);
                        }else if(param.get(i) instanceof String){
                            String s = (String) param.get(i);
                            if(UtilTool.isExist(s)){
                                c.setString(i+1, s);
                            }else{
                                c.setString(i+1, null);
                            }
                        }else if(param.get(i) instanceof Double){
                            double d =  ((Double) param.get(i)).doubleValue();
                            c.setDouble(i+1,d);
                        }else if(param.get(i) instanceof Float){
                            float f = ((Float) param.get(i)).floatValue();
                            c.setFloat(i+1, f);
                        }else if(param.get(i) instanceof Long){
                            long l = ((Long) param.get(i)).longValue();
                            c.setLong(i+1, l);
                        }else if(param.get(i) instanceof Boolean){
                            boolean b = ((Boolean) param.get(i)).booleanValue();
                            c.setBoolean(i+1, b);
                        }else if(param.get(i) instanceof Date){
                            Date da= (Date) param.get(i);
                            c.setDate(i+1, da);
                        }

                    }
                    //执行存储过程
                    c.execute();
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    try {
                        conn.close();
                        session.close();
                    } catch (SQLException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();

                    }
                    throw new RuntimeException("存储过程调用出错");
                }finally{
                    //关闭session
                    session.close();
                }
                return null;
            }
        });
//		return flag;

    }

    /**
     * 执行有OUTPUT 参数的存储过程
     * @param procedureName 存储过程名称
     * @param inParam 输入参数 (Object数组）
     * @param outParamKey 输出参数的key(key为任意唯一关键字)
     * @param outParamSqlType 输出参数的SQL类型（java.sql.Types.* 指定）
     * @return 返回Map<String,Object> 其中key 为 outParamKey 数据都在 这个返回Map中
     * @throws Exception
     * 注意：
     * 编写存储过程时 注意 把 输入参数  写在 输出参数之前
     * <pre>
     * CREATE PROCEDURE Test
     *	@vt_workdate	char(8)
     *	,@vt_result char(8) output
     *	,@vt_num numeric(9,0) output
     *	AS
     *	set @vt_result=@vt_workdate
     *	set @vt_num=10
     *	go
     * <pre>
     * 举例调用:
     * Object[] inParam=new Object[]{"20120908"};
     * String[] outParamKey=new String[]{"tow"};
     * Integer[] outParamSqlType=new Integer[]{java.sql.Types.VARBINARY};
     * Map<String,Object> result=userDao.executeProcedure("Test", inParam,outParamKey,outParamSqlType);
     * result.get("tow") 获取返回值
     */
    public Map<String,Object> executeProcedure(final String procedureName,final Object[] inParam,final String[] outParamKey,final Integer[] outParamSqlType)throws RuntimeException{

        Map<String,Object> result=(Map<String,Object>)hibernateTemplate.execute(new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException
            {
                Map<String,Object> result=new HashMap<String, Object>();

                //procedure 调用字符串拼接
                Integer inParamLength=(inParam==null)?0:inParam.length;

                String[] outParamKeyTemp=(outParamKey==null)?new String[]{}:outParamKey;

                String procedureSql=builderProcedureSql(procedureName,inParamLength,outParamKeyTemp.length);
                Connection conn=null;

                try {
                    //				Session session=getHibernateTemplate().getSessionFactory().openSession();
                    conn = SessionFactoryUtils.getDataSource(sessionFactory).getConnection();
                    //创建存储过程的对象
                    CallableStatement c;
                    c = conn.prepareCall(procedureSql);
                    for(Integer i=0;i<inParamLength;i++){
                        c.setObject(i+1, inParam[i]);
                    }

                    for(Integer i=0;i<outParamKeyTemp.length;i++){
                        c.registerOutParameter(inParamLength+i+1, outParamSqlType[i]);
                    }

                    //执行存储过程
                    c.execute();
                    for(Integer i=0;i<outParamKeyTemp.length;i++){
                        result.put(outParamKeyTemp[i], c.getObject(inParamLength+i+1));
                    }
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    throw new RuntimeException("存储过程调用出错");
                }finally{
                    //关闭session
                    session.close();
                }

                return result;
            }
        });
        return result;


    }

    /**
     * 执行有参数的存储过程 获取查询列表
     * @param procedureName 存储过程名称
     * @param inParam 存储过程输入参数(无参数 传递NULL)
     * @param columnName 查询列表的列名
     * @return 返回查询列表数据
     * @throws Exception
     * <pre>
     * 举例调用:
     * Object[] inParam=new Object[]{""};
     * String[] columnName=new String[]{"user_id","login_name","true_name"};
     * List<Map<String,Object>> result=userDao.executeProcedure("Test",null,columnName);
     */
    public List<Map<String,Object>> executeProcedure(final String procedureName,final Object[] inParam,final String[] columnName)throws RuntimeException{

        List<Map<String,Object>> result=(List<Map<String,Object>>)hibernateTemplate.execute(new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException
            {
                List<Map<String,Object>> result=new ArrayList<Map<String,Object>>();

                //procedure 调用字符串拼接
                Integer inParamLength=(inParam==null)?0:inParam.length;

                String procedureSql=builderProcedureSql(procedureName,inParamLength);
                Connection conn=null;

                try {
                    //				Session session=getHibernateTemplate().getSessionFactory().openSession();
                    conn = SessionFactoryUtils.getDataSource(sessionFactory).getConnection();
                    //创建存储过程的对象
                    CallableStatement c;
                    c = conn.prepareCall(procedureSql);
                    for(Integer i=0;i<inParamLength;i++){
                        c.setObject(i+1, inParam[i]);
                    }
                    ResultSet rs=c.executeQuery();
                    while(rs.next()){
                        Map<String,Object> tempMap=new HashMap<String, Object>();
                        for(String key:columnName){
                            tempMap.put(key, rs.getObject(key));
                        }
                        result.add(tempMap);
                    }
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    throw new RuntimeException("存储过程调用出错");
                } finally{
                    //关闭session
                    session.close();
                }
                return result;
            }
        });
        return result;


    }


    private static String builderProcedureSql(String procedureName,Integer length){
        StringBuilder pSql=new StringBuilder("{call "+procedureName+" ");
        for(Integer i=0;i<length;i++){
            if(i==0&&length-1==0){
                pSql.append("(?)");
                continue;
            }
            if(i==0){
                pSql.append("(?");
                continue;
            }
            if(i==length-1){
                pSql.append(",?)");
                continue;
            }
            pSql.append(",?");

        }
        pSql.append("}");

        return pSql.toString();
    }

    private static String builderProcedureSql(String procedureName,Integer inLength,Integer outLength){
        StringBuilder pSql=new StringBuilder("{call "+procedureName+" ");
        for(Integer i=0;i<inLength;i++){
            if(i==0&&inLength-1==0&&outLength==0){
                pSql.append("(:?)");
                continue;
            }
            if(i==0){
                pSql.append("(?");
                continue;
            }
            if(i==inLength-1&&outLength==0){
                pSql.append(",?)");
                continue;
            }
            pSql.append(",?");
        }
        for(Integer i=0;i<outLength;i++){
            if(i==0&&outLength-1==0&&inLength==0){
                pSql.append("(?)");
                continue;
            }
            if(i==0&&inLength==0){
                pSql.append("(?");
                continue;
            }
            if(i==outLength-1){
                pSql.append(",?)");
                continue;
            }
            pSql.append(",?");
        }
        pSql.append("}");

        return pSql.toString();
    }



}
