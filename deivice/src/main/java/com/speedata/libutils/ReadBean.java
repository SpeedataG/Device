package com.speedata.libutils;

import java.util.List;

/**
 * ----------Dragon be here!----------/
 * 　　　┏┓　　　┏┓
 * 　　┏┛┻━━━┛┻┓
 * 　　┃　　　　　　　┃
 * 　　┃　　　━　　　┃
 * 　　┃　┳┛　┗┳　┃
 * 　　┃　　　　　　　┃
 * 　　┃　　　┻　　　┃
 * 　　┃　　　　　　　┃
 * 　　┗━┓　　　┏━┛
 * 　　　　┃　　　┃神兽保佑
 * 　　　　┃　　　┃代码无BUG！
 * 　　　　┃　　　┗━━━┓
 * 　　　　┃　　　　　　　┣┓
 * 　　　　┃　　　　　　　┏┛
 * 　　　　┗┓┓┏━┳┓┏┛
 * 　　　　　┃┫┫　┃┫┫
 * 　　　　　┗┻┛　┗┻┛
 * ━━━━━━神兽出没━━━━━━
 *
 * @author :Reginer in  2017/3/10 9:57.
 *         联系方式:QQ:282921012
 *         功能描述:读取配置文件
 */
public class ReadBean {


    /**
     * id2 : {"serialPort":"/dev/ttyMT1","braut":115200,"powerType":"MAIN","gpio":[88,7]}
     * uhf : {"serialPort":"/dev/ttyMT2","braut":115200,"powerType":"MAIN","gpio":[94]}
     * r6 : {"serialPort":"/dev/rc663","braut":0,"powerType":"","gpio":[0]}
     * print : {"serialPort":"/dev/ttyMT2","braut":115200,"powerType":"MAIN","gpio":[94]}
     * pasm : {"serialPort":"/dev/ttyMT2","braut":115200,"powerType":"MAIN","gpio":[94]}
     * finger : {"serialPort":"/dev/ttyMT2","braut":115200,"powerType":"MAIN","gpio":[94]}
     * dist : {"serialPort":"/dev/ttyMT2","braut":115200,"powerType":"MAIN","gpio":[94]}
     * temp : {"serialPort":"/dev/ttyMT2","braut":115200,"powerType":"MAIN","gpio":[94]}
     * lf1 : {"serialPort":"","braut":115200,"powerType":"MAIN","gpio":[0]}
     * lf2 : {"serialPort":"","braut":115200,"powerType":"MAIN","gpio":[0]}
     * sp433 : {"serialPort":"/dev/ttyMT2","braut":115200,"powerType":"MAIN","gpio":[94]}
     * scan : {"serialPort":"/dev/ttyMT2","braut":115200,"powerType":"MAIN","gpio":[94]}
     * ur2k : {"serialPort":"/dev/ttyMT2","braut":115200,"powerType":"MAIN","gpio":[7]}
     * zigbee : {"serialPort":"","braut":115200,"powerType":"MAIN","gpio":[0]}
     * infrared : {"serialPort":"/dev/ttyMT0","braut":115200,"powerType":"MAIN","gpio":[106]}
     */

    private Id2Bean id2;
    private UhfBean uhf;
    private R6Bean r6;
    private PrintBean print;
    private PasmBean pasm;
    private FingerBean finger;
    private DistBean dist;
    private TempBean temp;
    private Lf1Bean lf1;
    private Lf2Bean lf2;
    private Sp433Bean sp433;
    private ScanBean scan;
    private Ur2kBean ur2k;
    private ZigbeeBean zigbee;
    private InfraredBean infrared;

    public Id2Bean getId2() {
        return id2;
    }

    public void setId2(Id2Bean id2) {
        this.id2 = id2;
    }

    public UhfBean getUhf() {
        return uhf;
    }

    public void setUhf(UhfBean uhf) {
        this.uhf = uhf;
    }

    public R6Bean getR6() {
        return r6;
    }

    public void setR6(R6Bean r6) {
        this.r6 = r6;
    }

    public PrintBean getPrint() {
        return print;
    }

    public void setPrint(PrintBean print) {
        this.print = print;
    }

    public PasmBean getPasm() {
        return pasm;
    }

    public void setPasm(PasmBean pasm) {
        this.pasm = pasm;
    }

    public FingerBean getFinger() {
        return finger;
    }

    public void setFinger(FingerBean finger) {
        this.finger = finger;
    }

    public DistBean getDist() {
        return dist;
    }

    public void setDist(DistBean dist) {
        this.dist = dist;
    }

    public TempBean getTemp() {
        return temp;
    }

    public void setTemp(TempBean temp) {
        this.temp = temp;
    }

    public Lf1Bean getLf1() {
        return lf1;
    }

    public void setLf1(Lf1Bean lf1) {
        this.lf1 = lf1;
    }

    public Lf2Bean getLf2() {
        return lf2;
    }

    public void setLf2(Lf2Bean lf2) {
        this.lf2 = lf2;
    }

    public Sp433Bean getSp433() {
        return sp433;
    }

    public void setSp433(Sp433Bean sp433) {
        this.sp433 = sp433;
    }

    public ScanBean getScan() {
        return scan;
    }

    public void setScan(ScanBean scan) {
        this.scan = scan;
    }

    public Ur2kBean getUr2k() {
        return ur2k;
    }

    public void setUr2k(Ur2kBean ur2k) {
        this.ur2k = ur2k;
    }

    public ZigbeeBean getZigbee() {
        return zigbee;
    }

    public void setZigbee(ZigbeeBean zigbee) {
        this.zigbee = zigbee;
    }

    public InfraredBean getInfrared() {
        return infrared;
    }

    public void setInfrared(InfraredBean infrared) {
        this.infrared = infrared;
    }

    public static class Id2Bean {
        /**
         * serialPort : /dev/ttyMT1
         * braut : 115200
         * powerType : MAIN
         * gpio : [88,7]
         */

        private String serialPort;
        private int braut;
        private String powerType;
        private List<String> gpio;

        public String getSerialPort() {
            return serialPort;
        }

        public void setSerialPort(String serialPort) {
            this.serialPort = serialPort;
        }

        public int getBraut() {
            return braut;
        }

        public void setBraut(int braut) {
            this.braut = braut;
        }

        public String getPowerType() {
            return powerType;
        }

        public void setPowerType(String powerType) {
            this.powerType = powerType;
        }

        public List<String> getGpio() {
            return gpio;
        }

        public void setGpio(List<String> gpio) {
            this.gpio = gpio;
        }
    }

    public static class UhfBean {
        /**
         * serialPort : /dev/ttyMT2
         * braut : 115200
         * powerType : MAIN
         * gpio : [94]
         */

        private String serialPort;
        private int braut;
        private String powerType;
        private List<String> gpio;

        public String getSerialPort() {
            return serialPort;
        }

        public void setSerialPort(String serialPort) {
            this.serialPort = serialPort;
        }

        public int getBraut() {
            return braut;
        }

        public void setBraut(int braut) {
            this.braut = braut;
        }

        public String getPowerType() {
            return powerType;
        }

        public void setPowerType(String powerType) {
            this.powerType = powerType;
        }

        public List<String> getGpio() {
            return gpio;
        }

        public void setGpio(List<String> gpio) {
            this.gpio = gpio;
        }
    }

    public static class R6Bean {
        /**
         * serialPort : /dev/rc663
         * braut : 0
         * powerType :
         * gpio : [0]
         */

        private String serialPort;
        private int braut;
        private String powerType;
        private List<String> gpio;

        public String getSerialPort() {
            return serialPort;
        }

        public void setSerialPort(String serialPort) {
            this.serialPort = serialPort;
        }

        public int getBraut() {
            return braut;
        }

        public void setBraut(int braut) {
            this.braut = braut;
        }

        public String getPowerType() {
            return powerType;
        }

        public void setPowerType(String powerType) {
            this.powerType = powerType;
        }

        public List<String> getGpio() {
            return gpio;
        }

        public void setGpio(List<String> gpio) {
            this.gpio = gpio;
        }
    }

    public static class PrintBean {
        /**
         * serialPort : /dev/ttyMT2
         * braut : 115200
         * powerType : MAIN
         * gpio : [94]
         */

        private String serialPort;
        private int braut;
        private String powerType;
        private List<String> gpio;

        public String getSerialPort() {
            return serialPort;
        }

        public void setSerialPort(String serialPort) {
            this.serialPort = serialPort;
        }

        public int getBraut() {
            return braut;
        }

        public void setBraut(int braut) {
            this.braut = braut;
        }

        public String getPowerType() {
            return powerType;
        }

        public void setPowerType(String powerType) {
            this.powerType = powerType;
        }

        public List<String> getGpio() {
            return gpio;
        }

        public void setGpio(List<String> gpio) {
            this.gpio = gpio;
        }
    }

    public static class PasmBean {
        /**
         * serialPort : /dev/ttyMT2
         * braut : 115200
         * powerType : MAIN
         * gpio : [94]
         */

        private String serialPort;
        private int braut;
        private String powerType;
        private List<String> gpio;

        public String getSerialPort() {
            return serialPort;
        }

        public void setSerialPort(String serialPort) {
            this.serialPort = serialPort;
        }

        public int getBraut() {
            return braut;
        }

        public void setBraut(int braut) {
            this.braut = braut;
        }

        public String getPowerType() {
            return powerType;
        }

        public void setPowerType(String powerType) {
            this.powerType = powerType;
        }

        public List<String> getGpio() {
            return gpio;
        }

        public void setGpio(List<String> gpio) {
            this.gpio = gpio;
        }
    }

    public static class FingerBean {
        /**
         * serialPort : /dev/ttyMT2
         * braut : 115200
         * powerType : MAIN
         * gpio : [94]
         */

        private String serialPort;
        private int braut;
        private String powerType;
        private List<String> gpio;

        public String getSerialPort() {
            return serialPort;
        }

        public void setSerialPort(String serialPort) {
            this.serialPort = serialPort;
        }

        public int getBraut() {
            return braut;
        }

        public void setBraut(int braut) {
            this.braut = braut;
        }

        public String getPowerType() {
            return powerType;
        }

        public void setPowerType(String powerType) {
            this.powerType = powerType;
        }

        public List<String> getGpio() {
            return gpio;
        }

        public void setGpio(List<String> gpio) {
            this.gpio = gpio;
        }
    }

    public static class DistBean {
        /**
         * serialPort : /dev/ttyMT2
         * braut : 115200
         * powerType : MAIN
         * gpio : [94]
         */

        private String serialPort;
        private int braut;
        private String powerType;
        private List<String> gpio;

        public String getSerialPort() {
            return serialPort;
        }

        public void setSerialPort(String serialPort) {
            this.serialPort = serialPort;
        }

        public int getBraut() {
            return braut;
        }

        public void setBraut(int braut) {
            this.braut = braut;
        }

        public String getPowerType() {
            return powerType;
        }

        public void setPowerType(String powerType) {
            this.powerType = powerType;
        }

        public List<String> getGpio() {
            return gpio;
        }

        public void setGpio(List<String> gpio) {
            this.gpio = gpio;
        }
    }

    public static class TempBean {
        /**
         * serialPort : /dev/ttyMT2
         * braut : 115200
         * powerType : MAIN
         * gpio : [94]
         */

        private String serialPort;
        private int braut;
        private String powerType;
        private List<String> gpio;

        public String getSerialPort() {
            return serialPort;
        }

        public void setSerialPort(String serialPort) {
            this.serialPort = serialPort;
        }

        public int getBraut() {
            return braut;
        }

        public void setBraut(int braut) {
            this.braut = braut;
        }

        public String getPowerType() {
            return powerType;
        }

        public void setPowerType(String powerType) {
            this.powerType = powerType;
        }

        public List<String> getGpio() {
            return gpio;
        }

        public void setGpio(List<String> gpio) {
            this.gpio = gpio;
        }
    }

    public static class Lf1Bean {
        /**
         * serialPort :
         * braut : 115200
         * powerType : MAIN
         * gpio : [0]
         */

        private String serialPort;
        private int braut;
        private String powerType;
        private List<String> gpio;

        public String getSerialPort() {
            return serialPort;
        }

        public void setSerialPort(String serialPort) {
            this.serialPort = serialPort;
        }

        public int getBraut() {
            return braut;
        }

        public void setBraut(int braut) {
            this.braut = braut;
        }

        public String getPowerType() {
            return powerType;
        }

        public void setPowerType(String powerType) {
            this.powerType = powerType;
        }

        public List<String> getGpio() {
            return gpio;
        }

        public void setGpio(List<String> gpio) {
            this.gpio = gpio;
        }
    }

    public static class Lf2Bean {
        /**
         * serialPort :
         * braut : 115200
         * powerType : MAIN
         * gpio : [0]
         */

        private String serialPort;
        private int braut;
        private String powerType;
        private List<String> gpio;

        public String getSerialPort() {
            return serialPort;
        }

        public void setSerialPort(String serialPort) {
            this.serialPort = serialPort;
        }

        public int getBraut() {
            return braut;
        }

        public void setBraut(int braut) {
            this.braut = braut;
        }

        public String getPowerType() {
            return powerType;
        }

        public void setPowerType(String powerType) {
            this.powerType = powerType;
        }

        public List<String> getGpio() {
            return gpio;
        }

        public void setGpio(List<String> gpio) {
            this.gpio = gpio;
        }
    }

    public static class Sp433Bean {
        /**
         * serialPort : /dev/ttyMT2
         * braut : 115200
         * powerType : MAIN
         * gpio : [94]
         */

        private String serialPort;
        private int braut;
        private String powerType;
        private List<String> gpio;

        public String getSerialPort() {
            return serialPort;
        }

        public void setSerialPort(String serialPort) {
            this.serialPort = serialPort;
        }

        public int getBraut() {
            return braut;
        }

        public void setBraut(int braut) {
            this.braut = braut;
        }

        public String getPowerType() {
            return powerType;
        }

        public void setPowerType(String powerType) {
            this.powerType = powerType;
        }

        public List<String> getGpio() {
            return gpio;
        }

        public void setGpio(List<String> gpio) {
            this.gpio = gpio;
        }
    }

    public static class ScanBean {
        /**
         * serialPort : /dev/ttyMT2
         * braut : 115200
         * powerType : MAIN
         * gpio : [94]
         */

        private String serialPort;
        private int braut;
        private String powerType;
        private List<String> gpio;

        public String getSerialPort() {
            return serialPort;
        }

        public void setSerialPort(String serialPort) {
            this.serialPort = serialPort;
        }

        public int getBraut() {
            return braut;
        }

        public void setBraut(int braut) {
            this.braut = braut;
        }

        public String getPowerType() {
            return powerType;
        }

        public void setPowerType(String powerType) {
            this.powerType = powerType;
        }

        public List<String> getGpio() {
            return gpio;
        }

        public void setGpio(List<String> gpio) {
            this.gpio = gpio;
        }
    }

    public static class Ur2kBean {
        /**
         * serialPort : /dev/ttyMT2
         * braut : 115200
         * powerType : MAIN
         * gpio : [7]
         */

        private String serialPort;
        private int braut;
        private String powerType;
        private List<String> gpio;

        public String getSerialPort() {
            return serialPort;
        }

        public void setSerialPort(String serialPort) {
            this.serialPort = serialPort;
        }

        public int getBraut() {
            return braut;
        }

        public void setBraut(int braut) {
            this.braut = braut;
        }

        public String getPowerType() {
            return powerType;
        }

        public void setPowerType(String powerType) {
            this.powerType = powerType;
        }

        public List<String> getGpio() {
            return gpio;
        }

        public void setGpio(List<String> gpio) {
            this.gpio = gpio;
        }
    }

    public static class ZigbeeBean {
        /**
         * serialPort :
         * braut : 115200
         * powerType : MAIN
         * gpio : [0]
         */

        private String serialPort;
        private int braut;
        private String powerType;
        private List<String> gpio;

        public String getSerialPort() {
            return serialPort;
        }

        public void setSerialPort(String serialPort) {
            this.serialPort = serialPort;
        }

        public int getBraut() {
            return braut;
        }

        public void setBraut(int braut) {
            this.braut = braut;
        }

        public String getPowerType() {
            return powerType;
        }

        public void setPowerType(String powerType) {
            this.powerType = powerType;
        }

        public List<String> getGpio() {
            return gpio;
        }

        public void setGpio(List<String> gpio) {
            this.gpio = gpio;
        }
    }

    public static class InfraredBean {
        /**
         * serialPort : /dev/ttyMT0
         * braut : 115200
         * powerType : MAIN
         * gpio : [106]
         */

        private String serialPort;
        private int braut;
        private String powerType;
        private List<String> gpio;

        public String getSerialPort() {
            return serialPort;
        }

        public void setSerialPort(String serialPort) {
            this.serialPort = serialPort;
        }

        public int getBraut() {
            return braut;
        }

        public void setBraut(int braut) {
            this.braut = braut;
        }

        public String getPowerType() {
            return powerType;
        }

        public void setPowerType(String powerType) {
            this.powerType = powerType;
        }

        public List<String> getGpio() {
            return gpio;
        }

        public void setGpio(List<String> gpio) {
            this.gpio = gpio;
        }
    }
}
