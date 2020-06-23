public class SumOfNumberOfRoomsCommandUnex extends Command {

    @Override
    public boolean checkCom(String[] s) {
        if(s.length == 0){
            return true;
        } else {
            System.out.println("Не пройдена вадидация. Просто \"sum_of_number_of_rooms\", без параметров");
            return false;
        }
    }
}
