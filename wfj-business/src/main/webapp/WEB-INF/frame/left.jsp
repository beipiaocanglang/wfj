<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

                    <c:forEach items="${mdulList}" var="item">
                        <li>
                            <a href="#">
                                <i class="fa fa-home"></i>
                                <span class="nav-label">${item.name}</span>
                                <span class="fa arrow"></span>
                            </a>
                            <ul class="nav nav-second-level">
                            <c:forEach items="${item.children}" var="it">
                                <c:choose>
                                    <c:when test="${fn:contains(it.url, 'http')}">
                                        <li><a class="J_menuItem" href="${it.url}" data-index="0">${it.name}</a></li>
                                    </c:when>
                                    <c:otherwise>
                                        <li><a class="J_menuItem" href="${ctx }/${it.url}" data-index="0">${it.name}</a></li>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                            </ul>
                        </li>
                    </c:forEach>
                    <!--
                    <li>
                        <a href="#">
                            <i class="fa fa-home"></i>
                            <span class="nav-label">系统管理</span>
                            <span class="fa arrow"></span>
                        </a>
                        <ul class="nav nav-second-level">
                            <li><a class="J_menuItem" href="${ctx }/sysUser?pageNo=1" data-index="0">用户管理</a></li>
                            <li><a class="J_menuItem" href="${ctx }/role?pageNo=1" data-index="0">角色管理</a></li>
                        </ul>
                    </li>
       
                
                    <li>
                        <a href="#">
                            <i class="fa fa-home"></i>
                            <span class="nav-label">组织结构</span>
                            <span class="fa arrow"></span>
                        </a>
                        <ul class="nav nav-second-level">
                            <li><a class="J_menuItem" href="${ctx }/pst?pageNo=1" data-index="0">职位管理</a></li>
                            <li><a class="J_menuItem" href="${ctx }/dept?pageNo=1" data-index="0">组织机构管理</a></li>
                        </ul>
                    </li>
					
					
					 <li>
                        <a href="#">
                            <i class="fa fa-home"></i>
                            <span class="nav-label">数据管理</span>
                            <span class="fa arrow"></span>
                        </a>
                        <ul class="nav nav-second-level">
                            <li><a class="J_menuItem" href="${ctx }/meta?pageNo=1" data-index="0">元数据管理</a></li>
                        </ul>
                        

                     </li>
                     
                     
                     <li>
                        <a href="#">
                            <i class="fa fa-home"></i>
                            <span class="nav-label">报表管理</span>
                            <span class="fa arrow"></span>
                        </a>
                        <ul class="nav nav-second-level">
                            <li><a class="J_menuItem" href="${ctx }/storSale" data-index="0">报表信息</a></li>
                             <li><a class="J_menuItem" href="${ctx }/pvuv" data-index="0">实时报表</a></li>
                        </ul>
                    </li>
                    
                    
                     <li>
                        <a href="#">
                            <i class="fa fa-home"></i>
                            <span class="nav-label">服务器资源</span>
                            <span class="fa arrow"></span>
                        </a>
                        <ul class="nav nav-second-level">
                            <li><a class="J_menuItem" href="${ctx }/srvr?pageNo=1" data-index="0">服务器资源管理</a></li>
                         	
                         	<c:if test="${qy:hasPermission('Link',0)}">
								<li><a class="J_menuItem" href="http://123.125.127.167:18060" data-index="0">实时监控</a></li>
							</c:if>
                        </ul>
                    </li>           
                    <li>
                        <a href="#">
                            <i class="fa fa-home"></i>
                            <span class="nav-label">任务管理</span>
                            <span class="fa arrow"></span>
                        </a>
                        <ul class="nav nav-second-level">
                            <li><a class="J_menuItem" href="${ctx }/sched" data-index="0">任务列表</a></li>
                   
                            <li><a class="J_menuItem" href="${ctx }/sch?pageNo=1" data-index="0">任务调度管理</a></li>
                        </ul>

                    </li>
                    -->
                    
                    