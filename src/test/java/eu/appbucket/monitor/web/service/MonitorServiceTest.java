package eu.appbucket.monitor.web.service;

import eu.appbucket.monitor.shared.queue.MessageHandler;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Before;
import org.junit.Test;

public class MonitorServiceTest {

    private MonitorService service;
    private final Mockery context = new JUnit4Mockery();
    private MessageHandler handlerMock;

    @Before
    public void setup() {
        handlerMock = context.mock(MessageHandler.class);
        service = new MonitorService() {
            @Override
            protected MessageHandler newMessageHandler() {
                return handlerMock;
            }
        };
    }

    @Test
    public void testSendDataUpdates() {
        context.checking(new Expectations() {
            {
                exactly(1).of(handlerMock).receiverMessageFromQueue();
            }
        });
        service.sendDataUpdates();
    }
}