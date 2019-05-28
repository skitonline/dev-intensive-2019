package net.thumbtack.school.elections.client;

import net.thumbtack.school.elections.server.Server;
import org.apache.commons.cli.*;

public class Client {
    public static void main(String[] args){
        Option option1 = new Option("l", true, "path from load server");
        Option option2 = new Option("s", true, "path to save server");
        option1.setOptionalArg(false);
        option2.setOptionalArg(false);
        Options options = new Options();
        options.addOption(option1).addOption(option2);
        CommandLineParser parser = new DefaultParser();
        try {
            CommandLine line = parser.parse( options, args );
            Server server = new Server();
            if( line.hasOption( "l" ) ) {
                server.startServer(line.getOptionValue( "l" ));
            } else {
                server.startServer(null);
            }
            //работа сервера
            if( line.hasOption( "s" ) ) {
                server.stopServer(line.getOptionValue( "s" ));
            } else {
                server.stopServer(null);
            }
        } catch (ParseException e) {}
    }
}
