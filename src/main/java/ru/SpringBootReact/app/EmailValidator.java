package ru.SpringBootReact.app;

import org.springframework.stereotype.Component;

import java.util.function.Predicate;
import java.util.regex.Pattern;

@Component
public class EmailValidator implements Predicate<String> {

    private static final Predicate IS_MAIL_VALID =
            Pattern.compile(
                    "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
                    Pattern.CASE_INSENSITIVE
            ).asPredicate();

    @Override
    public boolean test(String email) {
        return IS_MAIL_VALID.test(email);
    }
}
