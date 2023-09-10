package top._1manity.chatroomserver.job;

import com.alibaba.fastjson2.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import top._1manity.chatroomserver.message.ChatMessage;
import top._1manity.chatroomserver.service.MyWebSocketService;

import java.io.IOException;
import java.time.LocalDateTime;

/**
 * 消息生成job
 */
@Slf4j
@Component
public class MessageJob {
    @Autowired
    MyWebSocketService wsService;

    /**
     * 每5s发送
     */
//    @Scheduled(cron = "0/5 * * * * *")
    public void run(){
        try {
            ChatMessage data = new ChatMessage("chat","test","server",String.valueOf(System.currentTimeMillis()));
            String text = JSON.toJSONString(data);
            wsService.broadcastMsg(text);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
