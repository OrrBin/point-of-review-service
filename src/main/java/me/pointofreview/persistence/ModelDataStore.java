package me.pointofreview.persistence;

import me.pointofreview.core.data.filter.CodeSnippetsFilter;
import me.pointofreview.core.objects.*;

import java.util.List;

public interface ModelDataStore {

    /**
     * Get list of code snippets that satisfy a condition.
     *
     * @param filter {@link CodeSnippetsFilter} object with the conditions
     * @return the list of code snippets
     */
    List<CodeSnippet> getCodeSnippets(CodeSnippetsFilter filter);

    /**
     * Get code snippet by snippet ID
     *
     * @param snippetId the snippet ID
     * @return CodeSnippet if exists one with the ID, null otherwise
     */
    CodeSnippet getCodeSnippet(String snippetId);

    /**
     * Get list of code snippets which belongs to the user with the ID
     *
     * @param userId the user ID
     * @return list of code snippets, may be empty
     */
    List<CodeSnippet> getCodeSnippetsByUserId(String userId);

    /**
     * Get list of code snippets which contains a certain tag.
     * @param tagName name of the tag
     * @return list of code snippets, may be empty
     */
    List<CodeSnippet> getCodeSnippetByTag(String tagName);

    /**
     * Get list of code snippets which contains at least one tag.
     * @param tagNames list of relevant tags names
     * @return list of code snippets, may be empty
     */
    List<CodeSnippet> getCodeSnippetByTags(List<String> tagNames);

    /**
     * Creates a new code snippet
     *
     * @param snippet the code snippet
     * @return true if the creation was successful, and false otherwise
     */
    boolean createCodeSnippet(CodeSnippet snippet);

    /**
     * Creates a new comment
     *
     * @param comment the comment
     * @return true if the creation was successful, and false otherwise
     */
    boolean createComment(Comment comment);

    /**
     * Add a code review to an existing snippet, if exists
     *
     * @param review the code review to add, also holds the snippet ID
     * @return true if the insertion was successful, and false otherwise
     */
    boolean addCodeReview(CodeReview review);

    /**
     * @return list of all the code snippets
     */
    List<CodeSnippet> getAllCodeSnippets();


    /**
     * get a code review, if exists
     *
     * @param codeReviewId is the Id of the desired code review
     * @param snippetId    is the Id of the code-snippet which contains the code review
     * @return the code review if the code review was found, and null otherwise
     */
    CodeReview getCodeReview(String snippetId, String codeReviewId);


    /**
     * get a code review section, if exists
     *
     * @param sectionId    is the Id of the desired code review section
     * @param codeReviewId is the Id of the code review which contains the section
     * @param snippetId    is the code-snippet which contains the code review with the Id codeReviewId
     * @return the code review section if the code review section was found, and null otherwise
     */
    CodeReviewSection getCodeReviewSection(String snippetId, String codeReviewId, String sectionId);

//    /**
//     * update code-review section's impressions, if user already voted with the same impression - nothing is changed
//     * @param codeReviewSection is the relevant section
//     * @param userId is the Id of the voting user
//     * @param impression is the voting user impression (currently: like/dislike/report)
//     * @return true if the section exists, and false otherwise
//     */
//    boolean updateCodeReviewSectionImpressions(CodeReviewSection codeReviewSection, String userId, Impression impression);

    /**
     * update code-snippet's impressions, if user already voted with the same impression - nothing is changed
     *
     * @param codeSnippet is the relevant snippet
     * @param userId      is the Id of the voting user
     * @param impression  is the voting user impression (currently: like/dislike/report)
     * @return true if the snippet exists, and false otherwise
     */
    boolean updateCodeSnippetImpressions(CodeSnippet codeSnippet, String userId, Impression impression);
}

