window.StateView = Backbone.View.extend({

	initialize: function (options) {
		this.options = options || {};
        this.render();
    },

    render: function () {
		var states = this.model.models;
		var len = states.length;
		
		console.log(states);
		
		$(this.el).html('<ul class="thumbnails"></ul>');
		
		for (var i = 0; i < len; i++) {
            $('.thumbnails', this.el).append(new StateItemView({model: states[i]}).render().el);
        }
		// var that = this;
		// console.log(this.model.toJSON());
        // $(this.el).html(this.template(this.model.toJSON()));
		// add cards collection to view
		// var card = new Card();
		// card.fetch();
	
		// $(this.el).html($('#thermometerView').html());
		// console.log($("#stateRepresentation"));
		// $("#stateRepresentation").append("<canvas id=\"thermometer\" width=\"100\" height=\"450\"></canvas>"); //"<script src=\"tpl/stateView/thermometer.js\"></script>"; 
        return this;
    }
});

window.StateItemView = Backbone.View.extend({
	
	tagName: 'li',
	
	initialize: function() {
		this.model.bind("change", this.render, this);
		this.model.bind("destroy", this.render, this);
	},
	
	render: function() {
		$(this.el).html(this.template(this.model.toJSON()));
        return this;
	}
});