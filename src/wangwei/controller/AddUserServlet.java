package wangwei.controller;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import wangwei.model.User;
import wangwei.service.UserService;
import wangwei.service.impl.UserServiceImpl;
import wangwei.util.JDBCUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

@WebServlet("/admin/addUser")
public class AddUserServlet extends HttpServlet {
    UserService userService=new UserServiceImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        User user=getUser(request);
        //获取用户信息失败失败
        if (user == null){
            request.setAttribute("error",1);
            request.getRequestDispatcher(request.getContextPath()+"/add_user.jsp").forward(request,response);
        }
        /**
         * 将用户信息写入数据库
         */
        System.out.println(user);
        userService.addUser(user);
        response.sendRedirect("userList");
    }

    /**
     * 将表单数据封装成User对象
     * @param request
     * @return
     * @throws IOException
     */
    public static User getUser(HttpServletRequest request ) throws IOException {
        User user;
        return user;
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

}

