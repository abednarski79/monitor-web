package eu.appbucket.monitor.web.service;

import eu.appbucket.monitor.shared.pojo.Message;
import eu.appbucket.monitor.shared.queue.MessageHandler;
import eu.appbucket.monitor.shared.queue.MessageHandlerImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

// TODO : add unit test
@Component
public class MonitorService {

    private static final Logger logger = LoggerFactory.getLogger(MonitorService.class);
    private MessageHandler handler;

    @Autowired
    private SimpMessagingTemplate brokerMessagingTemplate;

    public MonitorService() {
        logger.info("Initializing connection to the queue in app root.");
        handler = new MessageHandlerImpl();
    }

    @Scheduled(fixedDelay = 1000)
    public void sendDataUpdates() {
        logger.info("Checking the queue...");
        handler = new MessageHandlerImpl();
        Message messageFromQueue = handler.receiverMessageFromQueue();
        if(messageFromQueue != null) {
            this.brokerMessagingTemplate.convertAndSend("/topic/message", messageFromQueue);
        }
    }
}
