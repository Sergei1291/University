package com.epam.university.command;

import java.util.Objects;

public final class CommandResult {

    private final String page;
    private final boolean redirect;

    private CommandResult(String page, boolean redirect) {
        this.page = page;
        this.redirect = redirect;
    }

    public String getPage() {
        return page;
    }

    public boolean isRedirect() {
        return redirect;
    }

    public static CommandResult forward(String page) {
        return new CommandResult(page, false);
    }

    public static CommandResult redirect(String page) {
        return new CommandResult(page, true);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CommandResult that = (CommandResult) o;
        if (redirect != that.redirect) {
            return false;
        }
        return Objects.equals(page, that.page);
    }

    @Override
    public int hashCode() {
        int result = page != null ? page.hashCode() : 0;
        result = 31 * result + (redirect ? 1 : 0);
        return result;
    }

}