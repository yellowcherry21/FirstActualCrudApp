package by.bsu.dao;

import by.bsu.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class PersonDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> getPeople(){
        return jdbcTemplate.query("SELECT * FROM Person", new PersonMapper());
    }

    public Person getExactPerson(int index){
        return jdbcTemplate.query("SELECT * FROM Person WHERE id = ?",
                new Object[]{index},
                new BeanPropertyRowMapper<Person>()).stream() .findAny().orElse(null);
    }

    public void create(Person person){
       jdbcTemplate.update("INSERT INTO Person VALUES (1,?)",person.getName());
    }

    public void edit(int id, Person updatedPerson){
        jdbcTemplate.update("UPDATE Person SET name = ? WHERE id = ?",
                updatedPerson.getName(),
                updatedPerson.getId());
    }

    public void delete(int id){
        jdbcTemplate.update("DELETE FROM Person WHERE id = ?", id);
    }
}
