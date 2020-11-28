package edu.javacodestream.designpattern.creational.factory;

/**
 * Abstract Factory is a creational design pattern that lets you produce families
 * of related objects without specifying their concrete classes.
 *
 * Use the Abstract Factory when your code needs to work with various families of
 * related products, but you don’t want it to depend on the concrete classes of those products
 */
interface UIButton {
    void paint();
}

class WinButton implements UIButton {
    @Override
    public void paint() {
        System.out.println("Render a button in Windows style.");
    }
}

class MacButton implements UIButton {

    @Override
    public void paint() {
        System.out.println("Render a button in macOS style");
    }
}

interface UICheckbox {
    void paint();
}

class WinCheckbox implements UICheckbox {

    @Override
    public void paint() {
        System.out.println("Render a checkbox in Windows style.");
    }
}

class MacCheckbox implements UICheckbox {

    @Override
    public void paint() {
        System.out.println("Render a checkbox in macOS style.");
    }
}

interface GUIFactory {
    UIButton createButton();
    UICheckbox createCheckbox();
}

class WinFactory implements GUIFactory {

    @Override
    public UIButton createButton() {
        return new WinButton();
    }

    @Override
    public UICheckbox createCheckbox() {
        return new WinCheckbox();
    }
}

class MacFactory implements GUIFactory {

    @Override
    public UIButton createButton() {
        return new MacButton();
    }

    @Override
    public UICheckbox createCheckbox() {
        return new MacCheckbox();
    }
}

public class AbstractFactoryDemo1 {
    public static void main(String[] args) {
        GUIFactory winFactory = new WinFactory();
        UIButton winButton = winFactory.createButton();
        winButton.paint();

        UICheckbox winCheckbox = winFactory.createCheckbox();
        winCheckbox.paint();

        MacFactory macFactory = new MacFactory();
        UIButton macButton = macFactory.createButton();
        macButton.paint();
        UICheckbox macCheckbox = macFactory.createCheckbox();
        macCheckbox.paint();
    }
}
