/**
 * Created by Iorlov on 22.02.2016.
 */
$(document).ready(function(){
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
    if ($("#tariff").length) {
        $("#tariff").on("change", getSum(), function () {
            getSum();
        });
    }

    $("#tariffForm").submit( function(){
        var b=!isNaN($("#speed").val())&&!isNaN($("#cost").val())&&($.trim($("#speed").val()).length!=0)&&($.trim($("#cost").val()).length!=0);
        //alert($.trim($("#speed").val()).length==0);
        if(!b){
            return false;
        }else {
            return true;
        }
    });
});