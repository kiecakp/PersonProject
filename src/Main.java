import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
//  ------ zad 1.1 ------
//        Person person = Person.fromCsvLine("Ewa Kowalska,03.11.1901,05.03.1990,,");
//        System.out.println(person.name);
//        System.out.println(person.birthDate);
//        System.out.println(person.deathDate);

//  ------ zad 1.2 && zad 1.3 && zad 1.4 -----
//        List<Person> list = Person.fromCsv("family.csv");
//        for(Person person : list){
//            System.out.println(person.name);
//        }

//  ------ zad 1.5 && zad 1.6 ------
//        List<Person> list = Person.fromCsv("family.csv");
//        for(Person person : list){
//            System.out.println(person.name);
//            for(Person parent : person.parents){
//                System.out.println("       " + parent.name);
//                System.out.println("        " + parent.birthDate);
//            }
//        }

//  ------ zad 1.7 -------
//        List<Person> list = Person.fromCsv("family.csv");
//        Person.toBinaryFile(list, "family.bin");
//
//        List<Person> result = Person.fromBinaryFile("family.bin");
//        for(Person person : result){
//            System.out.println(person.name);
//            for(Person parent : person.parents){
//                System.out.println("     " + parent.name);
//                System.out.println("        " + parent.birthDate);
//            }
//        }

//  ------ zad 2.1 -------
//        String data = "@startuml\n" +
//                "object JanKowalski\n" +
//                "object AnnaKowalska\n" +
//                "\n" +
//                "JanKowalski --> AnnaKowalska\n" +
//                "@enduml";
//        String path = "C:\\Users\\patek\\Desktop\\umcs\\sem2\\personProject\\uml";
//        String file = "uml";
//        PlantUMLRunner.setJarPath("C:\\Users\\patek\\Desktop\\umcs\\sem2\\personProject\\plantuml-1.2024.3.jar");
//        PlantUMLRunner.generate(data, path, file);

//  ------ zad 2.2 ------
//        Person anna = Person.fromCsvLine("Anna Dabrowska,07.02.1930,22.12.1991,Ewa Kowalska,Marek Kowalski");
//        String data = anna.toUML();
//        String path = "C:\\Users\\patek\\Desktop\\umcs\\sem2\\personProject\\uml";
//        String file = "zad2_2";
//        PlantUMLRunner.setJarPath("C:\\Users\\patek\\Desktop\\umcs\\sem2\\personProject\\plantuml-1.2024.3.jar");
//        PlantUMLRunner.generate(data, path, file);

//  ------ zad 2.3 -------
//        List<Person> list = Person.fromCsv("family.csv");
//        String data = Person.toUML(list);
//
//        String path = "C:\\Users\\patek\\Desktop\\umcs\\sem2\\personProject\\uml";
//        String file = "zad2_3";
//        PlantUMLRunner.setJarPath("C:\\Users\\patek\\Desktop\\umcs\\sem2\\personProject\\plantuml-1.2024.3.jar");
//        PlantUMLRunner.generate(data, path, file);

// ------- zad 2.4 -------
//        List<Person> list = Person.fromCsv("family.csv");
//        String substring = "Dabro";
//        List<Person> result = Person.includeSubstring(list, substring);
//        for(Person person : result){
//            System.out.println(person.name);
//        }
    }
}