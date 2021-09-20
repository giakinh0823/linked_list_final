package booking.controler;

import booking.model.Booking;
import customer.controler.ListCustomer;
import customer.model.Customer;
import train.controler.ListTrain;
import train.model.Train;
import util.input.Inputter;
import util.linkedList.Comparator;
import util.linkedList.LinkedList;
import util.linkedList.Node;

public class ListBooking extends LinkedList<Booking> {
    private final Inputter inputter = new Inputter();

    public void display(ListTrain listTrain, ListCustomer listCustomer) {
        System.out.println(new String().format("%s\t|\t%s\t|\t%s\t|\t%s\t|\t%s\t|\t%s\t|\t%s\t|\t%s", "Customer Code", "Customer Name", "Phone", "Train Code", "Train Name", "Seat", "Depart Time", "Depart Place"));
        System.out.println("---------------------------------------------------------------------------------------------------");
        Node<Booking> node = this.getHead();
        while (node != null) {
            Train train = listTrain.findByCode(node.getData().getTcode());
            Customer customer = listCustomer.findByCode(node.getData().getCcode());
            System.out.println(new String().format("%-20s%-20s%-14s%-16s%-16s%-14d%-15.1f%-18s", customer.getCcode(), customer.getName(), customer.getPhone(), train.getTcode(), train.getName(),node.getData().getSeat(), train.getDepartTime(), train.getDepartPlace()));
            node = node.getNext();
        }
    }

    public Booking findBooking(String tcode, String ccode){
        Node<Booking> node = this.getHead();
        while (node != null) {
            if (node.getData().getTcode().equals(tcode) && node.getData().getCcode().equals(ccode)) {
                return node.getData();
            }
            node = node.getNext();
        }
        return null;
    }

    public Train findByCodeTrain(ListTrain listTrain,String code) {
        Node<Train> node = listTrain.getHead();
        while (node != null) {
            if (node.getData().getTcode().equals(code)) {
                return node.getData();
            }
            node = node.getNext();
        }
        return null;
    }

    public Customer findByCodeCustomer(ListCustomer listCustomer,String code) {
        Node<Customer> node = listCustomer.getHead();
        while (node != null) {
            if (node.getData().getCcode().equals(code)) {
                return node.getData();
            }
            node = node.getNext();
        }
        return null;
    }


    public void inputBooking(ListTrain listTrain, ListCustomer listCustomer) {
        String tcode;
        Train train = null;
        while ((train = findByCodeTrain(listTrain,(tcode = inputter.inputStringNoSpace("Enter code train(0-> exit): ")))) == null) {
            if(tcode.equals("0")) return;
            System.out.println("Code doesn't exit!");
        }
        if(train.getSeat() - train.getBooked() == 0){
            System.out.println("Train is exhausted!");
            return;
        }
        String ccode;
        Customer customer = null;
        while ((customer = findByCodeCustomer(listCustomer,(ccode = inputter.inputStringNoSpace("Enter code customer(0-> exit): ")))) == null) {
            if(ccode.equals("0")) return;
            System.out.println("Code doesn't exit!");
        }
        if(findBooking(tcode, ccode)!=null){
            System.out.println("Train and customer are found in the booking list  then  data is not accepted");
            return;
        }
        int seat = inputter.inputInt("Input seat: ",0,train.getSeat()-train.getBooked());
        Booking booking = new Booking(tcode, ccode, seat);
        train.setBooked(train.getBooked()+seat);
        this.addLast(booking);
    }

    public void sortByTcodeAndCcode(){
        this.setHead(this.mergeSort(this.getHead(), new Comparator<Booking>() {
            @Override
            public int compare(Booking o1, Booking o2) {
                int compareTcode = o1.getTcode().compareTo(o2.getTcode());
                if(compareTcode==0){
                    return o1.getCcode().compareTo(o2.getCcode());
                }
                return compareTcode;
            }
        }));
    }

}
