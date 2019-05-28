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
    public void testSimpleFile() throws Exception{
        Result res = resultRepository.analyzeText(testDirectory+"test1.txt", true);
        List<Word> wordList = res.getWordList();
        //System.out.println(wordList.get(0).getWord());
        assert(wordList.get(0).getWord().equals("cat"));
        assert(wordList.get(0).getCount() == 4);
    }
}
