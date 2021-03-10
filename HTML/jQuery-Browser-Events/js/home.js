$(document).ready(function () {
    $('#akronInfoDiv').hide();
    $('#minneapolisInfoDiv').hide();
    $('#louisvilleInfoDiv').hide();
    $("#akronButton").click(function(){
        $('#akronInfoDiv').show();
        $('#mainInfoDiv').hide();
        $('#minneapolisInfoDiv').hide();
        $('#louisvilleInfoDiv').hide();
        $('#akronWeather').hide();
      });
      $('#akronWeatherButton').click(function(){
        $('#akronWeather').toggle();
     });
      $("#minneapolisButton").click(function(){
        $('#minneapolisInfoDiv').show();
        $('#akronInfoDiv').hide();
        $('#louisvilleInfoDiv').hide(); 
        $('#mainInfoDiv').hide();
        $('#minneapolisWeather').hide();
      });
      $('#minneapolisWeatherButton').click(function(){
        $('#minneapolisWeather').toggle();
     });
      $("#louisvilleButton").click(function(){
        $('#louisvilleInfoDiv').show();
        $('#mainInfoDiv').hide();
        $('#akronInfoDiv').hide();
        $('#minneapolisInfoDiv').hide();
        $('#louisvilleWeather').hide();
      });
      $('#louisvilleWeatherButton').click(function(){
        $('#louisvilleWeather').toggle();
     });
     $("#mainButton").click(function(){
        $('#mainInfoDiv').show();
        $('#akronInfoDiv').hide();
        $('#minneapolisInfoDiv').hide();
        $('#louisvilleInfoDiv').hide();
      });
      $('tr').not(':first-child').hover(
        // in callback
        function(){
            $(this).css('background-color','WhiteSmoke');
        },
        // out callback
        function(){
            $(this).css('background-color','');
        }
    );
        
});

// Page Load
// Only the content in the Main section should display when the 
// page is loaded.
// Navigation Button Behavior
// When the Akron button is clicked, only the content in the Akron 
// section should display; the weather information for Akron should 
// be hidden initially.
// When the Minneapolis button is clicked, only the content in the 
// Minneapolis section should display; the weather information for 
// Minneapolis should be hidden initially.
// When the Louisville button is clicked, only the content in the 
// Louisville section should display; the weather information for
// Louisville should be hidden initially.
// Show/Hide Weather Behavior
// When the Show/Hide Weather button is clicked, the page should 
// display the associated weather information if it was hidden or 
// hide the associated weather information if it was showing. It 
// should default to hidden.
// Table Row Behavior
// The background color of any table row should change to 
// “WhiteSmoke” when the mouse pointer is hovering over the row.
// The background color of the row should return to white when the 
// mouse pointer is no longer hovering over the row.
// This applies to all rows in all tables except the first (header) 
// row in a given table. The first (header) row in any table should 
// not change appearance when the mouse pointer hovers over it.