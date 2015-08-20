package inputs;


import com.google.common.eventbus.DeadEvent;
import com.google.common.eventbus.Subscribe;

public class DeadEventListener {

     boolean notDelivered = false;

     @Subscribe
     public void listen(DeadEvent event) {
        notDelivered = true;
         isNotDelivered(event);
     }

     public void isNotDelivered(DeadEvent event) {
        System.out.println(event.getEvent().toString()+" "+notDelivered);
     }
 }
