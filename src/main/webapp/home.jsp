<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Advertisement</title>
    <c:import url="parts/header.jsp"/>
</head>
<body>
<c:import url="parts/navigation.jsp"/>


<!-- Home Banner 1 -->
<section class=" parallex ">

    <div class="container">
        <div class="row">
            <div class="col-md-12">

                <div class="search-section">
                    <div id="form-panel">
                        <form action="${pageContext.servletContext.contextPath}/" method="get">
                            <ul class="list-unstyled search-options clearfix">
                                <li>
                                    <select name="category" class="category form-control">
                                        <option label="Select Option"></option>
                                        <c:forEach var="cat" items="${categoriesList}">
                                            <option value="${cat.id}"
                                                    <c:if test="${category == cat.id}">selected</c:if>> ${cat.title}</option>
                                        </c:forEach>
                                    </select>
                                </li>
                                <li>
                                    <input type="text" value="${search_text}" name="search_text"
                                           placeholder="Used Laptops....">
                                </li>
                                <li>
                                    <button type="submit" class="btn btn-danger btn-lg btn-block">Search</button>
                                </li>
                            </ul>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>

</section>
<div class="main-content-area clearfix">

    <section class="custom-padding gray">
        <div class="container">
            <div class="row">
                <div class="col-md-12 col-xs-12 col-sm-12">
                    <ul class="list-unstyled">
                        <c:forEach var="advertisement" items="${advertisementsList}">
                            <li>
                                <div class="well ad-listing clearfix">
                                    <div class="col-md-3 col-sm-5 col-xs-12 grid-style no-padding">
                                        <!-- Image Box -->
                                        <div class="img-box">
                                            <img src="${pageContext.servletContext.contextPath}/static/images/posting/car-4.jpg"
                                                 class="img-responsive" alt="">
                                        </div>
                                    </div>
                                    <div class="col-md-9 col-sm-7 col-xs-12">
                                        <!-- Ad Content-->
                                        <div class="row">
                                            <div class="content-area">
                                                <div class="col-md-9 col-sm-12 col-xs-12">
                                                    <div class="category-title"><span>${advertisement.category.title}</span>
                                                    </div>
                                                    <h3><a href="${pageContext.servletContext.contextPath}/advertisement/${advertisement.id}">${advertisement.title}</a></h3>
                                                    <c:if test="${sessionScope.user_role =='USER'}">
                                                    <ul class="additional-info pull-right">
                                                        <li>
                                                            <a data-toggle="tooltip" title="Bookmark" onclick="addFavorite(${advertisement.id});" href="#"
                                                               class="fa fa-heart"></a>
                                                        </li>
                                                    </ul>
                                                    </c:if>
                                                    <ul class="ad-meta-info">
                                                        <li><i class="fa fa-clock-o"></i>${advertisement.created_date}</li>
                                                    </ul>
                                                    <div class="ad-details">
                                                        <p>${advertisement.short_description} </p>
                                                    </div>
                                                </div>
                                                <div class="col-md-3 col-xs-12 col-sm-12">
                                                    <a href="${pageContext.servletContext.contextPath}/advertisement/${advertisement.id}"
                                                       class="btn btn-block btn-success"><i class="fa fa-eye"
                                                                                            aria-hidden="true"></i> View</a>
                                                </div>
                                            </div>
                                        </div>
                                        <!-- Ad Content End -->
                                    </div>
                                </div>
                            </li>
                        </c:forEach>
                    </ul>

                    <c:choose>
                        <c:when test="${empty advertisementsList}">
                            No data
                        </c:when>
                        <c:otherwise>
                            <nav aria-label="Navigation for countries">
                                <ul class="pagination">
                                    <c:if test="${currentPage != 1}">
                                        <li class="page-item"><a class="page-link"
                                                                 href="${pageContext.servletContext.contextPath}?<c:if test="${!empty search_text}">search_text=${search_text}&</c:if><c:if test="${!empty category}">category=${category}&</c:if>currentPage=${currentPage-1}">Previous</a>
                                        </li>
                                    </c:if>

                                    <c:forEach begin="1" end="${noOfPages}" var="i">
                                        <c:choose>
                                            <c:when test="${currentPage eq i}">
                                                <li class="page-item active"><a class="page-link">
                                                        ${i} <span class="sr-only">(current)</span></a>
                                                </li>
                                            </c:when>
                                            <c:otherwise>
                                                <li class="page-item"><a class="page-link"
                                                                         href="${pageContext.servletContext.contextPath}?<c:if test="${!empty search_text}">search_text=${search_text}&</c:if><c:if test="${!empty category}">category=${category}&</c:if>currentPage=${i}">${i}</a>
                                                </li>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:forEach>

                                    <c:if test="${currentPage lt noOfPages}">
                                        <li class="page-item"><a class="page-link"
                                                                 href="${pageContext.servletContext.contextPath}?<c:if test="${!empty search_text}">search_text=${search_text}&</c:if><c:if test="${!empty category}">category=${category}&</c:if>currentPage=${currentPage+1}">Next</a>
                                        </li>
                                    </c:if>
                                </ul>
                            </nav>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </div>
    </section>
    <c:import url="parts/footer.jsp"/>
</div>
<script>
    function addFavorite(advertisementId) {
                $.ajax({
                    type: "POST",
                    url: "${pageContext.servletContext.contextPath}/favorite/add",
                    data: {advertisementId: advertisementId},
                    success: function () {
                      alert("added");
                    }
                });
    }
</script>
</body>
</html>