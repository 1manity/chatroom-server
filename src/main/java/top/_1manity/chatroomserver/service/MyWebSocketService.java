package top._1manity.chatroomserver.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import top._1manity.chatroomserver.manager.WebSocketSessionManager;

import java.io.IOException;

/**
 * ws操作相关服务
 */
@Service
@Slf4j

public class MyWebSocketService {

    /**
     * 发送消息
     * @param session
     * @param text
     * @return
     * @throws IOException
     */
    public void sendMsg(WebSocketSession session, String text) throws IOException {
        session.sendMessage(new TextMessage(text));
    }

    /**
     * 广播消息
     * @param text
     * @return
     * @throws IOException
     */
    public void broadcastMsg(String text) throws IOException {
        for (WebSocketSession session: WebSocketSessionManager.SESSION_POOL.values()) {
//            log.info("send to client");
            session.sendMessage(new TextMessage(text));
        }
    }

}
