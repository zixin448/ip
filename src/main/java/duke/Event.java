package duke;

import exceptions.DukeEventException;
import java.io.IOException;

/**
 * Extends Task class
 * Represents an Event task held at a venue
 */
public class Event extends Task {

    protected String at;

    /**
     * Constructor
     * Takes in Event task name and Event task venue
     * @param name String Event task name
     * @param at String Event task location
     */
    public Event(String name, String at) {
        super(name);
        this.at = at;
    }

    /**
     * Static method that return an Event task object
     * @param input String to be parsed
     * @return Event object
     * @throws IOException
     */
    public static Event setEvent(String input) throws IOException {
        String eventname;
        String eventat;


        try {
            String[] split = input.split(" /at ");
            eventname = split[0];
            eventat = split[1];
            Event e = new Event(eventname, eventat);
            return e;
        } catch (Exception e) {
            DukeEventException error = new DukeEventException(
                    "OOPS!!! Please enter in format: event <event> /at <event venue> \n " +
                    "e.g. event Lesson /at Com1");
            System.out.println(error.getMessage());
        }
        return null;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at:" + at + ")";
    }
}
