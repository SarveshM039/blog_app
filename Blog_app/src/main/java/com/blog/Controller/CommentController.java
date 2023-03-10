package com.blog.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.Payloads.APiResponse;
import com.blog.Payloads.CommentDto;
import com.blog.Services.CommentService;

@RestController
@RequestMapping("/api")
public class CommentController {
	
	@Autowired
     private CommentService commentService;
	
	@PostMapping("/post/{postId}/comments")
	public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentDto, @PathVariable Integer postId) {
		CommentDto createComment = this.commentService.createComment(commentDto, postId);
		
		return new ResponseEntity<CommentDto>(createComment, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/comments/{commentId}")
	public ResponseEntity<APiResponse> deleteComment(@PathVariable Integer commentId) {
		this.commentService.deletedComment(commentId);
		return new ResponseEntity<APiResponse>(new APiResponse("Comment is deleted Successfully!!", true), HttpStatus.OK);
	}

}
