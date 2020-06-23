import java.util.ListResourceBundle;

public class Lables_ru_RU extends ListResourceBundle {
    private Object[][] contents = {
            {"Add", "Добавить"},
            {"Remove all","Очистить всё"},
            {"Update","Обновить"},
            {"Remove by key","Удалить по ключу"},
            {"Replace if greater","Заменить, если больше"},
            {"Replace if lower","Заменить, если меньше"},
            {"Remove lower", "Удалить меньшее"},
            {"Max by transport", "Макс. по транспорту"},
            {"Sum of rooms", "Сумма комнат"},
            {"Flag", "Russia.png"},
            {"Filter", "Фильтр"},
            {"Main window", "Основное окно"},
            {"Key","Ключ"},
            {"Name of Flat","Имя площади"},
            {"Creation date","Дата создания"},
            {"Area", "Площадь"},
            {"Number of rooms","Кол-во комнат"},
            {"New?","Новый?"},
            {"Furnish","Мебель"},
            {"Transport", "Транспорт"},
            {"House name", "Имя дома"},
            {"House year","Год постройки"},
            {"Number of floors","Кол-во этажей"},
            {"Owner","Владелец"},
            {"Login", "Логин"},
            {"Password","Пароль"},
            {"Color of background is yours", "Цвет фона - ваш цвет"},
            {"Insert","Введите"},
            {"Port","Порт"},
            {"Enter like new user?","Войти как новый пользователь?"},
            {"Authorization","Авторизация"},
            {"Passport of object","Паспорт объекта"},
            {"field is empty","поле пустое"},
            {"Check each parameter. Maybe it's empty or you use excess signs. ","Проверьте каждый параметр. Возможно оно пустое, либо вы использовали лишние знаки. "},
            {"This key already exists","Такой ключ уже существует"},
            {"and it doesn't belong to you","и он не принадлежит вам"},
            {"Changing", "Изменение"},
            {"Replacement", "Замена"},
            {"Clearing","Стирание"},
            {"Unchanged","Неизменный"},
            {"Error", "Ошибка"},
            {"Server answer","Ответ сервера"},
            {"Enter again","Войти снова"},
            {"Name is taken", "Имя занято"},
            {"No user with this name","Нет пользователя с таким именем"},
            {"Incorrect password","Пароль не подходит"},
            {"Connection error", "Ошибка подключения"},
            {"Incorrect", "Неверно"},
            {"Incorrect enter", "Неверный ввод"},
            {"info", "Тип коллекции - LinkedHashMap\nКлюч - Integer\nЗначение - Flat"},
            {"Information", "Информация"},
            {"help", "<html><h2>Действия кнопок</h2><i>i - вывести информацию о коллекции<br>Добавить - добавить элемент с заданным ключом<br>" +
                    "Очистить всё - очистить все элеметы коллекции, принадлежащие вам<br>" +
                    "Обновить - обновить принадлежащий вам элемент(можно обновить из таблицы, можно по id)<br>" +
                    "Удалить по ключу - удалить по ключу принадлежащий вам элемент<br>" +
                    "Заменить если больше - заменить значение по ключу, если новое значение больше старого<br>" +
                    "Заменить если меньше - заменить значение по ключу, если новое значение меньше старого<br>" +
                    "Удалить меньшее - удалить из коллекции все элементы, меньшие, чем заданный<br>" +
                    "Макс. по транспорту - вывести любой объект из коллекции, значение поля которого является максимальным<br>" +
                    "Сумма комнат - вывести сумму значений поля Кол-во комнат для всех элементов коллекции</i>"},
            {"Help","Помощь"}
    };
    @Override
    protected Object[][] getContents() {
        return contents;
    }
}
