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
      }); 