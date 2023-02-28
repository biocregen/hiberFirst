package jm.task.core.jdbc;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;



public class Main extends Util  {
    public static void main(String[] args) {
        UserService service = new UserServiceImpl();
        service.createUsersTable();
        service.saveUser("Ivan", "Ivanov", (byte) 32);
        service.saveUser("Nikita", "Zakurdaev", (byte) 24);
        service.saveUser("Vladimir", "Kuznetcov", (byte) 41);
        service.saveUser("Eldar", "Pravdin", (byte) 18);
        service.getAllUsers();
        service.cleanUsersTable();
        service.dropUsersTable();


    }
}