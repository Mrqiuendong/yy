package com.feicuiedu.dao;

import java.util.List;

import com.feicuiedu.bean.Book;
import com.feicuiedu.exception.DaoException;
import com.feicuiedu.util.JDBCUtils;

public class BookDaoImpl implements BookDao {

	@Override
	public int addBook(Book book) throws DaoException {
		return 0;
	}

	@Override
	public int updateBook(Book book) throws DaoException {
		return 0;
	}
	
	public static void main(String[] args) throws DaoException {
		List<Book> list = new BookDaoImpl().findBookAll();
		for (Book book : list) {
			System.out.println(book);
		}
	}
	@Override
	public List<Book> findBookAll() throws DaoException {
		
		String sql = "select * from book;";
		
		List<Book> list =  JDBCUtils.select(Book.class, sql);
		return list;
	}

	@Override
	public List<Book> findBookAll(String type) throws DaoException {
		return null;
	}

	@Override
	public Book findBook(String id) throws DaoException {
		
		
		return null;
	}

	@Override
	public List<Book> findHotBook() throws DaoException {
		return null;
	}

	@Override
	public List<Book> findBookAllByKey(String key) throws DaoException {
		return null;
	}

	@Override
	public int deleteBook(String bookId) throws DaoException {
		return 0;
	}

	@Override
	public boolean canDelete(String id) throws DaoException {
		return false;
	}

}
