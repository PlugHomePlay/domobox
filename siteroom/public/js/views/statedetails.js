window.StateView = Backbone.View.extend({

	initialize: function (options) {
		this.options = options || {};
        this.render();
    },

    render: function () {
		var states = this.model.models;
		var len = states.length;
		
		var valueX = "";
		var valueY = "";
		
		states.forEach(function(list){
			var date = new Date(list.get("date"));
			var hour = date.getHours();
			var min = date.getMinutes();
			var sec = date.getSeconds();


			var time = "'" + ((hour < 10 ? "0" : "") + hour) + ":" + ((min < 10 ? "0" : "") + min) + ":" + ((sec < 10 ? "0" : "") + sec) + "'";
			valueX = valueX + time + ", ";
			valueY = valueY + list.get("key") + ", ";
		});
		
		var graph = new Graph();
		graph.attributes.valueX = valueX;
		graph.attributes.valueY = valueY;
		
		console.log(valueX);
		console.log(valueY);
		$(this.el).html(this.template(graph.toJSON()));
		//$(this.el).html('<ul class="thumbnails"></ul>');
		return this;
    }
});

window.Graph = Backbone.Model.extend({
	
	urlRoot: "/graph",
	
	idAttribute: "_id",
	
	initialize: function() {
		
	},
	
	defaults: {
		_id: null,
		valueX: "",
		valueY: ""
	}
});