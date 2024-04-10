package org.ibs.fazlyakhmetov.steps;

import io.cucumber.java.ru.И;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;

import java.sql.*;

@Tag("@jdbc")
public class JdbcSteps {
    public static Statement statement;
    public static Connection connection;
    public static PreparedStatement preparedStatement;

    @И("подключение к базе данных Food")
    public void подключение_к_базе_данных_Food() throws SQLException {
        connection = DriverManager.getConnection("jdbc:h2:tcp://localhost:9092/mem:testdb",
                "user", "pass");
        statement = connection.createStatement(
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY
        );

    }

    @И("проверяем таблицу на наличие дефолтных записей")
    public void проверяем_таблицу_на_наличие_дефолтных_записей() throws SQLException {
        String selectAll = "SELECT * FROM food";

        ResultSet defaultTable = statement.executeQuery(selectAll);

        System.out.printf("%s%n", "Проверка таблицы на наличие дефолтных значений");
        while (defaultTable.next()) {
            int food_id = defaultTable.getInt("food_id");
            String food_name = defaultTable.getString("food_name");
            String food_type = defaultTable.getString("food_type");
            boolean exotic = defaultTable.getBoolean("food_exotic");

            System.out.printf("%d %s %s %b%n", food_id, food_name, food_type, exotic);
        }
    }

    @И("выполнен запрос на вставку записей {string},{string},{int}")
    public void выполнен_запрос_на_вставку_записей(String nameFruit, String typeFruit, int typeExotic) throws SQLException {
        String insert =
                "INSERT INTO food(food_name, food_type, food_exotic) VALUES (?, ?, ?)";

        preparedStatement = connection.prepareStatement(insert);

        preparedStatement.setString(1, nameFruit);
        preparedStatement.setString(2, typeFruit);
        preparedStatement.setInt(3, typeExotic);
        preparedStatement.executeUpdate();
    }

    @И("проверяем таблицу на добавление записи {int},{string},{string}")
    public void проверяем_таблицу_на_добавление_записи(int idFood, String nameFruit, String typeFruit) throws SQLException {
        String selectAll = "SELECT * FROM food";
        ResultSet tableAfterUpdate = statement.executeQuery(selectAll);

        System.out.printf("%n%s%n", "Проверяем таблицу на добавление товара последнего товара");
        tableAfterUpdate.last();
        int food_id = tableAfterUpdate.getInt("food_id");
        String food_name = tableAfterUpdate.getString("food_name");
        String food_type = tableAfterUpdate.getString("food_type");
        boolean exotic = tableAfterUpdate.getBoolean("food_exotic");

        Assertions.assertEquals(idFood, tableAfterUpdate.getInt("food_id"));
        Assertions.assertEquals(nameFruit, tableAfterUpdate.getString("food_name"));
        Assertions.assertEquals(typeFruit, tableAfterUpdate.getString("food_type"));
        Assertions.assertFalse(tableAfterUpdate.getBoolean("food_exotic"));

        System.out.printf("%d %s %s %b%n", food_id, food_name, food_type, exotic);
    }

    @И("выполнен запрос на удаление")
    public void выполнен_запрос_на_удаление() throws SQLException {
        statement = connection.createStatement(
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY
        );

        String delete = "DELETE FROM food WHERE food_id = 5";

        statement.executeUpdate(delete);
    }

    @И("проверяем таблицу на удаление записи")
    public void проверяем_таблицу_на_удаление_записи() throws SQLException {
        String selectAll = "SELECT * FROM food";
        System.out.printf("%n%s%n", "Проверяем таблицу на удаление товара");


        ResultSet tableAfterDeleteString = statement.executeQuery(selectAll);

        while (tableAfterDeleteString.next()) {
            int food_id = tableAfterDeleteString.getInt("food_id");
            String food_name = tableAfterDeleteString.getString("food_name");
            String food_type = tableAfterDeleteString.getString("food_type");
            boolean exotic = tableAfterDeleteString.getBoolean("food_exotic");

            System.out.printf("%d %s %s %b%n", food_id, food_name, food_type, exotic);
        }
    }

    @И("проверяем последнее дефолтное значение в таблице {int},{string},{string}")
    public boolean проверяем_последнее_дефолтное_значение_в_таблице(int productId, String productName, String productType) throws SQLException {
        String selectAll = "SELECT * FROM food";
        ResultSet resultSet = statement.executeQuery(selectAll);

        boolean lastProduct = false;
        System.out.printf("%s%n", "Выводим последний элемент в списке");
        while (resultSet.last()) {
            int food_id = resultSet.getInt("food_id");
            String food_name = resultSet.getString("food_name");
            String food_type = resultSet.getString("food_type");
            boolean exotic = resultSet.getBoolean("food_exotic");
            if (food_id == productId && food_name.equals(productName) && food_type.equals(productType)) {
                lastProduct = true;
                System.out.printf("%d %s %s %b%n", food_id, food_name, food_type, exotic);
            }
            break;
        }
        return lastProduct;
    }

    @И("проверяем предпоследнее дефолтное значение в таблице {int},{string},{string}")
    public boolean проверяем_предпоследнее_дефолтное_значение_в_таблице(int productId, String productName, String productType) throws SQLException {
        String selectAll = "SELECT * FROM food";
        ResultSet resultSet = statement.executeQuery(selectAll);

        boolean previousProduct = false;
        System.out.printf("%s%n", "Выводим последний элемент в списке");
        while (resultSet.previous()) {
            int food_id = resultSet.getInt("food_id");
            String food_name = resultSet.getString("food_name");
            String food_type = resultSet.getString("food_type");
            boolean exotic = resultSet.getBoolean("food_exotic");
            if (food_id == productId && food_name.equals(productName) && food_type.equals(productType)) {
                previousProduct = true;
                System.out.printf("%d %s %s %b%n", food_id, food_name, food_type, exotic);
            }
        }
        return previousProduct;
    }
    @И("отключение от базы данных Food")
    public void отключение_от_базы_данных_Food() throws SQLException {
        connection.close();
    }
}