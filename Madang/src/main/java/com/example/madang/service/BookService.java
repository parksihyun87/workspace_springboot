package com.example.madang.service;

import com.example.madang.data.dao.BookDAO;
import com.example.madang.data.dto.BookDTO;
import com.example.madang.data.dto.OrderInfoDTO;
import com.example.madang.data.entity.BookEntity;
import com.example.madang.data.entity.OrderEntity;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookDAO bookDAO;

    public BookDTO getBookInfo(Integer id){
        BookEntity bookEntity= this.bookDAO.getBookInfo(id);
        if(bookEntity==null){
            throw new EntityNotFoundException("존재하지 않는 책의 아이디");
        }
        List<OrderInfoDTO> orderInfoList = new ArrayList<>();

        for(OrderEntity orderEntity: bookEntity.getOrderstbls()){
            OrderInfoDTO orderInfoDTO = OrderInfoDTO.builder()
                    .orderid(orderEntity.getId())
                    .bookName(orderEntity.getBook().getBookname())
                    .custName(orderEntity.getCust().getName())
                    .salePrice(orderEntity.getSaleprice())
                    .orderDate(orderEntity.getOrderdate())
                    .build();
            orderInfoList.add(orderInfoDTO);
            // 이 아이디 레코드에 대해서 북의 오더스에서 해당 주문내역 과져와서 필요한 내용만 오더스에 빌드
        }
        BookDTO bookDTO = BookDTO.builder()
                .id(bookEntity.getId())
                .bookname(bookEntity.getBookname())
                .publisher(bookEntity.getPublisher())
                .price(bookEntity.getPrice())
                .orders(orderInfoList).build();
            // 그걸 북 디티오에 다 욱여넣고, 또 entity로 표시해도 프론트에는 꼭 필요한 정보만 넣음.
            // 결론: 기존의 북정보인데, 아이디랑 연관한 정보이기에 아이디를 변수로 받으면 해당 북 정보만 전달이 되고, DTO의 변형으로
            // 해당 오더스에 대한 정보도 알맞게 넣었기에(별다른 조인없이도 잘 가져옴) 쓸 만한 정보를 한번에 넘기는 과정이 됨.

        return bookDTO;
    }
}


