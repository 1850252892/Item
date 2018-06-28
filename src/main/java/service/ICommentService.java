package service;

import java.util.List;

import po.Comment;

public interface ICommentService {

	/**
	 *
	 * @Description: 添加一条评论
	 *
	 * @param c 评论数据
	 * @return 操作结果
	 *
	 * @version: v1.0.0
	 * @author: PSY
	 * @date: 2018/6/28 15:34
	 *
	 */
	int addComment(Comment c);

	/**
	 *
	 * @Description: 获取商品的评论列表
	 *
	 * @param gid 商品id
	 * @return 评论列表
	 *
	 * @version: v1.0.0
	 * @author: PSY
	 * @date: 2018/6/28 15:34
	 *
	 */
	List<Comment> findComment(String gid);
}
