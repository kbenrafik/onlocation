package ma.onlocation.services;

import java.util.List;

import ma.onlocation.dao.CommentDAO;
import ma.onlocation.models.Comment;
import ma.onlocation.models.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

	@Autowired
	private CommentDAO commentDAO;

	public void addComment(Comment comment) {
		commentDAO.addComment(comment);
	}

	public List<Comment> listComment() {
		return commentDAO.listComment();
	}

	public void updateComment(Comment comment) {
		commentDAO.updateComment(comment);
	}

	public Comment getCommentById(Integer id) {
		return commentDAO.getCommentById(id);
	}

	public void removeComment(Integer id) {
		commentDAO.removeComment(id);
	}
	public int getNbrOfComment(Location location) {
		return commentDAO.getNbrOfComment(location);
	}
}
