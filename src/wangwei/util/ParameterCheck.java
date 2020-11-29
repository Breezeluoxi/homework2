package wangwei.util;

import javax.servlet.http.HttpServletRequest;

public class ParameterCheck {
    public static int id(String userIdStr,HttpServletRequest request) {
        userIdStr = request.getParameter(userIdStr);
        int userId=-1;
        if (userIdStr==null||userIdStr==""){
            request.setAttribute("Failed",1);
            return userId;
        }
        try {
            userId= Integer.parseInt(userIdStr);
        }catch (Exception exception){
            request.setAttribute("Failed",1);
        }
        return userId;
    }
}
