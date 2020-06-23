public class UpdateIdCommandUnex extends Command {
    protected long id;
    protected Flat newFlat;
    protected Integer newKey;
    @Override
    public boolean checkCom(String[] s) {
        try {
            id = Long.parseLong(s[0]);
        } catch (NumberFormatException e) {
            System.out.println("Не пройдена вадидация. Введите число (id) на второе место");
            return false;
        }
        return true;
    }
    public void set(long id, Flat newFlat, Integer newKey){
        this.newFlat =newFlat;
        this.id = id;
        this.newKey = newKey;
    }
}
