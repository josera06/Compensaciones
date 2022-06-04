$(document).ready(function(){
    console.log("HOLA");
    	// buttons for next and previous item
        var buttons = { previous:$('#jslidernews1 .button-previous') ,
                        next:$('#jslidernews1 .button-next') };
            $('#jslidernews1').lofJSidernews( { interval : 4000,
                                            direction		: 'opacitys',
                                            easing			: 'easeInOutExpo',
                                            duration		: 1200,
                                            auto		 	: true,
                                            maxItemDisplay  : 4,
                                            navPosition     : 'horizontal', // horizontal
                                            navigatorHeight : 32,
                                            navigatorWidth  : 80,
                                            mainWidth		: 980,
                                            buttons			: buttons } );
    console.log("ADIOS");
//    swal({
//      buttons: {
//        cancel: true,
//        confirm: true,
//      },
//    });
//                    swal({
//                        title: 'Are you sure?sdf',
//                        text: "You won't be able to revert this!",
//                        type: 'warning',
//                        showCancelButton: true,
//                        confirmButtonText: 'Yes, delete it!'
//                    }).then(function (result) {
//                        if (result.value) {
//                            swal(
//                                'Deleted!',
//                                'Your file has been deleted.',
//                                'success'
//                            )
//                        }
//                    });
//    $("#btnAgregaTrabajador").click(function(event){
//        event.preventDefault();
//        event.stopImmediatePropagation();
//        $("#frmAgregaTrabajador").submit();
//    });
//        swal({
//            title: 'Auto close alert!',
//            text: 'I will close in 5 seconds.',
//            timer: 5000,
//            onOpen: function () {
//                swal.showLoading()
//            }
//        }).then(function (result) {
//            if (result.dismiss === 'timer') {
//                console.log('I was closed by the timer')
//            }
//        })
    /*alert($('#lista-solicitudes').attr('id'));
      $('#lista-solicitudes').DataTable();
      $('.dataTables_length').addClass('bs-select');

    $(function () {
    	$('#tablasolicitudes_transfusion').DataTable({scrollX:true,
    		'paging'      : true,
    		'lengthChange': true,
    		'searching'   : true,
    		'ordering'    : false,
    		'info'        : true,
    		'autoWidth'   : false
    		});
      });*/
});