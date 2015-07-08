var SerialPort = require("serialport").SerialPort;

var receivedData = "";
serialPort = new SerialPort("COM4", {
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

serialPort.on("open", function () {
	console.log('open serial communication');
			// Listens to incoming data
	serialPort.on('data', function(data) {
		receivedData += data.toString();
		console.log(receivedData);
	});
});