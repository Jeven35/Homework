var fs = require("fs");

setImmediate(function(){
    fs.writeFile("readme.txt","you read me!",function(){

    });
})