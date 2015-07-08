var AppRouter = Backbone.Router.extend({

	routes: {
		"" : "home",
		"about" : "about",
		"cards" : "cardList",
		"cards/add" : "addCard"
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
	
	cardList: function(page) {
		var p = page ? parseInt(page, 10) : 1;
		var cardList = new CardCollection();
		cardList.fetch({success: function(){
			$("#content").html(new CardListView({model:cardList, page:p}).el);
		}});
		this.headerView.selectMenuItem('card-menu');
	},
	
	addCard: function() {
		var card = new Card();
		$('#content').html(new CardView({model:card}).el);
		this.headerView.selectMenuItem('add-menu');
	}
	
});

utils.loadTemplate(['HomeView', 'HeaderView', 'AboutView', 'CardListItemView', 'CardView'], function() {
	app = new AppRouter();
	Backbone.history.start();
});