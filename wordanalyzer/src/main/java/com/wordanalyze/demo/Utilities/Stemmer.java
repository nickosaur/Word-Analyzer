package com.wordanalyze.demo.Utilities;

import java.util.HashMap;
import java.util.HashSet;

/**
 * Stemmer utilizes Porter2 algorithm that can be found :
 *      https://snowballstem.org/algorithms/english/stemmer.html
 *
 * Stemmer provides a slightly more aggressive stemmer algorithm that
 * stems out irregular verbs too
 */
public class Stemmer {
    private static final char[] vowels = {'a', 'e', 'i', 'o', 'u'};
    private String originalWord;
    private HashMap<String, String> exceptionRules; //
    public Stemmer(){
        initializeExceptionRules();
    }

    /**
     * this method initializes 50 most common irregular verbs
     * and their base form
     * Preserves base form words such that if the base form exists
     * in the hashMap then it will not go through stemming
     * Also initializes a number of exceptions
     */
    private void initializeExceptionRules(){
        exceptionRules = new HashMap<>();
        exceptionRules.put("said", "say");
        exceptionRules.put("say", "say");
        exceptionRules.put("made", "make");
        exceptionRules.put("make", "make");
        exceptionRules.put("went", "go");
        exceptionRules.put("gone", "go");
        exceptionRules.put("go", "go");
        exceptionRules.put("took", "take");
        exceptionRules.put("taken", "take");
        exceptionRules.put("take", "take");
        exceptionRules.put("came", "come");
        exceptionRules.put("come", "come");
        exceptionRules.put("saw", "see");
        exceptionRules.put("see", "see");
        exceptionRules.put("seen", "see");
        exceptionRules.put("knew", "know");
        exceptionRules.put("known", "know");
        exceptionRules.put("know", "know");
        exceptionRules.put("got", "get");
        exceptionRules.put("gotten", "get");
        exceptionRules.put("get", "get");
        exceptionRules.put("gave", "give");
        exceptionRules.put("given", "give");
        exceptionRules.put("give", "give");
        exceptionRules.put("found", "find");
        exceptionRules.put("find", "find");
        exceptionRules.put("thought", "think");
        exceptionRules.put("think", "think");
        exceptionRules.put("told", "tell");
        exceptionRules.put("tell", "tell");
        exceptionRules.put("became", "become");
        exceptionRules.put("become", "become");
        exceptionRules.put("showed", "show");
        exceptionRules.put("shown", "show");
        exceptionRules.put("show", "show");
        exceptionRules.put("left", "leave");
        exceptionRules.put("leave", "leave");
        exceptionRules.put("felt", "feel");
        exceptionRules.put("feel", "feel");
        exceptionRules.put("brought", "bring");
        exceptionRules.put("bring", "bring");
        exceptionRules.put("began", "begin");
        exceptionRules.put("begun", "begin");
        exceptionRules.put("begin", "begin");
        exceptionRules.put("kept", "keep");
        exceptionRules.put("keep", "keep");
        exceptionRules.put("held", "hold");
        exceptionRules.put("hold", "hold");
        exceptionRules.put("wrote", "write");
        exceptionRules.put("written", "write");
        exceptionRules.put("write", "write");
        exceptionRules.put("stood", "stand");
        exceptionRules.put("heard", "hear");
        exceptionRules.put("hear", "hear");
        exceptionRules.put("meant", "mean");
        exceptionRules.put("mean", "mean");
        exceptionRules.put("met", "meet");
        exceptionRules.put("meet", "meet");
        exceptionRules.put("ran", "run");
        exceptionRules.put("run", "run");
        exceptionRules.put("paid", "pay");
        exceptionRules.put("pay", "pay");
        exceptionRules.put("sat", "sit");
        exceptionRules.put("sit", "sit");
        exceptionRules.put("spoke", "speak");
        exceptionRules.put("spoken", "speak");
        exceptionRules.put("speak", "speak");
        exceptionRules.put("lied", "lie");
        exceptionRules.put("lie", "lie");
        exceptionRules.put("led", "lead");
        exceptionRules.put("lead", "lead");
        exceptionRules.put("grew", "grow");
        exceptionRules.put("grown", "grow");
        exceptionRules.put("grow", "grow");
        exceptionRules.put("lost", "lose");
        exceptionRules.put("lose", "lose");
        exceptionRules.put("fell", "fall");
        exceptionRules.put("fallen", "fall");
        exceptionRules.put("fall", "fall");
        exceptionRules.put("sent", "send");
        exceptionRules.put("send", "send");
        exceptionRules.put("built", "build");
        exceptionRules.put("build", "build");
        exceptionRules.put("understood", "understand");
        exceptionRules.put("understand", "understand");
        exceptionRules.put("drew", "draw");
        exceptionRules.put("drawn", "draw");
        exceptionRules.put("draw", "draw");
        exceptionRules.put("spent", "spend");
        exceptionRules.put("spend", "spend");
        exceptionRules.put("risen", "rise");
        exceptionRules.put("rose", "rise");
        exceptionRules.put("rise", "rise");
        exceptionRules.put("driven", "drive");
        exceptionRules.put("drove", "drive");
        exceptionRules.put("drive", "drive");
        exceptionRules.put("bought", "buy");
        exceptionRules.put("buy", "buy");
        exceptionRules.put("worn", "wear");
        exceptionRules.put("wore", "wear");
        exceptionRules.put("wear", "wear");
        exceptionRules.put("chose", "choose");
        exceptionRules.put("chosen", "choose");
        exceptionRules.put("choose", "choose");
        exceptionRules.put("ate", "eat");
        exceptionRules.put("eat", "eat");
        exceptionRules.put("swum", "swim");
        exceptionRules.put("swam", "swim");
        exceptionRules.put("swim", "swim");
        exceptionRules.put("skis", "ski");
        exceptionRules.put("skies", "sky");
        exceptionRules.put("dying", "die");
        exceptionRules.put("lying", "lie");
        exceptionRules.put("tying", "tie");
        exceptionRules.put("idly", "idl");
        exceptionRules.put("gently", "gentl");
        exceptionRules.put("ugly", "ugli");
        exceptionRules.put("early", "earli");
        exceptionRules.put("only", "onli");
        exceptionRules.put("singly", "singl");
        exceptionRules.put("sky", "sky");
        exceptionRules.put("news", "news");
        exceptionRules.put("howe", "howe");
        exceptionRules.put("atlas", "atlas");
        exceptionRules.put("cosmos", "cosmos");
        exceptionRules.put("bias", "bias");
        exceptionRules.put("endes", "endes");
        exceptionRules.put("generously", "generou");
        exceptionRules.put("trying", "try");
        exceptionRules.put("tried", "try");

    }

    public void setOriginalWord(String originalWord){
        this.originalWord = originalWord;
    }

    public String getOriginalWord(){
        return this.originalWord;
    }
    /**
     * Checks if the word is not a null reference or an empty string
     * @param word - word to be checked
     * @return - true if word is not a null reference or empty string
     *         - false otherwise
     */
    private static boolean isValidString(String word){
        if (word == null){
            return false;
        }

        if (word.equals("")){
            return false;
        }

        return true;
    }

    /**
     * We consider A, E, I, O, U, and Y (in cases where
     * the letter before Y is a consonant) as vowels
     * everything else is a consonant
     * @param index - index of word to be checked
     * @param word - word to be checked
     * @return - true if word[i] is a vowel
     *         - false otherwise
     */
    private static boolean isVowelAt(int index, String word){

        if (index >= word.length()){
            return false;
        }

        for (char c : vowels){
            if (word.charAt(index) == c){
                return true;
            }
        }

        if(word.charAt(index) == 'y' && index != 0){
            return !isVowelAt(index - 1, word);
        }

        return false;
    }

    /**
     * @param index - index of word to be checked
     * @param word - word to be checked
     * @return - true if word[i] is a consonant
     *         - false otherwise
     */
    private static boolean isConsonantAt(int index, String word){

        if (index >= word.length()){
            return false;
        }

        return !isVowelAt(index, word);
    }

    /**
     * Calculates the measure, m, of a word
     * m is the measure of the word in the form
     *      [C](VC)^{m}[V]
     * where [C] and [V] are consecutive consonants/vowels
     * For example :
     * m = 0 for TR, EE, TREE, Y, BY
     * m = 1 for TROUBLE, OATS, TREES, IVY
     * m = 2 for TROUBLES, PRIVATE, OATEN, ORRERY
     * @param word - the word to be calculated
     * @return m >= 0 - the measure of word
     *         m == -1 - if word is a null reference
     */
    private static int countM(String word){

        int m = 0;
        int start = 0;
        int end = word.length();
        for (int i = 0; i < word.length(); i++){
            if (isConsonantAt(i, word)){
                start++;
            } else {
                break;
            }
        }

        for (int i = word.length() - 1; i > 0; i--){
            if (isVowelAt(i, word)){
                end--;
            } else {
                break;
            }
        }

        for(int i = start; i < end; i++){
            if(isVowelAt(i, word) && i+1 < end){
                if(isConsonantAt(i+1, word)){
                    m++;
                    i++;
                }
            }
        }

        return m;
    }

    /**
     * Checks if word contains a vowel
     * @param word - word to be checked
     * @return - true if contains a vowel
     *         - false otherwise
     */
    private static boolean containsVowel(String word){

        if(!isValidString(word)){
            return false;
        }

        for (int i = 0; i < word.length(); i++){
            if (isVowelAt(i, word)){
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if word ends with a double consonants s.t.
     * they are the same
     *      dogg -> true
     *      dogs -> false
     *      dayy -> false due to the special 'y' case
     * @param word - word to be checked
     * @return - true if it ends with 2 same consonants that are not y
     *         - false otherwise
     */
    private static boolean endsWithDoubleConsonant(String word){

        if (!isValidString(word)){
            return false;
        }

        int index = word.length() - 1;

        if (index < 1){
            return false;
        }

        if (isVowelAt(index, word)){
            return false;
        }

        if (word.charAt(index) != word.charAt(index-1)){
            return false;
        }

        return true;
    }

    /**
     * Checks if word ends with a consonant + vowel + consonant s.t.
     * the second consonant is not W, X, or Y
     *      awil -> true
     *      hop -> true
     *      how -> false
     *      and -> false
     * @param word - word to be checked
     * @return - true if ends with cons + vowel + cons s.t. cons2 != w,x,y
     *         - false otherwise
     */
    private static boolean endsWithCVC(String word){
        int index = word.length() - 1;

        if(index < 2){
            return false;
        }

        if (isConsonantAt(index, word)){
            char lastChar = word.charAt(index);
            if (lastChar != 'w' && lastChar != 'x' && lastChar != 'y'){
                index--;
                if (isVowelAt(index, word)
                        && isConsonantAt(index - 1, word)){
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Removes the last n characters from word and concatenate it with replace
     * @param n - last n characters to be replaced
     * @param word - string to be modified
     * @param replace - string to be concatenated
     * @return
     */
    private static String replaceLastNWith(int n, String word, String replace){

        if (n > word.length()){
            return replace;
        }

        if (n < 0){
            return word;
        }

        return word.substring(0,word.length() - n) + replace;
    }

    /**
     * Step1a takes care of common plural nouns and third person verbs
     * @param word - word to be modified
     * @return String word - word that is modified if applicable
     */
    private static String Step1a(String word){

        // caresses -> caress
        if (word.endsWith("sses")){
            return replaceLastNWith(4, word, "ss");
        }

        // ponies -> poni
        if (word.endsWith("ies")){
            return replaceLastNWith(3, word, "i");
        }

        // caress -> caress (no change)
        if (word.endsWith("ss")){
            return word;
        }

        // cats -> cat
        if (word.endsWith("s")){
            return replaceLastNWith(1, word, "");
        }

        return word;
    }

    /**
     *
     * Step1b takes care of common past and continuous tense words
     * @param word - word to be modified
     * @return String word - word that is modified if applicable
     *
     * feed -> feed
     * agreed -> agree
     * plastered -> plaster
     * bled -> bled
     * motoring -> motor
     * sing -> sing
     */
    private static String Step1b(String word){
        boolean needAdditionalStep = false;

        if (word.endsWith("eed")){
            String stem = replaceLastNWith(3, word, "");
            if (countM(stem) > 0){
                return replaceLastNWith(3, word, "ee");
            } else {
                return word; // feed -> feed
            }
        }

        // needs additional step for cleanup
        if (word.endsWith("ed")){
            String stem = replaceLastNWith(2, word, "");
            if(containsVowel(stem)) {
                word = stem;
            }
            needAdditionalStep = true;
        }

        // needs additional step for cleanup
        if (word.endsWith("ing")){
            String stem = replaceLastNWith(3, word, "");
            if(containsVowel(stem)){
                word = stem;
            }
            needAdditionalStep = true;
        }

        /**
         * amazingly -> amaz
         * shockingly -> shock
         */
        if (word.endsWith("ingly")){
            String stem = replaceLastNWith(5, word, "");
            if(containsVowel(stem)){
                word = stem;
            }
            needAdditionalStep = true;
        }


        if (needAdditionalStep){
            /**
             * conflat(ed) -> conflate
             * troubl(ed) -> trouble
             * siz(ed) -> size
             */
            if (
                    word.endsWith("at") ||
                            word.endsWith("bl") ||
                            word.endsWith("iz")
                    ){
                return word + "e";
            }

            /**
             * hopp(ing) -> hop
             * tann(ed) -> tan
             * fall(ing) -> fall
             * hiss(ing) -> hiss
             * fizz(ing) -> fizz
             */
            if(endsWithDoubleConsonant(word)){
                if(
                        !word.endsWith("l") &&
                        !word.endsWith("s") &&
                        !word.endsWith("z")
                        ){
                    return replaceLastNWith(1, word, "");

                }
                return word;
            }

            /**
             * fail(ing) -> fail
             * fil(ing) -> file
             */
            if(countM(word) == 1){
                if(endsWithCVC(word)){
                    return word + "e";
                }
                return word;
            }

        }
        return word;
    }

    /**
     * Step 1c deals with past participles
     * happy -> happi
     * @param word - word to be modified
     * @return String word - word that has been modified
     */
    private static String Step1c(String word){
        if(word.endsWith("y")){
            String stem = replaceLastNWith(1, word, "");
            if(containsVowel(stem)){
                return replaceLastNWith(1,word,"i");
            }
        }
        return word;
    }

    /**
     * Step 1 is a combination of Steps 1abc
     * @param word - word to be modified
     * @return String word - word that has been modified
     */
    private static String Step1(String word){

        word = Step1a(word);
        word = Step1b(word);
        word = Step1c(word);
        return word;
    }

    /**
     * Helper method for steps 2,3,and 4
     * Given that word ends with suffix, get the stem before
     * suffix and check its m value
     *
     * If m value > m provided, concatenate the new suffix to stem
     * otherwise do nothing
     *
     * @param word - word to be modified
     * @param s1 - suffix to be extracted from word
     * @param s2 - suffix to be concatenated to word
     * @param m - m to be compared
     * @return String word - word that has been altered
     */
    private static String changeWordwithSuffix(String word, String s1, String s2, int m){

        String stem = replaceLastNWith(s1.length(), word, "");
        if (countM(stem) > m){
            return stem + s2;
        }
        return word;
    }

    /**
     * Step 2 deals with longer words (examples are provided in the method)
     * @param word - word to be modified
     * @return String word - word that has been modified
     */
    private static String Step2(String word){

        // relational -> relate
        if(word.endsWith("ational")){
            return changeWordwithSuffix(word,"ational", "ate", 0);
        }

        // conditional -> condition
        // rational -> rational
        if(word.endsWith("tional")){
            return changeWordwithSuffix(word,"tional", "tion", 0);
        }

        // valenci -> valence
        if(word.endsWith("enci")){
            return changeWordwithSuffix(word,"enci", "ence", 0);
        }

        // hesitanci -> hesitance
        if(word.endsWith("anci")){
            return changeWordwithSuffix(word,"anci", "ance", 0);
        }

        // digitizer -> digitize
        if(word.endsWith("izer")){
            return changeWordwithSuffix(word,"izer", "ize", 0);
        }

        // conformabli -> conformable
        if(word.endsWith("abli")){
            return changeWordwithSuffix(word,"abli", "able", 0);
        }

        // radicalli -> radical
        if(word.endsWith("alli")){
            return changeWordwithSuffix(word,"alli", "al", 0);
        }

        // differentli -> different
        if(word.endsWith("entli")){
            return changeWordwithSuffix(word,"entli", "ent", 0);
        }

        // vileli -> vile
        if(word.endsWith("eli")){
            return changeWordwithSuffix(word,"eli", "e", 0);
        }

        // analogousli -> analogous
        if(word.endsWith("ousli")){
            return changeWordwithSuffix(word,"ousli", "ous", 0);
        }

        // vietnamization -> vietnamize
        if(word.endsWith("ization")){
            return changeWordwithSuffix(word,"ization", "ize", 0);
        }

        // predication -> predicate
        if(word.endsWith("ation")){
            return changeWordwithSuffix(word,"ation", "ate", 0);
        }

        // operator -> operate
        if(word.endsWith("ator")){
            return changeWordwithSuffix(word,"ator", "ate", 0);
        }

        // feudalism -> feudal
        if(word.endsWith("alism")) {
            return changeWordwithSuffix(word, "alism", "al", 0);
        }

        // decisiveness -> decisive
        if(word.endsWith("iveness")) {
            return changeWordwithSuffix(word, "iveness", "ive", 0);
        }

        // hopefulness -> hopeful
        if(word.endsWith("fulness")) {
            return changeWordwithSuffix(word, "fulness", "ful", 0);
        }

        // callousness -> callous
        if(word.endsWith("ousness")) {
            return changeWordwithSuffix(word, "ousness", "ous", 0);
        }

        // formaliti -> formal
        if(word.endsWith("aliti")) {
            return changeWordwithSuffix(word, "aliti", "al", 0);
        }

        // sensitiviti -> sensitive
        if(word.endsWith("iviti")) {
            return changeWordwithSuffix(word, "iviti", "ive", 0);
        }

        // sensibiliti -> sensible
        if(word.endsWith("biliti")) {
            return changeWordwithSuffix(word, "biliti", "ble", 0);
        }

        // hopefulli -> hopeful
        if(word.endsWith("fulli")){
            return changeWordwithSuffix(word, "fulli", "ful", 0);
        }

        // hopelessli -> hopeless
        if(word.endsWith("fulli")){
            return changeWordwithSuffix(word, "lessli", "less", 0);
        }
        return word;
    }

    /**
     * Step 3 truncates and removes certain suffixes
     * @param word - word to be modified
     * @return String word - word that has been modified
     */
    private static String Step3(String word){

        // triplicate -> triplic
        if(word.endsWith("icate")){
            return changeWordwithSuffix(word, "icate", "ic", 0);
        }

        // formative -> form
        if(word.endsWith("ative")){
            return changeWordwithSuffix(word, "ative", "", 0);
        }

        // formalize -> formal
        if(word.endsWith("alize")){
            return changeWordwithSuffix(word, "alize", "al", 0);
        }

        // electriciti -> electric
        if(word.endsWith("iciti")){
            return changeWordwithSuffix(word, "iciti", "ic", 0);
        }

        // electrical -> electric
        if(word.endsWith("ical")){
            return changeWordwithSuffix(word, "ical", "ic", 0);
        }

        // hopeful -> hope
        if(word.endsWith("ful")){
            return changeWordwithSuffix(word, "ful", "", 0);
        }

        // goodness -> good
        if(word.endsWith("ness")){
            return changeWordwithSuffix(word, "ness", "", 0);
        }

        return word;
    }

    /**
     * Step 4 removes the rest of the common suffixes
     * @param word - word to be modified
     * @return String word - word that has been modified
     */
    private static String Step4(String word){

        // revival -> reviv
        if(word.endsWith("al")){
            return changeWordwithSuffix(word, "al", "", 1);
        }

        // allowance -> allow
        if(word.endsWith("ance")){
            return changeWordwithSuffix(word, "ance", "", 1);
        }

        // inference -> infer
        if(word.endsWith("ence")){
            return changeWordwithSuffix(word, "ence", "", 1);
        }

        // airliner -> airlin
        if(word.endsWith("er")){
            return changeWordwithSuffix(word, "er", "", 1);
        }

        // gyroscopic -> gyroscop
        if(word.endsWith("ic")){
            return changeWordwithSuffix(word, "ic", "", 1);
        }

        // adjustable -> adjust
        if(word.endsWith("able")){
            return changeWordwithSuffix(word, "able", "", 1);
        }

        // defensible -> defens
        if(word.endsWith("ible")){
            return changeWordwithSuffix(word, "ible", "", 1);
        }

        // irritant -> irrit
        if(word.endsWith("ant")){
            return changeWordwithSuffix(word, "ant", "", 1);
        }

        // replacement -> replac
        if(word.endsWith("ement")){
            return changeWordwithSuffix(word, "ement", "", 1);
        }

        // ment
        if(word.endsWith("ment")){
            return changeWordwithSuffix(word, "ment", "", 1);
        }

        // dependent -> depend
        if(word.endsWith("ent")){
            return changeWordwithSuffix(word, "ent", "", 1);
        }

        // adoption -> adopt
        // special case
        if (word.endsWith("ion")){
            String stem = replaceLastNWith(3, word, "");
            if (
                    countM(stem) > 1 &&
                    (stem.endsWith("s") || stem.endsWith("t"))
               ){
                return stem;
            }
            else return word;
        }

        // homologou -> homolog
        if(word.endsWith("ou")){
            return changeWordwithSuffix(word, "ou", "", 1);
        }

        // communism -> commun
        if(word.endsWith("ism")){
            return changeWordwithSuffix(word, "ism", "", 1);
        }

        // activate -> activ
        if(word.endsWith("ate")){
            return changeWordwithSuffix(word, "ate", "", 1);
        }

        // angulariti -> angular
        if(word.endsWith("iti")){
            return changeWordwithSuffix(word, "iti", "", 1);
        }

        // homologous -> homolog
        if(word.endsWith("ous")){
            return changeWordwithSuffix(word, "ous", "", 1);
        }

        // effective -> effect
        if(word.endsWith("ive")){
            return changeWordwithSuffix(word, "ive", "", 1);
        }

        // bowdlerize -> bowdler
        if(word.endsWith("ize")){
            return changeWordwithSuffix(word, "ize", "", 1);
        }

        return word;
    }

    /**
     * Step 5 cleans up words
     * @param word - word to be modified
     * @return String word - word that has been modified
     */
    private static String Step5a(String word){

        // probate -> probat
        // rate -> rate
        // cease -> ceas
        if(word.endsWith("e")){
            String stem = replaceLastNWith(1, word, "");
            int m = countM(stem);
            if (m > 1){
                return stem;
            }

            if (m == 1 && !endsWithCVC(stem)){
                return stem;
            }
                return word;
        }

        return word;
    }

    private static String Step5b(String word){

        // controll -> control
        // roll -> roll
        if (
                countM(word) > 1   &&
                word.endsWith("l") &&
                endsWithDoubleConsonant(word)
           ){
            word = replaceLastNWith(1, word, "");
        }
        return word;
    }

    private static String Step5(String word){
        word = Step5a(word);
        word = Step5b(word);
        return word;
    }

    public String Stem(){
        boolean specialRule = false;
        String word = this.getOriginalWord();
        String stem = "";
        if(!isValidString(word)){
            return word;
        }

        if(exceptionRules.containsKey(word)){
            return exceptionRules.get(word);
        }

        if(word.startsWith("gener")){
            stem = "gene";
            word = word.substring(4,word.length());
            specialRule = true;
        }

        //TODO REMOVE PRINTS
        word = Step1(word);
        word = Step2(word);
        word = Step3(word);
        word = Step4(word);
        word = Step5(word);

        if (specialRule){
            word = stem + word;
        }
        return word;
    }
}
