//
// Configuration:
//      ws ::
//          - parameters ::
//                  - in :: { 'query' }
//                  - name :: string
//          - verb :: string
//          - path :: string
//
//      fetch ::
//          - before :: (call, query) -> call
//          - after :: (call, query, result) -> [ ... ] 
//     
//      rendering ::
//          - host :: string
//          - item? :: ({ item, ctx }) -> string
//          - before :: 
//          - after ::
// 
//  

gra.component('list', function (name, usrConfig) {

    var $core = window.gra;

    var _dftConfig = {
        ws: {
            verb: 'GET'
        },
        fetch: {
            before: null,
            after: null
        },
        rendering: {
            item: function (payload) { return JSON.stringify(payload.item, 2); },
            before: null,
            after: null
        }
    };

    var _CONFIG = usrConfig;

    // Initialize the configuration object.
    var _init = function (config) {
        return $core.extend(true, {}, _dftConfig, config);
    };

    // Takes the configuration object and a query 
    // specification and returns the full web 
    // service object definition to invoce.
    var _buildCall = function (query) {

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
        var _getQS = function (query) {
            var params = [];
            for (var i = 0; i < _CONFIG.ws.parameters.length; i++) {
                var param = _CONFIG.ws.parameters[i];
                if (param.in && param.in == 'query') {
                    params.push(_getParam(param.name, query[param.name]));
                }
            }
            params = params.join('&');
            return (params.length > 0 ? '?' + params : '');
        };

        // Decide on the call http verb.
        var _getVerb = function () {
            return _CONFIG.ws.verb.toUpperCase() === 'GET' ? 'GET' : 'POST';
        };

        // Return the complete web service url to call.
        return {
            url: _CONFIG.ws.path + _getQS(query),
            type: _getVerb()
        };
    };

    // Take the configuration object and a list of elements.
    // Apply the rendering function to every item in the
    // list. Returns the generated html.
    var _renderList = function (list) {

        var html = '';

        // Traverse all list items, compute 
        // item context and html payload.
        for (var i = 0; i < list.length; i++) {

            var isLast = i + 1 == list.length;

            // Define the context for this item.
            // This will help with rendering.
            ctx = {
                index: i,
                isFirst: i == 0,
                isLast: isLast,
                hasMore: !isLast
            };

            // Call item rendering hook, to get the
            // html payload for this item.
            html += _CONFIG.rendering.item({ item: list[i], ctx: ctx });
        }

        return html;
    };

    // Main entry point for this component.
    // Execute web service call, render the
    // content.
    var _render = function (query, host) {

        var elm = host ? host : _CONFIG.rendering.host;

        var call = _buildCall(query);

        call = _CONFIG.fetch.before ? _CONFIG.fetch.before(call, query) : call;

        call.success = function (result) {

            var list = _CONFIG.fetch.after ? _CONFIG.fetch.after(call, query, result) : result;
            
            var noContentMessage = "Nenhum item corresponde á sua pesquisa!";
            if(themeDisplay.getLanguageId() == "en_US"){
            	noContentMessage= "No items matched your search!";
    		}else if(themeDisplay.getLanguageId() == "pt_PT"){
    			noContentMessage = "Nenhum item corresponde á sua pesquisa!";
    		}

            var payload = list.length === 0 ? noContentMessage : _renderList(list);

            $core.dom.attach(elm, payload);

            if (_CONFIG.rendering.after) {
                _CONFIG.rendering.after();
            }
        };

        call.error = function () {
            $core.dom.attach(elm, 'Não foi possivel retornar qualquer informação!');
        };

        $core.ajax(call);
    }

    var _api = {
        render: _render
    };

    _CONFIG = _init(usrConfig);

    return _api;
});
