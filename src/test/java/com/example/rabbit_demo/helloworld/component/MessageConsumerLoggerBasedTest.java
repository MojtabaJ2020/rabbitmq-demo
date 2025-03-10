package com.example.rabbit_demo.helloworld.component;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.read.ListAppender;
import org.awaitility.Awaitility;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith (MockitoExtension.class) public class MessageConsumerLoggerBasedTest
{
  
  private Logger logger;
  private ListAppender <ILoggingEvent> listAppender;
  @Mock private NotificationService notificationService;
  @InjectMocks private MessageConsumer messageConsumer;
  
  @BeforeEach void setUp ()
  {
    logger = (Logger) LoggerFactory.getLogger (MessageConsumer.class);
    listAppender = new ListAppender <> ();
    listAppender.start ();
    logger.addAppender (listAppender);
  }
  
  @AfterEach void tearDown ()
  {
    logger.detachAppender (listAppender);
  }
  
  @Test void testConsumerLogsMessage ()
  {
    String testMessage = "Test Message !!";
    String expectedResult = "Received a message: " + testMessage;
    messageConsumer.receiveMessage (testMessage);
    
    Awaitility.await ().atMost (5, TimeUnit.SECONDS).until (() -> listAppender.list.stream ()
                            .anyMatch (event -> event.getFormattedMessage ()
                                                     .contains (expectedResult)));

    boolean logFound = listAppender.list.stream ().anyMatch (event -> event.getFormattedMessage ().contains (expectedResult));
    assertTrue (logFound, "Expected log message not found");
  }
}
