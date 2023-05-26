/*Подумать над структурой класса Ноутбук для магазина техники - выделить поля и методы. Реализовать в java.
Создать множество ноутбуков.
Написать метод, который будет запрашивать у пользователя критерий (или критерии) фильтрации и выведет ноутбуки, отвечающие фильтру. Критерии фильтрации можно хранить в Map. Например:
“Введите цифру, соответствующую необходимому критерию:
1 - ОЗУ
2 - Объем ЖД
3 - Операционная система
4 - Цвет …
Далее нужно запросить минимальные значения для указанных критериев - сохранить параметры фильтрации можно также в Map.
Отфильтровать ноутбуки их первоначального множества и вывести проходящие по условиям.*/

import java.io.IOException;
import java.util.*;

public class Task_1 {
    /**
     * @param args
     * @throws IOException
     * @throws ClassNotFoundException
     * @throws CloneNotSupportedException
     */

    public static void main(String[] args) throws IOException, ClassNotFoundException, CloneNotSupportedException {
        Set<Lap> laptops = new HashSet<>();

        String[] colors = {"black", "white", "grey", "red"};
        int[] rams = {8, 16, 32, 64};
        String[] cpus = {"Intel Core i3", "Intel Core i5", "Intel Core i7", "Intel Core i9"};
        int[] hdds = {64, 128, 512, 1024, 2048};
        String[] oss = {"Linux", "Windos", "Unix", "None"};
        String[] manufacturers = {"Lenovo", "Asus", "Acer", "Del"};
        for(String manufacturer: manufacturers) {
            for(String color: colors) {
                for(int ram: rams) {
                    for(String cpu: cpus) {
                        for(int hdd: hdds) {
                            for (String os: oss) {
                                laptops.add(new Lap(manufacturer, color, ram, hdd, os, cpu));
                            }
                        }
                    }
                }
            }
        }


        Scanner scanner = new Scanner(System.in);
        while(true) {
            Set<Lap> results = findByManufacturers(laptops, scanner);
            results = findByColors(results, scanner);
            results = findByOs(results, scanner);
            results = findByCpu(results, scanner);
            results = findByRam(results, scanner);
            results = findByHdd(results, scanner);
            for(Lap laptop: results) {
                System.out.println(laptop);
            }
            System.out.println("Желаете продолжить: y | n");
            String answer = scanner.nextLine();
            if (answer.equals("n")) {
                break;
            }
        }  
        scanner.close();
    }

    public static Set<Lap> findByManufacturers(Set<Lap> laptops, Scanner scanner) {
        System.out.println("Введите производителя через запятую");
        Set<String> set = new HashSet<>();
        for(Lap laptop: laptops) {
            set.add(laptop.manufacturer);
        }
        System.out.println("Доступные: " + String.join(", ", set));
        String line  = scanner.nextLine();
        if (!line.equals("")) {
            String[] manufacturers = line.split(",");
            Set<Lap> results = new HashSet<>();
            for(Lap laptop: laptops) {
                if (laptop.equalsManufacturer(manufacturers)){
                    results.add(laptop);
                }
            }
            return results;
        }
        return laptops;
    }

    public static Set<Lap> findByColors(Set<Lap> laptops, Scanner scanner) {
        System.out.println("Введите цвета через запятую");
        Set<String> set = new HashSet<>();
        for(Lap laptop: laptops) {
            set.add(laptop.color);
        }
        System.out.println("Доступные: " + String.join(", ", set));
        String line  = scanner.nextLine();
        if (!line.equals("")) {
            String[] colors = line.split(",");
            Set<Lap> results = new HashSet<>();
            for(Lap laptop: laptops) {
                if (laptop.equalsColor(colors)){
                    results.add(laptop);
                }
            }
            return results;
        }
        return laptops;
    }

    public static Set<Lap> findByOs(Set<Lap> laptops, Scanner scanner) {
        System.out.println("Введите Os через запятую");
        Set<String> set = new HashSet<>();
        for(Lap laptop: laptops) {
            set.add(laptop.oSystem);
        }
        System.out.println("Доступные: " + String.join(", ", set));
        String line  = scanner.nextLine();
        if (!line.equals("")) {
            String[] oSystems = line.split(",");
            Set<Lap> results = new HashSet<>();
            for(Lap laptop: laptops) {
                if (laptop.equalsOs(oSystems)){
                    results.add(laptop);
                }
            }
            return results;
        } 
        return laptops;
    }
    public static Set<Lap> findByCpu(Set<Lap> laptops, Scanner scanner) {
        System.out.println("Введите cpu через запятую");
        Set<String> set = new HashSet<>();
        for(Lap laptop: laptops) {
            set.add(laptop.cpu);
        }
        System.out.println("Доступные: " + String.join(", ", set));
        String line  = scanner.nextLine();
        if (!line.equals("")) {
            String[] cpus = line.split(",");
            Set<Lap> results = new HashSet<>();
            for(Lap laptop: laptops) {
                if (laptop.equalsCpu(cpus)){
                    results.add(laptop);
                }
            }
            return results;
        } 
        return laptops;
    }

    public static Set<Lap> findByRam(Set<Lap> laptops, Scanner scanner) {
        System.out.println("Введите ram минимальный, максимальные");
        Set<Integer> set = new TreeSet<>();
        for(Lap laptop: laptops) {
            set.add(laptop.ram);
        }

        System.out.print("Доступные:");
        for(Integer ram: set) {
            System.out.print(" " + ram);
        }
        System.out.println("");
        String line  = scanner.nextLine();
        if (!line.equals("")) {
            String[] ram = line.split(",");
            Integer minRam = Integer.parseInt(ram[0]);
            Integer maxRam = 0;
            if (ram.length == 2) {
                maxRam = Integer.parseInt(ram[1]);
            }
            Set<Lap> results = new HashSet<>();

            for(Lap laptop: laptops) {
                if (laptop.equalsRam(minRam, maxRam)){
                    results.add(laptop);
                }
            }
            return results;
        } 
        return laptops;
    }

    public static Set<Lap> findByHdd(Set<Lap> laptops, Scanner scanner) {
        System.out.println("Введите hdd минимальный, максимальные");
        Set<Integer> set = new TreeSet<>();
        for(Lap laptop: laptops) {
            set.add(laptop.hdd);
        }

        System.out.print("Доступные:");
        for(Integer hdd: set) {
            System.out.print(" " + hdd);
        }
        System.out.println("");
        String line  = scanner.nextLine();
        if (!line.equals("")) {
            String[] hdd = line.split(",");
            Integer minHdd = Integer.parseInt(hdd[0]);
            Integer maxHdd = 0;
            if (hdd.length == 2) {
                maxHdd = Integer.parseInt(hdd[1]);
            }
            Set<Lap> results = new HashSet<>();

            for(Lap laptop: laptops) {
                if (laptop.equalsHdd(minHdd, maxHdd)){
                    results.add(laptop);
                }
            }
            return results;
        } 
        return laptops;
    }
}

