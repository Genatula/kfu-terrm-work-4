$(document).ready(function () {
    let speeddateId = $('input#id').val();
    $('#participate-btn').click(function (event) {
        $.ajax({
            url: '/speeddates/participate',
            method: 'POST',
            data: {
                id: speeddateId
            },
            success: function () {
                $('#participate-btn').prop('hidden', true);
                $('#undo-participate-btn').prop('hidden', false);
            },
            error: function (msg) {
                console.log(msg);
            }
        });
    });
    $('#undo-participate-btn').click(function (event) {
        $.ajax({
            url: '/speeddates/participate',
            method: 'POST',
            data: {
                id: speeddateId,
                undo: true
            },
            success: function () {
                $('#participate-btn').prop('hidden', false);
                $('#undo-participate-btn').prop('hidden', true);
            },
            error: function (msg) {
                console.log(msg);
            }
        });
    });
});