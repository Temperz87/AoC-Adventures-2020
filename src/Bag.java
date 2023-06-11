import java.util.ArrayList;

public class Bag
{
    public Bag (String name)
    {
        this.name = name;
        this.bags = new ArrayList<>();
    }
    public Bag (Bag bag, int count )
    {
        bag.count = count;
    }
    public String name;
    public int count;
    public ArrayList<Bag> bags;
}
