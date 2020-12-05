package edu.javacodestream.designpattern.behavioral;

import java.util.Scanner;

/**
 * Ref# https://refactoring.guru/design-patterns/chain-of-responsibility
 *
 * the Chain of Responsibility relies on transforming particular behaviors into stand-alone objects
 * called handlers. In our case, each check should be extracted to its own class with a single
 * method that performs the check. The request, along with its data, is passed to this method
 * as an argument.
 *
 * The pattern suggests that you link these handlers into a chain. Each linked handler has a
 * field for storing a reference to the next handler in the chain. In addition to processing a
 * request, handlers pass the request further along the chain. The request travels along the chain
 * until all handlers have had a chance to process it.
 *
 * Here’s the best part: a handler can decide not to pass the request further down the chain and
 * effectively stop any further processing.
 *
 * Usage:
 * Use the pattern when it’s essential to execute several handlers in a particular order.
 * Use the CoR pattern when the set of handlers and their order are supposed to change at runtime.
 */
class Currency {
    private final int amount;
    public Currency(int amt){
        this.amount = amt;
    }
    public int getAmount(){
        return this.amount;
    }
}

interface DispenserChain {
    void setNextChain(DispenserChain nextChain);
    void dispense(Currency cur);
}

class Dollar50Dispenser implements DispenserChain {
    private DispenserChain nextDispenserChain;
    @Override
    public void setNextChain(DispenserChain nextDispenserChain) {
        this.nextDispenserChain = nextDispenserChain;
    }
    @Override
    public void dispense(Currency cur) {
        if(cur.getAmount() >= 50){
            int num = cur.getAmount()/50;
            int remainder = cur.getAmount() % 50;
            System.out.println("Dispensing "+num+" 50$ note");
            if(remainder !=0) this.nextDispenserChain.dispense(new Currency(remainder));
        }
        else {
            this.nextDispenserChain.dispense(cur);
        }
    }
}

class Dollar20Dispenser implements DispenserChain{

    private DispenserChain nextDispenserChain;

    @Override
    public void setNextChain(DispenserChain nextDispenserChain) {
        this.nextDispenserChain = nextDispenserChain;
    }

    @Override
    public void dispense(Currency cur) {
        if(cur.getAmount() >= 20){
            int num = cur.getAmount()/20;
            int remainder = cur.getAmount() % 20;
            System.out.println("Dispensing "+num+" 20$ note");
            if(remainder !=0) this.nextDispenserChain.dispense(new Currency(remainder));
        }
        else {
            this.nextDispenserChain.dispense(cur);
        }
    }
}

class Dollar10Dispenser implements DispenserChain {

    private DispenserChain nextDispenserChain;

    @Override
    public void setNextChain(DispenserChain nextDispenserChain) {
        this.nextDispenserChain = nextDispenserChain;
    }

    @Override
    public void dispense(Currency cur) {
        if(cur.getAmount() >= 10){
            int num = cur.getAmount()/10;
            int remainder = cur.getAmount() % 10;
            System.out.println("Dispensing "+num+" 10$ note");
            if(remainder !=0) this.nextDispenserChain.dispense(new Currency(remainder));
        }
        else {
            this.nextDispenserChain.dispense(cur);
        }
    }
}

public class ChainOfResponsibilityDemo1 {
    private final DispenserChain c1;
    private final DispenserChain c2;
    private final DispenserChain c3;

    public ChainOfResponsibilityDemo1() {
        this.c1 = new Dollar50Dispenser();
        this.c2 = new Dollar20Dispenser();
        this.c3 = new Dollar10Dispenser();
        this.c1.setNextChain(c2);
        this.c2.setNextChain(c3);
    }

    public static void main(String[] args) {
        ChainOfResponsibilityDemo1 demo1 = new ChainOfResponsibilityDemo1();
        while (true) {
            int amount = 0;
            System.out.println("Enter amount to dispense: ");
            Scanner input = new Scanner(System.in);
            amount = input.nextInt();
            if (amount % 10 != 0) {
                System.out.println("Amount should be in multiple of 10s.");
                continue;
            }
            // process the request
            demo1.c1.dispense(new Currency(amount));
        }
    }
}
