/**
 * COOKIE HANDLER LIBRARY
 * @author: Paulo Leite
 * @ver: 0.1
 * 
 */

//Cookie Types Status  (special status cookies)
var GRA_ANALYTICS_COOKIE    	= "gra_analytics_cookie";
var GRA_COMARK_COOKIE 			= "gra_comark_cookie";
var GRA_DEFS_STATUS_COOKIE    	= "gra_defs_status_cookie";
var GRA_DEMANDED_COOKIES 	    = "gra_demanded_cookie";

//Cookies
var GRA_PRIVACY_POLICY_STATUS 	= "gra_privacy_policy_status";		//Stores privacy-policy status
var GRA_HIGH_CONTRAST_MODE	    = "gra_high_contrast_mode";		    //Stores hicontrast-view status
var GRA_TEXT_ONY_STATUS			= "gra_text_only_status";			//Stores text-only-view status
var GRA_TEXT_SIZE_STATUS        = "gra_text_size_status";			//Store text-size status
var GRA_LATITUDE_VALUE   		= "gra_latitude_value";				//Stores an latitude value
var GRA_LONGITUDE_VALUE			= "gra_longitude_value";			//Stores an longitude value
var GRA_DASHBOARD_STATE			= "gra_dashboard_state";			//Stores an longitude value

/**
 * Class to manipulate cookies
 * @class
 */
class CookieDo {
	
	/**
	 * Cookie Handler
	 * @constructor
	 */
	constructor(){	
		
		this.readValue = function(cname){
			var name = cname + "=";
			var decodedCookie = decodeURIComponent(document.cookie.toString());
			var ca = decodedCookie.split(';');
					for(var i = 0; i <ca.length; i++) {
					var c = ca[i];
								while (c.charAt(0) == ' ') {
									c = c.substring(1);
								}
						if (c.indexOf(name) == 0) {
							return c.substring(name.length, c.length);
						}
					}
			return '';
		}	
	}

	
	/**
	 * Sets a cookie value
	 * @param {cname} Name of the cookie
	 * @param {cvalue} Value of the cookie
	 * @param {exdays} Expiration days, set to 0 to delete cookie
	 */
	cookieInit() {
		 this.set(GRA_ANALYTICS_COOKIE, "true", 365);
		 this.set(GRA_COMARK_COOKIE, "true", 365);
		 this.set(GRA_DEFS_STATUS_COOKIE, "true", 365);
	}
	

	/**
	 * Sets a cookie value
	 * @param {cname} Name of the cookie
	 * @param {cvalue} Value of the cookie
	 * @param {exdays} Expiration days, set to 0 to delete cookie
	 */
	set(cname, cvalue, exdays) {
		var d = new Date();

		exdays != 0 ? d.setTime(d.getTime() + (exdays*24*60*60*1000)) : d.setTime(d.getTime() + (exdays*-24*-60*-60*-1000)) ;
		var expires = "expires="+ d.toUTCString();
		document.cookie = cname + "=" + cvalue + ";" + expires + ";path=/";	
	}
	
	/**
	 * Obtain a kookie value
	 * @param {cookieName} Name of the desired cookie
	 */
	valueOf(cookieName) {	  
		var readableCookie = this.readValue(cookieName);
		  if (readableCookie == '') {
		     return false;
		  } else {
		    if (readableCookie != '' && readableCookie != null) {
		    	return readableCookie;
		    }
		  }
	 }
	
	/**
	 * Reset definitions
	 * @param {cookieName} Name of the desired cookie
	 */
	resetSettings() {	  
		this.set(GRA_TEXT_ONY_STATUS, "false", 365);
		this.set(GRA_TEXT_SIZE_STATUS, "false", 365);
		this.set(GRA_TEXT_ONY_STATUS, "false", 365);
	 }
	
}

