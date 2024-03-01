package com.coding404.myweb.command;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductVO {
	private Integer prod_id;
	private LocalDateTime prod_regdate;
	private String prod_enddate;
	private String prod_category;
	private String prod_writer;
	private String prod_name;
	private Integer prod_price;
	private Integer prod_count;
	private Integer prod_discount;
	private String prod_purchase_yn;
	private String prod_content;
	private String prod_comment;
	@Override
	public String toString() {
		return "ProductVO [prod_id=" + prod_id + ", prod_regdate=" + prod_regdate + ", prod_enddate=" + prod_enddate
				+ ", prod_category=" + prod_category + ", prod_writer=" + prod_writer + ", prod_name=" + prod_name
				+ ", prod_price=" + prod_price + ", prod_count=" + prod_count + ", prod_discount=" + prod_discount
				+ ", prod_purchase_yn=" + prod_purchase_yn + ", prod_content=" + prod_content + ", prod_comment="
				+ prod_comment + "]";
	}
	
	
}


