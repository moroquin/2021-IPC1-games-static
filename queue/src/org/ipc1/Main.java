package org.ipc1;

import org.ipc1.course.Student;
import org.ipc1.queue.Queue;

/**
 * Main
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("hola, soy yo de nuevo");

        Queue queue = new Queue();

        Student student1 = new Student(1, "Victor", "xela");
        queue.push(student1);
        student1 = new Student(2, "Freddy", "Mazate");
        queue.push(student1);
        student1 = new Student(3, "Jeidy", "Xela");
        queue.push(student1);

        queue.push(new Student(4, "oliver", "xela"));
        
        System.out.println("saque a "+queue.pop().toString()+" y la lista quedo vacia= "+queue.isEmpty());
        System.out.println("saque a "+queue.pop().toString()+" y la lista quedo vacia= "+queue.isEmpty());
        System.out.println("saque a "+queue.pop().toString()+" y la lista quedo vacia= "+queue.isEmpty());
        System.out.println("saque a "+queue.pop().toString()+" y la lista quedo vacia= "+queue.isEmpty());

        // queue.push("oliver");
        // queue.push("emily");
        // queue.push("madelayne");
        // queue.push("victor");
        // queue.push("moises");
        // System.out.println("saque a "+queue.pop()+" y la lista quedo vacia= "+queue.isEmpty());
        // System.out.println("saque a "+queue.pop()+" y la lista quedo vacia= "+queue.isEmpty());
        // System.out.println("saque a "+queue.pop()+" y la lista quedo vacia= "+queue.isEmpty());
        // System.out.println("saque a "+queue.pop()+" y la lista quedo vacia= "+queue.isEmpty());
        // System.out.println("saque a "+queue.pop()+" y la lista quedo vacia= "+queue.isEmpty());




    }
}