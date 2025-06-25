package com.pol.rev;

import com.pol.rev.service.RpnCalculator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        if (args.length != 1) {
            logger.error("Usage: java -jar rev-pol-not-cal-1.0.jar <input_file>");
        }
        Path path = Path.of(args[0]);
        try (var lines = Files.lines(path)) {
            lines.forEach(line -> {
                try {
                    double result = RpnCalculator.evaluate(line);
                    logger.info("{} = {}", line, result);
                } catch (IllegalArgumentException e) {
                    logger.warn("{} - {}", line, e.getMessage());
                }
            });
        } catch (IOException e) {
            logger.error("Error reading file '{}': {}", path, e.getMessage());
        }
    }
}