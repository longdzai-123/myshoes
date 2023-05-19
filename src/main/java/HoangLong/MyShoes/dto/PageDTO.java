package HoangLong.MyShoes.dto;

import java.util.List;

import lombok.Data;

@Data
public class PageDTO<T> {
	
    private int totalPages; // tong so trang 
    private long totalElements; // tong so ban ghi 
    private List<T> contents;
}
