package com.ac.web;

import com.ac.domain.Comment;
import com.ac.domain.CommentRepository;
import com.ac.domain.User;
import com.ac.util.HttpSessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * Created by Jbee on 2016. 12. 11..
 */
@Controller
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private CommentRepository commentRepository;

    @PostMapping("/{id}")
    public String createComment(@PathVariable int id, Comment comment, HttpSession session) {
        User loginUser = HttpSessionUtils.getUserFromSession(session);
        commentRepository.insertComment(comment, loginUser.getId(), id);
        return "redirect:/articles/" + id;
    }
}
