package ua.adeptius.spribe.test.firsttask;


import org.junit.Assert;
import org.junit.Test;

import java.util.NoSuchElementException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static ua.adeptius.spribe.test.firsttask.WordCounter.*;


public class WordCounterTest {

    @Test(expected=IllegalArgumentException.class)
    public void wordAddEmptyStringTest() throws Exception {
        addNewWord("");
    }

    @Test(expected=IllegalArgumentException.class)
    public void wordAddNullStringTest() throws Exception {
        addNewWord(null);
    }

    @Test(expected = NoSuchElementException.class)
    public void getCountOfWordTest() throws Exception {
        getCountOfWord("Absent word");
    }

    @Test
    public void wordCounterTest() throws Exception {
        CountDownLatch latch = new CountDownLatch(1);
        ExecutorService executor = Executors.newFixedThreadPool(4);

        executor.submit(new RunAnotherThread(latch, "FirstWord"));
        executor.submit(new RunAnotherThread(latch, "FIRSTWORD"));
        executor.submit(new RunAnotherThread(latch, "SecondWord"));
        executor.submit(new RunAnotherThread(latch, "secondword"));
        latch.countDown();

        executor.shutdown();
        while (!executor.awaitTermination(1, TimeUnit.MILLISECONDS));

        Assert.assertEquals(200000L, getCountOfWord("FirstWord"));
        Assert.assertEquals(200000L, getCountOfWord("SecondWord"));
    }

    private class RunAnotherThread implements Runnable{

        private CountDownLatch latch;
        private String word;

        private RunAnotherThread(CountDownLatch latch, String word){
            this.latch = latch;
            this.word = word;
        }

        @Override
        public void run() {
            try {
                latch.await();
                for (int i = 1; i <= 100000; i++) {
                    addNewWord(word);
                }
            } catch (InterruptedException e) {
                System.out.println("Latch was interrupted in " + Thread.currentThread());
            }
        }
    }
}
