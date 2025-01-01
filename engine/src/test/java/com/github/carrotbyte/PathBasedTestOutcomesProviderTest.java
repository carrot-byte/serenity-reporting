package com.github.carrotbyte;

import net.thucydides.model.domain.DataTableRow;
import net.thucydides.model.domain.TestOutcome;
import net.thucydides.model.domain.TestResult;
import net.thucydides.model.domain.TestTag;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PathBasedTestOutcomesProviderTest {

    PathBasedTestOutcomesProvider pathBasedTestOutcomesProvider;

    @BeforeEach
    public void setup() {
        pathBasedTestOutcomesProvider = new PathBasedTestOutcomesProvider(Path.of("src/test/resources/resultfile"));
    }

    @Test
    public void testFailedTestNoParamsParsing() {
        TestOutcome outcome = getMatchingOutcome("No Params - Fail");
        Assertions.assertEquals("calculator2;no-params---fail", outcome.getId());
        Assertions.assertEquals("classpath:features/cucumber/maintain_my_todo_list/bdd_tests2:No Params - Fail", outcome.getScenarioId());
        Assertions.assertEquals("No Params - Fail", outcome.getMethodName());
        Assertions.assertEquals(3, outcome.getTestSteps().size());
        Assertions.assertNotNull(outcome.getUserStory());
        Assertions.assertEquals("No Params - Fail", outcome.getTitle());
        Assertions.assertEquals("", outcome.getDescription());
        Assertions.assertEquals(2, outcome.getTags().size());
        Assertions.assertNotNull(outcome.getStartTime());
        Assertions.assertEquals(0, outcome.getDuration());
        Assertions.assertNotNull(outcome.getTestFailureCause());
        Assertions.assertEquals("java.lang.AssertionError", outcome.getTestFailureClassname());
        Assertions.assertEquals("\nExpected: \u003c1\u003e\n     but: was \u003c8\u003e", outcome.getTestFailureMessage());
        Assertions.assertEquals("FAILURE;java.lang.AssertionError;\nExpected: \u003c1\u003e\n     but: was \u003c8\u003e;MyStepdefs.java", outcome.getTestFailureSummary());
        Assertions.assertNull(outcome.getProject());
        Assertions.assertEquals(TestResult.FAILURE, outcome.getAnnotatedResult());
        Assertions.assertFalse(outcome.isManualTestingUpToDate());
        Assertions.assertFalse(outcome.isManual());
        Assertions.assertEquals("Cucumber", outcome.getTestSource());
        Assertions.assertEquals(TestResult.FAILURE, outcome.getResult());
        Assertions.assertNull(outcome.getDataTable());
        Assertions.assertNull(outcome.getTestOutlineName());
        Assertions.assertEquals(0, outcome.getDataTableRowCount());
    }

    @Test
    public void testTagsParsing() {
        TestOutcome outcome = getMatchingOutcome("No Params - Pass 2");
        TestTag tag = outcome.getTags().iterator().next();
        Assertions.assertEquals("myTag", tag.getName());
        Assertions.assertEquals("tag", tag.getType());
        Assertions.assertEquals("myTag", tag.getDisplayName());
    }

    @Test
    public void testScenarioOutline() {
        TestOutcome outcome = getMatchingOutcome("Add two numbers <num1> & <num2>");
        Assertions.assertEquals("calculator2;add-two-numbers-<num1>-&-<num2>", outcome.getId());
        Assertions.assertEquals("classpath:features/cucumber/maintain_my_todo_list/bdd_tests2:Add two numbers <num1> & <num2>", outcome.getScenarioId());
        Assertions.assertEquals("Add two numbers <num1> & <num2>", outcome.getMethodName());
        Assertions.assertEquals(5, outcome.getTestSteps().size());
        Assertions.assertNotNull(outcome.getUserStory());
        Assertions.assertEquals("Add two numbers <num1> & <num2>", outcome.getTitle());
        Assertions.assertEquals("", outcome.getDescription());
        Assertions.assertEquals(3, outcome.getTags().size());
        Assertions.assertNotNull(outcome.getStartTime());
        Assertions.assertEquals(3092, outcome.getDuration());
        Assertions.assertNotNull(outcome.getTestFailureCause());
        Assertions.assertEquals("java.lang.AssertionError", outcome.getTestFailureClassname());
        Assertions.assertEquals("\n" +
                "Expected: <0>\n" +
                "     but: was <25>", outcome.getTestFailureMessage());
        Assertions.assertEquals("FAILURE;java.lang.AssertionError;\n" +
                "Expected: <0>\n" +
                "     but: was <25>;MyStepdefs.java", outcome.getTestFailureSummary());
        Assertions.assertNull(outcome.getProject());
        Assertions.assertNull(outcome.getAnnotatedResult());
        Assertions.assertFalse(outcome.isManualTestingUpToDate());
        Assertions.assertFalse(outcome.isManual());
        Assertions.assertEquals("Cucumber", outcome.getTestSource());
        Assertions.assertEquals(TestResult.FAILURE, outcome.getResult());
        Assertions.assertEquals(5, outcome.getDataTable().getSize());
        Assertions.assertNull(outcome.getTestOutlineName());
        Assertions.assertEquals(5, outcome.getDataTableRowCount());
    }


    @Test
    public void testScenarioOutlineDataTable() {
        TestOutcome outcome = getMatchingOutcome("Add two numbers <num1> & <num2>");
        DataTableRow dataTableRow = outcome.getDataTable().getRows().get(1);
        Assertions.assertEquals(TestResult.FAILURE, dataTableRow.getResult());
        List<String> expectedValues = Arrays.asList("10", "15", "0");
        List<?> actualValues = dataTableRow.getValues();
        Assertions.assertEquals(expectedValues.size(), actualValues.size());
        Assertions.assertTrue(actualValues.containsAll(expectedValues));
    }

    @Test
    public void testThrowsExceptionWhenNoDirectoryProvided() {
        pathBasedTestOutcomesProvider = new PathBasedTestOutcomesProvider(Path.of("somefakepath"));
        Assertions.assertThrows(IllegalArgumentException.class, () -> pathBasedTestOutcomesProvider.getTestOutcomes());
    }

    private TestOutcome getMatchingOutcome(String desiredName) {
        Stream<TestOutcome> testOutcomes = pathBasedTestOutcomesProvider.getTestOutcomes();
        for (TestOutcome outcome : testOutcomes.collect(Collectors.toList())) {
            if (outcome.getName().equals(desiredName)) {
                System.out.println(outcome.getName());
                return outcome;
            }
        }
        throw new IllegalArgumentException("Test name was not found in Outcomes folder.");
    }
}
