package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Encapsulates a command.
 * All command types must extend from this class.
 */
public abstract class Command {

    /**
     * Executes the command.
     *
     * @param storage Storage
     * @param taskList Task list
     * @param archive
     * @param ui Ui
     * @return Output strings displayed in the UI
     */
    public abstract String[] execute(Storage storage, TaskList taskList, TaskList archive, Ui ui);
}
