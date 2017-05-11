package android.serialport;

import android.os.SystemClock;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class DeviceControl {
    public static final String POWER_MAIN = "/sys/class/misc/mtgpio/pin";
    public static final String POWER_EXTERNAL = "/sys/class/misc/aw9523/gpio";

    public enum PowerType {
        MAIN, EXPAND, MAIN_AND_EXPAND
    }

    private BufferedWriter CtrlFile;

    private String poweron = "";
    private String poweroff = "";
    private String currentPath = "";
    public DeviceControl(String path) throws IOException {
        File DeviceName = new File(path);
        CtrlFile = new BufferedWriter(new FileWriter(DeviceName, false));    //open file
        currentPath = path;
    }

    public void setGpio(int gpio) {
        if (currentPath.equals(POWER_EXTERNAL)) {
            poweron = gpio + "on";
            poweroff = gpio + "off";
        } else {
            poweron = "-wdout " + gpio + " 1";
            poweroff = "-wdout " + gpio + " 0";
        }
    }

    int[] gpios;
    private PowerType power_type;

    public DeviceControl(PowerType power_type, int... gpios) throws IOException {
        this.gpios = gpios;
        this.power_type = power_type;
    }

    public DeviceControl(String power_type, int... gpios) throws IOException {
        this.gpios = gpios;
        switch (power_type) {
            case "MAIN":
                this.power_type = PowerType.MAIN;
                break;
            case "EXPAND":
                this.power_type = PowerType.EXPAND;
                break;
            case "MAIN_AND_EXPAND":
                this.power_type = PowerType.MAIN_AND_EXPAND;
                break;
        }
    }

    public void MainPowerOn(int gpio) throws IOException {
        DeviceControl deviceControl = new DeviceControl(DeviceControl.POWER_MAIN);
        deviceControl.setGpio(gpio);
        deviceControl.writeON();
        deviceControl.DeviceClose();
    }

    public void MainPowerOff(int gpio) throws IOException {
        DeviceControl deviceControl = new DeviceControl(DeviceControl.POWER_MAIN);
        deviceControl.setGpio(gpio);
        deviceControl.WriteOff();
        deviceControl.DeviceClose();
    }

    public void ExpandPowerOn(int gpio) throws IOException {
        DeviceControl deviceControl = new DeviceControl(DeviceControl.POWER_EXTERNAL);
        deviceControl.setGpio(gpio);
        deviceControl.writeON();
        deviceControl.DeviceClose();
    }

    public void ExpandPowerOff(int gpio) throws IOException {
        DeviceControl deviceControl = new DeviceControl(DeviceControl.POWER_EXTERNAL);
        deviceControl.setGpio(gpio);
        deviceControl.WriteOff();
        deviceControl.DeviceClose();
    }




    private void writeON() throws IOException {
        CtrlFile.write(poweron);
        CtrlFile.flush();
    }

    private void WriteOff() throws IOException {
        CtrlFile.write(poweroff);
        CtrlFile.flush();
    }

    public void PowerOnDevice() throws IOException        //poweron id device
    {
        switch (power_type) {
            case MAIN:
                MainPowerOn(gpios[0]);
                SystemClock.sleep(200);
                break;
            case EXPAND:
                ExpandPowerOn(gpios[0]);
                SystemClock.sleep(200);
                break;
            case MAIN_AND_EXPAND:
                MainPowerOn(gpios[0]);
                SystemClock.sleep(200);
                for (int i = 1; i < gpios.length; i++) {
                    ExpandPowerOn(gpios[i]);
                    SystemClock.sleep(200);
                }
                break;
            default:
                break;
        }
    }


    public void PowerOffDevice() throws IOException        //poweroff id device
    {
        switch (power_type) {
            case MAIN:
                MainPowerOff(gpios[0]);
                break;
            case EXPAND:
                ExpandPowerOff(gpios[0]);
                break;
            case MAIN_AND_EXPAND:
                MainPowerOff(gpios[0]);
                for (int i = 1; i < gpios.length; i++) {
                    ExpandPowerOff(gpios[i]);
                }
                break;
            default:
                break;
        }
    }

    public void DeviceClose() throws IOException        //close file
    {
        CtrlFile.close();
    }

    public void setMode(int num, int mode) throws IOException {
        CtrlFile.write("-wmode" + num + " " + mode);   //设置为模式 0为GPIO模式
        CtrlFile.flush();
    }

    public void setDir(int num, int mode,String path) throws IOException {
        File DeviceName = new File(path);
        CtrlFile = new BufferedWriter(new FileWriter(DeviceName, false));    //open file
        CtrlFile.write("-wdir" + num + " " + mode);   //设置为输入输出
        CtrlFile.flush();
    }
}