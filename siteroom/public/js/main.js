var AppRouter = Backbone.Router.extend({

	routes: {
		"" : "home",
		"about" : "about",
		"sensors" : "sensorList",
		"sensors/page/:page" : "sensorList",
		"sensors/add" : "addSensor",
		"sensors/:id" : "sensorDetail",
		"cards" : "cardList",
		"cards/page/:page" : "cardList",
		"cards/add" : "addCard",
		"cards/:id" : "cardDetail",
		"pairs/value/:id" : "sensorState",
		"dashboard/:id" : "dashboard"
	},

	initialize: function() {
		this.headerView = new HeaderView();
		$('.header').html(this.headerView.el);
	},

	home: function() {
		if(!this.homeView) {
			this.homeView = new HomeView();
		}
		$('#content').html(this.homeView.el);
		//this.headerView.selectMenuItem('home-menu');
	},
	
	about: function() {
		if(!this.aboutView) {
			this.aboutView = new AboutView();
		}
		$('#content').html(this.aboutView.el);
		this.headerView.selectMenuItem('about-menu');
	},
	
	sensorList: function(page) {
		var p = page ? parseInt(page, 10) : 1;
		var sensorList = new SensorCollection();
		sensorList.fetch({success: function(){
			$("#content").html(new SensorListView({model:sensorList, page:p}).el);
		}});
		this.headerView.selectMenuItem('sensor-menu');
	},
	
	addSensor: function() {
		var sensor = new Sensor();
		$('#content').html(new SensorView({model:sensor}).el);
		this.headerView.selectMenuItem('sensor-menu');
	},
	
	sensorDetail: function(id) {
		var sensor = new Sensor({_id: id});
		sensor.fetch({success: function() {
			$('#content').html(new SensorView({model: sensor}).el);
		}});
		this.headerView.selectMenuItem('sensor-menu');
	},
	
	cardList: function(page) {
		var p = page ? parseInt(page, 10) : 1;
		var cardList = new CardCollection();
		cardList.fetch({success: function(){
			$("#content").html(new CardListView({model:cardList, page:p}).el);
		}});
		this.headerView.selectMenuItem('card-menu');
	},
	
	cardDetail: function(id) {
		var card = new Card({_id: id});
		card.fetch({success: function() {
			$('#content').html(new CardView({model: card}).el);
		}});
		this.headerView.selectMenuItem();
	},
	
	addCard: function() {
		var card = new Card();
		$('#content').html(new CardView({model:card}).el);
		this.headerView.selectMenuItem('add-menu');
	},
	
	sensorState : function(id) {
		var pairList = new PairCollection();
		pairList.fetch({success: function(){
			var listItem = pairList.byValueAndDate(id, 1);
			console.log(listItem);
			$('#content').html(new StateView({model:listItem}).el);
		}});
		this.headerView.selectMenuItem('sensor-menu');
	},

	dashboard : function(id) {
		$('#content').html(new DashboardView().el);
		this.headerView.selectMenuItem('dashboard-menu');
	}
});

utils.loadTemplate(['HomeView', 'HeaderView', 'AboutView', 'SensorView', 'SensorListItemView', 'CardView', 'CardListItemView', 'StateView', 'DashboardView'], function() {
	app = new AppRouter();
	Backbone.history.start();
});