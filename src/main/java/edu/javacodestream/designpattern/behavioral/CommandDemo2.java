package edu.javacodestream.designpattern.behavioral;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.checkerframework.checker.guieffect.qual.UI;

import java.util.Map;
import java.util.stream.Collectors;

/**
 * Ref# https://refactoring.guru/design-patterns/command
 *
 * Your current task is to create a toolbar with a bunch of buttons for various
 * operations of the editor. The simplest solution is to create tons of subclasses
 * for each place where the button is used. These subclasses would contain the code
 * that would have to be executed on a button click.
 *
 * problem1: you have an enormous number of subclasses
 * problem2: Some operations, such as copying/pasting text, would need to be
 * invoked from multiple places. via “Copy” button on the toolbar, via the context menu,
 * or via Ctrl+C on the keyboard Short-cut.
 * when you implement context menus, shortcuts, and other stuff, you have to either
 * duplicate the operation’s code in many classes
 *
 * Solution:
 * Command pattern, we no longer need all those button subclasses to implement various
 * click behaviors. It’s enough to put a single field into the base Button class that
 * stores a reference to a command object and make the button execute that command on a click.
 *
 * You’ll implement a bunch of command classes for every possible operation and link them with
 * particular buttons, depending on the buttons’ intended behavior.
 *
 * Here, every particular button is the invoker with the object ref of the specific command class.
 *
 */
enum UIAction {

    COPY("CTRL+C"), PASTE("CTRL+P");

    private String value;

    public String getValue() {
        return value;
    }

    UIAction(String value) {
        this.value = value;
    }

    static UIAction getByValue(String value) {
        return Lists.newArrayList(UIAction.values())
                .stream().filter(action -> action.getValue().equals(value)).findFirst().get();
    }
}

interface UICommand {
    void execute();
}

class CopyCommand implements UICommand {
    @Override
    public void execute() {
        System.out.println("Copy Command: Copied the content to clipboard..");
    }
}

class PasteCommand implements UICommand {
    @Override
    public void execute() {
        System.out.println("Paste Command: Pasted the content from clipboard..");
    }
}

class Button {
    private UICommand command;
    public Button(UICommand command) {
        this.command = command;
    }
    public void click() {
        System.out.println("Button Clicked...");
        this.command.execute();
    }
}

class ContextMenu {
    private Map<UIAction, UICommand> actionUICommandMap = Maps.newHashMap();
    public ContextMenu() {
    }
    public void addMenuAction(UIAction action, UICommand command) {
        this.actionUICommandMap.putIfAbsent(action, command);
    }
    public void click(UIAction action) {
        System.out.println("ContextMenu - " + action.name() + " - Clicked...");
        this.actionUICommandMap.get(action).execute();
    }
}

class KeyboardShortCut {
    private Map<UIAction, UICommand> actionUICommandMap = Maps.newHashMap();
    public KeyboardShortCut() {
    }
    public void addMenuAction(UIAction action, UICommand command) {
        this.actionUICommandMap.putIfAbsent(action, command);
    }
    public void click(UIAction action) {
        System.out.println("KeyboardShortCut - " + action.name() + " - Clicked...");
        this.actionUICommandMap.get(action).execute();
    }
}

public class CommandDemo2 {
    public static void main(String[] args) {
        UICommand copyCommand = new CopyCommand();
        UICommand pasteCommand = new PasteCommand();

        Button copyButton = new Button(copyCommand);
        copyButton.click();

        ContextMenu contextMenu = new ContextMenu();
        contextMenu.addMenuAction(UIAction.COPY, copyCommand);
        contextMenu.addMenuAction(UIAction.PASTE, pasteCommand);
        contextMenu.click(UIAction.COPY);
        contextMenu.click(UIAction.PASTE);

        KeyboardShortCut keyboardShortCuts = new KeyboardShortCut();
        keyboardShortCuts.addMenuAction(UIAction.getByValue("CTRL+C"), copyCommand);
        keyboardShortCuts.addMenuAction(UIAction.getByValue("CTRL+P"), pasteCommand);
        keyboardShortCuts.click(UIAction.COPY);
        keyboardShortCuts.click(UIAction.PASTE);
    }
}
