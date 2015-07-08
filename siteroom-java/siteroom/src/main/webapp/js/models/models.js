window.Card = Backbone.Model.extend({
	
	urlRoot: "siteroom/cards",
	
	idAttribute: "id",
	
	initialize: function () {
		this.validators = {};
		
		this.validators.name = function(value) {
			return value.length > 0 ? {isValid: true} : {isValid:false, message: "You must enter a name"};
		};
		
		this.validators.portName = function(value) {
			return value.length > 0 ? {isValid: true} : {isValid:false, message: "You must enter a port name"}; 
		};
	},
	
	validateItem: function(key) {
		return (this.validators[key]) ? this.validators[key](this.get(key)) : {isValid: true};
	},
	
	validateAll: function() {
		var messages = {};
		
		for (var key in this.validators) {
			if(this.validators.hasOwnProperty(key)) {
				var check = this.validators[key](this.get(key));
				if (check.isValid === false) {
					messages[key] = check.message;
				}
			}
		}
		
		return _.size(messages) > 0 ? {isValid: false, messages: messages} : {isValid: true};
	},
	
	defaults: {
		id: null,
		name: "",
		portName: "",
		picture: null,
		createdAt: null,
		description : ""
	}
});

window.CardCollection = Backbone.Collection.extend({
	
	model: Card,
	
	url: "siteroom/cards"
	
});
