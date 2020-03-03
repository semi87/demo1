<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Advertisement</title>
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

    <section class="custom-padding gray">
        <div class="container">
            <div class="row">
                <div class="col-md-12 col-xs-12 col-sm-12">
                    <table class="table table-striped">
                        <c:forEach var="user" items="${userList}">
                            <c:if test="${sessionScope.user_id != user.id}">
                            <tr>
                                <td> ${user.email}</td>
                                <td> ${user.name}</td>
                                <td> ${user.phone}</td>
                                <td> ${user.role}</td>
                                <td> ${user.status}</td>
                                <td width="50px">
                                    <button class="btn btn-success"
                                            onclick="prepare_edit('${user.email}','${user.role}','${user.status}', ${user.id});">Edit
                                    </button>
                                </td>
                            </tr>
                            </c:if>
                        </c:forEach>
                    </table>
                    <c:if test="${empty userList}">
                        No data
                    </c:if>

                </div>
            </div>
        </div>
    </section>
    <c:import url="parts/footer.jsp"/>
</div>
<!-- Modal -->
<div class="modal fade" id="categoryModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
     aria-hidden="true">
    <div class="modal-dialog" role="document">
        <form>
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel"></h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <input class="form-control" style="margin: 25px; width: 90%;" disabled="disabled"  value="" id="email"/>
                    <select type="text" style="margin: 25px;     width: 90%;" class="form-control" name="userRole" id="userRole">
                        <option value="ADMIN">ADMIN</option>
                        <option value="MANAGER">MANAGER</option>
                        <option value="USER">USER</option>
                    </select>
                    <select type="text" style="margin: 25px;     width: 90%;" class="form-control" name="userStatus" id="userStatus">
                        <option value="NEW">NEW</option>
                        <option value="ACTIVE">ACTIVE</option>
                        <option value="BLOCKED">BLOCKED</option>
                    </select>
                    <input type="hidden" name="userId" id="userId" value=""/>
                    <input type="hidden" name="action" id="action" value=""/>
                    <div id="message"></div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                    <button type="button" style="margin: 15px;" class="btn btn-primary" onclick="send_changes();">Save changes</button>
                </div>
            </div>
        </form>
    </div>
</div>
<script>

    function prepare_edit(email,role, status, userId) {
        $("#userStatus option[value='" + status + "']").prop('selected', true);
        $("#userRole option[value='" + role + "']").prop('selected', true);
        $("#userId").val(userId);
        $("#email").val(email);
        $("#action").val("edit");
        $('#categoryModal').modal('toggle')
    }

    function send_changes() {
        let userStatus = $("#userStatus").val();
        let userRole = $("#userRole").val();
        let userId = $("#userId").val();
        let action = $("#action").val();
            $.ajax({
                type: "POST",
                url: "${pageContext.servletContext.contextPath}/admin/users",
                data: {userId: userId, userStatus: userStatus, userRole: userRole, action: action},
                success: function () {
                    $("#message").html("Success");
                    setTimeout(function () {
                        location.reload();
                    }, 1000);
                }
            });

    }
</script>
</body>
</html>