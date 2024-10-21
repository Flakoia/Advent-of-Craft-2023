package blog;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ArticleTests {
    public static final String COMMENT_TEXT = "Amazing article !!!";
    public static final String COMMENT_AUTHOR = "Pablo Escobar";
    private Article article;

    @BeforeEach
    public void createArticleWithComment() throws CommentAlreadyExistException {
        article = new Article(
                "Lorem Ipsum",
                "consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore"
        );

        article.addComment(COMMENT_TEXT, COMMENT_AUTHOR);
    }

    @Test
    void it_should_add_a_comment_in_an_article() {
               assertThat(article.getComments())
                .hasSize(1)
                .anyMatch(comment -> comment.text().equals(COMMENT_TEXT))
                .anyMatch(comment -> comment.author().equals(COMMENT_AUTHOR));
    }

    @Test
    void it_should_add_different_comments_in_an_article() throws CommentAlreadyExistException {
        article.addComment("It's magic !", "Harry Potter");

        assertThat(article.getComments())
                .hasSize(2)
                .anyMatch(comment -> comment.text().equals(COMMENT_TEXT))
                .anyMatch(comment -> comment.text().equals("It's magic !"))
                .anyMatch(comment -> comment.author().equals(COMMENT_AUTHOR))
                .anyMatch(comment -> comment.author().equals("Harry Potter"));
    }

    @Test
    void it_should_throw_an_exception_when_adding_existing_comment() {
        assertThatThrownBy(() -> {
            article.addComment(COMMENT_TEXT, COMMENT_AUTHOR);
        }).isInstanceOf(CommentAlreadyExistException.class);
    }
}
