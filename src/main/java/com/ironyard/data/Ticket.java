package com.ironyard.data;

import com.google.gson.Gson;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.StringTokenizer;

/**
 * Created by jasonskipper on 1/19/17.
 */
public class Ticket {
    private final static int MAX = 53;
    private final static int MIN = 1;
    private final static String TMP_DIR = "/tmp/";


    // ticket properties
    private ArrayList<Integer> numbers = new ArrayList<Integer>();
    private String ticketFileName = null;
    private String owner;

    /**
     * Constructor creates a new ticket with random
     * and unique values
     */
    public Ticket() {
        setOwner("Random Gen");

        for (int i = 0; i < 6; i++) {
            int tmp = 0;
            do{
                tmp = generateRandomTicketNumber();
            }while(numbers.contains(tmp));

            // i know tmp has a unique value
            numbers.add(tmp);
        }
        genAndSetTicketFileName();
    }

    /**
     * Constructor allows user to specify exact numbers of ticket
     * @param one
     * @param two
     * @param three
     * @param four
     * @param five
     * @param six
     */
    public Ticket(int one, int two, int three, int four, int five, int six){
        numbers.add(one);
        numbers.add(two);
        numbers.add(three);
        numbers.add(four);
        numbers.add(five);
        numbers.add(six);
        genAndSetTicketFileName();
    }

    /**
     * Constructor allows user to specify exact numbers of ticket via ArralList
     * @param myNumbers
     */
    public Ticket(ArrayList<Integer> myNumbers) throws Exception {
        if(myNumbers.size()!=6){
            throw new Exception("Exactly 6 numbers should be sent to new Ticket");
        }
        numbers.addAll(myNumbers);
        genAndSetTicketFileName();
    }

    public Ticket(String filePathToThisTicketFile){
        try (FileReader fr = new FileReader(filePathToThisTicketFile)) {

            Gson tmpG = new Gson();
            Ticket t = tmpG.fromJson(fr, Ticket.class);

            // transfer data from new ticket to this instance
            this.setOwner(t.getOwner());
            this.setNumbers(t.getNumbers());
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private void genAndSetTicketFileName(){
        // set filename to systemtime.ticket ex: 123123123123123.ticket
        if(ticketFileName == null) {
            // only set this once for each ticket
            ticketFileName = System.currentTimeMillis() + ".ticket";
        }
    }
    private int generateRandomTicketNumber() {
        Random r = new Random();
        int got = r.nextInt((Ticket.MAX - Ticket.MIN) + 1) + Ticket.MIN;
        return got;
    }

    public String toString() {
        String display = "<strong>";
        display = display + toDataString();
        display = display + "</strong>";
        return display;
    }

    public String toDataString() {
        String display = "";
        for (int i = 0; i < numbers.size(); i++) {
            display = display + String.format("%02d", numbers.get(i)) + "  ";
        }
        return display;
    }
    public void writeTicketToDisk(){

        try (FileWriter fw = new FileWriter(TMP_DIR+ticketFileName)) {

            Gson tmpG = new Gson();
            tmpG.toJson(this, fw);

        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    /**
     * Scans the temp ticket directory for files ending in ".ticket"
     * Creates a Ticket instance for each one, and returns a list
     * that holds all tickets found.
     * @return
     */
    public static List<Ticket> readAllTicketsFromDisk(){
        List<Ticket> found = new ArrayList<>();
        File directory = new File(Ticket.TMP_DIR);
        //get all the files ending in ".ticket" from a directory
        File[] fList = directory.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith(".ticket");
            }
        });
        for (File file : fList){
            if (file.isFile()){
                found.add(new Ticket(file.getAbsolutePath()));
            }
        }
        return found;
    }

    public ArrayList<Integer> getNumbers() {
        return numbers;
    }

    public void setNumbers(ArrayList<Integer> numbers) {
        this.numbers = numbers;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }
}



