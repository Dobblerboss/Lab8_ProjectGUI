import java.util.Scanner;

public class CreateFlat{
    Scanner scanner;
    private Flat newFlat;

    CreateFlat(){
      scanner = Console.sc;
        newFlat = new Flat();
    }


    public Flat getNewFlat() {
        boolean iter = false;
        while (!iter){
            System.out.println("Введите название элемента");
            iter = newName();
        }
        iter = false;
        while(!iter){
            System.out.println("Введите координаты дома(x и y)");
            iter = newCoordinate();
        }
        iter = false;
        while(!iter){
            System.out.println("Введите площадь");
            iter = newArea();
        }
        iter = false;
        while (!iter){
            System.out.println("Введите количество комнат");
            iter = newNumbersOfRooms();
        }
        iter = false;
        while (!iter){
            System.out.println("Ваш дом новый или старый?");
            iter = newNewOrNot();
        }
        iter = false;
        while (!iter){
            System.out.println("Какое количество транспорта? (Достаточно, нормально, мало или нет)");
            iter = newTransport();
        }
        iter = false;
        while (!iter){
            System.out.println("Какая мебель?(Хорошая, маленькая или нету)");
            iter = newFurnish();
        }
        iter = false;
        while (!iter){
            System.out.println("Введите название дома, год постройки и количество этажей (через пробел)");
            iter = newHouse();
        }
        return newFlat;
    }
    private boolean newHouse(){
        String[] strings = scanner.nextLine().split(" ");

        try{
            if(strings.length!=3){
                System.out.println("Нужно три параметра");
                throw new NumberFormatException();
            }
            String houseName = strings[0];
            Long houseYear = Long.parseLong(strings[1]);
            Long numberOfFloors = Long.parseLong(strings[2]);
            if ((houseName.trim().length()==0)||(houseYear<=0)||(houseYear>155)||(numberOfFloors<=0)){
                throw new NumberFormatException();
            }
            newFlat.setHouse(houseName,houseYear,numberOfFloors);

        } catch (NumberFormatException e){
            System.out.println("Введите название(в одно слово) и два положительных числа. Первое число до 155");
            return false;
        }
        return true;
    }
    private boolean newFurnish(){
        String strings = scanner.nextLine().trim();
        String condition="";
        switch (strings){
            case "нету":
            case "Нету":
            case "нет":
            case "Нет":
            case "нема":
            case "Нема":
                condition = "NONE";
                break;
            case "маленькая":
            case "Маленькая":
                condition = "LITTLE";
                break;
            case "Хорошая":
            case "хорошая":
            case "пойдёт":
            case "Пойдёт":
                condition = "FINE";
                break;
            default:
                System.out.println("Хорошая, маленькая или нету?");
                return false;
        }
        newFlat.setFurnish(condition);
        return true;
    }

    private boolean newTransport(){
        String strings = scanner.nextLine().trim();
        String condition="";
        switch (strings){
            case "нету":
            case "Нету":
            case "нет":
            case "Нет":
            case "нема":
            case "Нема":
                condition = "NONE";
                break;
            case "мало":
            case "Мало":
            case "маловато":
            case "Маловато":
                condition = "FEW";
                break;
            case "Достаточно":
            case "достаточно":
            case "Хвататет":
            case "хватает":
                condition = "ENOUGH";
                break;
            case "нормально":
            case "Нормально":
            case "нормуль":
            case "Нормуль":
                condition = "NORMAL";
                break;
            default:
                System.out.println("Достаточно, нормально, мало или нет?");
                return false;
        }
        newFlat.setTransport(condition);
        return true;
    }

    private boolean newCoordinate(){
        String[] strings = scanner.nextLine().split(" ");
        if(strings.length!=2){
            System.out.println("Нужно два параметра");
            return false;
        }
        Float x;
        Float y;
        try {
            x = Float.parseFloat(strings[0]);
            y = Float.parseFloat(strings[1]);
        } catch (NumberFormatException e){
            System.out.println("Введите число!");
            return false;
        }
        newFlat.setCoordinates(x,y);
        return true;
    }

    private boolean newArea(){
        String strings = scanner.nextLine().trim();
        Integer area;
        try {
            area = Integer.parseInt(strings);
            if(area<=0){
                throw new NumberFormatException();
            }
        } catch (NumberFormatException e){
            System.out.println("Введите положительное целое число!");
            return false;
        }
        newFlat.setArea(area);
        return true;
    }

    private boolean newNumbersOfRooms(){
        String strings = scanner.nextLine().trim();
        Long number;
        try {
            number = Long.parseLong(strings);
            if (number<=0||number>10){
                throw new NumberFormatException();
            }
        } catch (NumberFormatException e){
            System.out.println("Введите положительное целое число до 10-ти!");
            return false;
        }
        newFlat.setNumberOfRooms(number);
        return true;
    }

    private boolean newNewOrNot(){
        String strings = scanner.nextLine().trim();
        switch (strings){
            case "new":
            case "New":
            case "old":
            case "Old":
                System.out.println("По-рузски");
                return false;
            case "Старый":
            case "старый":
                newFlat.setNewOrNot(false);
                break;
            case "Новый":
            case "новый":
                newFlat.setNewOrNot(true);
                break;
            default:
                System.out.println("Старый или новый?(одно слово)");
                return false;
        }
        return true;
    }

    private boolean newName(){
        String Name = scanner.nextLine();
        if(Name.trim().length()==0){
            System.out.println("Вы не назвали(пустая строка)");
            return false;
        }
        String[] words = Name.split(" ");
        if(words.length>1){
            System.out.println("Назовите одним словом, пожалуйста");
            return false;
        }
        newFlat.setName(Name);
        return true;
    }
}
