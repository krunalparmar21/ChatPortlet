<div class="layout_theme_detail" id="main-content" role="main">
	<div class="row no-gutters gra-layout-theme-detail">
		<div class="col-lg-3 col-md-12 grey">
			<div>
				$processor.processPortlet("portal_portlet_themeNavigation_themeNavigationPortlet_INSTANCE_theme-detail-theme-navigation")
				$processor.processColumn("column-1")
			</div>
			
			<div>
				$processor.processPortlet("com_gra_theme_entityRelated_EntityRelatedPortlet_INSTANCE_theme-detail-entity-related")
				$processor.processColumn("column-2")
			</div>
			
			<div class="portlet-spacing"> </div>
			<div>
				$processor.processPortlet("com_gra_portal_pub_PortalPortletPubPortlet_INSTANCE_theme-detail-pub")
				$processor.processColumn("column-3")
			</div>
		</div>
		
		<div class="col-lg-9 col-md-12">
			<div class="gra-layout-theme-detail-wide-section">
				$processor.processPortlet("com_gra_gacs_highlights_HighlightsPortlet_INSTANCE_theme-detail-highlights")
				$theme.breadcrumb("", false, false, true, true)
				$processor.processColumn("column-4")
			</div>
			
			<div class="gra-layout-theme-detail-handigap-section">
				$processor.processPortlet("com_gra_theme_navigationCards_NavigationCardsPortlet_INSTANCE_theme-detail-navigation-cards")
				$processor.processColumn("column-5")
			</div>
			
			<div class="gra-layout-theme-detail-handigap-section">
				$processor.processPortlet("com_gra_gacs_agendaNext_AgendaNextPortlet_INSTANCE_theme-detail-agenda-next")
				$processor.processColumn("column-6")
			</div>
			
			<div class="gra-layout-theme-detail-handigap-section">
				$processor.processPortlet("com_gra_theme_relatedContent_portlet_RelatedContentPortlet_INSTANCE_theme-detail-related-content")
				$processor.processColumn("column-7")
			</div>
		</div>
	</div>
</div>