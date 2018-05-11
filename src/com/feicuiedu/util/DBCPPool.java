package com.feicuiedu.util;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSourceFactory;
/**
 * dataSource: 要连接的 datasource (通常我们不会定义在 server.xml)
 * defaultAutoCommit: 对于事务是否 autoCommit, 默认值为 true
 * defaultReadOnly: 对于数据库是否只能读取, 默认值为 false
 * driverClassName:连接数据库所用的 JDBC Driver Class,
 * maxActive: 可以从对象池中取出的对象最大个数，为0则表示没有限制，默认为8
 * maxIdle: 最大等待连接中的数量,设 0 为没有限制 （对象池中对象最大个数）
 * minIdle：对象池中对象最小个数
 * maxWait: 最大等待秒数, 单位为 ms, 超过时间会丟出错误信息
 * password: 登陆数据库所用的密码
 * url: 连接数据库的 URL
 * username: 登陆数据库所用的帐号
 * validationQuery: 验证连接是否成功, SQL SELECT 指令至少要返回一行
 * removeAbandoned: 是否自我中断, 默认是 false
 * removeAbandonedTimeout: 几秒后会自我中断, removeAbandoned 必须为 true
 * logAbandoned: 是否记录中断事件, 默认为 false
 * minEvictableIdleTimeMillis：大于0 ，进行连接空闲时间判断，或为0，对空闲的连接不进行验证；默认30分钟
 * timeBetweenEvictionRunsMillis：失效检查线程运行时间间隔，如果小于等于0，不会启动检查线程，默认-1
 * testOnBorrow：取得对象时是否进行验证，检查对象是否有效，默认为false
 * testOnReturn：返回对象时是否进行验证，检查对象是否有效，默认为false
 * testWhileIdle：空闲时是否进行验证，检查对象是否有效，默认为false
 * initialSize：初始化线程数
 * @author suntao
 *
 */
public class DBCPPool {  
	
	
    //首先定义私有的datasource  
    private static DataSource datasource; 
    
    static{  
        try {  
            //实例化properties集合  
            Properties prop=new Properties();     
            prop.load(new FileInputStream("resource/dbcp.properties"));        
            //首先加载核心类      
            datasource=BasicDataSourceFactory.createDataSource(prop);  
        } catch (Exception e) { 
            e.printStackTrace();  
        }         
          
    }  
    //提供获得数据源    
    public static DataSource getDataSource(){  
        return datasource;  
    }  
    //提供获得连接       
    public static Connection getConnection() throws SQLException{  
        return datasource.getConnection();  
    }  
} 
