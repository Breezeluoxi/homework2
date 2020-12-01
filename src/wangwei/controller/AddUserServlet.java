package cdu.pjm.controller;

import cdu.pjm.model.User;
import cdu.pjm.service.UserService;
import cdu.pjm.service.impl.UserServiceImpl;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

@WebServlet(name="AddUserServlet",urlPatterns = "/AddUserServlet")
public class AddUserServlet extends HttpServlet {
    UserService userService=new UserServiceImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("utf-8");
        request.setCharacterEncoding("utf-8");
        User user=getUser(request);
//        if (user == null){
//            request.setAttribute("error",1);
//            request.getRequestDispatcher(request.getContextPath()+"/add_user.jsp").forward(request,response);
//        }
//        System.out.println(user);
//        userService.addUser(user);
//        response.sendRedirect("userList");
    }
    static User getUser(HttpServletRequest request){
        boolean isMult = ServletFileUpload.isMultipartContent(request);
        long userId=System.currentTimeMillis();
        String userName=request.getParameter("txtID");
        String userPwd=request.getParameter("txtPW");
        String userGender=request.getParameter("rad");
        String userAge= request.getParameter("txtH");
        String userLike= request.getParameter("txtLOVE");
        String userPicUrl= "";
        if(isMult){//是否为文件
            FileItemFactory factory=new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);
//            items接受所有
            try {
                List<FileItem> items = upload.parseRequest(request);
                //迭代器遍历item
                Iterator<FileItem> iter=items.iterator();
                while(iter.hasNext()){
                    FileItem item=iter.next();
                    String itemName=item.getFieldName();
                    if(item.isFormField()){//not picture
                        switch(itemName){
                            case "txtName":
                                userName=item.getString("UTF-8");
                                System.out.println(userName);
                                break;
                            case "txtPW":
                                userPwd=item.getString("UTF-8");
                                System.out.println(userPwd);
                                break;
                            case "gender":
                                userGender=item.getString("UTF-8");
                                System.out.println(userGender);
                                break;
                            case "selH":
                                userAge=item.getString("UTF-8");
                                System.out.println(userAge);
                                break;
                            case "txtLOVE":
                                userLike=item.getString("UTF-8");
                                System.out.println(userLike);
                                break;
                            default://??
                                break;
                        }
                    }else{//img pictuer ->file
                        //storge path
                        String path=request.getSession().getServletContext().getRealPath("");
                        //gain fileName
                        String fileName=item.getName();
                        //gain fileContent&upload
                        File file = new File(path+"web/WEB-INF/photo",userId+fileName.substring(fileName.lastIndexOf(".") ));
                        /*===========================================*/
                        /*===========================================*/
                        //write imgFile
//                        item.write(file);
                        userPicUrl+=userId+fileName.substring(fileName.lastIndexOf("."));
                        System.out.println(userId);
                        System.out.println(userPicUrl);
                    }
                }
            } catch (FileUploadException e) {
                //file upload error
                e.printStackTrace();
                System.out.println("file upload error");
            } catch (Exception e) {
                //file write Error
                e.printStackTrace();
                System.out.println("file write Error");
            }
        }
        User user = new User(userId, userName, userPwd, userGender, userAge, userLike,userPicUrl);
        return user;
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
