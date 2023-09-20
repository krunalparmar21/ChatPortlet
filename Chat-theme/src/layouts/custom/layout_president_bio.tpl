<div class="layout-president-bio" id="main-content" role="main">
	
	<div class="president-bio-breadcrumb">
		$theme.breadcrumb("", false, false, true, true)
	</div>
	
	<!-- Desktop Layout -->
	<div class="main-container-desktop row no-gutters">
		<div class="desktop-left-column col-xl-3 col-lg-4 col-md-5">
			
			<div class="desktop-top-entity">
				<span class="entity-title bold">$themeDisplay.getScopeGroupName()</span>
			</div>
			
			<img class="entity-badge" src="$themeDisplay.getPathThemeImages()/president_bio/president-badge.svg" alt="Presidente" >
			
			<div class="desktop-side-menu" id="column-1-desktop">
				$processor.processColumn("column-1-desktop")
				$processor.processPortlet("com_gra_portal_sideMenu_SideMenuPortlet_INSTANCE_gov-president-bio")
			</div>
			
			<div class="desktop-contact-box" id="column-2-desktop">
				$processor.processColumn("column-2-desktop")
				$processor.processPortlet("com_gra_gov_entityContactBox_EntityContactBoxPortlet_INSTANCE_gov-president-bio")
			</div>
		</div>
		
		<div class="col-xl-9 col-lg-8 col-md-7">
			
			<div id="column-3-desktop">
				$processor.processColumn("column-3-desktop")
				$processor.processPortlet("gov_portlet_presidentBioHeader_gov_portlet_presidentBioHeaderPortlet_INSTANCE_gov-president-bio")
			</div>
			
			<div class="spacer"></div>
			<div class="container-with-gaps" id="column-4-desktop">
				$processor.processColumn("column-4-desktop")
				$processor.processPortlet("com_gra_gacs_agendaNext_AgendaNextPortlet_INSTANCE_gov-president-bio")
			</div>
			
			<div class="spacer"></div>
			<div class="container-with-gaps only-right" id="column-5-desktop">
				$processor.processColumn("column-5-desktop")
				$processor.processPortlet("com_gra_portal_galleries_GalleriesPortlet_INSTANCE_gov-president-bio")
			</div>
			
		</div>
	</div>
		
	<!-- Mobile Layout -->
	<div class="main-container-mobile">
		
		<div class="mobile-top-entity">
			<img class="entity-badge" src="$themeDisplay.getPathThemeImages()/president_bio/president-badge.svg" alt="Presidente"> <br>
			<span class="entity-title bold">$themeDisplay.getScopeGroupName()</span> <br>
		</div>
		
		<div id="column-1-mobile">
			$processor.processColumn("column-1-mobile")
			$processor.processPortlet("gov_portlet_presidentBioHeader_gov_portlet_presidentBioHeaderPortlet_INSTANCE_gov-president-bio")
		</div>
		
		<div id="column-2-mobile">
			$processor.processColumn("column-2-mobile")
			$processor.processPortlet("com_gra_portal_sideMenu_SideMenuPortlet_INSTANCE_gov-president-bio")
		</div>
		
		<div class="spacer"></div>
		<div class="container-with-gaps" id="column-3-mobile">
			$processor.processColumn("column-3-mobile")
			$processor.processPortlet("com_gra_gacs_agendaNext_AgendaNextPortlet_INSTANCE_gov-president-bio")
		</div>
		
		<div class="spacer"></div>
		<div class="container-with-gaps only-right" id="column-4-mobile">
			$processor.processColumn("column-4-mobile")
			$processor.processPortlet("com_gra_portal_galleries_GalleriesPortlet_INSTANCE_gov-president-bio")
		</div>
		
		<div id="column-5-mobile">
			$processor.processColumn("column-5-mobile")
			$processor.processPortlet("com_gra_gov_entityContactBox_EntityContactBoxPortlet_INSTANCE_gov-president-bio")
		</div>
	</div>
	
</div>