package android.serialport;

import android.os.SystemClock;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * 注意：
 * 无论新老设备，操作主板gpio， 需要设置gpio模式和输出模式，代码调用需要先设置为gpio模式再设置输出模式再讲gpio拉高
 */
public class DeviceControl {
    //kt系列

    public static final String POWER_MAIN = "/sys/class/misc/mtgpio/pin";

    //背夹 以及 SD60

    public static final String POWER_EXTERNAL = "/sys/class/misc/aw9523/gpio";

    //sk80 新添加

    public static final String POWER_EXTERNAL2 = "/sys/class/misc/aw9524/gpio";

    //新设备上电路径(SD55,SD60)

    public static final String POWER_NEWMAIN = "/sys/bus/platform/drivers/mediatek-pinctrl/10005000.pinctrl/mt_gpio";

    /**
     * 高通平台上电
     */
    public static final String POWER_GAOTONG = "/sys/class/switch/app_switch/app_state";

    /**
     * 上电类型
     */
    public enum PowerType {
        /**
         * 主板
         */
        MAIN,
        /**
         * 外部扩展
         */
        EXPAND,
        /**
         * 主板和外部扩展
         */
        MAIN_AND_EXPAND,
        /**
         * 新设备 sd55上电
         */
        NEW_MAIN,
        /**
         * 9524上电sk80使用
         */
        EXPAND2,
        /**
         * 主板和9524上电
         */
        MAIN_AND_EXPAND2,
        /**
         * 高通平台上电
         */
        GAOTONG_MAIN

    }

    private BufferedWriter CtrlFile;

    private String poweron = "";
    private String poweroff = "";
    private String currentPath = "";

    public DeviceControl() throws IOException {

    }

    public DeviceControl(String path) throws IOException {
        File DeviceName = new File(path);
        //open file
        CtrlFile = new BufferedWriter(new FileWriter(DeviceName, false));
        currentPath = path;
    }


    private int[] gpios;
    private String[] gtGpios;

    /**
     * 此方法可单独设置gpio
     *
     * @param gpio 设置gpio
     */
    public void setGpio(int gpio) {
        if (currentPath.equals(POWER_EXTERNAL) || currentPath.equals(POWER_EXTERNAL2)) {
            poweron = gpio + "on";
            poweroff = gpio + "off";
        } else {
            //将GPIO99设置为GPIO模式
            poweron = "-wmode " + gpio + " 0";
            //将GPIO99设置为输出模式
            poweron = "-wdir " + gpio + " 1";
            poweron = "-wdout " + gpio + " 1";
            poweroff = "-wdout " + gpio + " 0";
        }
    }

    private PowerType power_type;

    /**
     * @param powerType 上电类型
     * @param gpios     若为主板上电 gpio[0]需为主板gpio 扩展gpio可以有多个
     * @throws IOException
     */
    public DeviceControl(PowerType powerType, int... gpios) throws IOException {
        this.gpios = gpios;
        this.power_type = powerType;
    }

    public DeviceControl(String powerType, String... gpios) throws IOException {
        this.gtGpios = gpios;
        switch (powerType) {
            case "GAOTONG_MAIN":
                this.power_type = PowerType.GAOTONG_MAIN;
                break;
            default:
                break;
        }
    }

    /**
     * @param powerType 上电类型
     * @param gpios     若为主板上电 gpio[0]需为主板gpio 扩展gpio可以有多个
     * @throws IOException
     */
    public DeviceControl(String powerType, int... gpios) throws IOException {
        this.gpios = gpios;
        switch (powerType) {
            case "MAIN":
                this.power_type = PowerType.MAIN;
                break;
            case "EXPAND":
                this.power_type = PowerType.EXPAND;
                break;
            case "MAIN_AND_EXPAND":
                this.power_type = PowerType.MAIN_AND_EXPAND;
                break;
            case "NEW_MAIN":
                this.power_type = PowerType.NEW_MAIN;
                break;
            case "EXPAND2":
                this.power_type = PowerType.EXPAND2;
                break;
            case "MAIN_AND_EXPAND2":
                this.power_type = PowerType.MAIN_AND_EXPAND2;
                break;
            case "GAOTONG_MAIN":
                this.power_type = PowerType.GAOTONG_MAIN;
                break;
            default:
                break;

        }
    }

    /**
     * 高通平台 上电下电
     *
     * @param s open uhf上电； close uhf下电
     */
    public void gtPower(String s) {
        try {
            File DeviceName = new File(POWER_GAOTONG);
            CtrlFile = new BufferedWriter(new FileWriter(DeviceName, false));
            CtrlFile.write(s);
            CtrlFile.flush();
            CtrlFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 主板上电
     *
     * @param gpio
     * @throws IOException
     */
    public void MainPowerOn(int gpio) throws IOException {
        DeviceControl deviceControl = new DeviceControl(DeviceControl.POWER_MAIN);
        deviceControl.setGpio(gpio);
        deviceControl.writeON();
        deviceControl.DeviceClose();
    }

    /**
     * 主板下电
     *
     * @param gpio
     * @throws IOException
     */
    public void MainPowerOff(int gpio) throws IOException {
        DeviceControl deviceControl = new DeviceControl(DeviceControl.POWER_MAIN);
        deviceControl.setGpio(gpio);
        deviceControl.WriteOff();
        deviceControl.DeviceClose();
    }

    /**
     * 外部扩展上电 9523
     *
     * @param gpio
     * @throws IOException
     */
    public void ExpandPowerOn(int gpio) throws IOException {
        DeviceControl deviceControl = new DeviceControl(DeviceControl.POWER_EXTERNAL);
        deviceControl.setGpio(gpio);
        deviceControl.writeON();
        deviceControl.DeviceClose();
    }

    /**
     * 外部扩展上电 9524
     *
     * @param gpio
     * @throws IOException
     */
    public void Expand2PowerOn(int gpio) throws IOException {
        DeviceControl deviceControl = new DeviceControl(DeviceControl.POWER_EXTERNAL2);
        deviceControl.setGpio(gpio);
        deviceControl.writeON();
        deviceControl.DeviceClose();
    }

    /**
     * 外部扩展下电 9523
     *
     * @param gpio
     * @throws IOException
     */
    public void ExpandPowerOff(int gpio) throws IOException {
        DeviceControl deviceControl = new DeviceControl(DeviceControl.POWER_EXTERNAL);
        deviceControl.setGpio(gpio);
        deviceControl.WriteOff();
        deviceControl.DeviceClose();
    }

    /**
     * 外部扩展上电 9524
     *
     * @param gpio
     * @throws IOException
     */
    public void Expand2PowerOff(int gpio) throws IOException {
        DeviceControl deviceControl = new DeviceControl(DeviceControl.POWER_EXTERNAL2);
        deviceControl.setGpio(gpio);
        deviceControl.WriteOff();
        deviceControl.DeviceClose();
    }


    /**
     * 写ON
     *
     * @throws IOException
     */
    private void writeON() throws IOException {
        CtrlFile.write(poweron);
        CtrlFile.flush();
    }

    /**
     * 写off
     *
     * @throws IOException
     */
    private void WriteOff() throws IOException {
        CtrlFile.write(poweroff);
        CtrlFile.flush();
    }

    /**
     * 构造函数之后 可带调用此方法上电
     *
     * @throws IOException 抛出异常
     */
    public void PowerOnDevice() throws IOException {
        switch (power_type) {
            case MAIN:
                for (int i = 0; i < gpios.length; i++) {
                    MainPowerOn(gpios[i]);
                    SystemClock.sleep(100);
                }
                break;
            case EXPAND:
                for (int i = 0; i < gpios.length; i++) {
                    ExpandPowerOn(gpios[i]);
                    SystemClock.sleep(100);
                }
                break;
            case MAIN_AND_EXPAND:
                MainPowerOn(gpios[0]);
                SystemClock.sleep(100);
                for (int i = 1; i < gpios.length; i++) {
                    ExpandPowerOn(gpios[i]);
                    SystemClock.sleep(100);
                }
                break;
            case NEW_MAIN:
                for (int i = 0; i < gpios.length; i++) {
                    newSetGpioOn(gpios[i]);
                    SystemClock.sleep(100);
                }
                break;
            case EXPAND2:
                for (int i = 0; i < gpios.length; i++) {
                    Expand2PowerOn(gpios[i]);
                    SystemClock.sleep(100);
                }
                break;
            case MAIN_AND_EXPAND2:
                MainPowerOn(gpios[0]);
                SystemClock.sleep(100);
                for (int i = 1; i < gpios.length; i++) {
                    Expand2PowerOn(gpios[i]);
                    SystemClock.sleep(100);
                }
                break;
            case GAOTONG_MAIN:
                gtPower(gtGpios[0]);

                break;
            default:
                break;
        }
    }


    /**
     * 构造函数后 程序退出时可调用此方法下电
     *
     * @throws IOException 抛出异常
     */
    public void PowerOffDevice() throws IOException {
        switch (power_type) {
            case MAIN:
                for (int i = 0; i < gpios.length; i++) {
                    MainPowerOff(gpios[i]);
                    SystemClock.sleep(100);
                }
                break;
            case EXPAND:
                for (int i = 0; i < gpios.length; i++) {
                    ExpandPowerOff(gpios[i]);
                    SystemClock.sleep(100);
                }
                break;
            case EXPAND2:
                for (int i = 0; i < gpios.length; i++) {
                    Expand2PowerOff(gpios[i]);
                    SystemClock.sleep(100);
                }
                break;
            case MAIN_AND_EXPAND:
                MainPowerOff(gpios[0]);
                for (int i = 1; i < gpios.length; i++) {
                    ExpandPowerOff(gpios[i]);
                }
                break;
            case MAIN_AND_EXPAND2:
                MainPowerOff(gpios[0]);
                for (int i = 1; i < gpios.length; i++) {
                    Expand2PowerOff(gpios[i]);
                }
                break;
            case NEW_MAIN:
                for (int i = 0; i < gpios.length; i++) {
                    newSetGpioOff(gpios[i]);
                }
                break;
            case GAOTONG_MAIN:
                gtPower(gtGpios[1]);
                break;
            default:
                break;
        }
    }

    /**
     * 关闭文件
     *
     * @throws IOException
     */
    public void DeviceClose() throws IOException {
        CtrlFile.close();
    }

    /**
     * 设置制定管脚模式  0为gpio模式
     *
     * @param num  管脚
     * @param mode 0为gpio模式
     * @throws IOException
     */
    public void setMode(int num, int mode) throws IOException {
        //设置为模式 0为GPIO模式
        CtrlFile.write("-wmode" + num + " " + mode);
        CtrlFile.flush();
    }

    /**
     * 设置输入输出模式
     *
     * @param num  管脚
     * @param mode 输入输出模式
     * @param path 路径
     * @throws IOException
     */
    public void setDir(int num, int mode, String path) throws IOException {
        File DeviceName = new File(path);
        CtrlFile = new BufferedWriter(new FileWriter(DeviceName, false));
        //设置为输入输出
        CtrlFile.write("-wdir" + num + " " + mode);
        CtrlFile.flush();
    }

    /**
     * 设置上拉下拉
     *
     * @param num
     * @param mode
     * @param path
     * @throws IOException
     */
    public void setPull(int num, int mode, String path) throws IOException {
        File DeviceName = new File(path);
        CtrlFile = new BufferedWriter(new FileWriter(DeviceName, false));
        // 设置为输入输出
        CtrlFile.write("-wpsel" + num + " " + mode);
        CtrlFile.flush();
        //设置为输入输出
        CtrlFile.write("-wpen" + num + " " + mode);
        CtrlFile.flush();
    }

    //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

    /**
     * 设置新设备上电 例如sd55 sd60
     *
     * @param gpio
     * @throws IOException
     */
    public void newSetGpioOn(int gpio) throws IOException {
        DeviceControl deviceControl = new DeviceControl(POWER_NEWMAIN);
        //将GPIO设置为高电平
        deviceControl.CtrlFile.write("out " + gpio + " 1");
        deviceControl.CtrlFile.flush();
        newSetMode(gpio);
        newSetDir(gpio, 1);
    }

    /**
     * 设置新设备下电 例如sd55 sd60
     *
     * @param gpio
     * @throws IOException
     */
    public void newSetGpioOff(int gpio) throws IOException {
        DeviceControl deviceControl = new DeviceControl(POWER_NEWMAIN);
        //将GPIO设置为低电平
        deviceControl.CtrlFile.write("out " + gpio + " 0");
        deviceControl.CtrlFile.flush();
    }

    /**
     * 设置新设备下电gpio为 gpio模式 例如sd55 sd60
     *
     * @param gpio
     * @throws IOException
     */
    public void newSetMode(int gpio) throws IOException {
        DeviceControl deviceControl = new DeviceControl(POWER_NEWMAIN);
        //将GPIO设置为GPIO模式
        deviceControl.CtrlFile.write("mode " + gpio + " 0");
        deviceControl.CtrlFile.flush();
    }

    /**
     * 设置新设备下电gpio为输入/输出模式 例如sd55 sd60
     *
     * @param gpio 要操作的gpio
     * @param dir  0：输入模式  1：输出模式
     * @throws IOException
     */
    public void newSetDir(int gpio, int dir) throws IOException {
        DeviceControl deviceControl = new DeviceControl(POWER_NEWMAIN);
        deviceControl.CtrlFile.write("dir " + gpio + " " + dir);
        deviceControl.CtrlFile.flush();
    }
}