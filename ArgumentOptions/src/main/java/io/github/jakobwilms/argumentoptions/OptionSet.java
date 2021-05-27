package io.github.jakobwilms.argumentoptions;

public class OptionSet {

    private final String[] get;

    public OptionSet(String... get) {
        this.get = get;
    }

    public OptionSet(String get) {
        this(get.split(""));
    }

    OptionSet() {
        this.get = new String[0];
    }

    public boolean has(String string) {
        if (getGet().length > 0) return has0(string);
        return true;
    }

    private boolean has0(String string) {
        for (String s : getGet()) {
            if (s.equals(string)) {
                return true;
            }
        }
        return false;
    }

    public String[] getGet() {
        return get;
    }
}
