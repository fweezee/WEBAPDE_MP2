/**
 * Created by Jords on 7/26/2017.
 */

$( document ).ready(function() {

    // $('img').on('click', function () {
    //     var image = $(this).attr('src');
    //     $('#myModal').on('show.bs.modal', function () {
    //         $(".img-responsive").attr("src", image);
    //     });
    // });


        $(".tabbable").find(".tab").hide();
        $(".tabbable").find(".tab").first().show();
        $(".tabbable").find(".tabs li").first().find("a").addClass("active");
        $(".tabbable").find(".tabs").find("a").click(function(){
            tab = $(this).attr("href");
            $(".tabbable").find(".tab").hide();
            $(".tabbable").find(".tabs").find("a").removeClass("active");
            $(tab).show();
            $(this).addClass("active");
            return false;
        });

});


