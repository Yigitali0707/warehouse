package com.example.demo.mapper;

import com.example.demo.dto.CustomerProductDto;
import com.example.demo.entity.OrderProduct;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface CustomerProductMapper {
    OrderProduct toEntity(CustomerProductDto customerProductDto);

    CustomerProductDto toDto(OrderProduct orderProduct);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    OrderProduct partialUpdate(CustomerProductDto customerProductDto, @MappingTarget OrderProduct orderProduct);
}