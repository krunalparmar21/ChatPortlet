(function () {

    // The global space variable. This
    // variable is the only entry point
    // of the complete library.
    var __LIBRARY_NAME = 'gra';

    if (!window[__LIBRARY_NAME]) {

        window[__LIBRARY_NAME] = function () {

            // The component definitions.
            var _DEF = {};

            // The hashset of the available templates.
            var _TEMPLATE = {};

            // The prefix value for identifiers.
            var _ID = 'baseID';

            // The number of generated identifiers.
            var _idCount = 0;

            // Setup a function to run when the 
            // document finishes loading. 
            var _ready = function (fn) {
                $(document).ready(fn());
            }

            // Perform an ajax call, uses jQuery
            // as the interface definition.
            var _ajax = $.ajax;

            // Reference for the jQuery extend call.
            var _extend = $.extend;

            // Define a new component. Associate 
            // a name with a component definition.
            var _component = function (name, fn) {
                _DEF[name] = fn;
            };

            // Create a new instance for a component.
            var _instance = function (name) {
                var fn = _DEF[name];
                if (fn) {
                    var ret = fn.apply(null, arguments);
                    return ret ? ret : {};
                }
                else {
                    console.error('GRA: component \'' + name + '\' not found!')
                }
            };

            // Add and get a specific template.
            var _template = function (name, tmpl) {

                // If its a get operation, return the 
                // template, or undef if that does not 
                // exist.
                if (!tmpl) {
                    return _TEMPLATE[name];
                }

                // Add the compiled template, or its
                // value if not a string.
                _TEMPLATE[name] = typeof tmpl === 'string' ? Handlebars.compile(tmpl) : tmpl;
            };

            // Attach a html payload to an existing html
            // element in the DOM. The host is defined
            // by an identifier and the html is a piece
            // of html text.
            var _dom_attach = function (host, html) {
                $('#' + host).html(html);
            }

            // Get the DOM identified by the supplied
            // unique identifier. This functions returns
            // the DOM element.
            var _dom_get = function (id) {
                return $('#' + id);
            }

            // Get a new unique identifier with an optional
            // suffix value. The suffix is used to attach a
            // specific value at the end of the generated id.
            var _dom_id = function (suffix) {
                return _ID + _idCount++ + (suffix ? suffix : '');
            }

            // This components interface API.
            return {
                ready: _ready,
                ajax: _ajax,
                extend: _extend,
                component: _component,
                instance: _instance,
                template: _template,
                dom: {
                    id: _dom_id,
                    attach: _dom_attach,
                    get: _dom_get
                }
            };
        }();
    }
})();
