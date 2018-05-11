package com.feicuiedu.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.feicuiedu.bean.Book;

public class DButils {

	private static QueryRunner qr = new QueryRunner(DBCPPool.getDataSource());
	
	
	public static void main(String[] args) throws SQLException  {
		String sql = "select * from book where id =?";
		Book i  = qr.query( sql,new BeanHandler<>(Book.class),"1");
		List<Book> list  = qr.query( sql,new BeanListHandler<>(Book.class),"1");
		System.out.println(i);
		
	}
	
	
	
}
