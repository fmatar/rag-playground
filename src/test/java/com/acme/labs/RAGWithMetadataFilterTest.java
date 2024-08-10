package com.acme.labs;

import dev.langchain4j.data.message.ChatMessage;
import dev.langchain4j.data.message.UserMessage;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@QuarkusTest
 class RAGWithMetadataFilterTest {

    @Inject
    AiService service;

    private final static AtomicReference<List<ChatMessage>> lastQuery = new AtomicReference<>();


    @Test
     void testWithUser1Filter() {
        service.chat("1", "When was Charlie born?");
        List<ChatMessage> query = lastQuery.get();
        UserMessage userMessage = (UserMessage) query.get(0);
        Assertions.assertTrue(userMessage.singleText().contains("Charlie was born in 2018."));
    }

    @Test
     void testWithUser2Filter() {
        service.chat("2", "When was Charlie born?");
        List<ChatMessage> query = lastQuery.get();
        UserMessage userMessage = (UserMessage) query.get(0);
        Assertions.assertTrue(userMessage.singleText().contains("Charlie was born in 2015."));
    }

}