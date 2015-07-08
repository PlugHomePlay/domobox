var MongoClient = require('mongodb').MongoClient;
var Server = require('mongodb').Server;
var CollectionDriver = require('./driver').CollectionDriver;

var mongoHost = '127.0.0.1';
var mongoPort = 27017;
var mongoClient = new MongoClient(new Server(mongoHost, mongoPort));

var CronJob = require('cron').CronJob;
var fs = require('fs');

// var collectionDriver = mongoClient.open();

mongoClient.open(function(error, mongoClient) {
	if(!mongoClient) {
		console.error('Error! Exiting... Must start MongoDB first.');
		process.exit(1);
	} else {
		console.log("Connected 'sensordb' database");
	}
	var db = mongoClient.db('sensordb');
	collectionDriver = new CollectionDriver(db);
});

exports.findAll = function(req, res) {
	var params = req.params;
	collectionDriver.findAll(req.params.collection, function(error, objs) {
		if(error) {
			res.status(400).send(error);
		} else {
			if(req.accepts('html')) {
				res.send(objs);; //send({objects: objs, collection: req.params.collection});
			} else {
				res.status(200).send(objs);
			}
		}
	})
};

exports.findPairs = function(req, res) {
	var params = req.params;
	var id = params.id;
	
	console.log('recherche des valeurs pour ' + id + ' dans la collection ' + 'pairs');
	collectionDriver.find('pairs', id, function(error, objs) {
		if(error) {
			res.status(400).send(error);
		} else {
			if(req.accepts('html')) {
				res.send({objects: objs, collection: req.params.collection});
			} else {
				res.status(200).send(objs);
			}
		}
	})
};

exports.findById = function(req, res) {
	var params = req.params;
	var id = params.id;
	var collection = params.collection;
	console.log(collection + ' ' + id);
	if(id) {
		collectionDriver.findById(collection, id, function(error, items) {
			res.send(items);
			// console.log(items);
		});
	} else {
		res.send({error: 'bad url', url: req.url});
	}
};

exports.findSensorById = function(req, res) {
	var params = req.params;
	var id = params.id;
	var collection = 'sensors';
	console.log(collection + ' ' + id);
	if(id) {
		collectionDriver.findById(collection, id, function(error, items) {
			res.send(items);
			console.log(items);
		});
	} else {
		res.send({error: 'bad url', url: req.url});
	}
};

exports.findItemById = function(collection, id, callback) {
	if(id) {
		collectionDriver.findById(collection, id, function(error, items) {
			callback(null,items);
		});
	} else {
		callback({error: 'bad url', url: req.url});
	}
};

exports.addItem = function(req, res) {
	var params = req.params;
	var item = req.body;
	// console.log('Adding item: ' + JSON.stringify(item));
	var collection = params.collection;
	collectionDriver.save(collection, item, function(error, item) {
		if (error) {
			res.send({'error':'An error has occured'});
		} else {
			// console.log('Success: ' + JSON.stringify(item));
			res.send(item);
		}
	});
};

saveItem = function(collection, item, callback) {
	collectionDriver.save(collection, item, function(error, item) {
		if (error) {
			callback({'error':'An error has occured'});
		} else {
			// console.log('Success: ' + JSON.stringify(item));
			callback(null, item);
		}
	});
};

exports.delete = function(req, res) {
	var params = req.params;
	var id = params.id;
    // console.log('Deleting item: ' + id);
	var collection = params.collection;
	collectionDriver.delete(collection, id, function(error, item) {
		if(error){
			res.send({'error':'An error has occured'});
		} else {
			// console.log( item + ' document(s) deleted');
			res.send(req.body);
		}
	});
};

exports.update = function(req, res) {
	var params = req.params;
	var id = params.id;
	var item =  req.body;
	var collection = params.collection;
	collectionDriver.update(collection, id, item, function(error, result) {
		if(error){
			res.send({'error':'An error has occured'});
		} else {
			addCronJob(item, id);			
			res.send(req.body);
		}
	});
};

addCronJob = function(item, id){
	if(item.sheduled!=undefined && item.sheduled > 0){
		var c = new CronJob('*/' + item.sheduled + ' * * * * *', 
			(function(id){
				return function(){
					//get state sensor._id
					var state = Math.round((Math.random() * 3 + 17)*100)/100;
					var pair = new Pair(id, state);
					saveItem('pairs', pair, function(error){
						console.error(error);
					});
				};
			})(id), 
			null, 
			true, 
			"Europe/Paris");
	}
};

function Pair(value, key){
	this._id = null;
	this.date = new Date();
	this.value = value;
	this.key = key;
}