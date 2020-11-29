<%--
  Created by IntelliJ IDEA.
  User: 王大哥
  Date: 2020/11/26
  Time: 17:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>用户列表</title>
    <link rel="stylesheet" href="../css/reset.css">
    <link rel="stylesheet" href="../css/userList.css">
</head>
<body>
    <h1>用户列表</h1>

    <article>
        <table>
            <tr>
                <td class="td_head td_icon">Icon</td>
                <td class="td_head td_name">Name</td>
                <td class="td_head td_password">Password</td>
                <td class="td_head td_gender">Gender</td>
                <td class="td_head td_age">Age</td>
                <td class="td_head td_hobby">Hobby</td>
                <td class="td_head td_delete">Delete</td>
                <td class="td_head td_modify">Modify</td>
            </tr>
            <c:forEach items="${userList}" var="user">
                <tr>
                    <td class="td_icon">
                        <img class="pic" src="../${user.picUrl}">
                    </td>
                    <td class="td_name">
                            ${user.name}
                    </td>
                    <td class="td_password">
                            ${user.password}
                    </td>
                    <td class="td_gender">
                            ${user.gender}
                    </td>
                    <td class="td_age">
                            ${user.age}
                    </td>
                    <td class="td_hobby">
                            ${user.hobby}
                    </td>
                    <td class="td_delete">
                        <a href="delUser?userId=${user.id}">❌</a>
                    </td>
                    <td class="td_modify">
                        <a href="modUser?userId=${user.id}">⭕</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </article>
    <nav>
        <a href="userList?pageIndex=${(pageIndex-1)<1?1:(pageIndex-1)}">🔺</a>
        <c:forEach begin="1" end="${pageNum}" var="index" step="1">
            <a class="pageIndex" href="userList?pageIndex=${index}">${index}</a>
        </c:forEach>
        <a href="userList?pageIndex=${(pageIndex+1)>pageNum?pageNum:(pageIndex+1)}">🔻</a>
    </nav>
</body>
    <script>
        window.onload=function () {
            if (${deleteSuccess!=null}){
                alert("删除成功")
            }
            if (${deleteFailed!=null}){
                alert("删除失败")
            }
            if (${updateSuccess!=null}){
                alert("修改用户成功")
            }
        }
    </script>
</html>
