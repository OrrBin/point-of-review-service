package me.pointofreview.core.data.generator;

import me.pointofreview.core.objects.Tag;

import java.util.*;

public class TagGenerator {

    private static Random rand = new Random();

    public static List<Tag> getLanguageTags() {
        Tag[] tags = new Tag[]{
                new Tag("python", "language"),
                new Tag("Java", "language"),
                new Tag("C#", "language"),
                new Tag("JavaScript", "language"),
                new Tag("TypeScript", "language")
        };

        return Arrays.asList(tags);
    }

    public static List<Tag> getSnippetTags(){
        Tag[] tags = new Tag[]{
                new Tag("sorting", "algorithm"),
                new Tag("GCD", "algorithm"),
                new Tag("binary search", "algorithm"),
                new Tag("BFS", "algorithm"),
                new Tag("Dijkstra", "algorithm"),
                new Tag("memory", "feedback"),
                new Tag("complexity", "feedback"),
                new Tag("design", "feedback"),
                new Tag("modularity", "feedback")
        };

        return Arrays.asList(tags);
    }
    /**
     * Generates a list of tags.
     * @param amount how many tags to generate
     */
    public static List<Tag> generateMany(int amount) {
        List<Tag> tags = new ArrayList<>();
        for (int i = 0; i < amount; i++)
            tags.add(generate());
        return tags;
    }

    /**
     * Generates a random tag.
     */
    public static Tag generate() {
        List<Tag> tags = getSnippetTags();
        return tags.get(rand.nextInt(tags.size()));
    }

    /**
     * Generate a tag that is not of a certain type.
     * @param exclude the tag type to exclude
     * @return a tag of a type that is not exclude if exists one, null otherwise
     */
    public static Tag generateWithout(String exclude) {
        for (int i = 0; i < 10000; i++) {
            Tag tag = generate();
            if (!(tag.getType().equals(exclude)))
                return tag;
        }

        return null;
    }

    /**
     * Generate a tag that is of a specific type.
     * @param type the tag type to generate
     * @return a tag of this type if exists, null otherwise
     */
    public static Tag generateWith(String type) {
        for (int i = 0; i < 10000; i++) {
            Tag tag = generate();
            if (tag.getType().equals(type))
                return tag;
        }

        return null;
    }


}