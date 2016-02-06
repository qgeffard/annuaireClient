$(document).ready(function() {

	var editFormButton = function() {
		$("#submit_create_client_form").hide();
		$("#submit_update_client_form").show();
		$("#cancel_client_form").show();
	};

	var createFormButton = function() {
		$("#submit_create_client_form").show();
		$("#submit_update_client_form").hide();
		$("#cancel_client_form").hide();
	};

	var resetInputForm = function() {
		$("#input_email").val("");
		$("#input_contact_name").val("");
		$("#input_entreprise").val("");
	}

	var getAllClient = function() {
		$("#tbody_table_client").html("");

		$.ajax({
			url : '/annuaireView/rest/client',
			type : 'GET'
		}).done(function(data) {
			data.forEach(function(element, index, array) {
				var appender = "<td class=\"entreprise\">" + element.entreprise + "</td>";
				appender += "<td class=\"name\">" + element.name + "</td>";
				appender += "<td class=\"email\">" + element.email + "</td>";
				appender += "<td><a class=\"text-faded edit_client\"><span class=\"glyphicon glyphicon-edit\"></span></a></td>";
				appender += "<td><a class=\"text-faded remove_client\"><span class=\"glyphicon glyphicon-remove\"></span></a></td>";
				appender = "<tr>" + appender + "</tr>";
				$(appender).appendTo('#tbody_table_client');
			});

			$(".edit_client").on("click", function(event) {
				event.preventDefault();

				$("#input_email").val($(this).parent().parent().find(".email").text());
				$("#input_contact_name").val($(this).parent().parent().find(".name").text());
				$("#input_entreprise").val($(this).parent().parent().find(".entreprise").text());

				editFormButton();
			});

			$(".remove_client").on("click", function(event) {
				event.preventDefault();
				$.ajax({
					url : '/annuaireView/rest/client/delete',
					type : 'POST',
					data : {
						name : $(this).parent().parent().find(".email").text(),
						entreprise : $(this).parent().parent().find(".name").text(),
						email : $(this).parent().parent().find(".entreprise").text()
					}
				}).done(function() {
					console.log("success");
					getAllClient();
				}).fail(function() {
					console.log("error");
				});
			});
		}).fail(function() {
			console.log("error");
		});
	};

	$("#submit_create_client_form").on("click", function(event) {
		event.preventDefault();

		var email = $("#input_email").val();
		var name = $("#input_contact_name").val();
		var entreprise = $("#input_entreprise").val();

		if (!email || !name || !entreprise) {
			return;
		}
		
		$.ajax({
			url : '/annuaireView/rest/client/create',
			type : 'POST',
			data : {
				name : name,
				entreprise : entreprise,
				email : email
			}
		}).done(function() {
			console.log("success");
			resetInputForm();
			getAllClient();
		}).fail(function() {
			console.log("error");
		});
	});
	
	$("#submit_update_client_form").on("click", function(event) {
		event.preventDefault();

		var email = $("#input_email").val();
		var name = $("#input_contact_name").val();
		var entreprise = $("#input_entreprise").val();

		if (!email || !name || !entreprise) {
			return;
		}
		
		$.ajax({
			url : '/annuaireView/rest/client/update',
			type : 'POST',
			data : {
				name : name,
				entreprise : entreprise,
				email : email
			}
		}).done(function() {
			console.log("success");
			resetInputForm();
			createFormButton();
			getAllClient();
		}).fail(function() {
			console.log("error");
		});
	});

	$("#cancel_client_form").on("click", function() {
		resetInputForm();
		createFormButton();
	});

	getAllClient();

});