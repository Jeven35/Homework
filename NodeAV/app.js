var fs = require("fs");

setImmediate(function cbImmediate(){
    fs.exists("readMe.txt",function cbExist(exist){
        if(exist){
            fs.readFile("readMe.txt","utf8",function read(err,data){
                console.log(data);
            })
        }
    })
})

setTimeout(function cdTimeout(){
    fs.unlink("readMe.txt",function(err){
        if(err){
            throw(err);
        }

        console.log("文件删除成功")
    });
},5);