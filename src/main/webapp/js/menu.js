    $(document).ready(function(){  
        
        //When mouse rolls over  
        $("li.active").mouseover(function(){  
            $(this).stop().animate({height:'150px'},{queue:false, duration:600, easing: 'easeOutBounce'});
        });  
      
        //When mouse is removed  
        $("li.active").mouseout(function(){  
            $(this).stop().animate({height:'50px'},{queue:false, duration:600, easing: 'easeOutBounce'});  
        });  
      
    });  
