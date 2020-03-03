<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Registration</title>
    <c:import url="parts/header.jsp"/>
</head>
<body>
<c:import url="parts/navigation.jsp"/>
<div class="small-breadcrumb">
    <div class="container">
        <div class=" breadcrumb-link">
            <ul><li>&nbsp;</li></ul>
        </div>
    </div>
</div>
<div class="main-content-area clearfix">
    <!-- =-=-=-=-=-=-= Latest Ads =-=-=-=-=-=-= -->
    <section class="section-padding error-page pattern-bg ">
        <!-- Main Container -->
        <div class="container">
            <!-- Row -->
            <div class="row">
                <!-- Middle Content Area -->
                <div class="col-md-5 col-md-push-3 col-sm-6 col-xs-12">
                    <!--  Form -->
                    <div class="form-grid">
                        <form action="${pageContext.servletContext.contextPath}/registration" method="post">
                            <div class="form-group">
                                <label>Email</label>
                                <input placeholder="Your Email" class="form-control" required  autofocus value="${email}" name="email" type="email">

                            </div>
                            <div class="form-group">
                                <label>Name</label>
                                <input placeholder="Your Name" class="form-control" required  value="${name}" name="name" type="text">
                           ${NotEmpty.User.name}
                            </div>
                            <div class="form-group">
                                <label>Phone</label>
                                <input placeholder="Your Phone" class="form-control" required name="phone" value="${phone}" type="number">
                            </div>
                            <div class="form-group">
                                <label>Password</label>
                                <input placeholder="Password" class="form-control" required name="password" value="${password}" type="password">
                            </div>
                            <div class="form-group">
                                <label>Confirm Password</label>
                                <input placeholder="Confirm Password" class="form-control" required name="confirm_password" value="${confirm_password}" type="password">
                            </div>
                            <div>${message}</div>
                            <div style="color: red;"><p>
                                <c:forEach var="err" items="${errors}">
                                    <c:out value="${err}"/>
                                    <br>
                                </c:forEach>
                            </p></div>
                            <button class="btn btn-theme btn-lg btn-block">Registration</button>
                        </form>
                    </div>
                    <!-- Form -->
                </div>

            </div>
            <!-- Row End -->
        </div>
        <!-- Main Container End -->
    </section>
    <!-- =-=-=-=-=-=-= Ads Archives End =-=-=-=-=-=-= -->
    <c:import url="parts/footer.jsp"/>
</div>

</body>
</html>