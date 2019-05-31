package com.wordanalyze.demo;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.wordanalyze.demo.POJO.Result;
import com.wordanalyze.demo.POJO.Word;
import com.wordanalyze.demo.Repositories.WordFrequencyRepository;
import com.wordanalyze.demo.Utilities.PropertiesLoader;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class WordFrequencyResultRepositoryTest {
    private WordFrequencyRepository resultRepository = new WordFrequencyRepository();
    private Gson gson = new Gson();
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

    @Test
    public void test1LongerFile() throws Exception{
        Result res = resultRepository.analyzeText(testDirectory+"test3.txt", true);
        List<Word> wordList = res.getWordList();

        assert(wordList.get(0).getWord().equals("tom"));
        assert(wordList.get(0).getCount() == 32);
        assert(wordList.get(1).getWord().equals("daisy"));
        assert(wordList.get(1).getCount() == 30);

    }

}
