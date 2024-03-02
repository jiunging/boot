package com.coding404.myweb.command;

import java.time.LocalDateTime;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TopicVO {
	
	private Integer topic_num;
	private String topic_enddate;
	private String topic_id;
	private String topic_title;
	private String topic_comment;
	
	@Override
	public String toString() {
		return "TopicVO [topic_num=" + topic_num + ", topic_enddate=" + topic_enddate + ", topic_id=" + topic_id
				+ ", topic_title=" + topic_title + ", topic_comment=" + topic_comment + "]";
	}
	
	

}
