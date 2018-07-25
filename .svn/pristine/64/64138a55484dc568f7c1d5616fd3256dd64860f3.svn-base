package com.hx.hxfeima.base.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;


import com.hx.hxfeima.core.bean.PageBean;
import org.hibernate.Session;



public abstract interface BaseDao<T>
{
    public abstract Session getSession();

    public abstract Serializable save(Object paramObject);

    public abstract void delete(Object paramObject);

    public abstract int delete(String paramString);

    public abstract int delete(String paramString, Object[] paramArrayOfObject);

    public abstract int delete(String paramString, List<Object> paramList);

    public abstract int delete(String paramString, Map<String, Object> paramMap);

    public abstract void update(Object paramObject);

    public abstract int update(String paramString);

    public abstract int update(String paramString, Object[] paramArrayOfObject);

    public abstract int update(String paramString, List<Object> paramList);

    public abstract int update(String paramString, Map<String, Object> paramMap);

    public abstract void saveOrUpdate(Object paramObject);

    public abstract Object first(String paramString);

    public abstract Object first(String paramString, Object[] paramArrayOfObject);

    public abstract Object first(String paramString, List<Object> paramList);

    public abstract Object first(String paramString, Map<String, Object> paramMap);

    public abstract <T> T get(Class<T> paramClass, Serializable paramSerializable);

    public abstract Object get(String paramString, Object[] paramArrayOfObject);

    public abstract Object get(String paramString, List<Object> paramList);

    public abstract Object get(String paramString, Map<String, Object> paramMap);

    public abstract List find(String paramString);

    public abstract List find(String paramString, Object... paramArrayOfObject);

    public abstract List findTo(String paramString, Object... paramArrayOfObject);

    public abstract List find(String paramString, List<Object> paramList);

    public abstract List find(String paramString, Map<String, Object> paramMap);

    public abstract List find(String paramString, Integer paramInteger1, Integer paramInteger2);

    public abstract List find(String paramString, Object[] paramArrayOfObject, Integer paramInteger1, Integer paramInteger2);

    public abstract List find(String paramString, List<Object> paramList, Integer paramInteger1, Integer paramInteger2);

    public abstract List find(String paramString, Map<String, Object> paramMap, Integer paramInteger1, Integer paramInteger2);

    public abstract long count(String paramString);

    public abstract long count(String paramString, Object[] paramArrayOfObject);

    public abstract long count(String paramString, List<Object> paramList);

    public abstract long count(String paramString, Map<String, Object> paramMap);

    public abstract List<Object> executeProcedure(String paramString, Class[] paramArrayOfClass1, Object[] paramArrayOfObject, Class[] paramArrayOfClass2);


    /**
     * 通过主键ID,获取对应的对象
     */
    public T getNew(Class clazz, Serializable id)throws RuntimeException;

    /**
     * 获取所有对应对象
     * @return
     */
    public List<T> getAll(Class clazz)throws RuntimeException;

    /**
     * 通过SQL 查询 列表 类型为Map
     * @param sql SQL语句
     * @param param 查询参数值
     * @return
     */
    public List<Map> getObjListBySql(String sql, Map<String, Object> param)throws RuntimeException;

    /**
     * 通过SQL 查询 列表类型为Map
     * @param sql SQL语句
     * @param param 查询参数值
     * @return
     * @throws Exception
     */
    public List<Map> getObjListBySql(String sql, Object... param)throws RuntimeException;

    /**
     * 通过SQL 查询 列表类型为Map
     * @param sql SQL语句 参数通过拼接闯入(SQL注入攻击不推荐使用,查看验证语句方便)
     * @return
     * @throws Exception
     */
    public List<Map> getObjListBySql(String sql)throws RuntimeException;

    /**
     * 通过SQL 查询 传入 页数及长度
     * @param sql SQL语句  起始页  页面长度 (SQL注入攻击不推荐使用,查看验证语句方便)
     * @return
     * @throws Exception
     */
    public List<Map> getObjListBySql(String sql, Integer page, Integer rows)throws RuntimeException;
    /**
     * 通过SQL 查询 获得数量
     * @param sql
     * @param param
     * @return
     * @throws Exception
     */
    public Integer getObjCountBySql(String sql, Object... param)throws RuntimeException;

    /**
     * 通过SQL 查询 获得数量
     * @param sql
     * @param param
     * @return
     * @throws Exception
     */
    public Integer getObjCountBySql(String sql, Map<String, Object> param)throws RuntimeException;

    /**
     * 使用原生SQL查询数量
     * @param sql SQL语句 参数通过拼接闯入(SQL注入攻击不推荐使用,查看验证语句方便)
     * @return
     */
    public Integer getObjCountBySql(String sql)throws RuntimeException;

    /**
     * 通过SQL 查询列表 分页
     * @param sql  Sql 语句
     * @param keyColumnName  表主键
     * @param param 查询参数
     * @param page 分页信息
     * @return Map<String,Object> 其中含有3个关键Key:
     *	ExtConstantUtil.GRID_TOTAL：查询总条数
     *	ExtConstantUtil.GRID_ITEMS：查询记录
     *	ExtConstantUtil.GRID_SUCCESS：查询是否含有记录
     * @throws Exception
     */
    public Map<String,Object> getObjListPageBySql(String sql, String keyColumnName, String tableAlias, Map<String, Object> param, PageBean page)throws RuntimeException;

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
    public Map<String,Object> getObjListPageBySql(String sql, String keyColumnName, String tableAlias, boolean isAsc, Map<String, Object> param, PageBean page)throws RuntimeException;

    /**
     * 通过SQL 查询列表 分页
     * @param Sql  Sql 语句
     * @param keyColumnName  表主键
     * @param param 查询参数
     * @param orderBy 排序语句  值为 "columnName1 asc,columnName2 desc"
     * @param page 分页信息
     * @return Map<String,Object> 其中含有3个关键Key:
     *	ExtConstantUtil.GRID_TOTAL：查询总条数
     *	ExtConstantUtil.GRID_ITEMS：查询记录
     *	ExtConstantUtil.GRID_SUCCESS：查询是否含有记录
     * @throws Exception
     */
    //public Map<String,Object> getObjListPageBySql(String sql,String keyColumnName,String tableAlias,String orderBy,Map<String,Object> param,PageBean page)throws RuntimeException;

    /**
     * 通过HQL 查询列表
     * @param hql HQL 语句
     * @param param Map参数
     * @return
     * @throws Exception
     */
    public List<T> getListByHql(final String hql, final Map<String, Object> param)throws RuntimeException;

    /**
     * 通过HQL 查询列表
     * @param hql
     * @param param Object...类型
     * @return
     * @throws Exception
     */
    public List<T> getListByHql(final String hql, final Object... param)throws RuntimeException;

    /**
     * 通过HQL 查询单个对象
     * @param hql
     * @param param
     * @return
     * @throws Exception
     */
    public T getObjByHql(String hql, Object... param)throws RuntimeException;

    /**
     * 通过HQL 查询单个对象
     * @param hql
     * @param param
     * @return
     * @throws Exception
     */
    public T getObjByHql(String hql, Map<String, Object> param)throws RuntimeException;

    /**
     * 保存对象
     * @param o
     */
    void saveNew(Object o)throws RuntimeException;

    /**
     * 删除对象
     * @param o
     */
    public void deleteNew(Object o)throws RuntimeException;

    /**
     * 更新对象
     * @param o
     */
    public void updateNew(Object o)throws RuntimeException;

    /**
     * 执行单条SQL 语句
     * @param sql sql语句
     * @param param 参数列表
     * @return 影响条数
     * @throws Exception
     */
    public Integer executeSql(String sql, Map<String, Object> param)throws RuntimeException;

    /**
     * 执行单条SQL 语句
     * @param sql sql语句
     * @param param 参数列表
     * @return 影响条数
     * @throws Exception
     */
    public Integer executeSql(String sql, Object... param)throws RuntimeException;

    /**
     * 执行单条SQL 语句
     * @param sql SQL语句 参数通过拼接闯入(SQL注入攻击不推荐使用,查看验证语句方便)
     * @return
     * @throws Exception
     */
    public Integer executeSql(String sql)throws RuntimeException;


    /**
     * 执行批量SQL 语句操作
     * @param sql SQL语句
     * @param collection List列表 里面以Object数组对形式保存数据
     * @return 返回影响条数
     * @throws Exception
     * <pre>
     * 举例调用:
     * String userid = UtilTool.valueOf(searchMap.get("userid"));
     * String opertype = UtilTool.valueOf(searchMap.get("opertype"));
     * String state = UtilTool.valueOf(searchMap.get("state"));
     * String statedate = UtilTool.valueOf(searchMap.get("statedate"));
     * StringBuilder sql=new StringBuilder("insert into sys_use_log (user_id,oper_type,state,statedate) values(?,?,?,?)");
     *	Object[] log={
     *			Long.valueOf(userid),opertype,state,statedate
     *	};
     *	List<Object[]> collection=new ArrayList<Object[]>();
     *	collection.add(log);
     *	Integer falg=userDao.executeBatchSql(sql.toString(),collection);
     */
    public Integer executeBatchSql(String sql, List<Object[]> collection)throws RuntimeException;

    /**
     * 执行无返回的存储过程 (无参数 param 为null)
     * @param procedureName 存储过程名称
     * @param param 存储过程参数
     * @throws Exception
     * 举例调用:
     * Object[] inParam=new Object[]{"20120908"};
     * userDao.executeProcedure("Test", inParam);
     */
    public void executeProcedure(String procedureName, Object[] param)throws RuntimeException;

    /**
     * 执行无返回的存储过程 (无参数 param 为null)
     * @param procedureName 存储过程名称
     * @param param 存储过程参数
     * @throws Exception
     * 举例调用:
     * Object[] inParam=new Object[]{"20120908"};
     * userDao.executeProcedure("Test", inParam);
     */
    public void executeProcedure(String procedureName, List<Object> param)throws RuntimeException;


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
    public Map<String,Object> executeProcedure(String procedureName, Object[] inParam, String[] outParamKey, Integer[] outParamSqlType)throws RuntimeException;

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
    public List<Map<String,Object>> executeProcedure(String procedureName, Object[] inParam, String[] columnName)throws RuntimeException;

}