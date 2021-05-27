import io.github.jakobwilms.argumentoptions.Option;
import io.github.jakobwilms.argumentoptions.OptionExecutor;
import io.github.jakobwilms.argumentoptions.OptionFinisher;
import io.github.jakobwilms.argumentoptions.OptionScanner;

import java.util.Arrays;

public class OptionScannerExample {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(args));

        OptionScanner scanner = new OptionScanner(args);
        OptionExecutor executor = scanner.asOptionExecutor();

        System.out.println(executor.allToString());

        Option option1 = executor.get("gui");
        option1.setService((optionName, option) -> {
            if (optionName.equals("gui")) {
                if (option != null) {
                    if (option.equals("true")) {
                        OptionScannerExample.openGui();
                    } else {
                        System.out.println("No GUI!");
                    }
                }
            }
        });

        OptionFinisher finisher = executor.asOptionFinisher();
        finisher.executeAll();
    }

    public static void openGui() {
        System.out.println("GUI opened");
    }
}
