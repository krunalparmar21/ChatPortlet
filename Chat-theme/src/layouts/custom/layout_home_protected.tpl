<div class="layout_home_protected" id="main-content" role="main">

	<div class="gra-layout-home-protected row no-gutters">
		<div class="col-12">
			$processor.processPortlet("prociv_portlet_warnings_WarningsPortlet_INSTANCE_layout-home-protected-warnings")
		</div>
		
		<div class="col-12 col-md-5 col-lg-4 col-xl-3">
			<div class="row no-gutters layout-sidebar-left-col">
				<div class="layout-sidebar-left-column-wrap" id="layout-sidebar-left-column-wrap">
					<div class="col-12 portlet-column portlet-column-only layout-sidebar-simple-3-9--table-col1" id="column-1">
						$processor.processColumn("column-1")
						$processor.processPortlet("com_gra_gov_entityTitle_EntityTitlePortlet_INSTANCE_layout-home-protected-entity-title")
						$processor.processPortlet("com_gra_portal_sideMenu_SideMenuPortlet_INSTANCE_layout-home-protected-side-menu")
					</div> 
					<div class="col-12 portlet-column portlet-column-only layout-sidebar-simple-3-9--table-col2 " id="column-ii">
						$processor.processColumn("column-2")
						$processor.processPortlet("com_gra_gov_entityContactBox_EntityContactBoxPortlet_INSTANCE_layout-home-protected-entity-contact-box")
					</div>
				</div>
			</div>
		</div>
		<div class="col-12 col-md-7 col-lg-8 col-xl-9">
				<div class="row no-gutters layout-sidebar-right-col">
					<div class="col-md-12 gra-breadcrumb layout-sidebar-simple-3-9--breadcrumb">
						$processor.processColumn("column-3")
						$processor.processPortlet("com_gra_gov_highlights_HighlightsPortlet_INSTANCE_layout-home-protected-highlights")
						$theme.breadcrumb("", false, false, true, true)
					</div>
					
					<div class="col-md-12 portlet-column portlet-column-only layout-sidebar-simple-3-9--inner" id="column-2">
						$processor.processColumn("column-4")
						$processor.processPortlet("com_gra_gov_navigationCardsLinksSimple_NavigationCardsLinksSimplePortlet_INSTANCE_layout-home-protected-navigation-cards-links-simple")
						<div class="portlet-spacing"> </div>
						$processor.processPortlet("com_gra_gacs_agendaNext_AgendaNextPortlet_INSTANCE_layout-home-protected-agenda-next")
					</div>
					
					<div class="col-md-12 portlet-column portlet-column-only layout-sidebar-simple-3-9--footer row no-gutters" id="column-3">
						<div class="news-container col-lg-8 col-md-12">
							$processor.processColumn("column-5")
						</div>
						
						<div class="pub-container col-lg-4 col-md-12">
							$processor.processColumn("column-6")
						</div>
					</div>
				</div>
		</div>
	</div>
	
</div>

<script>
	
	var leftTest  = $('.layout-sidebar-simple-3-9--table-col1').height() + $('.layout-sidebar-simple-3-9--table-col2').height()
	var rightTest = $('.layout-sidebar-right-col').height();
	
	if(leftTest >= rightTest){
		$(".layout-sidebar-simple-3-9--table-col2").css({position: 'relative'});
	}else{
		$(".layout-sidebar-simple-3-9--table-col2").css({position: 'absolute'});
	} 
	
	//Detect if it is the presidente and change background color
	if($(".layout_home_protected *").hasClass("president")){
		document.getElementById('layout-sidebar-left-column-wrap').style.backgroundColor = "#222E5E"; 
	}else{
		document.getElementById('layout-sidebar-left-column-wrap').style.backgroundColor = "#DEDADA"; 
	}
</script>