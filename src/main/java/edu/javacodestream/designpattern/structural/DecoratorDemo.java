package edu.javacodestream.designpattern.structural;

/**
 * Decorator is a structural design pattern that lets you attach new behaviors to objects
 * by placing these objects inside special wrapper objects that contain the behaviors.
 *
 * In this example, the Decorator pattern lets you compress and encrypt sensitive data
 * independently from the code that actually writing this data.
 *
 * the main role of FileDataSource is to read/wrire data into file.
 * in addition to this function , we wanted to add
 * 1. the ability of compress/de-compress before write/read
 * 2. the ability of encrypt/de-crypt before write/read
 *
 * Usage:
 * Use the Decorator pattern when you need to be able to assign extra behaviors to objects at
 * runtime without breaking the code that uses these objects.
 */

// The component interface defines operations that can be
// altered by decorators.
interface DataSource {
    void writeData(String data);
    void readData();
}

// Concrete components provide default implementations for the read/write operations.
class FileDataSource implements DataSource {

    private String fileName;

    public FileDataSource(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void writeData(String data) {
        System.out.println("Write data to file: " + this.fileName);
    }

    @Override
    public void readData() {
        System.out.println("Read data from file: " + this.fileName);
    }
}

// The base decorator class follows the same interface as the
// other components. The primary purpose of this class is to
// define the wrapping interface for all concrete decorators.
// The default implementation of the wrapping code might include
// a field for storing a wrapped component and the means to
// initialize it.
class DataSourceDecorator implements DataSource {

    protected DataSource wrappedDS;

    public DataSourceDecorator(DataSource wrappedDS) {
        this.wrappedDS = wrappedDS;
    }

    @Override
    public void writeData(String data) {
        wrappedDS.writeData(data);
    }

    @Override
    public void readData() {
        wrappedDS.readData();
    }
}

// Concrete decorators must call methods on the wrapped object,
// but may also add something of their own to the result.
class EncryptionDecorator extends DataSourceDecorator {

    public EncryptionDecorator(DataSource wrappedDS) {
        super(wrappedDS);
    }

    // Encrypt the data before write is done by the decorator
    public void writeData(String data) {
        System.out.println("EncryptionDecorator: Encrypt data before write");
        wrappedDS.writeData(data);
    }

    // De-Encrypt the data after read is done by the decorator
    public void readData() {
        wrappedDS.readData();
        System.out.println("EncryptionDecorator: Decrypt data after read");
    }
}

class CompressionDecorator extends DataSourceDecorator {

    public CompressionDecorator(DataSource wrappedDS) {
        super(wrappedDS);
    }

    public void writeData(String data) {
        System.out.println("CompressionDecorator: Compress data before write");
        wrappedDS.writeData(data);
    }

    public void readData() {
        wrappedDS.readData();
        System.out.println("CompressionDecorator: De-Compression data after read");
    }
}

public class DecoratorDemo {
    public static void main(String[] args) {
        // The target file has been written with plain data.
        System.out.println("Using the Basic FileDataSource");
        DataSource basicDS = new FileDataSource("somefile.dat");
        basicDS.writeData("test data");
        basicDS.readData();

        System.out.println("========================================");
        System.out.println("Using the Basic EncryptionDecorator");
        DataSource encryptionDecoratorDS = new EncryptionDecorator(basicDS);
        encryptionDecoratorDS.writeData("test data");
        encryptionDecoratorDS.readData();

        System.out.println("========================================");
        System.out.println("Using the Basic EncryptionDecorator");
        DataSource compressionDecoratorDS = new CompressionDecorator(basicDS);
        compressionDecoratorDS.writeData("test data");
        compressionDecoratorDS.readData();
    }

}
