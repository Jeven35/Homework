var CourseID,fileName,reName;

var set = function (var1,var2,var3) {
    CourseID = var1;
    fileName = var2;
    reName = var3;
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
    var CourseID = "";
    $.ajax({
        async: false,
        type: "GET",
        url: "/teacher/getCourseID",
        success: function (data) {
            CourseID = data;
        }
    });
    fileName = CourseID+"/"+fileName+suffix;
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
    console.log(fileName,CourseID,reName);
    var suffix = fileName.substr(fileName.lastIndexOf(".")).toLowerCase();

    var object = CourseID + "/"+reName+suffix;
	var filename = reName+suffix;
	console.log(filename);

	var result = client.signatureUrl(object, {
		response: {
			'content-disposition': 'attachment; filename="' + filename + '"'
		}
	});
	window.location = result;

	return result;
};