package top._1manity.chatroomserver.handler;

import com.alibaba.fastjson2.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.BinaryMessage;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;
import top._1manity.chatroomserver.manager.WebSocketSessionManager;
import top._1manity.chatroomserver.message.ChatMessage;
import top._1manity.chatroomserver.service.MyWebSocketService;

import java.net.InetSocketAddress;
import java.time.LocalDateTime;

/**
 * ws消息处理类
 */
@Component
@Slf4j
public class MyWebSocketHandler extends AbstractWebSocketHandler {

    @Autowired
    MyWebSocketService wsService;
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        log.info("建立ws连接");
        WebSocketSessionManager.add(session.getId(),session);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        log.info("发送文本消息");
        // 获得客户端传来的消息
        String payload = message.getPayload();
        String clientIp = session.getRemoteAddress().getAddress().getHostAddress();

        log.info("server 接收到消息 " + payload);

        ChatMessage getData=JSON.parseObject(payload,ChatMessage.class);
        ChatMessage data = new ChatMessage("chat",getData.getContent(),clientIp,String.valueOf(System.currentTimeMillis()));;
        String text = JSON.toJSONString(data);
        wsService.broadcastMsg(text);

    }

    @Override
    protected void handleBinaryMessage(WebSocketSession session, BinaryMessage message) throws Exception {
        log.info("发送二进制消息");
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        log.error("异常处理");
        WebSocketSessionManager.removeAndClose(session.getId());
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        log.info("关闭ws连接");
        WebSocketSessionManager.removeAndClose(session.getId());
    }
}
