
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Appliance {
    private static HashMap<Appliance, Integer> appliances = new HashMap<Appliance, Integer>();
    private static List<Integer> powers = new ArrayList<Integer>();
    private static List<Integer[]> values = new ArrayList<Integer[]>();
    static String s [] = {"Fridges:", "Ac :", "Tv:"};
    

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Input Total power");
        float power = scan.nextFloat();
        Appliance fridge = new Appliance();
        System.out.print("Input the Number of Fridges: ");
        int fridges = scan.nextInt();
        appliances.put(fridge, fridges);
        Appliance ac = new Appliance();
        System.out.print("Input the Number of Air Conditioners: ");
        int acs = scan.nextInt();
        appliances.put(ac, acs);
        Appliance tv = new Appliance();
        System.out.print("Input the Number of TVs: ");
        int tvs = scan.nextInt();
        appliances.put(tv, tvs);
        
        for(int i=0 ; i <= fridges;i++) {
            for(int j = 0 ; j<=acs; j++) {
                for(int k = 0; k<=tvs; k++) {
                    int o = (200*i + 150*j + 125*k);
                    if(o <= power) {
                        
                        Integer[] b = {i,j,k};
                        powers.add(o);
                        values.add(b);
                    }
                }
            }
        }
        int max = Collections.max(powers);
        for(int i=0; i<powers.size(); i++){
            if (max == powers.get(i)) {
                for(int j=0; j<values.get(i).length;j++) {
                    System.out.print(s[j]);
                    System.out.println(values.get(i)[j]);
                    }
                System.out.println();
                }
            }
        }
    }
    

   /* Assume we have the following appliances each with a power consumption per unit:
            1. Fridge requires 200 watts
            2. Air conditioner requires 150 watts
            3. Television requires 125 watts
    Write a function like follows:
    def appliances_powered(total_power, appliances):
            … your code here …
    Where total_power is a float describing power available from the solar power available
    and appliances is a hash where the keys are the appliance type and the value is the
    number of those appliances. The function should return a hash with the combination of
    appliances that maximizes the power consumed from your solar installation while not going
    over the power available and therefore drawing from the grid.
    For example, if we have the following inputs.
            total_power = 500
 appliances = {
‘fridge’: 1,
‘ac’: 1,
‘tv’: 2
    }
    Calling appliances_powered(total_power, appliances) should return:
    {
‘fridge’: 1,
‘ac’: 1,
‘tv’: 1
    }
    If there are multiple answers that maximize the power usage without exceeding the power
    available, return an array of hashes with the various combinations. */
