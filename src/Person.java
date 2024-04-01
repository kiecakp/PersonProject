import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// zad1.1
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

// zad 1.5
        person.parents = new ArrayList<>();
        for(int i = 3; i < splited.length; i++){
            Person parent = new Person();
            parent.name = splited[i];
            person.parents.add(parent);
        }
        return person;
    }

// zad 1.2
    public static List<Person> fromCsv(String path){
        ArrayList<Person> list = new ArrayList<>();

        try {
            FileReader file = new FileReader(path);
            BufferedReader reader = new BufferedReader(file);
            String line = reader.readLine();    // linia naglowkowa
            line = reader.readLine();           // linia z danymi

            while(line != null){
                boolean cancelled = false;
                Person person = fromCsvLine(line);

// zad 1.3 && zad 1.4
                try{
                    person.checkDeathDate();
                    person.checkName(list);
                } catch (NegativeLifespanException | AmbiguousPersonException e){
                    System.out.println(e.getMessage());
                }

// zad 1.5
                for(int i = 0; i < person.parents.size(); i++){
                    for (Person person1 : list){
                        if(person.parents.get(i).name.equals(person1.name)){
                            person.parents.set(i, person1);
                        }
                    }
                }

// zad 1.6
                try{
                    person.checkParent();
                } catch (ParentingAgeException e) {
                    Scanner scanner = new Scanner(System.in);
                    System.out.println(e.getMessage());
                    String input = scanner.nextLine().trim();
                    if(input.equals("Y")){
                        list.add(person);
                        cancelled = true;
                    } else{
                        cancelled = true;
                    }
                }
                if(cancelled == false){
                    list.add(person);
                }

                line = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return list;
    }

// zad 1.3
    public void checkDeathDate() throws NegativeLifespanException {
        if(deathDate != null && deathDate.isBefore(birthDate)){
            throw new NegativeLifespanException(this);
        }
    }

// zad 1.4
    public void checkName(List<Person> list) throws AmbiguousPersonException {
        for(Person person : list){
            if(person.name.equals(this.name)){
                throw new AmbiguousPersonException(this);
            }
        }
    }

// zad 1.6
    public void checkParent() throws ParentingAgeException{
        for(Person parent : this.parents){
            if(!parent.name.isEmpty() && parent.deathDate != null){
                if((Period.between(parent.birthDate, parent.deathDate).getYears() < 15) ||
                        (parent.deathDate.isBefore(this.birthDate))){
                    throw new ParentingAgeException(parent);
                }
            }
        }
    }
}
