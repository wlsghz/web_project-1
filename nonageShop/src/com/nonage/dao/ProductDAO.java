package com.nonage.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.nonage.dto.ProductVO;

public interface ProductDAO {

	// 신상품 리스트 얻어오기..
	public List<ProductVO> listNewProduct() throws SQLException;

	// 베스트 상품 리스트 얻어오기
	public List<ProductVO> listBestProduct() throws SQLException;
	
	// 상품번호로 상품정보 한개 가져오기
	public ProductVO getProduct(int pseq) throws SQLException;
	
	// 상품종류별 상품 리스트 얻어오기
	public ArrayList<ProductVO> listKindProduct(String kind) 
				throws SQLException;
	
	// 상품이름별 개수 가져오기
	public int totalRecord(int tpage, String kind, String product_name) 
							throws SQLException;
	
	// 상품이름 검색한 상품리스트 페이지번호...
	public ArrayList<ProductVO> listProduct(int tpage,String kind,String product_name)
										throws SQLException;
	
	// 페이지 이동을 위한 메소드
	public String pageNumber(int tpage,String kind,String product_name)throws SQLException;
	public String adminPageNumber(int tpage,String kind,String product_name)throws SQLException;
	
	

	// 상품 등록
	public int insertProduct(ProductVO product) throws SQLException;
	
	// 상품 수정.
	public int updateProduct(ProductVO product) throws SQLException;
}





















