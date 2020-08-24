package main.java.duke.command;

import main.java.duke.Storage;
import main.java.duke.TaskList;
import main.java.duke.Ui;

public class ExitCommand extends Command {
    @Override
    public void execute(Storage storage, TaskList tasks, Ui ui) {
        storage.resetFile();
        storage.saveTaskList(tasks);
        ui.printExit();
    }
}