<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 王大哥
  Date: 2020/11/26
  Time: 17:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>修改用户</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" href="../css/reset-min.css">
    <link rel="stylesheet" href="../css/addUser.css">
</head>
<body>
    <h1>修改用户${user.name}</h1>
    <form action="modUserDo?userId=${user.id}" method="post" enctype="multipart/form-data">
        <ul id="content">
            <li>
                <input class="text" type="text" name="name" placeholder="${user.name}" value="${user.name}">
            </li>
            <li>
                <input class="text" type="text" name="password" placeholder="${user.password}" value="${user.password}">
            </li>
            <li>
                <select name="age">
                    <option value="age_00" <c:if test="${user.age=='00后'}">selected</c:if>>00后</option>
                    <option value="age_90" <c:if test="${user.age=='90后'}">selected</c:if>>90后</option>
                    <option value="age_80" <c:if test="${user.age=='80后'}">selected</c:if>>80后</option>
                    <option value="age_70" <c:if test="${user.age=='70后'}">selected</c:if>>70后</option>
                    <option value="age_other" <c:if test="${user.age=='other'}">selected</c:if>>其他</option>
                </select>
            </li>
            <li>
                <label for="check1">
                    <input type="checkbox" name="hobby" id="check1" value="read" <c:if test="${user.isContain('read')}">checked</c:if>><span><span></span></span>阅读
                </label>
                <label for="check2">
                    <input type="checkbox" name="hobby" id="check2" value="music" <c:if test="${user.isContain('music')}">checked</c:if>><span><span></span></span>音乐
                </label>
                <label for="check3">
                    <input type="checkbox" name="hobby" id="check3" value="sports" <c:if test="${user.isContain('sports')}">checked</c:if>><span><span></span></span>运动
                </label>
                <label for="check4">
                    <input type="checkbox" name="hobby" id="check4" value="other" <c:if test="${user.isContain('other')}">checked</c:if>><span><span></span></span>其他
                </label>
            </li>
            <li>
                <label for="radio1">
                    <input type="radio" name="gender" id="radio1" value="male" <c:if test="${user.gender=='male'}">checked</c:if>><span><span></span></span>男
                </label>
                <label for="radio2">
                    <input type="radio" name="gender" id="radio2" value="female" <c:if test="${user.gender=='female'}">checked</c:if>><span><span></span></span>女
                </label>
            </li>
            <li>
                <div class="centerEle">
                    <div class="form-group col-md-3  mb-2">
                        <input type="text" id="viewfile" placeholder="未选择文件" disabled autocomplete="off" class="form-control">
                        <input type="file" style="display: none" name="photo" onchange="reShow();" id="upload"/>
                    </div>
                    <label class="btn btn-primary  mb-2" for="upload" id="upText">浏览</label>
                </div>
            </li>

            <li>
                <input type="submit" class="button" value="修改">
                <input type="reset" class="button" value="重置">
            </li>
        </ul>
    </form>

</body>
<script>
    window.onload=function () {
        if (${updateFailed!=null}){
            alert("修改失败")
        }
    }
    function reShow() {
        document.getElementById('viewfile').value = document.getElementById('upload').value;
    }
</script>
</html>
