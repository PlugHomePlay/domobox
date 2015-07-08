var express = require('express');
var path = require('path');
var io = require('socket.io');

var favicon = require('serve-favicon');
var logger = require('morgan');
var bodyParser = require('body-parser');
var errorHandler = require('errorhandler');

var elements = require('./routes/items');
var serials = {};

var app = express();
var server = require('http').Server(app);

var Serial = require('./routes/serial').Serial;

app.set('port', process.env.PORT || 3000);
app.set('views', path.join(__dirname, 'views'));

app.use(favicon(__dirname + '/public/favicon.ico'));
app.use(logger('dev'));

app.use(bodyParser.json());
app.use(express.static(path.join(__dirname, 'public')));

app.get('/pairs/value/:id', elements.findPairs);
app.get('/:collection', elements.findAll);
app.get('/:collection/:id', elements.findById);

app.post('/:collection', elements.addItem);
app.put('/:collection/:id', elements.update);
app.delete('/:collection/:id', elements.delete);

var application = app.listen(app.get('port'), function(){
	console.log("Express server listening on port " + app.get('port'));
});

io = io.listen(application);

io.sockets.on("connection", function(socket){
	var message_to_client = {
		data:"Connection with the server established"
	}
	socket.send(JSON.stringify(message_to_client)); 
    socket.on("message",function(data){
        data = JSON.parse(data);
        // console.log(data);
		
		var action = data.action;
		var serial = new Serial();
		
		if(action==='startListener'){
			if(data.message.portName in serials){
				console.log('do nothing');
			} else {
				serial.serialListener(data.message.portName);
				serials[data.message.portName] = serial;
			}
		} else if(action==='stateSensor'){
			//Get card
			var sensor = data.message;
			var card = elements.findItemById('cards', data.message.card, function(error, card){
				
				if(card.portName in serials){
					serial = serials[card.portName];
				}
				serial.stateSensor(sensor, card);
			});
			
		}
		//socket.send(JSON.stringify(ack_to_client));
    });
});


