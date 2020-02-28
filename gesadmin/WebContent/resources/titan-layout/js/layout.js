/** 
 * PrimeFaces Titan Layout
 */
PrimeFaces.widget.Titan = PrimeFaces.widget.BaseWidget.extend({
  
    init: function(cfg) {
        this._super(cfg);
        this.menuWrapper = $('#layout-menu-cover');
        this.menu = this.menuWrapper.find('ul.layout-menu');
        this.menulinks = this.menu.find('a.menulink');
        this.menuButton = $('#menu-button');
        this.topmenuButton = $('#topmenu-button');
        this.topbarMenu = $('#topbar-menu');
        this.expandedMenuitems = this.expandedMenuitems||[];
        this.layoutMenuClick = false;
        this.topbarMenuClick = false;
 
        this.restoreMenuState();
        this.bindEvents();
    },
    
    bindEvents: function() {
        var $this = this;
        
        this.menuButton.off('click.menubutton').on('click.menubutton',function(e) { 
            $this.layoutMenuClick = true;
            
            if($this.menuWrapper.hasClass('active')){
                $this.menuButton.removeClass('active');
                $this.menuWrapper.removeClass('active');
            }
            else{
                $this.menuButton.addClass('active');
                $this.menuWrapper.addClass('active');
                $this.topbarMenu.removeClass('active');
                $this.topmenuButton.removeClass('active');
            }
            
            // Equalizing of height of first level nodes of main menu
            equalheight('.WideMenu .layout-menu > li');
            e.preventDefault();
        });
        
        this.topmenuButton.off('click.topmenubutton').on('click.topmenubutton',function(e) {   
            $this.topbarMenuClick = true;
            
            if($this.topbarMenu.hasClass('active')){
                $this.topbarMenu.removeClass('active');
                $this.topmenuButton.removeClass('active');
            }
            else{
                $this.topbarMenu.addClass('active');
                $this.topmenuButton.addClass('active');
                $this.menuButton.removeClass('active');
                $this.menuWrapper.removeClass('active');
            }
            e.preventDefault();
        });
       
        this.menulinks.on('click',function(e) {
            var menuitemLink = $(this),
            menuitem = menuitemLink.parent();
            
            if(menuitem.hasClass('active-menu-parent')) {
                menuitem.removeClass('active-menu-parent');
                menuitemLink.removeClass('active-menu').next('ul').removeClass('active-menu');
                $this.removeMenuitem(menuitem.attr('id'));
            }
            else {
                var activeSibling = menuitem.siblings('.active-menu-parent');
                if(activeSibling.length) {
                    activeSibling.removeClass('active-menu-parent');
                    $this.removeMenuitem(activeSibling.attr('id'));

                    activeSibling.find('ul.active-menu,a.active-menu').removeClass('active-menu');
                    activeSibling.find('li.active-menu-parent').each(function() {
                        var menuitem = $(this);
                        menuitem.removeClass('active-menu-parent');
                        $this.removeMenuitem(menuitem.attr('id'));
                    });
                }

                menuitem.addClass('active-menu-parent');
                menuitemLink.addClass('active-menu').next('ul').addClass('active-menu');
                $this.addMenuitem(menuitem.attr('id'));
            }

            if(menuitemLink.next().is('ul')) {
                e.preventDefault();
            }
            else {
                $this.menuButton.removeClass('active');
                $this.menuWrapper.removeClass('active');
            }
            // Equalizing of height of first level nodes of main menu
            equalheight('.WideMenu .layout-menu > li');
            
            $this.saveMenuState();
        });
        
        this.menuWrapper.on('click', function() {
            $this.layoutMenuClick = true;
        });
        
        this.topbarMenu.on('click', function() {
            $this.topbarMenuClick = true;
        });
        
        this.clickNS = 'click.' + this.id;
        $(document.body).off(this.clickNS).on(this.clickNS, function (e) {
            if(!$this.layoutMenuClick) {
                $this.menuButton.removeClass('active');
                $this.menuWrapper.removeClass('active');
            }
            
            if(!$this.topbarMenuClick) {
                $this.topbarMenu.removeClass('active');
                $this.topmenuButton.removeClass('active');
            }
            
            $this.layoutMenuClick = false;
            $this.topbarMenuClick = false;
        });
    },
    
    removeMenuitem: function(id) {        
        this.expandedMenuitems = $.grep(this.expandedMenuitems, function(value) {
            return value !== id;
        });
    },
    
    addMenuitem: function(id) {
        if($.inArray(id, this.expandedMenuitems) === -1) {
            this.expandedMenuitems.push(id);
        }
    },
    
    saveMenuState: function() {
        $.cookie('titan_expandeditems', this.expandedMenuitems.join(','), {path:'/'});
    },
    
    clearMenuState: function() {
        $.removeCookie('titan_expandeditems', {path:'/'});
    },
    
    restoreMenuState: function() {
        var menucookie = $.cookie('titan_expandeditems');
        if(menucookie) {
            this.expandedMenuitems = menucookie.split(',');
            for(var i = 0; i < this.expandedMenuitems.length; i++) {
                var id = this.expandedMenuitems[i];
                if(id) {
                    var menuitem = $("#" + this.expandedMenuitems[i].replace(/:/g,"\\:"));
                    menuitem.addClass('active-menu-parent');
                    menuitem.children('a,ul').addClass('active-menu');
                }             
            }
        }
    },
    
    isMobile: function() {
        return (/Android|webOS|iPhone|iPad|iPod|BlackBerry|IEMobile|Opera Mini/i.test(window.navigator.userAgent));
    },
 
    changeBodyClass: function(changedClass) {
        var body = $('body');

        switch (changedClass) {
            case 'BoxedLayout':
                body.addClass('BoxedLayout');
                break;
            
            case 'FlyingTopbar':
                body.removeClass('FixedTopbar').addClass('FlyingTopbar');
                break;
            
            case 'FixedTopbar':
                body.removeClass('FlyingTopbar').addClass('FixedTopbar')
            break;
            
            case 'SlimMenu':
                body.removeClass('WideMenu').addClass('SlimMenu');
                $('.SlimMenu .layout-menu> li').css('height', 'auto');
                break;
            
            case 'WideMenu':
                body.removeClass('SlimMenu').addClass('WideMenu');
                break;
        }
        
        return false;
    }
});

/* Issue #924 is fixed for 5.3+ and 6.0. (compatibility with 5.3) */
if(window['PrimeFaces'] && window['PrimeFaces'].widget.Dialog) {
    PrimeFaces.widget.Dialog = PrimeFaces.widget.Dialog.extend({
        
        enableModality: function() {
            this._super();
            $(document.body).children(this.jqId + '_modal').addClass('ui-dialog-mask');
        },
        
        syncWindowResize: function() {}
    });
}
