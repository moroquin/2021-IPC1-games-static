package org.ipc1.queue;

import org.ipc1.course.Student;

public class Queue {
    // saber quien está al inicio root
    private Node root;

    public Queue() {
        this.root = null;
    }

    public void push(Student data) {
        if (root == null) {
            System.out.println("            root = "+data);
            root = new Node(data);
        }
        else{
            Node tmp = root;

            while(tmp.getNext()!=null){
                System.out.println("          "+tmp.getData().toString()+".next = "+tmp.getNext().getData().toString());
                tmp = tmp.getNext();
            }

            System.out.println("            "+tmp.getData().toString()+".next = "+data);
            tmp.setNext(data);
        }
    }

    public boolean isEmpty(){
        return this.root == null;
    }

    public Student pop() {
        if (root != null) {
            Student tmp = root.getData();
            root = root.getNext();
            return tmp;
        }
        return null;
    }

    public void printQueue() {
        if (root != null) {
            System.out.println("node");
            System.out.println(root.getData().toString());
        } else {
            System.out.println("no hay nadie chavo");
        }
    }
}
