<div class="layout_wt_wc portal-gra--wt-wc-layout-wrap" id="main-content" role="main">
	
	<div class="portlet-layout gra-layout-no-vert-portlet-gap" id="column-1">
		<div class="gra-breadcrumb">
			$theme.breadcrumb("", false, false, true, true)
		</div>
		
		$processor.processColumn("column-1")
	</div>
	
	<div class="portlet-layout gra-layout-portlet-gap-section" id="column-2">
		$processor.processColumn("column-2")
	</div>
	
	<div class="portlet-layout gra-layout-no-vert-portlet-gap" id="column-3">
		$processor.processColumn("column-3")
	</div>
	
	<div class="portlet-layout gra-layout-portlet-gap-section" id="column-4">
		$processor.processColumn("column-4")
	</div>
</div>