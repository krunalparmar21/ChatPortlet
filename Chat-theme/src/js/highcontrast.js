/**
 * HICONTRAST HANDLER LIBRARY
 * @author: Paulo Leite
 * @ver: 0.1
 * 
 */

/**
 * Class to handle HighContrast
 * @class
 */
class HighContrast {
	
//    constructor(targetTag) { 
//		this.contrastSetBlack = '<style>' + targetTag + '{filter: grayscale(100%) contrast(130%) invert(100%);} ' + targetTag + ' img{filter: invert(100%);} .az-portal-no-contrast{filter:invert(100%)!important;} .az-portal-highContrast{filter:invert(0%)!important;}<style>';
//		this.contrastSetWhite = '<style>' + targetTag + '{filter: grayscale(100%) contrast(150%);} ' + targetTag + ' img{filter: invert(0%);} ' + targetTag + '.az-portal-no-contrast{filter:invert(0%)!important;} .az-portal-highContrast{filter:invert(0%)!important;}<style>';
//		this.contrastSetColor = '<style>' + targetTag + '{filter: grayscale(0%) contrast(130%) invert(100%);} ' + targetTag + ' img{filter: invert(100%);} .az-portal-no-contrast{filter:invert(100%)!important;} .az-portal-highContrast{filter:invert(0%)!important;}<style>';
//		this.contrastUnset = '<style>' + targetTag + '{filter: grayscale(0%) contrast(100%) invert(0%);} ' + targetTag + ' img{filter: invert(0%)} .az-portal-no-contrast{filter:invert(0%)!important;}' + targetTag + '.az-portal-highContrast{filter:invert(100%)!important;} <style>';
//	}
//
//	set(enable, type) {
//		if (enable) switch(type) {
//			case 'B': // Black on white
//				$(document.head).append(this.contrastSetBlack);
//				break;
//			case 'C': // Black on white
//				$(document.head).append(this.contrastSetColor);
//				break;
//			case 'W': // White on black
//				$(document.head).append(this.contrastSetWhite);
//				break;
//			default: // No contrast
//				$(document.head).append(this.contrastUnset);
//				break;
//		}
//		
//		else $(document.head).append(this.contrastUnset);
//	}
	
	constructor() { 
		this.contrastBlack = 'grayscale(100%) contrast(130%) invert(100%)';
		this.contrastBlackImg = 'invert(100%)';
		this.contrastBlackAz = 'invert(0%)'
		
		this.contrastColor = 'grayscale(0%) contrast(130%) invert(100%)';
		this.contrastColorImg = 'invert(100%)';
		this.contrastColorAz= 'invert(0%)';
		
		this.contrastWhite = 'grayscale(100%) contrast(150%)';
		this.contrastWhiteImg = 'invert(0%)';
		this.contrastWhiteAz = 'invert(0%)';
		
		this.contrastUnset = 'grayscale(0%) contrast(100%) invert(0%)';
		this.contrastUnsetImg = 'invert(0%)';
		this.contrastUnsetAz = 'invert(100%)';
	}
	
	set(enable, type) {
		if (enable) switch(type) {
			case 'B': // Black on white
				this.setFilter(this.contrastBlack, this.contrastBlackImg, this.contrastBlackAz);
				break;
			case 'C': // Color
				this.setFilter(this.contrastColor, this.contrastColorImg, this.contrastColorAz);
				break;
			case 'W': // White on black
				this.setFilter(this.contrastWhite, this.contrastWhiteImg, this.contrastWhiteAz);
				break;
			default: // No contrast
				this.setFilter(this.contrastUnset, this.contrastUnsetImg, this.contrastUnsetAz);
				break;
		}
		
		else this.setFilter(this.contrastUnset, this.contrastUnsetImg, this.contrastUnsetAz);
	}
	
	setFilter(filter, filterImg, filterAz) {
		$('.template-normal--header-menus').css('filter', filter);
		$('.template-normal--user-notification').css('filter', filter);
		$('.template-normal--user-dash').css('filter', filter);
		$('.template-normal--scroll-image').css('filter', filter);
		$('.template-normal--footer-wrap').css('filter', filter);
		$('#content').css('filter', filter);
		
		$('img').css('filter', filterImg);
		$('.az-portal-no-contrast').css('filter', filterImg);
	}
}
