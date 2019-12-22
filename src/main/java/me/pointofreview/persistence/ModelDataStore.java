package me.pointofreview.persistence;

import me.pointofreview.core.datafilter.CodeSnippetsFilter;
import me.pointofreview.core.objects.CodeReview;
import me.pointofreview.core.objects.CodeSnippet;
import me.pointofreview.core.objects.Comment;

import java.util.List;

public interface ModelDataStore {

    List<CodeSnippet> getCodeSnippets(CodeSnippetsFilter filter);

    CodeSnippet getCodeSnippet(String snippetId);

    List<CodeSnippet> getCodeSnippetsByUserId(String userId);

    boolean createCodeSnippet(CodeSnippet snippet);

    boolean createComment(Comment comment);

    boolean createCodeReview(CodeReview review);
}
