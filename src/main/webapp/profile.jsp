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
                <c:if test="${sessionScope.user_role =='USER'}">
                    <!-- Middle Content Area -->
                    <div class="col-md-4 col-sm-12 col-xs-12 leftbar-stick blog-sidebar">
                        <c:import url="parts/user_left_menu.jsp"/>
                    </div>
                </c:if>
                <div class="col-md-8 col-sm-12 col-xs-12">
                    <!-- Row -->
                    <div class="profile-section margin-bottom-20">
                        <div class="profile-tabs">
                            <div class="tab-content">
                                <div class="profile-edit tab-pane fade in active" id="profile">
                                    <h2 class="heading-md">Profile</h2>
                                    <dl class="dl-horizontal">
                                        <dt><strong>Your name </strong></dt>
                                        <dd>
                                            ${userDetails.name}
                                        </dd>
                                        <dt><strong>Email Address </strong></dt>
                                        <dd>
                                            ${userDetails.email}
                                        </dd>
                                        <dt><strong>Phone Number </strong></dt>
                                        <dd>
                                            ${userDetails.phone}
                                        </dd>
                                    </dl>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <c:import url="parts/footer.jsp"/>
</div>
</body>
</html>
