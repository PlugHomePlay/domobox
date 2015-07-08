module.exports = {
	"Add sensor" : function (browser) {
		browser
			.url("http://127.0.0.1:3000/#sensors/add")
			.waitForElementVisible('body', 1000)
			.setValue('#name', 'test insertion')
			
	}
};