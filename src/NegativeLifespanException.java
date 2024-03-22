// zad 3
public class NegativeLifespanException extends Exception{
    public NegativeLifespanException(Person person){
        super("Osoba " + person.name + " ma niepoprawna date smierci");
    }
}
