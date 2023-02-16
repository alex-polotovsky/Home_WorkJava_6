/* 1. Подумать над структурой класса Ноутбук для магазина техники - выделить поля и методы. Реализовать в java.
   2. Создать множество ноутбуков.
   3. Написать метод, который будет запрашивать у пользователя критерий (или критерии) фильтрации
      и выведет ноутбуки, отвечающие фильтру. Критерии фильтрации можно хранить в Map.
      Например: “Введите цифру, соответствующую необходимому критерию:
        1 - ОЗУ
        2 - Объем ЖД
        3 - Операционная система
        4 - Цвет …
   4. Далее нужно запросить минимальные значения для указанных критериев -
      сохранить параметры фильтрации можно также в Map.
   5. Отфильтровать ноутбуки их первоначального множества и вывести проходящие по условиям.*/

import java.util.*;
import java.util.stream.Collectors;

public class choosingLaptop {
    public static void main(String[] args) {
        Laptop lt1 = new Laptop("Lenovo", "Windows", 13.3, 8, 128, 123456);
        Laptop lt2 = new Laptop("Lenovo", "Linux", 15.6, 8, 128, 123458);
        Laptop lt3 = new Laptop("ASUS", "Windows", 15.6, 16, 512, 123457);
        Laptop lt4 = new Laptop("Acer", "Windows", 13.3, 4, 256, 123459);
        Laptop lt5 = new Laptop("HP", "Windows", 17.0, 32, 1000, 123446);
        Laptop lt6 = new Laptop("Acer", "Linux", 14, 4, 256, 123449);

        Set<Laptop> laptops = new HashSet<>(List.of(lt1, lt2, lt3, lt4, lt5, lt6));
        getLaptops(laptops);

        Map<String, String> filteredParameters = getFilterParameters(laptops);
        viewFilteredParameters(filteredParameters);

        Set<Laptop> filteredLaptops = getFilteredLaptops(laptops, filteredParameters);
        if (filteredLaptops.size() == 0) {
            System.out.println("Ничего не нашлось.");
        } else {
            getLaptops(filteredLaptops);
        }
    }


    private static void getLaptops(Set<Laptop> laptops) {
        if (laptops.size() == 0) {
            System.out.println("Пусто.");
            return;
        }
        System.out.println("Ноутбуки: ");
        for (Laptop laptop :
                laptops) {
            System.out.printf("Производитель %s, ОС %s, Экран %.1f, Память %.0f, HDD %.1f \n",
                    laptop.getBrand(), laptop.getOperatingSystem(), laptop.getScreenSize(),
                    laptop.getRamMemory(), laptop.getHardDiskSize());
        }
        System.out.println();
    }

    private static Map<String, String> getFilterParameters(Set<Laptop> laptops) {
        Map<String, String> parameters = new HashMap<>();
        parameters.put("brand", "Производитель");
        parameters.put("operatingSystem", "Операционная система");
        parameters.put("screenSize", "Размер экрана");
        parameters.put("ramMemory", "Оперативная память");
        parameters.put("hardDiskSize", "Жёсткий диск");

        Set<String> brands = laptops.stream().map(Laptop::getBrand).collect(Collectors.toSet());
        Set<String> operatingSystems = laptops.stream().map(Laptop::getOperatingSystem).collect(Collectors.toSet());
        Map<String, String> filteredParameters = new HashMap<>();

        System.out.println("Выберите параметры фильтрации через 'y' или 'n'.");
        Scanner sc = new Scanner(System.in);
        for (var parameter :
                parameters.entrySet()) {
            System.out.print(parameter.getValue() + ": ");
            String key = sc.nextLine();
            if (key.equals("n")) {
                continue;
            } else if (key.equals("y")) {
                if (parameter.getKey().equals("brand")) {
                    System.out.println("Выберите из " + brands);
                    String brand = sc.nextLine();
                    filteredParameters.put(parameter.getKey(), brand);
                } else if (parameter.getKey().equals("operatingSystem")) {
                    System.out.println("Выберите из " + operatingSystems);
                    String operatingSystem = sc.nextLine();
                    filteredParameters.put(parameter.getKey(), operatingSystem);
                } else {
                    System.out.print("Введите минимальное значение: ");
                    String num = sc.nextLine();
                    filteredParameters.put(parameter.getKey(), num);
                }
            }
        }
        return filteredParameters;
    }

    private static void viewFilteredParameters(Map<String, String> fps) {
        System.out.println("Вы выбрали:");
        for (var fp :
                fps.entrySet()) {
            System.out.printf("%s %s\n", fp.getKey(), fp.getValue());
        }
        System.out.println();
    }

    private static Set<Laptop> getFilteredLaptops(Set<Laptop> allLaptops, Map<String, String> filteredParams) {
        Set<Laptop> filteredLaptops = new HashSet<>(allLaptops);
        for (Laptop laptop :
                allLaptops) {
            for (String key :
                    filteredParams.keySet()) {
                switch (key) {
                    case "brand" -> {
                        if (!filteredParams.get(key).equals(laptop.getBrand())) {
                            filteredLaptops.remove(laptop);
                        }
                    }
                    case "operatingSystem" -> {
                        if (!filteredParams.get(key).equals(laptop.getOperatingSystem())) {
                            filteredLaptops.remove(laptop);
                        }
                    }
                    case "screenSize" -> {
                        if (Double.parseDouble(filteredParams.get(key)) > laptop.getScreenSize()) {
                            filteredLaptops.remove(laptop);
                        }
                    }
                    case "ramMemory" -> {
                        if (Integer.parseInt(filteredParams.get(key)) > laptop.getRamMemory()) {
                            filteredLaptops.remove(laptop);
                        }
                    }
                    case "hardDiskSize" -> {
                        if (Integer.parseInt(filteredParams.get(key)) > laptop.getHardDiskSize()) {
                            filteredLaptops.remove(laptop);
                        }
                    }
                }
            }
        }
        return filteredLaptops;
    }
}