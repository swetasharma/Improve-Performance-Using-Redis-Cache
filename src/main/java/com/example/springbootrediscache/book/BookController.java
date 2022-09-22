package com.example.springbootrediscache.book;

import java.time.Duration;
import java.time.Instant;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {
	@RequestMapping( path = "book/{name}", method = RequestMethod.GET )
	@Caching(
		      cacheable = {
		            @Cacheable(cacheNames = "GET_BOOK_DETAILS_BY_NAME", key = "#name + '_' + #country", condition = "#country != null"),
		            @Cacheable(cacheNames = "GET_BOOK_DETAILS_BY_NAME", key = "#name", condition = "#country == null")
		      }
		    )
	public Book bookDetail( @PathVariable String name,  @RequestParam(required = false) String country )
	{
		return null;
	}
	
	@RequestMapping( path = "books", method = RequestMethod.GET )
	@Cacheable(cacheNames = "GET_LIST_OF_BOOKS")
	public BookList listCatalogs()
	{
		Instant  startTime = Instant.now();
		Instant endTime = Instant.now();
		Duration  differenceInTime = Duration.between(startTime, endTime);
		return null;
	}
}
