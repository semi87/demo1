<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c' %>
<div class="colored-header">
    <!-- Top Bar -->
    <div class="header-top">
        <div class="container">
            <div class="row">
                <div class="header-top-left col-md-8 col-sm-6 col-xs-12 hidden-xs">
                </div>
                <div class="header-right col-md-4 col-sm-6 col-xs-12 ">
                    <div class="pull-right">
                        <ul class="listnone">

                            <c:choose>
                                <c:when test="${!empty sessionScope.user_id}">

                                    <li class="dropdown">
                                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button"
                                           aria-haspopup="true" aria-expanded="false"><i class="icon-profile-male"
                                                                                         aria-hidden="true"></i> ${sessionScope.user_name}
                                            <span class="caret"></span></a>
                                        <ul class="dropdown-menu">
                                            <li><a href="${pageContext.servletContext.contextPath}/profile">Profile</a></li>

                                            <c:if test="${!empty sessionScope.user_id}">
                                                <c:choose>
                                                    <c:when test="${sessionScope.user_role =='ADMIN'}">
                                                        <li>
                                                            <a href="${pageContext.servletContext.contextPath}/admin/approval_advertisements">Pending</a>
                                                        </li>
                                                        <li>
                                                            <a href="${pageContext.servletContext.contextPath}/admin/categories">Categories</a>
                                                        </li>
                                                        <li>
                                                            <a href="${pageContext.servletContext.contextPath}/admin/users">Users</a>
                                                        </li>

                                                    </c:when>

                                                    <c:when test="${sessionScope.user_role =='MANAGER'}">
                                                        <li>
                                                            <a href="${pageContext.servletContext.contextPath}/admin/approval_advertisements">Pending</a>
                                                        </li>
                                                    </c:when>
                                                    <c:otherwise>


                                                        <li>
                                                            <a href="${pageContext.servletContext.contextPath}/advertisements/active">Active</a>
                                                        </li>
                                                        <li>
                                                            <a href="${pageContext.servletContext.contextPath}/advertisements/pending">Pending</a>
                                                        </li>
                                                        <li>
                                                            <a href="${pageContext.servletContext.contextPath}/advertisements/expired">Expired</a>
                                                        </li>
                                                        <li>
                                                            <a href="${pageContext.servletContext.contextPath}/advertisements/favorite">Favorite</a>
                                                        </li>

                                                    </c:otherwise>
                                                </c:choose>
                                            </c:if>
                                            <!--<li><a href="messages.html">Message Panel</a></li>-->
                                            <li><a class="login-btn"
                                                   href="${pageContext.servletContext.contextPath}/logout">Logout</a>
                                            </li>
                                        </ul>
                                    </li>
                                </c:when>
                                <c:otherwise>
                                    <li><a href="${pageContext.servletContext.contextPath}/login"><i
                                            class="fa fa-sign-in"></i> Log in</a></li>
                                    <li><a href="${pageContext.servletContext.contextPath}/registration"><i
                                            class="fa fa-unlock" aria-hidden="true"></i> Register</a></li>
                                </c:otherwise>
                            </c:choose>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- Top Bar End -->
    <!-- Navigation Menu -->
    <div class="clearfix"></div>
    <!-- menu start -->
    <nav id="menu-1" class="mega-menu">
        <!-- menu list items container -->
        <section class="menu-list-items">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12 col-md-12">

                        <!-- menu links -->
                        <ul class="menu-links">
                            <!-- active class -->
                            <li>
                                <a href="${pageContext.servletContext.contextPath}/"> Home</a>
                            </li>


                            <c:if test="${!empty sessionScope.user_id}">
                                <li>
                                    <a href="javascript:void(0)">Dashboard <i
                                            class="fa fa-angle-down fa-indicator"></i></a>
                                    <!-- drop down multilevel  -->
                                    <ul class="drop-down-multilevel">
                                        <li><a href="${pageContext.servletContext.contextPath}/profile">Profile</a></li>
                                        <c:choose>
                                            <c:when test="${sessionScope.user_role =='ADMIN'}">


                                                <li><a href="${pageContext.servletContext.contextPath}/admin/approval_advertisements">Pending</a></li>
                                                <li>  <a href="${pageContext.servletContext.contextPath}/admin/categories">Categories</a></li>
                                                <li>  <a href="${pageContext.servletContext.contextPath}/admin/users">Users</a></li>


                                            </c:when>

                                            <c:when test="${sessionScope.user_role =='MANAGER'}">

                                                <li>
                                                    <a href="${pageContext.servletContext.contextPath}/admin/approval_advertisements">Pending</a>
                                                </li>
                                            </c:when>
                                            <c:otherwise>

                                                <li>
                                                    <a href="${pageContext.servletContext.contextPath}/advertisements/active">Active</a>
                                                </li>
                                                <li>
                                                    <a href="${pageContext.servletContext.contextPath}/advertisements/pending">Pending</a>
                                                </li>
                                                <li>
                                                    <a href="${pageContext.servletContext.contextPath}/advertisements/expired">Expired</a>
                                                </li>
                                                <li>
                                                    <a href="${pageContext.servletContext.contextPath}/advertisements/favorite">Favorite</a>
                                                </li>
                                            </c:otherwise>
                                        </c:choose>
                                        <li><a class="login-btn"
                                               href="${pageContext.servletContext.contextPath}/logout">Logout</a>
                                        </li>
                                    </ul>
                                </li>
                            </c:if>


                        </ul>
                        <c:if test="${sessionScope.user_role !='ADMIN' and sessionScope.user_role !='MANAGER'}">
                            <ul class="menu-search-bar">
                                <li>
                                    <a href="${pageContext.servletContext.contextPath}/new" class="btn btn-light"><i
                                            class="fa fa-plus" aria-hidden="true"></i> Add new</a>
                                </li>
                            </ul>
                        </c:if>
                    </div>
                </div>
            </div>
        </section>
    </nav>
</div>

