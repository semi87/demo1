<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Advertisement</title>
    <c:import url="parts/header.jsp" />
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

    <section class="custom-padding gray">
        <div class="container">
            <div class="row">
                <div class="col-md-12 col-xs-12 col-sm-12">

                    <h2>${message}</h2>

                </div>
            </div>
        </div>
    </section>
    <c:import url="parts/footer.jsp"/>
</div>
</div>
</body>
</html>