package edu.javacodestream.designpattern.solid;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;

/**
 * The idea of Interface segregation principle is that instead of adding everything into
 * a single interface, like we did for the Machine interface below, we should put the absolute minimum
 * amount of behavior into the interface so that at no point, we need to implement certain method which
 * we don't need (like: OldFashionedPrinter need to only implement the print)
 */
class Document
{
}

interface Machine
{
    void print(Document d);
    void fax(Document d) throws Exception;
    void scan(Document d) throws Exception;
}

// ok if you need a multifunction machine
class MultiFunctionPrinter implements Machine
{
    public void print(Document d)
    {
        //
    }

    public void fax(Document d)
    {
        //
    }

    public void scan(Document d)
    {
        //
    }
}

class OldFashionedPrinter implements Machine
{
    public void print(Document d)
    {
        // the old fashioned printer can only print but still it has now need to implement with nothing for
        // the other 2 fax and scan, even if we want to throw an exception, we now need to change the
        // main interface
    }

    public void fax(Document d) throws Exception
    {
        throw new NotImplementedException();
    }

    public void scan(Document d) throws Exception
    {
        throw new NotImplementedException();
    }
}

interface Printer
{
    void Print(Document d) throws Exception;
}

interface IScanner
{
    void Scan(Document d) throws Exception;
}

class JustAPrinter implements Printer
{
    public void Print(Document d)
    {

    }
}

class Photocopier implements Printer, IScanner
{
    public void Print(Document d) throws Exception
    {
        throw new Exception();
    }

    public void Scan(Document d) throws Exception
    {
        throw new Exception();
    }
}

interface MultiFunctionDevice extends Printer, IScanner //
{

}

class MultiFunctionMachine implements MultiFunctionDevice
{
    // compose this out of several modules
    private Printer printer;
    private IScanner scanner;

    public MultiFunctionMachine(Printer printer, IScanner scanner)
    {
        this.printer = printer;
        this.scanner = scanner;
    }

    public void Print(Document d) throws Exception
    {
        printer.Print(d);
    }

    public void Scan(Document d) throws Exception
    {
        scanner.Scan(d);
    }
}

public class InterfaceSegregationPrinciple {
    public static void main(String[] args) {

    }
}
