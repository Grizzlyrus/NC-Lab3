/**
 * Created by Iorlov on 08.02.2016.
 */

$(document).ready(function(){
    var customerDialog,customerForm;
    var tariffDialog,tariffForm;
    var orderDialog,orderForm;
    $("table").stupidtable();
    /*customerDialog = $( "#customerForm" ).dialog({
        autoOpen: false,
        height: 320,
        width: 360,
        modal: true,
        resizable : false,
        buttons: {
            "Create a customer": function(){
                customerDialog.find( "form" ).submit();
            },
            Cancel: function() {
                customerDialog.dialog( "close" );
            }
        },
        close: function() {
            customerForm[ 0 ].reset();
            //allFields.removeClass( "ui-state-error" );
}
    });

    customerForm = customerDialog.find( "form" ).on( "submit", function( event ) {
        //event.preventDefault();
        //addUser();
    });

    $( "#customerLink" ).on( "click", function() {
        customerDialog.dialog( "open" );
    });

    tariffDialog = $( "#tariffForm" ).dialog({
        autoOpen: false,
        height: 320,
        width: 360,
        modal: true,
        resizable : false,
        buttons: {
            "Create a tariff": function(){
                tariffDialog.find( "form" ).submit();
            },
            Cancel: function() {
                tariffDialog.dialog( "close" );
            }
        },
        close: function() {
            tariffForm[ 0 ].reset();
            //allFields.removeClass( "ui-state-error" );
        }
    });

    tariffForm = tariffDialog.find( "form" ).on( "submit", function( event ) {
        //event.preventDefault();
        //addUser();
    });

    $( "#tariffLink" ).on( "click", function() {
        tariffDialog.dialog( "open" );
    });

    orderDialog = $( "#orderForm" ).dialog({
        autoOpen: false,
        height: 380,
        width: 370,
        modal: true,
        resizable : false,
        buttons: {
            "Create an order": function(){
                orderDialog.find( "form" ).submit();
            },
            Cancel: function() {
                orderDialog.dialog( "close" );
            }
        },
        close: function() {
            orderForm[ 0 ].reset();
            //allFields.removeClass( "ui-state-error" );
        }
    });

    orderForm = orderDialog.find( "form" ).on( "submit", function( event ) {
        //event.preventDefault();
        //addUser();
    });

    $( "#orderLink" ).on( "click", function() {
        getSum();
        $("#tariff").on("change",function(){
            getSum();
        });
        orderDialog.dialog( "open" );
    });
    function getSum(){
        $.ajax("getsum",{
            type:"POST",
            dataType: "text",
            data:"tariff="+$("#tariff option:selected").val(),
            success: function(data){
                $("#sum").html(data);
            }
        });
    }

    $("#modifyCustomer").on("click", function(){
        //alert("Something happen 1!")
        $.ajax("getCustomer",{
            type: "POST",
            data: "customerId="+$("#modifyCustomer").val(),
            success:function(data){
                $("#customerName").val(data.name);
                $("#phone").val(data.phone);
                $("#address").val(data.address);
                $("#tId").val($("#modifyCustomer").val());
                customerForm.attr("action","mCustomer");
                $( "#customerForm").attr("title","Change Customer");
                customerDialog.dialog( "open" );
            }
        });
    });*/
});