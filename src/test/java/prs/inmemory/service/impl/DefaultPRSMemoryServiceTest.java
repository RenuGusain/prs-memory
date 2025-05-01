package prs.inmemory.service.impl;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import prs.inmemory.config.CommandExecutorRegistry;
import prs.inmemory.executor.api.CommandExecutor;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class DefaultPRSMemoryServiceTest {
    private CommandExecutorRegistry registry;
    private DefaultPRSMemoryService service;

    @BeforeEach
    void setUp() {
        registry = mock(CommandExecutorRegistry.class);
        service = new DefaultPRSMemoryService(registry);

    }
@Test
  void test_EmptyCommand()
  {
     String actual= service.runCommand(new String[]{});
      assertEquals("Empty command",actual);

  }
  @Test
  void test_UnknowCommand()
  {
      String actual=service.runCommand(new String[]{"TEST"});
      assertEquals("Unknown command",actual);

  }
  @Test
    void test_SetCommand()
  {
      CommandExecutor mockExecutor = mock(CommandExecutor.class);
      when(registry.resolve("SET")).thenReturn(mockExecutor);
      when(mockExecutor.execute(new String []{"SET","name","Renu"})).thenReturn("OK");
      String actual=service.runCommand(new String []{"SET","name","Renu"});
      assertEquals(actual,"OK");
  }
}