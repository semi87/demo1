<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Registration</title>
    <c:import url="parts/header.jsp"/>
</head>
<body>
<c:import url="parts/navigation.jsp"/>
<div class="container">
    <div class="row">
        <div class="col-sm-9 col-md-7 col-lg-5 mx-auto">
            <h3 class="text-center">Login</h3>
            <form class="form-signin" action="${pageContext.servletContext.contextPath}/login" method="post">
                <div class="form-label-group">
                    <input type="email" id="inputEmail" class="form-control" name="email" placeholder="Email address"
                           value="" required autofocus>
                    <label for="inputEmail"></label>
                    <span class="error" id="inputEmail_err_msq"></span>
                </div>

                <div class="form-label-group">
                    <input type="password" id="inputPassword" class="form-control" name="password"
                           placeholder="Password" value="" required>
                    <label for="inputPassword"></label>
                    <span class="error" id="inputPassword_err_msq">${errorMsg}</span>
                </div>
                <button class="btn btn-lg btn-primary btn-block" type="submit">Login</button>
            </form>
        </div>
    </div>
</div>
<c:import url="parts/footer.jsp"/>
</body>
</html>
