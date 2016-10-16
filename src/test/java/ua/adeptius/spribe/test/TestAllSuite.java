package ua.adeptius.spribe.test;


import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import ua.adeptius.spribe.test.firsttask.WordCounterTest;
import ua.adeptius.spribe.test.secondtask.FindOpponentTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        WordCounterTest.class,
        FindOpponentTest.class
})
public class TestAllSuite {
}
