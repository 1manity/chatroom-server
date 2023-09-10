package top._1manity.chatroomserver.message;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ChatMessage {
    private String type;
    private String content;
    private String sender;
    private String timestamp;

    public ChatMessage(String type, String content, String sender, String timestamp) {
        this.type = type;
        this.content = content;
        this.sender = sender;
        this.timestamp = timestamp;
    }

    // 省略构造方法、getter 和 setter
}
