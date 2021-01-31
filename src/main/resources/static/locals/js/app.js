$(document).ready(function(){

    // INICIAR POPOVER
            // $('[data-toggle="popover"]').popover({
            //     placement: 'left',
            //     container: 'body',
            //     // trigger: 'focus',
            //     html: true,
            //     sanitize: false,
            //     content:
            //         `<div id="PopoverContent">
            //         <div class="input-group">
            //             <input type="text" class="form-control" placeholder="Nuevo nombre" value="xxx">
            //         </div> 
            //         <div class="mt-2"> <a class="btn btn-sm btn-secondary col-4 float-left mb-2" href="#">No</a> <a class="btn btn-sm btn-primary col-6 float-right" href="#">Guardar</a> </div>
            //         </div>`
            // });


    // INICIAR TOOLTIP 
        // $(function () {
        //     $('[data-toggle="tooltip"]').tooltip()
        // });
  

    // AGREGAR NUEVO CONTENIDO TABLA PERFIL DEMO
        $("#DatoNuevo").hide();

        $("#DemoCategoria").click(function(){
            $("#DatoNuevo").show();
        });

       
})