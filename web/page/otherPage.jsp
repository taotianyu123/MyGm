<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:choose>
    <c:when test="${param.msg=='1'}">
        <script>
            open("index.jsp",'_parent')
        </script>
    </c:when>
</c:choose>