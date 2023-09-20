window.onscroll = function() { scrollFunction() };

function scrollFunction() {
	var scrollUpButton = document.getElementsByClassName('template-normal--scroll-image')[0];
	
	if (document.body.scrollTop > 1000 || document.documentElement.scrollTop > 1000) scrollUpButton.style.display = 'block';
	else scrollUpButton.style.display = 'none';
}

function scrollUp() {
	window.scrollTo({ top: 0, behavior: 'smooth' });
}