package ImageHoster.controller;

import ImageHoster.model.Comment;
import ImageHoster.model.User;
import ImageHoster.service.CommentService;
import ImageHoster.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;

@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private ImageService imageService;

    @RequestMapping(value = "/image/{imageId}/{title}/comments", method = RequestMethod.POST)
    public String createComment(@PathVariable("imageId") Integer imageId, @PathVariable("title") String title, @RequestParam("comment") String text, HttpSession session) {
        User user = (User)session.getAttribute("loggeduser");
        Comment comment = new Comment();
        comment.setImage(imageService.getImage(imageId));
        comment.setText(text);
        comment.setCreatedDate(LocalDate.now());
        comment.setUser(user);
        commentService.insertComment(comment);

        return "redirect:/images/" + imageId + "/" + title;
    }

}
