
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
                    <div class="ibox-title">
                    	 <h5>添加管理员</h5>
                    	 <div class="ibox-tools">
                    	 	<button type="button" class="btn btn-outline btn-white btn-xs" id="btn-return-loippi" 
                    	 	onclick="location.href='list.jhtml'"><i class="fa fa-reply-all"></i> 返回列表</button>
                        </div>
                    </div>            	
                  	<div class="ibox-content">
                        <form id="saveAclForm" class="form-horizontal" action="${base}/admin/user/save.jhtml" method="post">
                            <div class="form-group">
                                <div class="row">
                                    <div class="col-sm-6">
                                        <label class="col-sm-3 control-label">用户名</label>
                                        <div class="col-sm-9">
                                            <input type="text" name="userName" id="userName" class="form-control">
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="row">
                                    <div class="col-sm-6">
                                        <label class="col-sm-3 control-label">密码</label>
                                        <div class="col-sm-9">
                                            <input type="password" name="password" id="password" class="form-control">
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="row">
                                    <div class="col-sm-6">
                                        <label class="col-sm-3 control-label">重复密码</label>
                                        <div class="col-sm-9">
                                            <input type="password" name="repassword" id="repassword" class="form-control">
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="row">
                                    <div class="col-sm-6">
                                        <label class="col-sm-3 control-label">昵称</label>
                                        <div class="col-sm-9">
                                            <input type="text" name="nickname" id="nickname" class="form-control">
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="row">
                                [#-- <div class="col-sm-6">
                                     <label class="col-sm-3 control-label">头像</label>
                                     <div class="col-sm-9">
                                         <div class="input-group">
                                             <input type="text" class="form-control" name="avatar" id="avatar" value="${user.avatar}">
                                             <span class="input-group-btn">
                                             <button class="btn btn-white" type="button" id="browserButton"><i class="fa fa-send-o"></i>&nbsp;选择</button>
                                         </span>
                                         </div>
                                     </div>
                                 </div>--]
                                    <div class="col-sm-6">
                                        <label class="col-sm-3 control-label">角色</label>
                                        <div class="col-sm-9">
                                            <select name="roleId" id="roleId" data-placeholder="选择角色..." class="chosen-select"  style="width:350px;" >
                                            [#list roles as role]
                                                <option  hassubinfo="true" value="${role.id}">${role.roleName}</option>
                                            [/#list]
                                            </select>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="row">
                                    <div class="col-sm-6">
                                        <label class="col-sm-3 control-label">手机</label>
                                        <div class="col-sm-9">
                                            <input type="text" name="phone" id="phone" class="form-control" ">
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="row">
                                    <div class="col-sm-6">
                                        <label class="col-sm-3 control-label">邮箱</label>
                                        <div class="col-sm-9">
                                            <input type="text" name="email" id="email" class="form-control"">
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="row">
                                    <div class="col-sm-6">
                                        <label class="col-sm-3 control-label">备注</label>
                                        <div class="col-sm-9">
                                        [#--<input type="password" name="password" id="password" class="form-control">--]
                                            <textarea name="remark" class="form-control" style="text-align: left">

											</textarea>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        [#--[#if user.username != 'admin']
                         <div class="form-group">
                       		<div class="row">
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">客服功能</label>
                       			 	 <div class="col-sm-9">
	                                    <div class="checkbox-inline i-checks">
	                                       <input type="checkbox" class="i-checks" name="servicer" value="true" [#if user.servicer == true]checked[/#if]>
	                                    </div>
	                                </div>

                       			</div>
                       		</div>
                        </div>
                        [/#if]--]
                            <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <div class="col-sm-12 col-sm-offset-10">
                                    <button type="button" class="btn btn-default" onclick="window.history.back();">返回</button>
                                    <button class="btn btn-success" type="button" onclick="save()">保存内容</button>
                                </div>
                            </div>
                        </form>
                  	</div>
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
        	var $browserButton = $("#browserButton");
			$browserButton.browser({input:$("#avatar")});
        	$(".chosen-select").chosen({width:"100%"});

            jQuery.validator.addMethod("checkPhone", function(value, element) {
                var length = value.length;
                var mobile = /^(13[0-9]{9})|(18[0-9]{9})|(14[0-9]{9})|(17[0-9]{9})|(15[0-9]{9})$/;
                return this.optional(element) || (length == 11 && mobile.test(value));
            }, "请正确填写您的手机号码");

        	$('.i-checks').iCheck({ checkboxClass: 'icheckbox_square-green',  radioClass: 'iradio_square-green',  });
        	jQuery("#saveAclForm").validate({
			                rules: {
			                   userName: {
									required: true,
									pattern: /^[0-9a-z_A-Z\u4e00-\u9fa5]+$/,
									minlength: 2,
									maxlength: 20,
									remote: {
										url: "check_username.jhtml",
										cache: false
									}
								},
								password: {
									required: true,
									pattern: /^[^\s&\"<>]+$/,
									minlength: 8,
									maxlength: 20
								},
								repassword: {
									required: true,
									equalTo: "#password"
								},
								email:{
									required: true,
									email :true
								},
                                phone:{
                                    required: true,
                                    checkPhone : true
                                },
                                nickname:{
                                    required: true
                                },
								roleId: "required"
			                },
			                messages: {
								userName: {
									pattern: "${message("admin.validate.illegal")}",
									remote: "${message("admin.validate.exist")}"
								},
								password: {
									pattern: "${message("admin.validate.illegal")}"
								},
                                phone : {
                                    required : "请输入手机号",
                                    minlength : "确认手机不能小于11个字符",
                                    isMobile : "请正确填写您的手机号码"
                                }
							},
				errorPlacement: function (error, element) {
                    error.appendTo(element.parent());
                }
			            });
        });
        function save() {
            $.ajax({
                type: "post",
                url : "${base}/admin/user/save.json",
                dataType:'json',
                data: {
                    "userName":$(" input[ name='userName' ] ").val(),
                    "password":$(" input[ name='password' ] ").val(),
                    "email":$(" input[ name='email' ] ").val(),
                    "phone":$(" input[ name='phone' ] ").val(),
                    "nickname":$(" input[ name='nickname' ] ").val(),
                    "remark":$(" input[ name='remark' ] ").val(),
                    "roleId":$("#roleId").val()
                },
                success: function(message) {//返回成功后执行的函数，result是返回的数据
                    if(message.code==400){
                        layer.msg('用户已存在', {icon: 1});
                    }else {
                        layer.msg('操作成功', {icon: 1});
                        window.setTimeout(function () {
                            window.location.href="${base}/admin/user/list.jhtml";
                        }, 1000);
                    }

                },
                error:function(message){
                    layer.msg('操作失败', {icon: 1});
                }
            });
        }
    </script>
</body>

</html>
