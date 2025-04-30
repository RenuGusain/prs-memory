package prs.inmemory.network.protocol.impl;

import prs.inmemory.network.protocol.api.CommandParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class SimpleCommandParser implements CommandParser {
    @Override
    public String parse(InputStream input) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(input));
        return br.readLine();
    }
}
