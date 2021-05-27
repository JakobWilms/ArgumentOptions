package io.github.jakobwilms.argumentoptions;

import org.jetbrains.annotations.NotNull;

public class OptionExecutor implements OptionConverter<OptionFinisher> {

    private final Option[] options;

    public OptionExecutor(Option[] options) {
        this.options = options;
    }

    public Option[] getOptions() {
        return options;
    }

    public String allToString() {
        if (getOptions().length <= 0) {
            return "Options:{}";
        }
        StringBuilder builder = new StringBuilder();
        builder.append("Options:{");
        int max = getOptions().length - 1;
        for (int i = 0; ; i++) {
            builder.append(getOptions()[i].toString());
            if (i == max) {
                return builder.append("}").toString();
            }
            builder.append(", ");
        }
    }

    public Option get(int i) {
        return getOptions()[i];
    }

    @NotNull
    public Option get(String optionName) {
        if (options.length > 0) {
            for (Option option : getOptions()) {
                if (option.getOptionName().equals(optionName)) {
                    return option;
                }
            }
        }
        return new Option("This option doesn't exist!");
    }

    @Override
    public OptionFinisher convert() {
        return new OptionFinisher(getOptions());
    }

    public OptionFinisher asOptionFinisher() {
        return convert();
    }
}
