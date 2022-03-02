package by.bsu.dao;

import by.bsu.models.Person;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDAO {
    private static int PEOPLE_COUNT = 0;
    private static final String URL = "jdbc:postgresql://localhost:5432/learn_db";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "admin";

    private static Connection connection;

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Person> getPeople(){
        List<Person> people = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            String SQL = "select * from Person";
            ResultSet resultSet = statement.executeQuery(SQL);

            while (resultSet.next()){
                Person person = new Person();
                person.setId(resultSet.getInt("id"));
                person.setName(resultSet.getString("name"));

                people.add(person);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return people;
    }

    public Person getExactPerson(Integer index){
        Person person = new Person();
        try{
            Statement statement = connection.createStatement();
            String SQL = "select * from Person where id = " +  index.toString();
            ResultSet resultSet = statement.executeQuery(SQL);

            person.setName(resultSet.getString("name"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
//        return people.stream()
//                .filter(x->x.getId() == index)
//                .findAny()
//                .orElse(null);
        return person;
    }

    public void create(Person person){
        try {
            Statement statement = connection.createStatement();
            String query = "insert into Person values(" + 1 + ", '"+ person.getName() + "')";
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
//        person.setId(++PEOPLE_COUNT);
//        people.add(person);
    }

    public void edit(int id, Person updatedPerson){
        Person personToUpdate = getExactPerson(id);

        personToUpdate.setName(updatedPerson.getName());
    }

    public void delete(int id){
//        people.remove(getExactPerson(id));
    }
}
