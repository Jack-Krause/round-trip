package jackRoundTrip.test.Repository;


import jackRoundTrip.test.Model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {}
