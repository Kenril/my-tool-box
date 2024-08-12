package erik.munk.cmdapps;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.time.Duration;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public class BigFileLineReplacer {

  private static final Option FILE_OPTION = Option.builder().required(true).option("f").longOpt("file").hasArg(true)
    .desc("Absolute or relative path to file that should be processed").build();

  private static final Option OUTPUT_OPTION = Option.builder().required(true).option("o").longOpt("output").hasArg(true)
    .desc("Where to send output, 'SYSTEM' send to std.out OR file name will output replacement to file relative to app execution directory")
    .build();

  private static final Option PATTERN_OPTION = Option.builder().required(true).option("p").longOpt("pattern").hasArg(true)
    .desc("Pattern to match in the file, follows regex conventions").build();

  private static final Option REPLACE_OPTION = Option.builder().required(true).option("r").longOpt("replace").hasArg(true)
    .desc("String or pattern to use to replace the pattern (p), follows regex conventions").build();

  private static final Option LOG_FILE_OPTION = Option.builder().required(false).option("l").longOpt("log").hasArg(true)
    .desc("Output log to this file").build();

  private static final Option VERBOSE_OPTION = Option.builder().required(false).option("v").longOpt("verbose").hasArg(false)
    .desc("Make the process log more things").build();

  private final DateTimeFormatter formater;

  private final File input;

  private final String findPattern;

  private final String replacePattern;

  private final String output;

  private final BufferedWriter fileWriter;

  private final BufferedWriter logWriter;

  private final boolean verbose;

  public BigFileLineReplacer(String[] args) throws ParseException, IOException {
    super();

    formater = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssZZZ").withZone(ZoneId.systemDefault());

    Options options = new Options();
    options.addOption(FILE_OPTION);
    options.addOption(PATTERN_OPTION);
    options.addOption(REPLACE_OPTION);
    options.addOption(VERBOSE_OPTION);
    options.addOption(OUTPUT_OPTION);
    options.addOption(LOG_FILE_OPTION);

    CommandLineParser parser = new DefaultParser();
    CommandLine cmd = parser.parse(options, args);

    verbose = cmd.hasOption(VERBOSE_OPTION);

    String logFile = cmd.getOptionValue(LOG_FILE_OPTION);
    if (logFile != null && !logFile.isEmpty()) {
      logWriter = new BufferedWriter(new FileWriter(logFile));
    } else {
      logWriter = new BufferedWriter(new OutputStreamWriter(System.out));
    }
    log("Starting application");
    logV("Found log file option " + logFile);

    logV("Init args");
    input = getFile(cmd.getOptionValue(FILE_OPTION));
    logV("Found input file " + input.getName());

    findPattern = cmd.getOptionValue(PATTERN_OPTION);
    logV("Found pattern string " + findPattern);

    replacePattern = cmd.getOptionValue(REPLACE_OPTION);
    logV("Found replace string " + replacePattern);

    output = cmd.getOptionValue(OUTPUT_OPTION);
    logV("Found output option " + output);
    if ("SYSTEM".equalsIgnoreCase(output)) {
      fileWriter = new BufferedWriter(new OutputStreamWriter(System.out));
    } else {
      fileWriter = new BufferedWriter(new FileWriter(output));
    }
  }

  public void run() throws IOException {
    Instant startTime = Instant.now();
    List<Duration> snapDurations = new ArrayList<>();
    try (BufferedReader br = new BufferedReader(new FileReader(this.input))) {
      log("Starting processing...");
      int processed = 0;
      String line = br.readLine();
      Instant snapStart = startTime;
      Instant snapEnd;
      while (line != null) {
        logV("Reading : " + line);
        String newLine = line.replaceFirst(this.findPattern, this.replacePattern);
        logV("Replaced to " + newLine);
        write(newLine);
        logV("Wrote line to file " + output);
        processed++;
        if (processed % 30_000 == 0) {
          snapEnd = Instant.now();
          Duration snapDuration = Duration.between(snapStart, snapEnd);
          log("Processed " + processed + " lines in " + Duration.between(startTime, snapEnd) + "...");
          snapDurations.add(snapDuration);
          snapStart = Instant.now();
        }
        line = br.readLine();
      }
    }
    Instant finishTime = Instant.now();
    Long totalRuntime = snapDurations.stream().map(Duration::getSeconds).reduce(Long::sum).orElse(0L);

    log("Spend " + Duration.ofSeconds(Long.divideUnsigned(totalRuntime, snapDurations.size())) + " on avg to process 30_000 lignes");
    log("Finished process in " + Duration.between(startTime, finishTime).toString());

    logWriter.flush();
    fileWriter.flush();
  }

  private File getFile(String path) {
    File f = new File(path);
    if (f.exists()) {
      return f;
    }
    throw new IllegalArgumentException("File " + path + " doesn't exist");
  }

  private void write(String newLine) throws IOException {
    fileWriter.write(newLine);
    fileWriter.newLine();
    fileWriter.flush();
  }

  private void log(String msg) throws IOException {
    logWriter.write(formater.format(Instant.now()) + " - " + msg);
    logWriter.newLine();
    logWriter.flush();
  }

  private void logV(String msg) throws IOException {
    if (verbose) {
      log(msg);
    }
  }

}
