package tk.picraftmc.argumentoptions;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class OptionScanner {

    private final List<Option> options;

    public OptionScanner(@NotNull String[] args) {
        this(args, new OptionSet());
    }

    public OptionScanner(@NotNull String[] args, @Nullable OptionSet optionSet) {
        this.options = new ArrayList<>();

        for (int i = 0; i < args.length; i++) {
            String string = args[i];
            if (string.charAt(0) == '-') {
                if (args[i + 1].charAt(0) == '-') {
                    getOptions().add(new Option(string));
                } else {
                    getOptions().add(new Option(string, args[i + 1]));
                }
            }
        }
    }

    public List<Option> getOptions() {
        return options;
    }
}
