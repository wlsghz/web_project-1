package com.nonage.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.nonage.dao.ProductDAO;
import com.nonage.db.sqlConfig.IBatisDBConnector;
import com.nonage.dto.ProductVO;

public class ProductDAOImpl implements ProductDAO{
	private static ProductDAOImpl instance=new ProductDAOImpl();
	private ProductDAOImpl(){};
	public static ProductDAOImpl getInstance(){
		return instance;
	}
	
	private SqlMapClient client=IBatisDBConnector.getSqlMapClient();
	
	@Override
	public List<ProductVO> listNewProduct() throws SQLException {
		List<ProductVO> listNewProduct=null;
		listNewProduct=(List<ProductVO>)client
				.queryForList("Product.listNewProduct", null);
		return listNewProduct;
	}
	@Override
	public List<ProductVO> listBestProduct() throws SQLException {
		List<ProductVO> listBestProduct=null;
		listBestProduct=(List<ProductVO>)client
				.queryForList("Product.listBestProduct", null);
		return listBestProduct;
	}
	
	@Override
	public ProductVO getProduct(int pseq) throws SQLException {
		ProductVO product=new ProductVO();
		product=(ProductVO)client.queryForObject("Product.getProduct", pseq);
		return product;
	}
	
	@Override
	public ArrayList<ProductVO> listKindProduct(String kind)
			throws SQLException {
		ArrayList<ProductVO> listKindProduct=
				(ArrayList<ProductVO>)client.queryForList("Product.listKindProduct",
						kind);
		return listKindProduct;
	}
	
	public static int view_rows=10;//페이지개수
	public static int counts=10;//한 페이지에 나타낼 상품 개수
	public static int totalRecord=0; // 전체 상품 개수.
	
	@Override
	public ArrayList<ProductVO> listProduct(int tpage, String kind, String product_name)
			throws SQLException {
		ArrayList<ProductVO> productList=null;
		
		totalRecord=totalRecord(tpage, kind, product_name);
		int	skipRows=(tpage-1)*counts;
		int maxRows=counts;
		
		Map<String,String> paramMap=new HashMap<String,String>();
		paramMap.put("name", product_name);
		paramMap.put("kind", kind);
		
		productList=(ArrayList<ProductVO>)client
				.queryForList("Product.listProduct",paramMap,skipRows,maxRows);
		
		return productList;
	}
	@Override
	public int totalRecord(int tpage, String kind, String product_name) throws SQLException {	
		
		int	skipRows=(tpage-1)*counts;
		int maxRows=counts;
		
		Map<String,String> paramMap=new HashMap<String,String>();
		paramMap.put("name", product_name);
		paramMap.put("kind", kind);
		
		int totalRecord=
				(Integer)client.queryForObject("Product.totalRecord",paramMap);
		return totalRecord;
	}
	@Override
	public String pageNumber(int tpage,String kind,String product_name) throws SQLException {
		String str=paging("category.do",tpage,kind,product_name);
		return str;
	}
	
	@Override
	public String adminPageNumber(int tpage,String kind,String product_name) throws SQLException {
		String str=paging("productList.do",tpage,kind,product_name);
		return str;
	}
	
	private String paging(String url,int tpage,String kind,String product_name){
		int page_count = 0;
		if (totalRecord % counts>0){
			page_count=totalRecord/counts+1;
		}else{
			page_count=totalRecord/counts;
		}
		
		if(tpage<1){
			tpage=1;
		}		
		int start_page=tpage-(tpage%view_rows)+1;
		int end_page=(start_page+view_rows)-1;
		
		if(end_page>page_count){
			end_page=page_count;
		}
		String str="";
		if(start_page>view_rows){
			str+="<a href='"+url+"?tpage=1&key="
					+product_name+"&kind="+kind+"'>&lt;&lt;</a>&nbsp;&nbsp;";
			str+="<a href='"+url+"?tpage="
					+(start_page-view_rows);
			str+="&key="+product_name+"&kind="+kind+"'>&lt;</a>&nbsp;&nbsp;";
		}
		for (int i=start_page;i<end_page+1;i++){
			if(i==tpage){
				str +="<font color=red>["+i+"]&nbsp;&nbsp;</font>";
			}else{
				str+="<a href='"+url+"?tpage="
						+i+"&key="+product_name+"&kind="+kind+"'>["+i+"]</a>&nbsp;&nbsp;";
			}
		}
		if(page_count>end_page){
			str +="<a href='"+url+"?tpage="
					+(start_page+view_rows)+"&key="+product_name
					+"&kind="+kind+"'>&gt;</a>&nbsp;&nbsp;";
			str +="<a href='"+url+"?tpage="
					+page_count+"&key="+product_name
					+"&kind="+kind+"'>&gt;&gt;</a>&nbsp;&nbsp;";
		}
		
		return str;
	}
	@Override
	public int insertProduct(ProductVO product) throws SQLException {
		int result=(Integer)client.update("Product.insertProduct", product);
		return result;
	}
	@Override
	public int updateProduct(ProductVO product) throws SQLException {
		int result=(Integer)client.update("Product.updateProduct", product);
		return result;
	} 
}







