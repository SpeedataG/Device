package com.speedata.libutils;

import com.speedata.bean.BaseBean;

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
 * 联系方式:QQ:282921012
 * 功能描述:读取配置文件
 */
public class ReadBean {


    /**
     * serialPort : /dev/ttyMT1
     * braut : 115200
     * powerType : MAIN
     * gpio : [63]
     */

    private Id2Bean id2;
    /**
     * serialPort : /dev/ttyMT2
     * braut : 115200
     * powerType : MAIN
     * gpio : [64]
     * module : r2k
     */

    private UhfBean uhf;
    /**
     * serialPort : /dev/rc663
     * braut : 0
     * powerType :
     * gpio : [-1]
     */

    private R6Bean r6;
    /**
     * serialPort : /dev/ttyMT1
     * braut : 115200
     * powerType : MAIN
     * gpio : [64]
     */

    private PrintBean print;
    /**
     * serialPort : /dev/ttyMT3
     * braut : 115200
     * powerType : MAIN
     * gpio : [14]
     * resetGpio : 44
     */

    private PasmBean pasm;
    /**
     * serialPort : ttyMT2
     * braut : 115200
     * powerType : MAIN
     * gpio : [64]
     */

    private FingerBean finger;
    /**
     * serialPort : /dev/ttyMT2
     * braut : 115200
     * powerType : MAIN
     * gpio : [94]
     */

    private DistBean dist;
    /**
     * serialPort : /dev/ttyMT2
     * braut : 115200
     * powerType : MAIN
     * gpio : [94]
     */

    private TempBean temp;
    /**
     * serialPort : /dev/ttyMT2
     * braut : 115200
     * powerType : MAIN
     * gpio : [64]
     */

    private Lf1Bean lf1;
    /**
     * serialPort : /dev/ttyMT2
     * braut : 115200
     * powerType : MAIN
     * gpio : [64]
     */

    private Lf2Bean lf2;
    /**
     * serialPort : ttyMT2
     * braut : 115200
     * powerType : MAIN
     * gpio : [64]
     */

    private Sp433Bean sp433;
    /**
     * serialPort : /dev/ttyMT0
     * braut : 115200
     * powerType : MAIN
     * gpio : [106]
     */

    private ScanBean scan;
    /**
     * serialPort : /dev/ttyMT2
     * braut : 115200
     * powerType : MAIN
     * gpio : [64]
     */

    private ZigbeeBean zigbee;
    /**
     * serialPort : /dev/ttyMT0
     * braut : 115200
     * powerType : MAIN
     * gpio : [106]
     */

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

    public static class Id2Bean  extends BaseBean {

    }

    public static class UhfBean  extends BaseBean {

        private String module;

        public String getModule() {
            return module;
        }

        public void setModule(String module) {
            this.module = module;
        }
    }

    public static class R6Bean  extends BaseBean {

    }

    public static class PrintBean  extends BaseBean {

    }

    public static class PasmBean extends BaseBean {

        private int resetGpio;
        public int getResetGpio() {
            return resetGpio;
        }
        public void setResetGpio(int resetGpio) {
            this.resetGpio = resetGpio;
        }

    }

    public static class FingerBean extends BaseBean {

    }

    public static class DistBean extends BaseBean {

    }

    public static class TempBean extends BaseBean {

    }

    public static class Lf1Bean extends BaseBean {

    }

    public static class Lf2Bean extends BaseBean {

    }

    public static class Sp433Bean extends BaseBean {

    }

    public static class ScanBean extends BaseBean {

    }

    public static class ZigbeeBean extends BaseBean {

    }

    public static class InfraredBean extends BaseBean {

    }

}
