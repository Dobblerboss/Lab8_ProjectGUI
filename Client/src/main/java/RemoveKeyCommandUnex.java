public class RemoveKeyCommandUnex extends Command {
    protected int key;
    @Override
    public boolean checkCom(String[] s) {
        try {
            key = Integer.parseInt(s[0]);
        }catch (NumberFormatException | ArrayIndexOutOfBoundsException e){
            System.out.println("Не пройдена валидация. Введите число(key) на второе место");
            return false;
        }
        if(s.length==1){
            return true;
        }else {
            System.out.println("Не пройдена валидация. Введите число(key) на второе место");
            return false;
        }
    }
}
