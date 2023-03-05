package com.blog.Controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.Payloads.APIiResponse;
import com.blog.Payloads.CategoryDto;
import com.blog.Services.CategoryService;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
	
	@Autowired
	private CategoryService CategoryService;

	//create
	
	@PostMapping("/")
	public ResponseEntity<CategoryDto> createCategory(@Valid  @RequestBody CategoryDto categoryDto){
		CategoryDto createCategory = this.CategoryService.createCategory(categoryDto);
		
		return new ResponseEntity<CategoryDto>(createCategory,HttpStatus.CREATED);
	}
	
	//update
	@PutMapping("/{catId}")
	public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto, @PathVariable Integer catId){
		CategoryDto updateCategory = this.CategoryService.updateCategory(categoryDto, catId);
		
		return new ResponseEntity<CategoryDto>(updateCategory,HttpStatus.OK);
	}
	
	//delete
	
	@DeleteMapping("/{catId}")
	public ResponseEntity<APIiResponse> deleteCategory(@PathVariable Integer catId){
	          this.CategoryService.deleteCategory(catId);
		  return new ResponseEntity<APIiResponse>(new APIiResponse("category is deleted successfully !!", true),HttpStatus.OK);
	}
	
	//get
	
	@GetMapping("/{catId}")
	public ResponseEntity<CategoryDto> getCategory(@PathVariable Integer catId){
        CategoryDto categoryDto = this.CategoryService.getCategory(catId);
	  return new ResponseEntity<CategoryDto>(categoryDto ,HttpStatus.OK);
}
	
	//getAll
	@GetMapping("/")
	public ResponseEntity<List<CategoryDto>> getCategories(){
         List<CategoryDto> categories = this.CategoryService.getCategories();
         return  ResponseEntity.ok(categories);
}
	
	
}
