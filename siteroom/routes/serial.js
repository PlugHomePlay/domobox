var portsRunning = [];
var SerialPort = require("serialport").SerialPort;
var serialPort;
var connected;

Serial = function Serial(){
	this.connected = false;
	this.serialPort = null;
}

Serial.prototype.sendData = function(req, res) {
	var data = req.body;
	console.log('Sending data :' + JSON.stringify(datas));
};

Serial.prototype.serialListener = function(portName) {
	// var portName = req.portName;
	// console.log(req);
	var receivedData = "";
    this.serialPort = new SerialPort(portName, {
        baudrate: 9600,
        // defaults for Arduino serial communication
        dataBits: 8,
        parity: 'none',
        stopBits: 1,
        flowControl: false
    }, true, function(error){
		if(error){
			callback(error);
		}
	});
	
	var that = this;
 
    this.serialPort.on("open", function () {
		console.log('open serial communication');
				// Listens to incoming data
		that.serialPort.on('data', function(data) {
			receivedData += data.toString();
			console.log(receivedData);
		});
		that.connected = true;
	});
	
};

Serial.prototype.stateSensor = function(sensor, card) {
	if(!this.connected){
		console.log('error communication not established');
		return;
	}
	this.serialPort.write('E');
}

Serial.prototype.serialClose = function(PortName) {
	
}

exports.Serial = Serial;