     $(document).ready(function() {debugger
     $("th").removeClass('sorting_desc');
          $('#upload_table').dataTable({
              ordering: false,
              searching:false,
              pageLength:5,
              lengthMenu: [ [5, 10, 25, 50, -1], [5, 10, 25, 50, "All"] ],
             /* "scrollY": "275px",*/
              "scrollCollapse": true,
          });
          
              var message = $('meta[name="message"]').attr('content');
            var error = $('meta[name="error"]').attr('content');
            if (message) {
                swal( message);
            }
            if (error) {
                swal( error);
            }
      }); 
      
      
      function registrationForm(){
	$("#registration_Modal").modal('show');
}


function showname() {
    var fileInput = document.getElementById('myfile');
    var fileName = fileInput.files[0].name;
    if(fileName != ""){
		$('#toggle').hide();
	}
    var label = document.getElementById('labelName');
    label.textContent = fileName;
}


 $(document).ready(function () {
	var userId='${userId}';
        $('#yourDataTable').DataTable({
            "processing": true,
            "serverSide": true,
            "ajax": {
                "url":'/users/' + userId + '/files',
                "type": "POST",
                "data": function (d) {
                    d.page = d.start / d.length;  
                    d.size = d.length; 
                    
                }
            },
            "columns": [
                { "data": "id" },
                { "data": "name" },
                { "data": "age" },
                
            ]
        });
    });
