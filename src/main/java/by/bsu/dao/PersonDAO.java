package by.bsu.dao;

import by.bsu.models.Person;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDAO {
    private static int PEOPLE_COUNT = 0;
    private List<Person> people;

    {
        people = new ArrayList<>();

        people.add(new Person(++PEOPLE_COUNT, "A"));
        people.add(new Person(++PEOPLE_COUNT, "B"));
        people.add(new Person(++PEOPLE_COUNT, "C"));

    }

    public List<Person> getPeople(){
        return people;
    }

    public Person getExactPerson(int index){
        return people.stream()
                .filter(x->x.getId() == index)
                .findAny()
                .orElse(null);
    }

    public void create(Person person){
        person.setId(++PEOPLE_COUNT);
        people.add(person);
    }

    public void edit(int id, Person updatedPerson){
        Person personToUpdate = getExactPerson(id);

        personToUpdate.setName(updatedPerson.getName());
    }

    public void delete(int id){
        people.remove(getExactPerson(id));
    }
}
