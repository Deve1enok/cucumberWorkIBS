package org.ibs.fazlyakhmetov.steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.ru.И;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;

import java.sql.*;
import java.util.List;

@Tag("@jdbc")
public class JdbcSteps {
    public static Statement statement;
    public static Connection connection;
    public static PreparedStatement preparedStatement;

    @И("подключение к базе данных Food")
    public void подключение_к_базе_данных_Food() throws SQLException {
        connection = DriverManager.getConnection("jdbc:h2:tcp://149.154.71.152:9092/mem:testdb;DB_CLOSE_DELAY=-1;DATABASE_TO_UPPER=false;",
                "user", "pass");
        statement = connection.createStatement(
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY
        );

    }

    @И("проверяем таблицу на наличие записей")
    public boolean проверяем_таблицу_на_наличие_записей() throws SQLException {
        String selectAll = "SELECT * FROM food";

        ResultSet defaultTable = statement.executeQuery(selectAll);

        boolean orange = false;
        boolean cabbage = false;
        boolean tomato = false;
        boolean apple = false;

        System.out.printf("%s%n", "Проверка таблицы на наличие дефолтных значений");
        while (defaultTable.next()) {
            int food_id = defaultTable.getInt("food_id");
            String food_name = defaultTable.getString("food_name");
            String food_type = defaultTable.getString("food_type");
            boolean exotic = defaultTable.getBoolean("food_exotic");

            if (food_id == 1 || food_name.equals("Апельсин")) {
                orange = true;
            } else if (food_id == 2 && food_name.equals("Капуста")) {
                cabbage = true;
            } else if (food_id == 3 && food_name.equals("Помидор")) {
                tomato = true;
            } else if (food_id == 4 && food_name.equals("Яблоко")) {
                apple = true;
            } else {
                break;
            }
            System.out.printf("%d %s %s %b%n", food_id, food_name, food_type, exotic);
        }
        return orange && cabbage && tomato && apple;
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

    @И("проверяем таблицу на добавление записи {string},{string},{string}")
    public String проверяем_таблицу_на_добавление_записи(String nameFruit, String typeFruit, String exotic) throws SQLException {
        String selectAll = "SELECT * FROM food";
        ResultSet tableAfterUpdate = statement.executeQuery(selectAll);

        System.out.printf("%n%s%n", "Проверяем таблицу на добавление товара последнего товара");
        while (tableAfterUpdate.next()) {
            String food_name = tableAfterUpdate.getString("food_name");
            String food_type = tableAfterUpdate.getString("food_type");
            String food_exotic = tableAfterUpdate.getString("food_exotic");

            if (food_name.equals(nameFruit) && food_type.equals(typeFruit) && food_exotic.equals(exotic)) {
                Assertions.assertEquals(nameFruit, food_name);
                Assertions.assertEquals(typeFruit, food_type);
                Assertions.assertEquals(exotic, food_exotic);
            } else {
                break;
            }
            System.out.printf("%s %s %b%n", food_name, food_type, food_exotic);
        }
        return String.valueOf(true);
    }

    @И("выполнен запрос на удаление")
    public void выполнен_запрос_на_удаление() throws SQLException {
        statement = connection.createStatement(
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY
        );

        String delete = "DELETE FROM food WHERE food_name LIKE '%Вишня%'";

        statement.executeUpdate(delete);
    }

    @И("проверяем таблицу на удаление записи")
    public boolean проверяем_таблицу_на_удаление_записи() throws SQLException {
        String selectAll = "SELECT * FROM food";
        System.out.printf("%n%s%n", "Проверяем таблицу на удаление товара");

        ResultSet tableAfterDeleteString = statement.executeQuery(selectAll);

        boolean orange = false;
        boolean cabbage = false;
        boolean tomato = false;
        boolean apple = false;

        while (tableAfterDeleteString.next()) {
            int food_id = tableAfterDeleteString.getInt("food_id");
            String food_name = tableAfterDeleteString.getString("food_name");
            String food_type = tableAfterDeleteString.getString("food_type");
            boolean exotic = tableAfterDeleteString.getBoolean("food_exotic");

            if (food_id == 1 && food_name.equals("Апельсин")) {
                orange = true;
            } else if (food_id == 2 && food_name.equals("Капуста")) {
                cabbage = true;
            } else if (food_id == 3 && food_name.equals("Помидор")) {
                tomato = true;
            } else if (food_id == 4 && food_name.equals("Яблоко")) {
                apple = true;
            } else {
                break;
            }
            System.out.printf("%d %s %s %b%n", food_id, food_name, food_type, exotic);
        }
        return orange && cabbage && tomato && apple;

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

    @И("выполняем запрос на вставку")
    public void выполняем_запрос_на_вставку(DataTable arg) throws SQLException {
        String insert =
                "INSERT INTO food(food_name, food_type, food_exotic) VALUES (?, ?, ?)";
        preparedStatement = connection.prepareStatement(insert);

        List<List<String>> table = arg.asLists(String.class);
        for (int i = 0; i < 10; i++) {
            preparedStatement.setString(1, table.get(i).get(0));
            preparedStatement.setString(2, table.get(i).get(1));
            preparedStatement.setString(3, table.get(i).get(2));
            preparedStatement.addBatch();
            preparedStatement.executeBatch();
        }
    }

    @И("выполнен запрос на удаление добавленных записей")
    public void выполнен_запрос_на_удаление_добавленных_записей() throws SQLException {
        String delete = "DELETE FROM food WHERE food_name LIKE '%Арбуз%' OR food_name LIKE '%Ананас%'" +
                "OR food_name LIKE '%Манго%' OR food_name LIKE '%Тыква%' OR food_name LIKE '%Свекла%'" +
                "OR food_name LIKE '%Мандарин%' OR food_name LIKE '%Гранат%' OR food_name LIKE '%Дыня%'" +
                "OR food_name LIKE '%Киви%' OR food_name LIKE '%Лук%'";

        statement.executeUpdate(delete);
    }
}
