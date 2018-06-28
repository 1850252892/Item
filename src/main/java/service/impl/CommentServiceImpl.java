package service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import mapper.CommentMapper;
import po.Comment;
import service.ICommentService;

@Service
public class CommentServiceImpl implements ICommentService {

	@Autowired
	private CommentMapper cm;

	@Override
	public int addComment(Comment c) {
		return cm.insert(c);
	}

	@Override
	public List<Comment> findComment(String gid) {
		return cm.find(gid);
	}

}
