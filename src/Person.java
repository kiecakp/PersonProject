import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

// zad1
public class Person {
    public String name;
    public LocalDate birthDate;
    public LocalDate deathDate;
    public List<Person> parents;

    public static Person fromCsvLine(String line){
        Person person = new Person();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String[] splited = line.split(",");
        person.name = splited[0];
        person.birthDate = LocalDate.parse(splited[1], formatter);
        if(!splited[2].isEmpty()){
            person.deathDate = LocalDate.parse(splited[2], formatter);
        }

// zad 5
        person.parents = new ArrayList<>();
        for(int i = 3; i < splited.length; i++){
            Person parent = new Person();
            parent.name = splited[i];
            person.parents.add(parent);
        }
        return person;
    }

// zad 2
    public static List<Person> fromCsv(String path){
        ArrayList<Person> list = new ArrayList<>();

        try {
            FileReader file = new FileReader(path);
            BufferedReader reader = new BufferedReader(file);
            String line = reader.readLine();    // linia naglowkowa
            line = reader.readLine();           // linia z danymi

            while(line != null){
                Person person = fromCsvLine(line);

// zad 3 && zad 4
                try{
                    person.checkDeathDate();
                    person.checkName(list);
                } catch (NegativeLifespanException | AmbiguousPersonException e){
                    System.out.println(e.getMessage());
                }

// zad 5
                for(int i = 0; i < person.parents.size(); i++){
                    for (Person person1 : list){
                        if(person.parents.get(i).name.equals(person1.name)){
                            person.parents.set(i, person1);
                        }
                    }
                }

                list.add(person);


                line = reader.readLine();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return list;
    }

// zad 3
    public void checkDeathDate() throws NegativeLifespanException {
        if(deathDate != null && deathDate.isBefore(birthDate)){
            throw new NegativeLifespanException(this);
        }
    }

// zad 4
    public void checkName(List<Person> list) throws AmbiguousPersonException {
        for(Person person : list){
            if(person.name.equals(this.name)){
                throw new AmbiguousPersonException(this);
            }
        }
    }
}
