function loadIframe() {
    //隐藏加载动画
    document.getElementById("loading").style.display = "none";
    document.getElementById("iframepage").style.display = "block";
    //调整高度
    var ifm = document.getElementById("iframepage");
    var subWeb = document.frames ? document.frames["iframepage"].document : ifm.contentDocument;
    if(ifm != null && subWeb != null) {
        ifm.height = document.body.scrollHeight - 92;
        ifm.width = document.body.scrollWidth - 60;
    }
}

function jumpIframe(page) {
    //加载动画
    document.getElementById("loading").style.display = "block";
    document.getElementById("iframepage").style.display = "none";
    //加载
    var ifm = document.getElementById("iframepage");
    ifm.src = page;
}