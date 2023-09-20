<div class="layout_gacs_home" id="main-content" role="main">
	<div class="gra-layout-gacs-home-wide-section">
		$processor.processPortlet("com_gra_gacs_highlights_HighlightsPortlet_INSTANCE_gacs-home-highlights")
		$theme.breadcrumb("", false, false, true, true)
		$processor.processColumn("column-1")
	</div>
	
	<div class="gra-layout-home-inner-section gra-layout-home-inner-section--cards">
		$processor.processPortlet("com_gra_portal_navigationCards_NavigationCardsPortlet_INSTANCE_gacs-home-navigation-cards")
		<div class="portlet-spacing"> </div>
		
		$processor.processPortlet("com_gra_gacs_agendaNext_AgendaNextPortlet_INSTANCE_gacs-home-agenda-next")
		$processor.processColumn("column-2")
	</div>
	
	<div class="grey gra-layout-home-inner-section gra-layout-home-inner-section--latests">
		$processor.processPortlet("com_gra_gacs_latestNews_LatestNewsPortlet_INSTANCE_gacs-home-latest-news")
		$processor.processColumn("column-3")
	</div>
</div>