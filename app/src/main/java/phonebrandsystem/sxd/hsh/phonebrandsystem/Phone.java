package phonebrandsystem.sxd.hsh.phonebrandsystem;

import java.io.Serializable;

public class Phone implements Serializable {
    String name = "";
    String cpu="";
    String battery="";

    public Phone() {
    }
    public Phone(String name, String cpu, String battery) {
        this.name = name;
        this.cpu = cpu;
        this.battery = battery;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpu() {
        return cpu;
    }

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    public String getBattery() {
        return battery;
    }

    public void setBattery(String battery) {
        this.battery = battery;
    }
}
