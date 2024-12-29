package org.example;

public class Task4 {
    public static void main(String[] args) {
        OldDevice oldDevice = new OldDeviceImpl();
        NewDevice adapter = new DeviceAdapter(oldDevice);
        adapter.operateNewFunction();
    }
}
interface OldDevice{
    void operateOldFunction();
}
interface NewDevice{
    void operateNewFunction();
}
class OldDeviceImpl implements OldDevice{
    @Override
    public void operateOldFunction() {
        System.out.println("Operating old device function.");
    }
}
class NewDeviceImpl implements NewDevice{
    @Override
    public void operateNewFunction() {
        System.out.println("Operating new device function.");
    }
}
class DeviceAdapter implements NewDevice{
    private OldDevice oldDevice;
    public DeviceAdapter(OldDevice oldDevice) {
        this.oldDevice = oldDevice;
    }
    @Override
    public void operateNewFunction() {
        oldDevice.operateOldFunction();
    }
}
