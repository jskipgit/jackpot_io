package com.ironyard.data;

import static org.junit.Assert.*;

/**
 * Created by jasonskipper on 1/25/17.
 */
public class TicketTest {

    @org.junit.Test
    public void checkNumbers() throws Exception {
        Ticket t = new Ticket(1,2,3,4,5,6);
        int[] winningNumbers = {1,2,3,0,9,8};
        t.checkNumbers(winningNumbers);

        assertEquals("Check number found incorrect match count",3, t.getMatchCount());

    }

    @org.junit.Test
    public void checkNumbersSmarter() throws Exception {
        Ticket t = new Ticket(1,2,3,4,5,6);
        int[] winningNumbers = {1,2,3,0,9,8};
        t.checkNumbersSmarter(winningNumbers);

        assertEquals("Check number found incorrect match count",3, t.getMatchCount());

    }

}