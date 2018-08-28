
<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">

    <title>${setting.siteName} - 主页</title>
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
                    	 <h5>管理员列表 </h5>
                    	 <div class="ibox-tools">
                    	 	<a class="btn btn-outline btn-success btn-xs" id="btn-add-loippi" href="add.jhtml"><i class="fa fa-plus"></i> 新增</a>
	                        <a class="btn btn-outline btn-danger btn-xs btn-delete-loippi-group"><i class="fa fa-trash"></i> 删除</a>
	                    </div>
                    </div>
                    <div class="ibox-content">
						   <div class="dataTables_wrapper form-inline">
                            <table class="table table-striped table-bordered table-hover dataTables-example dataTable">
                                <thead>
                                    <tr>

                                        <th><input type="checkbox"  class="i-checks" name="checkAll"></th>
                                        <th>管理员账号</th>
                                        <th>姓名</th>
                                        <th>角色</th>
                                        <th>手机号</th>
                                        <th>上次登录时间</th>
                                        <th>操作</th>
                                    </tr>
                                </thead>

                                <tbody>
                                	 [#list page.content as user] 
							        	 <tr>
						                        <td>
						                            <input type="checkbox" class="i-checks" name="ids" value="${user.id}">
						                        </td>
						                        <td>${user.userName!''}</td>
						                        <td>${user.nickname!''}</td>
						                        <td>${(user.role.roleName)!''}</td>
						                        <td>${user.phone!''}</td>
						                        <td>${(user.loginDate?string("yyyy-MM-dd HH:mm:ss"))!''}</td>
						                        <td>
						                        	<a class="btn btn-info btn-xs btn-edit-loippi" data-id="${user.id}" href="edit.jhtml?id=${user.id}" ><i class="fa fa-paste"></i> 编辑</a>
                                                    [#if user.isEnabled]
                                                        <input onclick="forbidden(userid=${user.id})"
                                                               class="btn btn-danger btn-xs" type="button" value="禁用">
                                                    [#else]
                                                        <input onclick="startUsing(userid=${user.id})"
                                                               class="btn btn-info btn-xs" type="button" value="启用">
                                                    [/#if]
                                                [#--<a class="btn btn-danger btn-xs btn-delete-loippi" data-id="${user.id}" ><i class="fa fa-trash"></i> 删除</a>--]
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
        });

        function forbidden() {
            layer.confirm('确定禁用该账号吗？', {
                shade: false //不显示遮罩
            }, function () {
                $.ajax({
                    type: 'post',//提交方式，，post get...
                    dataType: "json",//数据传输格式
                    url: "${base}/admin/user/forbiddenUser.json",//访问服务器后台的url
                    data: {
                        id: userid
                    },//数据可以写{'age':10,'name':'aaa'}方式
                    success: function (message) {//返回成功后执行的函数，result是返回的数据
                        layer.msg('禁用成功', {icon: 1});
                        setTimeout('location.reload()', 1000);
                    },
                    error: function (message) {
                        layer.msg('禁用失败', {icon: 1});
                    }

                });
            });
        }
        function startUsing() {
            layer.confirm('确定启用该账号吗？', {
                shade: false //不显示遮罩
            }, function () {
                $.ajax({
                    type: 'post',//提交方式，，post get...
                    dataType: "json",//数据传输格式
                    url: "${base}/admin/user/startUsingUser.json",//访问服务器后台的url
                    data: {
                        id: userid
                    },//数据可以写{'age':10,'name':'aaa'}方式
                    success: function (message) {//返回成功后执行的函数，result是返回的数据
                        layer.msg('启用成功', {icon: 1});
                        setTimeout('location.reload()', 1000);
                    },
                    error: function (message) {
                        layer.msg('启用失败', {icon: 1});
                    }

                });

            });
        }
    </script>
</body>

</html>
