package study.self;

enum Priority {HIGH, MEDIUM, LOW}

enum Status {IN_QUEUE, ASSIGNED, IN_PROGRESS, NOT_ASSIGNED}

public class Task implements Comparable<Task> {

    //fields derived from the task itself
    private String worker;
    private String projectName;
    private String taskDescription;

    //status and priority are enums
    Status status;
    Priority priority;

    //TASK = TASKNAME - DESCRIPTION - WORKER - PRIORITY - STATUS
    public Task (String projectName, String taskDescription, String worker, Priority priority, Status status){
        this.projectName = projectName;
        this.taskDescription = taskDescription;
        this.worker = worker;
        this.priority = priority;
        this.status = status;
    }


    public Task (String projectName, String taskDescription, String worker, Priority priority){
        this (projectName,taskDescription, worker, priority, worker == null ? Status.NOT_ASSIGNED : Status.ASSIGNED);
    }

    //FOR TASKS W/ NO NAME
    public Task (String projectName, String taskDescription, Priority priority){
        this (projectName,taskDescription, null, priority );
    }

    public String getWorker() {
        return worker;
    }

    public void setWorker(String worker) {
        this.worker = worker;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
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
    public final boolean equals(Object object) {
        if (!(object instanceof Task task)) return false;

        return getProjectName().equals(task.getProjectName()) && getTaskDescription().equals(task.getTaskDescription());
    }

    @Override
    public int hashCode() {
        int result = getProjectName().hashCode();
        result = 31 * result + getTaskDescription().hashCode();
        return result;
    }

    @Override
    public int compareTo(Task o) {
        int compareResult = this.projectName.compareTo(o.projectName);
        if (this.projectName == o.projectName){
            compareResult = this.taskDescription.compareTo(o.taskDescription);
        }
        return compareResult;
    }

    @Override
    public String toString (){

        return "%-20s %-25s %-15s %-10s %s".formatted(projectName, taskDescription, worker, priority, status);
    }
}
