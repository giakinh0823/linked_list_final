package app;

import booking.controler.ListBooking;
import booking.views.ManageBooking;
import customer.controler.ListCustomer;
import customer.views.ManageCustomer;
import train.controler.ListTrain;
import train.views.ManageTrain;
import util.menu.Menu;

import java.util.ArrayList;

public class Tester {
    private final static Menu menu = new Menu();
    private final static ListTrain listTrain = new ListTrain();
    private final static ListCustomer listCustomer = new ListCustomer();
    private final static ListBooking listBooking = new ListBooking();
    private final static ManageTrain manageTrain = new ManageTrain();
    private final static ManageCustomer manageCustomer = new ManageCustomer();
    private final static ManageBooking manageBooking = new ManageBooking();

    public static void main(String[] args) {
        ArrayList<String> options = new ArrayList<String>();
        options.add("Manage train");
        options.add("Manage customer");
        options.add("Manage booking");
        options.add("Exit app");
        while(true){
            int choice = menu.getChoice(options);
            switch (choice){
                case 1:
                    manageTrain.manage(listTrain);
                    break;
                case 2:
                    manageCustomer.manage(listCustomer);
                    break;
                case 3:
                    manageBooking.manage(listBooking, listTrain, listCustomer);
                    break;
                case 4:
                    break;
            }
            if(choice==options.size()){
                break;
            }
        }
    }
}
