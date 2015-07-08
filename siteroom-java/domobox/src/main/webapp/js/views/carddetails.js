window.CardView = Backbone.View.extend({

    initialize: function () {
        this.render();
    },

    render: function () {
        $(this.el).html(this.template(this.model.toJSON()));
        return this;
    },

    events: {
        'change'           : "change",
        'click .save'      : "beforeSave",
        'click .delete'    : "deleteCard",
		'click .start'     : "startCard",
		'drop #picture'    : "dropHandler"		
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
        this.saveCard();
        return false;
    },

    saveCard: function () {
        var self = this;
        console.log('before save');
        this.model.save(null, {
            success: function (model) {
                self.render();
                app.navigate('cards/' + model.id, false);
                utils.showAlert('Success!', 'Card saved successfully', 'alert-success');
            },
            error: function () {
                utils.showAlert('Error', 'An error occurred while trying to save this card', 'alert-error');
            }
        });
    },

    deleteCard: function () {
        this.model.destroy({
            success: function () {
                alert('Card deleted successfully');
                window.history.back();
            }
        });
        return false;
    },
	
	startCard : function() {
		var model = this.model.toJSON();
		console.log("request pull up for :" + model._id + " on the port : " + model.portName);
		
		var socket = io.connect("/");
		socket.on("message",function(message){  
			console.log("Message from the server arrived")
			message = JSON.parse(message);
			console.log(message);
			$('#content').append('<div >'+message.data+'</div>');
		});
		
		var data = { /*creating a Js ojbect to be sent to the server*/ 
			message:model, /*getting the text input data      */
			author:'pbruant',
			action:'startListener'			
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