$(document).ready(function () {
    let meetingId = $('input#id').val();
    $('#participate-btn').click(function (event) {
      $.ajax({
          url: '/meetings/participate',
          method: 'POST',
          data: {
              id: meetingId
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
          url: '/meetings/participate',
           method: 'POST',
           data: {
              id: meetingId,
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