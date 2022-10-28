package com.example.demo.mapper;

public interface IMapper<I,O> {

	public O map(I in);
}
