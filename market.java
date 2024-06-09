import java.util.Queue;
import java.util.LinkedList;

interface QueueBehaviour {
    void enqueue(String person);
    String dequeue();
}

interface MarketBehaviour {
    void addPersonToQueue(String person);
    String servePerson();
}

interface MessagePrinter {
    void printMessage(String message);
}

class SimpleMessagePrinter implements MessagePrinter {
    @Override
    public void printMessage(String message) {
        System.out.println(message);
    }
}

class MarketStatusUpdater implements marketUpdater {
    @Override
    public void update() {
        System.out.println("Market status updated.");
    }
}

public class market implements QueueBehaviour, MarketBehaviour {
    private Queue<String> queue = new LinkedList<>();
    private MessagePrinter messagePrinter;

    public market(MessagePrinter messagePrinter) {
        this.messagePrinter = messagePrinter;
    }

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
        messagePrinter.printMessage(person + " added to the queue.");
    }

    @Override
    public String servePerson() {
        if (!queue.isEmpty()) {
            String person = queue.poll();
            messagePrinter.printMessage(person + " served.");
            return person;
        } else {
            messagePrinter.printMessage("No one in the queue.");
            return null;
        }
    }

    public static void main(String[] args) {
        MessagePrinter messagePrinter = new SimpleMessagePrinter();
        market market = new market(messagePrinter);

        market.addPersonToQueue("Alice");
        market.addPersonToQueue("Bob");

        market.servePerson();
        market.servePerson();

        marketUpdater marketUpdater = new MarketStatusUpdater();
        marketUpdater.update();
    }
}