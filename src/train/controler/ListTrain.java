package train.controler;

import train.model.Train;
import util.input.Inputter;
import util.linkedList.Comparator;
import util.linkedList.LinkedList;
import util.linkedList.Node;

public class ListTrain extends LinkedList<Train> {
    private final Inputter inputter = new Inputter();

    public void display() {
        System.out.println(new String().format("%s\t|\t%s\t|\t%s\t|\t%s\t|\t%s\t|\t%s\t|\t%s\t", "Code", "Train Name", "Seat", "Booked", "Depart Time", "Depart Place", "Available Seat"));
        System.out.println("---------------------------------------------------------------------------------------------------");
        Node<Train> node = this.getHead();
        while (node != null) {
            System.out.println(node.getData());
            node = node.getNext();
        }
    }

    public void showTrain(Train train) {
        System.out.println(new String().format("%s\t|\t%s\t|\t%s\t|\t%s\t|\t%s\t|\t%s\t|\t%s\t", "Code", "Train Name", "Seat", "Booked", "Depart Time", "Depart Place", "Available Seat"));
        System.out.println("---------------------------------------------------------------------------------------------------");
        System.out.println(train);
    }

    public Train findByCode(String code) {
        Node<Train> node = this.getHead();
        while (node != null) {
            if (node.getData().getTcode().equals(code)) {
                return node.getData();
            }
            node = node.getNext();
        }
        return null;
    }

    private Train inputTrain() {
        String tcode;
        while (findByCode((tcode = inputter.inputStringNoSpace("Enter code: "))) != null) {
            System.out.println("Code is exit!");
        }
        String name = inputter.inputString("Enter name: ");
        int seat = inputter.inputInt("Enter seat: ", 1);
        int booked = inputter.inputInt("Enter booked: ", 0, seat);
        double departTime = inputter.inputDouble("Enter depart time: ", 0);
        String departPlace = inputter.inputString("Enter depart place: ", "[a-zA-Z0-9]+");
        Train train = new Train(tcode, name, seat, booked, departTime, departPlace);
        return train;
    }

    public void addTrainToEnd() {
        Train train = inputTrain();
        this.addLast(train);
    }


    public void searchByCode() {
        String code = inputter.inputStringNoSpace("Enter code: ");
        Train train = findByCode(code);
        if (train == null) {
            System.out.println("Not found code!");
        } else {
            showTrain(train);
        }
    }

    public void removeByCode(String code){
        Node<Train> node = this.getHead();
        Node<Train> prevNode = null;
        while (node != null) {
            if (node.getData().getTcode().equals(code)) {
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

    public void deleteByCode() {
        String code = inputter.inputStringNoSpace("Enter code: ");
        Train train = findByCode(code);
        if (train == null) {
            System.out.println("Node found code!");
        } else {
            removeByCode(code);
        }
    }

    public void sortByCode() {
        this.setHead(this.mergeSort(this.getHead(), new Comparator<Train>() {
            @Override
            public int compare(Train o1, Train o2) {
                return o1.getTcode().compareTo(o2.getTcode());
            }
        }));
    }

    public void addAfterPos() {
        int pos = inputter.inputInt("Enter pos need add: ", 1, this.size());
        Train train = inputTrain();
        this.insertAfter(pos, train);
    }

    public void deleteBeforeCode() {
        String code = inputter.inputStringNoSpace("Enter code: ");
        Train train = findByCode(code);
        if (train == null) {
            System.out.println("Not found code!");
        } else {
            Node<Train> node = this.getHead();
            Node<Train> prevNode = null;
            while (node != null && node.getNext() != null) {
                if (node.getNext().getData().getTcode().equals(code)) {
                    if(prevNode == null){
                        this.setHead(node.getNext());
                    }else{
                        prevNode.setNext(node.getNext());
                        node = prevNode.getNext();
                    }
                    if(node.getNext() == null){
                        this.setTail(node);
                    }
                    break;
                }
                prevNode = node;
                node = prevNode.getNext();
            }
        }
    }

}
