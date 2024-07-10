package com.example.appcachingused.mappers;

import com.example.appcachingused.dto.PostDTO;
import com.example.appcachingused.entity.Post;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface PostMapper {
    Post toEntity(PostDTO postDTO);

    PostDTO toDto(Post post);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Post partialUpdate(PostDTO postDTO, @MappingTarget Post post);
}