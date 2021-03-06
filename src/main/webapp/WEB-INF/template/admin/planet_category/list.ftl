﻿
<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">

    <title>${setting.siteName} - 星球类别表管理</title>
    <meta name="keywords" content="${setting.siteName}">
  	<meta name="description" content="${setting.siteName}">

  	<!-- BEGIN HEADER -->
		[#include "/admin/include/style.ftl"]
	<!-- END HEADER -->
</head>

<body class="gray-bg">
    <div class="wrapper wrapper-content  animated fadeInRight">

        <div class="row">
            <div class="col-sm-12">
                <div class="ibox float-e-margins">
                	<form id="listForm" action="list.jhtml" method="get">
                    <div class="ibox-title">
                    	 <h5>星球类别表管理 </h5>
                    	 <div class="ibox-tools">
                    	 	<a class="btn btn-outline btn-success btn-xs" id="btn-add-loippi" href="add.jhtml"><i class="fa fa-plus"></i> 新增</a>
	                        <a class="btn btn-outline btn-danger btn-xs btn-delete-loippi-group"><i class="fa fa-trash"></i> 删除</a>
	                    </div>
                    </div>
                    <div class="ibox-content">
                    	<div class="row">
					        <div class="col-sm-3 m-b-xs">
					        <input type="text" placeholder="请输入id" name="filter_id" value="${(paramter.id)!''}" class="input-sm form-control">
                            </div>
					        <div class="col-sm-3 m-b-xs">
					        <input type="text" placeholder="请输入中文名称" name="filter_nameZh" value="${(paramter.nameZh)!''}" class="input-sm form-control">
                            </div>
					        <div class="col-sm-3 m-b-xs">
					        <input type="text" placeholder="请输入英文名称" name="filter_nameEn" value="${(paramter.nameEn)!''}" class="input-sm form-control">
                            </div>
					       
					       <div class="col-sm-3 m-b-xs">
                                        <select name="filter_status" class="chosen-select">
                                        	<option value="">类别状态</option>
                                            <option value="1" [#if paramter.status=1]selected[/#if] >启用</option>
                                            <option value="2" [#if paramter.status=2]selected[/#if] >禁用</option>
                                        </select>
                            </div>
					        
					        <div class="col-sm-3 m-b-xs">
                                        <select name="filter_published" class="chosen-select">
                                        	<option value="">爬取状态</option>
                                            <option value="1" [#if paramter.published=1]selected[/#if] >已经爬取</option>
                                            <option value="2" [#if paramter.published=2]selected[/#if] >还未爬取</option>
                                        </select>
                            </div>
					        <div class="col-sm-3 m-b-xs">
                                        <div class="input-daterange input-group " id="datepicker">
                                            <input type="text" class="form-control " placeholder="请选择创建日期"
                                                   name="filter_createTimes" value="${(paramter.createTimes)!''}"/>
                                            <span class="input-group-addon">—</span>
                                            <input type="text" class="form-control  " placeholder="请选择创建日期"
                                                   name="filter_createTimee" value="${(paramter.createTimee)!''}"/>
                                        </div>
                             </div>
                                    
                            <div class="col-sm-1">
                                <div class="input-group">
                                     <button type="submit" class="btn btn-sm btn-primary"> 搜索</button> </span>
                                </div>
                            </div>
                        </div>
						   <div class="dataTables_wrapper form-inline">
                            <table class="table table-striped table-bordered table-hover dataTables-example dataTable">
                                <thead>
                                    <tr>
                                        <th><input type="checkbox"  class="i-checks" name="checkAll"></th>
                                        <th>id</th>
                                        <th>中文名称</th>
                                        <th>英文名称</th>
                                        <th>中文描述</th>
                                        <th>英文描述</th>
                                        <th>类别状态</th>
                                        <th>排序</th>
                                        <th>uuid</th>
                                        <th>是否已经爬取</th>
                                        <th>更新时间</th>
                                        <th>创建时间</th>
                                        <th>操作</th>
                                    </tr>
                                </thead>
                                <tbody>
                                	 [#list page.content as planetCategory] 
							        	 <tr>
					                        <td>
					                            <input type="checkbox" class="i-checks" name="ids" value="${planetCategory.id}">
					                        </td>
					                        <td>${(planetCategory.id)!''}</td>
					                        <td>${(planetCategory.nameZh)!''}</td>
					                        <td>${(planetCategory.nameEn)!''}</td>
					                        <td>${(planetCategory.describeZh)!''}</td>
					                        <td>${(planetCategory.describeEn)!''}</td>
					                        <td>
                                                    [#if planetCategory.status = 1]是[/#if]
                                                    [#if planetCategory.status = 2]否[/#if]
                                            </td>
					                        <td>${(planetCategory.sort)!''}</td>
					                        <td>${(planetCategory.uuid)!''}</td>
					                         <td>
                                                    [#if planetCategory.published = 1]是[/#if]
                                                    [#if planetCategory.published = 2]否[/#if]
                                            </td>
					                        <td>${(planetCategory.updateTime?string('yyyy-MM-dd HH:mm:ss'))!''}</td>
					                        <td>${(planetCategory.createTime?string('yyyy-MM-dd HH:mm:ss'))!''}</td>
					                        <td>
					                        	<!-- <a href="${base}/admin/planet_category/view/${planetCategory.id}.jhtml" class="btn btn-xs btn-default"> ${message("admin.common.view")} </a> -->
					                        	
					                        	<a class="btn btn-info btn-xs btn-edit-loippi" data-id="${planetCategory.id}" href="edit/${planetCategory.id}.jhtml" ><i class="fa fa-paste"></i> ${message("admin.common.edit")} </a>
					                        	
					                        	<a class="btn btn-danger btn-xs btn-delete-loippi" data-id="${planetCategory.id}" ><i class="fa fa-trash"></i> ${message("admin.common.delete")} </a>
					                        </td>
					                    </tr>
							        [/#list] 
                                </tbody>
                            </table>
                       		[@pagination pageNumber = page.pageNumber totalPages = page.totalPages]
								[#include "/admin/include/pagination.ftl"]
							[/@pagination]
                        </div>
                    </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
	
	<!-- BEGIN Script -->
		[#include "/admin/include/script.ftl"]
	<!-- END Script -->
    
    <script>
    
        $(document).ready(function () {
        	[@flash_message /]
        	 $(".chosen-select").chosen({width: "100%"});
            $(".input-daterange").datepicker({
            todayBtn : "linked",
            keyboardNavigation : false,
            keyboardNavigation:false,
            forceParse:false,
            autoclose:true});
        });
    </script>
</body>

</html>
