package duke;
import java.io.IOException;

/**
 * Extends Command class
 * When executed, calls ui method to print relevant output
 */
public class UnmarkCommand extends Command {
    int ranking;

    /**
     * Constructor
     * Initialise index of task to mark as not done
     * @param ranking int ranking of task
     */
    public UnmarkCommand(int ranking) {
        this.ranking = ranking;
    }

    /**
     * Handles un-marking of task of specific index in tasklist as not done
     * @param tasklist TaskList has all current tasks
     * @param ui Ui handles printing to output
     * @param storage Storage saves tasklist
     * @return void
     * @throws IOException
     */
    public void execute(TaskList tasklist, Ui ui, Storage storage) throws IOException {
        assert ranking > 0 && ranking <= tasklist.getLength() : "Please give a valid input (0 < input < number of tasks";

        tasklist.markUndone(ranking);
        storage.writeToFile(tasklist);
        ui.printUnmarkTaskFromDone(tasklist, ranking);
    }
}