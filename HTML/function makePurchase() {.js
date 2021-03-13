function makePurchase() {
    $('#purchaseButton').click(function (event) {
		var amount = $('#moneyInput').val();
		var id = $('#itemToVend').val();
		
		$.ajax({
			type: 'POST',
			url: '	http://tsg-vending.herokuapp.com/money/' + amount + '/item/' + id,
			headers: {
				'Accept': 'application/json',
				'Content-Type': 'application/json'
			},
			'dataType': 'json',
			success: function(data) {
				var quarters = data.quarters;
				var dimes = data.dimes;
				var nickels = data.nickels;
				var pennies = data.pennies;

				formatChange(quarters, dimes, nickels, pennies);
				$('#vendingMessage').val('THANK YOU!!!');
				$('#moneyInput').val('0.00');
				loadItems();
			},
			error: function(xhr, status, error) {
				var responseText = jQuery.parseJSON(xhr.responseText);
           		$('#vendingMessage').val( responseText.message);
           		loadItems();

				$('#errorMessages')
					.append($('<li>')
					.attr({class: 'list-group-item list-group-item-danger'})
					.text('Error calling web service. Please try again later.'));
			}
		});
	});
}