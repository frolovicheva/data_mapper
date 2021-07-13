package ru.geekbrains.architecture;

public class MainApp {
    public static void main(String[] args) {

        UserMapper userMapper = new UserMapper (new SQLiteConnection ());
        if (userMapper.connect ()){
            System.out.println ("Успешное подключение к БД");
        };
        if (userMapper.insert (new User ("one","one","one"))){
            System.out.println ("Новый юзер в БД");
        }
        User user = userMapper.findByLoginAndPassword ("one","one");
        System.out.println (user);

    }
}
