import java.util.*;

class Laptop {
    private String brand;
    private int ram;
    private int hdd;
    private String os;
    private String color;

    public Laptop(String brand, int ram, int hdd, String os, String color) {
        this.brand = brand;
        this.ram = ram;
        this.hdd = hdd;
        this.os = os;
        this.color = color;
    }

    public String getBrand() {
        return brand;
    }

    public int getRam() {
        return ram;
    }

    public int getHdd() {
        return hdd;
    }

    public String getOs() {
        return os;
    }

    public String getColor() {
        return color;
    }

    @Override
    public String toString() {
        return "Laptop{" +
                "brand='" + brand + '\'' +
                ", ram=" + ram +
                ", hdd=" + hdd +
                ", os='" + os + '\'' +
                ", color='" + color + '\'' +
                '}';
    }
}

public class Main {
    public static void main(String[] args) {
        Set<Laptop> laptops = new HashSet<>();
        laptops.add(new Laptop("Dell", 8, 500, "Windows 10", "Black"));
        laptops.add(new Laptop("HP", 16, 1000, "Windows 10", "Silver"));
        laptops.add(new Laptop("Apple", 8, 256, "macOS", "Gray"));
        laptops.add(new Laptop("Lenovo", 4, 500, "Windows 10", "Black"));

        Scanner scanner = new Scanner(System.in);

        Map<String, Object> criteria = new HashMap<>();
        while (true) {
            System.out.println("Введите цифру, соответствующую необходимому критерию:");
            System.out.println("1 - ОЗУ");
            System.out.println("2 - Объем ЖД");
            System.out.println("3 - Операционная система");
            System.out.println("4 - Цвет");
            System.out.println("0 - Поиск");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            if (choice == 0) break;

            switch (choice) {
                case 1:
                    System.out.println("Введите минимальный объем ОЗУ (в ГБ):");
                    int minRam = scanner.nextInt();
                    criteria.put("ram", minRam);
                    break;
                case 2:
                    System.out.println("Введите минимальный объем ЖД (в ГБ):");
                    int minHdd = scanner.nextInt();
                    criteria.put("hdd", minHdd);
                    break;
                case 3:
                    System.out.println("Введите операционную систему:");
                    String os = scanner.nextLine();
                    criteria.put("os", os);
                    break;
                case 4:
                    System.out.println("Введите цвет:");
                    String color = scanner.nextLine();
                    criteria.put("color", color);
                    break;
                default:
                    System.out.println("Неверный выбор, попробуйте снова.");
                    break;
            }
        }

        Set<Laptop> filteredLaptops = filterLaptops(laptops, criteria);
        System.out.println("Подходящие ноутбуки:");
        for (Laptop laptop : filteredLaptops) {
            System.out.println(laptop);
        }
    }

    private static Set<Laptop> filterLaptops(Set<Laptop> laptops, Map<String, Object> criteria) {
        Set<Laptop> filteredLaptops = new HashSet<>(laptops);

        for (Map.Entry<String, Object> entry : criteria.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();

            switch (key) {
                case "ram":
                    filteredLaptops.removeIf(laptop -> laptop.getRam() < (int) value);
                    break;
                case "hdd":
                    filteredLaptops.removeIf(laptop -> laptop.getHdd() < (int) value);
                    break;
                case "os":
                    filteredLaptops.removeIf(laptop -> !laptop.getOs().equalsIgnoreCase((String) value));
                    break;
                case "color":
                    filteredLaptops.removeIf(laptop -> !laptop.getColor().equalsIgnoreCase((String) value));
                    break;
            }
        }
        return filteredLaptops;
    }
}
