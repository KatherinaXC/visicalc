public class Test {
    
    // static array of test cases (so that our static 
    // main method can access and execute  the  tests 
    
    public static Test[] tests = {

        new Test(new Step[] {
            new Step("help", VisiCalc.getHelp()),
            new Step("quit", "Goodbye." ),
        }),
            
        new Test(new Step[] {
            new Step("A1 = 1", new Result[] {
                new Result("A1", "         1"),
            }),
            new Step("A2 = 1.0", new Result[] {
                new Result("A2", "       1.0"),
            }),
            new Step("A3 = \"Hello\"", new Result[] {
                new Result("A3", "Hello     "),
            }),
        }),
        
        new Test(new Step[] {
            new Step("A1 = 1", new Result[] {       // A1 gets 1
                new Result("A1", "         1"),
            }), 
            new Step("A2 = A1", new Result[] {      // A2 gets reference to A1
                new Result("A2", "         1"),
            }),
            new Step("A3 = A1 + A2", new Result[] { // A3 gets sum of references A1 and A2
                new Result("A3", "         2"),
            }),
            new Step("A1 = 2", new Result[] {       // change value of A1
                new Result("A1", "         2"),        // should see the change
                new Result("A2", "         2"),        // and in reference from A2
                new Result("A3", "         4"),        // and in sum of references in A3
            }),
        }),
    };

//  _____   ____    _   _  ____ _______   ______ _____ _____ _______ 
// |  __ \ / __ \  | \ | |/ __ \__   __| |  ____|  __ \_   _|__   __|
// | |  | | |  | | |  \| | |  | | | |    | |__  | |  | || |    | |   
// | |  | | |  | | | . ` | |  | | | |    |  __| | |  | || |    | |   
// | |__| | |__| | | |\  | |__| | | |    | |____| |__| || |_   | |   
// |_____/ \____/  |_| \_|\____/  |_|    |______|_____/_____|  |_|   
//
// Do not edit the code beyond this point.
    
    public static void main(String[] args) {
        
        int passed = 0;
        
        for (int i = 0; i < tests.length; i++) {
            
            // execute the test (which will instantiate a new
            // spreadsheet) and count result if  test  passes

            try {
                tests[i].Execute();
                System.out.println("Passed    : Test " + i);
                passed += 1;
            }
            
            // catch exceptions thrown by the  test  framework
            // to indicate a mismatched output value or result
            
            catch (TestException te) {
                displayException(new TestException("Failed    : Test " + i + ", " + te.getMessage()));
            }
            
            // catch all exceptions thrown by the  spreadsheet.
            // this likely indicates a bug in  the spreadsheet.
            
            catch (Exception e) {
                displayException(new TestException("Exception : Test " + i, e));
            }
        }
        
        // display a summary of all the test results

        System.out.println("\nPassed/Total: " + passed + "/" + tests.length);
    }
    
    private static void displayException(Throwable e) {
        
        // iterate over the exception chain for the exception
        
        while (e != null && e != e.getCause()) {
            if (e instanceof TestException) 
                System.out.println(e.getMessage());
            else {
                System.out.println("    " + e);
                StackTraceElement[] stackTrace = e.getStackTrace();
                
                for (int k = 0; k < stackTrace.length; k++) {
                    System.out.println("        " + stackTrace[k]);
                }
            }
            
            // display any nest exceptions too
            e = e.getCause();
        }
    }

    // all of our member variables are private and have no
    // accessors (because we are the only ones to use them)

    private int       columns;
    private int       rows;
    private Step[]    steps;
    
    // by default, spreadsheets have 7 columns, 20 rows
    
    private static final int DEFAULT_COLUMNS =  7;
    private static final int DEFAULT_ROWS    = 20;
    
    // we have a number of different constructors for
    // creating without or without size and or results
    
    public Test(Step[] commands) {
        this(DEFAULT_COLUMNS, DEFAULT_ROWS, commands);
    }

    public Test(int columns, int rows, Step[] commands) {
        this.columns  = columns;
        this.rows     = rows;
        this.steps = commands;
    }
    
    public void Execute() throws Exception {
        
        // create a spreadsheet of the size specified by the test
        
        VisiCalc spreadsheet = new VisiCalc(this.rows, this.columns);
        
        // iterate over all the commands, pass the input  to  the
        // spreadsheet and verify output against  expected output
        
        for (int i = 0; i < steps.length; i++) {
            try {
                String actual = spreadsheet.calc(steps[i].getCommand());

                CheckOutput(actual, steps[i].getOutput());
                CheckResults(spreadsheet, steps[i].getResults());
            }
            catch (TestException te) {
                throw new TestException("step " + i + ", " + steps[i].getCommand() + ", " + te.getMessage());
            }
        }
    }
    
    
    private void CheckOutput(String actual, String expected) throws TestException {
        CheckValue(actual, expected, "output");
    }
    
    private void CheckResults(VisiCalc spreadsheet, Result[] results) throws TestException {
        if (results == null) return;
        
        for (int i = 0; i < results.length; i++) {
            CheckValue(spreadsheet.getValue(results[i].getCell()), results[i].getValue(), results[i].getCell());
        }
    }
    
    private void CheckValue(String actual, String expected, String message) throws TestException {

        // if both are null then they are the same
        if (actual == null) {
            if (expected == null) return;
        }
        else if (expected != null) {
            if (actual.equals(expected)) return;
            
            if (actual.trim().equalsIgnoreCase(expected.trim())) {
                throw new TestException("variant value for " + message + ", expected = '" + expected + "', actual = '" + actual + "'");
            }
        }
        
        throw new TestException("unexpected value for " + message + ", expected = '" + expected + "', actual = '" + actual + "'");
    }
    
    static class Step {
        
        private String   command;
        private String   output;
        private Result[] results;

        public Step(String input) {
            this(input, null, null);
        }
        
        public Step(String input, Result[] results) {
            this(input, null, results);
        }
        
        public Step(String input, String output) {
            this(input, output, null);
        }

        public Step(String input, String output, Result[] results) {
            this.command   = input;
            this.output  = output;
            this.results = results;
        }
        
        public String getCommand() {
            return this.command;
        }
        
        public String getOutput() {
            return this.output;
        }
        
        public Result[] getResults() {
            return this.results;
        }
    }
    
    static class Result {
        private String cell;
        private String value;
        
        public Result(String cell, String value) {
            this.cell  = cell;
            this.value = value;
        }
        
        public String getCell() {
            return this.cell;
        }

        public String getValue() {
            return this.value;
        }
    }
    
    static class TestException extends Exception {
        TestException(String message) {
            super(message);
        }

        TestException(String message, Throwable innerException) {
            super(message, innerException);
        }
    }
}
