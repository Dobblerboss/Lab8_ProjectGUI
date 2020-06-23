public class ShowCommandUnex extends Command {
    @Override
    public boolean checkCom(String[] s) {
        if(s.length == 0){
            return true;
        } else {
            System.out.println("Не пройдена вадидация. Просто \"show\", без параметров");
            return false;
        }
    }
}
