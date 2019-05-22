/**
 * Created by Administrator on 2016/12/19.
 */
$(function () {
    $(".table").offset({top:0,left:80});
    $(".table *").css("margin","0");
    $(".table *").css("padding","0");
    $(".table td").css("padding-top","1.5%");
    $(".table td").css("padding-left","8px");
    $(".table td").css("padding-bottom","0.5%");
    $(".table td").css("witdh","0.5%");
    $(".table #spDIV").css("margin-top","50px");
    $(".table #sptime1").css("margin-top","25px");
    $(".table #sptime2").css("margin-top","20px");
    $(".table #spcheckbox1").css("margin-top","5px");
    $(".table #spcheckbox2").css("margin-top","5px");
    $(".table #spcheckbox3").css("margin-top","5px");
    $(".table #spP").css("margin-left","15px");
    $(".table #spspan").css("padding-bottom","15px");





    var   str=" <tr id='asdd'> <td > <p></p> </td><td><p></p></td> <td> <p></p></td><td><td> </td> </td> </td></td><td>  <p></p> </td><td><p></p></td><td>  <p></p>  </td><td>  <p></p>  </td><td><p></p></td>  </tr>";
    $(".table tr:eq(0)").before(str);




    $("#asdd td").each(function () {
        $(this).css("border","none");
    });

    $(".table").css("border","none");


});




