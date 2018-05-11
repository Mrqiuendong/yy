package com.feicuiedu.dao;


import com.feicuiedu.bean.Admin;
import com.feicuiedu.bean.Reader;
import com.feicuiedu.exception.DaoException;
import com.feicuiedu.util.JDBCUtils;

public class ReaderDaoImpl implements ReaderDao{
	
	@Override
	public Reader findReaderByName(String name) {
		String sql = "select * from reader where `name`=?";
		return null;
	}

	@Override
	public Admin findAdminByName(String name) {
		return null;
	}

	@Override
	public void addReader(Reader reader) {
		
		String sql = "insert into reader values(?,?,?,?,?,?,?,?);";
		JDBCUtils.update(sql, reader.getId(),reader.getName(),reader.getPassword(),reader.getSex(),reader.getBalance(),reader.getTel(),reader.getEmail(),reader.getCreateDate());
	}

	@Override
	public void updateReader(Reader reader) throws DaoException {
		
		String sql = "UPDATE   `library`.`reader`SET   `name` = ?,   `password` = ?,   `sex` = ?,  `balance` = ?,`tel` = ?, `email` = ?,  `createDate` = ?  WHERE `id` = ? ;";
		JDBCUtils.update(sql, reader.getName(),reader.getPassword(),reader.getSex(),reader.getBalance(),reader.getTel(),reader.getEmail(),reader.getCreateDate(),reader.getId());
	}

	@Override
	public Reader findReaderById(String id) throws DaoException {
		
		String sql = "select * from reader where id=?";
		
		return JDBCUtils.select(Reader.class, sql, id).get(0);
	}

	@Override
	public void updedaReaderPassword(Reader reader) throws DaoException {
		
		String sql = "update reader set  `password` = ? where id = ?";
		JDBCUtils.update(sql,reader.getPassword(),reader.getId());
		
	}

	@Override
	public void updateBalance(Reader reader) throws DaoException {
		String sql = "update reader set  `balance` = ? where id = ?";
		JDBCUtils.update(sql,reader.getBalance(),reader.getId());
	}
	
	
	
}
