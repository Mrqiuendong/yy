package com.feicuiedu.dao;

import com.feicuiedu.bean.Admin;
import com.feicuiedu.bean.Reader;
import com.feicuiedu.exception.DaoException;

public interface ReaderDao {
	//根据用户名查找用户
	public Reader findReaderByName(String name);
	//根据用户名查找管理员
	public Admin findAdminByName(String name);
	//添加用户
	public void addReader(Reader reader);
	//更新用户
	public void updateReader(Reader sessionReader) throws DaoException;
	//根据id查找用户
	public Reader findReaderById(String id) throws DaoException;
	//更新密码
	public void updedaReaderPassword(Reader reader) throws DaoException;
	//更新余额
	public void updateBalance(Reader reader) throws DaoException;
}
