<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>New advertisements</title>
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


<!-- =-=-=-=-=-=-= Main Content Area =-=-=-=-=-=-= -->
<div class="main-content-area clearfix">
    <!-- =-=-=-=-=-=-= Latest Ads =-=-=-=-=-=-= -->
    <section class="section-padding gray">
        <!-- Main Container -->
        <div class="container">
            <!-- Row -->
            <div class="row">
                <div class="col-md-8 col-sm-12 col-xs-12">
                    <!-- Row -->
                    <div class="row">
                        <!-- Ads Archive -->
                        <div class="posts-masonry">
                            <div class="col-md-12 col-xs-12 col-sm-12 user-archives">
                                <c:forEach var="advertisement" items="${advertisementsList}">

                                    <div class="ads-list-archive" id="adv_${advertisement.id}">
                                        <!-- Image Block -->
                                        <div class="col-lg-3 col-md-3 col-sm-3 no-padding">
                                            <!-- Img Block -->
                                            <div class="ad-archive-img">
                                                <a href="#">
                                                    <img src="${pageContext.servletContext.contextPath}/static/images/posting/2.jpg" alt="">
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
                                                <h3>${advertisement.title}</h3>
                                                <!-- Category -->
                                                <div class="category-title"><span>${advertisement.category.title}</span></div>
                                                <div class="category-title">Author:<span>${advertisement.user.name}</span></div>
                                                <!-- Short Description -->
                                                <div class="clearfix visible-xs-block">${advertisement.short_description}</div>
                                                <div class="clearfix visible-xs-block">${advertisement.description}</div>

                                                <div class="clearfix archive-history">
                                                    <div class="last-updated">${advertisement.created_date}</div>
                                                    <div class="ad-meta">
                                                        <a class="btn save-ad" onclick="prepare_block(${advertisement.id},${advertisement.user.id},'BLOCKED')"><i class="fa fa-minus-circle"></i>
                                                            Block</a>
                                                        <a class="btn save-ad" onclick="send_approve(${advertisement.id},'ACTIVE');"><i class="fa fa-plus-circle"></i>
                                                            Approve</a>

                                                    </div>
                                                </div>
                                            </div>
                                            <!-- Ad Desc End -->
                                        </div>
                                        <!-- Content Block End -->
                                    </div>
                                </c:forEach>
                            </div>
                        </div>

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

    <!-- Modal -->
    <div class="modal fade" id="reasonModal" tabindex="-1" role="dialog" aria-labelledby="reasonModalLabel"
         aria-hidden="true">
        <div class="modal-dialog" role="document">
            <form>
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Reason</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <textarea type="text" class="form-control" name="reason" id="reason" placeholder="Reason "></textarea>
                        <input type="hidden" name="advertisementId" id="advertisementId" value=""/>
                        <input type="hidden" name="userId" id="userId" value=""/>
                        <div id="message"></div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                        <button type="button" class="btn btn-primary" onclick="send_unaprove()">Send</button>
                    </div>
                </div>
            </form>
        </div>
    </div>

    <c:import url="parts/footer.jsp" />
    <script>
        function prepare_block(advertisementId, userId) {
            $("#advertisementId").val(advertisementId);
            $("#userId").val(userId);
            $("#reason").val("");
            $('#reasonModal').modal('toggle');
        }


        function send_approve(advertisementId) {
            $.ajax({
                type: "POST",
                url: "${pageContext.servletContext.contextPath}/admin/approval_advertisements",
                data: {advertisementId:advertisementId, status:"ACTIVE"},
                success: function () {
                    $("#adv_"+advertisementId).remove();
                }
            });
        }

        function send_unaprove() {
            let reason = $("#reason").val();
            let advertisementId = $("#advertisementId").val();
            let userId = $("#userId").val();
            $.ajax({
                type: "POST",
                url: "${pageContext.servletContext.contextPath}/admin/approval_advertisements",
                data: {advertisementId:advertisementId, status:"BLOCKED", reason:reason, userId:userId},
                success: function () {
                    $("#adv_"+advertisementId).remove();
                    $('#reasonModal').modal('toggle');
                }
            });
        }
    </script>
</div>
<!-- Main Content Area End -->
</body>
</html>
