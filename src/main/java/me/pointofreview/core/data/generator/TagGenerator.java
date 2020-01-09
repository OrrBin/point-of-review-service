package me.pointofreview.core.data.generator;

import me.pointofreview.core.objects.Tag;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TagGenerator {

    private static Random rand = new Random();

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
        Tag[] tags = new Tag[]{
                new Tag("python", "language"),
                new Tag("java", "language"),
                new Tag("C#", "language"),
                new Tag("javascript", "language"),
                new Tag("sorting", "algorithm"),
                new Tag("GCD", "algorithm"),
                new Tag("binary search", "algorithm"),
                new Tag("BFS", "algorithm"),
                new Tag("Dijkstra", "algorithm"),
                new Tag("memory", "feedback"),
                new Tag("complexity", "feedback"),
                new Tag("design", "feedback"),
                new Tag("modularity", "feedback"),
        };

        return tags[rand.nextInt(tags.length)];
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