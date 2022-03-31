package org.ipc1.queue;

import org.ipc1.course.Student;

public class Node {
  

    private Node next;
    private Student data;

    public Node(Student data){
        this.data = data;
        this.next = null;
    }

    public Student getData() {
        return this.data;
    }

    public void setData(Student data) {
        this.data = data;
    }

    public Node getNext() {
        return this.next;
    }

    public void setNext(Student data) {
        this.next = new Node(data);
    }
    
}
