import java.util.ListResourceBundle;

public class Lables_en_IE extends ListResourceBundle {
    private Object[][] contents = {
            {"Add", "Add"},
            {"Remove all", "Remove all"},
            {"Update","Update"},
            {"Remove by key","Remove by key"},
            {"Replace if greater","Replace if greater"},
            {"Replace if lower","Replace if greater"},
            {"Remove lower", "Remove lower"},
            {"Max by transport", "Max by transport"},
            {"Sum of rooms", "Sum of rooms"},
            {"Flag", "Ireland.png"},
            {"Filter", "Filter"},
            {"Main window", "Main window"},
            {"Key","Key"},
            {"Name of Flat","Name of Flat"},
            {"Creation date","Creation date"},
            {"Area", "Area"},
            {"Number of rooms","Number of rooms"},
            {"New?","New?"},
            {"Furnish","Furnish"},
            {"Transport", "Transport"},
            {"House name", "House name"},
            {"House year","House year"},
            {"Number of floors","Number of floors"},
            {"Owner","Owner"},
            {"Login", "Login"},
            {"Password","Password"},
            {"Color of background is yours", "Color of background is yours"},
            {"Insert","Insert"},
            {"Port","Port"},
            {"Enter like new user?","Enter like new user?"},
            {"Authorization","Authorization"},
            {"Passport of object","Passport of object"},
            {"field is empty","field is empty"},
            {"Check each parameter. Maybe it's empty or you use excess signs. ","Check each parameter. Maybe it's empty or you use excess signs. "},
            {"This key already exists","This key already exists"},
            {"and it doesn't belong to you","and it doesn't belong to you"},
            {"Changing", "Changing"},
            {"Replacement", "Replacement"},
            {"Clearing","Clearing"},
            {"Unchanged","Unchanged"},
            {"Error", "Error"},
            {"Server answer","Server answer"},
            {"Enter again","Enter again"},
            {"Name is taken", "Name is taken"},
            {"No user with this name","No user with this name"},
            {"Incorrect password","Incorrect password"},
            {"Connection error", "Connection error"},
            {"Incorrect", "Incorrect"},
            {"Incorrect enter", "Incorrect enter"},
            {"info", "Type of collection - LinkedHashMap\nKey - Integer\nValue - Flat"},
            {"Information", "Information"},
            {"Help","Help"},
            {"help", "<html><h2>Action of buttons</h2><i>i - show info about collection<br>" +
                    "Add - add element with key<br>" +
                    "Remove all - remove all yours elements of collection<br>" +
                    "Update - update your element(possible from table or with id)<br>" +
                    "Remove by key - remove your element by key<br>" +
                    "Replace if greater - Replace element by key, if new element is greater<br>" +
                    "Replace if lower - Replace element by key, if new element is lower<br>" +
                    "Remove lower - remove all your elements from collection lower than this<br>" +
                    "Max. transport - show any element from collection, which transport is max<br>" +
                    "Sum of rooms - show sum of field  Number of rooms for each element of collection</i>"},
    };
    @Override
    protected Object[][] getContents() {
        return contents;
    }
}
