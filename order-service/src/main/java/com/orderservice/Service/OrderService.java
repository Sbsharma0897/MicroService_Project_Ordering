package com.orderservice.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orderservice.Dto.OrderLineItemsDto;
import com.orderservice.Dto.OrderRequest;
import com.orderservice.External.InventoryService;
import com.orderservice.Model.InventoryResponse;
import com.orderservice.Model.Order;
import com.orderservice.Model.OrderLineItems;
import com.orderservice.Repo.OrderRepository;

import antlr.debug.Tracer;
import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;
    
    @Autowired
    private InventoryService inventoryService;
    
   

    public String placeOrder(OrderRequest orderRequest) {
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());

        List<OrderLineItems> orderLineItems = orderRequest.getOrderLineItemsDtoList()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());

        order.setOrderLineItemsList(orderLineItems);
        
        List<String> skuCodes =order.getOrderLineItemsList()
        .stream()
        .map(orderLineItem-> orderLineItem.getSkuCode())
        .collect(Collectors.toList());
        
        List<InventoryResponse> inventoryResponses=inventoryService.isInStock(skuCodes);
        
        boolean allProductsInStock= inventoryResponses.stream().allMatch(inven->inven.isInStock());
        
        if(allProductsInStock)
        {
        	 orderRepository.save(order);
        }
        else
        {
		 throw new IllegalArgumentException("not in stock");	
		}
       return "Success";
        
    }

    private OrderLineItems mapToDto(OrderLineItemsDto orderLineItemsDto) {
        OrderLineItems orderLineItems = new OrderLineItems();
        orderLineItems.setPrice(orderLineItemsDto.getPrice());
        orderLineItems.setQuantity(orderLineItemsDto.getQuantity());
        orderLineItems.setSkuCode(orderLineItemsDto.getSkuCode());
        return orderLineItems;
    }
}
