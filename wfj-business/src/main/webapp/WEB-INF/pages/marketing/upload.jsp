<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>文件上传下载</title>
        <%@ include file="../common/meta.jsp" %>
        <script type="text/javascript">
            $(function () {
                $("#upload").click(function () {
                    $("#imgWait").show();
                    var formData = new FormData();
                    formData.append("file", document.getElementById("file1").files[0]);
                    $.ajax({
                        url: "/mktactive/seed/importSeed",
                        type: "POST",
                        data: formData,
                        /**
                         *必须false才会自动加上正确的Content-Type
                         */
                        contentType: false,
                        /**
                         * 必须false才会避开jQuery对 formdata 的默认处理
                         * XMLHttpRequest会对 formdata 进行正确的处理
                         */
                        processData: false,
                        success: function (data) {
                            var member = JSON.parse(data);
                            console.log(member);
                            if (data.status == "true") {
                                alert("上传成功！");
                            }
                            if (data.status == "error") {
                                alert(data.msg);
                            }
                        },
                        error: function () {
                            alert("上传失败！");
                        }
                    });
                });
            });
        </script>
    </head>
    <body>
    <form action="http://localhost:8082/mktactive/seed/importSeed" method="post" enctype="multipart/form-data">
        选择文件:<input type="file" name="file" id="file1" width="120px"><br/>
        <input type="button" id="upload" value="上传">
    </form>
    </body>
</html>
