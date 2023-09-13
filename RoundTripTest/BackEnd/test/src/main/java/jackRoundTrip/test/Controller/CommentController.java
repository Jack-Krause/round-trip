package jackRoundTrip.test.Controller;

import jackRoundTrip.test.Model.Comment;
import jackRoundTrip.test.Repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommentController {
    //test

    @Autowired
    CommentRepository commentRepository;

    @GetMapping("/")
    public String index() {
        return "Greetings!!!";
    }

    @GetMapping("comment/all")
    List<Comment> GetAllComment() {
        return commentRepository.findAll();
    }

    @PostMapping("comment/post/{f}/{l}/{c}")
    Comment PostCommentByPath(@PathVariable String f, @PathVariable String l, @PathVariable String c) {
        Comment newComment = new Comment();
        newComment.setFirstName(f);
        newComment.setLastName(l);
        newComment.setComment(c);
        commentRepository.save(newComment);
        return newComment;
    }

    @PostMapping("comment/post")
    Comment PostCommentByPath(@RequestBody Comment newComment) {
        commentRepository.save(newComment);
        return newComment;
    }



}