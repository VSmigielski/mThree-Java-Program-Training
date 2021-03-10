$(document).ready(function () {
    $('H1').css('text-align', 'center');
    $('H2').css('text-align', 'center');
    $('.myBannerHeading').addClass('.page-header').removeClass('.myBannerHeading');
    $('#yellowHeading').html('Yellow Team');
    $('#yellowTeamList').append('<li>Joseph Banks</li>').append('<li>Simon Jones</li>');
    $('#oops').hide();
    $('#footerPlaceholder').remove();
    $('#footer').append('<p>Veronica Hana Smigielski McCormick veronicasmigielski@gmail.com </p>').css({ 'font': 'Courier', 'font-size': '24px' });
});

// Center all H1 elements.
// Center all H2 elements.
// Replace the class that is on the element containing the text "Team Up!" with the class page-header.
// Change the text of "The Squad" to "Yellow Team".
// Change the background color for each team list to match the name of the team.
// Add Joseph Banks and Simon Jones to the Yellow Team list.
// Hide the element containing the text "Hide Me!!!"
// Remove the element containing the text "Bogus Contact Info" from the footer.
// Add a paragraph element containing your name and email to the footer. The text must be in Courier font and be 24 pixels in height.