package com.suraam.accepttest.formatters;


import cucumber.api.TestCase;
import cucumber.api.event.EventHandler;
import cucumber.api.event.EventPublisher;
import cucumber.api.event.TestCaseStarted;
import cucumber.api.event.TestRunFinished;
import cucumber.api.formatter.Formatter;
import cucumber.api.formatter.NiceAppendable;
import gherkin.pickles.PickleTag;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TagsFormatter implements Formatter {

    private String currentFeatureFile;
    private final NiceAppendable out;

    private List<String> tags;

    private EventHandler<TestCaseStarted> caseStartedHandler = this::handleTestCaseStarted;

    private EventHandler<TestRunFinished> runFinishedHandler = event -> finishReport();

    public TagsFormatter(Appendable out) {
        this.out = new NiceAppendable(out);
        tags = new ArrayList<>();
    }

    @Override
    public void setEventPublisher(EventPublisher publisher) {
        publisher.registerHandlerFor(TestCaseStarted.class, caseStartedHandler);
        publisher.registerHandlerFor(TestRunFinished.class, runFinishedHandler);
    }

    private void handleTestCaseStarted(TestCaseStarted event) {
        if (currentFeatureFile == null || !currentFeatureFile.equals(event.testCase.getUri())) {
            currentFeatureFile = event.testCase.getUri();
            collectTags(event.testCase);
        }
    }

    private void finishReport() {
        String text = "TAGS=" + String.join(",", tags);
        out.append(text);
        out.close();
    }

    private void collectTags(TestCase testCase) {
        testCase.getTags();
        tags.addAll(testCase.getTags()
                .stream()
                .map(PickleTag::getName)
                .collect(Collectors.toList()));
    }

}
