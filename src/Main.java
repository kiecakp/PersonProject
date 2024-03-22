import java.util.List;

public class Main {
    public static void main(String[] args) {
//  ------ zad1 ------
//        Person person = Person.fromCsvLine("Ewa Kowalska,03.11.1901,05.03.1990,,");
//        System.out.println(person.name);
//        System.out.println(person.birthDate);
//        System.out.println(person.deathDate);

//  ------ zad2 && zad3 && zad4 -----
        List<Person> list = Person.fromCsv("family.csv");
        for(Person person : list){
            System.out.println(person.name);

        }
    }
}