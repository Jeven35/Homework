// 传入一个方法 用来调用接口
var applyTokenDo = function(func) {
    var client = new OSS({
        region: "oss-cn-hangzhou",
        accessKeyId: "LTAIT9uPRUVswRb0",
        accessKeySecret: "Li3tbj1my5zqmXAMaCAAzkqfqeCL1o",
        bucket: "vediodemo"
    });
    return func(client);
};

var tempCheckPoint;
//进度条
var progress = function(p, checkpoint) {
    console.log(p);
    tempCheckPoint = checkpoint;
    var bar = document.getElementById('progress-bar');
    bar.style.width = ""+Math.floor(p * 100) + '%';
    bar.innerHTML = ""+Math.floor(p * 100) + '%';
	return function(done) {
		done();
	}
};


//上传文件
var uploadFile = function(client) {
	var file = document.getElementById('file').files[0];
	if(document.getElementById('filename').value == null || document.getElementById('filename').value.trim().length == 0) {
		alert("文件名不能为空");
		return;
	}
	var fileName = document.getElementById('filename').value.trim() || 'object';
	if(document.getElementById('file').files.length == 0) {
		alert("请选择文件！");
		return;
	}
	var suffix = file.name.substr(file.name.lastIndexOf(".")).toLowerCase(); //获得文件后缀名
	console.log(suffix);
    return client.multipartUpload(fileName, file, {
        progress: progress,
        checkpoint: tempCheckPoint,
        parallel: 100,
    }).then(function(res) {
        console.log('upload success: %j', res);
        location.reload();
    });

};


//下载文件
var downloadFile = function(client) {
	var object = getNowUserID();
	var filename = getFileNameByID_save('self');
	console.log(filename)

	var result = client.signatureUrl(object, {
		response: {
			'content-disposition': 'attachment; filename="' + filename + '"'
		}
	});
	window.location = result;

	return result;
};



function download(url_arr) {
	var triggerDelay = 600;
	var removeDelay = 10000;
	//存放多个下载的url，
	$('.standardload').each(function() {
		url_arr.push($(this).attr('href'));
	})

	url_arr.forEach(function(item, index) {
		_createIFrame(item, index * triggerDelay, removeDelay);
	})

	function _createIFrame(url, triggerDelay, removeDelay) {
		//动态添加iframe，设置src，然后删除
		setTimeout(function() {
			var frame = $('<iframe style="display: none;" class="multi-download"></iframe>');
			frame.attr('src', url);
			$(document.body).after(frame);
			setTimeout(function() {
				frame.remove();
			}, removeDelay);
		}, triggerDelay);
	}
}
