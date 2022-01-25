import exceptions.DukeDeadlineException;
import exceptions.DukeEventException;
import exceptions.DukeException;
import exceptions.DukeTodoException;

import java.time.LocalDate;
import java.util.*;

public class Duke {

    public static void output_matching_deadline(List<Task> all, LocalDate input_date) {
        List<Task> match_date = new ArrayList<Task>();
        for (Task t : all) {
            if (Task.isDeadline(t)) {
                Deadline d = (Deadline) t;
                if (d.getDate().equals(input_date.toString())) {
                    match_date.add(t);
                }
            }
        }
        System.out.println("Here you go! Hope it includes the event you were looking for:)");
        for (Task t : match_date) {
            System.out.println(t);
        }
    }
    public static void main(String[] args) throws DukeException, DukeDeadlineException {
        System.out.println("Oh hello dear, I'm Dukie, Zi Xin's favourite chattie box\n" +
                            "Nice to meet you dear:>\n" +
                            "What can I do for you?");

        Scanner myObj = new Scanner(System.in); //Create a Scanner object
        String input; //declare a string variable to store input
        List<Task> all = new ArrayList<Task>(); //ArrayList of Task
        while (myObj.hasNextLine()) {
            input = myObj.nextLine();

            if (!input.equals("bye")) { //check input not "bye"
                String[] words = input.split(" ", 2); //split input string to get first word (action)

                if (input.equals("list")) { //if list
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 1; i <= all.size(); i++) {
//                        System.out.println(i + ". " + all.get(i - 1).toString());
                        System.out.println(i + ". " + all.get(i - 1));
                    }

                } else if (words[0].equals("mark")) {
                    int n = Integer.parseInt(words[1]);
                    all.get(n - 1).markDone(); //call Task method, mark task as done

                } else if (words[0].equals("unmark")) {
                    int n = Integer.parseInt(words[1]);
                    all.get(n - 1).unMarkDone(); //call Task method, mark task as not done

                } else if (words[0].equals("delete")) {
                    int n = Integer.parseInt(words[1]);
                    all.remove(n - 1);
                    System.out.println("Now you have " + all.size() + " tasks in the list.");

                } else if (words[0].equals("find")) {
                    // get all deadline tasks that matches input date
                    String input_date = words[1];

                    try {
                        LocalDate localdate = LocalDate.parse(input_date);
                        Duke.output_matching_deadline(all, localdate);
                    } catch (Exception e) {
                        DukeException ex = new DukeException("Please input date in the format YYYY-MM-DD to be found");
                        System.out.println(ex.getMessage());
                    }
                }

                else { //adding of Tasks
                    if (words[0].equals("todo")) {
                        try {
                            ToDo item = new ToDo(words[1]);
                            all.add(item);
                            System.out.println("Got it. I've added this task: ");
                            System.out.println(item);
                            System.out.println("Now you have " + all.size() + " tasks in the list."); //print when new task added
                        } catch (Exception e) {
                            DukeTodoException error = new DukeTodoException("OOPS!!! The description of a todo cannot be empty.");
                            System.out.println(error.getMessage());
                        }
                    }

                    else if (words[0].equals("deadline")) {
                        try {
                            Deadline item = Deadline.setDeadline(words[1]);
                            if (item != null) {
                                all.add(item);
                                System.out.println("Now you have " + all.size() + " tasks in the list."); //print when new task added
                            }
                        } catch(Exception e) {
                            DukeDeadlineException d = new DukeDeadlineException("OOPS!!! Please re-enter.");
                            System.out.println(d.getMessage());
                        }
                    }

                    else if (words[0].equals("event")) {
                        try {
                            Event item = Event.setEvent(words[1]);
                            if (item != null) {
                                all.add(item);
                                System.out.println("Now you have " + all.size() + " tasks in the list."); //print when new task added
                            }
                        } catch(Exception e) {
                            DukeEventException d = new DukeEventException("OOPS!!! Please re-enter.");
                            System.out.println(d.getMessage());
                        }
                    }

                    else {
                        DukeException d = new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
                        System.out.println(d.getMessage());
                    }

                }

            } else {
                System.out.println("Bye. Hope to see you again soon!"); //ending sentence
                System.exit(0);
            }
        }
    }
}
