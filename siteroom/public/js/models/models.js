window.Sensor = Backbone.Model.extend({

    urlRoot: "/sensors",

    idAttribute: "_id",

    initialize: function () {
        this.validators = {};

        this.validators.name = function (value) {
            return value.length > 0 ? {isValid: true} : {isValid: false, message: "You must enter a name"};
        };
		
		this.validators.card = function (value) {
            return value.length > 0 ? {isValid: true} : {isValid: false, message: "You must enter a Card"};
        };
		
		this.validators.role = function (value) {
            return value.length > 0 ? {isValid: true} : {isValid: false, message: "You must enter a Role"};
        };

        // this.validators.grapes = function (value) {
            // return value.length > 0 ? {isValid: true} : {isValid: false, message: "You must enter a grape variety"};
        // };

        // this.validators.country = function (value) {
            // return value.length > 0 ? {isValid: true} : {isValid: false, message: "You must enter a country"};
        // };
    },

    validateItem: function (key) {
        return (this.validators[key]) ? this.validators[key](this.get(key)) : {isValid: true};
    },

    // TODO: Implement Backbone's standard validate() method instead.
    validateAll: function () {

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
        _id: null,
        name: "",
        description: "",
        picture: null,
		card: "",
		role: "",
		pin: "",
		mode: "",
		createdAt:"",
		modele:"thermometer",
		sheduled:0
    }
});

window.SensorCollection = Backbone.Collection.extend({

    model: Sensor,

    url: "/sensors"

});

window.Pair = Backbone.Model.extend({
	
	urlRoot: "/pairs",
	
	idAttribute: "_id",
	
	initialize: function() {
		
	},
	
	defaults: {
		_id: null,
		date: "",
		value: "",
		key: 0.0
	}
});

window.PairCollection = Backbone.Collection.extend({
	model: Pair,
	url: "/pairs",
	
	byValue : function(value){
		filtered = this.filter(function(pair){
			return pair.get("value")===value;
		});
		return new PairCollection(filtered);
	},
	
	byValueAndDate: function(value, hours){
		filtered = this.filter(function(pair){
			var dateLimit = new Date().setHours(new Date().getHours() - hours);
			var dateAction = new Date(pair.get("date")).getTime();
			// console.log(new Date(pair.get("date")).getTime() + ' > ' + dateLimit);
			return pair.get("value")===value &&  dateAction > dateLimit;
		});
		return new PairCollection(filtered);
	}
});

window.Card = Backbone.Model.extend({
	
	urlRoot: "/cards",
	
	idAttribute: "_id",
	
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
		_id: null,
		name: "",
		portName: "",
		picture: null,
		createdAt: null,
		description : ""
	}
});

window.CardCollection = Backbone.Collection.extend({
	
	model: Card,
	
	url: "/cards"
	
});
