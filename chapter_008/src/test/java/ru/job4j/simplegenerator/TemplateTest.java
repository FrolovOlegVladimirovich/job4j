package ru.job4j.simplegenerator;

import org.junit.Test;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class TemplateTest {

    @Test
    public void whenTakeTextWithDataShouldReplaceParamsToString() throws Exception {
        Template template = new SimpleGenerator();
        String text = "I am ${name}. Who are ${subject}?";
        Map<String, String> data = Map.of(
                "${name}", "Oleg",
                "${subject}", "you"
        );
        String expect = "I am Oleg. Who are you?";

        String result = template.generate(text, data);

        assertThat(result, is(expect));
    }

    @Test
    public void whenTakeOneDataOnMultipleKeysResultIsTextWithDataInsteadOfKeys() throws Exception {
        Template template = new SimpleGenerator();
        String text = "Help, ${sos}, ${sos}, ${sos}";
        Map<String, String> data = Map.of(
                "${sos}", "Aaaa"
        );
        String expect = "Help, Aaaa, Aaaa, Aaaa";

        String result = template.generate(text, data);

        assertThat(result, is(expect));
    }

    @Test (expected = Exception.class)
    public void whenNoKeyInDataResultIsException() throws Exception {
        Template template = new SimpleGenerator();
        String text = "Help, ${sos}, ${sos}, ${sos}";
        Map<String, String> data = new HashMap<>();

        template.generate(text, data);
    }

    @Test (expected = Exception.class)
    public void whenKeysInDataMoreThanKeysInText() throws Exception {
        Template template = new SimpleGenerator();
        String text = "Help, ${sos}, ${sos}, ${sos}";
        Map<String, String> data = Map.of(
                "${sos}", "Aaaa",
                "${name}", "Oleg"
        );

        template.generate(text, data);
    }
}