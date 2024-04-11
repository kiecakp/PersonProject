import java.io.*;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;
import java.util.stream.Collectors;

// zad1.1
public class Person implements Serializable{
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

// zad 1.7
    public static void toBinaryFile(List<Person> list, String file){
        try(FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos)){

            for(Person person : list){
                oos.writeObject(person);
            }
        } catch (IOException e) {
            System.err.println("Cannot access: " + file);
        }
    }

    public static List<Person> fromBinaryFile(String file){
        List<Person> result = new ArrayList<>();
        try(FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis)){
            while(true){
                try{
                    result.add((Person) ois.readObject());
                } catch (IOException e) {
                    break;
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return result;
    }

// zad 2.2
    public String toUML(){
        StringBuilder objects = new StringBuilder();
        StringBuilder relations = new StringBuilder();

        // tworzenie funkcji LAMBDA ktora zamienia spacje na brak znaku
        Function<String, String> replaceSpaces = str -> str.replaceAll(" ", "");

        objects.append("object " + replaceSpaces.apply(name) + "\n");
        for(Person parent : parents){
            objects.append("object " + replaceSpaces.apply(parent.name) + "\n");
            relations.append(replaceSpaces.apply(parent.name) + " --> " + replaceSpaces.apply(name) + "\n");
        }

        return String.format(
                "@startuml\n%s\n%s\n@enduml",
                objects, relations
        );
    }

// zad 2.3
    public static String toUML(List<Person> list){
        StringBuilder objects = new StringBuilder();
        StringBuilder relations = new StringBuilder();

        // tworzenie funkcji LAMBDA ktora zamienia spacje na brak znaku
        Function<String, String> replaceSpaces = str -> str.replaceAll(" ", "");

        List<Person> notDouble = new ArrayList<>();
        for(Person person : list){
            objects.append("object " + replaceSpaces.apply(person.name) + "\n");
            notDouble.add(person);

            for(Person parent : person.parents){
                if(!notDouble.contains(parent) && !parent.name.isEmpty()){
                    objects.append("object " + replaceSpaces.apply(parent.name) + "\n");
                    relations.append(replaceSpaces.apply(parent.name) + " --> " + replaceSpaces.apply(person.name) + "\n");
                    continue;
                }

                if(!parent.name.isEmpty())
                    relations.append(replaceSpaces.apply(parent.name) + " --> " + replaceSpaces.apply(person.name) + "\n");
            }
        }

        return String.format(
                "@startuml\n%s\n%s\n@enduml",
                objects, relations
        );
    }

// zad 2.4
    public static List<Person> includeSubstring(List<Person> list, String substring){
        List<Person> changed = list.stream()
                .filter(name -> name.name.contains(substring))
                .collect(Collectors.toList());

        return changed;
    }
}
