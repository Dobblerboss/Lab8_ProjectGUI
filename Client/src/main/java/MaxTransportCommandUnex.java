public class MaxTransportCommandUnex extends Command {

    @Override
    public boolean checkCom(String[] s) {
        if(s.length == 0){
            return true;
        } else {
            System.out.println("Не пройдена вадидация. Просто \"max_by_transport\", без параметров");
            return false;
        }
    }
}
