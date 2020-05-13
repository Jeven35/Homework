var async_hooks = require('async_hooks');
var fs = require("fs");
var index_objId = 1;
(function() {
    function showLocation(iid) {
        var id = J$.getGlobalIID(iid);
    };


    /*
        asyncId:当前资源的ID
        triggerId:谁触发了当前的事件
        async_hooks.executionAsyncId()：返回当前执行事件的ID
        async_hooks.triggerAsyncId()：返回触发事件的ID
         
    */
    var hooks = {
        init:init
    }
    var asyncHooks = async_hooks.createHook(hooks)
    asyncHooks.enable()
    function init(asyncId, type, triggerId,resource) {
        var currentId = async_hooks.executionAsyncId();
        // don't want the initial start TCPWRAP
        if (currentId === 1 && type === 'TCPWRAP') return
        if (triggerId === 0) return
        writeSpan(asyncId,type,triggerId,currentId);
    }

    function writeSpan (id, type, parent,currentId) {
        fs.appendFileSync("track.txt",currentId+" ");
        fs.appendFileSync("track.txt",parent+" ");
        fs.appendFileSync("track.txt",id+" "); 
        fs.appendFileSync("track.txt",`${type} \n`);
    }
    
    




    // 调用jalangi分析资源

    /*
        currentId: 当前执行事件的Id
        scopeId：静态范围标识符
        name：访问对象名称
        type:读还是写(0表示读，1表示写)
        location：在代码里面的位置
    */

    function writeCallback(currentId,scopeId,name,type,location) {
        fs.appendFileSync("callback.txt",currentId+" ");
        fs.appendFileSync("callback.txt",scopeId+" ");
        fs.appendFileSync("callback.txt",name+" ");
        fs.appendFileSync("callback.txt",type+" ");
        fs.appendFileSync("callback.txt",`${location} \n`);
    }
    J$.analysis = {

        // 读写变量
        read: function(iid, name, val, isGlobal,isScriptLocal){
            var id = J$.getGlobalIID(iid);
            var location = J$.iidToLocation(id);
            var currentId = async_hooks.executionAsyncId();

            var scopeId = "var";
            if(isGlobal){
                scopeId = scopeId+"1";
            }
            else{
                scopeId = scopeId+"0";
            }
            
            if(isScriptLocal){
                scopeId = scopeId+"1";
            }
            else{
                scopeId = scopeId+"0";
            }
            if(name != "console" && name != "setTimeout"){
                writeCallback(currentId,scopeId,name,0,location);
            }
         
        },
        write: function(iid, name, val, lhs, isGlobal, isScriptLocal) {
            var id = J$.getGlobalIID(iid);
            var location = J$.iidToLocation(id);
            var currentId = async_hooks.executionAsyncId();
            var scopeId = "var";
            if(isGlobal){
                scopeId = scopeId+"1";
            }
            else{
                scopeId = scopeId+"0";
            }
            
            if(isScriptLocal){
                scopeId = scopeId+"1";
            }
            else{
                scopeId = scopeId+"0";
            }

            writeCallback(currentId,scopeId,name,1,location);
            
        },



        // 读写对象
        putField: function(iid, base, offset, val, isComputed, isOpAssign) {
            var id = J$.getGlobalIID(iid);
            var location = J$.iidToLocation(id);
            var currentId = async_hooks.executionAsyncId();
            writeCallback(currentId,base.objId,offset,1,location);
        },

        putFieldPre: function(iid, base, offset, val, isComputed, isOpAssign) {
            if(base.objId == undefined){
                base.objId = index_objId;
                index_objId++;
            }


        },




        getFieldPre: function (iid, base, offset, isComputed, isOpAssign, isMethodCall) {
            if(base.objId == undefined){
                base.objId = index_objId;
                index_objId++;
            }        
        },
        getField: function(iid, base, offset, val, isComputed, isOpAssign, isMethodCall) {
            
            var id = J$.getGlobalIID(iid);
            var location = J$.iidToLocation(id);
            var currentId = async_hooks.executionAsyncId();
            writeCallback(currentId,base.objId,offset,0,location);

        },
        declare: function (iid, name, val, isArgument, argumentIndex, isCatchParam) {
            
        },

        invokeFunPre: function(iid, f, base, args, isConstructor, isMethod, functionIid) {
            var id = J$.getGlobalIID(iid);
            var location = J$.iidToLocation(id);
            var currentId = async_hooks.executionAsyncId();

            var functionName = f.name;

            var fileLocation;

            if(functionName == "writeFileSync" || functionName=="writeFile" || functionName=="appendFile    "){
                fileLocation = args[0];
                writeCallback(currentId,fileLocation,"",1,location);
            }
            if(functionName == "readFile" || functionName=="readFileSync"){
                fileLocation = args[0];
                writeCallback(currentId,fileLocation,"",0,location);
            }


        }



    };

    
}())