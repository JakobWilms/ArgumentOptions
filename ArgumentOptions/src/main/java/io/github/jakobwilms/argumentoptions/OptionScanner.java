package io.github.jakobwilms.argumentoptions;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * A class to scan and save all given arguments and options, for further computing convertible to an OptionExecutor
 */
public class OptionScanner implements OptionConverter<OptionExecutor>{

    private final List<Option> options;
    @Nullable
    private final HelpMessage helpMessage;
    @Nullable
    private final String[] helpArguments;

    public OptionScanner(@NotNull String[] args) {
        this(args, new OptionSet());
    }

    public OptionScanner(@NotNull String[] args, @NotNull OptionSet optionSet) {
        this(args, optionSet, "", (String) null);
    }

    public OptionScanner(@NotNull String[] args, @NotNull OptionSet optionSet, @Nullable String helpMessage, @Nullable String... helpArguments) {
        this(args, optionSet, () -> helpMessage, helpArguments);
    }

    public OptionScanner(@NotNull String[] args, @NotNull OptionSet optionSet, @Nullable HelpMessage message, @Nullable String... helpArguments) {

        this.options = new ArrayList<>();
        this.helpMessage = message;
        this.helpArguments = helpArguments;

        for (int i = 0; i < args.length; i++) {
            String string = args[i];
            if (getHelpArguments().length > 0) {
                for (String s : getHelpArguments()) {
                    if (string.toLowerCase().equals(s)) {
                        if (getHelpMessage() != null) {
                            System.out.println(getHelpMessage().getMessage());
                        }
                        System.exit(0);
                    }
                }
            }
            if (string.charAt(0) == '-' && optionSet.has(string)) {
                if (args[i + 1].charAt(0) == '-') {
                    getOptions().add(new Option(string.substring(1)));
                } else {
                    getOptions().add(new Option(string.substring(1), args[i + 1]));
                }
            }
        }
    }

    @Override
    public OptionExecutor convert() {
        return new OptionExecutor(getOptions().toArray(new Option[0]));
    }

    public OptionExecutor asOptionExecutor() {
        return convert();
    }

    public List<Option> getOptions() {
        return options;
    }

    @Nullable
    public HelpMessage getHelpMessage() {
        return helpMessage;
    }

    @Nullable
    public String[] getHelpArguments() {
        return helpArguments;
    }
}
