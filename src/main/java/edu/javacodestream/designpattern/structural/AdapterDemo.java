package edu.javacodestream.designpattern.structural;

/**
 * Ref# https://refactoring.guru/design-patterns/adapter
 *
 * Also known as: Wrapper
 * Adapter is a structural design pattern that allows objects with incompatible interfaces to collaborate.
 *
 * This example of the Adapter pattern is based on the classic conflict between square pegs and round holes.
 * The power plug and sockets standards are different in different countries.
 * some countries used square peg but some uses round peg
 *
 * Usage:
 * Use the Adapter class when you want to use some existing class, but its interface
 * isn’t compatible with the rest of your code.
 *
 * Now we have 2 types of plug: roundPeg and squarePeg, but the socket is of type roundHole.
 * since squarePeg plug in incompatible, we need an adapter.
 */

class RoundSocket {
    int radius;

    public RoundSocket(int radius) {
        this.radius = radius;
    }

    public int getRadius() {
        return this.radius;
    }
    public boolean fits(RoundPowerPlug roundPlug) {
        return this.radius >= roundPlug.getRadius();
    }
}

class RoundPowerPlug{
    int radius;

    public RoundPowerPlug(int radius) {
        this.radius = radius;
    }

    public int getRadius() {
        return this.radius;
    }
}

class SquarePowerPlug {
    int width;

    public SquarePowerPlug(int width) {
        this.width = width;
    }

    public int getWidth() {
        return width;
    }
}
// When a circle is inscribed in a square, the length of each side of the square
// is equal to the diameter of the circle. hence the radius is = (side of the square/2)
class SquarePowerPlugAdapter extends RoundPowerPlug {
    SquarePowerPlug squarePowerPlug;

    public SquarePowerPlugAdapter(SquarePowerPlug squarePowerPlug) {
        super(squarePowerPlug.width / 2); // radius is = (side of the square/2)
        this.squarePowerPlug = squarePowerPlug;
    }
}

public class AdapterDemo {
    public static void main(String[] args) {
        // Somewhere in client code.
        RoundSocket roundSocket = new RoundSocket(5);
        RoundPowerPlug roundPowerPlug = new RoundPowerPlug(5);
        roundSocket.fits(roundPowerPlug); // true

        SquarePowerPlug squarePowerPlug1 = new SquarePowerPlug(10);
//        holeSocket.fits(squarePowerPlug1); // compiler error due to incompatible type.
        SquarePowerPlug squarePowerPlug2 = new SquarePowerPlug(20);
//        holeSocket.fits(squarePowerPlug2); // compiler error due to incompatible type.

        SquarePowerPlugAdapter squarePowerPlugAdapter = new SquarePowerPlugAdapter(squarePowerPlug1);
        System.out.println(String.format("SquarePowerPlug of width: %s -- fits (Using Adapter) to RoundSocket of radius (%s) : %s",
                squarePowerPlug1.getWidth(), roundSocket.radius, roundSocket.fits(squarePowerPlugAdapter)));

        squarePowerPlugAdapter = new SquarePowerPlugAdapter(squarePowerPlug2);
        System.out.println(String.format("SquarePowerPlug of width: %s -- fits (Using Adapter) to RoundSocket of radius (%s) : %s",
                squarePowerPlug2.getWidth(), roundSocket.radius, roundSocket.fits(squarePowerPlugAdapter)));
    }
}
