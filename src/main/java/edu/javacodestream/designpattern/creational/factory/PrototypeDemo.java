package edu.javacodestream.designpattern.creational.factory;

import org.checkerframework.checker.units.qual.C;

/**
 * Ref# https://refactoring.guru/design-patterns/prototype
 * Prototype is a creational design pattern that lets you copy existing objects
 * without making your code dependent on their classes.
 *
 * The Prototype pattern delegates the cloning process to the actual objects that are being cloned.
 *
 * The implementation of the clone method is very similar in all classes.
 * The method creates an object of the current class and carries over all of the field
 * values of the old object into the new one.
 *
 * Use the Prototype pattern when your code shouldn’t depend on the concrete classes
 * of objects that you need to copy.
 */
interface Shape {
    Shape clone();
}

class Rectangle implements Shape {
    private int height;
    private int weight;

    public Rectangle() {
    }

    public Rectangle(int height, int weight) {
        this.height = height;
        this.weight = weight;
    }

    public Rectangle(Rectangle rec) {
        this();
        this.height = rec.height;
        this.weight = rec.weight;
    }

    @Override
    public Shape clone() {
        return new Rectangle(this);
    }
}

class Circle implements Shape {

    private int radius;

    public Circle() {
    }

    public Circle(int radius) {
        this.radius = radius;
    }

    public Circle(Circle c) {
        this();
        this.radius = c.radius;
    }

    @Override
    public Shape clone() {
        return new Circle();
    }
}

public class PrototypeDemo {
    public static void main(String[] args) {
        Circle circle = new Circle(20);
        System.out.println("Circle Original: " + circle);
        Circle anotherCircle = (Circle) circle.clone();
        System.out.println("Circle Cloned: " + anotherCircle);
        System.out.println("Is Cloning of Circle success: " + (circle != anotherCircle));

        Rectangle rectangle = new Rectangle(10, 20);
        System.out.println("Rectangle Original: " + rectangle);
        Rectangle anotherRectangle = (Rectangle) rectangle.clone();
        System.out.println("Rectangle Cloned: " + anotherRectangle);
        System.out.println("Is Cloning of Rectangle success: " + (rectangle != anotherRectangle));
    }
}
