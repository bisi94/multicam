package day01;

public class Q03_GopTable {

	public static void printTable() {
		System.out.println("  |  1   2   3   4   5   6   7   8   9  ");
		System.out.println("--+-------------------------------------");
		for(int i=1; i<10; i++) {
			System.out.printf("%d |",i);
			for(int j=1; j<10; j++) {
				if(j<9) {
					System.out.printf(" %2d ",i*j);
				}else if(j==9) {
					System.out.printf(" %2d \n",i*j);
				}
			}
		}
	}
	
	public static void printTable2() {
		System.out.printf("   |");
		for(int i=1;i<10;i++) {
			System.out.printf("%3d", i);
		}//for--------
		System.out.println();
		System.out.println("---+--------------------------------");
		for(int i=1;i<10;i++) {
			System.out.printf("%2d |", i);
			for(int j=1;j<10;j++) {
				System.out.printf("%3d", (i*j));
			}
			System.out.println();
			}//ifor--------------------
		}//ofor------------------
		
	
	
	public static void main(String[] args) {
		
		printTable();
	}
}
