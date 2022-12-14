package ru.vsu.porkhunov.trainroutes.ui.command;

public abstract class ConsoleCommand {
    public static final String COMMAND_SEPARATOR =
            "******************************************************************************";

    private final String name;

    public ConsoleCommand(String name) {
        this.name = name;
    }

    public final void execute() {
        doPreExecute();
        doPostExecute(doExecute());
    }

    public String getName() {
        return name;
    }

    protected abstract String doExecute();

    protected void doPreExecute() {
        System.out.println("\n" + COMMAND_SEPARATOR);
        System.out.println(name);
    }

    protected void doPostExecute(String result) {
        System.out.println(result);
        System.out.println(COMMAND_SEPARATOR + "\n");
    }
}
