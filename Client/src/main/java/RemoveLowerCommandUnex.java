public class RemoveLowerCommandUnex extends Command {
    Flat newFlat;
    @Override
    public boolean checkCom(String[] s) {
        if(s.length==1){
            try {
                JsonDecoder jsonDecoder = new JsonDecoder(s[0]);
                newFlat = jsonDecoder.getDecodeValue();
                return true;
            } catch (NullPointerException e){
                System.out.println("Какое-то значение пустое");
                return false;
            }
        } else if(s.length == 0){
            CreateFlat createFlat = new CreateFlat();
            newFlat = createFlat.getNewFlat();
            return true;
        }
        System.out.println("Не пройдена валидация. Либо не пишите параметры, перейдя в создание Flat, либо напишите json представление элемента Flat");
        return false;
    }
}
