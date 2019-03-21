var uploadName,downloadName;

// 设置上传时的名字
setUploadName = function (var1) {
    uploadName = var1;
};
// 设置下载时的名字
setDownloadName = function (var1) {
    downloadName = var1;
};

// 传入一个方法 用来调用接口
var applyTokenDo = function(func) {
    $.ajax({
        async: false,
        cache: false,
        type: 'GET',
        url: '/getSTS',

        error: function() {
            alert('STS请求失败');
        },
        success: function(data) {
            var client = new OSS({
                region: data.region,
                accessKeyId: data.accessKeyId,
                accessKeySecret: data.accessKeyIdSecret,
                stsToken: data.stsToken,
                bucket: data.bucket
            });
            return func(client);
        }
    });

};

var tempCheckPoint;
//进度条
var progress = function(p, checkpoint)  {
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
    return client.multipartUpload(uploadName, file, {
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

	var result = client.signatureUrl(uploadName, {
		response: {
			'content-disposition': 'attachment; filename="' + downloadName + '"'
		}
	});
	window.location = result;

	return result;
};