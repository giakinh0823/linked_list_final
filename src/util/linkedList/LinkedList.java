package util.linkedList;

public class LinkedList<E>{
    Node<E> head;
    Node<E> tail;

    public LinkedList() {
        head = null;
        tail = null;
    }

    public void clear() {
        head = null;
        tail = null;
    }

    public Node<E> getHead(){
        return this.head;
    }

    public void  setHead(Node<E> head){
        this.head = head;
    }

    public Node<E> getTail(){
        return this.tail;
    }

    public void setTail(Node<E> tail){
        this.tail = tail;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public void add(E data) {
        Node<E> q = new Node<E>(data);
        if (this.isEmpty()) {
            head = tail = q;
            return;
        }
        tail.next = q;
        tail = q;
    }

    public void addFirst(E data) {
        Node<E> q = new Node<E>(data, head);
        if (tail == null) {
            tail = head;
        }
    }

    public void addLast(E data) {
        Node<E> q = new Node<E>(data);
        if (this.isEmpty()) {
            head = tail = q;
            return;
        }
        tail.next = q;
        tail = q;
    }

    public void addMany(E... listData) {
        for (E data : listData) {
            addLast(data);
        }
    }

    public void visit(Node<E> p) {
        if (p != null) {
            System.out.println(p.data);
        }
    }

    public void traverse() {
        Node<E> p = head;
        while (p != null) {
            visit(p);
            p = p.next;
        }
    }

    public void insert(int pos, E data) {
        Node<E> head = this.head;
        if (pos < 1) {
            System.out.print("Invalid position");
        }
        if (pos == 1) {
            Node<E> newNode = new Node<E>(data);
            newNode.next = head;
            this.head = newNode;
        } else {
            while (pos-- != 0) {
                if (pos == 1) {
                    Node<E> newNode = new Node<E>(data);
                    newNode.next = head.next;
                    head.next = newNode;
                    if (newNode.next == null) {
                        tail = newNode;
                    }
                    break;
                }
                head = head.next;
            }
            if (pos != 1) {
                System.out.print("Position out of range");
            }
        }
    }

    public void insertAfter(int pos, E data) {
        Node<E> node = this.head;
        if (pos < 1) {
            System.out.print("Invalid position");
        }
        if (pos == 1) {
            Node<E> newNode = new Node<E>(data);
            newNode.next = node.next;
            node.next = newNode;
        } else {
            while (pos-- != 0) {
                if (pos == 0) {
                    Node<E> newNode = new Node<E>(data);
                    newNode.next = node.next;
                    node.next = newNode;
                    if (newNode.next == null) {
                        tail = newNode;
                    }
                    break;
                }
                node = node.next;
            }

            if (pos != 1) {
                System.out.print("Position out of range");
            }
        }
    }

    public void insertAfter(Node<E> q, E data) {
        if (q == null) {
            return;
        }
        Node<E> newNode = new Node<E>(data);
        newNode.next = q.next;
        q.next = newNode;
        if (newNode.next == null) {
            tail = newNode;
        }
    }

    public void insertBefore(Node<E> q, E data) {
        if (q == null) {
            return;
        }
        Node<E> newNode = new Node<E>(data);
        if (q == this.head) {
            newNode.next = this.head;
            this.head = newNode;
        } else {
            Node<E> current = this.head;
            while(current.next != null){
                if(current.next==q){
                    newNode.next = current.next;
                    current.next = newNode;
                    break;
                }
                current = current.next;
            }
        }
    }

    public void remove(Node<E> q) {
        Node<E> node = this.head;
        Node<E> prevNode = null;
        while (node != null) {
            if (node == q) {
                if (prevNode == null) {
                    this.head = node.next;
                    node = this.head;
                } else {
                    prevNode.next = node.next;
                    node = prevNode;
                }
                if(node.next ==null){
                    tail = node;
                }
                return;
            } else {
                prevNode = node;
                node = prevNode.next;
            }
        }
    }

    public void removePos(int k) {
        Node<E> node = this.head;
        Node<E> prevNode = null;
        int d = 1;
        while (node != null) {
            if (d == k) {
                if (prevNode == null) {
                    this.head = node.next;
                    node = this.head;
                } else {
                    prevNode.next = node.next;
                    node = prevNode;
                }
                if(node.next ==null){
                    tail = node;
                }
                return;
            } else {
                prevNode= node;
                node = prevNode.next;
            }
            d++;
        }
    }

    public Node<E> getNode(int k) {
        Node<E> node= this.head;
        while (k-- != 0 && node != null) {
            if (k == 0) {
                return node;
            }
            node = node.next;
        }
        return null;
    }

    public int size() {
        int d = 0;
        Node<E> q = head;
        while (q != null) {
            d++;
            q = q.next;
        }
        return d;
    }

    public E[] toArray() {
        Object list[] = new Object[size()];
        Node<E> q = head;
        int d = 0;
        while (q != null) {
            list[d]= q.data;
            d++;
            q = q.next;
        }
        return (E[]) list;
    }

    public void reverse() {
        Node<E> curentNode, prevNode, nextNode;
        curentNode = this.head;
        prevNode = null;
        nextNode = null;
        while (curentNode != null) {
            nextNode = curentNode.next;
            curentNode.next = prevNode;
            prevNode = curentNode;
            curentNode = nextNode;
        }
        this.head = prevNode;
    }

    public void setData(Node<E> p, E x) {
        p.data = x;
    }


    private Node<E> getMiddle(Node<E> node) {
        if (node == null) return null;
        Node<E> slow = node, fast = node;
        while (fast.getNext() != null && fast.getNext().getNext() != null) {
            slow = slow.getNext();
            fast = fast.getNext().getNext();
        }
        return slow;
    }

    Node<E> sortedMerge(Node<E> a, Node<E> b, Comparator<E> comp) {
        Node<E> result = null;
        if (a == null)
            return b;
        if (b == null)
            return a;

        if(comp.compare(a.getData(),b.getData())<=0) {
            result = a;
            result.setNext(sortedMerge(a.getNext(), b, comp));
        } else {
            result = b;
            result.setNext( sortedMerge(a, b.getNext(), comp));
        }
        return result;
    }

    public Node<E> mergeSort(Node<E> h, Comparator<E> comp) {
        if (h == null || h.getNext() == null) {
            return h;
        }
        Node<E> middle = getMiddle(h);
        Node<E> nextofmiddle = middle.getNext();
        middle.setNext(null);
        Node<E> left = mergeSort(h, comp);
        Node<E> right = mergeSort(nextofmiddle, comp);
        Node<E> sortedlist = sortedMerge(left, right, comp);
        return sortedlist;
    }

}
