package wangwei.controller;

import wangwei.service.UserService;
import wangwei.service.impl.UserServiceImpl;
import wangwei.util.ParameterCheck;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/delUser")
public class DelUserServlet extends HttpServlet {
    UserService userService=new UserServiceImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int userId = ParameterCheck.id("userId", request);
        if (userId<0)return;
        int isOK = userService.deleteUser(userId);
        if (isOK>0){
            request.getSession().setAttribute("deleteStatus","true");
        }else{
            request.getSession().setAttribute("deleteStatus","false");
        }
        response.sendRedirect("userList");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
