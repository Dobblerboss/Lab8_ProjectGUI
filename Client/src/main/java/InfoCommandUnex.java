public class InfoCommandUnex extends Command {
    @Override
    public boolean checkCom(String[] s) {
        if(s.length == 0){
            return true;
        } else {
            System.out.println("Не пройдена вадидация. Просто \"info\", без параметров");
            return false;
        }
    }
}
