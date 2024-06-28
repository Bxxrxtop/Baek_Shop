package com.example.baekshop.Domain.Item.Service;


import com.example.baekshop.Domain.Item.Dto.CreateItemRequestDto;
import com.example.baekshop.Domain.Item.Dto.ItemResponseDto;
import com.example.baekshop.Domain.Item.Dto.UpdateItemRequestDto;
import com.example.baekshop.Domain.Item.Entity.Item;
import com.example.baekshop.Domain.Item.Repository.ItemRepository;
import com.example.baekshop.Domain.User.Entity.User;
import com.example.baekshop.Domain.User.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class ItemService {
    private final ItemRepository itemRepository;
    private final UserRepository userRepository;

    public void saveItem(CreateItemRequestDto createItemReqDto, String email){
        Item item = createItemReqDto.toEntity();  // dto -> entity로 변환
        Optional<User> user = userRepository.findByEmail(email);
        if(user.isPresent()){
            User findUser = user.get();
            String name = findUser.getName();
            item.setUserName(name);      // 로그인한 유저의 name 같이 저장.
        }
        log.info("[Item Service] 상품 등록 이름 ---> {}", createItemReqDto.getName());

        itemRepository.save(item);
    }

    @Transactional(readOnly = true)
    public ItemResponseDto getItem(Long itemId) {
        try {
            // db에서 orderId에 맞는 order 가져오기
            Optional<Item> item = itemRepository.findById(itemId);
            if (item.isPresent()) {
                Item findItem = item.get();
                // entity -> dto
                return ItemResponseDto.from(findItem);

            } else {
                throw new NoSuchElementException("상품을 찾을 수 없습니다: " + itemId);
            }
        } catch (Exception e) {
            log.error("상품 정보 가져오는 중 오류 발생 ---> {}", e.getMessage());
            throw new RuntimeException("상품을 가져오는 중 오류 발생", e);
        }
    }


    public void updateOrder(Long itemId, UpdateItemRequestDto reqdto){
        // repository findById 같은 메소드로 db에서 가져오기
        try{
            Optional<Item> item = itemRepository.findById(itemId);
            if(item.isPresent()){
                Item findItem = item.get();
                findItem.update(reqdto);
                itemRepository.save(findItem);
            }

        } catch (Exception e){
            log.error("[Item Service] 해당 Id 상품 가져오기 실패 ---> {}", itemId);
        }
    }

    public void deleteOrder(Long itemId){
        // repository에서 delete 메소드
        try{
            Optional<Item> item = itemRepository.findById(itemId);
            if(item.isPresent()){
                Item findItem= item.get();
                itemRepository.deleteById(itemId);
            }
        } catch (Exception e){
            log.error("[Item Service] 해당 Id 상품 삭제 실패 ---> {}", itemId);
        }
    }
}
