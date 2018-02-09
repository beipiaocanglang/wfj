<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<input type="hidden" name="pageSize" id="pageSize" value="${page.pageSize}"/>
<input type="hidden" name="pageNumber" id="pageNumber" value="${page.thisPageNumber}"/>
<div class="row">
    <div class="col-sm-6">
        <div class="dataTables_info" id="DataTables_Table_0_info" role="alert" aria-live="polite" aria-relevant="all">
            当前第${page.thisPageNumber }页/共${page.lastPageNumber }页&nbsp;&nbsp;共${page.totalCount }条记录&nbsp;&nbsp;
        </div>
    </div>
    <div class="col-sm-6">
        <div class="dataTables_paginate paging_simple_numbers" id="DataTables_Table_0_paginate">
            <ul class="pagination">
                <c:if test="${!page.firstPage}">
                    <li class="paginate_button previous">
                        <a href="javascript:document.forms.ec.pageNumber.value=1;document.forms.ec.submit();">第一页</a>
                    </li>
                </c:if>
                <c:if test="${page.firstPage}">
                    <li class="paginate_button previous">
                        <a href="javascript:">第一页</a>
                    </li>
                </c:if>
                <c:if test="${!page.firstPage}">
                    <li class="paginate_button previous">
                        <a href="javascript:document.forms.ec.pageNumber.value='${page.previousPageNumber}';document.forms.ec.submit();">上一页</a>
                    </li>
                </c:if>
                <c:if test="${page.firstPage}">
                    <li class="paginate_button previous disabled">
                        <a href="javascript:">上一页</a>
                    </li>
                </c:if>

                <c:forEach items="${page.linkPageNumbers }" var="i">
                    <li class="paginate_button <c:if test='${i == page.thisPageNumber }'>active</c:if>">
                        <a href="javascript:document.forms.ec.pageNumber.value='${i}';document.forms.ec.submit();">${i }</a>
                    </li>
                </c:forEach>

                <c:if test="${!page.lastPage}">
                    <li class="paginate_button next">
                        <a href="javascript:document.forms.ec.pageNumber.value='${page.nextPageNumber}';document.forms.ec.submit();">下一页</a>
                    </li>
                </c:if>
                <c:if test="${page.lastPage}">
                    <li class="paginate_button next disabled">
                        <a href="javascript:">下一页</a>
                    </li>
                </c:if>
                <c:if test="${!page.lastPage}">
                    <li class="paginate_button next">
                        <a href="javascript:document.forms.ec.pageNumber.value='${page.lastPageNumber}';document.forms.ec.submit();">尾页</a>
                    </li>
                </c:if>
                <c:if test="${page.lastPage}">
                    <li class="paginate_button next">
                        <a href="javascript:">尾页</a>
                    </li>
                </c:if>
            </ul>
        </div>
    </div>
</div>