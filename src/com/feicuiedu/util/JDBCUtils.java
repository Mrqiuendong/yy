package com.feicuiedu.util;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;


public class JDBCUtils {
	
	private static String url;
	private static String user;
	private static String pwd;
	private static Connection conn = null;
	private static PreparedStatement pd = null;
	private static ResultSet rs = null;
	static{
		try {
			Properties pro = new Properties();
			pro.load(new FileInputStream("resource/db.properties"));
			url = pro.getProperty("url");
			user = pro.getProperty("user");
			pwd = pro.getProperty("pwd");
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public static Connection getConnection()
	{
		
		try {
			conn = DriverManager.getConnection(url,user,pwd);
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
		return conn;
	}
	
		public static void main(String[] args) {
			List<Object[]> list = new ArrayList<>();
			String sql1 = "INSERT INTO `library`.`admin` (`id`, `name`, `password`) VALUES   (?, ?, ?) ;";
			list.add(new Object[] {"1231","liudehua","159"});
			String sql2 = "INSERT INTO `library`.`admin` (`id`, `name`, `password`) VALUES   (?, ?, ?) ;";
			list.add(new Object[] {"123451","liudehua1","1591"});
			String sql3 = "update `category` set `name`='近代文学' where id='11'";
			list.add(new Object[] {});
			
			boolean boo = transactionUpdate(new String[] {sql1,sql2,sql3},list);
			System.out.println(boo);
		}
	 public static boolean transactionUpdate(String[] sqls, List<Object[]> args)
	 {
		 getConnection();
		 try {
			 //取消自动提交
			conn.setAutoCommit(false);
			
			for (int i = 0; i < sqls.length; i++) {
				
				pd = conn.prepareStatement(sqls[i]);
				Object[] obj = args.get(i);
				for (int j = 0; j < obj.length; j++) {
					pd.setObject(j+1, obj[j]);
				}
				pd.executeUpdate();
				pd.close();
			}
			conn.commit();
			
			
		} catch (Exception e) {
			try {
				conn.rollback();
				return false;
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}finally
		 {
			try {
				conn.setAutoCommit(true);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			close();
		 }
		 return true;
	 }
	
	
	
	//执行查询语句 并且返回结果集
	public static <T> List<T> select(Class<T> clazz,String sql,Object... obj)
	{
		getConnection();
		List<T> list = new ArrayList<>();
		try {
			pd = conn.prepareStatement(sql);
			for (int i = 0; i < obj.length; i++) {
				pd.setObject(i+1, obj[i]);
			}
			rs = pd.executeQuery();
			//得到结果集属性信息
			ResultSetMetaData  rsmd = rs.getMetaData();
			while(rs.next())
			{
				T entity = clazz.newInstance();//反射创建对象   
				
				for(int i=0;i<rsmd.getColumnCount();i++)//遍历所有的属性
				{
					String colName = rsmd.getColumnLabel(i+1);//获得当前数据中的属性名称
					Object colValue = rs.getObject(i+1);//获得属性对应的值
					Field field = clazz.getDeclaredField(colName);//得到该属性名称所对应的对象属性对象
					//----1-------打开访问权限--------------------------------------
					
//					field.setAccessible(true);//打开访问权限
//					field.set(entity, colValue);//设置指定对象的该属性的值
					
					//----2------set方法名拼接--------------------------------------
//					//拼接set方法名称
//					String ae = "set"+colName.substring(0, 1).toUpperCase()+colName.substring(1);
//					//根据方法名称得到方法对象
//					Method method = clazz.getDeclaredMethod(ae,field.getType());
//					//在指定对象上执行该方法
//					method.invoke(entity, colValue);
					//----3-----内省----------------------------------------------
					//反射：计算机程序运行过程中 在运行时可以访问、检测、修改本身状态或行为的一种能力
					//内省：在计算机编程中，内省是指计算机程序在运行中检查对象类型的一种能力，通常也可以称作运行时类型检查
					BeanInfo beanInfo = Introspector.getBeanInfo(clazz);//获得类型描述器
					PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();//获得类型中所有的属性的属性描述器
					for (PropertyDescriptor pr : pds)
					{
						if(pr.getName().equals(colName))//判断属性描述器是否是当前属性的描述器
						{
							Method me = pr.getWriteMethod();//获得属性对应的setter方法
							me.invoke(entity, colValue);//执行setter方法
						}
					}
					
				}
				list.add(entity);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}  catch (SecurityException e) {
			e.printStackTrace();
		}  catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		}  catch (IntrospectionException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}finally
		{
			close();
		}
		return list;
	}
	
	//执行更新的sql语句
	public static int update(String sql,Object...obj)
	{
		getConnection();
		int j = 0;
		try {
			pd = conn.prepareStatement(sql);
			for (int i = 0; i < obj.length; i++) {
				pd.setObject(i+1, obj[i]);
			}
			j = pd.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally
		{
			try {
				conn.close();
				pd.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return j;
		
	}
	
	
	
	//关闭资源
	public static void close()
	{
			
			if(conn!=null)
			{
				try {
					conn.close();
				} catch (SQLException e) {
					conn=null;
					e.printStackTrace();
				}
			}
			if(rs!=null)
			{
				try {
					rs.close();
				} catch (SQLException e) {
					rs = null;
					e.printStackTrace();
				}
			}
			if(pd!=null)
			{
				try {
					pd.close();
				} catch (SQLException e) {
					pd = null;
					e.printStackTrace();
				}
			}
	}
}
