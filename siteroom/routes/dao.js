var mongoose = require('mongoose');

var mongoHost = '127.0.0.1';
var mongoPort = 27017;
var mongoDataBase = 'adminRoom';

connection = function(){
	mongoose.connect('mongodb://'+mongoHost+':' + mongoPort + '/'+mongoDataBase, function(error){
		if(error){
			throw error;
		}
	});
};

var cardSchema = new mongoose.Schema({
	_id: null,
	name: String,
	portName: String,
	picture: String,
	createdAt: {type : Date, default : Date.now},
	description : String
});

var cardModel = mongoose.model('cards', cardSchema);

exports.addCard = function(req, res) {
	var params = req.params;
	var item = req.body;
	
	console.log('Adding item: ' + JSON.stringify(item));
	
	var card = new cardModel();
	card.name = item.name;
	card.portName = item.portName;
	card.picture = item.picture;
	card.createdAt = item.createdAt;
	card.description = item.description;
	
	card.save(function(error){
		if(error){
			throw error;
		}
		
	})
	// var collection = params.collection;
	// collectionDriver.save(collection, item, function(error, item) {
		// if (error) {
			// res.send({'error':'An error has occured'});
		// } else {
			// console.log('Success: ' + JSON.stringify(item));
			// res.send(item);
		// }
	// });
};