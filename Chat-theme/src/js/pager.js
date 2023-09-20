gra.component('pager', function (name, config) {

    var $core = gra;

    var _state = {
        id: {
            next: $core.dom.id('nextPage'),
            prev: $core.dom.id('prevPage')
        },
        api: null
    };

    var _defaultTemplate =
        /**/'<nav aria-label="Page navigation">' +
        /*      */'<ul class="pagination gra-nomargin">' +
        /*              */'<li class="page-item prev-page">' +
        /*                      */'<a id="' + _state.id.prev + '" class="page-link gra-noglow" aria-label="Previous">' +
        /*                              */'<span aria-hidden="true">&laquo;</span>' +
        /*                      */'</a>' +
        /*              */'</li>#pageContent#' +
        /*              */'<li class="page-item next-page">' +
        /*                      */'<a id="' + _state.id.next + '" class="page-link gra-noglow" aria-label="Next">' +
        /*                              */'<span aria-hidden="true">&raquo;</span>' +
        /*                      */'</a>' +
        /*              */'</li>' +
        /*      */'</ul>' +
        /**/'</nav>';

    var _nextPageClicked = function() {
        if (_state.api && _state.api.nextPage) {
            _state.api.nextPage();
        }
    };

    var _prevPageClicked = function() {
        if (_state.api && _state.api.prevPage) {
            _state.api.prevPage();
        }
    };
    
    var _numberPageClicked = function() {
        if (_state.api && _state.api.selectPage && !$(this).parent().hasClass("active-page")) {
            _state.api.selectPage($(this).data("index"));
        }
    };

    var _api = function(api) {
        if (api) {
            _state.api = api;
        }
    };

    var _refresh = function (pageIndex, maxPage) {
    	var pageContent = ''
    	var start = Math.max(0, Math.min(maxPage - config.paging.pageLength, pageIndex - 2))
		var end = Math.min(start + config.paging.pageLength, maxPage)
		if (start > 0) {
			pageContent += '<li id="'+config.rendering.host+'pagePrevDots" class="number-item disabled-item"><a class="number-link page-link gra-noglow"><span aria-hidden="true">...</span></a></li> ';
		}
		for (var i = start; i < end; i++) {
			pageContent += '<li id="page'+(i)+'" class="number-item"><a class="number-link page-link gra-noglow" data-index="'+(i)+'" aria-label="Previous"><span aria-hidden="true">'+(i+1)+'</span></a></li> ';
		}
		if (end < maxPage) {
			pageContent += '<li id="'+config.rendering.host+'pageNextDots" class="number-item disabled-item"><a class="number-link page-link gra-noglow"><span aria-hidden="true">...</span></a></li> ';
		}
    	
    	var template = _defaultTemplate.replace('#pageContent#', pageContent);
        // Attach the page rendering html.
        $core.dom.attach(config.rendering.host, template);

        // Hook up the events.
        $core.dom.get(_state.id.prev).on('click', _prevPageClicked);
        $core.dom.get(_state.id.next).on('click', _nextPageClicked);
        $("#"+config.rendering.host+" .number-link").on('click', _numberPageClicked);
    }
    
    var _render = function (pageIndex, pageCount) {
    	if(config.paging.type == 'PAGE_WITH_NUMBERS' && pageCount) {
    		_state.api.maxPage=pageCount;
    		_state.api.refresh=null;

    		if(pageCount > config.paging.pageLength) {
    			_state.api.refresh=_refresh;
    		}
    		_refresh(pageIndex, pageCount);
    	} else {
    		var template = _defaultTemplate.replace('#pageContent#', '');
    		// Attach the page rendering html.
    		$core.dom.attach(config.rendering.host, template);
    		
    		// Hook up the events.
    		$core.dom.get(_state.id.prev).on('click', _prevPageClicked);
    		$core.dom.get(_state.id.next).on('click', _nextPageClicked);
    	}
    }

    return {
        render: _render,
        api: _api
    };
});