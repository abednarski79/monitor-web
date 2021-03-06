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

@Component
public class MonitorService {

    private static final Logger logger = LoggerFactory.getLogger(MonitorService.class);
    private MessageHandler handler;

    private SimpMessagingTemplate brokerMessagingTemplate;

    @Autowired
    public void setBrokerMessagingTemplate(SimpMessagingTemplate brokerMessagingTemplate) {
        this.brokerMessagingTemplate = brokerMessagingTemplate;
    }

    public MonitorService() {
        logger.info("Initializing connection to the queue in app root.");
        handler = newMessageHandler();
    }

    protected MessageHandler newMessageHandler() {
        return new MessageHandlerImpl();
    }

    @Scheduled(fixedDelay = 1000)
    public void sendDataUpdates() {
        logger.info("Checking the queue...");
        Message messageFromQueue = handler.receiverMessageFromQueue();
        if(messageFromQueue != null) {
            this.brokerMessagingTemplate.convertAndSend("/topic/message", messageFromQueue);
        }
    }
}
