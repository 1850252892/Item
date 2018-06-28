package model;

import java.util.List;

import po.Comment;
import po.Item;

public class ItemAllData {

	private Item item;
	private int collect;// 商品收藏总数
	private int rq;// 商品被请求交换的次数(即作为gid_b的数量)
	private int comment;// 商品评论总数
	private List<Comment> commentList;// 商品评论列表

	public List<Comment> getCommentList() {
		return commentList;
	}

	public void setCommentList(List<Comment> commentList) {
		this.commentList = commentList;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public int getCollect() {
		return collect;
	}

	public void setCollect(int collect) {
		this.collect = collect;
	}

	public int getRq() {
		return rq;
	}

	public void setRq(int rq) {
		this.rq = rq;
	}

	public int getComment() {
		return comment;
	}

	public void setComment(int comment) {
		this.comment = comment;
	}

}
