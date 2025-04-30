package prs.inmemory.network.protocol.api;

import java.io.IOException;
import java.io.InputStream;

public interface CommandParser {
    String parse(InputStream input) throws IOException;
}