package com.blog.Services;

import com.blog.Payloads.CommentDto;

public interface CommentService {

	
	CommentDto createComment(CommentDto commentDto, Integer postId);
	
	void deletedComment(Integer commentId);
}
