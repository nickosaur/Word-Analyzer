package com.wordanalyze.demo;
import com.wordanalyze.demo.POJO.Result;
import com.wordanalyze.demo.POJO.Word;
import com.wordanalyze.demo.Repositories.ResultRepository;
import org.junit.Test;

import java.util.List;

public class ResultRepositoryTest {
    private ResultRepository resultRepository = new ResultRepository();

    private final String testDirectory = "./src/test/java/com/wordanalyze/demo/";

    @Test
    public void test1SimpleFile() throws Exception{
        Result res = resultRepository.analyzeText(testDirectory+"test1.txt", true);
        List<Word> wordList = res.getWordList();
        assert(wordList.get(0).getWord().equals("cat"));
        assert(wordList.get(0).getCount() == 4);
    }

    @Test
    public void test2SimpleFile() throws Exception{
        Result res = resultRepository.analyzeText(testDirectory+"test1.txt", false);
        List<Word> wordList = res.getWordList();
        assert(wordList.get(0).getWord().equals("i"));
        assert(wordList.get(0).getCount() == 5);
        assert(wordList.get(1).getWord().equals("a"));
        assert(wordList.get(1).getCount() == 4);
    }

    @Test
    public void test1LongFile() throws Exception{
        Result res = resultRepository.analyzeText(testDirectory+"test2.txt", true);
        List<Word> wordList = res.getWordList();
        assert(wordList.get(0).getWord().equals("purchase"));
        assert(wordList.get(0).getCount() == 5);
    }

    @Test
    public void test2LongFile() throws Exception{
        Result res = resultRepository.analyzeText(testDirectory+"test2.txt", false);
        List<Word> wordList = res.getWordList();
        assert(wordList.get(0).getWord().equals("a"));
        assert(wordList.get(0).getCount() == 10);
    }
}
