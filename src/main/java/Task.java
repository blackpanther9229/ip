public class Task {
    private String description;
    private boolean isDone = false;
    Task(String description) {
        this.description = description;
    }

    private void mark() {
        isDone = true;
    }

    private void unMark() {
        isDone = false;
    }

    private String getStatus() {
        return isDone ? "[X]" : "[ ]";
    }

    @Override
    public String toString() {
        return getStatus() + " " + this.description;
    }

}
