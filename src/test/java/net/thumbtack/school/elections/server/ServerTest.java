package net.thumbtack.school.elections.server;

import org.apache.commons.io.FileUtils;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import static org.junit.Assert.*;

public class ServerTest {
    @Test
    public void startAndStopServer() {
        try {
            Server s = new Server();
            s.startServer(null);
            assertEquals("start", s.getName());
            s.setState(new StringBuilder("12345"));
            s.stopServer("stop1");
            s.startServer("stop1");
            s.stopServer("stop2");
            assertTrue(FileUtils.contentEquals(new File("stop1"), new File("stop2")));
        } catch (IOException ex) {
            fail();
        }
    }
}