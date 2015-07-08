var mongo = require('mongodb');
var BSON = mongo.BSONPure;

CollectionDriver = function(db) {
	this.db = db;
}

CollectionDriver.prototype.getCollection = function(collectionName, callBack) {
	this.db.collection(collectionName, function(error, collection) {
		if(error) {
			callBack(error);
		} else {
			callBack(null, collection);
		}
	});
};

CollectionDriver.prototype.findAll = function(collectionName, callBack) {
	this.getCollection(collectionName, function(error, collection){
		collection.find().toArray(function(error, results){
			callBack(null, results);
		});
	});	
};

CollectionDriver.prototype.find = function(collectionName, query, callBack) {
	console.log(collectionName);
	console.log(query);
	
	this.getCollection(collectionName, function(error, collection){
		console.log(query);
		collection.find({value:query}).toArray(function(error, results){
			callBack(null, results);
		});
	});	
};

CollectionDriver.prototype.findById = function(collectionName, id, callBack) {
	this.getCollection(collectionName, function(error, collection){
		var checkForHexRegExp = new RegExp("^[0-9a-fA-F]{24}$");
		if(!checkForHexRegExp.test(id)){
			callBack({error: 'invalid id'});
		} else {
			collection.findOne({'_id':new BSON.ObjectID(id)}, function(error, items) {
				callBack(null, items);
			});
		}
	});
};

CollectionDriver.prototype.save = function(collectionName, item, callBack) {
	this.getCollection(collectionName, function(error, collection){
		item.createdAt = new Date();
		collection.insert(item, function() {
			callBack(null, item);
		});
	});
}

CollectionDriver.prototype.delete = function(collectionName, id, callBack) {
	this.getCollection(collectionName, function(error, collection) { 
		collection.remove({'_id':new BSON.ObjectID(id)}, function(error,doc) { 
			if (error) callBack(error);
			else callBack(null, doc);
		});
    });
}

CollectionDriver.prototype.update = function(collectionName, id, item, callBack) {
	this.getCollection(collectionName, function(error, collection) {
		delete item._id;
		// console.log('Updating item: ' + id);
		// console.log(JSON.stringify(item));
		collection.update({'_id':new BSON.ObjectID(id)}, item, {safe:true}, function(error,doc) { 
			if (error) 
				callBack(error);
			else 
				callBack(null, doc);
		});
    });
};


CollectionDriver.prototype.initDb = function(){
	var mongoHost = '127.0.0.1';
	var mongoPort = 27017;
	var collectionDriver;
	var mongoClient = new MongoClient(new Server(mongoHost, mongoPort));
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
	return collectionDriver;
};

exports.CollectionDriver = CollectionDriver;