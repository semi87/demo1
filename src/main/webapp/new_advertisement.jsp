<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>New advertise</title>
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
<div class="main-content-area clearfix">
    <section class="section-padding  gray ">
        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <div class="post-ad-form extra-padding postdetails">
                        <div class="heading-panel">
                            <h3 class="main-title text-left">
                                New
                            </h3>
                        </div>
                        <c:if test="${empty message}">
                            <form class="submit-form" action="${pageContext.servletContext.contextPath}/new"
                                  method="post">
                                <!-- Title  -->
                                <div class="row">
                                    <div class="col-md-12 col-lg-12 col-xs-12 col-sm-12">
                                        <label class="control-label">Title </label>
                                        <input class="form-control" placeholder="Title" name="title" type="text">
                                    </div>
                                </div>
                                <!-- Ad Tags  -->
                                <div class="row">
                                    <div class="col-md-12 col-lg-12 col-xs-12  col-sm-12">
                                        <label class="control-label">Short description <small>Enter a short
                                            description</small></label>
                                        <textarea class="form-control" rows="3" name="short_description"
                                                  id="short_description"></textarea>
                                    </div>
                                </div>
                                <!-- end row -->
                                <div class="row">
                                    <!-- Category  -->
                                    <div class="col-md-6 col-lg-6 col-xs-12 col-sm-12">
                                        <label class="control-label">Category <small>Select suitable
                                            category </small></label>
                                        <select class="category form-control" name="category">
                                            <option label="Select Option"></option>
                                            <c:forEach var="cat" items="${categoriesList}">
                                                <option value="${cat.id}"
                                                        <c:if test="${category == cat.title}">selected</c:if>> ${cat.title}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                    <div class="col-md-6 col-lg-6 col-xs-12 col-sm-12">
                                        <label class="control-label">Expiration date </label>
                                        <input class="form-control" readonly="readonly" name="expiration_date"
                                               id="expiration_date" value="">
                                    </div>
                                </div>
                                <!-- end row -->
                                <!-- Ad Description  -->
                                <div class="row">
                                    <div class="col-md-12 col-lg-12 col-xs-12  col-sm-12">
                                        <label class="control-label">Description </label>
                                        <textarea name="description" id="description<" rows="12" class="form-control"
                                                  placeholder=""></textarea>
                                    </div>
                                </div>

                                <!-- end row -->
                                <button class="btn btn-theme pull-right">Save</button>
                            </form>
                        </c:if><h3>${message}</h3>
                    </div>
                    <!-- end post-ad-form-->
                </div>
                <!-- end col -->
            </div>
            <!-- Row End -->
        </div>
        <!-- Main Container End -->
    </section>
    <c:import url="parts/footer.jsp"/>
</div>
<script src="<c:url value='/static/js/ckeditor/ckeditor.js'/>"></script>
<script type="text/javascript">
    "use strict";
    CKEDITOR.replace('description');

    $('input#expiration_date').datepicker({format: 'yyyy-dd-mm'});
</script>
</body>
</html>
