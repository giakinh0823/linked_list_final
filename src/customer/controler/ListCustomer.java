package customer.controler;

import customer.model.Customer;
import train.model.Train;
import util.input.Inputter;
import util.linkedList.LinkedList;
import util.linkedList.Node;

public class ListCustomer extends LinkedList<Customer> {
    private final Inputter inputter = new Inputter();

    public void display() {
        System.out.println(new String().format("%s\t|\t%s\t|\t%s", "Code", "Name", "Phone"));
        System.out.println("-------------------------------------------");
        Node<Customer> node = this.getHead();
        while (node != null) {
            System.out.println(node.getData());
            node = node.getNext();
        }
    }

    public void showCustomer(Customer customer) {
        System.out.println(new String().format("%s\t|\t%s\t|\t%s", "Code", "Name", "Phone"));
        System.out.println("-------------------------------------------");
        System.out.println(customer);
    }


    public Customer findByCode(String code) {
        Node<Customer> node = this.getHead();
        while (node != null) {
            if (node.getData().getCcode().equals(code)) {
                return node.getData();
            }
            node = node.getNext();
        }
        return null;
    }

    public void removeByCode(String code){
        Node<Customer> node = this.getHead();
        Node<Customer> prevNode = null;
        while (node != null) {
            if (node.getData().getCcode().equals(code)) {
                if (prevNode == null) {
                    this.setHead(node.getNext());
                    node = this.getHead();
                } else {
                    prevNode.setNext(node.getNext());
                    node = prevNode;
                }
                if(node.getNext() == null){
                    this.setTail(node);
                }
                break;
            } else {
                prevNode = node;
                node = prevNode.getNext();
            }
        }
    }

    public void searchByCode() {
        String code = inputter.inputStringNoSpace("Enter code: ");
        Customer customer = findByCode(code);
        if (customer == null) {
            System.out.println("Not found code!");
        } else {
            showCustomer(customer);
        }
    }


    private Customer inputCustomer() {
        String ccode;
        while (findByCode((ccode = inputter.inputStringNoSpace("Enter code: "))) != null) {
            System.out.println("Code is exit!");
        }
        String name = inputter.inputString("Enter name: ");
        String phone = inputter.inputString("Enter phone: ", "[0-9]{4,}");
        Customer customer = new Customer(ccode, name, phone);
        return customer;
    }

    public void addCustomerToEnd() {
        Customer customer = inputCustomer();
        this.addLast(customer);
    }

    public void deleteByCode() {
        String code = inputter.inputStringNoSpace("Enter code: ");
        Customer customer = findByCode(code);
        if (customer == null) {
            System.out.println("Node found code!");
        } else {
            removeByCode(code);
        }
    }

}
