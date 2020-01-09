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
     * update code-review section's impressions, if user already voted with the same impression - nothing is changed
     * @param codeReviewSectionId is the relevant section id
     * @param userId is the Id of the voting user
     * @param impression is the voting user impression (currently: like/dislike/report)
     * @return true if the section exists, and false otherwise
     */
    Score updateCodeReviewSectionImpressions(String snippetId,String codeReviewId, String codeReviewSectionId, String userId, Impression impression);

    /**
     * update code-snippet's impressions, if user already voted with the same impression - nothing is changed
     * @param snippet is the relevant snippet
     * @param userId is the Id of the voting user
     * @param impression is the voting user impression (currently: like/dislike/report)
     * @return true if the snippet exists, and false otherwise
     */
    boolean updateCodeSnippetImpressions(CodeSnippet snippet, String userId, Impression impression);

    }
