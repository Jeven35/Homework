function getSessionID() {
	var form = new FormData();
	var result;
	$.ajaxSetup({
		async: false
	});
	$.ajax({
		url: '/getSessionID',
		type: "POST",
		data: form,
		processData: false,
		contentType: false,
		success: function(data, status) {

			if(data["isSuccess"]) {
				result = data["userid"]
			} else {
				result = null;
			}

		},
		error: function(XMLHttpRequest, textStatus,
			errorThrown) {
			console.log(errorThrown);
		}
	});
	$.ajaxSetup({
		async: true
	});
	return result;

}
G_layim = null;

function initLayIM() {
	var fifid = getSessionID();
	if(fifid == null) {
		return;
	}
	layui.use('layim', function(layim) {
		//基础配置
		layim.config({

			init: {
				url: '/queryForMainPane' //接口地址（返回的数据格式见下文）
					,
				type: 'get' //默认get，一般可不填
					,
				data: {} //额外参数
			} //获取主面板列表信息，下文会做进一步介绍

			//获取群员接口（返回的数据格式见下文）
			,
			members: {
				url: '/queryGroupMember' //接口地址（返回的数据格式见下文）
					,
				type: 'get' //默认get，一般可不填
					,
				data: {} //额外参数
			}

			//上传图片接口（返回的数据格式见下文），若不开启图片上传，剔除该项即可
			,
			uploadImage: {
				url: 'http://nvidia.njuftp.org/upload.aspx' //接口地址
					,
				type: 'post' //默认post
			}

			//上传文件接口（返回的数据格式见下文），若不开启文件上传，剔除该项即可
			,
			uploadFile: {
				url: '' //接口地址
					,
				type: 'post' //默认post
			}
			//扩展工具栏，下文会做进一步介绍（如果无需扩展，剔除该项即可）
			,
			tool: [{
					alias: 'code' //工具别名
						,
					title: '代码' //工具名称
						,
					icon: '&#xe64e;' //工具图标，参考图标文档
				}]

				,

			find: '/includeFindFriendPage' //发现页面地址，若不开启，剔除该项即可

		});
	});

}