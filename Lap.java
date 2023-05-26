public class Lap {
    String manufacturer;
    String color;
    int ram;
    int hdd;
    String oSystem;    
    String cpu;


    public Lap(String manufacturer, String color, int ram, int hdd, String oSystem, String cpu){
        this.manufacturer = manufacturer;
        this.color = color;
        this.ram = ram;
        this.hdd = hdd;
        this.oSystem = oSystem;    
        this.cpu = cpu;
    }

    @Override
    public String toString(){
        return  manufacturer + ": " + " цвет: " + color + " ОС: " + oSystem + " " + ram + "Gb, " + hdd + "Gb, " + cpu;
    }

    public boolean equalsManufacturer(String[] manufacturers) {
        for(String value: manufacturers) {
            if(value.toLowerCase().equals(manufacturer.toLowerCase())) {
                return true;
            }
        }
        return false;
    }

    public boolean equalsColor(String[] colors) {
        for(String value: colors) {
            if(value.toLowerCase().equals(color.toLowerCase())) {
                return true;
            }
        }
        return false;
    }

    public boolean equalsOs(String[] oSystems){
        for(String value: oSystems) {
            if(value.toLowerCase().equals(oSystem.toLowerCase())) {
                return true;
            }
        }
        return false;
    }

    public boolean equalsCpu(String[] cpus){
        for(String value: cpus) {
            if(value.toLowerCase().equals(cpu.toLowerCase())) {
                return true;
            }
        }
        return false;
    }

    public boolean equalsRam(Integer minRam, Integer maxRam){
        if (maxRam == 0) {
            return (minRam <= ram);
        } else {
            return (minRam <= ram && maxRam >= ram);
        } 
    }

    public boolean equalsHdd(Integer minHdd, Integer maxHdd){
        if (maxHdd == 0) {
            return (minHdd <= hdd);
        } else {
            return (minHdd <= hdd && maxHdd >= hdd);
        }
    }

    public int hashCode(){
        return manufacturer.hashCode() ^ color.hashCode() ^ ram ^ hdd ^ oSystem.hashCode() ^ cpu.hashCode();
    }

    public boolean equals(Object obj){
        Lap laptop = (Lap) obj;
        return manufacturer.equals(laptop.manufacturer)
               && color.equals(laptop.color)
               && ram == laptop.ram
               && hdd == laptop.hdd
               && oSystem.equals(laptop.oSystem)
               && cpu.equals(laptop.cpu);
    }
}
