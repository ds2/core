package ds2.oss.core.api;

public class TaskExecutionTimedOutException extends CoreException {
    public TaskExecutionTimedOutException(String msg) {
        super(CoreErrors.TimedOut, msg);
    }

    public TaskExecutionTimedOutException(String msg, Throwable t) {
        super(CoreErrors.TimedOut, msg, t);
    }
}
