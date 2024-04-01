// zad 1.6
public class ParentingAgeException extends Exception{
    public ParentingAgeException(Person person){
        super("Czy chcesz dodac osobe: " + person.name + "?");
    }
}
