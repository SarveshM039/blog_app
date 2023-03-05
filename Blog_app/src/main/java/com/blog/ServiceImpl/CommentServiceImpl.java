package com.blog.ServiceImpl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.Entities.Comment;
import com.blog.Entities.Post;
import com.blog.Exceptions.ResourceNotFoundException;
import com.blog.Payloads.CommentDto;
import com.blog.Repositories.CommentRepo;
import com.blog.Repositories.PostRepo;
import com.blog.Services.CommentService;
@Service
public class CommentServiceImpl  implements CommentService{

	@Autowired
	private CommentRepo commentrepo;
	
	@Autowired
	private ModelMapper modelmapper;
	
	@Autowired
	private PostRepo postRepo;
	
	@Override
	public CommentDto createComment(CommentDto commentDto, Integer postId) {
		Post post = this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "post Id", postId));
		  Comment comment = this.modelmapper.map(commentDto, Comment.class);
		  comment.setPost(post);
		  Comment saveedComment = this.commentrepo.save(comment);
		return this.modelmapper.map(saveedComment, CommentDto.class);
	}

	@Override
	public void deletedComment(Integer commentId) {
		 Comment com= this.commentrepo.findById(commentId).orElseThrow(()->new ResourceNotFoundException("Comment", "CommentId", commentId));
		this.commentrepo.delete(com);
	}

}
