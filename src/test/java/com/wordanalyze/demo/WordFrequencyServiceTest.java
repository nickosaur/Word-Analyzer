package com.wordanalyze.demo;

import com.google.gson.Gson;
import com.wordanalyze.demo.POJO.Result;
import com.wordanalyze.demo.Services.WordFrequencyService;
import com.wordanalyze.demo.Utilities.PropertiesLoader;
import org.junit.Test;

import java.io.File;
import java.nio.file.Files;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class WordFrequencyServiceTest {

    private WordFrequencyService wfService = new WordFrequencyService();

    private Gson gson = new Gson();
    private final String testDirectory = "./src/test/java/com/wordanalyze/demo/";
    private PropertiesLoader propLoader = PropertiesLoader.getInstance();

    @Test
    public void insertAndDeleteFileTest() throws Exception{
        String pattern = "MMddyyyyHHmmss";
        DateFormat df = new SimpleDateFormat(pattern);
        Date now = Calendar.getInstance().getTime();
        String sdf = df.format(now);
        String newFileName = propLoader.getDataLocation() + sdf +".txt";
        String originalFileName = testDirectory + "test.txt";//
        File file = new File(originalFileName);
        Files.copy(file.toPath(), new File(newFileName).toPath());

        Result res = wfService.analyzeFrequency(sdf + ".txt",
                "test.txt", true);
        assert (res.getOriginalFileName().equals("test.txt"));
        assert (res.getNewFileName().equals(sdf + ".txt"));
        wfService.deleteFile(res.getNewFileName());

    }

    @Test
    public void getFileTest() throws Exception{
        String pattern = "MMddyyyyHHmmss";
        DateFormat df = new SimpleDateFormat(pattern);
        Date now = Calendar.getInstance().getTime();
        String sdf = df.format(now);
        String newFileName = propLoader.getDataLocation() + sdf +".txt";
        String originalFileName = testDirectory + "test.txt";//
        File file = new File(originalFileName);
        Files.copy(file.toPath(), new File(newFileName).toPath());

        Result res = wfService.analyzeFrequency(sdf + ".txt",
                "test.txt", true);
        Result r = wfService.getResultByName(sdf);
        wfService.deleteAll();
        assert(r.getNewFileName().equals(sdf + ".txt"));
    }

    @Test
    public void analyzeMultipleFilesTest() throws Exception{
        String pattern = "MMddyyyyHHmmss";
        DateFormat df = new SimpleDateFormat(pattern);
        Date now = Calendar.getInstance().getTime();
        String sdf = df.format(now);
        String newFileName = propLoader.getDataLocation() + sdf +".txt";
        String originalFileName = testDirectory + "test.txt";//
        File file = new File(originalFileName);
        Files.copy(file.toPath(), new File(newFileName).toPath());

        Result res = wfService.analyzeFrequency(sdf + ".txt",
                "test.txt", true);
        Result r = wfService.getResultByName(sdf);
        assert(r.getNewFileName().equals(sdf + ".txt"));

        TimeUnit.SECONDS.sleep(1); // by the time

        now = Calendar.getInstance().getTime();
        sdf = df.format(now);
        String newFileName2 = propLoader.getDataLocation() + sdf +".txt";
        String originalFileName2 = testDirectory + "test2.txt";
        File file2 = new File(originalFileName2);
        Files.copy(file2.toPath(), new File(newFileName2).toPath());

        Result res2 = wfService.analyzeFrequency(sdf + ".txt",
                "test2.txt", true);
        Result r2 = wfService.getResultByName(sdf);
        assert(r2.getNewFileName().equals(sdf + ".txt"));
        System.out.println(wfService.getResults().get(0).getOriginalFileName());
        System.out.println(wfService.getResults().get(1).getOriginalFileName());
        wfService.deleteAll();
    }

    /**
     * Insert 10 of the same file. The 11th insert will replace the one at
     * the very top referenced by results.get(0);
     * @throws Exception
     */
    @Test
    public void analyzeAutoUpdateResultsList() throws Exception{
        String newFileName = "00000000000001";
        String originalFileName = testDirectory + "test.txt";
        File file = new File(originalFileName);

        for(int i = 0; i< 10; i++){
            System.out.println(i);
            Files.copy(file.toPath(), new File(propLoader.getDataLocation() + newFileName + ".txt").toPath());
            wfService.analyzeFrequency(newFileName + ".txt",
                    "test.txt", true);
            newFileName = newFileName.substring(1, newFileName.length()) + "1";
        }

        String originalFileName2 = testDirectory + "test2.txt";
        File file2 = new File(originalFileName2);
        Files.copy(file2.toPath(), new File(propLoader.getDataLocation() + newFileName + ".txt").toPath());
        wfService.analyzeFrequency(newFileName + ".txt",
                "test2.txt", true);
        assert(wfService.getResults().size()==10);
        assert(wfService.getResults().get(0).getOriginalFileName().equals("test2.txt"));

        wfService.deleteAll();
    }
}
