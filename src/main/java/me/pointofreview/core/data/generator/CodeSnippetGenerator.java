package me.pointofreview.core.data.generator;

import me.pointofreview.core.objects.*;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class CodeSnippetGenerator {

    private static Random rand = new Random();

    /**
     * Adds generated code snippets to mongo DB.
     *
     * @param amount        how many code snippets to generate
     * @param mongoTemplate database object
     */
    public static void generateToDB(int amount, MongoTemplate mongoTemplate) {
        for (int i = 0; i < amount; i++)
            mongoTemplate.insert(generate());
    }

    /**
     * Adds generated code snippets that are linked to a specific user to mongo DB.
     *
     * @param amount        how many code snippets to generate
     * @param mongoTemplate database object
     * @param userId        the code snippets will be linked with this userId
     */
    public static void generateWithUserIDtoDB(int amount, MongoTemplate mongoTemplate, String userId) {
        for (int i = 0; i < amount; i++) {
            CodeSnippet snippet = generate();
            snippet.setUserId(userId);
            mongoTemplate.insert(snippet);
        }
    }

    /**
     * @param amount how many users to generate
     * @return list of users
     */
    public static List<CodeSnippet> generateMany(int amount) {
        List<CodeSnippet> snippets = new ArrayList<>();
        for (int i = 0; i < amount; i++)
            snippets.add(generate());
        return snippets;
    }

    /**
     * @return a generated code snippet
     */
    public static CodeSnippet generate() {
        String snipperId = UUID.randomUUID().toString();
        long timestamp = GeneratorUtil.getTimestamp();
        String userId = UUID.randomUUID().toString();
        String title = generateTitle();
        String description = generateDescription();
        Code code = generateCode();
        List<CodeReview> reviews = new ArrayList<>();
        List<Tag> tags = generateTags();
        Score score = new Score();

        return new CodeSnippet(snipperId, timestamp, userId, title, description, code, reviews, tags, score);
    }

    private static String generateTitle() {
        String[] titles = new String[]{
                "Null pointer exception",
                "I am very stupid",
                "Problem with array",
                "LinkedList implementation",
                "My first code",
                "My second coode",
                "My program",
        };

        return titles[rand.nextInt(titles.length)];
    }

    private static String generateDescription() {
        String[] titles = new String[]{
                "Null pointer exception",
                "I am very stupid",
                "Problem with array",
                "LinkedList implementation",
                "My first code",
                "My second coode",
                "My program",
        };

        return titles[rand.nextInt(titles.length)];
    }

    private static Code generateCode() {
        String cwd = System.getProperty("user.dir");
        File file =
                new File(cwd + "\\src\\main\\java\\me\\pointofreview\\core\\data\\generator\\codes_for_generator.txt");
        Scanner sc = null;
        try {
            sc = new Scanner(file);
        } catch (FileNotFoundException e) {
        }

        sc.useDelimiter("~");

        Code[] codes = new Code[4];

        for (int i = 0; i < 4; i++) {
            String language = sc.next();
            String text = sc.next();
            Code code = new Code(text, language);
            codes[i] = code;
        }

        return codes[rand.nextInt(codes.length)];
    }

    private static List<Tag> generateTags() {
        List<Tag> tags = new ArrayList<>();
        tags.add(TagGenerator.generateWith("language"));

        int total = 2 + rand.nextInt(3); // a number between 2 to 4
        for (int i = 0; i < total; i++)
            tags.add(TagGenerator.generateWithout("language"));

        return tags;
    }


}
