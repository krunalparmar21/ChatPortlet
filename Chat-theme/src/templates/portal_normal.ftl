<!DOCTYPE html>

<#include init />

<link href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">

<html class="${root_css_class}" dir="<@liferay.language key="lang.dir" />"lang="${w3c_language_id}">
	
		<head>
		
			<title>${the_title} - ${company_name}</title>
			
	    	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	    	
			<@liferay_util["include"] page=top_head_include />
			
			<@liferay.js file_name="${themeDisplay.getPathThemeJavaScript()}/core.js" />
			<@liferay.js file_name="${themeDisplay.getPathThemeJavaScript()}/pager.js" />
			<@liferay.js file_name="${themeDisplay.getPathThemeJavaScript()}/rollup.js" />
			<@liferay.js file_name="${themeDisplay.getPathThemeJavaScript()}/search.js" />
			<@liferay.js file_name="${themeDisplay.getPathThemeJavaScript()}/joraa.js" />
			<@liferay.js file_name="${themeDisplay.getPathThemeJavaScript()}/handlebars.js" />
			<@liferay.js file_name="${themeDisplay.getPathThemeJavaScript()}/riac.js" />
			<@liferay.js file_name="${themeDisplay.getPathThemeJavaScript()}/list.js" />
		
			<@liferay.js file_name="${themeDisplay.getPathThemeJavaScript()}/cookiedo.js" />
		    <@liferay.js file_name="${themeDisplay.getPathThemeJavaScript()}/highcontrast.js" />
		    <@liferay.js file_name="https://unpkg.com/masonry-layout@4/dist/masonry.pkgd.min.js" />
		        
 		    <@liferay.js file_name="${themeDisplay.getPathThemeJavaScript()}/maps.js" />
		    
		    <!-- Google Firebase messaging -->
		    <@liferay.js file_name="https://www.gstatic.com/firebasejs/3.9.0/firebase-app.js" />
		    <@liferay.js file_name="https://www.gstatic.com/firebasejs/3.9.0/firebase-messaging.js" />
		    <@liferay.js file_name="${themeDisplay.getPathThemeJavaScript()}/firebase-messaging.js" />
			<meta property="og:image" content="${themeDisplay.getPathThemeImages()}/default-news.jpg" />
		   
		</head>

		<body class="${css_class}">
		<h1 class="sr-only" role="heading" aria-level="1">${the_title}</h1>
		<a class="sr-only sr-only-focusable" href="#content">Skip to main content</a>
		
		<!-- <@liferay_ui["quick-access"] contentId="#main-content" /> -->
		<@liferay_util["include"] page=body_top_include />
		<@liferay.control_menu />
		
		<div class="pt-0" id="wrapper">
		
			<#-- This is an overlay in main template for canvas effects,  This overlay does not effect mouse click on under position elements  -->
			<div class="template-normal--overlay" >
				<div class="back-row-toggle splat-toggle">
				  <div class="rain front-row"></div>
				  <div class="rain back-row"></div>
				</div>
			</div>
			<div class="template-normal--loader-overlay" ></div>
			<#-- End of overlay -->
		
		
		<div class="container no-padding no-margin-vert template-normal--container">
						
			<div class="template-normal--header-menus">
			
				<#-- AZORES FLAG -->
				<div class="row no-gutters template-normal--azores-flag" style="height:4px;padding:0!important;margin:0!important;background-color:white;">	
					<div class="" style="background-color:#232F5D;width:40%!important"></div>
				</div>
				
				<#-- INTERNET EXPLORER WARNING -->
				<#if request.getHeader("User-Agent")?? && request.getHeader("User-Agent")?contains("Trident/")>
					<div class="template-normal--browser-warning">O Portal do Governo dos Açores não suporta Internet Explorer. Algumas funcionalidades podem não funcionar como esperado!</div>
				</#if>
				
				<#-- YOU ARE HERE -->
				<div class="row no-gutters template-normal--you-are-here d-print-none">	
					<div class="col-md-12">
						<#assign youAreHerePrefs = freeMarkerPortletPreferences.getPreferences({"portletSetupPortletDecoratorId": "none" }) />		
						<@liferay_portlet["runtime"]
		    				defaultPreferences="${youAreHerePrefs}"
		    				portletName="com_gra_portal_youAreHere_YouAreHerePortlet" />
					</div>
				</div>
							
				<#-- MAIN MENU -->
				<div class="row no-gutters template-normal--main-menu d-print-none">
					<div class="col-md-12">
						<#assign mainMenuPrefs = freeMarkerPortletPreferences.getPreferences({"portletSetupPortletDecoratorId": "none" }) />		
						<@liferay_portlet["runtime"]
		    				defaultPreferences="${mainMenuPrefs}"
		    				portletName="com_gra_portal_mainMenu_MainMenuPortlet" />
					</div>
				</div>
			</div>	
				
				
				
				<#-- USER WARNINGS/NOTIFICATIONS  -->	
				<div class="row no-gutters template-normal--user-notification">
					<div class="col-md-12">
						<#assign userNotsPrefs = freeMarkerPortletPreferences.getPreferences({"portletSetupPortletDecoratorId": "none" }) />		
						<@liferay_portlet["runtime"]
		    				defaultPreferences="${userNotsPrefs}"
		    				portletName="com_gra_portal_userNotifications_UserNotificationsPortlet" />
					</div>
				</div>
				
				<#-- USER DASHBOARD -->
				<div class="template-normal--user-dash d-print-none">
					<#assign userDashPrefs = freeMarkerPortletPreferences.getPreferences({"portletSetupPortletDecoratorId": "none" }) />		
					<@liferay_portlet["runtime"]
		    			defaultPreferences="${userDashPrefs}"
		    			portletName="com_gra_portal_userDash_UserDashPortlet" />
		    	</div>
								
				<#-- SCROLL UP BUTTON -->
				<@liferay.js file_name="${ javascript_folder }/scroll-up.js"/>
				
				<#assign scrollUpImage = theme_display.getPathThemeImages() + "/arrow-up.svg" />
				<input type="image" class="template-normal--scroll-image" src="${ scrollUpImage }" onclick="scrollUp()" alt="scroll">		
				
		    	<#-- MAIN -->
				<section class="${portal_content_css_class} template-normal--content no-padding no-margin-vert" id="content">
					
					

			
					<#if selectable>
						<@liferay_util["include"] page=content_include />
					<#else>
						${portletDisplay.recycle()}
						
						${portletDisplay.setTitle(the_title)}
			
						<@liferay_theme["wrap-portlet"] page="portlet.ftl">
							<@liferay_util["include"] page=content_include />
						</@>
					</#if>
				</section>
				
				
				<#-- FOOTER -->
				<#if show_footer>
				  <div class="template-normal--footer-wrap d-print-none">
					<div class="row no-gutters">	
						<div class="col-md-12">
							<footer id="footer" role="contentinfo">
								<div>
									<#assign socialFooterPrefs = freeMarkerPortletPreferences.getPreferences({"portletSetupPortletDecoratorId": "none" }) />		
									<@liferay_portlet["runtime"]
		    								defaultPreferences="${socialFooterPrefs}"
		    								portletName="com_gra_portal_socialFooter_SocialFooterPortlet" />
								</div>
								<div>
									<#assign footerPrefs = freeMarkerPortletPreferences.getPreferences({"portletSetupPortletDecoratorId": "none" }) />		
									<@liferay_portlet["runtime"]
		    								defaultPreferences="${footerPrefs}"
		    								portletName="com_gra_portal_footer_FooterPortlet" />
								</div>
							</footer>
						</div>
					</div>
				</div>
				</#if>
				
			</div>
		</div> <#-- End of Wrapper -->

		<footer id="footer" role="contentinfo">
			<p class="MyHelloCSSSS">
				<@liferay_portlet["runtime"]
					defaultPreferences="${freeMarkerPortletPreferences}"
					portletProviderAction=portletProviderAction.VIEW
					instanceId="nY0HO5vbpOfA"
					portletName="com_socket_portlet_SocketPortlet" />
			</p>
		</footer>
		
		<#include "${full_templates_path}/js_includes.ftl"/>
		
		<@liferay_util["include"] page=body_bottom_include />
		<@liferay_util["include"] page=bottom_include />
					
		</body>   
</html>
