<div class="layout_wt portal-gra--wt-layout-wrap" id="main-content" role="main">
	
	<div class="portlet-layout gra-layout-no-vert-portlet-gap" id="column-1">
		<div class="gra-breadcrumb">
			$theme.breadcrumb("", false, false, true, true)
		</div>
		
		$processor.processColumn("column-1")
	</div>
	
	<div class="portlet-layout gra-layout-portlet-gap-section" id="column-2">
		$processor.processColumn("column-2")
	</div>
	
</div>