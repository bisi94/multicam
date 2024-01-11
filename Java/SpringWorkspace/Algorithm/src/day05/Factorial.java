package day05;
import java.util.*;
/*n!
1! : 1
2! : (1)x 2
3! : (1x2)x3 ====>6
4! : (1x2x3)x4 ====>24
5! : (1x2x3x4)x5 ===> 120
n! : (n-1)!xn
���⼭ 1!=1 �׸��� n!=nx(n-1)! ��� ���丮�� ������ �̿��ؼ� ���α׷��� ������
 * */
public class Factorial {
	
	public static int factorial(int num) {
		if(num<1) return 1;
		String str=(num==1)? "=":"x";
		System.out.print(num+str);
		return num* factorial(num-1);
	}//-----------------------
	
	
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		System.out.println("������ �Է��ϼ���");
		int num=sc.nextInt();
		int result=factorial(num);
		System.out.println(result);
	}

}/////////////////////////
