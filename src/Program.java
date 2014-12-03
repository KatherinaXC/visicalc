import java.util.Scanner;

public class Program {

	public static void main(String[] args) {
		System.out.println("VisiCalc by Zhou, Joyce");

		String   output   = "";
		Scanner  scanner  = new Scanner(System.in);
		VisiCalc visiCalc = new VisiCalc(7, 20);
		
		while (output == null || ! output.equals("Goodbye.")) {
			
			System.out.println(visiCalc);
			if (output != null) System.out.println(output);
			
			System.out.print("> ");
			String input = scanner.nextLine().trim();
			output = visiCalc.calc(input);
		}
		
		scanner.close();
		System.out.println("Goodbye.");
	}
}
