package erik.munk;

import erik.munk.cmdapps.BigFileLineReplacer;

public class App {

  public static void main(String[] args) throws Exception {
    BigFileLineReplacer app = new BigFileLineReplacer(args);
    app.run();
  }
}
