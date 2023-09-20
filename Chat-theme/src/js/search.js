gra.component('search', function (name, host, config) {

    var $core = window.gra;

    // Object that contains the component's 
    // runtime state. Includes result tabs
    // and input text box.
    var _state = {

        input: {
            id: null,
            text: null,
            dirty: false
        },

        clear: {
            id: null
        },

        search: {
            id: null
        },

        results: {
            id: null,
            visible: false
        },

        mostSearched: {
            id: null,
            fullSize: true
        },

        active: {
            index: null
        },

        tabs: [
            // id
            //      tab
            //      panel
            //      content
            //      pager
            // header
            // content
            // component
            // config
        ]
    }

    // 
    var _MIN_WIDTH = 'col-10';
    var _MAX_WIDTH = 'col-12';
    var _MAX_WIDTH_SITE_SEARCH_TAB_SECTION = "col-lg-8 col-md-12";
    var _MAX_WIDTH_SITE_SEARCH_SECTION = "col-lg-4 col-md-12";

    var _search = function () {

        if (_state.input.text && _state.input.text.length > 0) {
            // Make result panel visible if not already.
            _results(true);
            _mostSearched(true);

            // Change query to reflect the search text.
            var tab = _state.tabs[_state.active.index];

            // Render search results.
            tab.config.query[tab.config.search.param] = _state.input.text;
            tab.component.render(tab.config.query, tab.pager);
            displayContactoDetail(_state.input.text);
        }
    };
    
    var displayContactoDetail = function(keyword){
    	var companyId = themeDisplay.getCompanyId();
    	var languageId = themeDisplay.getLanguageId();
    	
    	$.ajax({
    		url: "/o/search/get-governo-contacto-detail",
    		type: "GET",
    		dataType: "json",
    		data:{
    			keyword: keyword,
    			companyId: companyId,
    			languageId: languageId
    		},
    		success: function(data){
    			fillContactoDetail(data);
    		},
    		error: function(data){
    			
    		}
    	});
    }
    
    var fillContactoDetail = function(data){
    	var template = "<div class='card card-horizontal shadow-none rounded-0'>" +
							"<div class='card-body'>" +
								"<div class='card-row'>" +
									"<div class='autofit-col autofit-col-expand autofit-col-gutters'>" +
										"<section class='autofit-section'>" +
											"<div class='card-title site-title'><a href='{{contactGroupUrl}}' target='_blank'>{{contact_site}}</a></div>" +
											"<div class='card-text address pb-2'>" +
												"{{contact_address}}" +
											"</div>" +
											"<div class='contacts'>" +
												"{{contact_contact_detail}}"+
											"</div>" +
										"</section>" +
									"</div>" +
									"<div class='autofit-col entity-map'>" +
										"<img alt='thumbnail' class='card-item-first' src='"+ themeDisplay.getPathThemeImages() +"/map-icon.jpg' style='width:120px; height: 120px;'>" +
									"</div>" +
								"</div>" +
							"</div>" +
						"</div>";
    	var contactoHtml = "";
    	$.each(data, function(i, item) {
    		var templateHtml = template;
    		
    		if (item.contactName != null || item.contactName != undefined){
    			templateHtml = templateHtml.replace("{{contact_site}}", item.contactName);
    		}else{
    			templateHtml = templateHtml.replace("{{contact_site}}", "");
    		}
    		
    		if (item.contactAddress != null || item.contactAddress != undefined){
    			templateHtml = templateHtml.replace("{{contact_address}}", item.contactAddress);
    		}else{
    			templateHtml = templateHtml.replace("{{contact_address}}", "");
    		}
    		
    		var contactDetailHtml = ""; 
    		if (item.contactEmail != null || item.contactEmail != undefined){
    			contactDetailHtml = "<p class='mb-0'><b>Email:</b> " + item.contactEmail + "</p>";
    		}
    		
    		if (item.contactPhone != null || item.contactPhone != undefined){
    			contactDetailHtml += "<p class='mb-0'><b>Telefone:</b> " + item.contactPhone + "</p>";
    		}
    		
    		if (item.contactFax != null || item.contactFax != undefined){
    			contactDetailHtml += "<p class='mb-0'><b>Fax:</b> " + item.contactFax + "</p>";
    		}
    		
    		templateHtml = templateHtml.replace("{{contact_contact_detail}}", contactDetailHtml);
    		
    		if (item.contactGroupUrl != null || item.contactGroupUrl != undefined){
    			templateHtml = templateHtml.replace("{{contactGroupUrl}}", item.contactGroupUrl);
    		}else{
    			templateHtml = templateHtml.replace("{{contactGroupUrl}}", "");
    		}
    		
    		contactoHtml += templateHtml;
		});
    	
    	if(contactoHtml.length===0){
    		if(themeDisplay.getLanguageId() == "en_US"){
    			$(".card-outer-body").html("No items matched your search!");
    		}else if(themeDisplay.getLanguageId() == "pt_PT"){
    			$(".card-outer-body").html("Nenhum item corresponde á sua pesquisa!");
    		}
    	}else{
    		$(".card-outer-body").html(contactoHtml);
    	}
    }
    
    var addSearchKeyword = function(keyword){
    	$.ajax({
    		url: "/o/search/add-search-keyword",
    		type: "GET",
    		dataType: "json",
    		data:{
    			keyword: keyword 
    		}
    	});
    }

    var _results = function (visible) {
        _state.results.visible = visible;
        if (_state.results.visible) {
            $core.dom.get(_state.results.id).show();
        } else {
            $core.dom.get(_state.results.id).hide();
        }
    }

    var _mostSearched = function (fullSize) {
        _state.mostSearched.fullSize = fullSize;
        $core.dom.get(_state.mostSearched.id).removeClass();
        if (_state.mostSearched.fullSize) {
            $core.dom.get(_state.mostSearched.id).addClass(_MAX_WIDTH);
        } else {
            $core.dom.get(_state.mostSearched.id).addClass(_MIN_WIDTH);
        }
    }

    var _activate = function (tab) {

        var index = null;
        var $this = null;

        switch (typeof tab) {

            case 'number':
                {
                    // Just get the index for the tab
                    // to display/update the search.
                    index = tab;

                    // Get the tab elm from the html 
                    // fragment and return a jQuery
                    // object.
                    $this = $core.dom.get(_state.tabs[index].id.tab);
                }
                break;

            default:
                {
                    // The hook sends to the handler 
                    $this = this;

                    // The tab identifier is in a data-X attribute
                    // extract this value using jQuery's data acessor. 
                    index = $(this).data('id');

                    // Get the event handler and prevent
                    // the bubbling...
                    var event = tab;
                    event.preventDefault();
                }
                break;
        }

        // Set the active index.
        _state.active.index = index;

        // Show the tab normally.
        $($this).tab('show');
    }

    var _searchBtnClicked = function (event) {
    	config.tabs.forEach(function(data){
    		if(data.hasOwnProperty("query") && data.query.hasOwnProperty("from") && data.query.hasOwnProperty("initFrom"))
    			data.query.from = data.query.initFrom;
    		else if(data.hasOwnProperty("query") && data.query.hasOwnProperty("page") && data.query.hasOwnProperty("initFrom"))
    			data.query.page = data.query.initFrom;
    	});
    	_state.input.text = $core.dom.get(_state.input.id).val();
        _search();
    };

    var _clearBtnClicked = function (event) {
    	if(!$('.gacs-portlet-search-rended').hasClass('open')){
    		console.log("_clearBtnClicked");
    		$core.dom.get(_state.input.id).focus();
		}
        $core.dom.get(_state.input.id).val('');
        var isHomePage = $("#isHomePage").val();
        if(isHomePage && isHomePage != "false"){
        	_results(false);
        	_mostSearched(false);
        	$('#clearIcon').hide();
        	$('#searchIcon').show();
        	$('.search-cst').removeClass('active');
        	$('.gacs-portlet-search-rended').removeClass('open');
        }
        
    };

    // Generate the html for all the configured tabs.
    var _setup = function (config, state) {

        var tabLst = '';
        var pnlLst = '';

        // Generate the tabs html payload.
        for (var i = 0; i < config.tabs.length; i++) {

            var stab = {};

            var id = {
                tab: 'tab' + i,
                panel: 'panel' + i,
                content: 'content' + i,
                pager: 'pager' + i,
            };

            var header =
                /**/'<li class="nav-item">' +
                /*      */'<a data-id="' + i + '" class="nav-link rounded-0' + (i == 0 ? ' active' : '') + '" id="' + id.tab + '" data-toggle="tab" href="#' + id.panel + '" role="tab" aria-controls="' + id.tab + '" aria-selected="true">' +
                /*              */config.tabs[i].name +
                /*      */'</a>' +
                /**/'</li>';

            var content =
                /**/'<div data-id="' + i + '" class="tab-pane show' + (i == 0 ? ' active' : '') + '" id="' + id.panel + '" role="tabpanel" aria-labelledby="' + id.tab + '">' +
                /*      */'<div class="gra-padding-large" style="overflow:auto">' +
                /*              */'<div id="' + id.content + '" class="">' +
                /*                      */config.tabs[i].name +
                /*              */'</div>' +
                /*      */'</div>' +
                /*      */'<div id="' + id.pager + '" class="gra-padding-medium row justify-content-center">' +
                /*              */'PAGER' +
                /*      */'</div>' +
                /***/'</div>';

            stab.id = id;
            stab.header = header;
            stab.content = content;
            stab.config = config.tabs[i];
            stab.config.rendering.host = id.content;
            stab.component = $core.instance('rollup', stab.config, id);
            stab.pager = $core.instance('pager', { rendering: { host: id.pager }, paging: stab.config.paging });
            stab.pager.api(stab.component.paging);

            tabLst += header;
            pnlLst += content;

            state.tabs.push(stab);
        }

        // Generate the input text box.
        state.input.id = 'inputBox';
        state.clear.id = 'clearIcon';
        state.search.id = 'searchIcon';
        state.results.id = 'resultPanel';
        state.mostSearched.id = 'mostSearched';

        var mostSearchTerm = "";
        if(themeDisplay.getLanguageId() == "en_US"){
        	mostSearchTerm = "Most searched terms";
		}else if(themeDisplay.getLanguageId() == "pt_PT"){
			mostSearchTerm = "Termos mais procurados";
		}
        
        // Main html template for component.
        var _template =
            /**/'<div class="gra-searchbox rounded-0 row no-gutters search-cst">' +
            /*      */'<div class="col-lg-12">' +
            /*              */
            /*              */'<!-- SEARCH BOX -->' +
            /*              */
            /*              */'<div class="row justify-content-center gra-marginb-small">' +
            /*                      */'<div class="' + _MIN_WIDTH + ' col-10-search" >' +
            /*                                      */'<div class="input-group">' +
            /*                                              */'<input id="' + state.input.id + '"class="form-control form-control-lg rounded-0 gra-noglow" type="text" placeholder="">' +
            /*                                              */'<span id="' + state.clear.id + '"class="m16 mt18" style="cursor:pointer">' +
            /*                                                      */'<span class=" rounded-0 gra-h100" id="inputGroupPrepend"><div id="closeIcon"></div></span>' +
            /*                                              */'</span>' +
            /*                                              */'<span id="' + state.search.id + '"class="m16" style="cursor:pointer">' +
            /*                                                      */'<span class=" rounded-0 gra-h100" id="inputGroupPrepend"><div id="searchBoxIcon"></div></span>' +
            /*                                              */'</span>' +
            /*                                      */'</div>' +
            /*                      */'</div>' +
            /*              */'</div>' +
            /*              */
            /*              */'<!-- RESULT PANEL -->' +
            /*              */
            /*              */'<div id="' + state.results.id + '" class="row no-gutters background gra-marginb-small" style="display: none">' +
            /*                      */'<div class="' + _MAX_WIDTH_SITE_SEARCH_TAB_SECTION + '">' +
            /*                              */'<div>' +
            /*                                      */'<ul class="nav nav-tabs" role="tablist">' +
            /*                                              */tabLst +
            /*                                      */'</ul>' +
            /*                                      */'<div class="tab-content results">' +
            /*                                              */'<div id="tab-overlay"><img width="200px" src="/o/portal.theme.default/images/loading.gif"></div>' +
            /*                                              */pnlLst +
            /*                                      */'</div>' +
            /*                              */'</div>' +
            /*                      */'</div>' +
            /*                      *///'<div class="col-4">' +
            /*                              *///'<div class="debug-box">' +
            /*                              *///'</div>' +
            /*                      *///'</div>' +
            /*                      */'<div class="' + _MAX_WIDTH_SITE_SEARCH_SECTION + '">' +
            
            						
						            '<div class="card-container">'+
						            '<div class="cards-title pt-3 pb-4">'+
						             '   Relacionados com “Edu”'+
						            '</div>'+
						            '<div class="card-outer-body">'+
						            '</div>'+
						        '</div>'+
            
            
            /*                      */'</div>' +
            /*              */'</div>' +
            /*              */
            /*              */'<!-- MOST SEARCHED TERMS -->' +
            /*              */
            /*              */'<div class="row justify-content-center">' +
            /*                      */'<div id="' + state.mostSearched.id + '" class="' + _MIN_WIDTH + '">' +
            /*                              */'<div class="terms">' +
            /*                                      */'<div class="gra-padding-medium">' +
            /*                                              */'<span class="mostSearched">' + mostSearchTerm + ' |</span>' +
            /*												*/'<span class="ms-text mostSearchKeyword">Emprego, Taxas, Família, Educação</span>' +
            /*                                      */'</div>' +
            /*                              */'</div>' +
            /*                      */'</div>' +
            /*              */'</div>' +
            /*      */'</div>' +
            /**/'</div>';


        // Add the main component html payload to DOM.
        $core.dom.attach(host, _template);

        // Hook up the tab change event.
        for (var i = 0; i < state.tabs.length; i++) {
            $core.dom.get(state.tabs[i].id.tab).on('click', function (event) {
                _activate.call(this, event);
                _search();
            });
        }

        $core.dom.get(state.search.id).on('click', function(event){
        	// If link is there then redirect to link
        	var link = $core.dom.get(state.input.id).attr("data-link");
        	if(!$('.gacs-portlet-search-rended').hasClass('open') && link && link != '') {
        		location.href = link;
        		return;
        	}
        	_searchBtnClicked();
        	$('.search-cst').addClass('active');
            $('.gacs-portlet-search-rended').addClass('open');
            $('#clearIcon').show();
        	if($core.dom.get(state.input.id).val().length > 5){
        		//add search keyword into db table
        		addSearchKeyword($core.dom.get(state.input.id).val());
        	}
        });

        var timer;
        
        // Whenever the user presses the enter key or writes 
        // more than a specific number of characters.
        $core.dom.get(state.input.id).on('keyup', function (event) {
            if (event.keyCode == 13) {
                _searchBtnClicked();
                $('.search-cst').addClass('active');
                $('.gacs-portlet-search-rended').addClass('open');
            } else if($core.dom.get(state.input.id).val().length > 2){
            	 $('.search-cst').addClass('active');
                 $('.gacs-portlet-search-rended').addClass('open');
            }
            $('#clearIcon').show();
            //$('#searchIcon').hide();
            
            // Search Result most search configurable if length is more than specific number of character
            if((event.keyCode == 13 || event.keyCode == 32) && $core.dom.get(state.input.id).val().length > 5){
	    		//add search keyword into db table
	    		addSearchKeyword($core.dom.get(state.input.id).val());
            }
            
//            var lastKeyCode = event.keyCode;
//            clearTimeout(timer);
//            timer = setTimeout(function() {
//                if(lastKeyCode != 32 && $core.dom.get(state.input.id).val().length > 5){
//            		//add search keyword into db table
//            		addSearchKeyword($core.dom.get(state.input.id).val());
//            	}
//            }, 5000);
        });

        $core.dom.get(state.input.id).focus( function (event) {
        	console.log("focused");
        	if(!$('.gacs-portlet-search-rended').hasClass('open')){
        		$('#clearIcon').show();
        	}
        });
        $core.dom.get(state.input.id).blur( function (event) {
        	setTimeout(function() { 
        		console.log("blur");
        		if(!$('.gacs-portlet-search-rended').hasClass('open')){
        			$('#clearIcon').hide();
        		}
        	}, 500);
        });
        
        // Clear the search results.
        $core.dom.get(state.clear.id).on('click', _clearBtnClicked);

        $('#clearIcon').hide();
        $('#searchIcon').show();
        
        var isHomePage = $("#isHomePage").val();
        var hasClasssCon = $(".search-cst").hasClass("not-search");
        if(isHomePage === "true"){
        	$(".search-cst").addClass("not-search");
        }else{
        	$(".search-cst").removeClass("not-search");
        }
        
        return state;
    };

    // Setup component and activate the first tab, if any.
    _state = _setup(config, _state);
    _activate(0);
});
