package android.serialport;

import android.os.SystemClock;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * 注意：
 * 无论新老设备，操作主板gpio， 需要设置gpio模式和输出模式，代码调用需要先设置为gpio模式-再设置输出模式-再将gpio拉高
 */
public class DeviceControlSpd {
    /**
     * MTK(6737)平台安卓6.0及以下版本 主板上电路径（例如：kt55、kt50、kt80、kt40、sk100）
     */
    public static final String POWER_MAIN = "/sys/class/misc/mtgpio/pin";

    /**
     * 扩展GPIO 上电路径
     */
    public static final String POWER_EXTERNAL = "/sys/class/misc/aw9523/gpio";

    /**
     * 扩展GPIO 上电路径2 （目前只针对sk80新添加）
     */
    public static final String POWER_EXTERNAL2 = "/sys/class/misc/aw9524/gpio";

    /**
     * MTK(6763)平台安卓8.1版本  主板上电路径(例如：SD55、SD60)
     */
    public static final String POWER_NEWMAIN = "/sys/bus/platform/drivers/mediatek-pinctrl/10005000.pinctrl/mt_gpio";

    /**
     * 高通平台主板上电路径（例如：sd100）
     */
    public static final String POWER_GAOTONG = "/sys/class/switch/app_switch/app_state";

    /**
     * 上电类型
     */
    public enum PowerType {
        /**
         * MTK(6737)平台安卓6.0及以下版本 主板上电路径（例如：kt55、kt50、kt80、kt40、sk100）
         */
        MAIN,
        /**
         * 扩展GPIO 上电路径
         */
        EXPAND,
        /**
         * MTK(6737)平台安卓6.0及以下版本 主板上电路径 和 扩展GPIO 上电路径
         */
        MAIN_AND_EXPAND,
        /**
         * MTK(6763)平台安卓8.1版本  主板上电路径(例如：SD55、SD60)
         */
        NEW_MAIN,
        /**
         * 扩展GPIO 上电路径2 （目前只针对sk80新添加）
         */
        EXPAND2,
        /**
         * MTK(6737)平台安卓6.0及以下版本 主板上电路径 和 扩展GPIO 上电路径2
         */
        MAIN_AND_EXPAND2,
        /**
         * 高通平台主板上电路径（例如：sd100）
         */
        GAOTONG_MAIN

    }

    private BufferedWriter ctrlfile;
    private String poweron = "";
    private String powermode = "";
    private String powerdir = "";
    private String poweroff = "";
    private String currentPath = "";
    private int[] gpios;
    private String[] gtGpios;
    private PowerType power_type;

    public DeviceControlSpd() throws IOException {
    }

    public DeviceControlSpd(String path) throws IOException {
        File DeviceName = new File(path);
        //open file
        ctrlfile = new BufferedWriter(new FileWriter(DeviceName, false));
        currentPath = path;
    }

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
            powermode = "-wmode " + gpio + " 0";
            //将GPIO99设置为输出模式
            powerdir = "-wdir " + gpio + " 1";
            poweron = "-wdout " + gpio + " 1";
            poweroff = "-wdout " + gpio + " 0";
        }
    }


    /**
     * @param powerType 上电类型
     * @param gpios     若为主板上电 gpio[0]需为主板gpio 扩展gpio可以有多个
     * @throws IOException
     */
    public DeviceControlSpd(PowerType powerType, int... gpios) throws IOException {
        this.gpios = gpios;
        this.power_type = powerType;
    }

    public DeviceControlSpd(String powerType, String... gpios) throws IOException {
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
    public DeviceControlSpd(String powerType, int... gpios) throws IOException {
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
     * 高通平台 上电下电  （例如：sd100）
     *
     * @param s 例如： open uhf上电； close uhf下电
     */
    public void gtPower(String s) {
        try {
            File DeviceName = new File(POWER_GAOTONG);
            ctrlfile = new BufferedWriter(new FileWriter(DeviceName, false));
            ctrlfile.write(s);
            ctrlfile.flush();
            ctrlfile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * MTK(6737)平台安卓6.0及以下版本 主板上电（例如：kt55、kt50、kt80、kt40、sk100）
     *
     * @param gpio
     * @throws IOException
     */
    public void MainPowerOn(int gpio) throws IOException {
        DeviceControlSpd deviceControl = new DeviceControlSpd(DeviceControlSpd.POWER_MAIN);
        deviceControl.setGpio(gpio);
        deviceControl.writeON();
        deviceControl.DeviceClose();
    }

    /**
     *  MTK(6737)平台安卓6.0及以下版本 主板下电（例如：kt55、kt50、kt80、kt40、sk100）
     *
     * @param gpio
     * @throws IOException
     */
    public void MainPowerOff(int gpio) throws IOException {
        DeviceControlSpd deviceControl = new DeviceControlSpd(DeviceControlSpd.POWER_MAIN);
        deviceControl.setGpio(gpio);
        deviceControl.WriteOff();
        deviceControl.DeviceClose();
    }

    /**
     * 扩展GPIO上电 （9523）
     *
     * @param gpio
     * @throws IOException
     */
    public void ExpandPowerOn(int gpio) throws IOException {
        DeviceControlSpd deviceControl = new DeviceControlSpd(DeviceControlSpd.POWER_EXTERNAL);
        deviceControl.setGpio(gpio);
        deviceControl.writeON();
        deviceControl.DeviceClose();
    }

    /**
     * 扩展GPIO2 上电（9524）
     *
     * @param gpio
     * @throws IOException
     */
    public void Expand2PowerOn(int gpio) throws IOException {
        DeviceControlSpd deviceControl = new DeviceControlSpd(DeviceControlSpd.POWER_EXTERNAL2);
        deviceControl.setGpio(gpio);
        deviceControl.writeON();
        deviceControl.DeviceClose();
    }

    /**
     * 扩展GPIO 下电（9523）
     *
     * @param gpio
     * @throws IOException
     */
    public void ExpandPowerOff(int gpio) throws IOException {
        DeviceControlSpd deviceControl = new DeviceControlSpd(DeviceControlSpd.POWER_EXTERNAL);
        deviceControl.setGpio(gpio);
        deviceControl.WriteOff();
        deviceControl.DeviceClose();
    }

    /**
     * 扩展GPIO2 下电 （9524）
     *
     * @param gpio
     * @throws IOException
     */
    public void Expand2PowerOff(int gpio) throws IOException {
        DeviceControlSpd deviceControl = new DeviceControlSpd(DeviceControlSpd.POWER_EXTERNAL2);
        deviceControl.setGpio(gpio);
        deviceControl.WriteOff();
        deviceControl.DeviceClose();
    }


    /**
     * 扩展GPIO上电 写ON
     *
     * @throws IOException
     */
    private void writeON() throws IOException {
        ctrlfile.write(powermode);
        ctrlfile.flush();
        ctrlfile.write(powerdir);
        ctrlfile.flush();
        ctrlfile.write(poweron);
        ctrlfile.flush();
    }

    /**
     * 扩展GPIO下电 写off
     *
     * @throws IOException
     */
    private void WriteOff() throws IOException {
        ctrlfile.write(poweroff);
        ctrlfile.flush();
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
        ctrlfile.close();
    }

    /**
     *
     *  MTK(6737)平台安卓6.0及以下版本 设置制定管脚（gpio）为gpio模式 （例如：kt55、kt50、kt80、kt40、sk100）
     * @param num  管脚
     * @param mode 0为gpio模式
     * @throws IOException
     */
    public void setMode(int num, int mode) throws IOException {
        //设置为模式 0为GPIO模式
        ctrlfile.write("-wmode" + num + " " + mode);
        ctrlfile.flush();
    }

    /**
     * 设置输入输出模式
     * MTK(6737)平台安卓6.0及以下版本 主板上电路径（例如：kt55、kt50、kt80、kt40、sk100）
     * @param num  管脚（gpio）
     * @param mode 输入输出模式  0：输入模式  1：输出模式
     * @param path 路径
     * @throws IOException
     */
    public void setDir(int num, int mode, String path) throws IOException {
        File DeviceName = new File(path);
        ctrlfile = new BufferedWriter(new FileWriter(DeviceName, false));
        //设置为输入输出
        ctrlfile.write("-wdir" + num + " " + mode);
        ctrlfile.flush();
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
        ctrlfile = new BufferedWriter(new FileWriter(DeviceName, false));
        // 设置为输入输出
        ctrlfile.write("-wpsel" + num + " " + mode);
        ctrlfile.flush();
        //设置为输入输出
        ctrlfile.write("-wpen" + num + " " + mode);
        ctrlfile.flush();
    }

    //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

    /**
     * MTK(6763)平台安卓8.1版本  上电(例如：SD55、SD60)
     *
     * @param gpio
     * @throws IOException
     */
    public void newSetGpioOn(int gpio) throws IOException {
        DeviceControlSpd deviceControl = new DeviceControlSpd(POWER_NEWMAIN);
        //将GPIO设置为高电平
        deviceControl.ctrlfile.write("out " + gpio + " 1");
        deviceControl.ctrlfile.flush();
        newSetMode(gpio);
        newSetDir(gpio, 1);
    }

    /**
     * MTK(6763)平台安卓8.1版本  下电(例如：SD55、SD60)
     *
     * @param gpio
     * @throws IOException
     */
    public void newSetGpioOff(int gpio) throws IOException {
        DeviceControlSpd deviceControl = new DeviceControlSpd(POWER_NEWMAIN);
        //将GPIO设置为低电平
        deviceControl.ctrlfile.write("out " + gpio + " 0");
        deviceControl.ctrlfile.flush();
    }

    /**
     * MTK(6763)平台安卓8.1版本  设置当前gpio为 gpio模式(例如：SD55、SD60)
     *
     * @param gpio 设置gpio
     * @throws IOException
     */
    public void newSetMode(int gpio) throws IOException {
        DeviceControlSpd deviceControl = new DeviceControlSpd(POWER_NEWMAIN);
        //将GPIO设置为GPIO模式
        deviceControl.ctrlfile.write("mode " + gpio + " 0");
        deviceControl.ctrlfile.flush();
    }

    /**
     * MTK(6763)平台安卓8.1版本 设置当前gpio为输入/输出模式(例如：SD55、SD60)
     *
     * @param gpio 要操作的gpio
     * @param dir  0：输入模式  1：输出模式
     * @throws IOException
     */
    public void newSetDir(int gpio, int dir) throws IOException {
        DeviceControlSpd deviceControl = new DeviceControlSpd(POWER_NEWMAIN);
        deviceControl.ctrlfile.write("dir " + gpio + " " + dir);
        deviceControl.ctrlfile.flush();
    }
}