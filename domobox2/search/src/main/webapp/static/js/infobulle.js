/*
 * Formacen CSN v0.0.1
 * http://www.kleegroup.com/
 *
 * Copyright 2013 Kleegroup
 * Author : Thomas Gallon
 *
 * Date: Aout 2013
 */
(function($) {

    $.widget("csn.infobulle", {

	/**
	 * Les options.
	 */
	options : {
	    code : null,
	    color : "#000",
	    axe : "Axe"
	},

	/**
	 * Le constructeur.
	 */
	_create : function() {
	    var theCodeToExecute = this.options.code;
	    var color = this.options.color;
	    var axe = this.options.axe;
	    $(this.element).qtip({
		content : {
		    text : function(api) {
			var dynamicValue = String(eval(theCodeToExecute));
			var dynamicText = "<div class=\"infobulle-title\">" + axe + "</div><div class=\"infobulle-value\" style=\"color:" + color + "\">" + dynamicValue + "</div>";
			return dynamicText;
		    }
		},
		position : {
		    my : 'bottom center',
		    at : 'top center',
		// target : $('.selector')
		},
		style : {
		    classes : 'qtip-blue qtip-bootstrap'
		},
		show : {
		    delay : 0,
		// distance : 100,
		},
	    });
	},

    });

}(jQuery));
