package day03;
import java.util.*;
public class ArraysBinarySearch {
	
	static Scanner sc=new Scanner(System.in);
	
	public static void floatBinarySearch() {
		float[] arr= {1.1f, 2.1f, 0.4f, 'A', 'a', 30e-1f, -7.4f};
		System.out.println(Arrays.toString(arr));
		System.out.println("---�������� ���� ��-----------");
		Arrays.sort(arr);
		System.out.println(Arrays.toString(arr));
		
		System.out.println("�˻��� �Ǽ����� �Է��ϼ���: ");
		float key=sc.nextFloat();
		int idx=Arrays.binarySearch(arr, key);
		if(idx<0) {
			System.out.println("idx: "+idx);
			System.out.println("�˻��� "+key+"���� �迭�� �����ϴ�");
		}else {
			System.out.println("�˻��� "+key+"���� �������� ���ĵ� �迭 "+idx+"�� �ε����� �ֽ��ϴ�");
		}
	}//------------------------------------

	public static void stringBinarySearch() {
		/*StringŬ������ Comparable�������̽�(compareTo()�޼��带 �����ϰ� ����)�� ��ӹ޾� �����ϰ� �ֱ� ������ �����̵ȴ�. 
		 * => ������ �Ǹ� �����˻� �����ϴ�.
		 * */
		String[] str= {
				"instanceof", "int",          "interface", "long",       "native",
	            "switch",     "synchronized", "this",      "throw",      "throws",
	            "transient",  "try",          "void",      "volatile",   "while",
	            "new",        "package",      "private",   "protected",  "public",
				"abstract",   "assert",       "boolean",   "break",      "byte",
	            "case",       "catch",        "char",      "class",      "const",
				"continue",   "default",      "do",        "double",     "else",
	            "enum",       "extends",      "final",     "finally",    "float",
	            "for",        "goto",         "if",        "implements", "import",
	            "return",     "short",        "static",    "strictfp",   "super"
		};
		
		System.out.println("---�������� ���� ��-----------");
		System.out.println(Arrays.toString(str));
		
		System.out.println("�˻��� Ű���带 �Է��ϼ���: ");
		String key=sc.next();

		System.out.println("---�������� ���� ��-----------");
		Arrays.sort(str);
		System.out.println(Arrays.toString(str));
		
		int idx=Arrays.binarySearch(str, key);
		if(idx<0) {
			System.out.println("idx: "+idx);
			System.out.println("�˻��� "+key+"���� �迭�� �����ϴ�");
		}else {
			System.out.println("�˻��� "+key+"���� �������� ���ĵ� �迭 "+idx+"�� �ε����� �ֽ��ϴ�");
		}
		
	}//----------------------------------------------
	
	
	public static void stringBinarySearch1() {
		/*StringŬ������ Comparable�������̽�(compareTo()�޼��带 �����ϰ� ����)�� ��ӹ޾� �����ϰ� �ֱ� ������ �����̵ȴ�. 
		 * => ������ �Ǹ� �����˻� �����ϴ�.
		 * */
		String[] str= {
				"instanceof", "int",          "interface", "long",       "native",
	            "switch",     "synchronized", "this",      "throw",      "throws",
	            "transient",  "try",          "void",      "volatile",   "while",
	            "new",        "package",      "private",   "protected",  "public",
				"abstract",   "assert",       "boolean",   "break",      "byte",
	            "case",       "catch",        "char",      "class",      "const",
				"continue",   "default",      "do",        "double",     "else",
	            "enum",       "extends",      "final",     "finally",    "float",
	            "for",        "goto",         "if",        "implements", "import",
	            "return",     "short",        "static",    "strictfp",   "super"
		};
		
		System.out.println("�˻��� Ű���带 �Է��ϼ���: ");
		String key=sc.next();
		Arrays.sort(str);
		System.out.println("---�������� ���� ��-----------");
		System.out.println(Arrays.toString(str));
		
		int idx=Arrays.binarySearch(str, key);
		System.out.println("idx: "+idx);
		
	}//---------------------------------------
	
	
	public static void main(String[] args) {
		//floatBinarySearch();
		//stringBinarySearch1();
		//memberBinarySearch();
		empBinarySearch();
	}

	private static void empBinarySearch() {
		//1. Emp��ü 4�� �����ؼ� �迭�� �����ϼ���
		Emp e1=new Emp("���Ϲ�", 190, 2600);
		Emp e2=new Emp("���̹�", 170, 8600);
		Emp e3=new Emp("����", 160, 3600);
		Emp e4=new Emp("����", 180, 4600);
		
		//2. �迭�� ����غ���
		Emp emps[]= {e1, e2, e3, e4};
		System.out.println(Arrays.toString(emps));
		System.out.println("------sort() ����--------");
		System.out.println();
		//3. HeightOrderComparator��ü �����ϱ�
		final HeightOrderComparator heightComp=new HeightOrderComparator();
		
		//4. Arrays.sort()�� 1�� �迭 �����ϱ�
		Arrays.sort(emps, heightComp);//Ű ������������ ����
		
		//5.  �迭�� ����غ���
		System.out.println(">> Ű�� ����(asc) <<");
		System.out.println(Arrays.toString(emps));
		System.out.println();
		
		//6. �޿� ������������ �ٽ� �����ؼ� ����غ�����
		final SalaryOrderComparator salComp=new SalaryOrderComparator();
		Arrays.sort(emps, salComp);//sal ������������ ����
		System.out.println(">> sal�� ����(desc) <<");
		System.out.println(Arrays.toString(emps));
		
		System.out.println("�޿��� ���� ��������� ã�� �ֳ���?");
		int findSalary=sc.nextInt();
		
		//Arrays binarySearch() �̿��ؼ� �˻��� �˻��� ����� ��� ���� ����ϼ���
		int idx=Arrays.binarySearch(emps, new Emp("",0,findSalary),salComp);
		if(idx<0) {
			System.out.println(findSalary+"�� �޿��� �޴� ����� �����");
		}else {
			System.out.println(idx+"�ε����� �־��");
			System.out.println(emps[idx]);
		}
	}

	public static void memberBinarySearch() {
		//Member 4�� �����ؼ� �迭�� �����ϱ�
		Member m1=new Member("ȫ�浿", 30, 160);
		Member m2=new Member("ȫ���", 24, 181);
		Member m3=new Member("��浿", 21, 167);
		Member m4=new Member("�ڳ���", 26, 172);
		Member m5=new Member("ȫ���", 24, 181);
		
		//�迭�� ������ �� �迭�� ����ϱ�
		Member[] users= {m1, m2, m3, m4, m5};
		System.out.println(Arrays.toString(users));
		
		//Arrays.sort()�� �����ϰ� �迭�� ����ϱ� --> Member�� compareTo()���� ����
		Arrays.sort(users);
		System.out.println("--------sort() ���� Member------------");
		System.out.println(Arrays.toString(users));
		//boolean equals(Object obj) : �ּҰ� ��
		System.out.println("m2: "+m2);
		System.out.println("m5: "+m5);
		
		System.out.println("m2.equals(m5) : "+m2.equals(m5)); //false heap�� �ö󰡴� �޸��� �ּҰ��� �ٸ�
		//m2�� m5�� ����񱳸� �ϰ�ʹٸ� ==> equals()�� �������̵� �ؾ� �Ѵ�

		System.out.println("�˻��� ȸ���� ���̴� : ");
		int findAge=sc.nextInt();
		//user���� findAgeȸ���� ���� ������������ ������ �迭 ��������
		//���° �ε����� �ִ��� ����ϼ���
		int idx=Arrays.binarySearch(users, new Member("",findAge,0));
		if(idx<0) {
			System.out.println(findAge+"�� ȸ���� �����ϴ�");
		}else {
			System.out.println(findAge+"�� ȸ���� ã�ҽ��ϴ� : idx = "+idx);
			System.out.println(users[idx]);
		}
	}
}










