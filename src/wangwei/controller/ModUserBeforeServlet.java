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

@WebServlet("/admin/modUser")
public class ModUserBeforeServlet extends HttpServlet {
    UserService userService=new UserServiceImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int userId = ParameterCheck.id("userId", request);
        if (userId<0)return;
        HttpSession session = request.getSession();
        if (session.getAttribute("updateFailed")!=null){
            session.removeAttribute("updateFailed");
            request.setAttribute("updateFailed",1);
        }

        User user = userService.queryUser(userId);
        request.setAttribute("user",user);
        request.getRequestDispatcher("../WEB-INF/view/mod_user.jsp").forward(request,response);

    }



    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
