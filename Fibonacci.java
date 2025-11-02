import java.util.Scanner;
public class Fibonacci {

    static int stepCount=0;

    public static int RecursiveFib(int n){
        stepCount++;
        if(n==0){
            return 0;
        }
        if(n==1){
            return 1;
        }
        return RecursiveFib(n-1) + RecursiveFib(n-2);
    }

    public static int IterativeFib(int n){
        int t1=0;
        int t2=1;
        int next=0;
        for(int i=2;i<=n;i++){
            stepCount++;
            next=t1+t2;
            t1=t2;
            t2=next;
        }
        return next;
    }
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter the value of n: ");
        int n=sc.nextInt();
        
        stepCount=0;
        int recResult=RecursiveFib(n);
        System.out.println("Recursive Fibonacci of "+n+" is: "+recResult);  
        System.out.println("Steps taken in Recursive approach: "+stepCount);

        stepCount=0;
        int iterResult=IterativeFib(n); 
        System.out.println("Iterative Fibonacci of "+n+" is: "+iterResult);  
        System.out.println("Steps taken in Iterative approach: "+stepCount);
    }
}
