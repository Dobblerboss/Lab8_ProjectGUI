import java.util.ListResourceBundle;

public class Lables_pl_PL extends ListResourceBundle {
    private Object[][] contents = {
            {"Add", "Dodać"},
            {"Remove all", "Usuń wszystkie"},
            {"Update", "Zaktualizować"},
            {"Remove by key", "Usuń według klucza"},
            {"Replace if greater", "Usuń jeśli więcej"},
            {"Replace if lower", "Usuń jeśli mniej"},
            {"Remove lower", "Usuń mniejsze"},
            {"Max by transport", "Maks. w transporcie"},
            {"Sum of rooms", "Suma pokoi"},
            {"Flag", "Poland.png"},
            {"Filter", "Filtr"},
            {"Main window", "Okno główne"},
            {"Key", " Klucz"},
            {"Name of Flat", "Nazwa placu"},
            {"Creation date", "Data utworzenia"},
            {"Area", "Powierzchnia"},
            {"Number of rooms", "Liczba pokoi"},
            {"New?", "Nowy?"},
            {"Furnish", "Meble"},
            {"Transport", "Transport"},
            {"House name", "Nazwa domu"},
            {"House year", "Rok budowy"},
            {"Number of floors", "Liczba pięter"},
            {"Owner", "Właściciel"},
            {"Login", "Login"},
            {"Password", "Hasło"},
            {"Color of background is yours", "Kolor tła-twój kolor"},
            {"Insert", "Wpisz"},
            {"Port", "Port"},
            {"Enter like new user?", "Zaloguj się jako nowy użytkownik?"},
            {"Authorization", "Autoryzacja"},
            {"Passport of object", "Paszport obiektu"},
            {"field is empty", "pole puste"},
            {"Check each parameter. Maybe it's empty or you use excess signs. ", "Sprawdź każdą opcję. Być może jest pusty, albo użyłeś dodatkowych znaków. "},
            {"This key already exists", "Taki klucz już istnieje"},
            {"and it doesn' t belong to you", "i nie należy do ciebie"},
            {"Changing", "Zmiana"},
            {"Replacement", "Wymiana"},
            {"Clearing", "Kasowanie"},
            {"Unchanged", "Niezmieniony",},
            {"Error", "Błąd"},
            {"Server answer", "odpowiedź serwera"},
            {"Enter again", "Zaloguj się ponownie"},
            {"Name is taken", "Nazwa zajęta"},
            {"No user with this name", "Nie ma Użytkownika o tej nazwie"},
            {"Incorrect password", "Hasło nie pasuje"},
            {"Connection error", "Błąd połączenia"},
            {"Incorrect", "Niepoprawny"},
            {"Incorrect enter", "Nieprawidłowe wejście"},
            {"info", "Typ kolekcji - LinkedHashMap\nKlucz - Integer\nWartość - Flat"},
            {"Information", "Informacje"},
            {"help", "<html><h2>Działania przycisków</h2><i>" +
                    "i-wyświetla informacje o kolekcji<br>" +
                    "Dodać-Dodaj element z podanym kluczem<br>" +
                    "Usuń wszystkie - wyczyść wszystkie elementy kolekcji należące do ciebie<br>" +
                    "Zaktualizować - Aktualizuj element, który posiadasz (możesz zaktualizować z tabeli, możesz użyć id)<br>" +
                    "Usuń według klucza - Usuń według klucza element, który posiadasz<br>" +
                    "Zastąp jeśli więcej - zastąp wartość według klucza, jeśli nowa wartość jest większa niż stara<br>" +
                    "Zastąp jeśli mniej - zastąp wartość według klucza, jeśli nowa wartość jest mniejsza niż stara<br>" +
                    "Usuń mniejsze - usuń wszystkie elementy mniejsze niż podany<br>" +
                    "Maks. w transporcie - wyprowadzenie dowolnego obiektu z kolekcji, którego wartość pola jest maksymalna<br>" +
                    "Suma pokoi - wyświetla sumę wartości pola Liczba pokoi dla wszystkich elementów kolekcji</i>"},
            {"Help", "Pomoc"}
    };

    @Override
    protected Object[][] getContents() {
        return contents;
    }
}
