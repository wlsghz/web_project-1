package com.nonage.action.admin;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.nonage.action.Action;
import com.nonage.dao.ProductDAO;
import com.nonage.dao.impl.ProductDAOImpl;
import com.nonage.dto.ProductVO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class AdminProductUpdateAction implements Action {

   @Override
   public String execute(HttpServletRequest request,
         HttpServletResponse response) throws ServletException, IOException {
      String url="redirect:productList.do";
      HttpSession session=request.getSession();
      
      //파일 업로드 설정
      int sizeLimit=1024*1024*5; //파일크기제한
      String savePath="product_images"; // 저장 폴더 선택
      ServletContext context=session.getServletContext();
      String uploadFilePath = context.getRealPath(savePath);
      
      MultipartRequest multi=new MultipartRequest(
            request,
            uploadFilePath,
            sizeLimit,
            "utf-8",
            new DefaultFileRenamePolicy()
            );
      
      ProductVO product=new ProductVO();
      product.setKind(multi.getParameter("kind"));
      product.setName(multi.getParameter("name"));
      product.setPrice1(Integer.parseInt
            (multi.getParameter("price1")));
      product.setPrice2(Integer.parseInt
            (multi.getParameter("price2")));
      product.setPrice3(Integer.parseInt
            (multi.getParameter("price3")));
      product.setContent(multi.getParameter("content"));
      product.setImage(multi.getFilesystemName("image"));
      
      product.setPseq(Integer.parseInt(multi.getParameter("pseq")));
      product.setUseyn(multi.getParameter("useyn"));
      product.setBestyn(multi.getParameter("bestyn"));
      if(multi.getFilesystemName("image")==null){
         product.setImage(multi.getParameter("nonmakeImg"));
      }else{
         product.setImage(multi.getFilesystemName("image"));
      }
      
      ProductDAO productDao=ProductDAOImpl.getInstance();
      try {
         productDao.updateProduct(product);
      } catch (SQLException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
      
      
      return url;
   }

}