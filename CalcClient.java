import CalcApp.*;
import CalcApp.CalcPackage.DivisionByZero;

import org.omg.CosNaming.*;
import org.omg.CORBA.*;

import java.io.*;

public class CalcClient {

    static BufferedReader br =
        new BufferedReader(new InputStreamReader(System.in));

    public static void main(String args[]) {
        try {
            ORB orb = ORB.init(args, null);

            org.omg.CORBA.Object objRef =
                orb.resolve_initial_references("NameService");

            NamingContextExt ncRef =
                NamingContextExtHelper.narrow(objRef);

            Calc calc =
                CalcHelper.narrow(ncRef.resolve_str("Calc"));

            while (true) {
                System.out.println("\n1.Sum 2.Sub 3.Mul 4.Div 5.Exit");
                System.out.print("Choice: ");

                int ch = Integer.parseInt(br.readLine());

                if (ch == 5) break;

                float a = input("a");
                float b = input("b");

                switch (ch) {
                    case 1:
                        System.out.println("Result: " + calc.sum(a, b));
                        break;
                    case 2:
                        System.out.println("Result: " + calc.sub(a, b));
                        break;
                    case 3:
                        System.out.println("Result: " + calc.mul(a, b));
                        break;
                    case 4:
                        try {
                            System.out.println("Result: " + calc.div(a, b));
                        } catch (DivisionByZero e) {
                            System.out.println("Division by zero!");
                        }
                        break;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static float input(String name) throws Exception {
        System.out.print(name + ": ");
        return Float.parseFloat(br.readLine());
    }
}
