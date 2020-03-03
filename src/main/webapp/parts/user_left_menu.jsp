<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<div class="user-profile">
    <ul>
        <li  class="<c:if test="${user_menu == 'profile'}">active</c:if> "><a href="${pageContext.servletContext.contextPath}/profile">Profile</a></li>
        <li class="<c:if test="${user_menu == 'active'}">active</c:if> "><a href="${pageContext.servletContext.contextPath}/advertisements/active">Active </a></li>
        <li class="<c:if test="${user_menu == 'pending'}">active</c:if> "><a href="${pageContext.servletContext.contextPath}/advertisements/pending">Pending </a></li>
        <li class="<c:if test="${user_menu == 'rejected'}">active</c:if> "><a href="${pageContext.servletContext.contextPath}/advertisements/rejected">Rejected </a></li>
        <li class="<c:if test="${user_menu == 'expired'}">active</c:if>" ><a href="${pageContext.servletContext.contextPath}/advertisements/expired">Expired</a></li>
        <li class="<c:if test="${user_menu == 'favorite'}">active</c:if> "><a href="${pageContext.servletContext.contextPath}/advertisements/favorite">Favorites</a></li>
        <!--<li ><a href="messages.html">Messages</a></li>-->
        <li><a href="${pageContext.servletContext.contextPath}/logout">Logout</a></li>
    </ul>
</div>