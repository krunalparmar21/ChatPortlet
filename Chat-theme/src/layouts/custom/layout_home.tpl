<div class="layout_home portal-gra--home-layout-wrap" id="main-content" role="main">

	<div class="portlet-layout row no-gutters gra-layout-no-vert-portlet-gap">
		<div class="col-md-12 portlet-column portlet-column-only" id="column-1">
			$processor.processColumn("column-1")
			$processor.processPortlet("prociv_portlet_warnings_WarningsPortlet_INSTANCE_layout-home-warnings")
		</div>

		<div class="col-md-12 portlet-column portlet-column-only" id="column-2">
			$processor.processColumn("column-2")
			$processor.processPortlet("com_gra_gacs_highlights_HighlightsPortlet_INSTANCE_layout-highlights-home")
<!-- 		$processor.processPortlet("com_gra_portal_search_SearchPortlet_INSTANCE_layout-search-home") -->
		</div>
	</div>
	
	<div class="grey">
		<div class="portlet-layout row no-gutters gra-layout-portlet-gap-section">
			
            <!--  Main Section -->
			<div class="gra-layout-home-maincontent">
				<div class="row no-gutters">
					<div class="col-md-12">
						<div class="row no-gutters">
							<div class="col-md-12 portlet-column gra-layout-home-column-top" id="gra-layout-home-column-a">
							   <!-- Top section of home main contents (Where will reside some warnings )-->
							 	$processor.processColumn("gra-layout-home-column-a")
							 	$processor.processPortlet("com_gra_portal_communicationWarning_CommunicationWarningPortlet_INSTANCE_layout-communication-warning")
							</div>
						</div>
						<div class="row no-gutters">
							
							<div class="col-lg-12 col-xl-9 portlet-column gra-layout-home-column-main" id="gra-layout-home-column-b">
								<!-- Main section of home (Where will reside the latests portlet )-->
								$processor.processColumn("gra-layout-home-column-b")
								$processor.processPortlet("com_gra_gacs_latest_LatestPortlet_INSTANCE_layout-latest")
							</div>
							
							<div class="col-lg-12 col-xl-3">
								<div class="gra-layout-home--inner-content ">
	
									<div class="portlet-column gra-layout-home-column-sec-c" id="gra-layout-home-column-c">
										<!-- Main section - Second column sec A -->
										$processor.processColumn("gra-layout-home-column-c")
										$processor.processPortlet("com_gra_portal_pub_PortalPortletPubPortlet_INSTANCE_layout-pub")
									</div>
										
									<div class="portlet-column gra-layout-home-column-sec-d" id="gra-layout-home-column-d">
										<!-- Main section - Second column sec B -->
										$processor.processColumn("gra-layout-home-column-d")
										$processor.processPortlet("com_gra_portal_sideBarWarning_SideBarWarningPortlet_INSTANCE_layout-side-bar-warning")	
									</div>
									
									<div class="portlet-column gra-layout-home-column-sec-e" id="gra-layout-home-column-e">
										<!-- Main section - Second column sec C -->
										$processor.processColumn("gra-layout-home-column-e")
										$processor.processPortlet("com_gra_portal_joraa_JoraaPortlet_INSTANCE_layout-joraa")
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		
		
		<div class="row">
			<div class="col-12">
				<div class="gra-layout-home--communication-container" align="center">
					<a href="/web/comunicacao/search" class="gra-layout-home--communication-button"> 
						#if( $locale == 'pt_PT' )
							+						
						#else
							+
						#end
					</a>
				</div>	
			</div>		
		</div>

	</div>
	
<!-- 	where resides the map -->
	<div class="portlet-layout row no-gutters gra-layout-no-vert-portlet-gap">
		<div class="col-md-12 portlet-column portlet-column-only" id="column-3">
			$processor.processColumn("column-3")
			$processor.processPortlet("com_gra_portal_homeMap_HomeMapPortlet_INSTANCE_layout-home-homemap")
		</div>
	</div>
	
	<div class="">
		<div class="portlet-layout row no-gutters gra-layout-portlet-gap-section gra-layout-home-inner-section">
			<div class="col-md-12 portlet-column portlet-column-only">
				<div class="portlet-spacing"></div>
				<div id="column-4">
					$processor.processColumn("column-4")
					$processor.processPortlet("com_gra_portal_navigationLinks_NavigationLinksPortlet_INSTANCE_layout-home-navigation-links")
				</div>
				
				<div class="portlet-spacing "></div>
				
				<div id="column-5">
					$processor.processColumn("column-5")
					$processor.processPortlet("com_gra_gacs_agendaNext_AgendaNextPortlet_INSTANCE_layout-home-agenda-next")
				</div>
				
				<div class="portlet-spacing "></div>
				
				<div id="column-6">
					$processor.processColumn("column-6")
					$processor.processPortlet("com_portal_homeCardsLinks_HomeCardsLinksPortlet_INSTANCE_layout-home-cards-links")	
				</div>
			</div>
		</div>
	</div>
		
</div>
