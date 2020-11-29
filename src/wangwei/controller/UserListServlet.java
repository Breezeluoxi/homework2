package wangwei.controller;

import jdk.nashorn.internal.ir.CallNode;
import wangwei.dao.UserDao;
import wangwei.dao.impl.UserDaoImpl;
import wangwei.model.User;
import wangwei.service.UserService;
import wangwei.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/admin/userList")
public class UserListServlet extends HttpServlet {
    UserService userService=new UserServiceImpl();
    private static final int NUM = 5;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        checkSessionStatus(request,"deleteStatus");
        checkSessionStatus(request,"updateStatus");

        String pageIndexStr = request.getParameter("pageIndex");
        if (pageIndexStr==null||pageIndexStr=="")
            pageIndexStr="1";
        int pageIndex = Integer.parseInt(pageIndexStr);

        pageIndex=pageIndex<=0?1:pageIndex;
        //设置页数
        int userCount = userService.getUserCount();
        int pageNum=userCount/NUM+(userCount%NUM==0?0:1);
        request.setAttribute("pageNum",pageNum);
        //设置内容
        if(pageIndex>pageNum){
            pageIndex=pageNum;
        }else if(pageIndex<=0){
            pageIndex=1;
        }
        List<User> userList = userService.getUserList((pageIndex - 1) * NUM, NUM);
        request.setAttribute("pageIndex",pageIndex);
        request.setAttribute("userList",userList);
        request.getRequestDispatcher("../WEB-INF/view/userlist.jsp").forward(request,response);
    }

    private void checkSessionStatus(HttpServletRequest request,String statusName) {
        HttpSession session = request.getSession();
        String status = (String)session.getAttribute(statusName);
        if (status!=null){
            session.removeAttribute(statusName);
            if ("true".equals(status)){
                request.setAttribute(statusName.substring(0,statusName.lastIndexOf("Status"))+"Success",1);
            }else{
                request.setAttribute(statusName.substring(0,statusName.lastIndexOf("Status"))+"Failed",1);
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
