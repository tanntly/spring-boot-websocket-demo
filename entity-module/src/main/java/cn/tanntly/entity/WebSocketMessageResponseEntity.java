package cn.tanntly.entity;

import lombok.Data;

@Data
public class WebSocketMessageResponseEntity {

    public WebSocketMessageResponseEntity() {
    }

    public WebSocketMessageResponseEntity(String content) {
        this.content = content;
    }

    private String content;

}
