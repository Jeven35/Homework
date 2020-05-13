var fs = require("fs");

fs.unlink("callback.txt",function(err,data) {
    if(err){
        console.log(err);
    }
    else{
        console.log("delete callback.txt ok");
    }
});
fs.unlink("track.txt",function(err,data) {
    if(err){
        console.log(err);
    }
    else{
        console.log("delete track.txt ok");
    }
    
})