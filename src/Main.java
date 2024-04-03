import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
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
//        List<Person> list = Person.fromCsv("family.csv");
//        for(Person person : list){
//            System.out.println(person.name);
//            for(Person parent : person.parents){
//                System.out.println("       " + parent.name);
//                System.out.println("        " + parent.birthDate);
//            }
//        }

//  ------ zad1.7 -------
        List<Person> list = Person.fromCsv("family.csv");
        Person.toBinaryFile(list, "family.bin");

        List<Person> result = Person.fromBinaryFile("family.bin");
        for(Person person : result){
            System.out.println(person.name);
            for(Person parent : person.parents){
                System.out.println("     " + parent.name);
                System.out.println("        " + parent.birthDate);
            }
        }
    }
}