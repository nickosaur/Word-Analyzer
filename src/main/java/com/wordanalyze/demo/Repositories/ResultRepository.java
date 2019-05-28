package com.wordanalyze.demo.Repositories;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wordanalyze.demo.POJO.Result;
import com.wordanalyze.demo.POJO.Word;
import com.wordanalyze.demo.POJO.WordComparator;
import com.wordanalyze.demo.Utilities.Stemmer;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

@Repository
public class ResultRepository {
    private List<Result> results;
    private HashMap<String, Word> frequencyMap;
    private HashSet<String> stopWordsList;
    private Properties prop;
    private String resultsLocation;

    public ResultRepository(){

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
     * @return Result - the result object that has been analyzed
     *///TODO STOPWORD
    public Result analyzeText(String fileName, boolean excludeStopWords){

        results = new ArrayList<>();
        frequencyMap = new HashMap<>();
        // fileName is already validated in the controller
        File file = new File(fileName);
        Stemmer stemmer = new Stemmer();
        Result res = new Result();
        Scanner scanner;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e){
            System.err.println(file + " not found in analyzeTest()");
            return res;
        }
        res.setFileName(fileName);
        res.setStopSetting(excludeStopWords);
        while(scanner.hasNext()){
            String s = scanner.next();

            /**
             * If string contains numbers or special characters, we will not
             * be stemming them.
             *
             * Example : 10, it's, her's, Adam's
             * The regex removes all punctuations except those within words
             */
            s = s.toLowerCase();
            s = s.replaceAll("[\\p{P}&&[^']]|(?<![a-zA-Z])'|'(?![a-zA-Z])"
                    ,"");
            if(excludeStopWords){
                if(stopWordsList.contains(s)) {
                    continue;
                }
            }
            if (!s.matches("[a-zA-Z]+")){
                updateFrequencyMap(s,s);
            } else {
                stemmer.setOriginalWord(s);
                String key = stemmer.Stem();
                updateFrequencyMap(key, s);
            }
        }
        PriorityQueue<Word> wordPQ = new PriorityQueue<>(new WordComparator());
        for (Map.Entry<String, Word> entry : frequencyMap.entrySet()){
            //System.out.println(entry.getKey());
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
}
