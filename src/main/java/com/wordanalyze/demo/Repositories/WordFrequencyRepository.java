package com.wordanalyze.demo.Repositories;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.wordanalyze.demo.POJO.Result;
import com.wordanalyze.demo.Utilities.ResultComparator;
import com.wordanalyze.demo.POJO.Word;
import com.wordanalyze.demo.Utilities.WordComparator;
import com.wordanalyze.demo.Utilities.PropertiesLoader;
import com.wordanalyze.demo.Utilities.Stemmer;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

@Repository
public class WordFrequencyRepository {
    private List<Result> results;
    private HashMap<String, Word> frequencyMap;
    private HashSet<String> stopWordsList;
    private String pathToResultsList;
    private String pathToData;

    public WordFrequencyRepository(){
        PropertiesLoader propLoader = PropertiesLoader.getInstance();
        pathToResultsList = propLoader.getResultsFile();
        pathToData = propLoader.getDataLocation();
        updateResultsList();
        initializeStopWords();
    }

    /**
     * Helper method to initialize all of the stopWords
     */
    private void initializeStopWords(){
        stopWordsList = new HashSet<>();
        stopWordsList.add("a");
        stopWordsList.add("about");
        stopWordsList.add("above");
        stopWordsList.add("after");
        stopWordsList.add("again");
        stopWordsList.add("against");
        stopWordsList.add("all");
        stopWordsList.add("am");
        stopWordsList.add("an");
        stopWordsList.add("and");
        stopWordsList.add("any");
        stopWordsList.add("are");
        stopWordsList.add("aren't");
        stopWordsList.add("as");
        stopWordsList.add("at");
        stopWordsList.add("be");
        stopWordsList.add("because");
        stopWordsList.add("been");
        stopWordsList.add("before");
        stopWordsList.add("being");
        stopWordsList.add("below");
        stopWordsList.add("between");
        stopWordsList.add("both");
        stopWordsList.add("but");
        stopWordsList.add("by");
        stopWordsList.add("can't");
        stopWordsList.add("cannot");
        stopWordsList.add("could");
        stopWordsList.add("couldn't");
        stopWordsList.add("did");
        stopWordsList.add("didn't");
        stopWordsList.add("do");
        stopWordsList.add("does");
        stopWordsList.add("doesn't");
        stopWordsList.add("doing");
        stopWordsList.add("don't");
        stopWordsList.add("down");
        stopWordsList.add("during");
        stopWordsList.add("each");
        stopWordsList.add("few");
        stopWordsList.add("for");
        stopWordsList.add("from");
        stopWordsList.add("further");
        stopWordsList.add("had");
        stopWordsList.add("hadn't");
        stopWordsList.add("has");
        stopWordsList.add("hasn't");
        stopWordsList.add("have");
        stopWordsList.add("haven't");
        stopWordsList.add("having");
        stopWordsList.add("he");
        stopWordsList.add("he'd");
        stopWordsList.add("he'll");
        stopWordsList.add("he's");
        stopWordsList.add("her");
        stopWordsList.add("here");
        stopWordsList.add("here's");
        stopWordsList.add("hers");
        stopWordsList.add("herself");
        stopWordsList.add("him");
        stopWordsList.add("himself");
        stopWordsList.add("his");
        stopWordsList.add("how");
        stopWordsList.add("how's");
        stopWordsList.add("i");
        stopWordsList.add("i'd");
        stopWordsList.add("i'll");
        stopWordsList.add("i'm");
        stopWordsList.add("i've");
        stopWordsList.add("if");
        stopWordsList.add("in");
        stopWordsList.add("into");
        stopWordsList.add("is");
        stopWordsList.add("isn't");
        stopWordsList.add("it");
        stopWordsList.add("it's");
        stopWordsList.add("its");
        stopWordsList.add("itself");
        stopWordsList.add("let's");
        stopWordsList.add("me");
        stopWordsList.add("more");
        stopWordsList.add("most");
        stopWordsList.add("mustn't");
        stopWordsList.add("my");
        stopWordsList.add("myself");
        stopWordsList.add("no");
        stopWordsList.add("nor");
        stopWordsList.add("not");
        stopWordsList.add("of");
        stopWordsList.add("off");
        stopWordsList.add("on");
        stopWordsList.add("once");
        stopWordsList.add("only");
        stopWordsList.add("or");
        stopWordsList.add("other");
        stopWordsList.add("ought");
        stopWordsList.add("our");
        stopWordsList.add("ours");
        stopWordsList.add("ourselves");
        stopWordsList.add("out");
        stopWordsList.add("over");
        stopWordsList.add("own");
        stopWordsList.add("same");
        stopWordsList.add("shan't");
        stopWordsList.add("she");
        stopWordsList.add("she'd");
        stopWordsList.add("she'll");
        stopWordsList.add("she's");
        stopWordsList.add("should");
        stopWordsList.add("shouldn't");
        stopWordsList.add("so");
        stopWordsList.add("some");
        stopWordsList.add("such");
        stopWordsList.add("than");
        stopWordsList.add("that");
        stopWordsList.add("that's");
        stopWordsList.add("the");
        stopWordsList.add("their");
        stopWordsList.add("theirs");
        stopWordsList.add("them");
        stopWordsList.add("themselves");
        stopWordsList.add("then");
        stopWordsList.add("there");
        stopWordsList.add("there's");
        stopWordsList.add("these");
        stopWordsList.add("they");
        stopWordsList.add("they'd");
        stopWordsList.add("they'll");
        stopWordsList.add("they're");
        stopWordsList.add("they've");
        stopWordsList.add("this");
        stopWordsList.add("those");
        stopWordsList.add("through");
        stopWordsList.add("to");
        stopWordsList.add("too");
        stopWordsList.add("under");
        stopWordsList.add("until");
        stopWordsList.add("up");
        stopWordsList.add("very");
        stopWordsList.add("was");
        stopWordsList.add("wasn't");
        stopWordsList.add("we");
        stopWordsList.add("we'd");
        stopWordsList.add("we'll");
        stopWordsList.add("we're");
        stopWordsList.add("we've");
        stopWordsList.add("were");
        stopWordsList.add("weren't");
        stopWordsList.add("what");
        stopWordsList.add("what's");
        stopWordsList.add("when");
        stopWordsList.add("when's");
        stopWordsList.add("where");
        stopWordsList.add("where's");
        stopWordsList.add("which");
        stopWordsList.add("while");
        stopWordsList.add("who");
        stopWordsList.add("who's");
        stopWordsList.add("whom");
        stopWordsList.add("why");
        stopWordsList.add("why's");
        stopWordsList.add("with");
        stopWordsList.add("won't");
        stopWordsList.add("would");
        stopWordsList.add("wouldn't");
        stopWordsList.add("you");
        stopWordsList.add("you'd");
        stopWordsList.add("you'll");
        stopWordsList.add("you're");
        stopWordsList.add("you've");
        stopWordsList.add("your");
        stopWordsList.add("yours");
        stopWordsList.add("yourself");
        stopWordsList.add("yourselves");
    }

    /**
     * Helper method to update the Frequency Map
     * @param key - either a stemmed word or a word containing numbers
     * @param entry - the proper form of the word to update <Key, Word> obj
     */
    private void updateFrequencyMap(String key, String entry){
        if (frequencyMap.containsKey(key)){
            Word word = frequencyMap.get(key);
            word.updateWord(entry);
        }
        else {
            Word word = new Word();
            word.updateWord(entry);
            frequencyMap.put(key, word);
        }
    }

    /**
     * This method takes in fileName, parses and analyze it
     * Validation of fileName will happen in the controller
     * @param fileName - directory of file to be analyzed
     * @param excludeStopWords - if true, filter stopWords
     * @return Result - the result object that has been analyzed
     */
    public Result analyzeText(String fileName, boolean excludeStopWords){

        frequencyMap = new HashMap<>();
        // fileName is already validated in the controller
        File file = new File(fileName);
        Stemmer stemmer = new Stemmer();
        Result res = new Result();
        Scanner scanner;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e){
            e.printStackTrace();
            return null;
        }
        res.setStopSetting(excludeStopWords);
        while(scanner.hasNext()){
            String s = scanner.next();

            /**
             * If string contains numbers or special characters, we will not
             * be stemming them.
             *
             * Example : 10, it's, her's, Adam's
             * The regex removes all punctuations except those within words.
             * This is to prevent stemming of words like
             *      he'll -> hell or she's -> shes
             */
            s = s.toLowerCase().replaceAll("[\\p{P}&&[^']]|(?<![a-z])'|'(?![a-z])"
                    ,"");

            if (s.length() == 0){
                continue;
            }

            if(excludeStopWords){
                if(stopWordsList.contains(s)) {
                    continue;
                }
            }
            if (!s.matches("[a-z]+")){
                updateFrequencyMap(s,s);
            } else {
                stemmer.setOriginalWord(s);
                String key = stemmer.Stem();
                updateFrequencyMap(key, s);
            }
        }
        PriorityQueue<Word> wordPQ = new PriorityQueue<>(new WordComparator());
        for (Map.Entry<String, Word> entry : frequencyMap.entrySet()){
            wordPQ.add(entry.getValue());
        }

        for (int i = 0; i < 10; i++){
            if (!wordPQ.isEmpty()) {
                Word word = wordPQ.remove();
                res.insertToWordList(word);
            }
            else{
                break;
            }
        }
        return res;
    }

    /**
     * This method takes in a Result object and updates the json File
     * and results with the newly added Result object
     * @param result - the newly added object that has been analyzed
     * @return
     */
    public boolean updateResultsList(Result result){
        JsonReader reader;
        try{
            reader = new JsonReader(new FileReader(pathToResultsList));
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }

        Type typeList = new TypeToken<List<Result>>() {}.getType();
        Gson gson = new Gson();
        List<Result> resultsList = gson.fromJson(reader, typeList);
        resultsList.add(result);
        Collections.sort(resultsList, new ResultComparator());
        while(resultsList.size() >= 10){
            Result toDelete = resultsList.remove(resultsList.size() - 1);
            try{
                Files.deleteIfExists(Paths.get(pathToData +
                        toDelete.getNewFileName() + ".txt"));
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        String jsonResult = gson.toJson(resultsList);
        try {
            FileWriter writer = new FileWriter(pathToResultsList);
            writer.write(jsonResult);
            writer.close();
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }

        this.results = resultsList;
        return true;
    }

    /**
     * This method is called only once during app startup when
     * WordFrequencyRepository is initialized. Reads
     * the results.json file and populate the List<Results>
     */
    public void updateResultsList(){
        JsonReader reader;
        try{
            reader = new JsonReader(new FileReader(pathToResultsList));
        } catch (Exception e){
            e.printStackTrace();
            return;
        }

        Type typeList = new TypeToken<List<Result>>() {}.getType();
        Gson gson = new Gson();
        results = gson.fromJson(reader, typeList);
    }

    /**
     * This method is called in the WordFrequencyService class.
     * It serves the post request called in the controller to
     * analyze the post-ed file and adds the results into the
     * results.json file.
     * @param newFileName - the name of the file in /data expressed as its timestamp
     * @param originalFileName - the name of the file when the user uploaded it
     * @param stopWordSetting - exclude stop words in analysis if true,
     *                          otherwise if false
     * @return the Result object used to render the view after post request
     */
    public Result analyzeAndUpdate(String newFileName,
                                     String originalFileName,
                                     boolean stopWordSetting){

        Result toInsert = analyzeText(pathToData + newFileName, stopWordSetting);
        if (toInsert == null){
            System.err.println("Error creating file");
            return null;
        }
        toInsert.setNewFileName(newFileName);
        toInsert.setOriginalFileName(originalFileName);
        if (updateResultsList(toInsert)){
            return toInsert;
        }
        return null;
    }

    /**
     * Called for GET requests to display a list of past results
     * @return the list of past results
     */
    public List<Result> getResults(){
        return this.results;
    }

    /**
     * Called for GET request to display the previous or current
     * result based on their new Name, i.e., the time stamp
     * @return Result object requested
     */
    public Result getResultByName(String name){
        for(Result res : results){
            String resNewFileName = res.getNewFileName();
            /**
             * ddMMyyyyHHmmss.txt -> ddMMyyyyHHmmss
             */
            if(resNewFileName.substring(0, resNewFileName.length()-4).equals(name)){
                return res;
            }
        }
        return null;
    }

    /** TODO
     * Delete file referenced by Result
     * @param res - file to be deleted
     */
    public void deleteFileFromDiskAndList(Result res){
        if(res == null){
            return;
        }

        try{
            if (results.contains(res)){

            }

        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
