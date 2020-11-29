package wangwei.controller;

import wangwei.model.User;
import wangwei.service.UserService;
import wangwei.service.impl.UserServiceImpl;
import wangwei.util.ParameterCheck;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/admin/modUserDo")
public class ModUserServlet extends HttpServlet {
    UserService userService=new UserServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userId=request.getParameter("userId");
        User user = AddUserServlet.getUser(request);
        //获取用户信息失败失败
        if (user==null){
            request.getSession().setAttribute("updateFailed",1);
            response.sendRedirect("/admin/modUser?userId="+userId);
            return;
        }
        user.setId(Integer.parseInt(userId));
        int isOk = userService.updateUser(user);
        HttpSession session = request.getSession();
        if (isOk>0){
            session.setAttribute("updateStatus","true");
            response.sendRedirect(request.getContextPath()+"/admin/userList");
        } else{
            session.setAttribute("updateStatus","false");
            response.sendRedirect(request.getContextPath()+"/admin/modUser?userId="+userId);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
