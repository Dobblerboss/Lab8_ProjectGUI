public class KeyFlatCommand extends Command {
    protected Integer key;
    protected Flat newFlat;

    public boolean checkCom(String[] s) {
        if(s.length==2){
            try {
                key = Integer.parseInt(s[0]);
            }catch(NumberFormatException e){
                System.out.println("Не пройдена вадидация. Введите число (key) на второе место");
                return false;
            }catch (ArrayIndexOutOfBoundsException e){
                System.out.println("Не пройдена вадидация. Нужно указать один параметр (key) или два параметра(key, json представление элемента Flat).");
                return false;
            }
            try {
                JsonDecoder jsonDecoder = new JsonDecoder(s[1]);
                newFlat = jsonDecoder.getDecodeValue();
                return true;
            } catch (NullPointerException e){
                System.out.println("Не пройдена вадидация. Какое-то значение пустое");
                return false;
            }
        } else if(s.length == 1){
            key = Integer.parseInt(s[0]);
            /*CreateFlat createFlat = new CreateFlat();
            newFlat = createFlat.getNewFlat();*/
            return true;
        }
        System.out.println("Не пройдена вадидация. Нужно указать один параметр (key) или два параметра(key, json представление элемента Flat).");
        return false;
    }

    public void setKey(Integer key) {
        this.key = key;
    }

    public void setNewFlat(Flat newFlat) {
        this.newFlat = newFlat;
    }
}
