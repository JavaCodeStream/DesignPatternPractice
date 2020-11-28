package edu.javacodestream.designpattern.creational.factory;

/**
 * Reference # https://refactoring.guru/design-patterns/factory-method
 *
 * Factory Method is a creational design pattern that provides an interface
 * for creating objects in a superclass, but allows subclasses to alter the
 * type of objects that will be created.
 *
 * The Factory Method separates product construction code from the code that
 * actually uses the product. Therefore it’s easier to extend the product
 * construction code independently from the rest of the code.
 */
interface Button {
    void onClick();
}

class HTMLButton implements Button {
    @Override
    public void onClick() {
        System.out.println("HTML Button is CLICKED");
    }
}

class WindowsButton implements Button {

    @Override
    public void onClick() {
        System.out.println("Windows Button is CLICKED");
    }
}

interface Dialog {
    Button createButton();
}

class WebDialog  implements Dialog {

    @Override
    public Button createButton() {
        return new HTMLButton();
    }
}

class WindowsDialog implements Dialog {

    @Override
    public Button createButton() {
        return new WindowsButton();
    }
}

public class FactoryMethodDemo {
    public static void main(String[] args) {
        WebDialog webDialog = new WebDialog();
        Button webButton = webDialog.createButton();
        webButton.onClick();

        WindowsDialog windowsDialog = new WindowsDialog();
        Button windowsButton = windowsDialog.createButton();
        windowsButton.onClick();
    }
}
