$(document).ready(function () {
    loadDVDs();
    addDVD();
    updateDVD();
});

function loadDVDs() {
    clearDVDTable();
    clearDetailTable();
    var contentRows = $('#contentRows');
    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/api/dvds',
        success: function(dvdArray) {
            $.each(dvdArray, function(index, dvd){
                var title = dvd.title;
                var releaseYear = dvd.releaseYear;
                var director = dvd.director;
                var rating = dvd.rating;
                var dvdId = dvd.dvdid;
                
                var row = '<tr>';
                    row += '<td><a href="#" onClick="dvdDetailsForm(' + dvdId + ')">' + title + '</a></td>';
                    row += '<td>' + releaseYear + '</td>';
                    row += '<td>' + director + '</td>';
                    row += '<td>' + rating + '</td>';
                    row += '<td><button type="button" class="btn btn-info" onclick="showEditForm(' + dvdId + ')">Edit</button></td>';
                    row += '<td><button type="button" class="btn btn-danger" onclick="deleteDVD(' + dvdId + ')">Delete</button></td>';
                    row += '</tr>';
                
                contentRows.append(row);
            })
        },
        error: function() {
            $('#errorMessages')
            .append($('<li>')
            .attr({class: 'list-group-item list-group-item-danger'})
            .text('Error calling web service. Please try again later.'));
        }
    })
}
function showAddForm() {
    $('#errorMessages').empty();
    
    $('#addTitle').val('');
    $('#addReleaseYear').val('');
    $('#addDirector').val('');
    $('#addRating').val('G');
    $('#addNotes').val('');

    $('#addForm').show();
    $('#dvdTable').hide();
}

function addDVD() {
    $('#addButton').click(function (event) {
            var haveValidationErrors = checkAndDisplayValidationErrors($('#addForm').find('input'));
            
            if(haveValidationErrors) {
                return false;
            }
        $.ajax({
           type: 'POST',
           url: 'http://localhost:8080/api/dvds',
           data: JSON.stringify({
                title: $('#addTitle').val(),
                releaseYear: $('#addReleaseYear').val(),
                director: $('#addDirector').val(),
                rating: $('#addRating').val(),
                notes: $('#addNotes').val()
           }),
           headers: {
               'Accept': 'application/json',
               'Content-Type': 'application/json'
           },
           'dataType': 'json',
           success: function() {
               $('#errorMessages').empty();
               $('#addTitle').val('');
               $('#addReleaseYear').val('');
               $('#addDirector').val('');
               $('#addRating').val('');
               $('#addNotes').val('');
               loadDVDs();
           },
           error: function () {
                $('#errorMessages')
                .append($('<li>')
                .attr({class: 'list-group-item list-group-item-danger'})
                .text('Error calling web service. Please try again later.'));
               }
        })
        $('#addForm').hide();
        $('#dvdTable').show();
    });
}

function clearDVDTable() {
    $('#contentRows').empty();
}

function hideAddForm() {
    $('#errorMessages').empty();
    
    $('#addTitle').val('');
    $('#addReleaseYear').val('');
    $('#addDirector').val('');
    $('#addRating').val('');
    $('#addNotes').val('');

    $('#dvdTable').show();
    $('#addForm').hide();
}

function dvdDetailsForm(dvdid) {
    $('#errorMessages').empty();
    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/api/dvds/' + dvdid,
        success: function(data, status) {
            $('#showTitle').text(data.title);
            $('#showReleaseYear').text(data.releaseYear);
            $('#showDirector').text(data.director);
            $('#showRating').text(data.rating);
            $('#showNotes').text(data.notes);
            $('#showDVDId').text(data.dvdid);
            
        },
        error: function() {
            $('#errorMessages')
            .append($('<li>')
            .attr({class: 'list-group-item list-group-item-danger'})
            .text('Error calling web service. Please try again later.')); 
        }
    })
    $('#dvdTable').hide();
    $('#dvdDetailsForm').show();
}

function showEditForm(dvdid) {
    $('#errorMessages').empty();
    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/api/dvds/' + dvdid,
        success: function(data, status) {
            $('#editTitle').val(data.title);
            $('#editReleaseYear').val(data.releaseYear);
            $('#editDirector').val(data.director);
            $('#editRating').val(data.rating);
            $('#editNotes').val(data.notes);
            $('#editDVDId').val(data.dvdid);
            
        },
        error: function() {
            $('#errorMessages')
            .append($('<li>')
            .attr({class: 'list-group-item list-group-item-danger'})
            .text('Error calling web service. Please try again later.')); 
        }
    })
    $('#dvdTable').hide();
    $('#editFormDiv').show();
}

function hideEditForm() {
    $('#errorMessages').empty();
    
    $('#editTitle').val('');
    $('#editReleaseYear').val('');
    $('#editDirector').val('');
    $('#editRating').val('');
    $('#editNotes').val('');

    $('#dvdTable').show();
    $('#editFormDiv').hide();
}

function hideSearchContainer() {
    $('#searchContainer').hide();
}

function hideDetailsForm() {
    $('#errorMessages').empty();
    
    $('#showTitle').text('');
    $('#showReleaseYear').text('');
    $('#showDirector').text('');
    $('#showRating').text('');
    $('#showNotes').text('');

    $('#dvdTable').show();
    $('#dvdDetailsForm').hide();
}

function updateDVD(dvdid) {
    $('#updateButton').click(function(event) {
        var haveValidationErrors = checkAndDisplayValidationErrors($('#editForm').find('input'));
        
        if(haveValidationErrors) {
            return false;
        }
        $.ajax({
            type: 'PUT',
            url: 'http://localhost:8080/api/dvds/dvd/' + $('#editDVDId').val(),
            data: JSON.stringify({
                dvdid: $('#editDVDId').val(),
                title: $('#editTitle').val(),
                releaseYear: $('#editReleaseYear').val(),
                director: $('#editDirector').val(),
                rating: $('#editRating').val(),
                notes: $('#editNotes').val()
            }),
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            'dataType': 'json',
            'success': function() {
                $('#errorMessage').empty();
                hideEditForm();
                loadDVDs();
            },
            'error': function() {
                $('#errorMessages')
                .append($('<li>')
                .attr({class: 'list-group-item list-group-item-danger'})
                .text('Error calling web service. Please try again later.')); 
            }
        })
    })
}

function deleteDVD(dvdid) {
    if (confirm("Are you sure you want to delete this DVD from your collection?")){
    $.ajax({
        type: 'DELETE',
        url: 'http://localhost:8080/api/dvds/dvd/' + dvdid,
        success: function() {
            loadDVDs();
        }
    });   
}
return false;
}

function checkAndDisplayValidationErrors(input) {
    $('#errorMessages').empty();
    
    var errorMessages = [];
    
    input.each(function() {
        if (!this.validity.valid) {
            var errorField = $('label[for=' + this.id + ']').text();
            errorMessages.push(errorField + ' ' + this.validationMessage);
        }  
    });
    
    if (errorMessages.length > 0){
        $.each(errorMessages,function(index,message) {
            $('#errorMessages').append($('<li>').attr({class: 'list-group-item list-group-item-danger'}).text(message));
        });
        // return true, indicating that there were errors
        return true;
    } else {
        // return false, indicating that there were no errors
        return false;
    }
}

function clearDetailTable() {
    $('#contentSearchRows').empty();
    hideSearchContainer()
}

function dropdown() {
    clearDetailTable();
    var contentSearchRows = $('#contentSearchRows');
    var result= $("#dvdAttributes option:selected").val();
    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/api/dvds/' + result + '/' + ($('#searchText').val()),
        success: function(dvdArray) {
            $.each(dvdArray, function(index, dvd){
                var title = dvd.title;
                var releaseYear = dvd.releaseYear;
                var director = dvd.director;
                var rating = dvd.rating;
                var dvdId = dvd.dvdid;
                
                var row = '<tr>';
                    row += '<td>' + title + '</td>';
                    row += '<td>' + releaseYear + '</td>';
                    row += '<td>' + director + '</td>';
                    row += '<td>' + rating + '</td>';
                    row += '<td><button type="button" class="btn btn-info" onclick="showEditForm(' + dvdId + ')">Edit</button></td>';
                    row += '<td><button type="button" class="btn btn-danger" onclick="deleteDVD(' + dvdId + ')">Delete</button></td>';
                    row += '</tr>';

                contentSearchRows.append(row);
            })
        },
        error: function() {
            if ($('#searchText').val('')) {
            $('#errorMessages')
            .append($('<li>')
            .attr({class: 'list-group-item list-group-item-danger'})
            .text('Both Search Category and Search Term are required'));
            }
        }
    })
    $('#dvdTable').hide();
    $('#searchContainer').show();
}
