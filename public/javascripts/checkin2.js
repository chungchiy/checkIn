/*
 * CheckIn App
 * @author: cy
 */

var App = (function(win, doc, $){

	var
		// CONFIG
		containerSel = '#checkIn',
		nameSel = '#name',
		emailSel = '#email',
    errorsSel = '#errors',

		// PRIVATE VARIABLES
		$container,
		$name,
		$email,
    $errors,
    
    valid,
    userError,

		// PRIVATE METHODS
    validateName = function() {
      if($name.val().length < 3) {
        valid = false;
        userError += "Name too short!<br />";
        $name.addClass('error');
      }
    },
    
    sendForm = function() {
      $.ajax({
        data: {
          name: $name.val(),
          email: $email.val()
        },
        type: "POST",
        url: "/users"
      }).done(sendCompleteHandler);
    },

    sendCompleteHandler = function(data) {
      console.log(data);
    },
    
    showErrors = function() {
      $errors.html(userError).show();
    },
    
		submitHandler = function(e) {
			console.log('Form Submitted!');
			e.preventDefault();
      $errors.hide();
      valid = true;
      userError = "";
      validateName();
      if(valid) {
        sendForm();
      }else{
        showErrors();
      }
		},

		// KICK OFF
		init = function() {
			$container = $(containerSel);
			$name = $container.find(nameSel);
			$email = $container.find(emailSel);
			$errors = $container.find(errorsSel);

			$container.on('submit', submitHandler);
		};

	// EXPOSE WHAT YOU NEED
	return {
		init: init
	}
})(window, document, jQuery);



// This would live somewhere else
$(function(){
	App.init();
});