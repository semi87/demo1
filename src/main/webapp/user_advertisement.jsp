<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Profile</title>
    <c:import url="parts/header.jsp"/>
</head>
<body>
<c:import url="parts/navigation.jsp"/>


<div class="small-breadcrumb">
    <div class="container">
        <div class=" breadcrumb-link">
            <ul>
                <li>&nbsp;</li>
            </ul>
        </div>
    </div>
</div>
<!-- =-=-=-=-=-=-= Main Content Area =-=-=-=-=-=-= -->
<div class="main-content-area clearfix">
    <!-- =-=-=-=-=-=-= Latest Ads =-=-=-=-=-=-= -->
    <section class="section-padding gray">
        <!-- Main Container -->
        <div class="container">
            <!-- Row -->
            <div class="row">
                <!-- Middle Content Area -->
                <div class="col-md-4 col-sm-12 col-xs-12 leftbar-stick blog-sidebar">
                    <c:import url="parts/user_left_menu.jsp"/>
                </div>
                <div class="col-md-8 col-sm-12 col-xs-12">
                    <!-- Row -->
                    <div class="row">
                        <!-- Sorting Filters -->
                        <div class="col-md-12 col-xs-12 col-sm-12 col-lg-12 heading">

                        </div>
                        <!-- Sorting Filters End-->
                        <div class="clearfix"></div>
                        <!-- Ads Archive -->
                        <div class="posts-masonry">
                            <div class="col-md-12 col-xs-12 col-sm-12 user-archives">
                                <c:forEach var="advertisement" items="${advertisementsList}">

                                    <div class="ads-list-archive">
                                        <!-- Image Block -->
                                        <div class="col-lg-3 col-md-3 col-sm-3 no-padding">
                                            <!-- Img Block -->
                                            <div class="ad-archive-img">
                                                <a href="#">
                                                    <img src="${pageContext.servletContext.contextPath}/static/images/posting/2.jpg"
                                                         alt="">
                                                </a>
                                            </div>
                                            <!-- Img Block -->
                                        </div>
                                        <!-- Ads Listing -->
                                        <div class="clearfix visible-xs-block"></div>
                                        <!-- Content Block -->
                                        <div class="col-lg-9 col-md-9 col-sm-9 no-padding">
                                            <!-- Ad Desc -->
                                            <div class="ad-archive-desc">
                                                <!-- Title -->
                                                <h3><a href="${pageContext.servletContext.contextPath}/advertisement/${advertisement.id}">${advertisement.title}</a></h3>
                                                <!-- Category -->
                                                <div class="category-title"><span>${advertisement.category.title}</span>
                                                </div>
                                                <!-- Short Description -->
                                                <div class="clearfix visible-xs-block"></div>
                                                <!-- Ad Features -->
                                                <!-- Ad History -->
                                                <div class="clearfix archive-history">
                                                    <div class="last-updated">${advertisement.created_date}</div>
                                                </div>
                                                <b>Short description</b>:<div style="width: 100%;">${advertisement.short_description}</div>
                                                <b>Description</b>:<div  style="width: 100%;">${advertisement.description}</div>
                                            </div>
                                            <c:if test="${user_menu=='active'}">
                                                <button class="btn btn-danger" style="float: right;margin: 10px;" onclick="send_disable(${advertisement.id}    )"><i class="fa fa-minus-circle"></i>
                                                    Disable</button>
                                            </c:if>

                                            <c:if test="${user_menu!='pending'}">
                                            <a style="float: right;margin: 10px;" class="btn btn-primary" href="${pageContext.servletContext.contextPath}/edit?advertisementId=${advertisement.id}">Edit</a>
                                            </c:if>
                                        </div>
                                        <!-- Content Block End -->
                                    </div>
                                </c:forEach>
                                <c:if test="${empty advertisementsList}">
                                    No data
                                </c:if>
                            </div>
                        </div>
                        <!-- Ads Archive End -->
                        <div class="clearfix"></div>
                    </div>
                    <!-- Row End -->
                </div>
                <!-- Middle Content Area  End -->
            </div>
            <!-- Row End -->
        </div>
        <!-- Main Container End -->
    </section>
    <!-- =-=-=-=-=-=-= Ads Archives End =-=-=-=-=-=-= -->
    <c:import url="parts/footer.jsp"/>
</div>

<c:if test="${user_menu=='active'}">
<script>
    function send_disable(advertisementId) {
        $.ajax({
            type: "POST",
            url: "${pageContext.servletContext.contextPath}/advertisements/active/disable_advertisement",
            data: {advertisementId:advertisementId},
            success: function () {
                $("#adv_"+advertisementId).remove();
            }
        });
    }
</script>
</c:if>
</body>
</html>
