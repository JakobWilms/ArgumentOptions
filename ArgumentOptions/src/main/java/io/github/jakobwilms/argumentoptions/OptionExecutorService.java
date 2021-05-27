package io.github.jakobwilms.argumentoptions;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface OptionExecutorService {
    void execute(@NotNull String optionName, @Nullable String option);
}
