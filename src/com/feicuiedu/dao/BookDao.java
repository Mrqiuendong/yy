package com.feicuiedu.dao;


import java.util.List;

import com.feicuiedu.bean.Book;
import com.feicuiedu.exception.DaoException;


public interface BookDao {
	
	int addBook(Book book) throws DaoException;
	int updateBook(Book book)throws DaoException;
	List<Book> findBookAll()throws DaoException;
	List<Book> findBookAll(String type)throws DaoException;
	Book findBook(String id)throws DaoException;
	List<Book> findHotBook()throws DaoException;
	List<Book> findBookAllByKey(String key)throws DaoException;
	int deleteBook(String bookId) throws DaoException;
	boolean canDelete(String id) throws DaoException;
	
}
