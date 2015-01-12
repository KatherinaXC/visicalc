public class Test {
    
    // static array of test cases (so that our static 
    // main method can access and execute  the  tests 
    
    public static Test[] tests = {
    	
    	// CHECKPOINT 1
        new Test("Quit", new Step[] {
            new Step("quit", "Goodbye." ),
        }),
        new Test("Assign text, clear, assign number", 1, 1, new Step[] {
            new Step("A1 = \"Hello\"", new Result[] {
                new Result("A1", "Hello     "),
            }),
            new Step("clear A1", new Result[] {
                new Result("A1", "          "),
            }),
            new Step("A1 = 1", new Result[] {
                new Result("A1", "         1"),
            }),
        }),
        new Test("Assign number, clear, assign text", 1, 1, new Step[] {
            new Step("A1 = 1", new Result[] {
                new Result("A1", "         1"),
            }),
            new Step("clear A1", new Result[] {
                new Result("A1", "          "),
            }),
            new Step("A1 = \"Hello\"", new Result[] {
                new Result("A1", "Hello     "),
            }),
        }),
        new Test("Assign integer", new Step[] {
            new Step("A1 = 1", new Result[] {
                new Result("A1", "         1"),
            }),
        }),
        new Test("Assign negative integer", new Step[] {
                new Step("A1 = -1", new Result[] {
                    new Result("A1", "        -1"),
                }),
            }),
        new Test("Assign string with number", new Step[] {
            new Step("A1 = \"1\"", new Result[] {
                new Result("A1", "1         "),
            }),
        }),
        new Test("Assign string", new Step[] {
            new Step("A2 = \"Hello\"", new Result[] {
                new Result("A2", "Hello     "),
            }),
        }),
        new Test("Assign long string", new Step[] {
            new Step("A3 = \"Good Morning!\"", new Result[] {
                new Result("A3", "Good Morni"),
            }),
        }),
        
        new Test("Assign double (no fraction)", new Step[] {
            new Step("A4 = 1.0", new Result[] {
                new Result("A4", "         1"),
            }),
        }),
        new Test("Assign double (fraction)", new Step[] {
            new Step("A4 = 1.23", new Result[] {
                new Result("A4", "      1.23"),
            }),
        }),
        new Test("Assign long double", new Step[] {
            new Step("A5 = 123456.123456", new Result[] {
                new Result("A5", "##########"),
            }),
        }),
        new Test("Assign string with spaces", new Step[] {
            new Step("A6 = \"A B C D E\"", new Result[] {
                new Result("A6", "A B C D E "),
            }),
        }),
        new Test("Assign lowercase string", new Step[] {
            new Step("A6 = \"abcde\"", new Result[] {
                new Result("A6", "abcde     "),
            }),
        }),
        new Test("Assign lowercase cell name", new Step[] {
            new Step("a7 = 1", new Result[] {
                new Result("a7", "         1"),
            }),
        }),
        new Test("Assign Z99", 26, 99, new Step[] {
            new Step("Z99 = 1",   new Result[] { new Result("Z99", "         1") }),
        }),
        new Test("Clear", 3, 3, new Step[] {
            new Step("A1 = 1"),
            new Step("A2 = 2"),
            new Step("A3 = 3"),
            new Step("B1 = 4"),
            new Step("B2 = 5"),
            new Step("B3 = 6"),
            new Step("C1 = 7"),
            new Step("C2 = 8"),
            new Step("C3 = 9", new Result [] {
                new Result("A1", "         1"),		
                new Result("A2", "         2"),		
                new Result("A3", "         3"),		
                new Result("B1", "         4"),		
                new Result("B2", "         5"),		
                new Result("B3", "         6"),		
                new Result("C1", "         7"),		
                new Result("C2", "         8"),		
                new Result("C3", "         9"),		
            }),
            new Step("clear", new Result[] {
                new Result("A1", "          "),		
                new Result("A2", "          "),		
                new Result("A3", "          "),		
                new Result("B1", "          "),		
                new Result("B2", "          "),		
                new Result("B3", "          "),		
                new Result("C1", "          "),		
                new Result("C2", "          "),		
                new Result("C3", "          "),		
            })
        }),
        new Test("Clear Z99", 26, 99, new Step[] {
            new Step("Z99 = 1",      new Result[] { new Result("Z99", "         1") }),
            new Step("clear Z99",    new Result[] { new Result("Z99", "          ") })
        }),
        new Test("Clear Z99 range", 26, 99, new Step[] {
            new Step("Z99 = 1",      new Result[] { new Result("Z99", "         1") }),
            new Step("clear A1:Z99", new Result[] { new Result("Z99", "          ") })
        }),
        new Test("Clear Z99 spreadsheet", 26, 99, new Step[] {
            new Step("Z99 = 1",      new Result[] { new Result("Z99", "         1") }),
            new Step("clear",        new Result[] { new Result("Z99", "          ") })
        }),
        new Test("Clear cell", 3, 3, new Step[] {
            new Step("A1 = 1"),
            new Step("A2 = 2"),
            new Step("A3 = 3"),
            new Step("B1 = 4"),
            new Step("B2 = 5"),
            new Step("B3 = 6"),
            new Step("C1 = 7"),
            new Step("C2 = 8"),
            
            new Step("C3 = 9", new Result [] {
                new Result("A1", "         1"),		
                new Result("A2", "         2"),		
                new Result("A3", "         3"),		
                new Result("B1", "         4"),		
                new Result("B2", "         5"),		
                new Result("B3", "         6"),		
                new Result("C1", "         7"),		
                new Result("C2", "         8"),		
                new Result("C3", "         9"),		
            }),
            
            new Step("clear A1"),
            new Step("clear A3"),
            new Step("clear C1"),
            
            new Step("clear C3", new Result[] {
                new Result("A1", "          "),		
                new Result("A2", "         2"),		
                new Result("A3", "          "),		
                new Result("B1", "         4"),		
                new Result("B2", "         5"),		
                new Result("B3", "         6"),		
                new Result("C1", "          "),		
                new Result("C2", "         8"),		
                new Result("C3", "          "),		
            })
        }),
        new Test("Clear range", 3, 3, new Step[] {
            new Step("A1 = 1"),
            new Step("A2 = 2"),
            new Step("A3 = 3"),
            new Step("B1 = 4"),
            new Step("B2 = 5"),
            new Step("B3 = 6"),
            new Step("C1 = 7"),
            new Step("C2 = 8"),
            
            new Step("C3 = 9", new Result [] {
                new Result("A1", "         1"),		
                new Result("A2", "         2"),		
                new Result("A3", "         3"),		
                new Result("B1", "         4"),		
                new Result("B2", "         5"),		
                new Result("B3", "         6"),		
                new Result("C1", "         7"),		
                new Result("C2", "         8"),		
                new Result("C3", "         9"),		
            }),
            new Step("clear A1:B2"),
            
            new Step("clear B2:C3", new Result[] {
                new Result("A1", "          "),		
                new Result("A2", "          "),		
                new Result("A3", "         3"),		
                new Result("B1", "          "),		
                new Result("B2", "          "),		
                new Result("B3", "          "),		
                new Result("C1", "         7"),		
                new Result("C2", "          "),		
                new Result("C3", "          "),		
            })
        }),
        new Test("clear lowercase cell name", new Step[] {
            new Step("a8 = 1", new Result[] {
                new Result("a8", "         1"),
            }),
            new Step("clear a8", new Result[] {
                new Result("a8", "          "),
            }),
        }),
        new Test("clear lowercase cell range", new Step[] {
            new Step("a9 = 1", new Result[] {
                new Result("a9", "         1"),
            }),
            new Step("clear a9:a9", new Result[] {
                new Result("a9", "          "),
            }),
        }),
        new Test("Row out of range", 1, 1, new Step[] {
        	new Step("A2 = 1", "Cell reference, A2, is invalid")
        }),
        new Test("Column out of range", 1, 1, new Step[] {
        	new Step("B1 = 1", "Cell reference, B1, is invalid")
        }),
        new Test("Row and column out of range", 1, 1, new Step[] {
        	new Step("B2 = 1", "Cell reference, B2, is invalid")
        }),
        new Test("Clear row out of range", 1, 1, new Step[] {
        	new Step("clear A2", "Cell reference, A2, is invalid")
        }),
        new Test("Clear column out of range", 1, 1, new Step[] {
        	new Step("clear B1", "Cell reference, B1, is invalid")
        }),
        new Test("Clear row and column out of range", 1, 1, new Step[] {
        	new Step("clear B2", "Cell reference, B2, is invalid")
        }),
        new Test("Clear range", 1, 1, new Step[] {
        	new Step("clear A1:A1")
        }),
        new Test("Clear range invalid UL row", 1, 1, new Step[] {
        	new Step("clear A2:A2", "Cell reference, A2, is invalid")
        }),
        new Test("Clear range invalid UL col", 1, 1, new Step[] {
        	new Step("clear B1:B1", "Cell reference, B1, is invalid")
        }),
        new Test("Clear range invalid LR row", 1, 1, new Step[] {
        	new Step("clear A1:A2", "Cell reference, A2, is invalid")
        }),
        new Test("Clear range invalid LR col", 1, 1, new Step[] {
        	new Step("clear A1:B1", "Cell reference, B1, is invalid")
        }),
        new Test("Clear range inversion", 2, 2, new Step[] {
        	new Step("clear B2:A1", "Cell reference, A1, is invalid")
        }),
        new Test("Clear row and column out of range", 1, 1, new Step[] {
        	new Step("clear B2", "Cell reference, B2, is invalid")
        }),
        new Test("Dump invalid row,", 1, 1, new Step[] {
        	new Step("dump A2", "Cell reference, A2, is invalid")
        }),
        new Test("Dump invalid column,", 1, 1, new Step[] {
        	new Step("dump B1", "Cell reference, B1, is invalid")
        }),
        new Test("Dump invalid row and column,", 1, 1, new Step[] {
        	new Step("dump B2", "Cell reference, B2, is invalid")
        }),
        new Test("Dump empty spreadsheet,", 1, 1, new Step[] {
        	new Step("dump", "A1 = { \"Input\" = \"\", \"Value\" = \"\" }")
        }),
        new Test("Dump empty cell,", 1, 1, new Step[] {
            	new Step("dump A1", "A1 = { \"Input\" = \"\", \"Value\" = \"\" }")
            }),
        new Test("Dump empty range", 1, 1, new Step[] {
        	new Step("dump A1:A1", "A1 = { \"Input\" = \"\", \"Value\" = \"\" }")
        }),
        new Test("Dump long string", 1, 1, new Step[] {
            new Step("A1 = \"Good Morning!\""),
        	new Step("dump A1", "A1 = { \"Input\" = \"Good Morning!\", \"Value\" = \"Good Morning!\" }")
        }),
        new Test("Dump long integer", 1, 1, new Step[] {
            new Step("A1 = " + Integer.MIN_VALUE),
        	new Step("dump A1", "A1 = { \"Input\" = \"" + Integer.MIN_VALUE + "\", \"Value\" = \"" + Integer.MIN_VALUE + "\" }")
        }),
        new Test("Dump long double", 1, 1, new Step[] {
            new Step("A1 = -123456.123456"),
        	new Step("dump A1", "A1 = { \"Input\" = \"-123456.123456\", \"Value\" = \"-123456.123456\" }")
        }),
        new Test("Dump", 2, 2, new Step[] {
    		new Step("A1 = 1"),
    		new Step("A2 = 2"),
    		new Step("B1 = \"Hello\""),
    		new Step("B2 = \"World\""),
        	new Step("dump", 
            	"A1 = { \"Input\" = \"1\", \"Value\" = \"1\" }\n" +
            	"A2 = { \"Input\" = \"2\", \"Value\" = \"2\" }\n" +
            	"B1 = { \"Input\" = \"Hello\", \"Value\" = \"Hello\" }\n" +
            	"B2 = { \"Input\" = \"World\", \"Value\" = \"World\" }"
            ),
        }),
        new Test("Dump cell", 2, 2, new Step[] {
    		new Step("A1 = 1"),
    		new Step("A2 = 2"),
    		new Step("B1 = \"Hello\""),
    		new Step("B2 = \"World\""),
        	new Step("dump A1", "A1 = { \"Input\" = \"1\", \"Value\" = \"1\" }"),
        	new Step("dump B2", "B2 = { \"Input\" = \"World\", \"Value\" = \"World\" }"),
        }),
        new Test("Dump range", 2, 2, new Step[] {
    		new Step("A1 = 1"),
    		new Step("A2 = 2"),
    		new Step("B1 = \"Hello\""),
    		new Step("B2 = \"World\""),
        	new Step("dump A1:A2", 
            	"A1 = { \"Input\" = \"1\", \"Value\" = \"1\" }\n" +
            	"A2 = { \"Input\" = \"2\", \"Value\" = \"2\" }"
            ),
        }),
        new Test("Dump Z99", 26, 99, new Step[] {
            new Step("Z99 = 1",      new Result[] { new Result("Z99", "         1") }),
            new Step("dump Z99", "Z99 = { \"Input\" = \"1\", \"Value\" = \"1\" }")
        }),
        
        // CHECKPOINT 2
        new Test("ref = ref", new Step[] {
            new Step("A1 = 1"),
            new Step("A2 = A1", new Result[] {
                new Result("A2", "         1"),
            }),
        }),
        new Test("int + int", new Step[] {
            new Step("A1 = 1 + 1", new Result[] {
                new Result("A1", "         2"),
            }),
        }),
        new Test("ref chain", new Step[] {
            new Step("A1 = 1"),
            new Step("A2 = A1"),
        	new Step("A3 = A2", new Result[] {
                new Result("A3", "         1"),
            }),
        	new Step("A1 = 2", new Result[] {
                new Result("A3", "         2"),
            }),
        }),

        new Test("two way chain", new Step[] {
            new Step("A5 = 1"),
            new Step("A4 = A5"),
            new Step("A6 = A4"),
            new Step("A3 = A6"),
            new Step("A7 = A3"),
            new Step("A2 = A7"),
            new Step("A8 = A2"),
            new Step("A1 = A8"),
        	new Step("A9 = A1", new Result[] {
                new Result("A9", "         1"),
            }),
        	new Step("A5 = 2", new Result[] {
                    new Result("A9", "         2"),
                }),
        }),

        new Test("ref + int", new Step[] {
            new Step("A1 = 1"),
        	new Step("A2 = A1 + 1", new Result[] {
                new Result("A2", "         2"),
            }),
        }),
        new Test("ref + ref", new Step[] {
            new Step("A1 = 1"),
            new Step("A2 = 2"),
            new Step("A3 = A1 + A2", new Result[] {
                new Result("A3", "         3"),
            }),
        }),
        new Test("chain + chain", new Step[] {
            new Step("A1 = 1"),
            new Step("A2 = A1"),
            new Step("A3 = A2"),
            new Step("B1 = 1"),
            new Step("B2 = B1"),
            new Step("B3 = B2"),
        	new Step("A5 = A3 + B3", new Result[] {
                new Result("A5", "         2"),
            }),
        	new Step("A1 = 2", new Result[] {
                new Result("A5", "         3"),
            }),
        	new Step("B1 = 2", new Result[] {
                new Result("A5", "         4"),
            }),
        }),
        new Test("ref(string) + ref(int)", new Step[] {
           	new Step("A1 = \"Hello\""),
            new Step("A2 = 1"),
        	new Step("A3 = A1 + A2", new Result[] {
                new Result("A3", "#VALUE!   "),
            }),
        }),
        new Test("ref(string) + int", new Step[] {
        	new Step("A1 = \"Hello\""),
        	new Step("A2 = A1 + 1", new Result[] {
                new Result("A2", "#VALUE!   "),
            }),
        }),
        new Test("#VALUE! + int", new Step[] {
            new Step("A1 = \"Hello\""),
        	new Step("A2 = A1 + 1"),
        	new Step("A3 = A2 + 1", new Result[] {
                new Result("A3", "#VALUE!   "),
            }),
        }),
        new Test("#VALUE! + ref(int)", new Step[] {
            new Step("A1 = \"Hello\""),
        	new Step("A2 = A1 + 1"),
        	new Step("A3 = 1"),
        	new Step("A4 = A2 + A3", new Result[] {
                new Result("A4", "#VALUE!   "),
            }),
        }),
        new Test("ref = invalidref", new Step[] {
        	new Step("A1 = H21", new Result[] {
                new Result("A1", "#REF!     "),
            }),
        }),
        new Test("invalidref + int", new Step[] {
            new Step("A1 = H21 + 1", new Result[] {
                new Result("A1", "#REF!     "),
            }),
        }),
        new Test("invalidref + ref(int)", new Step[] {
            new Step("A1 = 1"),
            new Step("A2 = H21 + A1", new Result[] {
                new Result("A2", "#REF!     "),
            }),
        }),
        new Test("#REF! + int", new Step[] {
            new Step("A1 = H21"),
        	new Step("A2 = A1 + 1", new Result[] {
                new Result("A2", "#REF!     "),
            }),
        }),
        new Test("#REF! + ref(int)", new Step[] {
            new Step("A1 = H21"),
            new Step("A2 = 1"),
        	new Step("A3 = H21 + A1", new Result[] {
                new Result("A3", "#REF!     "),
            }),
        }),

        // CHECKPOINT 3
        new Test("SUM(ref)", new Step[] {
            new Step("A1 = 1"),
            new Step("A2 = SUM(A1)", new Result[] {
                new Result("A2", "         1"),
            }),
        }),
        new Test("SUM(ref) update", new Step[] {
            new Step("A1 = 1"),
            new Step("A2 = SUM(A1)"),
            new Step("A1 = 2", new Result[] {
                new Result("A2", "         2"),
            }),
        }),
        new Test("SUM(ref, num)", new Step[] {
            new Step("A1 = 1"),
            new Step("A2 = SUM(A1, 1)", new Result[] {
                new Result("A2", "         2"),
            }),
        }),
        new Test("SUM(ref, num) update", new Step[] {
            new Step("A1 = 1"),
            new Step("A2 = SUM(A1, 1)"),
            new Step("A1 = 2", new Result[] {
                new Result("A2", "         3"),
            }),
        }),
        new Test("SUM(ref, ref)", new Step[] {
            new Step("A1 = 1"),
            new Step("A2 = SUM(A1, A1)", new Result[] {
                new Result("A2", "         2"),
            }),
        }),
        new Test("SUM(ref, ref) update", new Step[] {
            new Step("A1 = 1"),
            new Step("A2 = SUM(A1, A1)"),
            new Step("A1 = 2", new Result[] {
                new Result("A2", "         4"),
            }),
        }),
        new Test("SUM(range)", new Step[] {
            new Step("A1 = 1"),
            new Step("A2 = 2"),
            new Step("B1 = 3"),
            new Step("B2 = 4"),
            new Step("C3 = SUM(A1:B2)", new Result[] {
                new Result("C3", "        10"),
            }),
        }),
        new Test("SUM(range) update", new Step[] {
            new Step("A1 = 1"),
            new Step("A2 = 2"),
            new Step("B1 = 3"),
            new Step("B2 = 4"),
            new Step("C3 = SUM(A1:B2)", new Result[] {
                new Result("C3", "        10"),
            }),
            new Step("A1 = 11", new Result[] {
                new Result("C3", "        20"),
            }),
            new Step("A2 = 12", new Result[] {
                new Result("C3", "        30"),
            }),
            new Step("B1 = 13", new Result[] {
                new Result("C3", "        40"),
            }),
            new Step("B2 = 14", new Result[] {
                new Result("C3", "        50"),
            }),
        }),
        new Test("SUM(ref, range)", new Step[] {
            new Step("A1 = 1"),
            new Step("A2 = SUM(A1, A1:A1)", new Result[] {
                new Result("A2", "         2"),
            }),
        }),
        new Test("SUM(range, range)", new Step[] {
            new Step("A1 = 1"),
            new Step("A2 = SUM(A1:A1, A1:A1)", new Result[] {
                new Result("A2", "         2"),
            }),
        }),
        new Test("SUM(ref, range, num)", new Step[] {
            new Step("A1 = 1"),
            new Step("A2 = SUM(A1, A1:A1, 1)", new Result[] {
                new Result("A2", "         3"),
            }),
        }),
        new Test("SUM(#REF!, 1)", new Step[] {
            new Step("A1 = SUM(H21, 1)", new Result[] {
                new Result("A1", "#REF!     "),
            }),
        }),
        new Test("SUM(ref(#REF!), 1)", new Step[] {
            new Step("A1 = H21"),
            new Step("A2 = SUM(A1, 1)", new Result[] {
                new Result("A2", "#REF!     "),
            }),
        }),
        new Test("SUM(range(#DIV/0!))", new Step[] {
            new Step("A1 = 0"),
            new Step("A2 = A1 / 0", new Result[] { new Result("A2", "#DIV/0!   ") }),
           	new Step("A3 = SUM(A1:A2)", new Result[] { new Result("A3", "#DIV/0!   ") }),
            new Step("dump A3", "A3 = { \"Input\" = \"SUM(A1:A2)\", \"Value\" = \"#DIV/0!\" }")
        }),
        new Test("SUM(number, number) truncate", new Step[] {
            new Step("A1 = 0.1234567"),
            new Step("A2 = 1234567"),
            new Step("A3 = SUM(A1:A2)", new Result[] {
                new Result("A3", "##########"),
            }),
        }),
        new Test("SUM(real, real, real)", new Step[] {
            new Step("A1 = 1.1"),
            new Step("A2 = 2.2"),
            new Step("A3 = 3.3"),
            new Step("A4 = SUM(A1:A3)", new Result[] {
                new Result("A4", "       6.6"),
            }),
        }),
        new Test("SUM(range) high", 26, 99, new Step[] {
            new Step("X97 = 1"),
            new Step("X98 = 1"),
            new Step("Y97 = 1"),
            new Step("Y98 = 1"),
           	new Step("Z99 = SUM(X97:Y98)", new Result[] { new Result("Z99", "         4") }),
            new Step("dump Z99", "Z99 = { \"Input\" = \"SUM(X97:Y98)\", \"Value\" = \"4\" }")
        }),
        new Test("CONCAT(string)", new Step[] {
            new Step("A1 = CONCAT(\"Hello\")", new Result[] {
                new Result("A1", "Hello     "),
            }),
        }),
        new Test("CONCAT(ref)", new Step[] {
            new Step("A1 = \"Hello\""),
            new Step("A2 = CONCAT(A1)", new Result[] {
                new Result("A2", "Hello     "),
            }),
            new Step("dump A2", "A2 = { \"Input\" = \"CONCAT(A1)\", \"Value\" = \"Hello\" }"),
            new Step("A1 = \"World\"", new Result[] {
                new Result("A2", "World     "),
            }),
        }),
        new Test("CONCAT(ref, string)", new Step[] {
            new Step("A1 = \"Hello\""),
            new Step("A2 = CONCAT(A1, \" World!\")", new Result[] {
                new Result("A2", "Hello Worl"),
            }),
            new Step("dump A2", "A2 = { \"Input\" = \"CONCAT(A1, \" World!\")\", \"Value\" = \"Hello World!\" }")
        }),
        new Test("CONCAT(ref, ref)", new Step[] {
            new Step("A1 = \"Hello\""),
            new Step("A2 = \" World!\""),
            new Step("A3 = CONCAT(A1, A2)", new Result[] {
                new Result("A3", "Hello Worl"),
            }),
            new Step("dump A3", "A3 = { \"Input\" = \"CONCAT(A1, A2)\", \"Value\" = \"Hello World!\" }")
        }),
        new Test("CONCAT(range)", new Step[] {
            new Step("A1 = \"Hello\""),
            new Step("A2 = \" World!\""),
            new Step("A3 = CONCAT(A1:A2)", new Result[] {
                new Result("A3", "Hello Worl"),
            }),
            new Step("dump A3", "A3 = { \"Input\" = \"CONCAT(A1:A2)\", \"Value\" = \"Hello World!\" }")
        }),
        new Test("CONCAT(ref, range)", new Step[] {
            new Step("A1 = \"Hello\""),
            new Step("A2 = \" World!\""),
            new Step("A3 = CONCAT(A1, A2:A2)", new Result[] {
                new Result("A3", "Hello Worl"),
            }),
            new Step("dump A3", "A3 = { \"Input\" = \"CONCAT(A1, A2:A2)\", \"Value\" = \"Hello World!\" }")
        }),
        new Test("CONCAT(range, range)", new Step[] {
            new Step("A1 = \"Hello\""),
            new Step("A2 = \" World!\""),
            new Step("A3 = CONCAT(A1:A1, A2:A2)", new Result[] {
                new Result("A3", "Hello Worl"),
            }),
            new Step("dump A3", "A3 = { \"Input\" = \"CONCAT(A1:A1, A2:A2)\", \"Value\" = \"Hello World!\" }")
        }),
        new Test("CONCAT(range, string)", new Step[] {
            new Step("A1 = \"Hello\""),
            new Step("A2 = CONCAT(A1:A1, \" World!\")", new Result[] {
                new Result("A2", "Hello Worl"),
            }),
            new Step("dump A2", "A2 = { \"Input\" = \"CONCAT(A1:A1, \" World!\")\", \"Value\" = \"Hello World!\" }")
        }),
        new Test("CONCAT(number, string)", new Step[] {
            new Step("A1 = CONCAT(1, \" World!\")", new Result[] {
                new Result("A1", "1 World!  "),
            }),
            new Step("dump A1", "A1 = { \"Input\" = \"CONCAT(1, \" World!\")\", \"Value\" = \"1 World!\" }")
        }),
        new Test("CONCAT(number, ref)", new Step[] {
            new Step("A1 = \" World!\""),
            new Step("A2 = CONCAT(1, A1)", new Result[] {
                new Result("A2", "1 World!  "),
            }),
            new Step("dump A2", "A2 = { \"Input\" = \"CONCAT(1, A1)\", \"Value\" = \"1 World!\" }")
        }),
        new Test("CONCAT(number, range)", new Step[] {
            new Step("A1 = \" World!\""),
            new Step("A2 = CONCAT(1, A1:A1)", new Result[] {
                new Result("A2", "1 World!  "),
            }),
            new Step("dump A2", "A2 = { \"Input\" = \"CONCAT(1, A1:A1)\", \"Value\" = \"1 World!\" }")
        }),
        new Test("CONCAT(number, ref, range, string)", new Step[] {
            new Step("A1 = \" Hello\""),
            new Step("A2 = \" World\""),
            new Step("A3 = CONCAT(1, A1, A2:A2, \" is enough\")", new Result[] {
                new Result("A3", "1 Hello Wo"),
            }),
            new Step("dump A3", "A3 = { \"Input\" = \"CONCAT(1, A1, A2:A2, \" is enough\")\", \"Value\" = \"1 Hello World is enough\" }")
        }),
        new Test("CONCAT(string) with colon in strings", new Step[] {
            new Step("A1 = CONCAT(\"A1:A2\", \"B1:B2\")", new Result[] {
                new Result("A1", "A1:A2B1:B2"),
            }),
            new Step("dump A1", "A1 = { \"Input\" = \"CONCAT(\"A1:A2\", \"B1:B2\")\", \"Value\" = \"A1:A2B1:B2\" }")
        }),
        new Test("CONCAT(ref(string), ref(string)) with colon strings", new Step[] {
            new Step("A1 = \"A1:A2\""),
            new Step("A2 = \"B1:B2\""),
            new Step("A3 = CONCAT(A1, A2)", new Result[] {
                new Result("A3", "A1:A2B1:B2"),
            }),
            new Step("dump A3", "A3 = { \"Input\" = \"CONCAT(A1, A2)\", \"Value\" = \"A1:A2B1:B2\" }")
        }),
        new Test("CONCAT(range) with colon strings", new Step[] {
            new Step("A1 = \"A1:A2\""),
            new Step("A2 = \"B1:B2\""),
            new Step("A3 = CONCAT(A1:A2)", new Result[] {
                new Result("A3", "A1:A2B1:B2"),
            }),
            new Step("dump A3", "A3 = { \"Input\" = \"CONCAT(A1:A2)\", \"Value\" = \"A1:A2B1:B2\" }")
        }),
        new Test("CONCAT(string, string, string)", new Step[] {
            new Step("A1 = CONCAT(\"A, \", \"B, \", \"C\")", new Result[] {
                new Result("A1", "A, B, C   "),
            }),
            new Step("dump A1", "A1 = { \"Input\" = \"CONCAT(\"A, \", \"B, \", \"C\")\", \"Value\" = \"A, B, C\" }")
        }),
        new Test("CONCAT(ref(string), ref(string), ref(string))", new Step[] {
            new Step("A1 = \"A, \""),
            new Step("A2 = \"B, \""),
            new Step("A3 = \"C\""),
            new Step("A4 = CONCAT(A1, A2, A3)", new Result[] {
                new Result("A4", "A, B, C   "),
            }),
            new Step("dump A4", "A4 = { \"Input\" = \"CONCAT(A1, A2, A3)\", \"Value\" = \"A, B, C\" }" ),
            new Step("A1 = \"D, \"", new Result[] {
                new Result("A4", "D, B, C   "),
            }),
            new Step("A2 = \"E, \"", new Result[] {
                new Result("A4", "D, E, C   "),
            }),
            new Step("A3 = \"F\"", new Result[] {
                new Result("A4", "D, E, F   "),
            }),
            new Step("dump A4", "A4 = { \"Input\" = \"CONCAT(A1, A2, A3)\", \"Value\" = \"D, E, F\" }" )
        }),
        new Test("CONCAT(range) high", 26, 99, new Step[] {
            new Step("X97 = 1"),
            new Step("X98 = 1"),
            new Step("Y97 = 1"),
            new Step("Y98 = 1"),
           	new Step("Z99 = CONCAT(X97:Y98)", new Result[] { new Result("Z99", "1111      ") }),
            new Step("dump Z99", "Z99 = { \"Input\" = \"CONCAT(X97:Y98)\", \"Value\" = \"1111\" }")
        }),
        new Test("COUNT(string)", new Step[] {
            new Step("A1 = COUNT(\"Hello World!\")", new Result[] { new Result("A1", "         0") }),
            new Step("dump A1", "A1 = { \"Input\" = \"COUNT(\"Hello World!\")\", \"Value\" = \"0\" }")
        }),
        new Test("COUNT(number)", new Step[] {
            new Step("A1 = COUNT(1)", new Result[] { new Result("A1", "         1") }),
            new Step("dump A1", "A1 = { \"Input\" = \"COUNT(1)\", \"Value\" = \"1\" }")
        }),
        new Test("COUNT(ref(string))", new Step[] {
            new Step("A1 = \"Hello World!\""),
           	new Step("A2 = COUNT(A1)", new Result[] { new Result("A2", "         0") }),
            new Step("dump A2", "A2 = { \"Input\" = \"COUNT(A1)\", \"Value\" = \"0\" }")
        }),
        new Test("COUNT(ref(number))", new Step[] {
            new Step("A1 = 1"),
           	new Step("A2 = COUNT(A1)", new Result[] { new Result("A2", "         1") }),
            new Step("dump A2", "A2 = { \"Input\" = \"COUNT(A1)\", \"Value\" = \"1\" }")
        }),
        new Test("COUNT(range)", new Step[] {
            new Step("A1 = 1"),
            new Step("A2 = 1"),
            new Step("B1 = 1"),
            new Step("B2 = 1"),
           	new Step("C3 = COUNT(A1:B2)", new Result[] { new Result("C3", "         4") }),
            new Step("dump C3", "C3 = { \"Input\" = \"COUNT(A1:B2)\", \"Value\" = \"4\" }")
        }),
        new Test("COUNT(range) update strings to numbers)", new Step[] {
            new Step("A1 = \"A\""),
            new Step("A2 = \"A\""),
            new Step("B1 = \"A\""),
            new Step("B2 = \"A\""),
            new Step("C3 = COUNT(A1:B2)", new Result[] { new Result("C3", "         0") }),
           	new Step("dump C3", "C3 = { \"Input\" = \"COUNT(A1:B2)\", \"Value\" = \"0\" }"),
            new Step("A1 = 1", new Result[] { new Result("C3", "         1") }),
            new Step("A2 = 1", new Result[] { new Result("C3", "         2") }),
            new Step("B1 = 1", new Result[] { new Result("C3", "         3") }),
            new Step("B2 = 1", new Result[] { new Result("C3", "         4") }),
           	new Step("dump C3", "C3 = { \"Input\" = \"COUNT(A1:B2)\", \"Value\" = \"4\" }"),
        }),
        new Test("COUNT(range) update numbers to strings", new Step[] {
            new Step("A1 = 1"),
            new Step("A2 = 1"),
            new Step("B1 = 1"),
            new Step("B2 = 1"),
            new Step("C3 = COUNT(A1:B2)", new Result[] { new Result("C3", "         4") }),
           	new Step("dump C3", "C3 = { \"Input\" = \"COUNT(A1:B2)\", \"Value\" = \"4\" }"),
            new Step("A1 = \"A\"", new Result[] { new Result("C3", "         3") }),
            new Step("A2 = \"A\"", new Result[] { new Result("C3", "         2") }),
            new Step("B1 = \"A\"", new Result[] { new Result("C3", "         1") }),
            new Step("B2 = \"A\"", new Result[] { new Result("C3", "         0") }),
           	new Step("dump C3", "C3 = { \"Input\" = \"COUNT(A1:B2)\", \"Value\" = \"0\" }"),
        }),
        new Test("COUNT(range(strings))", new Step[] {
            new Step("A1 = \"A\""),
            new Step("A2 = \"B\""),
            new Step("B1 = \"C\""),
            new Step("B2 = \"D\""),
           	new Step("C3 = COUNT(A1:B2)", new Result[] { new Result("C3", "         0") }),
            new Step("dump C3", "C3 = { \"Input\" = \"COUNT(A1:B2)\", \"Value\" = \"0\" }")
        }),
        new Test("COUNT(range(mixed))", new Step[] {
            new Step("A1 = 1"),
            new Step("A2 = \"B\""),
            new Step("B1 = \"C\""),
            new Step("B2 = 2"),
           	new Step("C3 = COUNT(A1:B2)", new Result[] { new Result("C3", "         2") }),
            new Step("dump C3", "C3 = { \"Input\" = \"COUNT(A1:B2)\", \"Value\" = \"2\" }")
        }),
        new Test("COUNT(range(#REF))", new Step[] {
            new Step("A1 = H21"),
            new Step("A2 = 1"),
           	new Step("A3 = COUNT(A1:A2)", new Result[] { new Result("A3", "#REF!     ") }),
            new Step("dump A3", "A3 = { \"Input\" = \"COUNT(A1:A2)\", \"Value\" = \"#REF!\" }")
        }),
        new Test("COUNT(range) high", 26, 99, new Step[] {
            new Step("X97 = 1"),
            new Step("X98 = 1"),
            new Step("Y97 = 1"),
            new Step("Y98 = 1"),
           	new Step("Z99 = COUNT(X97:Y98)", new Result[] { new Result("Z99", "         4") }),
            new Step("dump Z99", "Z99 = { \"Input\" = \"COUNT(X97:Y98)\", \"Value\" = \"4\" }")
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
        int warnings = 0;
        
        for (int i = 0; i < tests.length; i++) {
            
            // execute the test (which will instantiate a new
            // spreadsheet) and count result if  test  passes

            try {
                tests[i].Execute();
                System.out.println("Passed    : Test '" + tests[i].name + "'");
                passed += 1;
            }
            
            // catch exceptions thrown by the  test  framework
            // to indicate a mismatched output value or result
            
            catch (TestException te) {
                displayException(new TestException("Failed    : Test '" + tests[i].name + "'\n" + te.getMessage()));
            }
            
            catch (TestWarning tw) {
            	warnings += 1;
                displayException(new TestWarning("Warning   : Test '" + tests[i].name + "'\n" + tw.getMessage()));
            }
            
            // catch all exceptions thrown by the  spreadsheet.
            // this likely indicates a bug in  the spreadsheet.
            
            catch (Exception e) {
                displayException(new TestException("Exception : Test '" + tests[i].name + "'", e));
            }
        }
        
        // display a summary of all the test results

        System.out.println("\nPassed/Warning/Total: " + passed + "/" + warnings + "/" + tests.length);
    }
    
    private static void displayException(Throwable e) {
        
        // iterate over the exception chain for the exception
        
        while (e != null && e != e.getCause()) {
            if (e instanceof TestWarning) 
                System.out.println(e.getMessage());
            else if (e instanceof TestException) 
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

    private String    name;
    private int       columns;
    private int       rows;
    private Step[]    steps;
    
    // by default, spreadsheets have 7 columns, 20 rows
    
    private static final int DEFAULT_COLUMNS =  7;
    private static final int DEFAULT_ROWS    = 20;
    
    // we have a number of different constructors for
    // creating without or without size and or results
    
    public Test(String name, Step[] commands) {
        this(name, DEFAULT_COLUMNS, DEFAULT_ROWS, commands);
    }

    public Test(String name, int columns, int rows, Step[] commands) {
    	this.name     = name;
        this.columns  = columns;
        this.rows     = rows;
        this.steps    = commands;
    }
    
    public void Execute() throws Exception {
        
        // create a spreadsheet of the size specified by the test
        
        VisiCalc spreadsheet = new VisiCalc(this.columns, this.rows);
        
        // iterate over all the commands, pass the input  to  the
        // spreadsheet and verify output against  expected output

        String warnings = "";
        
        for (int i = 0; i < steps.length; i++) {
            try {
                String actual = spreadsheet.calc(steps[i].getCommand());

                CheckOutput(actual, steps[i].getOutput());
                CheckResults(spreadsheet, steps[i].getResults());
            }
            catch (TestWarning tw) {
                if (! warnings.equals("")) warnings += "\n";
                warnings += "    step " + i + ", " + tw.getMessage();
            }
            catch (TestException te) {
                throw new TestException("    step " + i + ", " + steps[i].getCommand() + ", " + te.getMessage());
            }
            catch (Exception e) {
                throw new Exception("step " + i + ", " + steps[i].getCommand() + ", " + e.getMessage(), e);
            }
        }
        
        if (! warnings.equals("")) {
        	throw new TestWarning(warnings);
        }
    }
    
    
    private void CheckOutput(String actual, String expected) throws Exception {
    	if (actual != null) {
    		if (actual.endsWith("\n")) actual = actual.substring(0, actual.length() - 1);
        	actual = actual.replace(", \"Alignment\" = \"auto\", \"Width\" = \"10\"", "");
    	}
    	
        CheckValue(actual, expected, "output");
    }
    
    private void CheckResults(VisiCalc spreadsheet, Result[] results) throws Exception {
        if (results == null) return;
        String warnings = "";
        
        for (int i = 0; i < results.length; i++) {
            try {
            	CheckValue(spreadsheet.getValue(results[i].getCell()), results[i].getValue(), results[i].getCell());
            }
            catch (TestWarning tw) {
            	warnings += "\n        result " + i + ", " + tw.getMessage();
            }
        }
        
        if (! warnings.equals("")) {
        	throw new TestWarning(warnings);
        }
    }
    
    private void CheckValue(String actual, String expected, String message) throws Exception {

        // if both are null then they are the same
        if (actual == null) {
            if (expected == null)
            	return;
            else if (expected.equals("")) {
                throw new TestWarning("variant value for " + message + ", expected = null, actual = '" + actual + "'");
            }
        }
        else if (expected != null) {
            if (actual.equals(expected)) return;
            
            if (actual.trim().equalsIgnoreCase(expected.trim())) {
                throw new TestWarning("variant value for " + message + ", expected = '" + expected + "', actual = '" + actual + "'");
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
    
    static class TestWarning extends Exception {
        TestWarning(String message) {
            super(message);
        }

        TestWarning(String message, Throwable innerException) {
            super(message, innerException);
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
