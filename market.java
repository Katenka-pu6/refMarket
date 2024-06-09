import java.util.LinkedList;
import java.util.Queue;

interface QueueBehaviour {
    void enqueue(String person);
    String dequeue();
}

interface MarketBehaviour {
    void addPersonToQueue(String person);
    String servePerson();
}

public class market implements QueueBehaviour, MarketBehaviour {
    private Queue<String> queue = new LinkedList<>();

    @Override
    public void enqueue(String person) {
        queue.add(person);
    }
 
    @Override
    public String dequeue() {
        return queue.poll();
    }

    @Override
    public void addPersonToQueue(String person) {
        queue.add(person);
        System.out.println(person + " added to the queue.");
    }

    @Override
    public String servePerson() {
        if (!queue.isEmpty()) {
            String person = queue.poll();
            System.out.println(person + " served.");
            return person;
        } else {
            System.out.println("No one in the queue.");
            return null;
        }
    }

    public void update() {
        System.out.println("Market status updated.");
    }

    public static void main(String[] args) {
        market market = new market();

        market.addPersonToQueue("Alice");
        market.addPersonToQueue("Bob");

        market.servePerson();
        market.servePerson();

        market.update();
    }
} 