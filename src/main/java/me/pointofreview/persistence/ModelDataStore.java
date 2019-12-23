package me.pointofreview.persistence;

import me.pointofreview.core.data.filter.CodeSnippetsFilter;
import me.pointofreview.core.objects.CodeReview;
import me.pointofreview.core.objects.CodeSnippet;
import me.pointofreview.core.objects.Comment;

import java.util.List;

public interface ModelDataStore {

    /**
     * Get list of code snippets that satisfy a condition.
     * @param filter {@link CodeSnippetsFilter} object with the conditions
     * @return the list of code snippets
     */
    List<CodeSnippet> getCodeSnippets(CodeSnippetsFilter filter);

    /**
     * Get code snippet by snippet ID
     * @param snippetId the snippet ID
     * @return CodeSnippet if exists one with the ID, null otherwise
     */
    CodeSnippet getCodeSnippet(String snippetId);

    /**
     * Get list of code snippets which belongs to the user with the ID
     * @param userId the user ID
     * @return list of code snippets, may be empty
     */
    List<CodeSnippet> getCodeSnippetsByUserId(String userId);

    /**
     * Creates a new code snippet
     * @param snippet the code snippet
     * @return true if the creation was successful, and false otherwise
     */
    boolean createCodeSnippet(CodeSnippet snippet);

    /**
     * Creates a new comment
     * @param comment the comment
     * @return true if the creation was successful, and false otherwise
     */
    boolean createComment(Comment comment);

    /**
     * Add a code review to an existing snippet, if exists
     * @param review the code review to add, also holds the snippet ID
     * @return true if the insertion was successful, and false otherwise
     */
    boolean addCodeReview(CodeReview review);
}
