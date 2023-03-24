$(function() {
    $('form').submit(function(e) {
        e.preventDefault();
        var action = $(this).attr('action');
        var method = $(this).attr('method');
        var data = $(this).serialize();
        $.ajax({
            url: action,
            type: method,
            data: data,
            success: function() {
                location.reload();
            }
        });
        return false;
    });
});
