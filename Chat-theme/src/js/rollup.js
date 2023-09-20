gra.component('rollup', function (name, usrConfig, idConfig) {

    var $core = window.gra;

    var _state = {
        query: null
    };

    var _CONFIG = usrConfig;
    var _QUERY = usrConfig.query;

    var _init = function (config) {

        // Initialize the item rendering function.
        // If the item property is a text string then
        // try to find if there is a way to get a 
        // item rendering function, if not, then the
        // rendering function will be that function.
        if (typeof config.rendering.item === 'string') {
            if (!config.rendering.itemRenderFn) {
                var compiler = config.rendering.compiler ? config.rendering.compiler : Handlebars.compile;
                config.rendering.itemRenderFn = compiler(config.rendering.item);
            }
        }
        else {
            config.rendering.itemRenderFn = config.rendering.item;
        }

        config.fetch = config.fetch ? config.fetch : {};

        return config;
    };

    // Takes the configuration object and a query 
    // specification and returns the full web 
    // service object definition to invoce.
    var _buildCall = function (config, query) {

        // Build a query string parameter value.
        //          <NAME> = <VALUE>
        // or
        //          <NAME>
        // if no value is provided.
        var _getParam = function (name, value) {
            return name ? name + (value ? '=' + value : '') : '';
        };

        // Build the query string value, if any.
        // Search all parameters in web service 
        // definition and extract the ones that 
        // are defined to be in the query string.
        var _getQS = function (config, query) {
            var params = [];
            for (var i = 0; i < config.ws.parameters.length; i++) {
                var param = config.ws.parameters[i];
                if (param.in && param.in == 'query') {
                    params.push(_getParam(param.name, query[param.name]));
                }
            }
            params = params.join('&');
            return (params.length > 0 ? '?' + params : '');
        };

        // Decide on the call http verb.
        var _getVerb = function (config) {
            return config.ws.verb.toUpperCase() === 'GET' ? 'GET' : 'POST';
        };

        var _getBody = function (config, query) {
        };

        // Return the complete web service url to call.
        var call = {
            url: config.ws.path + _getQS(config, query),
            type: _getVerb(config, query)
        }

        return call;
    };

    // Take the configuration object and a list of elements.
    // Apply the rendering function to every item in the
    // list. Returns the generated html.
    var _renderList = function (config, list) {
        
        var html = '';
        var isLast = false;

        //
        // Traverse all list items, compute 
        // item context and html payload.
        //

        for (var i = 0; i < list.length; i++) {

            //
            // Define the context for this item.
            // This will help with rendering.
            //

            isLast = i + 1 == list.length;

            ctx = {
                index: i,
                isFirst: i == 0,
                isLast: isLast,
                hasMore: !isLast
            }

            //
            // Call item rendering hook, to get the
            // html payload for this item.
            //

            html += config.rendering.itemRenderFn({ item: list[i], ctx: ctx });
        }

        return html;
    };

    // Main entry point for this component.
    // Execute web service call, render the
    // content.
    var _render = function (query, pager) {
        _QUERY = query ? query : _CONFIG.query;

        var call = _buildCall(_CONFIG, _QUERY);

        call = _CONFIG.fetch.before ? _CONFIG.fetch.before(call, query) : call;

        call.success = function (result) {

            var list = _CONFIG.fetch.after ? _CONFIG.fetch.after(result) : result;
            
            if(pager) {
            	if(result.totalData && result.totalData !=0 ){
            		var pages = Math.ceil(result.totalData/_QUERY.size)
            		pager.render(_QUERY.from, pages);
            		_selectPage(_QUERY.from, pages);
            	}
            	else{
            		pager.render();
            	}
            }

            if (list.length === 0) {
                var noContentMessage = "Nenhum item corresponde á sua pesquisa!";
                if(themeDisplay.getLanguageId() == "en_US"){
                	noContentMessage= "No items matched your search!";
        		}else if(themeDisplay.getLanguageId() == "pt_PT"){
        			noContentMessage = "Nenhum item corresponde á sua pesquisa!";
        		}
                $core.dom.attach(_CONFIG.rendering.host, noContentMessage);
            }
            else {
                $core.dom.attach(_CONFIG.rendering.host, _renderList(_CONFIG, list));

                if (_CONFIG.rendering.after) {
                    _CONFIG.rendering.after();
                }
            }
        };

        call.error = function () {
            $core.dom.attach(_CONFIG.rendering.host, 'Não foi possivel retornar qualquer informação!');
        };

        $core.ajax(call);
    }

    var _selectPage = function (index, maxPage, refreshFn) {
    	if(refreshFn && refreshFn !=null){
    		refreshFn(index, maxPage);
        }
    	$("#"+idConfig.pager+" .number-item.active-page").removeClass("active-page");
    	$("#"+idConfig.pager+" #page"+index).addClass("active-page");
    	// Disable next button if it reach to highest
    	if(index >= (maxPage-1)) {
    		$("#"+idConfig.pager+" .next-page").addClass("disabled-item");
    	} else {
    		$("#"+idConfig.pager+" .next-page").removeClass("disabled-item");
    	}
    	// Disable prev button if it reach to lowest
    	if(index <= 0) {
    		$("#"+idConfig.pager+" .prev-page").addClass("disabled-item");
    	} else {
    		$("#"+idConfig.pager+" .prev-page").removeClass("disabled-item");
    	}
    	
    }
    // When the rollup supports paging these are the methods that
    // implement the paging API. They serve all types of paging
    // schemes.
    // Initilize the paging API for this rollup component.
    // Check if the roll up supports paging, and if it does
    // based on the type of paging generate the api accordingly.
    var _pagInit = function (config, api) {
        var pagingAPI = null;

        if (config.paging) {
            if (config.paging.type) {
                switch (config.paging.type) {

                    case 'PAGE_SEGMENTS':
                        pagingAPI = {
                            nextPage: function () {
                                _QUERY[config.paging.params.page]++;
                                _render(_QUERY);

                            },
                            prevPage: function () {
                                _QUERY[config.paging.params.page]--;
                                _render(_QUERY);
                            }
                        }
                    break;
                        
                    case 'PAGE_WITH_NUMBERS':
                    	pagingAPI = {
                                nextPage: function () {
                                    _QUERY[config.paging.params.page]++;
                                    _render(_QUERY);
                                    _selectPage(_QUERY[config.paging.params.page], this.maxPage, this.refresh);

                                },
                                prevPage: function () {
                                    _QUERY[config.paging.params.page]--;
                                    _render(_QUERY);
                                    _selectPage(_QUERY[config.paging.params.page], this.maxPage, this.refresh);
                                },
                                selectPage: function (pageIndex) {
	                                _QUERY[config.paging.params.page] = pageIndex;
	                                _render(_QUERY);
	                                _selectPage(_QUERY[config.paging.params.page], this.maxPage, this.refresh);
                                },
                                maxPage: 0,
                                refresh: null
                        }
                    break;
                }
            }
        }

        // After the analysis, if we have
        // a paging object to add, then add it.
        if (pagingAPI) {
            api.paging = pagingAPI;
        }

        return api;
    }

    //

    var _defaultAPI = {
        render: _render
    };

    _CONFIG = _init(usrConfig);

    return _pagInit(_CONFIG, _defaultAPI);
});
