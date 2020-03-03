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
            <ul>
                <li>&nbsp;</li>
            </ul>
        </div>
    </div>
</div>
<div class="main-content-area clearfix">

    <section class="custom-padding gray">
        <div class="container">
            <div class="row">
                <button style="float: right; margin: 15px" type="button" class="btn btn-primary"
                        onclick="prepare_add();">Add new
                    category
                </button>
                <div class="col-md-12 col-xs-12 col-sm-12">
                    <table class="table table-striped">
                        <c:forEach var="category" items="${categoryList}">
                            <tr>
                                <td> ${category.title}</td>
                                <td width="50px">
                                    <button class="btn btn-success"
                                            onclick="prepare_edit('${category.title}', ${category.id})">Edit
                                    </button>
                                </td>
                                <td width="50px">
                                    <button class="btn btn-danger" onclick="delete_category(${category.id})">Delete
                                    </button>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                    <c:if test="${empty categoryList}">
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
                    <h5 class="modal-title" id="exampleModalLabel">Category</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <input type="text" class="form-control" name="categoryTitle" id="categoryTitle"
                           placeholder="Category ">
                    <input type="hidden" name="categoryId" id="categoryId" value=""/>
                    <input type="hidden" name="action" id="action" value=""/>
                    <div id="message"></div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                    <button type="button" style="margin: 15px;" class="btn btn-primary" onclick="send_changes()">Save
                        changes
                    </button>
                </div>
            </div>
        </form>
    </div>
</div>
<script>
    function prepare_add() {
        $("#categoryTitle").val("");
        $("#categoryId").val("");
        $("#action").val("add");
        $('#categoryModal').modal('toggle')
    }

    function prepare_edit(categoryTitle, categoryId) {
        $("#categoryTitle").val(categoryTitle);
        $("#categoryId").val(categoryId);
        $("#action").val("edit");
        $('#categoryModal').modal('toggle')
    }

    function send_changes() {
        let categoryTitle = $("#categoryTitle").val();
        let categoryId = $("#categoryId").val();
        let action = $("#action").val();
        if (categoryTitle !== "") {
            $.ajax({
                type: "POST",
                url: "${pageContext.servletContext.contextPath}/admin/categories",
                data: {categoryTitle: categoryTitle, categoryId: categoryId, action: action},
                success: function (result) {
                    if (result === "ok") {
                        $("#message").html("Success");
                        setTimeout(function () {
                            location.reload();
                        }, 1000);
                    } else {
                        $("#message").html(result);
                    }
                }
            });
        }
    }

    function delete_category(categoryId) {
        if (confirm("Are you sure?")) {
            $.ajax({
                type: "POST",
                url: "${pageContext.servletContext.contextPath}/admin/categories",
                data: {categoryId: categoryId, action: "delete"},
                success: function (result) {
                    if (result === "ok") {
                        setTimeout(function () {
                            location.reload();
                        }, 1000);
                    } else {
                       alert(result);
                    }
                }
            });
        }
    }
</script>
</body>
</html>