package com.demo.bookstore.book.web.servlet.admin;

import com.demo.bookstore.book.domain.Book;
import com.demo.bookstore.book.service.BookService;
import com.demo.bookstore.category.domain.Category;
import com.demo.bookstore.category.service.CategoryException;
import com.demo.bookstore.category.service.CategoryService;
import cn.itcast.commons.CommonUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@WebServlet("/AdminAddBookServlet")
public class AdminAddBookServlet extends HttpServlet {
    private CategoryService categoryService=new CategoryService();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        /*
        1.把表單數據封裝到 Book 對象中
            上傳3步
         */
        //創建工廠
        //DiskFileItemFactory factory=new DiskFileItemFactory();
        DiskFileItemFactory factory=new DiskFileItemFactory(15*1024,new File("e:/temp"));//設置緩存大小與臨時目錄
        //得到解析器
        ServletFileUpload sfu=new ServletFileUpload(factory);
        //設置單個文件大小為 50KB
        sfu.setFileSizeMax(50*1024);

        //使用解析器去解析request對象,得到List<FileItem>
        try {
            List<FileItem> fileItemList=sfu.parseRequest(request);
            /*
            把fileItemList的數據封裝到Book對象
                把所有的普通表單字段封裝到map中
                在把 map中的數據封裝到 book中
             */
            Map<String,String> map=new HashMap<String,String>();
            for(FileItem fileItem:fileItemList) {
                if(fileItem.isFormField())
                    map.put(fileItem.getFieldName(),fileItem.getString("utf-8"));
            }
            Book book = CommonUtils.toBean(map, Book.class);//book對象目前沒有 image 數據
            Category category = CommonUtils.toBean(map, Category.class);
            book.setCategory(category);


            /*
                2.保存上傳的文件
                    保存的路徑
                    保存文件的名稱
             */
            String savePath = request.getServletContext().getRealPath("/book_img");
            //得到文件名稱:給原來文件名稱添加uuid , 避免文件名稱衝突
            String filename=CommonUtils.uuid()+"_"+fileItemList.get(1).getName();

            /*
                    擴展名校驗
             */
            if(!filename.toLowerCase().endsWith("jpg")){
                request.setAttribute("msg","你上傳的文件不是jpg");
                request.setAttribute("categoryList",categoryService.findAll());
                request.getRequestDispatcher("/adminjsps/admin/book/add.jsp").forward(request,response);
                return;
            }

            //使用目錄和文件名稱創建目標文件
            File destFile=new File(savePath,filename);
            //保存上傳文件到目標文件位置
            fileItemList.get(1).write(destFile);

             /*
                   校驗圖片尺寸
             */
            Image img=new ImageIcon(destFile.getAbsolutePath()).getImage();
            if(img.getWidth(null)>400 || img.getHeight(null)>400){
                destFile.delete();//刪除圖片
                request.setAttribute("msg","你上傳的圖片尺寸 寬或高大於400pixel");
                request.setAttribute("categoryList",categoryService.findAll());
                request.getRequestDispatcher("/adminjsps/admin/book/add.jsp").forward(request,response);
                return;
            }


            /*
                3.設置 Book 對象 image, 即把圖片的路徑設置給 Book 的 image
             */
            book.setImage("book_img/"+filename);
            book.setBid(CommonUtils.uuid());

            BookService bookService=new BookService();
            bookService.add(book);



            request.getRequestDispatcher("/admin/AdminBookServlet?method=findAll").forward(request,response);

        } catch (Exception e) {
            //e.printStackTrace();
            if(e instanceof FileUploadBase.FileSizeLimitExceededException){
                request.setAttribute("msg","你上傳的文件大小超過15KB");
                try {
                    request.setAttribute("categoryList",categoryService.findAll());
                } catch (CategoryException ex) {
                    ex.printStackTrace();
                }
                request.getRequestDispatcher("/adminjsps/admin/book/add.jsp").forward(request,response);
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
