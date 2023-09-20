<div class="layout_3_9_rtb" id="main-content" role="main">
	<div class="portlet-layout row no-gutters">
		<div class="col-md-3 portlet-column portlet-column-first gra-layout-sidebar-left" id="column-1">
			$processor.processColumn("column-1", "portlet-column-content portlet-column-content-first")
		</div>
		<div class="col-md-9 portlet-column portlet-column-last">
			<div class="portlet-layout row gra-layout-no-vert-portlet-gap">
				<div class="col-md-12 gra-breadcrumb">
					## params(???, "Show Guest Site", "Show Page", "Show Parent Sites", "Show Application Breadcrumb")
					$theme.breadcrumb("", false, false, true, true)
				</div>
				<div class="col-md-12 portlet-column portlet-column-only" id="column-2">
					$processor.processColumn("column-2", "portlet-column-content portlet-column-content-only")
				</div>
			</div>
			<div class="portlet-layout row gra-layout-inner-section">
				<div class="col-md-12 portlet-column portlet-column-only gra-layout-portlet-gap-section" id="column-3">
					$processor.processColumn("column-3", "portlet-column-content portlet-column-content-only")
				</div>
			</div>
			<div class="gra-layout-sidebar-bottom">
				<div class="portlet-layout row gra-layout-inner-section gra-layout-portlet-gap-section gra-layout-sidebar-bottom">
					<div class="col-md-12 portlet-column portlet-column-only" id="column-4">
						$processor.processColumn("column-4", "portlet-column-content portlet-column-content-only")
					</div>
				</div>
			</div>
		</div>
	</div>
</div>