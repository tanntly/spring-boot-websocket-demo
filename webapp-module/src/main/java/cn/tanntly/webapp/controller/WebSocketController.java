package cn.tanntly.webapp.controller;

import cn.tanntly.entity.WebSocketMessageRequestEntity;
import cn.tanntly.entity.WebSocketMessageResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

@Controller
public class WebSocketController {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/request")
    @SendTo("/topic/response")
    public WebSocketMessageResponseEntity mass(WebSocketMessageRequestEntity message) throws Exception {
        return new WebSocketMessageResponseEntity("Hello, " + HtmlUtils.htmlEscape(message.getContent()) + "!");
    }

    @MessageMapping("/alone/request")
    public WebSocketMessageResponseEntity alone(WebSocketMessageRequestEntity message) throws Exception {
        WebSocketMessageResponseEntity webSocketMessageResponseEntity =
                new WebSocketMessageResponseEntity("Hi, " + HtmlUtils.htmlEscape(message.getContent()) + "!");

        simpMessagingTemplate.convertAndSendToUser("testuser1","/alone/response",webSocketMessageResponseEntity);

        return webSocketMessageResponseEntity;
    }

}
