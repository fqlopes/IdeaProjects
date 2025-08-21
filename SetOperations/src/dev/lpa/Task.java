package dev.lpa;
import java.util.Random;

enum Priority {HIGH, MEDIUM, LOW}

enum Status {IN_QUEUE, ASSIGNED, IN_PROGRESS, NOT_ASSIGNED}


public class Task implements Comparable<Task>{

    private String assignee;
    private String projectName;
    private String description;

    private Status status;
    private Priority priority;

    public Task(String projectName, String description, String assignee, Priority priority, Status status) {
        this.assignee = assignee;
        this.projectName = projectName;
        this.description = description;
        this.status = status;
        this.priority = priority;
    }

    public Task(String projectName, String description, String assignee, Priority priority) {
        this(projectName, description, assignee, priority, assignee == null ? Status.NOT_ASSIGNED : Status.ASSIGNED);
    }

    public Task(String projectName, String description, Priority priority) {
        this(projectName,description, null, priority);
    }

    public String getAssignee() {
        return assignee;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    @Override
    public String toString() {
        return "%-20s %-25s %-15s %-10s %s".formatted(projectName, description, priority,assignee,status);
    }

    @Override
    public int compareTo(Task o) {
        int result = this.projectName.compareTo(o.projectName);
        if (result == 0){
            result = this.description.compareTo(o.description);
        }
        return result;
    }

    @Override
    public final boolean equals(Object object) {
        if (!(object instanceof Task task)) return false;
        return getProjectName().equals(task.getProjectName()) && getDescription().equals(task.getDescription());
    }

    @Override
    public int hashCode() {
        int result = getProjectName().hashCode();
        result = 31 * result + getDescription().hashCode();
        return result;
    }
}
