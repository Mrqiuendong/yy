package com.feicuiedu.bean;

public class Book {
	private String id;
	private String name;
	private String author;
	private long stock;
	private String categoryid;
	private int borrowcount;
	private double bookprice;
	private double borrowprice;

	public Book(String id, String name, String author, int stock,
			String categoryid, int borrowcount, double bookprice,
			double borrowprice) {
		super();
		this.id = id;
		this.name = name;
		this.author = author;
		this.stock = stock;
		this.categoryid = categoryid;
		this.borrowcount = borrowcount;
		this.bookprice = bookprice;
		this.borrowprice = borrowprice;
	}
	
	public Book() {
		super();
	}

	public int getBorrowcount() {
		return borrowcount;
	}

	public void setBorrowcount(int borrowcount) {
		this.borrowcount = borrowcount;
	}

	public double getBorrowprice() {
		return borrowprice;
	}

	public void setBorrowprice(double borrowprice) {
		this.borrowprice = borrowprice;
	}

	public double getBookprice() {
		return bookprice;
	}

	public void setBookprice(double bookprice) {
		this.bookprice = bookprice;
	}

	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public long getStock() {
		return stock;
	}

	public void setStock(long stock) {
		this.stock = stock;
	}

	public String getCategoryid() {
		return categoryid;
	}

	public void setCategoryid(String categoryid) {
		this.categoryid = categoryid;
	}
	
	@Override
	public String toString() {
		return "Book [id=" + id + ", name=" + name + ", author=" + author
				+ ", stock=" + stock + ", categoryid=" + categoryid
				+ ", borrowcount=" + borrowcount + ", bookprice=" + bookprice
				+ ", borrowprice=" + borrowprice + "]";
	}

}
