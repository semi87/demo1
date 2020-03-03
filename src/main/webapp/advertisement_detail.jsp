<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Advertisement</title>
    <c:import url="parts/header.jsp" />
</head>
<body>
<c:import url="parts/navigation.jsp" />



<div class="small-breadcrumb">
    <div class="container">
        <div class=" breadcrumb-link">
            <ul><li>&nbsp;</li></ul>
        </div>
    </div>
</div>

<div class="main-content-area clearfix">
    <!-- =-=-=-=-=-=-= Latest Ads =-=-=-=-=-=-= -->
    <section class="section-padding error-page pattern-bgs gray ">
        <!-- Main Container -->
        <div class="container">
            <!-- Row -->
            <div class="row">
                  <!-- Middle Content Area -->
                <div class="col-md-10 col-xs-12 col-sm-12">
                    <div>
                        <!-- Title -->
                        <div class="ad-box">
                            <h1>${advertisementDetails.title}</h1>
                            <div class="short-history">
                                <ul>
                                    <li>Published on: <b>${advertisementDetails.created_date}</b></li>
                                    <li>Author: <b>${advertisementDetails.user.name}</b></li>
                                    <li>Phone: <b>${advertisementDetails.user.phone}</b></li>
                                </ul>
                            </div>
                        </div>
                        <!-- Short Description  -->
                        <div class="ad-box">
                            <div class="short-features">
                                <!-- Heading Area -->
                                <div class="heading-panel">
                                    <h3 class="main-title text-left">
                                        Description
                                    </h3>
                                </div>
                            </div>
                            <div class="specification">
                                ${advertisementDetails.description}
                            </div>
                            <div class="clearfix"></div>
                        </div>
                    </div>
                    <!-- Single Ad End -->

                </div>
                <!-- Middle Content Area  End -->
            </div>
            <!-- Row End -->
        </div>
        <!-- Main Container End -->
    </section>
    <c:import url="parts/footer.jsp" />
</div>
<!-- Main Content Area End -->



</body>
</html>