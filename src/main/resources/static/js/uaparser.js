(function() {
    console.log('Storing parsed User-Agent (Powered By https://github.com/maximthomas/uaparser-service)');
    var scriptElement = document.getElementById("uaparser");
    if(!scriptElement) {
        console.warn("Store User-Agent script does not exist");
        return;
    }
    var scriptSrc = scriptElement.getAttribute("src");
    var a = document.createElement("a");
    a.href = scriptSrc;

    var storeUrl = a.origin.concat("/store");

    var xhr = new XMLHttpRequest();
    xhr.open('GET', storeUrl, true);
    xhr.send();


    xhr.onerror = function() {
        console.warn( 'Error occurred while storing User-Agent' + this );
    }

})();
