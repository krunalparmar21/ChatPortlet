gra.component('joraa', function (name, host) {

    var $core = gra;

    var _state = {
        id: {
            serieI: $core.dom.id('serieI'),
            serieII: $core.dom.id('serieII')
        }
    };
      var _template =
      /**/'<div class="portal-portlet-joraa--wrap">' +
      /*    */'<div class="portal-portlet-joraa--first-serie" id="' + _state.id.serieI + '">&nbsp;</div>' +
      /*    */'<div class="portal-portlet-joraa--second-serie" id="' + _state.id.serieII + '">&nbsp;</div>' +
      /**/'</div>'
  
  var today = new Date();
  var dd = String(today.getDate()).padStart(2, '0');
  var mm = String(today.getMonth() + 1).padStart(2, '0'); //January is 0!
  var yyyy = today.getFullYear();

  today = yyyy + '-' + mm + '-' + dd;

    var configSerieI = {
        query: {
            page: '1',
            pageSize: '1',
            ordenacao: '1',
            toDate: today,
            serie: 'ec6ff6d5-7709-4517-8d3c-b9b92c443c8a' // Série I
        },
        ws: {
            path: 'https://jo.azores.gov.pt/api/public/search/ato',
            verb: 'GET',
            parameters: [
                {
                    name: 'page',
                    in: 'query',
                    required: false,
                    schema: {
                        type: 'integer',
                        format: 'int32'
                    }
                },
                {
                    name: 'pageSize',
                    in: 'query',
                    required: false,
                    schema: {
                        type: 'integer',
                        format: 'int32'
                    }
                },
                {
                    name: 'serie',
                    in: 'query',
                    required: false,
                    schema: {
                        type: 'string'
                    }
                },
                {
                    name: 'toDate',
                    in: 'query',
                    required: false,
                    schema: {
                        type: 'string'
                    }
                },
                {
                    name: 'ordenacao',
                    in: 'query',
                    required: false,
                    schema: {
                        type: 'integer',
                        format: 'int32'
                    }
                }
            ]
        },
        fetch: {
            after: function (result) { return result.list; }
        },
        rendering: {
            host: _state.id.serieI,
            item:
                    /*      */'<a target="_blank" href="https://jo.azores.gov.pt/api/public/jornal/pdfOriginal?numeroJornal={{ item.numeroJornal }}&ano={{ item.anoJornal }}&serieId={{ item.idJornal.serieId }}&suplemento=0" style="width:100%;">' +
                    /*      */'<div class="portal-portlet-joraa--first-series-header"> ' +
                    /*          */ '<div class="row portal-portlet-joraa--first-series-header-row">' +
                    /*              */ '<div class="col-12 portal-portlet-joraa--first-series-title">' +
                    /*                   */ '<span class="portal-portlet-joraa--title-text">JORNAL OFICIAL</span>' +
                    /*              */ '</div>' +
                    /*              */ '<div class="col-12 portal-portlet-joraa--first-series-serie">' +
                    /*                   */ '<span class="portal-portlet-joraa--title-text">SÉRIE {{ item.serie }} - NÚMERO {{ item.numeroJornal }}</span>' +
                    /*              */ '</div>' +
                    /*          */ '</div>' +
                    /*              */ '<div class="portal-portlet-joraa--first-series-serie-plus-sign">'+
                    /* 		             */'<img src="'+ themeDisplay.getPathThemeImages()+'/more.svg" />' +
                    /*              */ '</div>' +   
                    /*      */ '</div>' +
                    /*      */'<div class="portal-portlet-joraa--first-series-contents"> ' +
                    /*          */ '<div class="row portal-portlet-joraa--first-series-content-row">' +
                    /*              */ '<div class="col-12 portal-portlet-joraa--first-series-content-title">' +
                    /*                    */'<span class="inline-block text-truncate">' +
                    /*                         */'{{#each item.entidades }}' +
                    /*                             */'{{ this }}' +
                    /*                             */'{{#if @last }}' +
                    /*                             */'{{else}}' +
                    /*                             */', ' +
                    /*                             */'{{/if}}' +
                    /*                        */'{{/each}}' +
                    /*                    */'</span >' +
                    /*              */ '</div>' +
                    /*              */ '<div class="col-12 portal-portlet-joraa--first-series-content-item-id">' +
                    /*                       */'<a target="_blank" href="https://jo.azores.gov.pt/#/ato/{{ item.id }}">' +
                    /*                            */'<span class="inline-block text-truncate">{{ item.humanId }}</span>' +
                    /*                       */'<a>' +
                    /*              */ '</div>' +
                    /*              */ '<div class="col-12 portal-portlet-joraa--first-series-content-item-summary">' +
                    /*                     */'<span>{{ item.sumario }}</span>' +     
                    /*              */ '</div>' +            
                    /*          */ '</div>' +
                    /*      */ '</div>' +
                    /*      */ '</a>' +
                    /**/'{{#if ctx.hasMore }}' +
                    /*          */'<hr/>' +
                    /**/'{{/if}}'
        }
    };

    var configSerieII = {
        query: {
            page: '1',
            pageSize: '2',
            ordenacao: '1',
            serie: 'e5b1fb74-7a34-4d76-925a-10f088b27490' // Série II
        },
        ws: {
            path: 'https://jo.azores.gov.pt/api/public/search/ato',
            verb: 'GET',
            parameters: [
                {
                    name: 'page',
                    in: 'query',
                    required: false,
                    schema: {
                        type: 'integer',
                        format: 'int32'
                    }
                },
                {
                    name: 'pageSize',
                    in: 'query',
                    required: false,
                    schema: {
                        type: 'integer',
                        format: 'int32'
                    }
                },
                {
                    name: 'serie',
                    in: 'query',
                    required: false,
                    schema: {
                        type: 'string'
                    }
                },
                {
                    name: 'ordenacao',
                    in: 'query',
                    required: false,
                    schema: {
                        type: 'integer',
                        format: 'int32'
                    }
                }
            ]
        },
        fetch: {
            after: function (result) { return result.list; }
        },
        rendering: {
            host: _state.id.serieII,
            item:
                /*      */'<div class="portal-portlet-joraa--second-series-contents"> ' +
                /*          */ '<div class="row portal-portlet-joraa--second-series-header-row">' +
                /*              */ '<div class="col-12 portal-portlet-joraa--second-series-content-title">' +
                /*                    */'<span class="">' +
                /*                         */'{{#each item.entidades }}' +
                /*                             */'{{ this }}' +
                /*                             */'{{#if @last }}' +
                /*                             */'{{else}}' +
                /*                             */', ' +
                /*                             */'{{/if}}' +
                /*                        */'{{/each}}' +
                /*                    */'</span >' +
                /*              */ '</div>' +
                /*              */ '<div class="col-12 portal-portlet-joraa--second-series-content-item-id">' +
                /*                       */'<a target="_blank" href="https://jo.azores.gov.pt/#/ato/{{ item.id }}">' +
                /*                            */'<span class="inline-block text-truncate">{{ item.humanId }}</span>' +
                /*                       */'<a>' +
                /*              */ '</div>' +
                /*              */ '<div class="col-12 portal-portlet-joraa--second-series-content-item-summary">' +
                /*                     */'<span class="">{{ item.sumario }}</span>' +     
                /*              */ '</div>' +            
                /*          */ '</div>' +
                /*      */ '</div>' +
                /*      */'{{#if ctx.hasMore }}' +
                /*      */'{{else}}' +
                /*      */'<div class="portal-portlet-joraa--ellipsis" align="center">...</div>' +
                /*      */'<a target="_blank" href="https://jo.azores.gov.pt/api/public/jornal/pdfOriginal?numeroJornal={{ item.numeroJornal }}&ano={{ item.anoJornal }}&serieId={{ item.idJornal.serieId }}&suplemento=0">' +
                /*      */'<div class="portal-portlet-joraa--second-series-header">' +
                /*        */'<div class="row portal-portlet-joraa--second-series-header-row"> ' +
                /*              */ '<div class="portal-portlet-joraa--second-series-title">' +
                /*                   */ '<span class="portal-portlet-joraa--title-text portal-portlet-joraa--title-text-blue inline-block text-truncate ">JORNAL OFICIAL</span>' +
                /*              */ '</div>' +
                /*              */ '<div class="portal-portlet-joraa--second-series-serie">' +
                /*                   */ '<span class="portal-portlet-joraa--title-text portal-portlet-joraa--title-text-blue">SÉRIE {{ item.serie }} - NÚMERO {{ item.numeroJornal }}</span>' +
                /*              */ '</div>' +            
                /*        */ '</div>' +
                /*              */ '<div class="portal-portlet-joraa--second-series-serie-plus-sign">'+
                /* 		             */'<img src="'+ themeDisplay.getPathThemeImages()+'/more.svg" />' +
                /*              */ '</div>' +   
                /*      */ '</div>' +
                /*      */ '</a>' +
                /*      */'{{/if}}' 
        }
    };

    $core.dom.attach(host, _template);
    $core.instance('rollup', configSerieI).render();
    $core.instance('rollup', configSerieII).render();
});