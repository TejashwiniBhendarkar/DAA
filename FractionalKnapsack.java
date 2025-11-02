import java.util.*;

 class Item{
    int weight;
    int value;
    public Item(int weight, int value){
        this.weight=weight;
        this.value=value;
    }
}
public class FractionalKnapsack {
    public static double getMaxValue(int capacity,Item[] items,int n){
        Arrays.sort(items,(a,b) ->
            Double.compare((double)b.value/b.weight,(double)a.value/a.weight)
        );
        double totalValue=0.0;
        int currentWeight=0;

        for(int i=0;i<n;i++){
            if(currentWeight+items[i].weight<=capacity){
                currentWeight+=items[i].weight;
                totalValue+=items[i].value;
            }
            else{
                int rem=capacity-currentWeight;
                totalValue+=((double)items[i].value/items[i].weight)*rem;
                break;
            }
        }
        return totalValue;
    }
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter number of items: ");
        int n=sc.nextInt();

        Item[] items=new Item[n];

        for(int i=0;i<n;i++){
            System.out.print("Enter weight and value of item "+(i+1)+": ");
            int weight=sc.nextInt();
            int value=sc.nextInt();
            items[i]=new Item(weight,value);
        }
        System.out.print("Enter capacity of knapsack: ");
        int capacity=sc.nextInt();

        double maxValue=getMaxValue(capacity,items,n);
        System.out.println("Maximum value in Knapsack = "+maxValue);
    }
}
