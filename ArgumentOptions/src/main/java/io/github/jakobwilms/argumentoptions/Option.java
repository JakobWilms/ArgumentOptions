package io.github.jakobwilms.argumentoptions;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class Option {

    @NotNull
    private final String optionName;
    @Nullable
    private final String option;
    @NotNull
    private OptionExecutorService service;

    public Option(@NotNull String optionName, @Nullable String option, @NotNull OptionExecutorService service) {
        this.optionName = optionName;
        this.option = option;
        this.service = service;
    }

    public Option(@NotNull String optionName, @Nullable String option) {
        this(optionName, option, (name, declaredOption) -> {
        });
    }

    public Option(String optionName) {
        this(optionName, null);
    }

    @Nullable
    public String getOption() {
        return option;
    }

    @NotNull
    public String getOptionName() {
        return optionName;
    }

    public void execute() {
        getService().execute(getOptionName(), getOption());
    }

    @Override
    public String toString() {
        return option != null ? "Option{" +
                "optionName='" + optionName + '\'' +
                ", option='" + option + '\'' +
                '}' : "Option{" +
                "optionName='" + optionName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Option option1 = (Option) o;
        return getOptionName().equals(option1.getOptionName()) && Objects.equals(getOption(), option1.getOption());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getOptionName(), getOption());
    }

    @NotNull
    public OptionExecutorService getService() {
        return service;
    }

    public void setService(OptionExecutorService service) {
        this.service = service != null ? service : (optionName, option) -> {
        };
    }
}
