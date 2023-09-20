/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

AUI().ready('liferay-sign-in-modal', function(A) {
	var signIn = A.one('.sign-in > a');

	if (signIn && signIn.getData('redirect') !== 'true') {
		signIn.plug(Liferay.SignInModal);
	}
});

/* --- check date inputs --- */
if ( $('[type="date"]').prop('type') != 'date' ) {
	var datelang = "pt";
	if (themeDisplay.getLanguageId() == "en_US") datelang="en";
	$('[type="date"]').datepicker($.datepicker.regional[ datelang ]);
    $('[type="date"]').datepicker("option", "dateFormat", "yy-mm-dd");
}

/*Lat long of capital city of Island*/
//[37.7415,-25.6677] - Ponta Delgada - S. Miguel
//[36.9563,-25.1409] - Vila do Porto - S. Maria
//[38.6635,-27.2294] - Angra do Heroísmo - Terceira
//[39.0800,-28.0000] - Santa Cruz da Graciosa - Graciosa
//[38.6842,-28.2133] - Velas - S. Jorge
//[38.5325,-28.5237] - Madalena - Pico
//[38.5363,-28.6315] - Horta - Faial
//[39.4500,-31.1300] - Santa Cruz das Flores - Flores
//[39.6700,-31.1200] - Vila do Corvo - Corvo
var ISLAND_CEPITAL_LAT_LONG_ARRAY = [[37.7415,-25.6677], [36.9563,-25.1409], [38.6635,-27.2294], [39.0800,-28.0000], [38.6842,-28.2133], [38.5325,-28.5237], [38.5363,-28.6315], [39.4500,-31.1300], [39.6700,-31.1200]];
var ISLAND_CEPITAL_LAT_LONG_MAP = new Map(ISLAND_CEPITAL_LAT_LONG_ARRAY);

/* --- Get the nearest capital city of Island --- Start ---- */
function getNearestCapitalLatLong(currentPos) {
	if(currentPos == undefined) {
		console.log("Map current postion is not found returning default");
		currentPos = {
			lat: map.center.lat(),
			lng: map.center.lng()
		};
	}
	
	var distanceMap = new Map();
	ISLAND_CEPITAL_LAT_LONG_ARRAY.forEach(function (latlng, index) {
		distanceMap.set(latlng[0],getDistanceFromLatLonInKm(latlng[0],latlng[1],currentPos.lat,currentPos.lng));
	});
	var sortedMap = new Map([...distanceMap.entries()].sort((a, b) => a[1] - b[1]));
	return {lat:sortedMap.keys().next().value, lng:ISLAND_CEPITAL_LAT_LONG_MAP.get(sortedMap.keys().next().value)};
}

function getDistanceFromLatLonInKm(lat1,lon1,lat2,lon2) {
	var R = 6371; // Radius of the earth in km
	var dLat = deg2rad(lat2-lat1);  // deg2rad below
	var dLon = deg2rad(lon2-lon1); 
	var a = 
		Math.sin(dLat/2) * Math.sin(dLat/2) +
		Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * 
		Math.sin(dLon/2) * Math.sin(dLon/2); 
	var c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a)); 
	var d = R * c; // Distance in km
	return d;
}

function deg2rad(deg) {
	return deg * (Math.PI/180)
}

/* --- Get the nearest capital city of Island --- End ---- */
