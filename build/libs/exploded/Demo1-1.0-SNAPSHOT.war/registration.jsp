<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Registration</title>
    <link rel="stylesheet" href="static/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="static/css/style.css"/>

    <script src="https://code.jquery.com/jquery-1.10.2.js"
            type="text/javascript"></script>
    <script src="static/js/registration.js" type="text/javascript"></script>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-sm-9 col-md-7 col-lg-5 mx-auto">
            <div class="card card-signin my-5">
                <div class="">
                    <h5 class="card-title text-center">Registration</h5>tg
                    <form class="form-signin" action="/registration" method="post">
                        <div class="form-label-group">
                            <input type="email" id="inputEmail" class="form-control" name="email" placeholder="Email address" required autofocus>
                            <label for="inputEmail">Email address</label>

                        </div>
                        <label class="error" for="inputPassword" id="inputEmail_err_msq">vvv</label>
                        <div class="form-label-group">
                            <input type="password" id="inputPassword" class="form-control" name="password" placeholder="Password" required>
                            <label for="inputPassword">Password</label>
                            <label class="error" for="inputPassword" id="inputPassword_err_msq">vvv</label>
                        </div>
                        <div class="form-label-group">
                            <input type="password" id="inputPasswordConfirm" class="form-control" name="password_confirm" placeholder="Confirm password" required>
                            <label for="inputPassword">Password</label>
                        </div>
                        <button class="btn btn-lg btn-primary btn-block" type="button">Registration</button>
                     </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
