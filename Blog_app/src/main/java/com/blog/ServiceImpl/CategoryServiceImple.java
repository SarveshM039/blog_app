package com.blog.ServiceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.Entities.Category;
import com.blog.Exceptions.ResourceNotFoundException;
import com.blog.Payloads.CategoryDto;
import com.blog.Repositories.CategoryRepo;
import com.blog.Services.CategoryService;

@Service
public class CategoryServiceImple implements CategoryService {

	
	@Autowired
	private CategoryRepo categoryRepo;
	
	@Autowired
	private ModelMapper modelMapper; 
	
	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
     Category cat = this.modelMapper.map(categoryDto, Category.class);
	 Category addedCat = this.categoryRepo.save(cat);
		
		return this.modelMapper.map(addedCat, CategoryDto.class);
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
		
     Category cat = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category", "Category Id", categoryId));
		
     cat.setCategoryTitle(categoryDto.getCategoryTitle());
     cat.setCategoryDescription(categoryDto.getCategoryDescription());
     
     Category Updatedcat = this.categoryRepo.save(cat);
     
		return this.modelMapper.map(Updatedcat, CategoryDto.class) ;
	}
  
	@Override
	public void deleteCategory(Integer categoryId) {
            Category cat = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category", "category id", categoryId));
	        this.categoryRepo.delete(cat);
            
	}

	@Override
	public CategoryDto getCategory(Integer categoryId) {
       Category cat = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category", "category id", categoryId));
	
		return this.modelMapper.map(cat, CategoryDto.class);
	}

	@Override
	public List<CategoryDto> getCategories() {
              List<Category> categories = this.categoryRepo.findAll() ;
              List<CategoryDto> catDtos = categories.stream().map((cat)-> this.modelMapper.map(cat, CategoryDto.class)).collect(Collectors.toList());
		
		
		return catDtos;
	}

	

}
