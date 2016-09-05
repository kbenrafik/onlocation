package ma.onlocation.dao.impl;

import java.util.List;

import javax.transaction.Transactional;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ma.onlocation.dao.CommentDAO;
import ma.onlocation.models.Comment;
import ma.onlocation.models.Location;

@Transactional
@Repository
public class CommentDAOImpl implements CommentDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void addComment(Comment comment) {
		sessionFactory.getCurrentSession().persist(comment);
		System.out.println("Comment saved successfully, Comment Details="
				+ comment);
	}

	@Override
	public void updateComment(Comment comment) {
		sessionFactory.getCurrentSession().update(comment);
		System.out.println("Comment updated successfully, Comment Details="
				+ comment);

	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Comment> listComment() {
		List<Comment> commentList = sessionFactory.getCurrentSession()
				.createCriteria(Location.class).list();
		return commentList;

	}

	@Override
	public Comment getCommentById(Integer id) {
		Comment comment = (Comment) sessionFactory.getCurrentSession().load(
				Comment.class, new Integer(id));
		System.out.println(comment.getId());
		System.out.println("Comment loaded successfully, Comment details="
				+ comment);
		return comment;
	}

	@Override
	public void removeComment(Integer id) {
		Comment comment = (Comment) sessionFactory.getCurrentSession().load(
				Comment.class, new Integer(id));
		if (null != comment) {
			sessionFactory.getCurrentSession().delete(comment);
		}
		System.out.println("Comment deleted successfully, Comment details="
				+ comment);
	}

	@Override
	public int getNbrOfComment(Location location) {

		String sql = "select count(*) from comment where location_id="
				+ location.getLocationID();
		
		Integer nombreComment = ((Number)sessionFactory.getCurrentSession().createSQLQuery(sql).uniqueResult()).intValue();
		return nombreComment;
	}
	


}
