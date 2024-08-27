package Command;

import Exceptions.NahException;
import Storage.Storage;
import TaskList.TaskList;
import UI.UI;
import Exceptions.InvalidTaskNumberException;

public class MarkCommand extends Command{
    private int idx;
    public MarkCommand(int idx) {
        this.idx = idx;
    }
    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) throws NahException {
        ui.show(tasks.mark(this.idx));
        storage.rewrite(tasks.brief());
    }
}
