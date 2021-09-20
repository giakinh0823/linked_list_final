package booking.views;

import booking.controler.ListBooking;
import customer.controler.ListCustomer;
import train.controler.ListTrain;
import util.menu.Menu;

import java.util.ArrayList;

public class ManageBooking {
    private final Menu menu = new Menu();

    public void manage(ListBooking listBooking, ListTrain listTrain, ListCustomer listCustomer) {
        ArrayList<String> options = new ArrayList<String>();
        options.add("Input data");
        options.add("Display data");
        options.add("Sort by tcode + ccode");
        options.add("Back to home");
        while(true){
            int choice = menu.getChoice(options);
            switch (choice){
                case 1:
                    listBooking.inputBooking(listTrain, listCustomer);
                    break;
                case 2:
                    listBooking.display(listTrain, listCustomer);
                    break;
                case 3:
                    listBooking.sortByTcodeAndCcode();
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
