var contextPath = $('#contextPath').html();
var sharingBookId;

$( document ).ready(function() {
    $('#share').click(function() {
        var sEmail = $( "#shareEmail").val();
        $.ajax({
            type: "POST",
            url: contextPath + "/book/share/" + sharingBookId,
            data: {
                'email': sEmail
            },
            success: function (data, text) {
                $('#shareEmail').val('');
                $('#myModal').modal('hide');
                $.growl.notice({ message: "Changes saved successfully" });
            },
            error: function (request, status, error) {
                $.growl.error({ message: error });
            }
        });
    });
});

function sharedType(iBookId, sType) {
    $.ajax({
        type: "POST",
        url: contextPath + "/book/"+iBookId,
        data: {
            'type': sType
        },
        success: function (data, text) {
            $.growl.notice({ message: "Changes saved successfully" });
        },
        error: function (request, status, error) {
            $.growl.error({ message: error });
        }
    });
}

function share(iBookId) {
    $('#myModal').modal();
    var users = getSharedUsers(iBookId);
    sharingBookId = iBookId;
}

function getSharedUsers(iBookId) {
    var tableBody = $('#sharedUsersTable').find('tbody');
    tableBody.empty();
    $.ajax({
        type: 'GET',
        url: contextPath + '/book/' + iBookId + '/users',
        success: function (data) {
            $.each(data, function(index, user) {
                tableBody.append($('<tr>')
                    .append('<td>' + user.firstName + ' ' + user.lastName + '</td>' +
                        '<td>' + user.email + '</td>' +
                        '<td><span class="rm-user glyphicon glyphicon-remove" onclick="unShare(this, ' + iBookId + ', '+ user.id +')"></span></td>'));
            });
        }
    });
}

function unShare(event, iBookId, iUserId) {
    var tableBody = $('#sharedUsersTable').find('tbody');
//		var thisElement = this;
    $.ajax({
        type: 'POST',
        url: contextPath + '/books/' + iBookId + '/users/' + iUserId + '/delete',
        success: function (data) {
            $(event).parent().parent().remove();
            $.growl.notice({ message: "Successfully deleted" });
        }
    });
}