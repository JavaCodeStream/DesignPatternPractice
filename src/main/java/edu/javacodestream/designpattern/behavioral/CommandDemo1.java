package edu.javacodestream.designpattern.behavioral;

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
 * or via Ctrl+C on the keyboard.
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
interface FileSystem {
    void openFile();
    void writeFile();
    void closeFile();
}
class UnixFileSystem implements FileSystem {

    @Override
    public void openFile() {
        System.out.println("Opening file in unix OS");
    }
    @Override
    public void writeFile() {
        System.out.println("Writing file in unix OS");
    }
    @Override
    public void closeFile() {
        System.out.println("Closing file in unix OS");
    }
}

class WindowsFileSystem implements FileSystem {
    @Override
    public void openFile() {
        System.out.println("Opening file in Windows OS");

    }
    @Override
    public void writeFile() {
        System.out.println("Writing file in Windows OS");
    }
    @Override
    public void closeFile() {
        System.out.println("Closing file in Windows OS");
    }
}

interface Command {
    void execute();
}

class OpenFileCommand implements Command {
    private FileSystem fileSystem;
    public OpenFileCommand(FileSystem fs){
        this.fileSystem = fs;
    }
    @Override
    public void execute() {
        //open command is forwarding request to openFile method
        this.fileSystem.openFile();
    }
}

class CloseFileCommand implements Command {
    private FileSystem fileSystem;
    public CloseFileCommand(FileSystem fs){
        this.fileSystem=fs;
    }
    @Override
    public void execute() {
        this.fileSystem.closeFile();
    }
}

class WriteFileCommand implements Command {
    private FileSystem fileSystem;
    public WriteFileCommand(FileSystem fs){
        this.fileSystem=fs;
    }
    @Override
    public void execute() {
        this.fileSystem.writeFile();
    }
}

class FileSystemReceiverUtil {
    public static FileSystem getUnderlyingFileSystem(){
        String osName = System.getProperty("os.name");
        System.out.println("Underlying OS is:"+osName);
        if(osName.contains("Windows")){
            return new WindowsFileSystem();
        }else{
            return new UnixFileSystem();
        }
    }
}

class FileEditor {
    public Command command;

    public FileEditor(Command command) {
        this.command = command;
    }

    public void execute(){
        this.command.execute();
    }
}

public class CommandDemo1 {
    public static void main(String[] args) {
        //Creating the receiver object
        FileSystem fs = FileSystemReceiverUtil.getUnderlyingFileSystem();

        //creating command and associating with receiver
        OpenFileCommand openFileCommand = new OpenFileCommand(fs);

        //Creating invoker and associating with Command
        FileEditor file = new FileEditor(openFileCommand);
        //perform action on invoker object
        file.execute();

        WriteFileCommand writeFileCommand = new WriteFileCommand(fs);
        file = new FileEditor(writeFileCommand);
        file.execute();

        CloseFileCommand closeFileCommand = new CloseFileCommand(fs);
        file = new FileEditor(closeFileCommand);
        file.execute();
    }
}
