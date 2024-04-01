// zad 1.4
public class AmbiguousPersonException extends Exception{
    public AmbiguousPersonException(Person person){
        super("Osoba " + person.name + " juz istnieje");
    }
}
