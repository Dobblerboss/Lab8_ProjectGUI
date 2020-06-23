public class SumOfNumberOfRoomsCommand extends SumOfNumberOfRoomsCommandUnex implements ExecuteCommand {
    @Override
    public String execute() {
        Long all = MainServer.flats.values().stream().mapToLong(flat -> (Long) flat.getNumberOfRooms()).sum();
        return "Общее количество комнат - " + all.toString();
    }
}
