<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Advertisement</title>
    <c:import url="parts/header.jsp" />
</head>
<body>
<c:import url="parts/navigation.jsp" />
<div class="container">
    <div class="row">
        <div class="col-md-3 col-sm-3 col-xs-12 md-clearfix sm-clearfix" >
            <a href="${pageContext.servletContext.contextPath}/advertisements/">Active</a>
            <a href="${pageContext.servletContext.contextPath}/advertisements/new">New</a>
            <a href="${pageContext.servletContext.contextPath}/advertisements/blocked">Blocked</a>
        </div>
        <div class="col-md-9 col-sm-9 col-xs-12 md-clearfix sm-clearfix">
            <div class="row">
                <c:if test="${empty advertisementsList}">
                No data
                </c:if>

                <c:forEach var="advertisement" items="${advertisementsList}">
                    <div class="col-md-6 col-sm-12 col-xs-12 md-clearfix sm-clearfix">
                        <article>
                            <div class="vertical-advertisement-header">
                                <div class="advertisement-title-wrapper">
                                    <h3 class="advertisement-title">
                                        <a href="/">
                                                ${advertisement.title}
                                        </a>
                                    </h3>
                                    <h2 class="advertisement-category"><a href="/">${advertisement.category}</a></h2></div>
                            </div>
                            <div class="advertisement-middle">
                                <div class="advertisement-description"> ${advertisement.description}</div>
                            </div>

                            <div class="advertisement-tags-container">
                                <div class="advertisement-tags">
                                    <div class="job-tags with-title">
                                        <strong>Tags:</strong>
                                        <a class="advertisement-tag" href="/">some tag</a>
                                    </div>
                                </div>
                            </div>
                        </article><!-- #advertisement-## -->
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>
</div>
<c:import url="parts/footer.jsp" />
</body>
</html>