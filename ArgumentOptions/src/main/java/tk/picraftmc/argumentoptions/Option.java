package tk.picraftmc.argumentoptions;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class Option {

    @NotNull
    private final String optionName;
    @Nullable
    private final String option;

    public Option(@NotNull String optionName, @Nullable String option) {
        this.optionName = optionName;
        this.option = option;
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
}
