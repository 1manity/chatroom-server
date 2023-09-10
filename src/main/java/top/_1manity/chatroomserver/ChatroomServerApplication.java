package top._1manity.chatroomserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ChatroomServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChatroomServerApplication.class, args);
    }

}
