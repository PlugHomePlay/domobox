window.SensorView = Backbone.View.extend({

    initialize: function () {
        this.render();
    },

    render: function () {
		var that = this;
		
        $(this.el).html(this.template(this.model.toJSON()));
				
		//add cards collection to view
		var cards = new CardCollection();
		cards.fetch({success: function(){
			_.each(cards, function(element, index, list) {
				var cardName = list.models[index].attributes.name;
				var cardId = list.models[index].attributes._id;
				$("#card").append('<option value="' + cardId + '"' + (that.model.attributes.card == cardId?'selected':'') + '>' + cardName + '</option>');
			});
		}});
        return this;
    },

    events: {
        "change"        : "change",
        "click .save"   : "beforeSave",
        "click .delete" : "deleteSensor",
		"click .state"  : "stateSensor",
        "drop #picture" : "dropHandler"
    },

    change: function (event) {
        // Remove any existing alert message
        utils.hideAlert();

        // Apply the change to the model
        var target = event.target;
        var change = {};
        change[target.name] = target.value;
        this.model.set(change);

        // Run validation rule (if any) on changed item
        var check = this.model.validateItem(target.id);
        if (check.isValid === false) {
            utils.addValidationError(target.id, check.message);
        } else {
            utils.removeValidationError(target.id);
        }
    },

    beforeSave: function () {
        var self = this;
        var check = this.model.validateAll();
        if (check.isValid === false) {
            utils.displayValidationErrors(check.messages);
            return false;
        }
        this.saveSensor();
        return false;
    },

    saveSensor: function () {
        var self = this;
        console.log('before save');
        this.model.save(null, {
            success: function (model) {
                self.render();
                app.navigate('sensors/' + model.id, false);
                utils.showAlert('Success!', 'Sensor saved successfully', 'alert-success');
            },
            error: function () {
                utils.showAlert('Error', 'An error occurred while trying to save this item', 'alert-error');
            }
        });
    },

    deleteSensor: function () {
        this.model.destroy({
            success: function () {
                //alert('Sensor deleted successfully');
                window.history.back();
            }
        });
        return false;
    },
	
	stateSensor: function() {
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
			
		return false;
	},

    dropHandler: function (event) {
        event.stopPropagation();
        event.preventDefault();
        var e = event.originalEvent;
        e.dataTransfer.dropEffect = 'copy';
        this.pictureFile = e.dataTransfer.files[0];

        // Read the image file from the local file system and display it in the img tag
        var reader = new FileReader();
        reader.onloadend = function () {
            $('#picture').attr('src', reader.result);
        };
        reader.readAsDataURL(this.pictureFile);
    }

});