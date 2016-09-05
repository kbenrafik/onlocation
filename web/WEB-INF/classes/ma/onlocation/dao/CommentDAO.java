package ma.onlocation.dao;

import java.util.List;

import ma.onlocation.models.Comment;
import ma.onlocation.models.Location;

public interface CommentDAO {

	public void addComment(Comment comment);

	public void updateComment(Comment comment);

	public List<Comment> listComment();

	public Comment getCommentById(Integer id);

	public void removeComment(Integer id);
	
	public int getNbrOfComment(Location location);
}
