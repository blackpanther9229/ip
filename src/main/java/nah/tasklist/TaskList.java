package nah.tasklist;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;

import nah.data.Task;
import nah.exceptions.NahException;

public class TaskList {
    private LinkedList<Task> tasks;
    private int taskCount = 0;

    public TaskList(LinkedList<Task> tasks) {
        this.tasks = tasks;
    }
    public TaskList() {
        this.tasks = new LinkedList<Task>();
    }

    /**
     * return number of tasks in Nah.TaskList
     * @return
     */
    public int taskCount() {
        return this.taskCount;
    }
    /**
     * Add new Nah.Data.Task to the Nah.TaskList.Nah.TaskList
     * @param newTask
     * @return
     */
    public String add(Task newTask) {
        tasks.add(newTask);
        taskCount++;
        return " Got it. I've added this task:\n"
                + "   " + newTask.toString() + "\n"
                + " Now you have " + taskCount + " tasks in the list.\n";
    }

    /**
     * Mark task i as done
     * @param i
     * @return
     * @throws NahException.InvalidTaskNumberException
     */
    public String mark(int i) throws NahException.InvalidTaskNumberException {
        if (taskCount == 0) {
            throw new NahException.InvalidTaskNumberException();
        }
        if (i <= 0 || i > taskCount) {
            throw new NahException.InvalidTaskNumberException(i, taskCount);
        }
        tasks.get(i - 1).mark();
        return " Nice! I've marked this task as done:\n"
                + "   " + tasks.get(i - 1).toString();

    }

    /**
     * Unmark the task i
     * @param i
     * @return
     * @throws NahException.InvalidTaskNumberException
     */
    public String unMark(int i) throws NahException.InvalidTaskNumberException {
        if (taskCount == 0) {
            throw new NahException.InvalidTaskNumberException();
        }
        if (i <= 0 || i > taskCount) {
            throw new NahException.InvalidTaskNumberException(i, taskCount);
        }
        tasks.get(i - 1).unMark();
        return " OK, I've marked this task as not done yet:\n"
                + "   " + tasks.get(i - 1).toString();

    }

    /**
     * Delete task i
     * @param i
     * @return
     * @throws NahException.InvalidTaskNumberException
     */
    public String delete(int i) throws NahException.InvalidTaskNumberException {
        if (taskCount == 0) {
            throw new NahException.InvalidTaskNumberException();
        }
        if (i <= 0 || i >= taskCount) {
            throw new NahException.InvalidTaskNumberException(i, taskCount);
        }
        taskCount--;
        String s = " Noted. I've removed this task:\n"
                + "   " + tasks.get(i - 1).toString() + "\n"
                + " Now you have " + taskCount + " tasks in the list.\n";
        tasks.remove(i - 1);
        return s;
    }

    /**
     * Return String representation of all tasks in the list
     * @return
     */
    public String readTask() {
        String s = " Here are the tasks in your list:\n";
        for (int i = 1; i <= taskCount; i++) {
            s += " " + i + ". " + tasks.get(i - 1).toString() + "\n";
        }
        return s;
    }

    /**
     * Return String representation of tasks in the list that are before due
     * @param time
     * @return
     */
    public String dueOn(String time) {
        LocalDateTime due = LocalDateTime.parse(time, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        String s = " Here are the tasks in your list that ends before the due:\n";
        int i = 1;
        for (Task t : tasks) {
            if (t instanceof Task.Deadlines || t instanceof Task.Events) {
                if (t.endTime().isBefore(due)) {
                    s += " " + i + ". " + t.toString() + "\n";
                }
            }
        }
        return s;
    }

    /**
     * A brief description of task list to store in a txt file
     * @return
     */
    public String brief() {
        String s = "";
        for (int i = 1; i <= taskCount; i++) {
            s += " " + i + ". " + tasks.get(i - 1).brief() + "\n";
        }
        return s;
    }

}
