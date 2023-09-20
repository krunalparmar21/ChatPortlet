navigator.serviceWorker.register('/o/portal.theme.default/js/firebase-messaging-sw.js')
.then((registration) => {
  
	// Your web app's Firebase configuration
	// TODO replace here
	var firebaseConfig = {
	    apiKey: "AIzaSyCVDKe3VW5zZpctrU0GC3lRt5wjmncUknU",
	    authDomain: "portalgra.firebaseapp.com",
	    databaseURL: "https://portalgra.firebaseio.com",
	    projectId: "portalgra",
	    storageBucket: "portalgra.appspot.com",
	    messagingSenderId: "184856146742",
	    appId: "1:184856146742:web:2a5f0bfddc2f8cba653997",
	    measurementId: "G-9GFSW90DJQ"
	};
	  
	// Initialize Firebase
	firebase.initializeApp(firebaseConfig);
	const messaging = firebase.messaging();

	messaging.useServiceWorker(registration);

	// Let's check if the browser supports notifications
	if (!("Notification" in window)) {
		console.log("This browser does not support desktop notification");
	}
	//Let's check whether notification permissions have already been granted
	else if (Notification.permission === "denied" || Notification.permission === "default") {
		// Otherwise, we need to ask the user for permission
	    Notification.requestPermission().then(function (permission) {
	    	// If the user accepts, let's create a notification
	    	if (permission === "granted") {
	    		messaging.getToken().then(function(token){
	    			ajaxCallAddPushToken(token);
	    		});
	    	}
	    });
	}
	
	// ajax call to rest api for add token in database  /o/push-notification-rest/add-push-token
	var ajaxCallAddPushToken = function(token){
		$.ajax({
    		url: "/o/push-notification-rest/add-push-token",
    		type: "POST",
    		dataType: "json",
    		data:{
    			token: token,
    			userId: themeDisplay.getUserId(),
    			languageId: themeDisplay.getLanguageId()
    		},
    		success: function(data){
    			console.log(data);
    		},
    		error: function(data){
    			console.log(data);
    		}
    	});
	};
	
	if ('permissions' in navigator) {
		navigator.permissions.query({ name: 'notifications' }).then(function (notificationPerm) {
			notificationPerm.onchange = function () {
				if(notificationPerm.state=="granted"){
					messaging.getToken().then(function(token){
						ajaxCallAddPushToken(token);
					});
				}
		    };
		});
	}

	messaging.onMessage(function(payload){
		var title = "New Article";
		var body = "New Article";
		var icon = "";
		var tagName = "notification-tag";
		var redirect = "";
		
		try{
			if(payload.data){
				tagName = payload.data.articleId ? payload.data.articleId : tagName;
				title = payload.data.ptTitle ? payload.data.ptTitle : title;
				body = payload.data.ptBody ? payload.data.ptBody : body;
				icon = payload.data.ptIcon ? payload.data.ptIcon : icon;
				redirect = payload.data.ptRedirect ? payload.data.ptRedirect : redirect;
				
				if("en_US" == themeDisplay.getLanguageId()){
					title = payload.data.enTitle ? payload.data.enTitle : title;
					body = payload.data.enBody ? payload.data.enBody : body;
					icon = payload.data.enIcon ? payload.data.enIcon : icon;
					redirect = payload.data.enRedirect ? payload.data.enRedirect : redirect;
				}
			}
		}catch(er){ console.error(er); }
		
		const notificationTitle = title;
		const notificationOptions = {
				body: body,
				icon: icon,
				tag: tagName
		};
		var notification = new Notification(notificationTitle,notificationOptions);
		
		notification.onclick = function(){ 
			if(redirect){
				window.open(redirect);	
			}
		}
	});
});
