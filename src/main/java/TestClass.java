import com.ironyard.data.Ticket;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by jasonskipper on 1/19/17.
 */
public class TestClass {
    public static void main(String[] args){

        Ticket random = new Ticket();

        Ticket manualInts = new Ticket(1,2,3,4,5,6);
        Ticket arrayListTicket = null;
        System.out.println("manualInts:"+manualInts);
        ArrayList tmp = new ArrayList();
        tmp.add(5);
        tmp.add(6);
        tmp.add(22);
        tmp.add(7);
        tmp.add(4);
        tmp.add(5);
        try {
            arrayListTicket = new Ticket(tmp);
            System.out.println("array list:"+arrayListTicket);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // test write to dick
        random.writeTicketToDisk();
            manualInts.writeTicketToDisk();
            arrayListTicket.writeTicketToDisk();

        // read ticket from disk
        Ticket found = new Ticket("/tmp/1485185605655.ticket");
        System.out.println(found);

        List<Ticket> tickets = Ticket.readAllTicketsFromDisk();
        for(Ticket t: tickets){
            System.out.println("loaded:"+t.toDataString());
        }
    }
}
