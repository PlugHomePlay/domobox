var img;

function getTemperature() {
	var iTemp = 20;

	// Sanity checks
	if (iTemp > 50) {
		iTemp = 50;
	} else if (iTemp < -30) {
		iTemp = -30;
	}

	return iTemp;
}

function getRatio(iTemp) {
	/* The image is not in proportion this the gauge to pixel 
	 * ratio need slight adjustment
	 */

	var iRatio;

	if (iTemp > 0) {
		iRatio = 3.55;
	} else if (iTemp <= 0) {
		iRatio = 3.45;
	} else if (iTemp < -20) {
		iRatio = 3.385;
	}

	return iRatio;
}

function convertTempToScreenCoord(iRatio, iTemp) {
	/* Algorithm to convert the temperature to the correct x screen coord.
	 * Odd, but works!
	 */
	var iMAX_TEMP = 50,
		iSTART_Y_POS = 73.5;

	return iSTART_Y_POS + (iRatio * (iMAX_TEMP - iTemp));
}

function drawLiquid(ctx, iTempToYCoord) {
	/* Draw red rectangle to represent the fluid in the glass tube
	 * Coordinates you Y and are fixed!
	 * TODO: Calculare Y coord base on image X,Y
	 */

	var iX_POS = 45,
		iY_POS = 5,
		iY_OFFSET = 343,
		iWIDTH = 12;

	ctx.fillStyle = "rgb(200,0,0)";

	// Draw rectangle from -30 to iTempToYCoord
	ctx.fillRect(iX_POS, iTempToYCoord, iY_POS, iY_OFFSET - iTempToYCoord);

	// Draw rectangle from botton to -30
	ctx.fillRect(iX_POS, iY_OFFSET, iY_POS, iWIDTH);

	ctx.stroke();
}

function imgOnLoaded() {
	/* Simply grabs a handle to the canvas element and
	 * check the context (Canvas support). 
	*/

	var canvas = document.getElementById('thermometer'),
		ctx = null,
		iTemp = 0,
		iRatio  = 0,
		iTempToYCoord = 0;

	// Canvas supported?
	if (canvas.getContext) {

		ctx = canvas.getContext('2d');
		iTemp = getTemperature();
		iRatio = getRatio(iTemp);
		iTempToYCoord = convertTempToScreenCoord(iRatio, iTemp);

		// Draw the loaded image onto the canvas
		ctx.drawImage(img, 0, 0, 100, 450);

		// Draw the liquid level
		drawLiquid(ctx, iTempToYCoord);

	} else {
		alert("Canvas not supported!");
	}
}

function draw() {
	/* Main entry point got the thermometer Canvas example
	 * Simply grabs a handle to the canvas element and
	 * check the conect (Canvas support). 
	 */

	var canvas = document.getElementById('thermometer');

	// Create the image resource
	img = new Image();

	// Canvas supported?
	if (canvas.getContext) {
		// Setup the onload event
		img.onload = imgOnLoaded;

		// Load the image
		img.src = 'img/thermometer-template.png';
	} else {
		alert("Canvas not supported!");
	}
	
	stateSensor = function() {
		var model = this.model.toJSON();
		console.log("request pull up for :" + model._id + " on the : " + model.name);
		
		var socket = io.connect("/");
		socket.on("message",function(message){  
			console.log("Message from the server arrived")
			message = JSON.parse(message);
			console.log(message);
		});
		
		var data = { /*creating a Js ojbect to be sent to the server*/ 
			message:model, /*getting the text input data      */
			author:'pbruant',
			action:'stateSensor'			
		}
		socket.send(JSON.stringify(data)); 
	};
	
	stateSensor();
}

function setTempAndDraw() {
	/* Function called when user clicks the draw button
	 */

	var temp = document.getElementById('temp'),
		slider = document.getElementById('defaultSlider');

	if (temp !== null && slider !== null) {
		temp.value = slider.value;
		draw();
	}
}