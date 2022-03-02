package by.bsu.dao;

import by.bsu.models.Person;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonMapper implements RowMapper<Person> {
    @Override
    public Person mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Person person = new Person();


        person.setId(resultSet.getInt("id"));
        person.setName(resultSet.getString("name"));

        return person;
    }
}