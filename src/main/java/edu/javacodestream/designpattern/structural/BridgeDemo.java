package edu.javacodestream.designpattern.structural;

/**
 * REf# https://refactoring.guru/design-patterns/bridge
 *
 * Bridge is used when we need to decouple an abstraction from its implementation
 * so that the two can vary independently.
 *
 * lets say we have 2 devices, TV and Radio and they have implemented the common Device interface for
 * the common contracts like: get/set volume, get/set channel etc.
 *
 * Now during the wireless era, we are asked to create remote control for these devices,
 * The obvious choice is to create 2 new subclasses, TVRemoteControl and RadioRemoteControl
 *
 * else, use the Bridge pattern like below:
 * You can develop a single universal remote control classes independently from the device classes.
 * The class declares a reference field that links it with a device object.
 */
enum DeviceEnumType {
    TV, RADIO
}

interface Device {
    DeviceEnumType getDeviceType();
    boolean isSwitchedOn();
    boolean switchOn();
    boolean switchOff();
    int getVolume();
    void setVolume(int volume);
    int getChannel();
    void setChannel(int channel);
}

class Tv implements Device {
    DeviceEnumType deviceEnumType;
    boolean switchedOn;
    int currentVolume;
    int currentChannel;

    public Tv() {
      this.deviceEnumType = DeviceEnumType.TV;
    }

    @Override
    public DeviceEnumType getDeviceType() {
        return this.deviceEnumType;
    }

    @Override
    public boolean isSwitchedOn() {
        return switchedOn;
    }

    @Override
    public boolean switchOn() {
        return switchedOn;
    }

    @Override
    public boolean switchOff() {
        return switchedOn;
    }

    @Override
    public int getVolume() {
        return currentVolume;
    }

    @Override
    public void setVolume(int volume) {
        this.currentVolume = volume;
    }

    @Override
    public int getChannel() {
        return currentChannel;
    }

    @Override
    public void setChannel(int channel) {
        this.currentChannel = channel;
    }

    // additional function specific to TV
}

class Radio implements Device {
    DeviceEnumType deviceEnumType;
    boolean switchedOn;
    int currentVolume;
    int currentChannel;

    public Radio() {
        this.deviceEnumType = DeviceEnumType.RADIO;
    }

    @Override
    public DeviceEnumType getDeviceType() {
        return this.deviceEnumType;
    }

    @Override
    public boolean isSwitchedOn() {
        return switchedOn;
    }

    @Override
    public boolean switchOn() {
        return switchedOn;
    }

    @Override
    public boolean switchOff() {
        return switchedOn;
    }

    @Override
    public int getVolume() {
        return currentVolume;
    }

    @Override
    public void setVolume(int volume) {
        this.currentVolume = volume;
    }

    @Override
    public int getChannel() {
        return currentChannel;
    }

    @Override
    public void setChannel(int channel) {
        this.currentChannel = channel;
    }

    // additional function specific to Radio
}

class RemoteControl {
    protected Device device;

    public RemoteControl(Device device) {
        this.device = device;
    }

    public void togglePower() {
        System.out.println("Toggle for device: " + device.getDeviceType());
        if (device.isSwitchedOn()) device.switchOff();
        else device.switchOn();
    }

    public int volumeUp() {
        System.out.println("Volume up for device: " + device.getDeviceType());
        device.setVolume(device.getVolume() + 1);
        return device.getVolume();
    }

    public int volumeDown() {
        System.out.println("Volume down for device: " + device.getDeviceType());
        device.setVolume(device.getVolume() - 1);
        return device.getVolume();
    }
    public int channelUp() {
        System.out.println("Channel up for device: " + device.getDeviceType());
        device.setChannel(device.getChannel() + 1);
        return device.getChannel();
    }
    public int channelDown() {
        System.out.println("Channel down for device: " + device.getDeviceType());
        device.setChannel(device.getChannel() - 1);
        return device.getChannel();
    }

    public void mute() {
        device.setVolume(0);
    }
}

public class BridgeDemo {
    public static void main(String[] args) {
        Tv tv = new Tv();
        RemoteControl remoteTv = new RemoteControl(tv);
        remoteTv.togglePower();
        remoteTv.volumeUp();

        Radio radio = new Radio();
        RemoteControl remoteRadio = new RemoteControl(radio);
        remoteRadio.togglePower();
        remoteRadio.volumeUp();
    }
}
