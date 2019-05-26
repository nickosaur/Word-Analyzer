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
    private HashMap<String, String> irregularVerbs; //
    public Stemmer(){
        initializeIrregularVerbs();
    }

    /**
     * this method initializes 50 most common irregular verbs
     * and their base form
     * Preserves base form words such that if the base form exists
     * in the hashMap then it will not go through stemming
     */
    private void initializeIrregularVerbs(){
        irregularVerbs = new HashMap<>();
        irregularVerbs.put("said", "say");
        irregularVerbs.put("say", "say");
        irregularVerbs.put("made", "make");
        irregularVerbs.put("make", "make");
        irregularVerbs.put("went", "go");
        irregularVerbs.put("gone", "go");
        irregularVerbs.put("go", "go");
        irregularVerbs.put("took", "take");
        irregularVerbs.put("taken", "take");
        irregularVerbs.put("take", "take");
        irregularVerbs.put("came", "come");
        irregularVerbs.put("come", "come");
        irregularVerbs.put("saw", "see");
        irregularVerbs.put("see", "see");
        irregularVerbs.put("seen", "see");
        irregularVerbs.put("knew", "know");
        irregularVerbs.put("known", "know");
        irregularVerbs.put("know", "know");
        irregularVerbs.put("got", "get");
        irregularVerbs.put("gotten", "get");
        irregularVerbs.put("get", "get");
        irregularVerbs.put("gave", "give");
        irregularVerbs.put("given", "give");
        irregularVerbs.put("give", "give");
        irregularVerbs.put("found", "find");
        irregularVerbs.put("find", "find");
        irregularVerbs.put("thought", "think");
        irregularVerbs.put("think", "think");
        irregularVerbs.put("told", "tell");
        irregularVerbs.put("tell", "tell");
        irregularVerbs.put("became", "become");
        irregularVerbs.put("become", "become");
        irregularVerbs.put("showed", "show");
        irregularVerbs.put("shown", "show");
        irregularVerbs.put("show", "show");
        irregularVerbs.put("left", "leave");
        irregularVerbs.put("leave", "leave");
        irregularVerbs.put("felt", "feel");
        irregularVerbs.put("feel", "feel");
        irregularVerbs.put("brought", "bring");
        irregularVerbs.put("bring", "bring");
        irregularVerbs.put("began", "begin");
        irregularVerbs.put("begun", "begin");
        irregularVerbs.put("begin", "begin");
        irregularVerbs.put("kept", "keep");
        irregularVerbs.put("keep", "keep");
        irregularVerbs.put("held", "hold");
        irregularVerbs.put("hold", "hold");
        irregularVerbs.put("wrote", "write");
        irregularVerbs.put("written", "write");
        irregularVerbs.put("write", "write");
        irregularVerbs.put("stood", "stand");
        irregularVerbs.put("heard", "hear");
        irregularVerbs.put("hear", "hear");
        irregularVerbs.put("meant", "mean");
        irregularVerbs.put("mean", "mean");
        irregularVerbs.put("met", "meet");
        irregularVerbs.put("meet", "meet");
        irregularVerbs.put("ran", "run");
        irregularVerbs.put("run", "run");
        irregularVerbs.put("paid", "pay");
        irregularVerbs.put("pay", "pay");
        irregularVerbs.put("sat", "sit");
        irregularVerbs.put("sit", "sit");
        irregularVerbs.put("spoke", "speak");
        irregularVerbs.put("spoken", "speak");
        irregularVerbs.put("speak", "speak");
        irregularVerbs.put("lied", "lie");
        irregularVerbs.put("lie", "lie");
        irregularVerbs.put("led", "lead");
        irregularVerbs.put("lead", "lead");
        irregularVerbs.put("grew", "grow");
        irregularVerbs.put("grown", "grow");
        irregularVerbs.put("grow", "grow");
        irregularVerbs.put("lost", "lose");
        irregularVerbs.put("lose", "lose");
        irregularVerbs.put("fell", "fall");
        irregularVerbs.put("fallen", "fall");
        irregularVerbs.put("fall", "fall");
        irregularVerbs.put("sent", "send");
        irregularVerbs.put("send", "send");
        irregularVerbs.put("built", "build");
        irregularVerbs.put("build", "build");
        irregularVerbs.put("understood", "understand");
        irregularVerbs.put("understand", "understand");
        irregularVerbs.put("drew", "draw");
        irregularVerbs.put("drawn", "draw");
        irregularVerbs.put("draw", "draw");
        irregularVerbs.put("spent", "spend");
        irregularVerbs.put("spend", "spend");
        irregularVerbs.put("risen", "rise");
        irregularVerbs.put("rose", "rise");
        irregularVerbs.put("rise", "rise");
        irregularVerbs.put("driven", "drive");
        irregularVerbs.put("drove", "drive");
        irregularVerbs.put("drive", "drive");
        irregularVerbs.put("bought", "buy");
        irregularVerbs.put("buy", "buy");
        irregularVerbs.put("worn", "wear");
        irregularVerbs.put("wore", "wear");
        irregularVerbs.put("wear", "wear");
        irregularVerbs.put("chose", "choose");
        irregularVerbs.put("chosen", "choose");
        irregularVerbs.put("choose", "choose");
        irregularVerbs.put("ate", "eat");
        irregularVerbs.put("eat", "eat");
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

        if (word == null){
            return -1;
        }

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
                    i += 2;
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
                        !word.endsWith("l") ||
                                !word.endsWith("s") ||
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
            if(containsVowel(word)){
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
                            (stem.endsWith("S") || stem.endsWith("T"))
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
        if(word.endsWith("e")){
            word = changeWordwithSuffix(word, "e", "", 1);
        }

        //
        if (word.endsWith("e")){
            if (countM(word) == 1 && (!endsWithCVC(word))){
                word = replaceLastNWith(1, word, "");
            }
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
        String word = this.getOriginalWord();
        if(!isValidString(word)){
            return word;
        }

        if (irregularVerbs.containsKey(word)){
            return irregularVerbs.get(word);
        }

        word = Step1(word);
        word = Step2(word);
        word = Step3(word);
        word = Step4(word);
        word = Step5(word);
        return word;
    }
}
