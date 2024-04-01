import java.util.List;

public class Main {
    public static void main(String[] args) {
//  ------ zad1.1 ------
//        Person person = Person.fromCsvLine("Ewa Kowalska,03.11.1901,05.03.1990,,");
//        System.out.println(person.name);
//        System.out.println(person.birthDate);
//        System.out.println(person.deathDate);

//  ------ zad1.2 && zad1.3 && zad1.4 -----
//        List<Person> list = Person.fromCsv("family.csv");
//        for(Person person : list){
//            System.out.println(person.name);
//        }

//  ------ zad1.5 && zad1.6 ------
        List<Person> list = Person.fromCsv("family.csv");
        for(Person person : list){
            System.out.println(person.name);
            for(Person parent : person.parents){
                System.out.println("       " + parent.name);
                System.out.println("        " + parent.birthDate);
            }
        }
    }
}