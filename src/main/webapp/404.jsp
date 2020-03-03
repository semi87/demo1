<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Advertisement</title>
    <c:import url="parts/header.jsp" />
</head>
<body>
<c:import url="parts/navigation.jsp" />





404
    <c:import url="parts/footer.jsp" />

</div>









<!--


<div class="container">
    <div class="row">
        <div class="col-md-3 col-sm-3 col-xs-12 md-clearfix sm-clearfix" >
            <form action="${pageContext.servletContext.contextPath}/board" style="position: fixed;">
                <div class="form-group"><input class="form-control mr-sm-2" type="text" value="${search_text}" name="search_text" placeholder="Search"/></div>
                <div class="form-group"> <input class="form-control mr-sm-2" type="text" value="${tag}" name="tag" placeholder="Tag"/></div>
                <div class="form-group"> <select name="category" class="custom-select">
                    <option value="">Please select category</option>
                    <c:forEach var="cat" items="${categoriesList}">
                        <option value="${cat.title}" <c:if test = "${category == cat.title}">selected</c:if>> ${cat.title}</option>
                    </c:forEach>
                </select></div>
                <div class="form-group"> <input class="btn btn-success w-100" type="submit" value="Search"/></div>
            </form>
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
                        </article><-- #advertisement-## --
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>
</div>-->

</body>
</html>