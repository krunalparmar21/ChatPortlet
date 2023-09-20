function getSocketEndPoint(endPointPath) {
	var hostName = window.location.host;
	hostName += endPointPath;
	var protocol = window.location.protocol;
	var socketURL;
	if(protocol === 'http:'){
		socketURL = "ws://";
	} else {
		socketURL = "wss://";
	}
	socketURL += hostName;
	return socketURL;
}