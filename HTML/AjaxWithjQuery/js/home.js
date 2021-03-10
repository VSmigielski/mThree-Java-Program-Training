//File for use in Ajax lesson
$(document).ready(function(){
// Retrieves the contact list through ajax and inserts into a paragraph for each
    $.ajax({
        type: 'GET',
        url: 'https://tsg-contactlist.herokuapp.com/contacts',
        success: function(contactArray) {
            var contactsDiv = $('#allContacts');

            $.each(contactArray, function(index, contact) {
                var contactInfo = '<p>';
                contactInfo += 'Name: ' + contact.firstName + ' ' + contact.lastName + '<br>';
                contactInfo += 'Company: ' + contact.company + '<br>';
                contactInfo += 'Email: ' + contact.email + '<br>';
                contactInfo += 'Phone: ' + contact.phone + '<br>';
                contactInfo += '</p><hr>';
        
                contactsDiv.append(contactInfo);
            })
            
        },
        error: function() {
            alert('FAILURE!');
        }
    })
})