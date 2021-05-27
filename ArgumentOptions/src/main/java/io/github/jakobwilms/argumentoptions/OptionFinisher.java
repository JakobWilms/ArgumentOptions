package io.github.jakobwilms.argumentoptions;

public class OptionFinisher {

    private final Option[] options;

    public OptionFinisher(Option[] options) {
        this.options = options;
    }

    public void executeAll() {
        if (getOptions().length > 0) {
            for (Option option : getOptions()) {
                option.execute();
            }
        }
    }

    public Option[] getOptions() {
        return options;
    }
}
