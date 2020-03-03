<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
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
                        <form action="${pageContext.servletContext.contextPath}/login" method="post">
                            <div class="form-group">
                                <label>Email</label>
                                <input placeholder="Your Email" class="form-control" required autofocus name="email" type="email">
                            </div>
                            <div class="form-group">
                                <label>Password</label>
                                <input placeholder="Your Password" class="form-control" required name="password" type="password">
                            </div>
                            <div class="form-group">
                                <div class="row">
                                    <div class="col-xs-12">
                                        <div class="skin-minimal">
                                            <ul class="list">
                                                <li>
                                                    <input  type="checkbox" id="minimal-checkbox-1">
                                                    <label for="minimal-checkbox-1">Remember Me</label>
                                                </li>
                                            </ul>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div style="color: red;">${message}</div>
                            <button class="btn btn-theme btn-lg btn-block">Login</button>
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









